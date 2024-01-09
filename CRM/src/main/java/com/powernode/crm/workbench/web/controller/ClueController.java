package com.powernode.crm.workbench.web.controller;

import com.powernode.crm.commons.constants.Constants;
import com.powernode.crm.commons.domain.ReturnObject;
import com.powernode.crm.commons.utils.DateUtils;
import com.powernode.crm.commons.utils.UUIDUtils;
import com.powernode.crm.settings.domain.DicValue;
import com.powernode.crm.settings.domain.User;
import com.powernode.crm.settings.service.DicValueService;
import com.powernode.crm.settings.service.UserService;
import com.powernode.crm.workbench.domain.Activity;
import com.powernode.crm.workbench.domain.Clue;
import com.powernode.crm.workbench.domain.ClueActivityRelation;
import com.powernode.crm.workbench.domain.ClueRemark;
import com.powernode.crm.workbench.service.ActivityService;
import com.powernode.crm.workbench.service.ClueActivityRelationService;
import com.powernode.crm.workbench.service.ClueRemarkService;
import com.powernode.crm.workbench.service.ClueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * ClassName:ClueController
 * Package:com.powernode.crm.workbench.web.controller
 * Description:
 *
 * @Author abel
 * @CreateDate 2023-10-30 9:26
 * @Version 1.0
 */

@Controller
public class ClueController {

    @Autowired
    private UserService userService;

    @Autowired
    private DicValueService dicValueService;

    @Autowired
    private ClueService clueService;

    @Autowired
    private ClueRemarkService clueRemarkService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private ClueActivityRelationService clueActivityRelationService;

    @RequestMapping("/workbench/clue/clueIndex.do")
    public String clueIndex(HttpServletRequest request) {
        //调用service层方法，查询动态数据
        List<User> userList = userService.queryAllUser();
        List<DicValue> appellationList = dicValueService.queryDicValueByTypeCode("appellation");
        List<DicValue> clueStateList = dicValueService.queryDicValueByTypeCode("clueState");
        List<DicValue> sourceList = dicValueService.queryDicValueByTypeCode("source");
        //把数据保存到request中
        request.setAttribute("userList", userList);
        request.setAttribute("appellationList", appellationList);
        request.setAttribute("clueStateList", clueStateList);
        request.setAttribute("sourceList", sourceList);
        return "workbench/clue/clueIndex";
    }

    @RequestMapping("/workbench/clue/saveCreateClue.do")
    @ResponseBody
    public Object saveCreateClue(Clue clue, HttpSession session) {

        User user = (User) session.getAttribute(Constants.SESSION_USER);
        clue.setId(UUIDUtils.getUUID());
        clue.setCreateTime(DateUtils.formateDateTime(new Date()));
        clue.setCreateBy(user.getId());

        ReturnObject returnObject = new ReturnObject();
        try {
            int ret = clueService.saveCreateClue(clue);
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

    @RequestMapping("/workbench/clue/queryClueByConditionForPage.do")
    @ResponseBody
    public Object queryClueByConditionForPage(String fullname, String company,
                                              String phone, String source,
                                              String owner, String mphone,
                                              String state, int pageNo, int pageSize) {
        //封装参数
        Map<String, Object> map = new HashMap<>();
        map.put("fullname", fullname);
        map.put("company", company);
        map.put("phone", phone);
        map.put("source", source);
        map.put("owner", owner);
        map.put("mphone", mphone);
        map.put("state", state);
        map.put("beginNo", (pageNo - 1) * pageSize);
        map.put("pageSize", pageSize);

        //调用service层方法，查询数据
        List<Clue> clueList = clueService.queryClueByConditionForPage(map);
        int totalRows = clueService.queryCountOfClueByCondition(map);
        Map<String, Object> retMap = new HashMap<>();
        retMap.put("clueList", clueList);
        retMap.put("totalRows", totalRows);
        return retMap;
    }

    @RequestMapping("/workbench/clue/deleteClueIds.do")
    @ResponseBody
    public Object deleteClueIds(String[] id) {
        ReturnObject returnObject = new ReturnObject();

        try {
            int ret = clueService.deleteClueByIds(id);
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

    @RequestMapping("/workbench/clue/queryClueById.do")
    @ResponseBody
    public Object queryClueById(String id) {
        Clue clue = clueService.queryClueById(id);
        return clue;
    }

    @RequestMapping("/workbench/clue/saveEditClue.do")
    @ResponseBody
    public Object saveEditClue(Clue clue, HttpSession session) {

        User user = (User) session.getAttribute(Constants.SESSION_USER);

        clue.setEditTime(DateUtils.formateDateTime(new Date()));
        clue.setEditBy(user.getId());

        ReturnObject returnObject = new ReturnObject();

        try {
            int ret = clueService.saveEditClue(clue);
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


    @RequestMapping("/workbench/clue/detailClue.do")
    public String detailClue(String id, HttpServletRequest request) {
        Clue clue = clueService.queryClueForDetailById(id);
        List<ClueRemark> clueRemarkList = clueRemarkService.queryClueRemarkForDetailByClueId(id);
        List<Activity> activityList = activityService.queryActivityForDetailByClueId(id);
        request.setAttribute("clue", clue);
        request.setAttribute("clueRemarkList", clueRemarkList);
        request.setAttribute("activityList", activityList);
        return "workbench/clue/clueDetail";
    }

    @RequestMapping("/workbench/clue/queryActivityForDetailByNameClueId.do")
    @ResponseBody
    public Object queryActivityForDetailByNameClueId(String activityName, String clueId) {
        Map<String, Object> map = new HashMap<>();
        map.put("activityName", activityName);
        map.put("clueId", clueId);
        List<Activity> activityList = activityService.queryActivityForDetailByNameClueId(map);
        return activityList;
    }

    @RequestMapping("/workbench/clue/saveBund.do")
    @ResponseBody
    public Object saveBund(String[] activityId, String clueId) {

        ClueActivityRelation clueActivityRelation = null;
        List<ClueActivityRelation> relationList = new ArrayList<>();
        for (String ai : activityId) {
            clueActivityRelation = new ClueActivityRelation();
            clueActivityRelation.setActivityId(ai);
            clueActivityRelation.setClueId(clueId);
            clueActivityRelation.setId(UUIDUtils.getUUID());
            relationList.add(clueActivityRelation);
        }
        ReturnObject returnObject = new ReturnObject();
        try {
            int ret = clueActivityRelationService.saveCreateClueActivityRelationByList(relationList);
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

    @RequestMapping("/workbench/clue/saveUnbund.do")
    @ResponseBody
    public  Object saveUnbund(ClueActivityRelation relation){

        ReturnObject returnObject = new ReturnObject();

        try {

            int ret = clueActivityRelationService.deleteClueActivityRelationByClueIdActivityId(relation);

            if(ret>0){
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

    @RequestMapping("/workbench/clue/toConvert.do")
    public String toConvert(String id,HttpServletRequest request){
        Clue clue = clueService.queryClueForDetailById(id);
        List<DicValue> stageList = dicValueService.queryDicValueByTypeCode("stage");
        request.setAttribute("clue",clue);
        request.setAttribute("stageList",stageList);
        return "workbench/clue/clueConvert";
    }

    @RequestMapping("/workbench/clue/queryActivityForConvertByNameClueId.do")
    @ResponseBody
    public Object queryActivityForConvertByNameClueId(String activityName,String clueId){
        Map<String,Object> map= new HashMap<>();
        map.put("activityName",activityName);
        map.put("clueId",clueId);
        List<Activity> activityList = activityService.queryActivityForConvertByNameClueId(map);
        return activityList;
    }


    @RequestMapping("/workbench/clue/convertClue.do")
    @ResponseBody
    public  Object convertClue(String clueId,String money,String name,String expectedDate,String stage,String activityId,String isCreateTran,HttpSession session) {
        //封装参数
        Map<String,Object> map=new HashMap<>();
        map.put("clueId",clueId);
        map.put("money",money);
        map.put("name",name);
        map.put("expectedDate",expectedDate);
        map.put("stage",stage);
        map.put("activityId",activityId);
        map.put("isCreateTran",isCreateTran);
        map.put(Constants.SESSION_USER,session.getAttribute(Constants.SESSION_USER));

        ReturnObject returnObject = new ReturnObject();
        try {
            clueService.saveConvertClue(map);
            returnObject.setCode(Constants.RETURN_OBJECT_CODE_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统异常，请稍后重试....");
        }
        return returnObject;
    }


}
