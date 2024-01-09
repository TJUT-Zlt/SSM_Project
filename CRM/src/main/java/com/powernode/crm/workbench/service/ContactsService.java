package com.powernode.crm.workbench.service;

import com.powernode.crm.workbench.domain.Contacts;
import com.powernode.crm.workbench.domain.Customer;

import java.util.List;
import java.util.Map;

/**
 * ClassName:ContactsService
 * Package:com.powernode.crm.workbench.service
 * Description:
 *
 * @Author abel
 * @CreateDate 2023-11-06 14:57
 * @Version 1.0
 */
public interface ContactsService {

    List<Contacts> queryContactsByConditionForPage(Map<String,Object> map);

    int queryCountOfContactsByCondition(Map<String,Object> map);

    Contacts queryContactsForDetailById(String id);

    List<Contacts> queryContactsByContactsName(String contactsName);
}
