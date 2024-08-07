package com.powernode.crm.workbench.service.impl;

import com.powernode.crm.workbench.domain.Customer;
import com.powernode.crm.workbench.mapper.CustomerMapper;
import com.powernode.crm.workbench.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * ClassName:CustomerServiceImpl
 * Package:com.powernode.crm.workbench.service.impl
 * Description:
 *
 * @Author abel
 * @CreateDate 2023-11-03 16:28
 * @Version 1.0
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public List<Customer> queryCustomerByConditionForPage(Map<String, Object> map) {
        return customerMapper.selectCustomerByConditionForPage(map);
    }

    @Override
    public int queryCountOfCustomerByCondition(Map<String, Object> map) {
        return customerMapper.selectCountOfCustomerByCondition(map);
    }

    @Override
    public Customer queryCustomerForDetailById(String id) {
        return customerMapper.selectCustomerForDetailById(id);
    }

    @Override
    public List<String> queryCustomerNameByName(String name) {
        return customerMapper.selectCustomerNameByName(name);
    }

}
