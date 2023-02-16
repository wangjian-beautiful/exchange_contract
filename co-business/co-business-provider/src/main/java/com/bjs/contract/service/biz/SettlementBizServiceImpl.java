package com.bjs.contract.service.biz;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bijinsuo.common.utils.ProtoBeanUtils;
import com.bjs.contract.entity.Settlement;
import com.bjs.contract.proto.settlement.*;
import com.bjs.contract.service.SettlementService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.SneakyThrows;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: bjs code generator
 * @Date: 2022-11-11 10:54:13
 * @Description:
 */
@DubboService(filter = {"SetTokenFilter","ExtractTokenFilter"})
public class SettlementBizServiceImpl implements SettlementBizService {

    @Resource
    private SettlementService settlementService;

    @Override
    public SettlementPO getById(SettlementRequest request) {
        Settlement result = settlementService.getById(request.getId());

        SettlementPO po = entity2po(result);
        return po;
    }

    @Override
    public SettlementReply insertOne(SettlementRequest request) {
        SettlementPO po = request.getSettlementPO();
        Settlement entity = po2entity(po);

        boolean result = settlementService.save(entity);
        SettlementPO res = entity2po(entity);
        return SettlementReply.newBuilder()
                .setStatus(result)
                .setMessage("success")
                .setSettlementPO(res)
                .build();
    }

    @Override
    public SettlementReply updateById(SettlementRequest request) {
        SettlementPO po = request.getSettlementPO();
        Settlement entity = po2entity(po);

        boolean b = settlementService.updateById(entity);
        return SettlementReply.newBuilder()
                .setStatus(b)
                .setMessage("success")
                .build();
    }

    @Override
    public SettlementReply removeById(SettlementRequest request) {
        boolean b = settlementService.removeById(request.getId());
        return SettlementReply.newBuilder()
                .setStatus(b)
                .setMessage("success")
                .build();
    }

    @Override
    public SettlementListReply selectListByIds(SettlementIdsRequest request) {
        List<Settlement> settlementList = settlementService.listByIds(request.getIdList());
        List<SettlementPO> settlementPOList = entityList2poList(settlementList);
        return SettlementListReply.newBuilder()
                .addAllSettlementPO(settlementPOList)
                .build();
    }

    @Override
    public SettlementListReply selectAll(SettlementPageRequest request) {
        if (request.getPage() !=0 && request.getSize()!=0) {
            PageHelper.startPage(request.getPage(), request.getSize());
        }
        List<Settlement> settlementList = settlementService.list();
        PageInfo<Settlement> pageInfo = new PageInfo<>(settlementList);
        List<SettlementPO> settlementPOList = entityList2poList(pageInfo.getList());
        return SettlementListReply.newBuilder()
                .setTotal(pageInfo.getTotal())
                .addAllSettlementPO(settlementPOList)
                .build();
    }

    @Override
    public SettlementListReply selectList(SettlementPageRequest request) {
        if (request.getPage() !=0 && request.getSize()!=0) {
            PageHelper.startPage(request.getPage(), request.getSize());
        }
        SettlementPO po = request.getSettlementPO();
        Settlement settlement = po2entity(po);
        QueryWrapper<Settlement> queryWrapper = new QueryWrapper<>(settlement);
        List<Settlement> settlementList = settlementService.list(queryWrapper);
        PageInfo<Settlement> pageInfo = new PageInfo<>(settlementList);
        List<SettlementPO> settlementPOList = entityList2poList(pageInfo.getList());
        return SettlementListReply.newBuilder()
                .setTotal(pageInfo.getTotal())
                .addAllSettlementPO(settlementPOList)
                .build();
    }

    @Override
    public SettlementListReply insertBatch (SettlementListRequest request) {
        List<SettlementPO> dataList = request.getSettlementPOList();
        List<Settlement> entityList = poList2entityList(dataList);

        settlementService.saveBatch(entityList);

        List<SettlementPO> settlementPOList = entityList2poList(entityList);
        return SettlementListReply.newBuilder()
                .addAllSettlementPO(settlementPOList)
                .build();
    }

    @Override
    public SettlementReply updateBatch (SettlementListRequest request) {
        List<SettlementPO> dataList = request.getSettlementPOList();
        List<Settlement> entityList = poList2entityList(dataList);
        boolean b = settlementService.updateBatchById(entityList);
        return SettlementReply.newBuilder()
                .setStatus(b)
                .setMessage("success")
                .build();
    }

    @Override
    public SettlementReply removeAll (SettlementIdsRequest request) {
        boolean b = settlementService.removeByIds(request.getIdList());
        return SettlementReply.newBuilder()
                .setStatus(b)
                .setMessage("success")
                .build();
    }

    @SneakyThrows
    private List<Settlement> poList2entityList (List<SettlementPO> poList) {
        return ProtoBeanUtils.toPojoBeanList(Settlement.class, poList);
    }

    @SneakyThrows
    private List<SettlementPO> entityList2poList (List<Settlement> entityList) {
        return ProtoBeanUtils.toProtoBeanList(SettlementPO.getDefaultInstance(), entityList);
    }

    @SneakyThrows
    private SettlementPO entity2po (Settlement entity) {
        SettlementPO.Builder builder = SettlementPO.newBuilder();
        ProtoBeanUtils.toProtoBean(builder, entity);
        return builder.build();
    }

    @SneakyThrows
    private Settlement po2entity (SettlementPO po) {
        return ProtoBeanUtils.toPojoBean(Settlement.class, po);
    }
}
