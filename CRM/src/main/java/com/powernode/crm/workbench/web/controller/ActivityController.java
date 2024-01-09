package com.powernode.crm.workbench.web.controller;

import com.powernode.crm.commons.constants.Constants;
import com.powernode.crm.commons.domain.ReturnObject;
import com.powernode.crm.commons.utils.DateUtils;
import com.powernode.crm.commons.utils.HSSFUtils;
import com.powernode.crm.commons.utils.UUIDUtils;
import com.powernode.crm.settings.domain.User;
import com.powernode.crm.settings.service.UserService;
import com.powernode.crm.workbench.domain.Activity;
import com.powernode.crm.workbench.domain.ActivityRemark;
import com.powernode.crm.workbench.service.ActivityRemarkService;
import com.powernode.crm.workbench.service.ActivityService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

/**
 * ClassName:ActivityController
 * Package:com.powernode.crm.workbench.web.controller
 * Description:
 *
 * @Author abel
 * @CreateDate 2023-10-19 21:52
 * @Version 1.0
 */
@Controller
public class ActivityController {

    @Autowired
    private UserService userService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private ActivityRemarkService activityRemarkService;

    @RequestMapping("workbench/activity/activityIndex.do")
    public String activityIndex(HttpServletRequest request){
        //调用service层方法，查询所有的用户
        List<User> userList = userService.queryAllUser();
        //把数据保存到request中
        request.setAttribute("userList",userList);
        //请求转发到市场活动的主页面
        return "workbench/activity/ActivityIndex";
    }

    @RequestMapping("workbench/activity/saveCreateActivity.do")
    @ResponseBody
    public Object saveCreateActivity(Activity activity, HttpSession session){

        User user = (User) session.getAttribute(Constants.SESSION_USER);
        //封装参数
        activity.setId(UUIDUtils.getUUID());
        activity.setCreateTime(DateUtils.formateDateTime(new Date()));
        activity.setCreateBy(user.getId());

        ReturnObject returnObject = new ReturnObject();

        try {
            //调用service层方法，保存创建的市场活动
            int ret = activityService.saveCreateActivity(activity);
            if(ret > 0){
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_SUCCESS);
            }else {
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统繁忙,请稍后重试......");
            }
        }catch (Exception e) {
            e.getStackTrace();

            returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统异常,请稍后重试......");
        }

        return returnObject;
    }

    @RequestMapping("/workbench/activity/queryActivityByConditionForPage.do")
    @ResponseBody
    public Object queryActivityByConditionForPage(String name,String owner,
                                                  String startDate,String endDate,
                                                  int pageNo,int pageSize){
        //封装参数
        Map<String,Object> map = new HashMap<>();
        map.put("name",name);
        map.put("owner",owner);
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        map.put("beginNo",(pageNo-1)*pageSize);
        map.put("pageSize",pageSize);
        //调用service层方法，查询数据
        List<Activity> activityList = activityService.queryActivityByConditionForPage(map);
        int totalRows = activityService.queryCountOfActivityByCondition(map);
        Map<String,Object> retMap = new HashMap<>();
        retMap.put("activityList",activityList);
        retMap.put("totalRows",totalRows);
        return retMap;
    }

    @RequestMapping("/workbench/activity/deleteActivityIds.do")
    @ResponseBody
    public Object deleteActivityIds(String[] id){
        ReturnObject returnObject = new ReturnObject();

        try{
            //调用service层方法，删除市场活动
            int ret = activityService.deleteActivityByIds(id);
            if(ret > 0){
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_SUCCESS);
            }else {
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统繁忙,请稍后重试......");
            }
        }catch (Exception e){
            e.getStackTrace();
            returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统异常,请稍后重试......");
        }
        return returnObject;
    }

    @RequestMapping("/workbench/activity/queryActivityById.do")
    @ResponseBody
    public Object queryActivityById(String id){
        Activity activity = activityService.queryActivityById(id);
        return activity;
    }

    @RequestMapping("/workbench/activity/saveEditActivity.do")
    @ResponseBody
    public Object saveEditActivity(Activity activity,HttpSession session){

        User user =(User)session.getAttribute(Constants.SESSION_USER);

        activity.setEditTime(DateUtils.formateDateTime(new Date()));
        activity.setEditBy(user.getId());

        ReturnObject returnObject = new ReturnObject();

        try{
            int ret = activityService.saveEditActivity(activity);
            if(ret > 0){
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_SUCCESS);
            }else {
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统繁忙,请稍后重试......");
            }
        }catch (Exception e){
            e.getStackTrace();
            returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统异常,请稍后重试......");
        }
        return returnObject;
    }

    @RequestMapping("/workbench/activity/exportAllActivities.do")
    public void exportAllActivities(HttpServletResponse response) throws IOException {

        List<Activity> activityList = activityService.queryAllActivities();

        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("市场活动列表");

        HSSFRow row = sheet.createRow(0);

        HSSFCell cell = row.createCell(0);
        cell.setCellValue("ID");
        cell=row.createCell(1);
        cell.setCellValue("所有者");
        cell=row.createCell(2);
        cell.setCellValue("名称");
        cell=row.createCell(3);
        cell.setCellValue("开始日期");
        cell=row.createCell(4);
        cell.setCellValue("结束日期");
        cell=row.createCell(5);
        cell.setCellValue("成本");
        cell=row.createCell(6);
        cell.setCellValue("描述");
        cell=row.createCell(7);
        cell.setCellValue("创建时间");
        cell=row.createCell(8);
        cell.setCellValue("创建者");
        cell=row.createCell(9);
        cell.setCellValue("修改时间");
        cell=row.createCell(10);
        cell.setCellValue("修改者");

        if(activityList !=null && activityList.size() > 0){
            Activity activity = null;
            for(int i = 0;i<activityList.size();i++){
                activity =activityList.get(i);
                //每遍历出一个activity，生成一行
                row=sheet.createRow(i+1);
                //每一行创建11列，每一列的数据从activity中获取
                cell=row.createCell(0);
                cell.setCellValue(activity.getId());
                cell=row.createCell(1);
                cell.setCellValue(activity.getOwner());
                cell=row.createCell(2);
                cell.setCellValue(activity.getName());
                cell=row.createCell(3);
                cell.setCellValue(activity.getStartDate());
                cell=row.createCell(4);
                cell.setCellValue(activity.getEndDate());
                cell=row.createCell(5);
                cell.setCellValue(activity.getCost());
                cell=row.createCell(6);
                cell.setCellValue(activity.getDescription());
                cell=row.createCell(7);
                cell.setCellValue(activity.getCreateTime());
                cell=row.createCell(8);
                cell.setCellValue(activity.getCreateBy());
                cell=row.createCell(9);
                cell.setCellValue(activity.getEditTime());
                cell=row.createCell(10);
                cell.setCellValue(activity.getEditBy());
            }
        }
        response.setContentType("application/octet-stream,charset=UTF-8");
        response.addHeader("Content-Disposition","attachment;filename=activityList.xls");
        //简化版
        OutputStream out = response.getOutputStream();
        wb.write(out);
        wb.close();
        out.flush();
    }


    @RequestMapping("/workbench/activity/exportNeedActivities.do")
    public void exportNeedActivities(String[] id,HttpServletRequest request,HttpServletResponse response) throws IOException {

        List<Activity> activityList = activityService.queryNeedActivities(id);

        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("市场活动列表");

        HSSFRow row = sheet.createRow(0);

        HSSFCell cell = row.createCell(0);
        cell.setCellValue("ID");
        cell=row.createCell(1);
        cell.setCellValue("所有者");
        cell=row.createCell(2);
        cell.setCellValue("名称");
        cell=row.createCell(3);
        cell.setCellValue("开始日期");
        cell=row.createCell(4);
        cell.setCellValue("结束日期");
        cell=row.createCell(5);
        cell.setCellValue("成本");
        cell=row.createCell(6);
        cell.setCellValue("描述");
        cell=row.createCell(7);
        cell.setCellValue("创建时间");
        cell=row.createCell(8);
        cell.setCellValue("创建者");
        cell=row.createCell(9);
        cell.setCellValue("修改时间");
        cell=row.createCell(10);
        cell.setCellValue("修改者");

        if(activityList !=null && activityList.size() > 0){
            Activity activity = null;
            for(int i = 0;i<activityList.size();i++){
                activity =activityList.get(i);
                //每遍历出一个activity，生成一行
                row=sheet.createRow(i+1);
                //每一行创建11列，每一列的数据从activity中获取
                cell=row.createCell(0);
                cell.setCellValue(activity.getId());
                cell=row.createCell(1);
                cell.setCellValue(activity.getOwner());
                cell=row.createCell(2);
                cell.setCellValue(activity.getName());
                cell=row.createCell(3);
                cell.setCellValue(activity.getStartDate());
                cell=row.createCell(4);
                cell.setCellValue(activity.getEndDate());
                cell=row.createCell(5);
                cell.setCellValue(activity.getCost());
                cell=row.createCell(6);
                cell.setCellValue(activity.getDescription());
                cell=row.createCell(7);
                cell.setCellValue(activity.getCreateTime());
                cell=row.createCell(8);
                cell.setCellValue(activity.getCreateBy());
                cell=row.createCell(9);
                cell.setCellValue(activity.getEditTime());
                cell=row.createCell(10);
                cell.setCellValue(activity.getEditBy());
            }
        }
        response.setContentType("application/octet-stream,charset=UTF-8");
        response.addHeader("Content-Disposition","attachment;filename=activityListNeed.xls");
        //简化版
        OutputStream out = response.getOutputStream();
        wb.write(out);
        wb.close();
        out.flush();

    }


    @RequestMapping("/workbench/activity/importActivity.do")
    @ResponseBody
    public Object importActivity(MultipartFile activityFile, HttpSession session){
        User user = (User)session.getAttribute(Constants.SESSION_USER);
        ReturnObject returnObject = new ReturnObject();
        try {

            InputStream is = activityFile.getInputStream();
            HSSFWorkbook wb = new HSSFWorkbook(is);

            HSSFSheet sheet = wb.getSheetAt(0);
            HSSFRow row = null;
            HSSFCell cell = null;
            Activity activity = null;

            List<Activity> activityList = new ArrayList<>();
            for(int i = 1;i <= sheet.getLastRowNum();i++){
                row = sheet.getRow(i);

                activity = new Activity();
                activity.setId(UUIDUtils.getUUID());
                activity.setOwner(user.getId());
                activity.setCreateTime(DateUtils.formateDateTime(new Date()));
                activity.setCreateBy(user.getId());

                for(int j = 0;j < row.getLastCellNum();j++){
                    cell = row.getCell(j);
                    String cellValue = HSSFUtils.getCellValueForStr(cell);
                    if(j== 0){
                        activity.setName(cellValue);
                    }else if(j == 1){
                        activity.setStartDate(cellValue);
                    }else if(j == 2){
                        activity.setEndDate(cellValue);
                    }else if(j == 3){
                        activity.setCost(cellValue);
                    }else if(j == 4) {
                        activity.setDescription(cellValue);
                    }
                }
                activityList.add(activity);
            }
            int ret = activityService.saveCreateActivityByList(activityList);
            returnObject.setCode(Constants.RETURN_OBJECT_CODE_SUCCESS);
            returnObject.setRetData(ret);

        }catch (Exception e){
            e.getStackTrace();
            returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统异常,请稍后重试......");
        }
        return returnObject;
    }

    /**
     * 市场活动详情
     * @param id
     * @param request
     * @return
     */
    @RequestMapping("/workbench/activity/detailActivity.do")
    public String detailActivity(String id ,HttpServletRequest request){

        Activity activity = activityService.queryActivityForDetailById(id);
        List<ActivityRemark> activityRemarkList = activityRemarkService.queryActivityRemarkForDetailByActivityId(id);

        request.setAttribute("activity",activity);
        request.setAttribute("activityRemarkList",activityRemarkList);

        return "workbench/activity/ActivityDetail";

    }

}

