package com.powernode.crm.workbench.service.impl;

import com.powernode.crm.workbench.domain.CustomerRemark;
import com.powernode.crm.workbench.mapper.CustomerRemarkMapper;
import com.powernode.crm.workbench.service.CustomerRemarkService;
import com.powernode.crm.workbench.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName:CustomerRemarkServiceImpl
 * Package:com.powernode.crm.workbench.service.impl
 * Description:
 *
 * @Author abel
 * @CreateDate 2023-11-03 18:05
 * @Version 1.0
 */
@Service("customerRemarkService")
public class CustomerRemarkServiceImpl implements CustomerRemarkService {

    @Autowired
    private CustomerRemarkMapper customerRemarkMapper;

    @Override
    public List<CustomerRemark> queryCustomerRemarkForDetailByCustomerId(String customerId) {
        return customerRemarkMapper.selectCustomerRemarkForDetailByCustomerId(customerId);
    }
}
