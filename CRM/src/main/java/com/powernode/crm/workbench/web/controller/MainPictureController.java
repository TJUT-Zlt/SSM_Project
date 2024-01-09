package com.powernode.crm.workbench.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ClassName:MainPictureController
 * Package:com.powernode.crm.workbench.web.controller
 * Description: 跳转跳转workbench/main/PictureIndex.jsp 工作台图片
 *
 * @Author abel
 * @CreateDate 2023-10-28 16:34
 * @Version 1.0
 */
@Controller
public class MainPictureController {

    @RequestMapping("/workbench/main/PictureIndex.do")
    public String PictureIndex(){
        //跳转workbench/main/PictureIndex.jsp
        return "workbench/main/PictureIndex";
    }

}
