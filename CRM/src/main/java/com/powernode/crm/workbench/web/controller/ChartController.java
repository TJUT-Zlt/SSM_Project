package com.powernode.crm.workbench.web.controller;

import com.powernode.crm.workbench.domain.FunnelVO;
import com.powernode.crm.workbench.service.TranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * ClassName:ChartController
 * Package:com.powernode.crm.workbench.web.controller
 * Description:
 *
 * @Author abel
 * @CreateDate 2023-11-06 18:12
 * @Version 1.0
 */
@Controller
public class ChartController {
    @Autowired
    private TranService tranService;

    @RequestMapping("/workbench/chart/transaction/transactionChart.do")
    public String transactionChart(){
        //跳转页面
        return "workbench/chart/transaction/transactionChart";
    }
    @RequestMapping("/workbench/chart/transaction/queryCountOfTranGroupByStage.do")
    @ResponseBody
    public Object queryCountOfTranGroupByStage(){
        //调用service层方法，查询数据
        List<FunnelVO> funnelVOList=tranService.queryCountOfTranGroupByStage();
        //根据查询结果，返回响应信息
        return funnelVOList;
    }

}
