package com.powernode.crm.settings.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ClassName:SystemController
 * Package:com.powernode.crm.settings.web.controller
 * Description:
 *
 * @Author abel
 * @CreateDate 2023-10-28 16:42
 * @Version 1.0
 */
@Controller
public class SystemController {

    @RequestMapping("/settings/settingsIndex.do")
    public String settingsIndex(){
        return "settings/settingsIndex";
    }

    @RequestMapping("/settings/qx/qxIndex.do")
    public String qxIndex(){
        return "settings/qx/qxIndex";
    }

    @RequestMapping("/settings/qx/permission/permissionIndex.do")
    public String permissionIndex(){
        return "settings/qx/permission/permissionIndex";
    }

    @RequestMapping("/settings/qx/role/permissionIndex.do")
    public String roleIndex(){
        return "settings/qx/role/roleIndex";
    }

    @RequestMapping("/settings/qx/user/permissionIndex.do")
    public String userIndex(){
        return "settings/qx/user/userIndex";
    }

    @RequestMapping("/settings/dictionary/dictionaryIndex.do")
    public String dictionaryIndex(){
        return "settings/dictionary/dictionaryIndex";
    }

    @RequestMapping("/settings/dictionary/type/typeIndex.do")
    public String typeIndex(){
        return "settings/dictionary/type/typeIndex";
    }

    @RequestMapping("/settings/dictionary/type/valueIndex.do")
    public String valueIndex(){
        return "settings/dictionary/value/valueIndex";
    }
}
