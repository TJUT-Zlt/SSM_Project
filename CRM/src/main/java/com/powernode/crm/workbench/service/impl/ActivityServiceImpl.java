package com.powernode.crm.workbench.service.impl;

import com.powernode.crm.workbench.domain.Activity;
import com.powernode.crm.workbench.mapper.ActivityMapper;
import com.powernode.crm.workbench.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * ClassName:ActivityServiceImpl
 * Package:com.powernode.crm.workbench.service.impl
 * Description:
 *
 * @Author abel
 * @CreateDate 2023-10-20 10:36
 * @Version 1.0
 */
@Service("activityService")
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    @Override
    public int saveCreateActivity(Activity activity) {
        return activityMapper.insertActivity(activity);
    }

    @Override
    public List<Activity> queryActivityByConditionForPage(Map<String, Object> map) {
        return activityMapper.selectActivityByConditionForPage(map);
    }

    @Override
    public int queryCountOfActivityByCondition(Map<String, Object> map) {
        return activityMapper.selectCountOfActivityByCondition(map);
    }

    @Override
    public int deleteActivityByIds(String[] ids) {
        return activityMapper.deleteActivityByIds(ids);
    }

    @Override
    public Activity queryActivityById(String id) {
        return activityMapper.selectActivityById(id);
    }

    @Override
    public int saveEditActivity(Activity activity) {
        return activityMapper.updateActivity(activity);
    }

    @Override
    public List<Activity> queryAllActivities() {
        return activityMapper.selectAllActivities();
    }

    @Override
    public List<Activity> queryNeedActivities(String[] ids) {
        return activityMapper.selectNeedActivities(ids);
    }

    @Override
    public int saveCreateActivityByList(List<Activity> activityList) {
        return activityMapper.insertActivityByList(activityList);
    }

    @Override
    public Activity queryActivityForDetailById(String id) {
        return activityMapper.selectActivityForDetailById(id);
    }

    @Override
    public List<Activity> queryActivityForDetailByClueId(String clueId) {
        return activityMapper.selectActivityForDetailByClueId(clueId);
    }

    @Override
    public List<Activity> queryActivityForDetailByNameClueId(Map<String, Object> map) {
        return activityMapper.selectActivityForDetailByNameClueId(map);
    }

    @Override
    public List<Activity> queryActivityForDetailByIds(String[] ids) {
        return activityMapper.selectActivityForDetailByIds(ids);
    }

    @Override
    public List<Activity> queryActivityForConvertByNameClueId(Map<String, Object> map) {
        return activityMapper.selectActivityForConvertByNameClueId(map);
    }

    @Override
    public List<Activity> queryActivityForDetailByContactsId(String contactsId) {
        return activityMapper.selectActivityForDetailByContactsId(contactsId);
    }

    @Override
    public List<Activity> queryActivityForDetailByNameContactsId(Map<String, Object> map) {
        return activityMapper.selectActivityForDetailByNameContactsId(map);
    }

    @Override
    public List<Activity> queryActivityByActivityName(String activityName) {
        return activityMapper.selectActivityByActivityName(activityName);
    }


}
