package com.bjs.contract.service.biz;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bijinsuo.common.utils.ProtoBeanUtils;
import com.bjs.contract.entity.MaintenanceMarginRate;
import com.bjs.contract.mapper.MaintenanceMarginRateMapper;
import com.bjs.contract.proto.maintenanceMarginRate.*;
import com.bjs.contract.service.MaintenanceMarginRateService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.SneakyThrows;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Auther: bjs code generator
 * @Date: 2022-11-11 11:01:31
 * @Description:
 */
@DubboService(filter = {"SetTokenFilter","ExtractTokenFilter"})
public class MaintenanceMarginRateBizServiceImpl implements MaintenanceMarginRateBizService {

    @Resource
    private MaintenanceMarginRateService maintenanceMarginRateService;
    @Resource
    private MaintenanceMarginRateMapper maintenanceMarginRateMapper;

    @Override
    public MaintenanceMarginRatePO getById(MaintenanceMarginRateRequest request) {
        MaintenanceMarginRate result = maintenanceMarginRateService.getById(request.getId());

        MaintenanceMarginRatePO po = entity2po(result);
        return po;
    }

    @Override
    public MaintenanceMarginRatePO getByNominalValue(NominalValueRequest request) {
        MaintenanceMarginRate result = maintenanceMarginRateService.getByNominalValue(request.getSymbol(), new BigDecimal(request.getNominalValue()));

        MaintenanceMarginRatePO po = entity2po(result);
        return po;
    }

    @Override
    public MaintenanceMarginRatePO symbolMaxLeverage(SymbolMaxLeverageRequest request) {
        MaintenanceMarginRate result = maintenanceMarginRateService.symbolMaxLeverage(request.getSymbol());

        MaintenanceMarginRatePO po = entity2po(result);
        return po;
    }

    @Override
    public SymbolIntervalReply symbolInterval(SymbolIntervalRequest request) {
        return null;
    }

    @Override
    public MaintenanceMarginRateReply insertOne(MaintenanceMarginRateRequest request) {
        MaintenanceMarginRatePO po = request.getMaintenanceMarginRatePO();
        MaintenanceMarginRate entity = po2entity(po);

        boolean result = maintenanceMarginRateService.save(entity);
        MaintenanceMarginRatePO res = entity2po(entity);
        return MaintenanceMarginRateReply.newBuilder()
                .setStatus(result)
                .setMessage("success")
                .setMaintenanceMarginRatePO(res)
                .build();
    }

    @Override
    public MaintenanceMarginRateReply updateById(MaintenanceMarginRateRequest request) {
        MaintenanceMarginRatePO po = request.getMaintenanceMarginRatePO();
        MaintenanceMarginRate entity = po2entity(po);

        boolean b = maintenanceMarginRateService.updateById(entity);
        return MaintenanceMarginRateReply.newBuilder()
                .setStatus(b)
                .setMessage("success")
                .build();
    }

    @Override
    public MaintenanceMarginRateReply removeById(MaintenanceMarginRateRequest request) {
        boolean b = maintenanceMarginRateService.removeById(request.getId());
        return MaintenanceMarginRateReply.newBuilder()
                .setStatus(b)
                .setMessage("success")
                .build();
    }

    @Override
    public MaintenanceMarginRateListReply selectListByIds(MaintenanceMarginRateIdsRequest request) {
        List<MaintenanceMarginRate> maintenanceMarginRateList = maintenanceMarginRateService.listByIds(request.getIdList());
        List<MaintenanceMarginRatePO> maintenanceMarginRatePOList = entityList2poList(maintenanceMarginRateList);
        return MaintenanceMarginRateListReply.newBuilder()
                .addAllMaintenanceMarginRatePO(maintenanceMarginRatePOList)
                .build();
    }

    @Override
    public MaintenanceMarginRateListReply selectAll(MaintenanceMarginRatePageRequest request) {
        if (request.getPage() != 0 && request.getSize() != 0) {
            PageHelper.startPage(request.getPage(), request.getSize());
        }
        List<MaintenanceMarginRate> maintenanceMarginRateList = maintenanceMarginRateService.list();
        PageInfo<MaintenanceMarginRate> pageInfo = new PageInfo<>(maintenanceMarginRateList);
        List<MaintenanceMarginRatePO> maintenanceMarginRatePOList = entityList2poList(pageInfo.getList());
        return MaintenanceMarginRateListReply.newBuilder()
                .setTotal(pageInfo.getTotal())
                .addAllMaintenanceMarginRatePO(maintenanceMarginRatePOList)
                .build();
    }

    @Override
    public MaintenanceMarginRateListReply selectList(MaintenanceMarginRatePageRequest request) {
        if (request.getPage() != 0 && request.getSize() != 0) {
            PageHelper.startPage(request.getPage(), request.getSize());
        }
        MaintenanceMarginRatePO po = request.getMaintenanceMarginRatePO();
        MaintenanceMarginRate maintenanceMarginRate = po2entity(po);
        QueryWrapper<MaintenanceMarginRate> queryWrapper = new QueryWrapper<>(maintenanceMarginRate);
        List<MaintenanceMarginRate> maintenanceMarginRateList = maintenanceMarginRateService.list(queryWrapper);
        PageInfo<MaintenanceMarginRate> pageInfo = new PageInfo<>(maintenanceMarginRateList);
        List<MaintenanceMarginRatePO> maintenanceMarginRatePOList = entityList2poList(pageInfo.getList());
        return MaintenanceMarginRateListReply.newBuilder()
                .setTotal(pageInfo.getTotal())
                .addAllMaintenanceMarginRatePO(maintenanceMarginRatePOList)
                .build();
    }

    @Override
    public MaintenanceMarginRateListReply insertBatch(MaintenanceMarginRateListRequest request) {
        List<MaintenanceMarginRatePO> dataList = request.getMaintenanceMarginRatePOList();
        List<MaintenanceMarginRate> entityList = poList2entityList(dataList);

        maintenanceMarginRateService.saveBatch(entityList);

        List<MaintenanceMarginRatePO> maintenanceMarginRatePOList = entityList2poList(entityList);
        return MaintenanceMarginRateListReply.newBuilder()
                .addAllMaintenanceMarginRatePO(maintenanceMarginRatePOList)
                .build();
    }

    @Override
    public MaintenanceMarginRateReply updateBatch(MaintenanceMarginRateListRequest request) {
        List<MaintenanceMarginRatePO> dataList = request.getMaintenanceMarginRatePOList();
        List<MaintenanceMarginRate> entityList = poList2entityList(dataList);
        boolean b = maintenanceMarginRateService.updateBatchById(entityList);
        return MaintenanceMarginRateReply.newBuilder()
                .setStatus(b)
                .setMessage("success")
                .build();
    }

    @Override
    public MaintenanceMarginRateReply removeAll(MaintenanceMarginRateIdsRequest request) {
        boolean b = maintenanceMarginRateService.removeByIds(request.getIdList());
        return MaintenanceMarginRateReply.newBuilder()
                .setStatus(b)
                .setMessage("success")
                .build();
    }

    @Override
    public MaintenanceMarginRateMinReply getMaintenanceMarginRateMin(MaintenanceMarginRateMinRequest request) {
        String minRate=maintenanceMarginRateMapper.getMinRate(request.getSymbol());
        return MaintenanceMarginRateMinReply.newBuilder().setMinRate(minRate).build();
    }

    @SneakyThrows
    private List<MaintenanceMarginRate> poList2entityList(List<MaintenanceMarginRatePO> poList) {
        return ProtoBeanUtils.toPojoBeanList(MaintenanceMarginRate.class, poList);
    }

    @SneakyThrows
    private List<MaintenanceMarginRatePO> entityList2poList(List<MaintenanceMarginRate> entityList) {
        return ProtoBeanUtils.toProtoBeanList(MaintenanceMarginRatePO.getDefaultInstance(), entityList);
    }

    @SneakyThrows
    private MaintenanceMarginRatePO entity2po(MaintenanceMarginRate entity) {
        MaintenanceMarginRatePO.Builder builder = MaintenanceMarginRatePO.newBuilder();
        ProtoBeanUtils.toProtoBean(builder, entity);
        return builder.build();
    }

    @SneakyThrows
    private MaintenanceMarginRate po2entity(MaintenanceMarginRatePO po) {
        return ProtoBeanUtils.toPojoBean(MaintenanceMarginRate.class, po);
    }
}
