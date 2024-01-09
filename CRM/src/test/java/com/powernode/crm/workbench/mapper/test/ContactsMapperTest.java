package com.powernode.crm.workbench.mapper.test;

import com.powernode.crm.workbench.domain.Contacts;
import com.powernode.crm.workbench.mapper.ContactsMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName:ContactsMapperTest
 * Package:com.powernode.crm.workbench.mapper.test
 * Description:
 *
 * @Author abel
 * @CreateDate 2023-11-06 14:51
 * @Version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext_service.xml")
public class ContactsMapperTest {

    @Autowired
    private ContactsMapper contactsMapper;
    @Test
    public void testSelectContactsByConditionForPage(){
        Map map = new HashMap();
        map.put("beginNo",0);
        map.put("pageSize",2);
        List contactsList = contactsMapper.selectContactsByConditionForPage(map);
        contactsList.forEach(contacts->{
            System.out.println(contacts);
        });
    }

    @Test
    public void testSelectCountOfContactsByCondition(){
        Map map = new HashMap();
        int i = contactsMapper.selectCountOfContactsByCondition(map);
        System.out.println(i);
    }

    @Test
    public void testSelectContactsForDetailById(){
        Contacts contacts = contactsMapper.selectContactsForDetailById("7f50a55957f4420cb1f9fbb3b916af04");
        System.out.println(contacts);
    }
    @Test
    public void testSelectContactsByContactsName(){
        List<Contacts> contactsList = contactsMapper.selectContactsByContactsName("红");
        contactsList.forEach(contacts -> {
            System.out.println(contacts);
        });
    }
}
