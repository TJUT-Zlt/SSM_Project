package com.powernode.crm.workbench.mapper.test;

import com.powernode.crm.commons.utils.DateUtils;
import com.powernode.crm.commons.utils.UUIDUtils;
import com.powernode.crm.workbench.domain.ClueRemark;
import com.powernode.crm.workbench.mapper.ClueRemarkMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

/**
 * ClassName:ClueRemarkMapperTest
 * Package:com.powernode.crm.workbench.mapper.test
 * Description:
 *
 * @Author abel
 * @CreateDate 2023-10-31 17:44
 * @Version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext_service.xml")
public class ClueRemarkMapperTest {

    @Autowired
    private ClueRemarkMapper clueRemarkMapper;

    @Test
    public void testSelectClueRemarkForDetailByClueId(){
        List<ClueRemark> clueRemarks = clueRemarkMapper.selectClueRemarkForDetailByClueId("7cc247628a004dc68ceeae770be17b33");
        clueRemarks.forEach(clueRemark -> System.out.println(clueRemark));
    }

    @Test
    public void testInsertClueRemark(){
        ClueRemark clueRemark = new ClueRemark();
        clueRemark.setId(UUIDUtils.getUUID());
        clueRemark.setNoteContent("今天是11月的第一天");
        int i = clueRemarkMapper.insertClueRemark(clueRemark);
        System.out.println(i);
    }
    @Test
    public void testDeleteClueRemark(){
        int i = clueRemarkMapper.deleteClueRemark("1eb747820cee43b9936a11acf578f596");
        System.out.println(i);
    }

    @Test
    public void testUpdateClueRemark(){
        ClueRemark clueRemark = new ClueRemark();
        clueRemark.setId("1eb747820cee43b9936a11acf578f596");
        clueRemark.setEditTime(DateUtils.formateDateTime(new Date()));
        clueRemark.setNoteContent("2023-11-07");
        int i = clueRemarkMapper.updateClueRemark(clueRemark);
        System.out.println(i);
    }

}
