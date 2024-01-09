package com.powernode.crm.workbench.service;

import com.powernode.crm.workbench.domain.Activity;

import java.util.List;
import java.util.Map;

/**
 * ClassName:ActivityService
 * Package:com.powernode.crm.workbench.service
 * Description:
 *
 * @Author abel
 * @CreateDate 2023-10-20 10:36
 * @Version 1.0
 */
public interface ActivityService {

    int saveCreateActivity(Activity activity);

    List<Activity> queryActivityByConditionForPage(Map<String,Object> map);

    int queryCountOfActivityByCondition(Map<String,Object> map);

    int deleteActivityByIds(String[] ids);

    Activity queryActivityById(String id);

    int saveEditActivity(Activity activity);

    List<Activity> queryAllActivities();

    List<Activity> queryNeedActivities(String[] ids);

    int saveCreateActivityByList(List<Activity> activityList);

    Activity queryActivityForDetailById(String id);

    List<Activity> queryActivityForDetailByClueId(String clueId);

    List<Activity> queryActivityForDetailByNameClueId(Map<String,Object> map);

    List<Activity> queryActivityForDetailByIds(String[] ids);

    List<Activity> queryActivityForConvertByNameClueId(Map<String,Object> map);

    List<Activity> queryActivityForDetailByContactsId(String contactsId);

    List<Activity> queryActivityForDetailByNameContactsId(Map<String,Object> map);

    List<Activity> queryActivityByActivityName(String activityName);


}
