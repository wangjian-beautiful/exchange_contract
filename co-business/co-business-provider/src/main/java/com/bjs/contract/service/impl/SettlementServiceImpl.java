package com.bjs.contract.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bijinsuo.common.constants.PositionDataCacheFieldConstant;
import com.bijinsuo.common.redis.constant.RedisCacheKey;
import com.bijinsuo.common.redis.utils.RedisUtil;
import com.bijinsuo.common.utils.PositionUtil;
import com.bijinsuo.common.utils.entity.MatchTradeDetailsDTO;
import com.bijinsuo.common.utils.enums.PositionSideEnum;
import com.bijinsuo.common.utils.enums.TransactionSceneEnum;
import com.bjs.contract.AccountSymbolConstant;
import com.bjs.contract.action.AccountAction;
import com.bjs.contract.domain.UserPositionDetail;
import com.bjs.contract.entity.CoOrder;
import com.bjs.contract.entity.CoPositionOrder;
import com.bjs.contract.entity.Settlement;
import com.bjs.contract.mapper.SettlementMapper;
import com.bjs.contract.proto.contractConfig.ContractConfigPO;
import com.bjs.contract.service.SettlementService;
import com.bjs.contract.utils.ContractConfigUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

/**
 * 
 *
 * @author bjs code generator
 * @date 2022-11-11 10:54:13
 */
@Service
public class SettlementServiceImpl extends ServiceImpl<SettlementMapper, Settlement> implements SettlementService {

    @Resource
    private AccountAction accountAction;

    /**
     * 清算
     */
    public void liquidation(CoOrder userOrder, CoPositionOrder coPositionOrder, MatchTradeDetailsDTO matchTradeDetailsDTO) {
//        String dataKey = this.getCacheDataKey(coPositionOrder);
        BigDecimal profit = getProfitByTrade(coPositionOrder, matchTradeDetailsDTO);
        BigDecimal fee = coPositionOrder.getCloseFee();
//        BigDecimal pnl = RedisUtil.instance().hmget(dataKey, PositionDataCacheFieldConstant.PNL);
//        BigDecimal fee = RedisUtil.instance().hmget(dataKey, PositionDataCacheFieldConstant.CLOSE_FEE);
        Settlement settlement = createSettlementByTrade(userOrder, coPositionOrder.getId(), matchTradeDetailsDTO);
        settlement.setProfit(profit);
        settlement.setFee(fee);
        save(settlement);
    }

    /**
     *
     * @param coOrder
     * @param coPositionOrder
     * @param matchTradeDetailsDTO
     * @param deltaMargin
     * @param sceneEnum
     * @return
     */
    @Override
    public void settlement (CoOrder coOrder, CoPositionOrder coPositionOrder, MatchTradeDetailsDTO matchTradeDetailsDTO, BigDecimal deltaMargin, BigDecimal fee, TransactionSceneEnum sceneEnum) {
        if (TransactionSceneEnum.SCALE_IN == sceneEnum) {
            this.openPosition(coOrder, deltaMargin, fee, sceneEnum);
        } else if (TransactionSceneEnum.CLOSE_POSITION == sceneEnum) {
            this.closePosition(coOrder, coPositionOrder, matchTradeDetailsDTO, deltaMargin, fee, sceneEnum);
        } else if (TransactionSceneEnum.LIQUIDATION == sceneEnum) {
            this.liquidation(coOrder, coPositionOrder, matchTradeDetailsDTO);
        }
    }

    private void openPosition(CoOrder coOrder, BigDecimal deltaMargin, BigDecimal fee, TransactionSceneEnum sceneEnum) {
        //调用账户相关扣减  需要设置利润
        boolean accountResult = accountAction.operateAccount(coOrder.getUid(),
                deltaMargin.abs(),
                AccountSymbolConstant.USDT,
                sceneEnum.getValue(),
                "co_order",
                coOrder.getId(), BigDecimal.ZERO, BigDecimal.ZERO, fee);

        //账户操作不成功
        if (!accountResult) {
            log.error("openPosition uid=" + coOrder.getUid() +
                    ",deltaMargin=" + deltaMargin.toPlainString() +
                    ",symbol=" + AccountSymbolConstant.USDT +
                    ",sceneEnum=" + sceneEnum.getValue() +
                    ",fee=" + fee);
            throw new RuntimeException("CoPositionOrderServiceImpl -> mergePositionForTrade operateAccount error");
        }
    }

    private void closePosition (CoOrder coOrder, CoPositionOrder coPositionOrder, MatchTradeDetailsDTO matchTradeDetailsDTO, BigDecimal deltaMargin, BigDecimal fee, TransactionSceneEnum sceneEnum) {
        ContractConfigPO contractConfigPO = ContractConfigUtil.getContractConfig(coOrder.getSymbol());

        BigDecimal closeProfit = getProfitByTrade(coPositionOrder, matchTradeDetailsDTO);
        Settlement settlement = createSettlementByTrade(coOrder, coPositionOrder.getId(), matchTradeDetailsDTO);
        settlement.setProfit(closeProfit);
        settlement.setFee(fee);
        save(settlement);

        //调用账户相关扣减  需要设置利润
        boolean accountResult = accountAction.operateAccount(coPositionOrder.getUid(),
                deltaMargin.abs(),
                contractConfigPO.getQuote(),
                sceneEnum.getValue(),
                "co_order",
                coOrder.getId(), settlement.getProfit(), BigDecimal.ZERO, settlement.getFee());

        //账户操作不成功
        if (!accountResult) {
            log.error("closePosition uid=" + coPositionOrder.getUid() +
                    ",deltaMargin=" + deltaMargin.toPlainString() +
                    ",symbol=" + contractConfigPO.getQuote() +
                    ",sceneEnum=" + sceneEnum.getValue() +
                    ",profit=" + settlement.getProfit() +
                    ",fee=" + settlement.getFee());
            throw new RuntimeException("CoPositionOrderServiceImpl -> mergePositionForTrade operateAccount error");
        }
    }

    private BigDecimal getProfitByTrade (CoPositionOrder coPositionOrder, MatchTradeDetailsDTO matchTradeDetailsDTO) {
        BigDecimal newPrice = matchTradeDetailsDTO.getPrice();
        BigDecimal avgPrice = coPositionOrder.getAvgPrice();
        BigDecimal subtract = newPrice.subtract(avgPrice);
        if (PositionSideEnum.SELL.name().equalsIgnoreCase(coPositionOrder.getPositionSide())) {
            subtract = subtract.negate();
        }
        return matchTradeDetailsDTO.getAmount().multiply(subtract);
    }

    private Settlement createSettlementByTrade (CoOrder coOrder, Long positionOrderId, MatchTradeDetailsDTO matchTradeDetailsDTO) {
        Date now = new Date();
        Settlement settlement = new Settlement();
        settlement.setSymbol(coOrder.getSymbol());
        settlement.setType(coOrder.getType());
        settlement.setUid(coOrder.getUid());
        settlement.setCoverOrderId(coOrder.getId());
        settlement.setPositionOrderId(positionOrderId);
        settlement.setSettleBase(matchTradeDetailsDTO.getAmount());
        settlement.setSettleQuote(matchTradeDetailsDTO.getTurnover());
        settlement.setSettleAvgPrice(matchTradeDetailsDTO.getPrice());
        settlement.setCtime(now);
        settlement.setMtime(now);
        return settlement;
    }

    private String getCacheDataKey(CoPositionOrder coPositionOrder) {
        return PositionUtil.getPositionDataKey(RedisCacheKey.POSITION_DATA_PREFIX,
                coPositionOrder.getUid(), coPositionOrder.getSymbol(), coPositionOrder.getPositionSide());
    }
}
