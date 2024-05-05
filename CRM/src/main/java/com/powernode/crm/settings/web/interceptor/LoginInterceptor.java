package com.powernode.crm.settings.web.interceptor;

import com.powernode.crm.commons.constants.Constants;
import com.powernode.crm.settings.domain.User;
import com.sun.org.apache.xpath.internal.SourceTree;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * ClassName:LoginInterceptor
 * Package:com.powernode.crm.settings.web.interceptor
 * Description:
 *
 * @Author abel
 * @CreateDate 2023-10-19 16:58
 * @Version 1.0
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //如果用户没有登录成功,则跳转到登录页面
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute(Constants.SESSION_USER);
        if(user == null){
            //重定向时，url必须加项目的名称
            System.out.println("*******************" + request.getContextPath());
            response.sendRedirect(request.getContextPath());

            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
