package com.powernode.crm.settings.mapper.test;

/**
 * ClassName:DicValueMapperTest
 * Package:com.powernode.crm.settings.mapper.test
 * Description:
 *
 * @Author abel
 * @CreateDate 2023-10-30 10:02
 * @Version 1.0
 */

import com.powernode.crm.settings.domain.DicValue;
import com.powernode.crm.settings.mapper.DicValueMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext_service.xml"})
public class DicValueMapperTest {

    @Autowired
    private DicValueMapper dicValueMapper;

    @Test
    public void testSelectDicValueByTypeCode(){
        List<DicValue> sourceList = dicValueMapper.selectDicValueByTypeCode("source");
        sourceList.forEach(source->{
            System.out.println(source);
        });
    }

    @Test
    public void testSelectAllDicValue(){
        List<DicValue> dicValueList = dicValueMapper.selectAllDicValue();
        dicValueList.forEach(dicValue -> {
            System.out.println(dicValue);
        });
    }
}
