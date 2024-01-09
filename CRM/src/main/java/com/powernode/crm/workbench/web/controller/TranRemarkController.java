package com.powernode.crm.workbench.web.controller;

import com.powernode.crm.commons.constants.Constants;
import com.powernode.crm.commons.domain.ReturnObject;
import com.powernode.crm.commons.utils.DateUtils;
import com.powernode.crm.commons.utils.UUIDUtils;
import com.powernode.crm.settings.domain.User;
import com.powernode.crm.workbench.domain.ActivityRemark;
import com.powernode.crm.workbench.domain.TranRemark;
import com.powernode.crm.workbench.service.TranRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * ClassName:TranRemarkController
 * Package:com.powernode.crm.workbench.web.controller
 * Description:
 *
 * @Author abel
 * @CreateDate 2023-11-06 21:02
 * @Version 1.0
 */
@Controller
public class TranRemarkController {

    @Autowired
    private TranRemarkService tranRemarkService;


    @RequestMapping("/workbench/transaction/saveCreateTranRemark.do")
    @ResponseBody
    public Object saveCreateTranRemark(TranRemark tranRemark, HttpSession session) {

        User user = (User) session.getAttribute(Constants.SESSION_USER);

        tranRemark.setId(UUIDUtils.getUUID());
        tranRemark.setCreateTime(DateUtils.formateDateTime(new Date()));
        tranRemark.setCreateBy(user.getId());
        tranRemark.setEditFlag(Constants.REMARK_EDIT_FLAG_NO_EDITED);

        ReturnObject returnObject = new ReturnObject();
        try {
            int ret = tranRemarkService.saveCreateTranRemark(tranRemark);
            if (ret > 0) {
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_SUCCESS);
                returnObject.setRetData(tranRemark);
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

    @RequestMapping("/workbench/transaction/deleteTranRemarkById.do")
    @ResponseBody
    public Object deleteTranRemarkById(String id){

        ReturnObject returnObject= new ReturnObject();

        try {
            int ret = tranRemarkService.deleteTranRemarkById(id);
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
    @RequestMapping("/workbench/transaction/saveEditTranRemark.do")
    @ResponseBody
    public  Object saveEditTranRemark(TranRemark tranRemark,HttpSession session) {
        User user = (User) session.getAttribute(Constants.SESSION_USER);

        tranRemark.setEditTime(DateUtils.formateDateTime(new Date()));
        tranRemark.setEditBy(user.getId());
        tranRemark.setEditFlag(Constants.REMARK_EDIT_FLAG_YES_EDITED);

        ReturnObject returnObject = new ReturnObject();
        try {
            //调用service层方法，保存修改的市场活动备注
            int ret = tranRemarkService.saveEditTranRemark(tranRemark);

            if(ret > 0){
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_SUCCESS);
                returnObject.setRetData(tranRemark);
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
