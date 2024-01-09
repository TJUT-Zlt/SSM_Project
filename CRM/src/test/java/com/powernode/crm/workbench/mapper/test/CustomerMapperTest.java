package com.powernode.crm.workbench.mapper.test;

import com.powernode.crm.workbench.domain.Customer;
import com.powernode.crm.workbench.mapper.CustomerMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName:CustomerMapperTest
 * Package:com.powernode.crm.workbench.mapper.test
 * Description:
 *
 * @Author abel
 * @CreateDate 2023-11-03 16:18
 * @Version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext_service.xml")
public class CustomerMapperTest {
    @Autowired
    private CustomerMapper customerMapper;

    @Test
    public void testSelectCustomerByConditionForPage(){
        Map map = new HashMap();
        map.put("beginNo",0);
        map.put("pageSize",3);
        List<Customer> customerList = customerMapper.selectCustomerByConditionForPage(map);
        customerList.forEach(customer -> System.out.println(customer));
    }
    @Test
    public void testSelectCountOfCustomerByCondition(){
        Map map = new HashMap();
        map.put("owner","张龙涛");
        int i = customerMapper.selectCountOfCustomerByCondition(map);
        System.out.println(i);
    }
    @Test
    public void testSelectCustomerForDetailById(){
        Customer customer = customerMapper.selectCustomerForDetailById("785038f6f6bb4804a0e3488f92857326");
        System.out.println(customer);
    }
    @Test
    public void testSelectCustomerByName(){
        Customer customer = customerMapper.selectCustomerByName("达内教育");
        System.out.println(customer);
    }


}
