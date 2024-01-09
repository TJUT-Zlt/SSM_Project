package com.powernode.crm.workbench.mapper.test;

import com.powernode.crm.workbench.domain.ActivityRemark;
import com.powernode.crm.workbench.mapper.ActivityRemarkMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * ClassName:ActivityRemarkMapperTest
 * Package:com.powernode.crm.workbench.mapper.test
 * Description:
 *
 * @Author abel
 * @CreateDate 2023-10-25 11:26
 * @Version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext_service.xml")
public class ActivityRemarkMapperTest {
    @Autowired
    private ActivityRemarkMapper activityRemarkMapper;

    @Test
    public void testSelectActivityRemarkForDetailByActivityId(){
        List<ActivityRemark> activityRemarks = activityRemarkMapper.selectActivityRemarkForDetailByActivityId("765de599b098410e9ba19f873af7e663");
        activityRemarks.forEach(activityRemark -> System.out.println(activityRemark));
    }
    @Test
    public void testUpdateActivityRemark(){
        ActivityRemark activityRemark = new ActivityRemark();
        activityRemark.setId("eef42d96bc6c4a70a3220446e756ff18");
        activityRemark.setEditFlag("1");
        int i = activityRemarkMapper.updateActivityRemark(activityRemark);
        System.out.println(i);
    }

}
