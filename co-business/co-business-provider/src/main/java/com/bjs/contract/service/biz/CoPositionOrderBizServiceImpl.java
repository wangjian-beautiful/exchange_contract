package com.bjs.contract.service.biz;

import cn.hutool.core.collection.CollectionUtil;
import com.bijinsuo.common.domain.FundingRateSettlePositionDTO;
import com.bijinsuo.common.utils.enums.PositionSideEnum;
import com.bjs.contract.proto.coPositionOrder.*;
import com.bjs.contract.service.CoPositionOrderService;
import com.bjs.contract.entity.CoPositionOrder;
import com.bijinsuo.common.utils.ProtoBeanUtils;
import lombok.SneakyThrows;
import org.apache.dubbo.config.annotation.DubboService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: bjs code generator
 * @Date: 2022-11-11 17:58:51
 * @Description:
 */
@DubboService(filter = {"SetTokenFilter", "ExtractTokenFilter"})
public class CoPositionOrderBizServiceImpl implements CoPositionOrderBizService {

    @Resource
    private CoPositionOrderService coPositionOrderService;

    @Override
    public CoPositionOrderPO getById(CoPositionOrderRequest request) {
        CoPositionOrder result = coPositionOrderService.getById(request.getId());

        CoPositionOrderPO po = entity2po(result);
        return po;
    }

    @Override
    public CoPositionOrderReply fundingRateSettleMargin(FundingRateSettleMarginRequest request) {
        FundingRateSettleMarginPO po = request.getFundingRateSettleMarginPO();
        if (CollectionUtil.isEmpty(po.getFundingRateSettleMarginItemPOList())) {
            return CoPositionOrderReply.newBuilder()
                    .setStatus(true)
                    .setMessage("success")
                    .build();
        }
        List<FundingRateSettlePositionDTO> settlePositionDTOList = ProtoBeanUtils.toPojoBeanList(FundingRateSettlePositionDTO.class
                , po.getFundingRateSettleMarginItemPOList());
        boolean result = coPositionOrderService.updatePositionForFundingRateSettle(po.getSymbol(), po.getSide()
                , settlePositionDTOList);
        return CoPositionOrderReply.newBuilder()
                .setStatus(result)
                .setMessage("success")
                .build();
    }

    @Override
    public CoPositionOrderListReply findAllInSymbolList(FindAllInSymbolListRequest request) {
        List<CoPositionOrder> coPositionOrderList = coPositionOrderService.findAllInSymbolList(request.getStatus()
                , request.getSymbolListList());
        List<CoPositionOrderPO> coPositionOrderPOList = entityList2poList(coPositionOrderList);
        return CoPositionOrderListReply.newBuilder()
                .addAllCoPositionOrderPO(coPositionOrderPOList)
                .build();
    }

    @Override
    public CoPositionOrderReply insertOne(CoPositionOrderRequest request) {
        CoPositionOrderPO po = request.getCoPositionOrderPO();
        CoPositionOrder entity = po2entity(po);

        boolean result = coPositionOrderService.save(entity);
        CoPositionOrderPO res = entity2po(entity);
        return CoPositionOrderReply.newBuilder()
                .setStatus(result)
                .setMessage("success")
                .setCoPositionOrderPO(res)
                .build();
    }

    @Override
    public CoPositionOrderReply updateById(CoPositionOrderRequest request) {
        CoPositionOrderPO po = request.getCoPositionOrderPO();
        CoPositionOrder entity = po2entity(po);

        boolean b = coPositionOrderService.updateById(entity);
        return CoPositionOrderReply.newBuilder()
                .setStatus(b)
                .setMessage("success")
                .build();
    }

    @Override
    public CoPositionOrderReply removeById(CoPositionOrderRequest request) {
        boolean b = coPositionOrderService.removeById(request.getId());
        return CoPositionOrderReply.newBuilder()
                .setStatus(b)
                .setMessage("success")
                .build();
    }

    @Override
    public CoPositionOrderListReply selectListByIds(CoPositionOrderIdsRequest request) {
        List<CoPositionOrder> coPositionOrderList = coPositionOrderService.listByIds(request.getIdList());
        List<CoPositionOrderPO> coPositionOrderPOList = entityList2poList(coPositionOrderList);
        return CoPositionOrderListReply.newBuilder()
                .addAllCoPositionOrderPO(coPositionOrderPOList)
                .build();
    }

    @Override
    public CoPositionOrderListReply selectAll(CoPositionOrderPageRequest request) {
        if (request.getPage() != 0 && request.getSize() != 0) {
            PageHelper.startPage(request.getPage(), request.getSize());
        }
        List<CoPositionOrder> coPositionOrderList = coPositionOrderService.list();
        PageInfo<CoPositionOrder> pageInfo = new PageInfo<>(coPositionOrderList);
        List<CoPositionOrderPO> coPositionOrderPOList = entityList2poList(pageInfo.getList());
        return CoPositionOrderListReply.newBuilder()
                .setTotal(pageInfo.getTotal())
                .addAllCoPositionOrderPO(coPositionOrderPOList)
                .build();
    }

    @Override
    public CoPositionOrderListReply selectList(CoPositionOrderPageRequest request) {
        if (request.getPage() != 0 && request.getSize() != 0) {
            PageHelper.startPage(request.getPage(), request.getSize());
        }
        CoPositionOrderPO po = request.getCoPositionOrderPO();
        CoPositionOrder coPositionOrder = po2entity(po);
        QueryWrapper<CoPositionOrder> queryWrapper = new QueryWrapper<>(coPositionOrder);
        List<CoPositionOrder> coPositionOrderList = coPositionOrderService.list(queryWrapper);
        PageInfo<CoPositionOrder> pageInfo = new PageInfo<>(coPositionOrderList);
        List<CoPositionOrderPO> coPositionOrderPOList = entityList2poList(pageInfo.getList());
        return CoPositionOrderListReply.newBuilder()
                .setTotal(pageInfo.getTotal())
                .addAllCoPositionOrderPO(coPositionOrderPOList)
                .build();
    }

    @Override
    public CoPositionOrderListReply insertBatch(CoPositionOrderListRequest request) {
        List<CoPositionOrderPO> dataList = request.getCoPositionOrderPOList();
        List<CoPositionOrder> entityList = poList2entityList(dataList);

        coPositionOrderService.saveBatch(entityList);

        List<CoPositionOrderPO> coPositionOrderPOList = entityList2poList(entityList);
        return CoPositionOrderListReply.newBuilder()
                .addAllCoPositionOrderPO(coPositionOrderPOList)
                .build();
    }

    @Override
    public CoPositionOrderReply updateBatch(CoPositionOrderListRequest request) {
        List<CoPositionOrderPO> dataList = request.getCoPositionOrderPOList();
        List<CoPositionOrder> entityList = poList2entityList(dataList);
        boolean b = coPositionOrderService.updateBatchById(entityList);
        return CoPositionOrderReply.newBuilder()
                .setStatus(b)
                .setMessage("success")
                .build();
    }

    @Override
    public CoPositionOrderReply removeAll(CoPositionOrderIdsRequest request) {
        boolean b = coPositionOrderService.removeByIds(request.getIdList());
        return CoPositionOrderReply.newBuilder()
                .setStatus(b)
                .setMessage("success")
                .build();
    }

    @SneakyThrows
    private List<CoPositionOrder> poList2entityList(List<CoPositionOrderPO> poList) {
        return ProtoBeanUtils.toPojoBeanList(CoPositionOrder.class, poList);
    }

    @SneakyThrows
    private List<CoPositionOrderPO> entityList2poList(List<CoPositionOrder> entityList) {
        return ProtoBeanUtils.toProtoBeanList(CoPositionOrderPO.getDefaultInstance(), entityList);
    }

    @SneakyThrows
    private CoPositionOrderPO entity2po(CoPositionOrder entity) {
        CoPositionOrderPO.Builder builder = CoPositionOrderPO.newBuilder();
        ProtoBeanUtils.toProtoBean(builder, entity);
        return builder.build();
    }

    @SneakyThrows
    private CoPositionOrder po2entity(CoPositionOrderPO po) {
        return ProtoBeanUtils.toPojoBean(CoPositionOrder.class, po);
    }
}
