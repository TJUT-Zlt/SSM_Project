package com.powernode.crm.workbench.service;

import com.powernode.crm.workbench.domain.CustomerRemark;

import java.util.List;

/**
 * ClassName:CustomerRemarkService
 * Package:com.powernode.crm.workbench.service
 * Description:
 *
 * @Author abel
 * @CreateDate 2023-11-03 17:21
 * @Version 1.0
 */
public interface CustomerRemarkService {

    List<CustomerRemark> queryCustomerRemarkForDetailByCustomerId(String customerId);

}
