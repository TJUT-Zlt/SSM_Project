package com.powernode.crm.workbench.mapper;

import com.powernode.crm.workbench.domain.ContactsRemark;
import com.powernode.crm.workbench.domain.TranRemark;

import java.util.List;

public interface ContactsRemarkMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_contacts_remark
     *
     * @mbggenerated Fri Nov 03 12:00:11 CST 2023
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_contacts_remark
     *
     * @mbggenerated Fri Nov 03 12:00:11 CST 2023
     */
    int insert(ContactsRemark record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_contacts_remark
     *
     * @mbggenerated Fri Nov 03 12:00:11 CST 2023
     */
    int insertSelective(ContactsRemark record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_contacts_remark
     *
     * @mbggenerated Fri Nov 03 12:00:11 CST 2023
     */
    ContactsRemark selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_contacts_remark
     *
     * @mbggenerated Fri Nov 03 12:00:11 CST 2023
     */
    int updateByPrimaryKeySelective(ContactsRemark record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_contacts_remark
     *
     * @mbggenerated Fri Nov 03 12:00:11 CST 2023
     */
    int updateByPrimaryKey(ContactsRemark record);

    /**
     * 批量保存创建的联系人备注
     * @param list
     * @return
     */
    int insertContactsRemarkByList(List<ContactsRemark> list);

    /**
     * 根据tranId查询该联系人下所有备注
     * @param contactsId
     * @return
     */
    List<ContactsRemark> selectContactsRemarkForDetailByContactsId(String contactsId);

    /**
     *保存创建的联系人备注
     * @param contactsRemark
     * @return
     */
    int insertContactsRemark(ContactsRemark contactsRemark);

    /**
     *根据id删除联系人备注
     * @param id
     * @return
     */
    int deleteContactsRemark(String id);

    /**
     *保存修改的联系人备注
     * @param contactsRemark
     * @return
     */
    int updateContactsRemark(ContactsRemark contactsRemark);



}
