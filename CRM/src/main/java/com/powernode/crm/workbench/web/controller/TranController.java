package com.powernode.crm.workbench.web.controller;

import com.powernode.crm.commons.constants.Constants;
import com.powernode.crm.commons.domain.ReturnObject;
import com.powernode.crm.commons.utils.DateUtils;
import com.powernode.crm.settings.domain.DicValue;
import com.powernode.crm.settings.domain.User;
import com.powernode.crm.settings.service.DicValueService;
import com.powernode.crm.settings.service.UserService;
import com.powernode.crm.workbench.domain.*;
import com.powernode.crm.workbench.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * ClassName:TranController
 * Package:com.powernode.crm.workbench.web.controller
 * Description:
 *
 * @Author abel
 * @CreateDate 2023-11-03 10:41
 * @Version 1.0
 */
@Controller
public class TranController {

    @Autowired
    private DicValueService dicValueService;

    @Autowired
    private UserService userService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private TranService tranService;

    @Autowired
    private TranRemarkService tranRemarkService;

    @Autowired
    private TranHistoryService tranHistoryService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private ContactsService contactsService;

    @RequestMapping("/workbench/transaction/transactionIndex.do")
    public String transactionIndex(HttpServletRequest request){

        List<DicValue> stageList=dicValueService.queryDicValueByTypeCode("stage");
        List<DicValue> transactionTypeList=dicValueService.queryDicValueByTypeCode("transactionType");
        List<DicValue> sourceList=dicValueService.queryDicValueByTypeCode("source");
        //把数据保存到request
        request.setAttribute("stageList",stageList);
        request.setAttribute("transactionTypeList",transactionTypeList);
        request.setAttribute("sourceList",sourceList);

        return "workbench/transaction/transactionIndex";
    }

    @RequestMapping("/workbench/transaction/toSave.do")
    public String toSave(HttpServletRequest request){
        //调用service层方法，查询动态数据
        List<User> userList=userService.queryAllUser();
        List<DicValue> stageList=dicValueService.queryDicValueByTypeCode("stage");
        List<DicValue> transactionTypeList=dicValueService.queryDicValueByTypeCode("transactionType");
        List<DicValue> sourceList=dicValueService.queryDicValueByTypeCode("source");
        //把数据保存到request中
        request.setAttribute("userList",userList);
        request.setAttribute("stageList",stageList);
        request.setAttribute("transactionTypeList",transactionTypeList);
        request.setAttribute("sourceList",sourceList);
        //请求转发
        return "workbench/transaction/transactionSave";
    }


    @RequestMapping("/workbench/transaction/getPossibilityByStage.do")
    @ResponseBody
    public Object getPossibilityByStage(String stageValue){
        //解析properties配置文件，根据阶段获取可能性
        ResourceBundle bundle = ResourceBundle.getBundle("possibility");
//        for (String key:bundle.keySet()){
//            String value = bundle.getString(key);
//            System.out.println(key+":"+value);
//        }
        String possibility = bundle.getString(stageValue);
        //返回响应信息
        return possibility;
    }

    @RequestMapping("/workbench/transaction/queryCustomerNameByName.do")
    @ResponseBody
    public Object queryCustomerNameByName(String customerName){

        List<String> customerNameList = customerService.queryCustomerNameByName(customerName);

        return customerNameList;
    }

    @RequestMapping("/workbench/transaction/queryActivityByActivityName.do")
    @ResponseBody
    public Object queryActivityByActivityName(String activityName){

        List<Activity> activityList = activityService.queryActivityByActivityName(activityName);

        return activityList;
    }
    @RequestMapping("/workbench/transaction/queryContactsByContactsName.do")
    @ResponseBody
    public Object queryContactsByContactsName(String contactsName){

        List<Contacts> contactsList = contactsService.queryContactsByContactsName(contactsName);

        return contactsList;
    }

    @RequestMapping("/workbench/transaction/saveCreateTran.do")
    @ResponseBody
    public  Object saveCreateTran(@RequestParam Map<String,Object> map, HttpSession session) {
        map.put(Constants.SESSION_USER,session.getAttribute(Constants.SESSION_USER));

        ReturnObject returnObject= new ReturnObject();
        try{
            tranService.saveCreateTran(map);
            returnObject.setCode(Constants.RETURN_OBJECT_CODE_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统异常，请稍后重试....");
        }
        return returnObject;
    }

    @RequestMapping("/workbench/transaction/queryTranByConditionForPage.do")
    @ResponseBody
    public Object queryTranByConditionForPage(int pageNo, int pageSize) {
        //封装参数
        Map<String, Object> map = new HashMap<>();

        map.put("beginNo", (pageNo - 1) * pageSize);
        map.put("pageSize", pageSize);

        //调用service层方法，查询数据
        List<Tran> tranList = tranService.queryTranByConditionForPage(map);
        int totalRows = tranService.queryCountOfTranByCondition(map);
        Map<String, Object> retMap = new HashMap<>();
        retMap.put("tranList", tranList);
        retMap.put("totalRows", totalRows);
        return retMap;
    }

    @RequestMapping("/workbench/transaction/deleteTranIds.do")
    @ResponseBody
    public Object deleteTranIds(String[] id) {
        ReturnObject returnObject = new ReturnObject();

        try {
            int ret = tranService.deleteTranByIds(id);
            if (ret > 0) {
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_SUCCESS);
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

    @RequestMapping("/workbench/transaction/queryTranById.do")
    public String queryTranById(String id,HttpServletRequest request) {
        Tran tran = tranService.queryTranById(id);

        List<User> userList=userService.queryAllUser();
        List<DicValue> stageList=dicValueService.queryDicValueByTypeCode("stage");
        List<DicValue> transactionTypeList=dicValueService.queryDicValueByTypeCode("transactionType");
        List<DicValue> sourceList=dicValueService.queryDicValueByTypeCode("source");
        //把数据保存到request中
        request.setAttribute("tran",tran);
        request.setAttribute("userList",userList);
        request.setAttribute("stageList",stageList);
        request.setAttribute("transactionTypeList",transactionTypeList);
        request.setAttribute("sourceList",sourceList);
        return "workbench/transaction/transactionEdit";
    }

    @RequestMapping("/workbench/transaction/saveEditTran.do")
    @ResponseBody
    public Object saveEditTran(@RequestParam Map<String,Object> map, HttpSession session) {
        map.put(Constants.SESSION_USER,session.getAttribute(Constants.SESSION_USER));

        ReturnObject returnObject= new ReturnObject();
        try{
            tranService.saveEditTran(map);
            returnObject.setCode(Constants.RETURN_OBJECT_CODE_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统异常，请稍后重试....");
        }
        return returnObject;

    }

    @RequestMapping("/workbench/transaction/detailTran.do")
    public String detailTran(String id,HttpServletRequest request){
        Tran tran = tranService.queryTranForDetailById(id);
        List<TranRemark> tranRemarkList = tranRemarkService.queryTranRemarkForDetailByTranId(id);
        List<TranHistory> tranHistoryList = tranHistoryService.queryTranHistoryForDetailByTranId(id);

        ResourceBundle bundle = ResourceBundle.getBundle("possibility");
        String possibility = bundle.getString(tran.getStage());
        tran.setPossibility(possibility);

        request.setAttribute("tran",tran);
        request.setAttribute("tranRemarkList",tranRemarkList);
        request.setAttribute("tranHistoryList",tranHistoryList);

        List<DicValue> stageList = dicValueService.queryDicValueByTypeCode("stage");
        request.setAttribute("stageList",stageList);

        return "workbench/transaction/transactionDetail";
    }



}

