package com.powernode.crm.workbench.service;

import com.powernode.crm.workbench.domain.Activity;
import com.powernode.crm.workbench.domain.Clue;
import com.powernode.crm.workbench.domain.Customer;

import java.util.List;
import java.util.Map;

/**
 * ClassName:CustomerService
 * Package:com.powernode.crm.workbench.service
 * Description:
 *
 * @Author abel
 * @CreateDate 2023-11-03 16:28
 * @Version 1.0
 */
public interface CustomerService {

    List<Customer> queryCustomerByConditionForPage(Map<String,Object> map);

    int queryCountOfCustomerByCondition(Map<String,Object> map);

    Customer queryCustomerForDetailById(String id);

    List<String> queryCustomerNameByName(String name);


}
