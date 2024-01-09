package com.powernode.crm.workbench.mapper.test;

import com.powernode.crm.workbench.domain.CustomerRemark;
import com.powernode.crm.workbench.mapper.CustomerRemarkMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * ClassName:CustomerRemarkMapperTest
 * Package:com.powernode.crm.workbench.mapper.test
 * Description:
 *
 * @Author abel
 * @CreateDate 2023-11-03 19:35
 * @Version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext_service.xml")
public class CustomerRemarkMapperTest {

    @Autowired
    private CustomerRemarkMapper customerRemarkMapper;
    @Test
    public void testSelectClueRemarkForDetailByClueId(){
        List<CustomerRemark> customerRemarkList = customerRemarkMapper.selectCustomerRemarkForDetailByCustomerId("8f795ed0768f4438bb8664d69500f5dc");
        customerRemarkList.forEach(customerRemark -> System.out.println(customerRemark));
    }
}
