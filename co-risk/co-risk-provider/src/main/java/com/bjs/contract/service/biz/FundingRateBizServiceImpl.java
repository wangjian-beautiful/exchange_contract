package com.bjs.contract.service.biz;

import com.bjs.contract.proto.fundingRate.FundingRatePO;
import com.bjs.contract.proto.fundingRate.FundingRateBizService;
import com.bjs.contract.proto.fundingRate.FundingRateRequest;
import com.bjs.contract.proto.fundingRate.FundingRatePageRequest;
import com.bjs.contract.proto.fundingRate.FundingRateIdsRequest;
import com.bjs.contract.proto.fundingRate.FundingRateListRequest;
import com.bjs.contract.proto.fundingRate.FundingRateReply;
import com.bjs.contract.proto.fundingRate.FundingRateListReply;
import com.bjs.contract.service.FundingRateService;
import com.bjs.contract.entity.FundingRate;
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
 * @Date: 2022-11-11 11:13:47
 * @Description:
 */
@DubboService(filter = {"SetTokenFilter","ExtractTokenFilter"})
public class FundingRateBizServiceImpl implements FundingRateBizService {

    @Resource
    private FundingRateService fundingRateService;

    @Override
    public FundingRatePO getById(FundingRateRequest request) {
        FundingRate result = fundingRateService.getById(request.getId());

        FundingRatePO po = entity2po(result);
        return po;
    }

    @Override
    public FundingRateReply insertOne(FundingRateRequest request) {
        FundingRatePO po = request.getFundingRatePO();
        FundingRate entity = po2entity(po);

        boolean result = fundingRateService.save(entity);
        FundingRatePO res = entity2po(entity);
        return FundingRateReply.newBuilder()
                .setStatus(result)
                .setMessage("success")
                .setFundingRatePO(res)
                .build();
    }

    @Override
    public FundingRateReply updateById(FundingRateRequest request) {
        FundingRatePO po = request.getFundingRatePO();
        FundingRate entity = po2entity(po);

        boolean b = fundingRateService.updateById(entity);
        return FundingRateReply.newBuilder()
                .setStatus(b)
                .setMessage("success")
                .build();
    }

    @Override
    public FundingRateReply removeById(FundingRateRequest request) {
        boolean b = fundingRateService.removeById(request.getId());
        return FundingRateReply.newBuilder()
                .setStatus(b)
                .setMessage("success")
                .build();
    }

    @Override
    public FundingRateListReply selectListByIds(FundingRateIdsRequest request) {
        List<FundingRate> fundingRateList = fundingRateService.listByIds(request.getIdList());
        List<FundingRatePO> fundingRatePOList = entityList2poList(fundingRateList);
        return FundingRateListReply.newBuilder()
                .addAllFundingRatePO(fundingRatePOList)
                .build();
    }

    @Override
    public FundingRateListReply selectAll(FundingRatePageRequest request) {
        if (request.getPage() !=0 && request.getSize()!=0) {
            PageHelper.startPage(request.getPage(), request.getSize());
        }
        List<FundingRate> fundingRateList = fundingRateService.list();
        PageInfo<FundingRate> pageInfo = new PageInfo<>(fundingRateList);
        List<FundingRatePO> fundingRatePOList = entityList2poList(pageInfo.getList());
        return FundingRateListReply.newBuilder()
                .setTotal(pageInfo.getTotal())
                .addAllFundingRatePO(fundingRatePOList)
                .build();
    }

    @Override
    public FundingRateListReply selectList(FundingRatePageRequest request) {
        if (request.getPage() !=0 && request.getSize()!=0) {
            PageHelper.startPage(request.getPage(), request.getSize());
        }
        FundingRatePO po = request.getFundingRatePO();
        FundingRate fundingRate = po2entity(po);
        QueryWrapper<FundingRate> queryWrapper = new QueryWrapper<>(fundingRate);
        List<FundingRate> fundingRateList = fundingRateService.list(queryWrapper);
        PageInfo<FundingRate> pageInfo = new PageInfo<>(fundingRateList);
        List<FundingRatePO> fundingRatePOList = entityList2poList(pageInfo.getList());
        return FundingRateListReply.newBuilder()
                .setTotal(pageInfo.getTotal())
                .addAllFundingRatePO(fundingRatePOList)
                .build();
    }

    @Override
    public FundingRateListReply insertBatch (FundingRateListRequest request) {
        List<FundingRatePO> dataList = request.getFundingRatePOList();
        List<FundingRate> entityList = poList2entityList(dataList);

        fundingRateService.saveBatch(entityList);

        List<FundingRatePO> fundingRatePOList = entityList2poList(entityList);
        return FundingRateListReply.newBuilder()
                .addAllFundingRatePO(fundingRatePOList)
                .build();
    }

    @Override
    public FundingRateReply updateBatch (FundingRateListRequest request) {
        List<FundingRatePO> dataList = request.getFundingRatePOList();
        List<FundingRate> entityList = poList2entityList(dataList);
        boolean b = fundingRateService.updateBatchById(entityList);
        return FundingRateReply.newBuilder()
                .setStatus(b)
                .setMessage("success")
                .build();
    }

    @Override
    public FundingRateReply removeAll (FundingRateIdsRequest request) {
        boolean b = fundingRateService.removeByIds(request.getIdList());
        return FundingRateReply.newBuilder()
                .setStatus(b)
                .setMessage("success")
                .build();
    }

    @SneakyThrows
    private List<FundingRate> poList2entityList (List<FundingRatePO> poList) {
        return ProtoBeanUtils.toPojoBeanList(FundingRate.class, poList);
    }

    @SneakyThrows
    private List<FundingRatePO> entityList2poList (List<FundingRate> entityList) {
        return ProtoBeanUtils.toProtoBeanList(FundingRatePO.getDefaultInstance(), entityList);
    }

    @SneakyThrows
    private FundingRatePO entity2po (FundingRate entity) {
        FundingRatePO.Builder builder = FundingRatePO.newBuilder();
        ProtoBeanUtils.toProtoBean(builder, entity);
        return builder.build();
    }

    @SneakyThrows
    private FundingRate po2entity (FundingRatePO po) {
        return ProtoBeanUtils.toPojoBean(FundingRate.class, po);
    }
}
