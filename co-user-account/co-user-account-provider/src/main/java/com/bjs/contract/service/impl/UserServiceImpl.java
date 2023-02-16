package com.bjs.contract.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bjs.contract.entity.User;
import com.bjs.contract.mapper.UserMapper;
import com.bjs.contract.service.UserService;
import org.springframework.stereotype.Service;

/**
 * 用户表
 *
 * @author bjs code generator
 * @date 2022-11-11 11:09:58
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
