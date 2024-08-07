package com.powernode.crm.workbench.service.impl;

import com.powernode.crm.workbench.domain.ActivityRemark;
import com.powernode.crm.workbench.mapper.ActivityRemarkMapper;
import com.powernode.crm.workbench.service.ActivityRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName:ActivityRemarkServiceImpl
 * Package:com.powernode.crm.workbench.service.impl
 * Description:
 *
 * @Author abel
 * @CreateDate 2023-10-28 9:45
 * @Version 1.0
 */
@Service
public class ActivityRemarkServiceImpl implements ActivityRemarkService {

    @Autowired
    private ActivityRemarkMapper activityRemarkMapper;

    @Override
    public List<ActivityRemark> queryActivityRemarkForDetailByActivityId(String activityId) {
        return activityRemarkMapper.selectActivityRemarkForDetailByActivityId(activityId);
    }

    @Override
    public int saveCreateActivityRemark(ActivityRemark activityRemark) {
        return activityRemarkMapper.insertActivityRemark(activityRemark);
    }

    @Override
    public int deleteActivityRemarkById(String id) {
        return activityRemarkMapper.deleteActivityRemark(id);
    }

    @Override
    public int saveEditActivityRemark(ActivityRemark activityRemark) {
        return activityRemarkMapper.updateActivityRemark(activityRemark);
    }
}
