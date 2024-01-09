package com.powernode.crm.workbench.mapper.test;

import com.powernode.crm.workbench.domain.Activity;
import com.powernode.crm.workbench.mapper.ActivityMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

/**
 * ClassName:ActivityMapperTest
 * Package:com.powernode.crm.workbench.mapper.test
 * Description:
 *
 * @Author abel
 * @CreateDate 2023-10-20 10:35
 * @Version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext_service.xml"})
public class ActivityMapperTest {

    @Autowired
    private ActivityMapper activityMapper;

    @Test
    public void testSelectActivityForDetailById(){
        Activity activity = activityMapper.selectActivityForDetailById("765de599b098410e9ba19f873af7e663");
        System.out.println(activity);
    }
    @Test
    public void testUpdateActivity(){
        Activity activity = new Activity();

        activity.setId("765de599b098410e9ba19f873af7e663");
        activity.setStartDate("2023-10-01");
        int i = activityMapper.updateActivity(activity);
        System.out.println(i);
    }
    @Test
    public void testSelectActivityForDetailByNameClueId(){
        Map map = new HashMap();
        map.put("activityName","test05");
        map.put("clueId","7cc247628a004dc68ceeae770be17b33");
        List activityList = activityMapper.selectActivityForDetailByNameClueId(map);
        activityList.forEach(activity->{
            System.out.println(activity);
        });
    }

    @Test
    public void testSelectActivityForDetailByIds(){
        String[] ids = {"765de599b098410e9ba19f873af7e663","4e5db2b14f3949b49e5d3efe2b418440"};
        List<Activity> activityList = activityMapper.selectActivityForDetailByIds(ids);
        activityList.forEach(activity -> System.out.println(activity));
    }

    @Test
    public void testSelectActivityForDetailByContactsId(){
        List<Activity> activityList = activityMapper.selectActivityForDetailByContactsId("db75156443fd45b5a2df92a454d6a2bd");
        activityList.forEach(activity -> {
            System.out.println(activity);
        });
    }
    @Test
    public void testSelectActivityForDetailByNameContactsId(){
        Map map = new HashMap();
        map.put("activityName","0");
        map.put("contactsId","db75156443fd45b5a2df92a454d6a2bd");
        List<Activity> activityList = activityMapper.selectActivityForDetailByNameContactsId(map);
        activityList.forEach(activity -> {
            System.out.println(activity);
        });
    }
    @Test
    public void testSelectActivityByActivityName(){
        List<Activity> activityList = activityMapper.selectActivityByActivityName("0");
        activityList.forEach(activity -> {
            System.out.println(activity);
        });
    }


}
