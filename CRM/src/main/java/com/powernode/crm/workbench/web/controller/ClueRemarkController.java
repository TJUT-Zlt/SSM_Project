package com.powernode.crm.workbench.web.controller;

import com.powernode.crm.commons.constants.Constants;
import com.powernode.crm.commons.domain.ReturnObject;
import com.powernode.crm.commons.utils.DateUtils;
import com.powernode.crm.commons.utils.UUIDUtils;
import com.powernode.crm.settings.domain.User;

import com.powernode.crm.workbench.domain.ActivityRemark;
import com.powernode.crm.workbench.domain.ClueRemark;
import com.powernode.crm.workbench.mapper.ClueRemarkMapper;

import com.powernode.crm.workbench.service.ClueRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * ClassName:ClueRemarkController
 * Package:com.powernode.crm.workbench.web.controller
 * Description:
 *
 * @Author abel
 * @CreateDate 2023-11-01 8:49
 * @Version 1.0
 */
@Controller
public class ClueRemarkController {

    @Autowired
    private ClueRemarkService clueRemarkService;

    @RequestMapping("/workbench/clue/saveCreateClueRemark.do")
    @ResponseBody
    public Object saveCreateClueRemark(ClueRemark clueRemark, HttpSession session) {

        User user = (User) session.getAttribute(Constants.SESSION_USER);

        clueRemark.setId(UUIDUtils.getUUID());
        clueRemark.setCreateTime(DateUtils.formateDateTime(new Date()));
        clueRemark.setCreateBy(user.getId());
        clueRemark.setEditFlag(Constants.REMARK_EDIT_FLAG_NO_EDITED);

        ReturnObject returnObject = new ReturnObject();
        try {
            int ret = clueRemarkService.saveCreateClueRemark(clueRemark);
            if (ret > 0) {
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_SUCCESS);
                returnObject.setRetData(clueRemark);
            } else {
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统繁忙,请稍后重试......");
            }
        } catch (Exception e) {
            e.getStackTrace();
            returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统异常,请稍后重试......");
        }
        return returnObject;
    }

    @RequestMapping("/workbench/clue/deleteClueRemarkById.do")
    @ResponseBody
    public Object deleteClueRemarkById(String id){

        ReturnObject returnObject= new ReturnObject();

        try {
            //调用service层方法，删除备注
            int ret = clueRemarkService.deleteClueRemarkById(id);

            if(ret > 0){
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_SUCCESS);
            }else{
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统忙，请稍后重试....");
            }

        }catch (Exception e){
            e.printStackTrace();
            returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统忙，请稍后重试....");
        }
        return returnObject;
    }

    @RequestMapping("/workbench/clue/saveEditClueRemark.do")
    @ResponseBody
    public  Object saveEditClueRemark(ClueRemark clueRemark, HttpSession session) {
        User user = (User) session.getAttribute(Constants.SESSION_USER);

        clueRemark.setEditTime(DateUtils.formateDateTime(new Date()));
        clueRemark.setEditBy(user.getId());
        clueRemark.setEditFlag(Constants.REMARK_EDIT_FLAG_YES_EDITED);

        ReturnObject returnObject = new ReturnObject();
        try {
            //调用service层方法，保存修改的市场活动备注
            int ret = clueRemarkService.saveEditClueRemark(clueRemark);

            if(ret > 0){
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_SUCCESS);
                returnObject.setRetData(clueRemark);
            }else{
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统忙，请稍后重试....");
            }
        }catch (Exception e){
            e.printStackTrace();
            returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统忙，请稍后重试....");
        }

        return returnObject;

    }
}
