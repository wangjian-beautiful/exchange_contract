package com.bjs.contract.service.biz;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bijinsuo.common.constants.CoOrderConstant;
import com.bijinsuo.common.domain.CoOrderDTO;
import com.bijinsuo.common.domain.ContractConfigDTO;
import com.bijinsuo.common.mq.topic.TopicConstant;
import com.bijinsuo.common.utils.DateUtils;
import com.bijinsuo.common.utils.ProtoBeanUtils;
import com.bijinsuo.common.utils.entity.OrderCancelResultDTO;
import com.bijinsuo.common.utils.enums.*;
import com.bjs.contract.action.AccountAction;
import com.bjs.contract.action.ContractConfigAction;
import com.bjs.contract.action.MaintenanceMarginRateAction;
import com.bjs.contract.entity.CoOrder;
import com.bjs.contract.mapper.CoOrderMapper;
import com.bjs.contract.proto.coOrder.*;
import com.bjs.contract.service.CoOrderService;
import com.bjs.contract.service.CoPositionOrderService;
import com.bjs.contract.service.CoTriggerOrderService;
import com.bjs.contract.service.UserLeverageService;
import com.bijinsuo.common.utils.BizExceptionUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.rocketmq.spring.core.RocketMQTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.function.Predicate;

/**
 * @Auther: bjs code generator
 * @Date: 2022-11-12 14:25:01
 * @Description:
 */
@Slf4j
@DubboService(filter = {"SetTokenFilter", "ExtractTokenFilter"})
public class CoOrderBizServiceImpl implements CoOrderBizService {
    @Resource
    private MaintenanceMarginRateAction maintenanceMarginRateAction;
    @Resource
    private ContractConfigAction contractConfigAction;
    @Resource
    private CoPositionOrderService coPositionOrderService;
    @Resource
    private AccountAction accountAction;

    @Resource
    private CoOrderService coOrderService;
    @Resource
    private CoTriggerOrderService coTriggerOrderService;
    @Resource
    private UserLeverageService userLeverageService;
    @Resource
    private RocketMQTemplate rocketMQTemplate;
    @Resource
    private CoOrderMapper coOrderMapper;

    @Override
    public CoOrderPO getById(CoOrderRequest request) {
        CoOrder result = coOrderService.getById(request.getId());

        CoOrderPO po = entity2po(result);
        return po;
    }

    @Override
    public CoOrderReply insertOne(CoOrderRequest request) {
        CoOrderPO po = request.getCoOrderPO();
        CoOrder entity = po2entity(po);

        boolean result = coOrderService.save(entity);
        CoOrderPO res = entity2po(entity);
        return CoOrderReply.newBuilder().setStatus(result).setMessage("success").setCoOrderPO(res).build();
    }

    @Override
    public CoOrderReply updateById(CoOrderRequest request) {
        CoOrderPO po = request.getCoOrderPO();
        CoOrder entity = po2entity(po);

        boolean b = coOrderService.updateById(entity);
        return CoOrderReply.newBuilder().setStatus(b).setMessage("success").build();
    }

    @Override
    public CoOrderReply removeById(CoOrderRequest request) {
        boolean b = coOrderService.removeById(request.getId());
        return CoOrderReply.newBuilder().setStatus(b).setMessage("success").build();
    }

    @Override
    public CoOrderListReply selectListByIds(CoOrderIdsRequest request) {
        List<CoOrder> coOrderList = coOrderService.listByIds(request.getIdList());
        List<CoOrderPO> coOrderPOList = entityList2poList(coOrderList);
        return CoOrderListReply.newBuilder().addAllCoOrderPO(coOrderPOList).build();
    }

    @Override
    public CoOrderListReply selectAll(CoOrderPageRequest request) {
        if (request.getPage() != 0 && request.getSize() != 0) {
            PageHelper.startPage(request.getPage(), request.getSize());
        }
        List<CoOrder> coOrderList = coOrderService.list();
        PageInfo<CoOrder> pageInfo = new PageInfo<>(coOrderList);
        List<CoOrderPO> coOrderPOList = entityList2poList(pageInfo.getList());
        return CoOrderListReply.newBuilder().setTotal(pageInfo.getTotal()).addAllCoOrderPO(coOrderPOList).build();
    }

    @Override
    public CoOrderListReply selectList(CoOrderPageRequest request) {
        if (request.getPage() != 0 && request.getSize() != 0) {
            PageHelper.startPage(request.getPage(), request.getSize());
        }
        CoOrderPO po = request.getCoOrderPO();
        CoOrder coOrder = po2entity(po);
        QueryWrapper<CoOrder> queryWrapper = new QueryWrapper<>(coOrder);
        List<CoOrder> coOrderList = coOrderService.list(queryWrapper);
        PageInfo<CoOrder> pageInfo = new PageInfo<>(coOrderList);
        List<CoOrderPO> coOrderPOList = entityList2poList(pageInfo.getList());
        return CoOrderListReply.newBuilder().setTotal(pageInfo.getTotal()).addAllCoOrderPO(coOrderPOList).build();
    }

    @Override
    public CoOrderListReply insertBatch(CoOrderListRequest request) {
        List<CoOrderPO> dataList = request.getCoOrderPOList();
        List<CoOrder> entityList = poList2entityList(dataList);

        coOrderService.saveBatch(entityList);

        List<CoOrderPO> coOrderPOList = entityList2poList(entityList);
        return CoOrderListReply.newBuilder().addAllCoOrderPO(coOrderPOList).build();
    }

    @Override
    public CoOrderReply updateBatch(CoOrderListRequest request) {
        List<CoOrderPO> dataList = request.getCoOrderPOList();
        List<CoOrder> entityList = poList2entityList(dataList);
        boolean b = coOrderService.updateBatchById(entityList);
        return CoOrderReply.newBuilder().setStatus(b).setMessage("success").build();
    }

    @Override
    public CoOrderReply removeAll(CoOrderIdsRequest request) {
        boolean b = coOrderService.removeByIds(request.getIdList());
        return CoOrderReply.newBuilder().setStatus(b).setMessage("success").build();
    }

    @SneakyThrows
    private List<CoOrder> poList2entityList(List<CoOrderPO> poList) {
        return ProtoBeanUtils.toPojoBeanList(CoOrder.class, poList);
    }

    @SneakyThrows
    private List<CoOrderPO> entityList2poList(List<CoOrder> entityList) {
        return ProtoBeanUtils.toProtoBeanList(CoOrderPO.getDefaultInstance(), entityList);
    }

    @SneakyThrows
    private CoOrderPO entity2po(CoOrder entity) {
        CoOrderPO.Builder builder = CoOrderPO.newBuilder();
        ProtoBeanUtils.toProtoBean(builder, entity);
        return builder.build();
    }

    @SneakyThrows
    private CoOrder po2entity(CoOrderPO po) {
        return ProtoBeanUtils.toPojoBean(CoOrder.class, po);
    }

    @Override
    public CoOrderReply create(CoOrderRequest request) {
        try {
            CoOrder order = coOrderService.createOrder(request);
            rocketMQTemplate.syncSendOrderly(TopicConstant.MATCH_ORDER,
                    JSON.toJSONString(coOrderService.createMatchOrder(order, MatchInfoTypeEnum.match_order)), order.getSymbol());
            CoOrderPO.Builder builder = CoOrderPO.newBuilder();
            ProtoBeanUtils.toProtoBean(builder, order);
            return CoOrderReply.newBuilder().setStatus(true).setCoOrderPO(builder.build()).build();
        } catch (Exception e) {
            log.error("创建开仓订单出错：", e);
            return CoOrderReply.newBuilder()
                .setStatus(false)
                .setMessage(BizExceptionUtil.extractErrorCodeWithArgs(e))
                .build();
        }
    }

    @Override
    public CoOrderReply close(CoOrderRequest request) {
        try {
            var orderDTO = ProtoBeanUtils.toPojoBean(CoOrderDTO.class, request.getCoOrderPO());
            ContractConfigDTO symbolConfig = contractConfigAction.getBySymbol(orderDTO.getSymbol());
            CoOrder order = coOrderService.orderClose(orderDTO, symbolConfig);
            rocketMQTemplate.syncSendOrderly(TopicConstant.MATCH_ORDER, JSON.toJSONString(coOrderService.createMatchOrder(order, MatchInfoTypeEnum.match_order)), orderDTO.getSymbol());
            CoOrderPO.Builder builder = CoOrderPO.newBuilder();
            ProtoBeanUtils.toProtoBean(builder, orderDTO);
            return CoOrderReply.newBuilder().setStatus(true).setCoOrderPO(builder.build()).build();
        } catch (Exception e) {
            log.error("创建平仓订单出错：", e);
            return CoOrderReply.newBuilder()
                .setStatus(false)
                .setMessage(BizExceptionUtil.extractErrorCodeWithArgs(e))
                .build();
        }
    }

    @Override
    public CoOrderReply cancelOrder(CoOrderRequest request) {
        try {
            CoOrder order = coOrderService.cancelOrder(request.getId());
            rocketMQTemplate.syncSendOrderly(TopicConstant.MATCH_ORDER,
                JSON.toJSONString(coOrderService.createMatchOrder(order, MatchInfoTypeEnum.cancel_order)), order.getSymbol());
            return CoOrderReply.newBuilder().setStatus(true).build();
        } catch (Exception e) {
            log.error("撤销订单失败：", e);
            return CoOrderReply.newBuilder()
                .setStatus(false)
                .setMessage(BizExceptionUtil.extractErrorCodeWithArgs(e))
                .build();
        }
    }

    @Override
    public CoOrderStatusReply cancelUserOrders(CoOrderCancelRequest request) {
        final var result = coOrderService.cancelUserOrders(request.getUid(), request.getSymbol(), request.getSide(), request.getTimeout());
        final var status = result.stream().anyMatch(Predicate.not(OrderCancelResultDTO::isStatus));
        final var replyBuilder = CoOrderStatusReply.newBuilder();
        replyBuilder.setStatus(status);
        if (!status) {
            replyBuilder.setMessage("撤销委托失败");
        }
        for (int i = 0; i < result.size(); i++) {
            replyBuilder.setOrders(i, CoOrderStatus
                    .newBuilder()
                    .setId(result.get(i).getId())
                    .setStatus(result.get(i).isStatus())
                    .build());
        }
        return replyBuilder.build();
    }

    @Override
    public CoOrderReply cancelUserOrdersNotFilled(CoOrderCancelRequest request) {
        boolean b = coOrderService.cancelUserOrdersNotFilled(request.getUid());
        return CoOrderReply.newBuilder().setStatus(b).setMessage(CommonEnum.CANCEL_ORDER_ERROR.getResultMsg()).build();
    }

    @Override
    public CoUserOrderCountReply getUserOrderCount(CoUserOrderRequest request) {
        int orderCount =  coOrderService.getOrderCount(request.getId());
        int triggerOrderCount = coTriggerOrderService.getTriggerOrderCount(request.getId());
        return CoUserOrderCountReply.newBuilder().setOrderCount(orderCount).setTriggerOrderCount(triggerOrderCount).build();
    }

    @Override
    public CoOrderListReply selectListByStatus(CoOrderPageByStatusRequest request) {
        if (request.getPage() != 0 && request.getLimit() != 0) {
            PageHelper.startPage(request.getPage(), request.getLimit());
        }
        LambdaQueryWrapper<CoOrder> query = new LambdaQueryWrapper<>();
        if (StringUtils.isNotEmpty(request.getBeginTime())){
            query.ge(CoOrder::getCtime, DateUtils.stampToDate(Long.valueOf(request.getBeginTime())));
        }
        if (StringUtils.isNotEmpty(request.getEndTime())){
            query.le(CoOrder::getCtime, DateUtils.stampToDate(Long.valueOf(request.getEndTime())));
        }
        if(org.apache.commons.collections.CollectionUtils.isNotEmpty(request.getStatusList())){
            query.in(CoOrder::getStatus,request.getStatusList());
        }
        if(StringUtils.isNotEmpty(request.getCoOrderPO().getSymbol())){
            query.eq(CoOrder::getSymbol,request.getCoOrderPO().getSymbol());
        }
        if(request.getCoOrderPO().getType().getValue() != Integer.valueOf(CoOrderConstant.NO_VALUE_FLAG)){
            query.eq(CoOrder::getType,request.getCoOrderPO().getType().getValue());
        }
        if(request.getCoOrderPO().getUid().getValue() != 0){
            query.eq(CoOrder::getUid,request.getCoOrderPO().getUid().getValue());
        }
        List<CoOrder> coOrderList = coOrderMapper.selectList(query);
        PageInfo<CoOrder> pageInfo = new PageInfo<>(coOrderList);
        List<CoOrderPO> coOrderPOList = entityList2poList(pageInfo.getList());
        return CoOrderListReply.newBuilder().setTotal(pageInfo.getTotal()).addAllCoOrderPO(coOrderPOList).build();
    }
}