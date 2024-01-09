package com.powernode.crm.workbench.web.controller;

import com.powernode.crm.commons.constants.Constants;
import com.powernode.crm.commons.domain.ReturnObject;
import com.powernode.crm.commons.utils.DateUtils;
import com.powernode.crm.commons.utils.UUIDUtils;
import com.powernode.crm.settings.domain.User;
import com.powernode.crm.workbench.domain.ContactsRemark;
import com.powernode.crm.workbench.domain.TranRemark;
import com.powernode.crm.workbench.service.ContactsRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * ClassName:ContactsRemarkController
 * Package:com.powernode.crm.workbench.web.controller
 * Description:
 *
 * @Author abel
 * @CreateDate 2023-11-08 22:19
 * @Version 1.0
 */
@Controller
public class ContactsRemarkController {

    @Autowired
    private ContactsRemarkService contactsRemarkService;

    @RequestMapping("/workbench/contacts/saveCreateContactsRemark.do")
    @ResponseBody
    public Object saveCreateContactsRemark(ContactsRemark contactsRemark, HttpSession session) {

        User user = (User) session.getAttribute(Constants.SESSION_USER);

        contactsRemark.setId(UUIDUtils.getUUID());
        contactsRemark.setCreateTime(DateUtils.formateDateTime(new Date()));
        contactsRemark.setCreateBy(user.getId());
        contactsRemark.setEditFlag(Constants.REMARK_EDIT_FLAG_NO_EDITED);

        ReturnObject returnObject = new ReturnObject();
        try {
            int ret = contactsRemarkService.saveCreateContactsRemark(contactsRemark);
            if (ret > 0) {
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_SUCCESS);
                returnObject.setRetData(contactsRemark);
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

    @RequestMapping("/workbench/contacts/deleteContactsRemarkById.do")
    @ResponseBody
    public Object deleteContactsRemarkById(String id){

        ReturnObject returnObject= new ReturnObject();

        try {
            int ret = contactsRemarkService.deleteContactsRemarkById(id);
            if(ret > 0){
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_SUCCESS);
            }else{
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统繁忙，请稍后重试....");
            }

        }catch (Exception e){
            e.printStackTrace();
            returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统异常，请稍后重试....");
        }
        return returnObject;
    }
    @RequestMapping("/workbench/contacts/saveEditContactsRemark.do")
    @ResponseBody
    public  Object saveEditContactsRemark(ContactsRemark contactsRemark, HttpSession session) {
        User user = (User) session.getAttribute(Constants.SESSION_USER);

        contactsRemark.setEditTime(DateUtils.formateDateTime(new Date()));
        contactsRemark.setEditBy(user.getId());
        contactsRemark.setEditFlag(Constants.REMARK_EDIT_FLAG_YES_EDITED);

        ReturnObject returnObject = new ReturnObject();
        try {
            //调用service层方法，保存修改的市场活动备注
            int ret = contactsRemarkService.saveEditContactsRemark(contactsRemark);

            if(ret > 0){
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_SUCCESS);
                returnObject.setRetData(contactsRemark);
            }else{
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统繁忙，请稍后重试....");
            }
        }catch (Exception e){
            e.printStackTrace();
            returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统异常，请稍后重试....");
        }

        return returnObject;

    }
}
