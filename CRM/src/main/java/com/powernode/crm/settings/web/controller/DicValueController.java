package com.powernode.crm.settings.web.controller;

import com.powernode.crm.settings.domain.DicType;
import com.powernode.crm.settings.domain.DicValue;
import com.powernode.crm.settings.service.DicTypeService;
import com.powernode.crm.settings.service.DicValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName:DicValueController
 * Package:com.powernode.crm.settings.web.controller
 * Description:
 *
 * @Author abel
 * @CreateDate 2023-11-06 20:04
 * @Version 1.0
 */
@Controller
public class DicValueController {
    @Autowired
    private DicValueService dicValueService;

    @RequestMapping("/settings/dictionary/value/queryAllDicValue.do")
    @ResponseBody
    public Object queryAllDicValue(){
        List<DicValue> dicValueList = dicValueService.queryAllDicValue();
        Map<String, Object> retMap = new HashMap<>();
        retMap.put("dicValueList", dicValueList);
        return retMap;
    }

}
