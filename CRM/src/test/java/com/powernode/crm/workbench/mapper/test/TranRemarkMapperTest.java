package com.powernode.crm.workbench.mapper.test;

import com.powernode.crm.commons.utils.UUIDUtils;
import com.powernode.crm.workbench.domain.TranRemark;
import com.powernode.crm.workbench.mapper.TranRemarkMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:TranRemarkMapperTest
 * Package:com.powernode.crm.workbench.mapper.test
 * Description:
 *
 * @Author abel
 * @CreateDate 2023-11-05 21:39
 * @Version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext_service.xml")
public class TranRemarkMapperTest {
    @Autowired
    private TranRemarkMapper tranRemarkMapper;
    @Test
    public void testInsertTranRemarkByList(){
        List<TranRemark> tranRemarkList = new ArrayList<>();
        TranRemark tranRemark = new TranRemark();
        tranRemark.setId(UUIDUtils.getUUID());
        tranRemarkList.add(tranRemark);
        int i = tranRemarkMapper.insertTranRemarkByList(tranRemarkList);
        System.out.println(i);
    }
    @Test
    public void testSelectTranForDetailById(){
        List<TranRemark> tranRemarkList = tranRemarkMapper.selectTranRemarkForDetailByTranId("975fca7a60c64269a3b04c90bd06cd6c");
        tranRemarkList.forEach(tranRemark -> {
            System.out.println(tranRemark);
        });
    }
    @Test
    public void testInsertTranRemark(){
        TranRemark tranRemark = new TranRemark();
        tranRemark.setId(UUIDUtils.getUUID());
        tranRemarkMapper.insertTranRemark(tranRemark);
    }
    @Test
    public void testDeleteTranRemark(){
        tranRemarkMapper.deleteTranRemark("1077c84b35a04383ac5c3828b3020320");
    }
    @Test
    public void testUpdateTranRemark(){
        TranRemark tranRemark = new TranRemark();
        tranRemark.setId("f7c59976fdaa4ea0b5c1f9f09a3531f6");
        tranRemark.setNoteContent("zhonghua0001");
        int i = tranRemarkMapper.updateTranRemark(tranRemark);
    }
}
