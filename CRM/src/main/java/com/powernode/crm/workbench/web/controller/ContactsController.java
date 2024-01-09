package com.powernode.crm.workbench.web.controller;

import com.powernode.crm.commons.constants.Constants;
import com.powernode.crm.commons.domain.ReturnObject;
import com.powernode.crm.commons.utils.UUIDUtils;
import com.powernode.crm.settings.domain.DicValue;
import com.powernode.crm.settings.domain.User;
import com.powernode.crm.settings.service.DicValueService;
import com.powernode.crm.settings.service.UserService;
import com.powernode.crm.workbench.domain.*;
import com.powernode.crm.workbench.mapper.ContactsMapper;
import com.powernode.crm.workbench.service.ActivityService;
import com.powernode.crm.workbench.service.ContactsActivityRelationService;
import com.powernode.crm.workbench.service.ContactsRemarkService;
import com.powernode.crm.workbench.service.ContactsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName:ContactsController
 * Package:com.powernode.crm.workbench.web.controller
 * Description:
 *
 * @Author abel
 * @CreateDate 2023-11-03 10:41
 * @Version 1.0
 */
@Controller
public class ContactsController {

    @Autowired
    private ContactsService contactsService;

    @Autowired
    private ContactsRemarkService contactsRemarkService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private ContactsActivityRelationService contactsActivityRelationService;

    @Autowired
    private DicValueService dicValueService;

    @Autowired
    private UserService userService;

    @RequestMapping("/workbench/contacts/contactsIndex.do")
    public String contactsIndex(HttpServletRequest request){
        List<User> userList = userService.queryAllUser();
        List<DicValue> appellationList = dicValueService.queryDicValueByTypeCode("appellation");
        List<DicValue> sourceList = dicValueService.queryDicValueByTypeCode("source");
        request.setAttribute("userList", userList);
        request.setAttribute("appellationList", appellationList);
        request.setAttribute("sourceList", sourceList);

        return "workbench/contacts/contactsIndex";
    }

    @RequestMapping("/workbench/contacts/queryContactsByConditionForPage.do")
    @ResponseBody
    public Object queryContactsByConditionForPage(String owner,String fullname,
                                                  String customerId,String source,String birthday,
                                                  int pageNo,int pageSize){
        //封装参数
        Map<String,Object> map = new HashMap<>();
        map.put("owner",owner);
        map.put("fullname",fullname);
        map.put("customerId",customerId);
        map.put("source",source);
        map.put("birthday",birthday);
        map.put("beginNo",(pageNo-1)*pageSize);
        map.put("pageSize",pageSize);
        //调用service层方法，查询数据
        List<Contacts> contactsList = contactsService.queryContactsByConditionForPage(map);
        int totalRows = contactsService.queryCountOfContactsByCondition(map);
        Map<String,Object> retMap = new HashMap<>();
        retMap.put("contactsList",contactsList);
        retMap.put("totalRows",totalRows);
        return retMap;
    }

    @RequestMapping("/workbench/contacts/detailContacts.do")
    public String detailContacts(String id, HttpServletRequest request) {
        Contacts contacts = contactsService.queryContactsForDetailById(id);
        List<ContactsRemark> contactsRemarkList = contactsRemarkService.queryContactsRemarkForDetailByContactsId(id);
        List<Activity> activityList = activityService.queryActivityForDetailByContactsId(id);
        request.setAttribute("contacts", contacts);
        request.setAttribute("contactsRemarkList", contactsRemarkList);
        request.setAttribute("activityList", activityList);
        return "workbench/contacts/contactsDetail";
    }
    @RequestMapping("/workbench/contacts/queryActivityForDetailByNameContactsId.do")
    @ResponseBody
    public Object queryActivityForDetailByNameContactsId(String activityName, String contactsId) {
        Map<String, Object> map = new HashMap<>();
        map.put("activityName", activityName);
        map.put("contactsId", contactsId);
        List<Activity> activityList = activityService.queryActivityForDetailByNameContactsId(map);
        return activityList;
    }

    @RequestMapping("/workbench/contacts/saveBund.do")
    @ResponseBody
    public Object saveBund(String[] activityId, String contactsId) {

        ContactsActivityRelation contactsActivityRelation = null;
        List<ContactsActivityRelation> relationList = new ArrayList<>();
        for (String ai : activityId) {
            contactsActivityRelation = new ContactsActivityRelation();
            contactsActivityRelation.setActivityId(ai);
            contactsActivityRelation.setContactsId(contactsId);
            contactsActivityRelation.setId(UUIDUtils.getUUID());
            relationList.add(contactsActivityRelation);
        }
        ReturnObject returnObject = new ReturnObject();
        try {
            int ret = contactsActivityRelationService.saveCreateContactsActivityRelationByList(relationList);
            if (ret > 0) {
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_SUCCESS);

                List<Activity> activityList = activityService.queryActivityForDetailByIds(activityId);

                returnObject.setRetData(activityList);
            } else {
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统繁忙，请稍后重试....");
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统异常，请稍后重试....");
        }
        return returnObject;
    }
    @RequestMapping("/workbench/contacts/saveUnbund.do")
    @ResponseBody
    public  Object saveUnbund(ContactsActivityRelation relation){

        ReturnObject returnObject = new ReturnObject();

        try {

            int ret = contactsActivityRelationService.deleteContactsActivityRelationByContactsIdActivityId(relation);

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
}
