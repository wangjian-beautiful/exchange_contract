package com.bjs.contract.service.biz;

import com.bijinsuo.common.identify.UserContextHolder;
import com.bjs.contract.proto.userLeverage.*;
import com.bjs.contract.service.UserLeverageService;
import com.bjs.contract.entity.UserLeverage;
import com.bijinsuo.common.utils.ProtoBeanUtils;
import lombok.SneakyThrows;
import org.apache.dubbo.config.annotation.DubboService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: bjs code generator
 * @Date: 2022-11-30 11:36:13
 * @Description:
 */
@DubboService
public class UserLeverageBizServiceImpl implements UserLeverageBizService {

    @Resource
    private UserLeverageService userLeverageService;

    @Override
    public UserLeveragePO getById(UserLeverageRequest request) {
        UserLeverage result = userLeverageService.getById(request.getId());

        UserLeveragePO po = entity2po(result);
        return po;
    }

    @Override
    public UserLeveragePO getBySymbol(GetBySymbolRequest request) {
        UserLeverage result = userLeverageService.findByUidAndSymbolAndNotExistInit(UserContextHolder.get().getId()
                , request.getSymbol());
        UserLeveragePO po = entity2po(result);
        return po;
    }


    @Override
    public UserLeverageReply insertOne(UserLeverageRequest request) {
        UserLeveragePO po = request.getUserLeveragePO();
        UserLeverage entity = po2entity(po);

        boolean result = userLeverageService.save(entity);
        UserLeveragePO res = entity2po(entity);
        return UserLeverageReply.newBuilder()
                .setStatus(result)
                .setMessage("success")
                .setUserLeveragePO(res)
                .build();
    }

    @Override
    public UserLeverageReply updateById(UserLeverageRequest request) {
        UserLeveragePO po = request.getUserLeveragePO();
        UserLeverage entity = po2entity(po);

        boolean b = userLeverageService.updateById(entity);
        return UserLeverageReply.newBuilder()
                .setStatus(b)
                .setMessage("success")
                .build();
    }

    @Override
    public UserLeverageReply removeById(UserLeverageRequest request) {
        boolean b = userLeverageService.removeById(request.getId());
        return UserLeverageReply.newBuilder()
                .setStatus(b)
                .setMessage("success")
                .build();
    }

    @Override
    public UserLeverageListReply selectListByIds(UserLeverageIdsRequest request) {
        List<UserLeverage> userLeverageList = userLeverageService.listByIds(request.getIdList());
        List<UserLeveragePO> userLeveragePOList = entityList2poList(userLeverageList);
        return UserLeverageListReply.newBuilder()
                .addAllUserLeveragePO(userLeveragePOList)
                .build();
    }

    @Override
    public UserLeverageListReply selectAll(UserLeveragePageRequest request) {
        if (request.getPage() != 0 && request.getSize() != 0) {
            PageHelper.startPage(request.getPage(), request.getSize());
        }
        List<UserLeverage> userLeverageList = userLeverageService.list();
        PageInfo<UserLeverage> pageInfo = new PageInfo<>(userLeverageList);
        List<UserLeveragePO> userLeveragePOList = entityList2poList(pageInfo.getList());
        return UserLeverageListReply.newBuilder()
                .setTotal(pageInfo.getTotal())
                .addAllUserLeveragePO(userLeveragePOList)
                .build();
    }

    @Override
    public UserLeverageListReply selectList(UserLeveragePageRequest request) {
        if (request.getPage() != 0 && request.getSize() != 0) {
            PageHelper.startPage(request.getPage(), request.getSize());
        }
        UserLeveragePO po = request.getUserLeveragePO();
        UserLeverage userLeverage = po2entity(po);
        QueryWrapper<UserLeverage> queryWrapper = new QueryWrapper<>(userLeverage);
        List<UserLeverage> userLeverageList = userLeverageService.list(queryWrapper);
        PageInfo<UserLeverage> pageInfo = new PageInfo<>(userLeverageList);
        List<UserLeveragePO> userLeveragePOList = entityList2poList(pageInfo.getList());
        return UserLeverageListReply.newBuilder()
                .setTotal(pageInfo.getTotal())
                .addAllUserLeveragePO(userLeveragePOList)
                .build();
    }

    @Override
    public UserLeverageListReply insertBatch(UserLeverageListRequest request) {
        List<UserLeveragePO> dataList = request.getUserLeveragePOList();
        List<UserLeverage> entityList = poList2entityList(dataList);

        userLeverageService.saveBatch(entityList);

        List<UserLeveragePO> userLeveragePOList = entityList2poList(entityList);
        return UserLeverageListReply.newBuilder()
                .addAllUserLeveragePO(userLeveragePOList)
                .build();
    }

    @Override
    public UserLeverageReply updateBatch(UserLeverageListRequest request) {
        List<UserLeveragePO> dataList = request.getUserLeveragePOList();
        List<UserLeverage> entityList = poList2entityList(dataList);
        boolean b = userLeverageService.updateBatchById(entityList);
        return UserLeverageReply.newBuilder()
                .setStatus(b)
                .setMessage("success")
                .build();
    }

    @Override
    public UserLeverageReply removeAll(UserLeverageIdsRequest request) {
        boolean b = userLeverageService.removeByIds(request.getIdList());
        return UserLeverageReply.newBuilder()
                .setStatus(b)
                .setMessage("success")
                .build();
    }

    @Override
    public UserLeverageReply updateLeverage(UpdateLeverageRequest request) {
        userLeverageService.updateLeverage(UserContextHolder.get().getId(), request.getSymbol(), request.getLeverage());
        return UserLeverageReply.newBuilder()
                .setStatus(true)
                .setMessage("success")
                .build();
    }

    @SneakyThrows
    private List<UserLeverage> poList2entityList(List<UserLeveragePO> poList) {
        return ProtoBeanUtils.toPojoBeanList(UserLeverage.class, poList);
    }

    @SneakyThrows
    private List<UserLeveragePO> entityList2poList(List<UserLeverage> entityList) {
        return ProtoBeanUtils.toProtoBeanList(UserLeveragePO.getDefaultInstance(), entityList);
    }

    @SneakyThrows
    private UserLeveragePO entity2po(UserLeverage entity) {
        UserLeveragePO.Builder builder = UserLeveragePO.newBuilder();
        ProtoBeanUtils.toProtoBean(builder, entity);
        return builder.build();
    }

    @SneakyThrows
    private UserLeverage po2entity(UserLeveragePO po) {
        return ProtoBeanUtils.toPojoBean(UserLeverage.class, po);
    }
}
