package com.powernode.crm.settings.web.controller;

import com.powernode.crm.commons.domain.ReturnObject;
import com.powernode.crm.settings.domain.DicType;
import com.powernode.crm.settings.service.DicTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName:DicTypeController
 * Package:com.powernode.crm.settings.web.controller
 * Description:
 *
 * @Author abel
 * @CreateDate 2023-11-06 19:19
 * @Version 1.0
 */
@Controller
public class DicTypeController {

    @Autowired
    private DicTypeService dicTypeService;

    @RequestMapping("/settings/dictionary/type/queryAllDicType.do")
    @ResponseBody
    public Object queryAllDicType(){
        List<DicType> dicTypeList = dicTypeService.queryAllDicType();
        Map<String, Object> retMap = new HashMap<>();
        retMap.put("dicTypeList", dicTypeList);
        return retMap;
    }
}
