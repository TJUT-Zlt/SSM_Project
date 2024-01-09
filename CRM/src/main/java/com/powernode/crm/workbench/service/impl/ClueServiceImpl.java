package com.powernode.crm.workbench.service.impl;

import com.powernode.crm.commons.constants.Constants;
import com.powernode.crm.commons.utils.DateUtils;
import com.powernode.crm.commons.utils.UUIDUtils;
import com.powernode.crm.settings.domain.User;
import com.powernode.crm.workbench.domain.*;
import com.powernode.crm.workbench.mapper.*;
import com.powernode.crm.workbench.service.ClueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * ClassName:ClueServiceImpl
 * Package:com.powernode.crm.workbench.service.impl
 * Description:
 *
 * @Author abel
 * @CreateDate 2023-10-30 11:58
 * @Version 1.0
 */
@Service("clueService")
public class ClueServiceImpl implements ClueService {

    @Autowired
    private ClueMapper clueMapper;

    @Autowired
    private ClueRemarkMapper clueRemarkMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private CustomerRemarkMapper customerRemarkMapper;

    @Autowired
    private ContactsMapper contactsMapper;

    @Autowired
    private ContactsRemarkMapper contactsRemarkMapper;

    @Autowired
    private ClueActivityRelationMapper clueActivityRelationMapper;

    @Autowired
    private ContactsActivityRelationMapper contactsActivityRelationMapper;

    @Autowired
    private TranMapper tranMapper;

    @Autowired
    private TranRemarkMapper tranRemarkMapper;

    @Autowired
    private TranHistoryMapper tranHistoryMapper;

    @Override
    public int saveCreateClue(Clue clue) {
        return clueMapper.insertClue(clue);
    }

    @Override
    public List<Clue> queryClueByConditionForPage(Map<String, Object> map) {
        return clueMapper.selectClueByConditionForPage(map);
    }

    @Override
    public int queryCountOfClueByCondition(Map<String, Object> map) {
        return clueMapper.selectCountOfClueByCondition(map);
    }

    @Override
    public int deleteClueByIds(String[] ids) {
        return clueMapper.deleteCLueByIds(ids);
    }

    @Override
    public Clue queryClueById(String id) {
        return clueMapper.selectClueById(id);
    }

    @Override
    public int saveEditClue(Clue clue) {
        return clueMapper.updateClue(clue);
    }

    @Override
    public Clue queryClueForDetailById(String id) {
        return clueMapper.selectClueForDetailById(id);
    }

    @Override
    public void saveConvertClue(Map<String, Object> map) {

        String clueId = (String) map.get("clueId");
        User user = (User)map.get(Constants.SESSION_USER);
        String isCreateTran = (String) map.get("isCreateTran");
        //根据id查询线索的信息
        Clue clue = clueMapper.selectClueById(clueId);

        //把该线索中有关公司的信息转换到客户表中
        Customer customer = new Customer();
        customer.setId(UUIDUtils.getUUID());
        customer.setOwner(user.getId());
        customer.setName(clue.getCompany());
        customer.setWebsite(clue.getWebsite());
        customer.setPhone(clue.getPhone());
        customer.setCreateBy(user.getId());
        customer.setCreateTime(DateUtils.formateDateTime(new Date()));
//        customer.setEditBy();
//        customer.setEditTime();
        customer.setContactSummary(clue.getContactSummary());
        customer.setNextContactTime(clue.getNextContactTime());
        customer.setDescription(clue.getDescription());
        customer.setAddress(clue.getAddress());
        customerMapper.insertCustomer(customer);

        //把该线索中有关个人的信息转换到联系人表中
        Contacts contacts = new Contacts();
        contacts.setId(UUIDUtils.getUUID());
        contacts.setOwner(user.getId());
        contacts.setSource(clue.getSource());
        contacts.setCustomerId(customer.getId());
        contacts.setFullname(clue.getFullname());
        contacts.setAppellation(clue.getAppellation());
        contacts.setEmail(clue.getEmail());
        contacts.setMphone(clue.getMphone());
        contacts.setJob(clue.getJob());
        contacts.setCreateBy(user.getId());
        contacts.setCreateTime(DateUtils.formateDateTime(new Date()));
//        contacts.setEditBy();
//        contacts.setEditTime();
        contacts.setDescription(clue.getDescription());
        contacts.setContactSummary(clue.getContactSummary());
        contacts.setNextContactTime(clue.getNextContactTime());
        contacts.setAddress(clue.getAddress());
        contactsMapper.insertContacts(contacts);

        //根据clueId查询该线索下所有的备注
        List<ClueRemark> clueRemarkList = clueRemarkMapper.selectClueRemarkByClueId(clueId);

        //如果该线索下有备注，把该线索下所有的备注转换到客户备注表中一份,把该线索下所有的备注转换到联系人备注表中一份
        if(clueRemarkList != null && clueRemarkList.size() > 0){

            CustomerRemark customerRemark = null;
            ContactsRemark contactsRemark = null;
            List<CustomerRemark> customerRemarkList = new ArrayList<>();
            List<ContactsRemark> contactsRemarkList = new ArrayList<>();

            for(ClueRemark clueRemark:clueRemarkList){
                customerRemark = new CustomerRemark();
                customerRemark.setId(UUIDUtils.getUUID());
                customerRemark.setNoteContent(clueRemark.getNoteContent());
                customerRemark.setCreateBy(clueRemark.getCreateBy());
                customerRemark.setCreateTime(clueRemark.getCreateTime());
                customerRemark.setEditBy(clueRemark.getEditBy());
                customerRemark.setEditTime(clueRemark.getEditTime());
                customerRemark.setEditFlag(clueRemark.getEditFlag());
                customerRemark.setCustomerId(customer.getId());
                customerRemarkList.add(customerRemark);

                contactsRemark =new ContactsRemark();
                contactsRemark.setId(UUIDUtils.getUUID());
                contactsRemark.setNoteContent(clueRemark.getNoteContent());
                contactsRemark.setCreateBy(clueRemark.getCreateBy());
                contactsRemark.setCreateTime(clueRemark.getCreateTime());
                contactsRemark.setEditBy(clueRemark.getEditBy());
                contactsRemark.setEditTime(clueRemark.getEditTime());
                contactsRemark.setEditFlag(clueRemark.getEditFlag());
                contactsRemark.setContactsId(contacts.getId());
                contactsRemarkList.add(contactsRemark);
            }
            customerRemarkMapper.insertCustomerRemarkByList(customerRemarkList);
            contactsRemarkMapper.insertContactsRemarkByList(contactsRemarkList);
        }

        List<ClueActivityRelation> clueActivityRelationList = clueActivityRelationMapper.selectClueActivityRelationByClueId(clueId);
        if(clueActivityRelationList!= null && clueActivityRelationList.size()>0){
            ContactsActivityRelation contactsActivityRelation = null;
            List<ContactsActivityRelation> contactsActivityRelationList = new ArrayList<>();
            for(ClueActivityRelation clueActivityRelation:clueActivityRelationList){
                contactsActivityRelation = new ContactsActivityRelation();
                contactsActivityRelation.setId(UUIDUtils.getUUID());
                contactsActivityRelation.setContactsId(contacts.getId());
                contactsActivityRelation.setActivityId(clueActivityRelation.getActivityId());
                contactsActivityRelationList.add(contactsActivityRelation);
            }
            contactsActivityRelationMapper.insertContactsActivityRelationByList(contactsActivityRelationList);
        }

        //如果需要创建交易，则往交易表中添加一条记录,还需要把该线索下的备注转换到交易备注表中一份
        if("true".equals(isCreateTran)){
            Tran tran = new Tran();
            tran.setId(UUIDUtils.getUUID());
            tran.setOwner(user.getId());
            tran.setMoney((String)map.get("money"));
            tran.setName((String) map.get("name"));
            tran.setExpectedDate((String) map.get("expectedDate"));
            tran.setCustomerId(customer.getId());
            tran.setStage((String) map.get("stage"));
//            tran.setType();
//            tran.setSource();
            tran.setActivityId((String) map.get("activityId"));
//            tran.setContactsId();
            tran.setCreateBy(user.getId());
            tran.setCreateTime(DateUtils.formateDateTime(new Date()));
//            tran.setEditBy();
//            tran.setEditTime();
//            tran.setDescription();
//            tran.setContactSummary();
//            tran.setNextContactTime();
            tranMapper.insertTran(tran);

            if(clueRemarkList != null && clueRemarkList.size() > 0) {

                TranRemark tranRemark = null;
                List<TranRemark> tranRemarkList = new ArrayList<>();
                for(ClueRemark clueRemark:clueRemarkList){
                    tranRemark = new TranRemark();
                    tranRemark.setId(UUIDUtils.getUUID());
                    tranRemark.setNoteContent(clueRemark.getNoteContent());
                    tranRemark.setCreateBy(clueRemark.getCreateBy());
                    tranRemark.setCreateTime(clueRemark.getCreateTime());
                    tranRemark.setEditBy(clueRemark.getEditBy());
                    tranRemark.setEditTime(clueRemark.getEditTime());
                    tranRemark.setEditFlag(clueRemark.getEditFlag());
                    tranRemark.setTranId(tran.getId());
                    tranRemarkList.add(tranRemark);
                }
                tranRemarkMapper.insertTranRemarkByList(tranRemarkList);
            }
            //保存交易历史
            TranHistory tranHistory=new TranHistory();
            tranHistory.setCreateBy(user.getId());
            tranHistory.setCreateTime(DateUtils.formateDateTime(new Date()));
            tranHistory.setExpectedDate(tran.getExpectedDate());
            tranHistory.setId(UUIDUtils.getUUID());
            tranHistory.setMoney(tran.getMoney());
            tranHistory.setStage(tran.getStage());
            tranHistory.setTranId(tran.getId());
            tranHistoryMapper.insertTranHistory(tranHistory);
        }
    }
}
