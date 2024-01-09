package com.powernode.crm.workbench.mapper;

import com.powernode.crm.workbench.domain.ClueActivityRelation;
import com.powernode.crm.workbench.domain.ContactsActivityRelation;

import java.util.List;

public interface ContactsActivityRelationMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_contacts_activity_relation
     *
     * @mbggenerated Fri Nov 03 11:59:48 CST 2023
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_contacts_activity_relation
     *
     * @mbggenerated Fri Nov 03 11:59:48 CST 2023
     */
    int insert(ContactsActivityRelation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_contacts_activity_relation
     *
     * @mbggenerated Fri Nov 03 11:59:48 CST 2023
     */
    int insertSelective(ContactsActivityRelation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_contacts_activity_relation
     *
     * @mbggenerated Fri Nov 03 11:59:48 CST 2023
     */
    ContactsActivityRelation selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_contacts_activity_relation
     *
     * @mbggenerated Fri Nov 03 11:59:48 CST 2023
     */
    int updateByPrimaryKeySelective(ContactsActivityRelation record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_contacts_activity_relation
     *
     * @mbggenerated Fri Nov 03 11:59:48 CST 2023
     */
    int updateByPrimaryKey(ContactsActivityRelation record);

    /**
     * 批量保存创建的联系人和市场活动的关联关系
     * @param list
     * @return
     */
    int insertContactsActivityRelationByList(List<ContactsActivityRelation> list);
    /**
     * 根据clueId和activityId删除线索和市场活动的关联关系
     * @param relation
     * @return
     */
    int deleteContactsActivityRelationByContactsIdActivityId(ContactsActivityRelation relation);

    /**
     * 根据contactsId查询线索和市场活动的关联关系
     * @param contactsId
     * @return
     */
    List<ContactsActivityRelation> selectContactsActivityRelationByContactsId(String contactsId);


}