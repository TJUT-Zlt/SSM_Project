package com.powernode.crm.workbench.mapper.test;

import com.powernode.crm.workbench.domain.ContactsActivityRelation;
import com.powernode.crm.workbench.mapper.ContactsActivityRelationMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * ClassName:ContactsActivityRelationMapperTest
 * Package:com.powernode.crm.workbench.mapper.test
 * Description:
 *
 * @Author abel
 * @CreateDate 2023-11-12 10:41
 * @Version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext_service.xml")
public class ContactsActivityRelationMapperTest {
    @Autowired
    private ContactsActivityRelationMapper contactsActivityRelationMapper;
    @Test
    public void testSelectContactsActivityRelationByContactsId(){
        List<ContactsActivityRelation> contactsActivityRelationList = contactsActivityRelationMapper.selectContactsActivityRelationByContactsId("db75156443fd45b5a2df92a454d6a2bd");
        contactsActivityRelationList.forEach(contactsActivityRelation -> {
            System.out.println(contactsActivityRelation);
        });
    }

}
