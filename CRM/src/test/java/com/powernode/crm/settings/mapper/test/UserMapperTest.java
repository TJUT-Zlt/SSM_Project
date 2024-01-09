package com.powernode.crm.settings.mapper.test;

import com.powernode.crm.settings.domain.User;
import com.powernode.crm.settings.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName:UserMapperTest
 * Package:com.powernode.crm.settings.mapper.test
 * Description:
 *
 * @Author abel
 * @CreateDate 2023-10-18 21:32
 * @Version 1.0
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext_service.xml", "classpath:applicationContext-mapper.xml"})
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelectUserByLoginActAndPwd(){

        Map map = new HashMap<>();
        map.put("loginAct","ls");
        map.put("loginPwd","yf123");
        User user = userMapper.selectUserByLoginActAndPwd(map);
        System.out.println(user);
    }

    @Test
    public void testSelectAllUser(){
        List<User> users = userMapper.selectAllUser();
        users.forEach(user -> System.out.println(user));
    }


    @Test
    public void testUpdatePassword(){
        User user = new User();
        user.setId("06f5fc056eac41558a964f96daa7f27c");
        user.setLoginPwd("123456");
        int ret = userMapper.updatePassword(user);
        System.out.println(ret);
    }

}
