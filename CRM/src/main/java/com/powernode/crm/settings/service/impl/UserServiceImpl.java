package com.powernode.crm.settings.service.impl;

import com.powernode.crm.settings.domain.User;
import com.powernode.crm.settings.mapper.UserMapper;
import com.powernode.crm.settings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * ClassName:UserServiceImpl
 * Package:com.powernode.crm.settings.service.impl
 * Description:
 *
 * @Author abel
 * @CreateDate 2023-10-18 21:51
 * @Version 1.0
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private  UserMapper userMapper;

    @Override
    public User selectUserByLoginActAndPwd(Map<String, Object> map) {
        return userMapper.selectUserByLoginActAndPwd(map);
    }

    @Override
    public List<User> queryAllUser() {
        return userMapper.selectAllUser();
    }

    @Override
    public int saveEditPassword(User user) {
        return userMapper.updatePassword(user);
    }
}
