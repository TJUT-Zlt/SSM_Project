package com.powernode.crm.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ClassName:IndexController
 * Package:com.powernode.crm.web.controller
 * Description:
 *
 * @Author abel
 * @CreateDate 2023-10-18 8:21
 * @1.0
 */
@Controller
public class IndexController {
    /**
     * -----------起源---------
     * 跳转到首页
     * @return
     */
    @RequestMapping("/")
    public String index(){
        return "index";
    }
}
