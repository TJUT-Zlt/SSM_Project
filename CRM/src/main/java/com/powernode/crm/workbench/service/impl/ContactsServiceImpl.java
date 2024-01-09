package com.powernode.crm.workbench.service.impl;

import com.powernode.crm.workbench.domain.Contacts;
import com.powernode.crm.workbench.mapper.ContactsMapper;
import com.powernode.crm.workbench.service.ContactsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * ClassName:ContactsServiceImpl
 * Package:com.powernode.crm.workbench.service.impl
 * Description:
 *
 * @Author abel
 * @CreateDate 2023-11-06 14:58
 * @Version 1.0
 */
@Service("contactsService")
public class ContactsServiceImpl implements ContactsService {

    @Autowired
    private ContactsMapper contactsMapper;

    @Override
    public List<Contacts> queryContactsByConditionForPage(Map<String, Object> map) {
        return contactsMapper.selectContactsByConditionForPage(map);
    }

    @Override
    public int queryCountOfContactsByCondition(Map<String, Object> map) {
        return contactsMapper.selectCountOfContactsByCondition(map);
    }

    @Override
    public Contacts queryContactsForDetailById(String id) {
        return contactsMapper.selectContactsForDetailById(id);
    }

    @Override
    public List<Contacts> queryContactsByContactsName(String contactsName) {
        return contactsMapper.selectContactsByContactsName(contactsName);
    }
}
