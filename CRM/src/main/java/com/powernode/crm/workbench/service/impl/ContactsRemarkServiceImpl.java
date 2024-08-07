package com.powernode.crm.workbench.service.impl;

import com.powernode.crm.workbench.domain.ContactsRemark;
import com.powernode.crm.workbench.mapper.ContactsRemarkMapper;
import com.powernode.crm.workbench.service.ContactsRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName:ContactsRemarkServiceImpl
 * Package:com.powernode.crm.workbench.service.impl
 * Description:
 *
 * @Author abel
 * @CreateDate 2023-11-08 22:16
 * @Version 1.0
 */
@Service
public class ContactsRemarkServiceImpl implements ContactsRemarkService {

    @Autowired
    private ContactsRemarkMapper contactsRemarkMapper;

    @Override
    public List<ContactsRemark> queryContactsRemarkForDetailByContactsId(String contactsId) {
        return contactsRemarkMapper.selectContactsRemarkForDetailByContactsId(contactsId);
    }

    @Override
    public int saveCreateContactsRemark(ContactsRemark contactsRemark) {
        return contactsRemarkMapper.insertContactsRemark(contactsRemark);
    }

    @Override
    public int deleteContactsRemarkById(String id) {
        return contactsRemarkMapper.deleteContactsRemark(id);
    }

    @Override
    public int saveEditContactsRemark(ContactsRemark contactsRemark) {
        return contactsRemarkMapper.updateContactsRemark(contactsRemark);
    }
}
