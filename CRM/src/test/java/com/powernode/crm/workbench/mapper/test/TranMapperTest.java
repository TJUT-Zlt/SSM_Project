package com.powernode.crm.workbench.mapper.test;

import com.powernode.crm.workbench.domain.FunnelVO;
import com.powernode.crm.workbench.domain.Tran;
import com.powernode.crm.workbench.mapper.TranMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName:TranMapperTest
 * Package:com.powernode.crm.workbench.mapper.test
 * Description:
 *
 * @Author abel
 * @CreateDate 2023-11-04 12:06
 * @Version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext_service.xml")
public class TranMapperTest {


    @Autowired
    private TranMapper tranMapper;

    @Test
    public void testSelectTranByConditionForPage(){
        Map map = new HashMap();
        map.put("beginNo",0);
        map.put("pageSize",3);
        List tranList = tranMapper.selectTranByConditionForPage(map);
        tranList.forEach(tran->{
            System.out.println(tran);
        });
    }
    @Test
    public void testSelectCountOfTranByCondition(){
        Map map = new HashMap();
        int i = tranMapper.selectCountOfTranByCondition(map);
        System.out.println(i);
    }
    @Test
    public void testDeleteTranByIds(){
        String[] ids = {"111122","11222"};
        int i = tranMapper.deleteTranByIds(ids);
        System.out.println(i);
    }
    @Test
    public void testSelectTranById(){
        Tran tran = tranMapper.selectTranById("a613c35d149142c79b98025afd9a4446");
        System.out.println(tran);
    }

    @Test
    public void testUpdateTran() {
        Tran tran = new Tran();
        tran.setId("a613c35d149142c79b98025afd9a4446");
        tran.setName("尚硅谷-交易001");
        tranMapper.updateTran(tran);
    }

    @Test
    public void testSelectTranForDetailById(){
        Tran tran = tranMapper.selectTranForDetailById("5316000aeeca49c491441d5e150213f4");
        System.out.println(tran);
    }
    @Test
    public void testSelectCountOfTranGroupByStage(){
        List<FunnelVO> funnelVOS = tranMapper.selectCountOfTranGroupByStage();
        funnelVOS.forEach(num ->{
            System.out.println(num);
        });
    }
}
