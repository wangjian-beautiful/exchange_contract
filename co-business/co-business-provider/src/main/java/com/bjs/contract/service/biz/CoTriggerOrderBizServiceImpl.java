package com.bjs.contract.service.biz;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bijinsuo.common.constants.CoOrderConstant;
import com.bijinsuo.common.domain.ContractConfigDTO;
import com.bijinsuo.common.identify.UserContextHolder;
import com.bijinsuo.common.redis.constant.RedisCacheKey;
import com.bijinsuo.common.redis.utils.RedisUtil;
import com.bijinsuo.common.utils.DateUtils;
import com.bijinsuo.common.utils.ProtoBeanUtils;
import com.bijinsuo.common.utils.enums.CommonEnum;
import com.bijinsuo.common.utils.exception.BizException;
import com.bjs.contract.action.AccountAction;
import com.bjs.contract.action.ContractConfigAction;
import com.bjs.contract.entity.CoTriggerOrder;
import com.bjs.contract.mapper.CoTriggerOrderMapper;
import com.bjs.contract.proto.coTriggerOrder.*;
import com.bjs.contract.service.CoTriggerOrderService;
import com.bjs.contract.utils.OrderValidator;
import com.bijinsuo.common.utils.BizExceptionUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Auther: bjs code generator
 * @Date: 2022-11-11 10:46:36
 * @Description:
 */
@Slf4j
@DubboService(filter = {"SetTokenFilter", "ExtractTokenFilter"})
public class CoTriggerOrderBizServiceImpl implements CoTriggerOrderBizService {
    @Resource
    private ContractConfigAction contractConfigAction;
    @Resource
    private AccountAction accountAction;

    @Resource
    private CoTriggerOrderService coTriggerOrderService;
    @Resource
    private CoTriggerOrderMapper coTriggerOrderMapper;

    @Override
    public CoTriggerOrderPO getById(CoTriggerOrderRequest request) {
        CoTriggerOrder result = coTriggerOrderService.getById(request.getId());

        CoTriggerOrderPO po = entity2po(result);
        return po;
    }

    @Override
    public CoTriggerOrderReply insertOne(CoTriggerOrderRequest request) {
        CoTriggerOrderPO po = request.getCoTriggerOrderPO();
        CoTriggerOrder entity = po2entity(po);

        boolean result = coTriggerOrderService.save(entity);
        CoTriggerOrderPO res = entity2po(entity);
        return CoTriggerOrderReply.newBuilder()
                .setStatus(result)
                .setMessage("success")
                .setCoTriggerOrderPO(res)
                .build();
    }

    @Override
    public CoTriggerOrderReply updateById(CoTriggerOrderRequest request) {
        CoTriggerOrderPO po = request.getCoTriggerOrderPO();
        CoTriggerOrder entity = po2entity(po);

        boolean b = coTriggerOrderService.updateById(entity);
        return CoTriggerOrderReply.newBuilder()
                .setStatus(b)
                .setMessage("success")
                .build();
    }

    @Override
    public CoTriggerOrderReply removeById(CoTriggerOrderRequest request) {
        boolean b = coTriggerOrderService.removeById(request.getId());
        return CoTriggerOrderReply.newBuilder()
                .setStatus(b)
                .setMessage("success")
                .build();
    }

    @Override
    public CoTriggerOrderListReply selectListByIds(CoTriggerOrderIdsRequest request) {
        List<CoTriggerOrder> coTriggerOrderList = coTriggerOrderService.listByIds(request.getIdList());
        List<CoTriggerOrderPO> coTriggerOrderPOList = entityList2poList(coTriggerOrderList);
        return CoTriggerOrderListReply.newBuilder()
                .addAllCoTriggerOrderPO(coTriggerOrderPOList)
                .build();
    }

    @Override
    public CoTriggerOrderListReply selectAll(CoTriggerOrderPageRequest request) {
        if (request.getPage() != 0 && request.getSize() != 0) {
            PageHelper.startPage(request.getPage(), request.getSize());
        }
        List<CoTriggerOrder> coTriggerOrderList = coTriggerOrderService.list();
        PageInfo<CoTriggerOrder> pageInfo = new PageInfo<>(coTriggerOrderList);
        List<CoTriggerOrderPO> coTriggerOrderPOList = entityList2poList(pageInfo.getList());
        return CoTriggerOrderListReply.newBuilder()
                .setTotal(pageInfo.getTotal())
                .addAllCoTriggerOrderPO(coTriggerOrderPOList)
                .build();
    }

    @Override
    public CoTriggerOrderListReply selectList(CoTriggerOrderPageRequest request) {
        if (request.getPage() != 0 && request.getSize() != 0) {
            PageHelper.startPage(request.getPage(), request.getSize());
        }
        CoTriggerOrderPO po = request.getCoTriggerOrderPO();
        CoTriggerOrder coTriggerOrder = po2entity(po);
        QueryWrapper<CoTriggerOrder> queryWrapper = new QueryWrapper<>(coTriggerOrder);
        List<CoTriggerOrder> coTriggerOrderList = coTriggerOrderService.list(queryWrapper);
        PageInfo<CoTriggerOrder> pageInfo = new PageInfo<>(coTriggerOrderList);
        List<CoTriggerOrderPO> coTriggerOrderPOList = entityList2poList(pageInfo.getList());
        return CoTriggerOrderListReply.newBuilder()
                .setTotal(pageInfo.getTotal())
                .addAllCoTriggerOrderPO(coTriggerOrderPOList)
                .build();
    }

    @Override
    public CoTriggerOrderListReply insertBatch(CoTriggerOrderListRequest request) {
        List<CoTriggerOrderPO> dataList = request.getCoTriggerOrderPOList();
        List<CoTriggerOrder> entityList = poList2entityList(dataList);

        coTriggerOrderService.saveBatch(entityList);

        List<CoTriggerOrderPO> coTriggerOrderPOList = entityList2poList(entityList);
        return CoTriggerOrderListReply.newBuilder()
                .addAllCoTriggerOrderPO(coTriggerOrderPOList)
                .build();
    }

    @Override
    public CoTriggerOrderReply updateBatch(CoTriggerOrderListRequest request) {
        List<CoTriggerOrderPO> dataList = request.getCoTriggerOrderPOList();
        List<CoTriggerOrder> entityList = poList2entityList(dataList);
        boolean b = coTriggerOrderService.updateBatchById(entityList);
        return CoTriggerOrderReply.newBuilder()
                .setStatus(b)
                .setMessage("success")
                .build();
    }

    @Override
    public CoTriggerOrderReply removeAll(CoTriggerOrderIdsRequest request) {
        boolean b = coTriggerOrderService.removeByIds(request.getIdList());
        return CoTriggerOrderReply.newBuilder()
                .setStatus(b)
                .setMessage("success")
                .build();
    }

    @SneakyThrows
    private List<CoTriggerOrder> poList2entityList(List<CoTriggerOrderPO> poList) {
        return ProtoBeanUtils.toPojoBeanList(CoTriggerOrder.class, poList);
    }

    @SneakyThrows
    private List<CoTriggerOrderPO> entityList2poList(List<CoTriggerOrder> entityList) {
        return ProtoBeanUtils.toProtoBeanList(CoTriggerOrderPO.getDefaultInstance(), entityList);
    }

    @SneakyThrows
    private CoTriggerOrderPO entity2po(CoTriggerOrder entity) {
        CoTriggerOrderPO.Builder builder = CoTriggerOrderPO.newBuilder();
        ProtoBeanUtils.toProtoBean(builder, entity);
        return builder.build();
    }

    @SneakyThrows
    private CoTriggerOrder po2entity(CoTriggerOrderPO po) {
        return ProtoBeanUtils.toPojoBean(CoTriggerOrder.class, po);
    }

    @Override
    public CoTriggerOrderReply create(CoTriggerOrderRequest request) {
        try {
            CoTriggerOrder order = ProtoBeanUtils.toPojoBean(CoTriggerOrder.class, request.getCoTriggerOrderPO());
            ContractConfigDTO symbolConfig = contractConfigAction.getBySymbol(order.getSymbol());
            OrderValidator orderValidator = new OrderValidator(symbolConfig);
            orderValidator.validateTriggerOrder(order);
            Double redisPrice = RedisUtil.instance().hmget(RedisCacheKey.LATEST_PRICE_KEY, order.getSymbol());
            if (redisPrice == null) {
                throw new BizException(CommonEnum.NEW_PRICE_FAIL);
            }
            BigDecimal latestMarketPrice = new BigDecimal(redisPrice.toString());
            order.setCurrentMarketPrice(latestMarketPrice);
            coTriggerOrderService.save(order);
            return CoTriggerOrderReply.newBuilder().setStatus(true).build();
        } catch (Exception e) {
            log.error("创建条件订单出错：", e);
            return CoTriggerOrderReply.newBuilder()
                .setStatus(false)
                .setMessage(BizExceptionUtil.extractErrorCodeWithArgs(e))
                .build();
        }
    }

    @Override
    public CoTriggerOrderReply cancelTriggerOrderById(CancelTriggerOrderByIdRequest request) {
        boolean b = coTriggerOrderService.cancelActiveTriggerOrderById(request.getId(),UserContextHolder.get().getId());
        return CoTriggerOrderReply.newBuilder()
                .setStatus(b)
                .setMessage("success")
                .build();
    }

    @Override
    public CoTriggerOrderReply cancelAllTriggerOrder(CancelAllTriggerOrderRequest request) {
        boolean b = coTriggerOrderService.cancelAllActiveTriggerOrder(UserContextHolder.get().getId());
        return CoTriggerOrderReply.newBuilder()
                .setStatus(b)
                .setMessage("success")
                .build();
    }

    @Override
    public CoTriggerOrderListReply selectListByStatus(CoTriggerOrderPageByStatusRequest request) {
        if (request.getPage() != 0 && request.getLimit() != 0) {
            PageHelper.startPage(request.getPage(), request.getLimit());
        }
        LambdaQueryWrapper<CoTriggerOrder> query = new LambdaQueryWrapper<>();
        if (StringUtils.isNotEmpty(request.getBeginTime())){
            query.ge(CoTriggerOrder::getCtime, DateUtils.stampToDate(Long.valueOf(request.getBeginTime())));
        }
        if (StringUtils.isNotEmpty(request.getEndTime())){
            query.le(CoTriggerOrder::getCtime, DateUtils.stampToDate(Long.valueOf(request.getEndTime())));
        }
        if(CollectionUtils.isNotEmpty(request.getStatusList())){
            query.in(CoTriggerOrder::getStatus,request.getStatusList());
        }
        if(StringUtils.isNotEmpty(request.getCoTriggerOrderPO().getSymbol())){
            query.eq(CoTriggerOrder::getSymbol,request.getCoTriggerOrderPO().getSymbol());
        }
        if(request.getCoTriggerOrderPO().getType().getValue() != Integer.valueOf(CoOrderConstant.NO_VALUE_FLAG)){
            query.eq(CoTriggerOrder::getType,request.getCoTriggerOrderPO().getType().getValue());
        }
        if(request.getCoTriggerOrderPO().getUid().getValue() != 0 ){
            query.eq(CoTriggerOrder::getUid,request.getCoTriggerOrderPO().getUid().getValue());
        }

        List<CoTriggerOrder> coTriggerOrderList = coTriggerOrderMapper.selectList(query);
        PageInfo<CoTriggerOrder> pageInfo = new PageInfo<>(coTriggerOrderList);
        List<CoTriggerOrderPO> coTriggerOrderPOList = entityList2poList(pageInfo.getList());
        return CoTriggerOrderListReply.newBuilder()
                .setTotal(pageInfo.getTotal())
                .addAllCoTriggerOrderPO(coTriggerOrderPOList)
                .build();
    }
}
