package com.powernode.crm.workbench.service;

import com.powernode.crm.workbench.domain.ActivityRemark;

import java.util.List;

/**
 * ClassName:ActivityRemarkService
 * Package:com.powernode.crm.workbench.service
 * Description:
 *
 * @Author abel
 * @CreateDate 2023-10-28 9:45
 * @Version 1.0
 */
public interface ActivityRemarkService {

    List<ActivityRemark> queryActivityRemarkForDetailByActivityId(String activityId);

    int saveCreateActivityRemark(ActivityRemark activityRemark);

    int deleteActivityRemarkById(String id);

    int saveEditActivityRemark(ActivityRemark activityRemark);

}
