package com.powernode.crm.workbench.mapper.test;

import com.powernode.crm.commons.utils.UUIDUtils;
import com.powernode.crm.workbench.domain.ContactsRemark;
import com.powernode.crm.workbench.mapper.ContactsRemarkMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * ClassName:ContactsRemarkTest
 * Package:com.powernode.crm.workbench.mapper.test
 * Description:
 *
 * @Author abel
 * @CreateDate 2023-11-08 21:59
 * @Version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext_service.xml")
public class ContactsRemarkMapperTest {

    @Autowired
    private ContactsRemarkMapper contactsRemarkMapper;

    @Test
    public void testSelectContactsRemarkForDetailByTranId(){
        List<ContactsRemark> contactsRemarkList = contactsRemarkMapper.selectContactsRemarkForDetailByContactsId("db75156443fd45b5a2df92a454d6a2bd");
        contactsRemarkList.forEach(contactsRemark -> {
            System.out.println(contactsRemark);
        });
    }
    @Test
    public void testInsertContactsRemark(){
        ContactsRemark contactsRemark = new ContactsRemark();
        contactsRemark.setContactsId("db75156443fd45b5a2df92a454d6a2bd");
        contactsRemark.setId(UUIDUtils.getUUID());
        contactsRemarkMapper.insertContactsRemark(contactsRemark);
    }
    @Test
    public void testDeleteContactsRemark(){
        contactsRemarkMapper.deleteContactsRemark("5d403c65fdc34f7197ac977c6f89c358");
    }
    @Test
    public void testUpdateContactsRemark(){
        ContactsRemark contactsRemark = new ContactsRemark();
        contactsRemark.setId("c6e491e74b7d498ca59652c100b9fb6a");
        contactsRemark.setNoteContent("2023-11-11");
        contactsRemarkMapper.updateContactsRemark(contactsRemark);
    }
}
