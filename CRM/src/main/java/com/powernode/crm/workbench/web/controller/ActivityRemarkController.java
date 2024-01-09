package com.powernode.crm.workbench.web.controller;

import com.powernode.crm.commons.constants.Constants;
import com.powernode.crm.commons.domain.ReturnObject;
import com.powernode.crm.commons.utils.DateUtils;
import com.powernode.crm.commons.utils.UUIDUtils;
import com.powernode.crm.settings.domain.User;
import com.powernode.crm.workbench.domain.ActivityRemark;
import com.powernode.crm.workbench.service.ActivityRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * ClassName:ActivityRemarkController
 * Package:com.powernode.crm.workbench.web.controller
 * Description:
 *
 * @Author abel
 * @CreateDate 2023-10-28 9:48
 * @Version 1.0
 */

@Controller
public class ActivityRemarkController {

    @Autowired
    private ActivityRemarkService activityRemarkService;

    @RequestMapping("/workbench/activity/saveCreateActivityRemark.do")
    @ResponseBody
    public Object saveCreateActivityRemark(ActivityRemark activityRemark, HttpSession session) {

        User user = (User) session.getAttribute(Constants.SESSION_USER);

        activityRemark.setId(UUIDUtils.getUUID());
        activityRemark.setCreateTime(DateUtils.formateDateTime(new Date()));
        activityRemark.setCreateBy(user.getId());
        activityRemark.setEditFlag(Constants.REMARK_EDIT_FLAG_NO_EDITED);

        ReturnObject returnObject = new ReturnObject();
        try {
            int ret = activityRemarkService.saveCreateActivityRemark(activityRemark);
            if (ret > 0) {
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_SUCCESS);
                returnObject.setRetData(activityRemark);
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

    @RequestMapping("/workbench/activity/deleteActivityRemarkById.do")
    @ResponseBody
    public Object deleteActivityRemarkById(String id){

        ReturnObject returnObject= new ReturnObject();

        try {
            //调用service层方法，删除备注
            int ret = activityRemarkService.deleteActivityRemarkById(id);

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

    @RequestMapping("/workbench/activity/saveEditActivityRemark.do")
    @ResponseBody
    public  Object saveEditActivityRemark(ActivityRemark activityRemark,HttpSession session) {
        User user = (User) session.getAttribute(Constants.SESSION_USER);

        activityRemark.setEditTime(DateUtils.formateDateTime(new Date()));
        activityRemark.setEditBy(user.getId());
        activityRemark.setEditFlag(Constants.REMARK_EDIT_FLAG_YES_EDITED);

        ReturnObject returnObject = new ReturnObject();
        try {
            //调用service层方法，保存修改的市场活动备注
            int ret = activityRemarkService.saveEditActivityRemark(activityRemark);

            if(ret > 0){
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_SUCCESS);
                returnObject.setRetData(activityRemark);
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
