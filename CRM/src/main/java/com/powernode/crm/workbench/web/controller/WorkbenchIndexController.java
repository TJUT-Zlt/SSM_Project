package com.powernode.crm.workbench.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ClassName:WorkbenchIndexController
 * Package:com.powernode.crm.workbench.web.controller
 * Description:  用于跳转工作台主页面
 *
 * @Author abel
 * @CreateDate 2023-10-19 9:20
 * @Version 1.0
 */
@Controller
public class WorkbenchIndexController {

    @RequestMapping("/workbench/workbenchIndex.do")
    public String workbenchIndex(){
        //跳转到业务主页面  workbenchIndex.jsp
        return "workbench/workbenchIndex";
    }

}
