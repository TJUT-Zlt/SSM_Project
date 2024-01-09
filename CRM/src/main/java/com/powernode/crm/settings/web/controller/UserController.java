package com.powernode.crm.settings.web.controller;

import com.mysql.cj.Session;
import com.powernode.crm.commons.constants.Constants;
import com.powernode.crm.commons.domain.ReturnObject;
import com.powernode.crm.commons.utils.DateUtils;
import com.powernode.crm.settings.domain.User;
import com.powernode.crm.settings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName:UserController
 * Package:com.powernode.crm.settings.web.controller
 * Description:
 *
 * @Author abel
 * @CreateDate 2023-10-18 8:47
 * @Version 1.0
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 跳转到登录页面
     * @return
     */
    //@RequestMapping("/WEB-INF/pages/settings/qx/user/toLogin.do")
    @RequestMapping("/settings/qx/user/toLogin.do")
    public String toLogin(){
        return "settings/qx/user/login";
    }

    /**
     * 登录判断
     * @param loginAct
     * @param loginPwd
     * @param isRemPwd
     * @param request
     * @param response
     * @param session
     * @return
     */
    @RequestMapping("/settings/qx/user/login.do")
    @ResponseBody
    public  Object login(String loginAct, String loginPwd, String isRemPwd, HttpServletRequest request, HttpServletResponse response, HttpSession session){
        //封装参数
        Map<String,Object> map = new HashMap<>();
        map.put("loginAct",loginAct);
        map.put("loginPwd",loginPwd);
        //调用service层方法，查询用户
        User user = userService.selectUserByLoginActAndPwd(map);

        //根据查询结果，生成响应信息
        ReturnObject returnObject=new ReturnObject();

        if(user == null){
            returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("用户名或者密码错误");
        }else {
            //登录失败，账号已过期
            if(DateUtils.formateDateTime(new Date()).compareTo(user.getExpireTime())>0){
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("账号已过期");
            }else if("0".equals(user.getLockState())){
                //登录失败，状态被锁定
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("状态被锁定");
            }else if(!user.getAllowIps().contains(request.getRemoteAddr())){
                //登录失败，ip受限
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("ip受限");
            }else {
                //登录成功
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_SUCCESS);
                //把user保存到session中
                session.setAttribute(Constants.SESSION_USER,user);
                //选择记住密码
                if("true".equals(isRemPwd)){
                    Cookie cookieLoginAct = new Cookie("cookieLoginAct",user.getLoginAct());
                    Cookie cookieLoginPwd = new Cookie("cookieLoginPwd",user.getLoginPwd());
                    cookieLoginAct.setMaxAge(10*24*60*60);
                    cookieLoginPwd.setMaxAge(10*24*60*60);
                    response.addCookie(cookieLoginAct);
                    response.addCookie(cookieLoginPwd);
                }else{
                    //不选择记住密码,删除cookie
                    Cookie cookieLoginAct = new Cookie("cookieLoginAct","1");
                    Cookie cookieLoginPwd = new Cookie("cookieLoginPwd","1");
                    cookieLoginAct.setMaxAge(0);
                    cookieLoginPwd.setMaxAge(0);
                    response.addCookie(cookieLoginAct);
                    response.addCookie(cookieLoginPwd);
                }
            }
        }
        return returnObject;
    }

    @RequestMapping("/settings/qx/user/logout.do")
    public String logout(HttpServletResponse response,HttpSession session){
        //清空cookie
        Cookie cookieLoginAct = new Cookie("cookieLoginAct","1");
        Cookie cookieLoginPwd = new Cookie("cookieLoginPwd","1");
        cookieLoginAct.setMaxAge(0);
        cookieLoginPwd.setMaxAge(0);
        response.addCookie(cookieLoginAct);
        response.addCookie(cookieLoginPwd);
        //销毁session
        session.invalidate();
        //跳转到首页
        //借助SpringMvc来重定向,response.sendRedirect("/crm/");
        return "redirect:/";
    }

    @RequestMapping("/settings/qx/user/editPassword.do")
    @ResponseBody
    public Object editPassword(HttpSession session,String loginPwd){

        ReturnObject returnObject= new ReturnObject();
        User user = (User) session.getAttribute(Constants.SESSION_USER);
        user.setLoginPwd(loginPwd);

        try {
            int ret = userService.saveEditPassword(user);

            if(ret > 0){
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_SUCCESS);
            }else{
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统繁忙，请稍后重试....");
            }

        }catch (Exception e){
            e.printStackTrace();
            returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统异常，请稍后重试....");
        }
        return returnObject;
    }


}
