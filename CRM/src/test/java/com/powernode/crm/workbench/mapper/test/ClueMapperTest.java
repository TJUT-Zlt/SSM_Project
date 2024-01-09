package com.powernode.crm.workbench.mapper.test;

import com.powernode.crm.commons.utils.DateUtils;
import com.powernode.crm.workbench.domain.Clue;
import com.powernode.crm.workbench.mapper.ClueMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName:ClueMapperTest
 * Package:com.powernode.crm.workbench.mapper.test
 * Description:
 *
 * @Author abel
 * @CreateDate 2023-10-30 11:54
 * @Version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext_service.xml"})
public class ClueMapperTest {

    @Autowired
    private ClueMapper clueMapper;

    @Test
    public void testInsertClue(){
        Clue clue = new Clue();
        clue.setId("ssss");
        clue.setFullname("asdfg");
        int i = clueMapper.insertClue(clue);
        System.out.println(i);
    }
    @Test
    public void testSelectClueByConditionForPage(){
        Map map = new HashMap();
        map.put("beginNo",0);
        map.put("pageSize",3);
        map.put("phone","123456");
        map.put("fullname","老杜");
        List clueList = clueMapper.selectClueByConditionForPage(map);
        clueList.forEach(clue->{
            System.out.println(clue);
        });
    }
    @Test
    public void testSelectCountOfClueByCondition(){
        Map map = new HashMap();
        int i = clueMapper.selectCountOfClueByCondition(map);
        System.out.println(i);
    }

    @Test
    public void testDeleteCLueByIds(){
        String[] ids = {"2e334f73d9f74a0e8a13ec9a29002ba8"};
        int i = clueMapper.deleteCLueByIds(ids);
        System.out.println(i);
    }

    @Test
    public void testSelectClueById(){
        Clue clue = clueMapper.selectClueById("8ad2f420bce54bda939454ad9e053262");
        System.out.println(clue);
    }
    @Test
    public void testUpdateClue(){
        Clue clue = new Clue();
        clue.setId("8ad2f420bce54bda939454ad9e053262");
        clue.setEditTime(DateUtils.formateDateTime(new Date()));
        clue.setAppellation("59795c49896947e1ab61b7312bd0597c");
        int i = clueMapper.updateClue(clue);
        System.out.println(i);
    }
    @Test
    public void testSelectClueForDetailById(){
        Clue clue = clueMapper.selectClueForDetailById("7cc247628a004dc68ceeae770be17b33");
        System.out.println(clue);
    }
}
