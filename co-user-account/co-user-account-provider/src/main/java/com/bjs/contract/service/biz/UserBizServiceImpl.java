package com.bjs.contract.service.biz;

import com.bjs.contract.proto.user.UserPO;
import com.bjs.contract.proto.user.UserBizService;
import com.bjs.contract.proto.user.UserRequest;
import com.bjs.contract.proto.user.UserPageRequest;
import com.bjs.contract.proto.user.UserIdsRequest;
import com.bjs.contract.proto.user.UserListRequest;
import com.bjs.contract.proto.user.UserReply;
import com.bjs.contract.proto.user.UserListReply;
import com.bjs.contract.service.UserService;
import com.bjs.contract.entity.User;
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
 * @Date: 2022-11-11 11:09:58
 * @Description:
 */
@DubboService(filter = {"SetTokenFilter","ExtractTokenFilter"})
public class UserBizServiceImpl implements UserBizService {

    @Resource
    private UserService userService;

    @Override
    public UserPO getById(UserRequest request) {
        User result = userService.getById(request.getId());

        UserPO po = entity2po(result);
        return po;
    }

    @Override
    public UserReply insertOne(UserRequest request) {
        UserPO po = request.getUserPO();
        User entity = po2entity(po);

        boolean result = userService.save(entity);
        UserPO res = entity2po(entity);
        return UserReply.newBuilder()
                .setStatus(result)
                .setMessage("success")
                .setUserPO(res)
                .build();
    }

    @Override
    public UserReply updateById(UserRequest request) {
        UserPO po = request.getUserPO();
        User entity = po2entity(po);

        boolean b = userService.updateById(entity);
        return UserReply.newBuilder()
                .setStatus(b)
                .setMessage("success")
                .build();
    }

    @Override
    public UserReply removeById(UserRequest request) {
        boolean b = userService.removeById(request.getId());
        return UserReply.newBuilder()
                .setStatus(b)
                .setMessage("success")
                .build();
    }

    @Override
    public UserListReply selectListByIds(UserIdsRequest request) {
        List<User> userList = userService.listByIds(request.getIdList());
        List<UserPO> userPOList = entityList2poList(userList);
        return UserListReply.newBuilder()
                .addAllUserPO(userPOList)
                .build();
    }

    @Override
    public UserListReply selectAll(UserPageRequest request) {
        if (request.getPage() !=0 && request.getSize()!=0) {
            PageHelper.startPage(request.getPage(), request.getSize());
        }
        List<User> userList = userService.list();
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        List<UserPO> userPOList = entityList2poList(pageInfo.getList());
        return UserListReply.newBuilder()
                .setTotal(pageInfo.getTotal())
                .addAllUserPO(userPOList)
                .build();
    }

    @Override
    public UserListReply selectList(UserPageRequest request) {
        if (request.getPage() !=0 && request.getSize()!=0) {
            PageHelper.startPage(request.getPage(), request.getSize());
        }
        UserPO po = request.getUserPO();
        User user = po2entity(po);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>(user);
        List<User> userList = userService.list(queryWrapper);
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        List<UserPO> userPOList = entityList2poList(pageInfo.getList());
        return UserListReply.newBuilder()
                .setTotal(pageInfo.getTotal())
                .addAllUserPO(userPOList)
                .build();
    }

    @Override
    public UserListReply insertBatch (UserListRequest request) {
        List<UserPO> dataList = request.getUserPOList();
        List<User> entityList = poList2entityList(dataList);

        userService.saveBatch(entityList);

        List<UserPO> userPOList = entityList2poList(entityList);
        return UserListReply.newBuilder()
                .addAllUserPO(userPOList)
                .build();
    }

    @Override
    public UserReply updateBatch (UserListRequest request) {
        List<UserPO> dataList = request.getUserPOList();
        List<User> entityList = poList2entityList(dataList);
        boolean b = userService.updateBatchById(entityList);
        return UserReply.newBuilder()
                .setStatus(b)
                .setMessage("success")
                .build();
    }

    @Override
    public UserReply removeAll (UserIdsRequest request) {
        boolean b = userService.removeByIds(request.getIdList());
        return UserReply.newBuilder()
                .setStatus(b)
                .setMessage("success")
                .build();
    }

    @SneakyThrows
    private List<User> poList2entityList (List<UserPO> poList) {
        return ProtoBeanUtils.toPojoBeanList(User.class, poList);
    }

    @SneakyThrows
    private List<UserPO> entityList2poList (List<User> entityList) {
        return ProtoBeanUtils.toProtoBeanList(UserPO.getDefaultInstance(), entityList);
    }

    @SneakyThrows
    private UserPO entity2po (User entity) {
        UserPO.Builder builder = UserPO.newBuilder();
        ProtoBeanUtils.toProtoBean(builder, entity);
        return builder.build();
    }

    @SneakyThrows
    private User po2entity (UserPO po) {
        return ProtoBeanUtils.toPojoBean(User.class, po);
    }
}
