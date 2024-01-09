package com.powernode.crm.workbench.service.impl;

import com.powernode.crm.workbench.domain.ContactsActivityRelation;
import com.powernode.crm.workbench.mapper.ContactsActivityRelationMapper;
import com.powernode.crm.workbench.service.ContactsActivityRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName:ContactsActivityRelationServiceImpl
 * Package:com.powernode.crm.workbench.service.impl
 * Description:
 *
 * @Author abel
 * @CreateDate 2023-11-12 10:46
 * @Version 1.0
 */
@Service("contactsActivityRelationService")
public class ContactsActivityRelationServiceImpl implements ContactsActivityRelationService {

    @Autowired
    private ContactsActivityRelationMapper contactsActivityRelationMapper;


    @Override
    public int saveCreateContactsActivityRelationByList(List<ContactsActivityRelation> list) {
        return contactsActivityRelationMapper.insertContactsActivityRelationByList(list);
    }

    @Override
    public int deleteContactsActivityRelationByContactsIdActivityId(ContactsActivityRelation relation) {
        return contactsActivityRelationMapper.deleteContactsActivityRelationByContactsIdActivityId(relation);
    }
}
