package com.powernode.crm.settings.mapper.test;

import com.powernode.crm.settings.domain.DicType;
import com.powernode.crm.settings.mapper.DicTypeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * ClassName:DicTypeMapperTest
 * Package:com.powernode.crm.settings.mapper.test
 * Description:
 *
 * @Author abel
 * @CreateDate 2023-11-06 19:12
 * @Version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext_service.xml"})
public class DicTypeMapperTest {

    @Autowired
    private DicTypeMapper dicTypeMapper;
    @Test
    public void testSelectAllDicType(){
        List<DicType> dicTypeList = dicTypeMapper.selectAllDicType();
        dicTypeList.forEach(dicType -> {
            System.out.println(dicType);
        });
    }
}
