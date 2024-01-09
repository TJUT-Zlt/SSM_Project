package com.powernode.crm.workbench.service;

import com.powernode.crm.workbench.domain.ClueActivityRelation;
import com.powernode.crm.workbench.domain.ContactsActivityRelation;

import java.util.List;

/**
 * ClassName:ContactsActivityRelationService
 * Package:com.powernode.crm.workbench.service
 * Description:
 *
 * @Author abel
 * @CreateDate 2023-11-12 10:45
 * @Version 1.0
 */
public interface ContactsActivityRelationService {

    int saveCreateContactsActivityRelationByList(List<ContactsActivityRelation> list);

    int deleteContactsActivityRelationByContactsIdActivityId(ContactsActivityRelation relation);

}
