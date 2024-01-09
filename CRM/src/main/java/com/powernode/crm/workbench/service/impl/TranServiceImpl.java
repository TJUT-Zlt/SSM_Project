package com.powernode.crm.workbench.service.impl;

import com.powernode.crm.commons.constants.Constants;
import com.powernode.crm.commons.utils.DateUtils;
import com.powernode.crm.commons.utils.UUIDUtils;
import com.powernode.crm.settings.domain.User;
import com.powernode.crm.workbench.domain.Customer;
import com.powernode.crm.workbench.domain.FunnelVO;
import com.powernode.crm.workbench.domain.Tran;
import com.powernode.crm.workbench.domain.TranHistory;
import com.powernode.crm.workbench.mapper.CustomerMapper;
import com.powernode.crm.workbench.mapper.TranHistoryMapper;
import com.powernode.crm.workbench.mapper.TranMapper;
import com.powernode.crm.workbench.service.TranService;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * ClassName:TranServiceImpl
 * Package:com.powernode.crm.workbench.service.impl
 * Description:
 *
 * @Author abel
 * @CreateDate 2023-11-03 21:03
 * @Version 1.0
 */
@Service("tranService")
public class TranServiceImpl implements TranService {

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private TranMapper tranMapper;

    @Autowired
    private TranHistoryMapper tranHistoryMapper;

    @Override
    public void saveCreateTran(Map<String, Object> map) {
        String customerName = (String) map.get("customerName");
        User user = (User) map.get(Constants.SESSION_USER);
        //根据name精确查询客户
        Customer customer = customerMapper.selectCustomerByName(customerName);
        //如果客户不存在，则新建客户
        if(customer==null){
            customer=new Customer();
            customer.setOwner(user.getId());
            customer.setName(customerName);
            customer.setId(UUIDUtils.getUUID());
            customer.setCreateTime(DateUtils.formateDateTime(new Date()));
            customer.setCreateBy(user.getId());
            customerMapper.insertCustomer(customer);
        }
        //保存交易
        Tran tran = new Tran();
        tran.setStage((String)map.get("stage"));
        tran.setOwner((String) map.get("owner"));
        tran.setNextContactTime((String) map.get("nextContactTime"));
        tran.setName((String) map.get("name"));
        tran.setMoney((String) map.get("money"));
        tran.setId(UUIDUtils.getUUID());
        tran.setExpectedDate((String) map.get("expectedDate"));
        tran.setCustomerId(customer.getId());
        tran.setCreateTime(DateUtils.formateDateTime(new Date()));
        tran.setCreateBy(user.getId());
        tran.setContactSummary((String) map.get("contactSummary"));
        tran.setContactsId((String) map.get("contactsId"));
        tran.setActivityId((String) map.get("activityId"));
        tran.setDescription((String) map.get("description"));
        tran.setSource((String) map.get("source"));
        tran.setType((String) map.get("type"));
        tranMapper.insertTran(tran);
        //保存交易历史
        TranHistory tranHistory = new TranHistory();
        tranHistory.setCreateBy(user.getId());
        tranHistory.setCreateTime(DateUtils.formateDateTime(new Date()));
        tranHistory.setExpectedDate(tran.getExpectedDate());
        tranHistory.setId(UUIDUtils.getUUID());
        tranHistory.setMoney(tran.getMoney());
        tranHistory.setStage(tran.getStage());
        tranHistory.setTranId(tran.getId());
        tranHistoryMapper.insertTranHistory(tranHistory);
    }

    @Override
    public List<Tran> queryTranByConditionForPage(Map<String, Object> map) {
        return tranMapper.selectTranByConditionForPage(map);
    }

    @Override
    public int queryCountOfTranByCondition(Map<String, Object> map) {
        return tranMapper.selectCountOfTranByCondition(map);
    }

    @Override
    public int deleteTranByIds(String[] ids) {
        return tranMapper.deleteTranByIds(ids);
    }

    @Override
    public Tran queryTranById(String id) {
        return tranMapper.selectTranById(id);
    }

    @Override
    public void saveEditTran(Map<String,Object> map) {

        String customerName = (String) map.get("customerName");
        User user = (User) map.get(Constants.SESSION_USER);
        //根据name精确查询客户
        Customer customer = customerMapper.selectCustomerByName(customerName);
        //如果客户不存在，则新建客户
        if(customer==null){
            customer=new Customer();
            customer.setOwner(user.getId());
            customer.setName(customerName);
            customer.setId(UUIDUtils.getUUID());
            customer.setCreateTime(DateUtils.formateDateTime(new Date()));
            customer.setCreateBy(user.getId());
            customerMapper.insertCustomer(customer);
        }
        //保存交易
        Tran tran = new Tran();
        tran.setId((String) map.get("id"));

        tran.setStage((String)map.get("stage"));
        tran.setOwner((String) map.get("owner"));
        tran.setNextContactTime((String) map.get("nextContactTime"));
        tran.setName((String) map.get("name"));
        tran.setMoney((String) map.get("money"));
        tran.setExpectedDate((String) map.get("expectedDate"));
        tran.setCustomerId(customer.getId());
        tran.setEditTime(DateUtils.formateDateTime(new Date()));
        tran.setEditBy(user.getId());
        tran.setContactSummary((String) map.get("contactSummary"));
        tran.setContactsId((String) map.get("contactsId"));
        tran.setActivityId((String) map.get("activityId"));
        tran.setDescription((String) map.get("description"));
        tran.setSource((String) map.get("source"));
        tran.setType((String) map.get("type"));
        tranMapper.updateTran(tran);
        //保存交易历史
        TranHistory tranHistory = new TranHistory();
        tranHistory.setCreateBy(user.getId());
        tranHistory.setCreateTime(DateUtils.formateDateTime(new Date()));
        tranHistory.setExpectedDate(tran.getExpectedDate());
        tranHistory.setId(UUIDUtils.getUUID());
        tranHistory.setMoney(tran.getMoney());
        tranHistory.setStage(tran.getStage());
        tranHistory.setTranId(tran.getId());
        tranHistoryMapper.insertTranHistory(tranHistory);
    }

    @Override
    public Tran queryTranForDetailById(String id) {
        return tranMapper.selectTranForDetailById(id);
    }

    @Override
    public List<FunnelVO> queryCountOfTranGroupByStage() {
        return tranMapper.selectCountOfTranGroupByStage();
    }
}
