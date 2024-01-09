package com.powernode.crm.workbench.web.controller;

import com.powernode.crm.settings.domain.User;
import com.powernode.crm.settings.service.UserService;
import com.powernode.crm.workbench.domain.*;
import com.powernode.crm.workbench.service.CustomerRemarkService;
import com.powernode.crm.workbench.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName:CustomerController
 * Package:com.powernode.crm.workbench.web.controller
 * Description:
 *
 * @Author abel
 * @CreateDate 2023-11-03 10:39
 * @Version 1.0
 */
@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRemarkService customerRemarkService;

    @Autowired
    private UserService userService;

    @RequestMapping("/workbench/customer/customerIndex.do")
    public String customerIndex(HttpServletRequest request){
        List<User> userList = userService.queryAllUser();
        request.setAttribute("userList", userList);

        return "workbench/customer/customerIndex";
    }

    @RequestMapping("/workbench/customer/queryCustomerByConditionForPage.do")
    @ResponseBody
    public Object queryCustomerByConditionForPage(String name,String owner,
                                                  String phone,String website,
                                                  int pageNo,int pageSize){
        //封装参数
        Map<String,Object> map = new HashMap<>();
        map.put("name",name);
        map.put("owner",owner);
        map.put("phone",phone);
        map.put("website",website);
        map.put("beginNo",(pageNo-1)*pageSize);
        map.put("pageSize",pageSize);
        //调用service层方法，查询数据
        List<Customer> customerList = customerService.queryCustomerByConditionForPage(map);
        int totalRows = customerService.queryCountOfCustomerByCondition(map);
        Map<String,Object> retMap = new HashMap<>();
        retMap.put("customerList",customerList);
        retMap.put("totalRows",totalRows);
        return retMap;
    }

    @RequestMapping("/workbench/customer/detailCustomer.do")
    public String detailCustomer(String id, HttpServletRequest request) {
        Customer customer = customerService.queryCustomerForDetailById(id);
        List<CustomerRemark> customerRemarkList = customerRemarkService.queryCustomerRemarkForDetailByCustomerId(id);

        //待完善

        request.setAttribute("customer", customer);
        request.setAttribute("customerRemarkList", customerRemarkList);
        return "workbench/customer/customerDetail";
    }



}
