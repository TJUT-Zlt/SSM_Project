package com.powernode.crm.workbench.service;

import com.powernode.crm.workbench.domain.ContactsRemark;
import com.powernode.crm.workbench.domain.TranRemark;

import java.util.List;

/**
 * ClassName:ContactsRemarkService
 * Package:com.powernode.crm.workbench.service
 * Description:
 *
 * @Author abel
 * @CreateDate 2023-11-08 22:14
 * @Version 1.0
 */
public interface ContactsRemarkService {

    List<ContactsRemark> queryContactsRemarkForDetailByContactsId(String contactsId);

    int saveCreateContactsRemark(ContactsRemark contactsRemark);

    int deleteContactsRemarkById(String id);

    int saveEditContactsRemark(ContactsRemark contactsRemark);

}
