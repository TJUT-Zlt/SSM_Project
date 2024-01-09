package com.powernode.crm.workbench.mapper;

import com.powernode.crm.workbench.domain.Contacts;
import com.powernode.crm.workbench.domain.Customer;

import java.util.List;
import java.util.Map;

public interface ContactsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_contacts
     *
     * @mbggenerated Fri Nov 03 11:59:31 CST 2023
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_contacts
     *
     * @mbggenerated Fri Nov 03 11:59:31 CST 2023
     */
    int insert(Contacts record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_contacts
     *
     * @mbggenerated Fri Nov 03 11:59:31 CST 2023
     */
    int insertSelective(Contacts record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_contacts
     *
     * @mbggenerated Fri Nov 03 11:59:31 CST 2023
     */
    Contacts selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_contacts
     *
     * @mbggenerated Fri Nov 03 11:59:31 CST 2023
     */
    int updateByPrimaryKeySelective(Contacts record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_contacts
     *
     * @mbggenerated Fri Nov 03 11:59:31 CST 2023
     */
    int updateByPrimaryKey(Contacts record);

    /**
     * 保存创建的联系人
     * @param contacts
     * @return
     */
    int insertContacts(Contacts contacts);

    /**
     *根据条件分页查询联系人的列表
     * @param map
     * @return
     */
    List<Contacts> selectContactsByConditionForPage(Map<String,Object> map);

    /**
     * 根据条件查询联系人的总条数
     * @param map
     * @return
     */
    int selectCountOfContactsByCondition(Map<String,Object> map);

    /**
     * 根据id查询联系人的明细信息
     * @param id
     * @return
     */
    Contacts selectContactsForDetailById(String id);

    /**
     * 根据联系人姓名模糊查询联系人
     * @param contactsName
     * @return
     */
    List<Contacts> selectContactsByContactsName(String contactsName);
}