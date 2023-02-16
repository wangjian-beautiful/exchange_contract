package com.bjs.contract.action;

import com.bijinsuo.common.domain.MaintenanceMarginRateDTO;
import com.bijinsuo.common.utils.ProtoBeanUtils;
import com.bjs.contract.proto.maintenanceMarginRate.*;
import lombok.SneakyThrows;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class MaintenanceMarginRateAction {

    @DubboReference
    private MaintenanceMarginRateBizService maintenanceMarginRateBizService;

    @SneakyThrows
    public MaintenanceMarginRateDTO getById(Long id) {
        MaintenanceMarginRateRequest request = MaintenanceMarginRateRequest.newBuilder().setId(id).build();
        MaintenanceMarginRatePO po = maintenanceMarginRateBizService.getById(request);
        return ProtoBeanUtils.toPojoBean(MaintenanceMarginRateDTO.class, po);
    }

    @SneakyThrows
    public List<MaintenanceMarginRateDTO> selectAll() {
        MaintenanceMarginRatePageRequest request = MaintenanceMarginRatePageRequest.newBuilder().build();
        MaintenanceMarginRateListReply listReply = maintenanceMarginRateBizService.selectAll(request);
        List<MaintenanceMarginRatePO> poList = listReply.getMaintenanceMarginRatePOList();
        return ProtoBeanUtils.toPojoBeanList(MaintenanceMarginRateDTO.class, poList);
    }

    @SneakyThrows
    public List<MaintenanceMarginRateDTO> selectAllByPage(int page, int size) {
        MaintenanceMarginRatePageRequest request = MaintenanceMarginRatePageRequest.newBuilder().setPage(page).setSize(size).build();
        MaintenanceMarginRateListReply listReply = maintenanceMarginRateBizService.selectAll(request);
        List<MaintenanceMarginRatePO> poList = listReply.getMaintenanceMarginRatePOList();
        return ProtoBeanUtils.toPojoBeanList(MaintenanceMarginRateDTO.class, poList);
    }

    @SneakyThrows
    public List<MaintenanceMarginRateDTO> selectList(MaintenanceMarginRateDTO entity) {
        MaintenanceMarginRatePO po = entity2po(entity);
        MaintenanceMarginRatePageRequest request = MaintenanceMarginRatePageRequest.newBuilder().setMaintenanceMarginRatePO(po).build();
        MaintenanceMarginRateListReply listReply = maintenanceMarginRateBizService.selectList(request);
        List<MaintenanceMarginRatePO> poList = listReply.getMaintenanceMarginRatePOList();
        return ProtoBeanUtils.toPojoBeanList(MaintenanceMarginRateDTO.class, poList);
    }

    @SneakyThrows
    public List<MaintenanceMarginRateDTO> selectListByPage(MaintenanceMarginRateDTO entity, int page, int size) {
        MaintenanceMarginRatePO po = entity2po(entity);
        MaintenanceMarginRatePageRequest request = MaintenanceMarginRatePageRequest.newBuilder().setPage(page).setSize(size).setMaintenanceMarginRatePO(po).build();
        MaintenanceMarginRateListReply listReply = maintenanceMarginRateBizService.selectList(request);
        List<MaintenanceMarginRatePO> poList = listReply.getMaintenanceMarginRatePOList();
        return ProtoBeanUtils.toPojoBeanList(MaintenanceMarginRateDTO.class, poList);
    }

    @SneakyThrows
    public boolean insertOne(MaintenanceMarginRateDTO entity) {
        MaintenanceMarginRatePO po = entity2po(entity);
        MaintenanceMarginRateRequest request = MaintenanceMarginRateRequest.newBuilder().setMaintenanceMarginRatePO(po).build();
        MaintenanceMarginRateReply reply = maintenanceMarginRateBizService.insertOne(request);
        boolean b = reply.getStatus();
        if (b) {
            MaintenanceMarginRatePO resultPO = reply.getMaintenanceMarginRatePO();
            MaintenanceMarginRateDTO result = ProtoBeanUtils.toPojoBean(MaintenanceMarginRateDTO.class, resultPO);
            entity.setId(result.getId());
        }
        return b;
    }

    @SneakyThrows
    public List<MaintenanceMarginRateDTO> insertBatch(List<MaintenanceMarginRateDTO> entityList) {
        List<MaintenanceMarginRatePO> poList = ProtoBeanUtils.toProtoBeanList(MaintenanceMarginRatePO.getDefaultInstance(), entityList);
        MaintenanceMarginRateListRequest request = MaintenanceMarginRateListRequest.newBuilder()
                .addAllMaintenanceMarginRatePO(poList)
                .build();
        MaintenanceMarginRateListReply listReply = maintenanceMarginRateBizService.insertBatch(request);
        List<MaintenanceMarginRatePO> reultList = listReply.getMaintenanceMarginRatePOList();
        return ProtoBeanUtils.toPojoBeanList(MaintenanceMarginRateDTO.class, reultList);
    }

    @SneakyThrows
    public boolean updateById(MaintenanceMarginRateDTO entity) {
        MaintenanceMarginRatePO po = entity2po(entity);
        MaintenanceMarginRateRequest request = MaintenanceMarginRateRequest.newBuilder().setMaintenanceMarginRatePO(po).build();
        MaintenanceMarginRateReply reply = maintenanceMarginRateBizService.updateById(request);
        return reply.getStatus();
    }

    @SneakyThrows
    public boolean updateBatch(List<MaintenanceMarginRateDTO> entityList) {
        List<MaintenanceMarginRatePO> poList = ProtoBeanUtils.toProtoBeanList(MaintenanceMarginRatePO.getDefaultInstance(), entityList);
        MaintenanceMarginRateListRequest request = MaintenanceMarginRateListRequest.newBuilder()
                .addAllMaintenanceMarginRatePO(poList)
                .build();
        MaintenanceMarginRateReply reply = maintenanceMarginRateBizService.updateBatch(request);
        return reply.getStatus();
    }

    public boolean removeById(MaintenanceMarginRateDTO entity) {
        MaintenanceMarginRatePO po = entity2po(entity);
        MaintenanceMarginRateRequest request = MaintenanceMarginRateRequest.newBuilder().setId(entity.getId()).build();
        MaintenanceMarginRateReply reply = maintenanceMarginRateBizService.removeById(request);
        return reply.getStatus();
    }

    public boolean removeAll(List<Long> ids) {
        MaintenanceMarginRateIdsRequest request = MaintenanceMarginRateIdsRequest.newBuilder().addAllId(ids).build();
        MaintenanceMarginRateReply reply = maintenanceMarginRateBizService.removeAll(request);
        return reply.getStatus();
    }

    /**
     * 根据合约价值，查询最大杠杆倍数
     *
     * @param symbol
     * @param nominalValue
     * @return
     */
    public Integer getMaxLeverageByNominalValue(String symbol, BigDecimal nominalValue) {
        MaintenanceMarginRatePO po = maintenanceMarginRateBizService.getByNominalValue(
                NominalValueRequest.newBuilder()
                        .setNominalValue(nominalValue.toString())
                        .setSymbol(symbol).build());
        return po.getMaxLeverage().getValue();
    }

    /**
     * 根据币种、开仓名义价值，查询维持保证金率配置
     *
     * @param symbol
     * @param nominalValue
     * @return
     */
    public MaintenanceMarginRateDTO getByNominalValue(String symbol, BigDecimal nominalValue) {
        NominalValueRequest request = NominalValueRequest.newBuilder().setSymbol(symbol)
                .setNominalValue(nominalValue.toPlainString()).build();
        MaintenanceMarginRatePO maintenanceMarginRatePO = maintenanceMarginRateBizService.getByNominalValue(request);
        return ProtoBeanUtils.toPojoBean(MaintenanceMarginRateDTO.class, maintenanceMarginRatePO);
    }

    public MaintenanceMarginRateDTO symbolMaxLeverage(String symbol) {
        SymbolMaxLeverageRequest request = SymbolMaxLeverageRequest.newBuilder()
                .setSymbol(symbol).build();
        MaintenanceMarginRatePO maintenanceMarginRatePO = maintenanceMarginRateBizService.symbolMaxLeverage(request);
        return ProtoBeanUtils.toPojoBean(MaintenanceMarginRateDTO.class, maintenanceMarginRatePO);
    }

    @SneakyThrows
    private MaintenanceMarginRatePO entity2po(MaintenanceMarginRateDTO entity) {
        MaintenanceMarginRatePO.Builder builder = MaintenanceMarginRatePO.newBuilder();
        ProtoBeanUtils.toProtoBean(builder, entity);
        return builder.build();
    }
}
