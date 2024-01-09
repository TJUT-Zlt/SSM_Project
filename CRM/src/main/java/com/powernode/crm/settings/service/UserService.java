package com.powernode.crm.settings.service;

import com.powernode.crm.settings.domain.User;

import java.util.List;
import java.util.Map;

/**
 * ClassName:UserService
 * Package:com.powernode.crm.settings.service.impl
 * Description:
 *
 * @Author abel
 * @CreateDate 2023-10-18 21:50
 * @Version 1.0
 */
public interface UserService {

    User selectUserByLoginActAndPwd(Map<String,Object> map);

    List<User> queryAllUser();

    int saveEditPassword(User user);

}
