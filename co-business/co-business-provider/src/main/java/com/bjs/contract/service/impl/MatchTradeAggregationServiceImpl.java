package com.bjs.contract.service.impl;

import com.alibaba.fastjson.JSON;
import com.bijinsuo.common.mq.topic.TopicConstant;
import com.bijinsuo.common.utils.entity.MatchTradeDTO;
import com.bijinsuo.common.utils.enums.*;
import com.bjs.contract.action.ContractConfigAction;
import com.bjs.contract.domain.UserPositionDetail;
import com.bjs.contract.service.CoOrderService;
import com.bjs.contract.service.CoPositionOrderService;
import com.bjs.contract.service.MatchTradeAggregationService;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author Watson
 */

@Service
public class MatchTradeAggregationServiceImpl implements MatchTradeAggregationService {

    @Resource
    CoOrderService coOrderService;
    @Resource
    CoPositionOrderService coPositionOrderService;
    @Resource
    ContractConfigAction contractConfigAction;
    @Resource
    RocketMQTemplate rocketMQTemplate;

    @Override
    public void handle(MatchTradeDTO dto) {
        contractConfigAction.checkContractConfig(dto.getSymbol());
        List<UserPositionDetail> list = null;
        if (dto.getMatchInfoType() == MatchInfoTypeEnum.match_order) {
            //正常撮合
            list = coPositionOrderService.handlePositionForTrade(dto);
        } else {
            //撤单
            coOrderService.cancelOrderAfterMatch(dto);
        }

        if (!CollectionUtils.isEmpty(list)) {
            handlePositionPost(list);
        }
    }

    @Override
    public void handleForRobot(MatchTradeDTO dto) {
        if (dto.getMatchInfoType() == MatchInfoTypeEnum.match_order){
            coOrderService.handleMatchForRobot(dto);
        }
    }

    //业务逻辑完成后置处理
    private void handlePositionPost (List<UserPositionDetail> list) {
        if (!CollectionUtils.isEmpty(list)) {
            for (UserPositionDetail userPositionDetail : list) {
                if (userPositionDetail.isInit()) {
                    handleInitPosition(userPositionDetail);
                } else {
                    handleMergePosition(userPositionDetail);
                }
            }
        }
    }

    private void handleInitPosition (UserPositionDetail userPositionDetail) {

    }

    private void handleMergePosition (UserPositionDetail userPositionDetail) {

    }
}
