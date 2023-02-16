package com.bjs.contract.service.biz;

import com.bjs.contract.proto.coTrade.CoTradePO;
import com.bjs.contract.proto.coTrade.CoTradeBizService;
import com.bjs.contract.proto.coTrade.CoTradeRequest;
import com.bjs.contract.proto.coTrade.CoTradePageRequest;
import com.bjs.contract.proto.coTrade.CoTradeIdsRequest;
import com.bjs.contract.proto.coTrade.CoTradeListRequest;
import com.bjs.contract.proto.coTrade.CoTradeReply;
import com.bjs.contract.proto.coTrade.CoTradeListReply;
import com.bjs.contract.service.CoTradeService;
import com.bjs.contract.entity.CoTrade;
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
 * @Date: 2022-11-11 10:57:56
 * @Description:
 */
@DubboService(filter = {"SetTokenFilter","ExtractTokenFilter"})
public class CoTradeBizServiceImpl implements CoTradeBizService {

    @Resource
    private CoTradeService coTradeService;

    @Override
    public CoTradePO getById(CoTradeRequest request) {
        CoTrade result = coTradeService.getById(request.getId());

        CoTradePO po = entity2po(result);
        return po;
    }

    @Override
    public CoTradeReply insertOne(CoTradeRequest request) {
        CoTradePO po = request.getCoTradePO();
        CoTrade entity = po2entity(po);

        boolean result = coTradeService.save(entity);
        CoTradePO res = entity2po(entity);
        return CoTradeReply.newBuilder()
                .
                setStatus(result)
                .setMessage("success")
                .setCoTradePO(res)
                .build();
    }

    @Override
    public CoTradeReply updateById(CoTradeRequest request) {
        CoTradePO po = request.getCoTradePO();
        CoTrade entity = po2entity(po);

        boolean b = coTradeService.updateById(entity);
        return CoTradeReply.newBuilder()
                .setStatus(b)
                .setMessage("success")
                .build();
    }

    @Override
    public CoTradeReply removeById(CoTradeRequest request) {
        boolean b = coTradeService.removeById(request.getId());
        return CoTradeReply.newBuilder()
                .setStatus(b)
                .setMessage("success")
                .build();
    }

    @Override
    public CoTradeListReply selectListByIds(CoTradeIdsRequest request) {
        List<CoTrade> coTradeList = coTradeService.listByIds(request.getIdList());
        List<CoTradePO> coTradePOList = entityList2poList(coTradeList);
        return CoTradeListReply.newBuilder()
                .addAllCoTradePO(coTradePOList)
                .build();
    }

    @Override
    public CoTradeListReply selectAll(CoTradePageRequest request) {
        if (request.getPage() !=0 && request.getSize()!=0) {
            PageHelper.startPage(request.getPage(), request.getSize());
        }
        List<CoTrade> coTradeList = coTradeService.list();
        PageInfo<CoTrade> pageInfo = new PageInfo<>(coTradeList);
        List<CoTradePO> coTradePOList = entityList2poList(pageInfo.getList());
        return CoTradeListReply.newBuilder()
                .setTotal(pageInfo.getTotal())
                .addAllCoTradePO(coTradePOList)
                .build();
    }

    @Override
    public CoTradeListReply selectList(CoTradePageRequest request) {
        if (request.getPage() !=0 && request.getSize()!=0) {
            PageHelper.startPage(request.getPage(), request.getSize());
        }
        CoTradePO po = request.getCoTradePO();
        CoTrade coTrade = po2entity(po);
        QueryWrapper<CoTrade> queryWrapper = new QueryWrapper<>(coTrade);
        List<CoTrade> coTradeList = coTradeService.list(queryWrapper);
        PageInfo<CoTrade> pageInfo = new PageInfo<>(coTradeList);
        List<CoTradePO> coTradePOList = entityList2poList(pageInfo.getList());
        return CoTradeListReply.newBuilder()
                .setTotal(pageInfo.getTotal())
                .addAllCoTradePO(coTradePOList)
                .build();
    }

    @Override
    public CoTradeListReply insertBatch (CoTradeListRequest request) {
        List<CoTradePO> dataList = request.getCoTradePOList();
        List<CoTrade> entityList = poList2entityList(dataList);

        coTradeService.saveBatch(entityList);

        List<CoTradePO> coTradePOList = entityList2poList(entityList);
        return CoTradeListReply.newBuilder()
                .addAllCoTradePO(coTradePOList)
                .build();
    }

    @Override
    public CoTradeReply updateBatch (CoTradeListRequest request) {
        List<CoTradePO> dataList = request.getCoTradePOList();
        List<CoTrade> entityList = poList2entityList(dataList);
        boolean b = coTradeService.updateBatchById(entityList);
        return CoTradeReply.newBuilder()
                .setStatus(b)
                .setMessage("success")
                .build();
    }

    @Override
    public CoTradeReply removeAll (CoTradeIdsRequest request) {
        boolean b = coTradeService.removeByIds(request.getIdList());
        return CoTradeReply.newBuilder()
                .setStatus(b)
                .setMessage("success")
                .build();
    }

    @SneakyThrows
    private List<CoTrade> poList2entityList (List<CoTradePO> poList) {
        return ProtoBeanUtils.toPojoBeanList(CoTrade.class, poList);
    }

    @SneakyThrows
    private List<CoTradePO> entityList2poList (List<CoTrade> entityList) {
        return ProtoBeanUtils.toProtoBeanList(CoTradePO.getDefaultInstance(), entityList);
    }

    @SneakyThrows
    private CoTradePO entity2po (CoTrade entity) {
        CoTradePO.Builder builder = CoTradePO.newBuilder();
        ProtoBeanUtils.toProtoBean(builder, entity);
        return builder.build();
    }

    @SneakyThrows
    private CoTrade po2entity (CoTradePO po) {
        return ProtoBeanUtils.toPojoBean(CoTrade.class, po);
    }
}
