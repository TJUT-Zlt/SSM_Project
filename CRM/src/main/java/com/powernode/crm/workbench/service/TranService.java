package com.powernode.crm.workbench.service;

import com.powernode.crm.workbench.domain.FunnelVO;
import com.powernode.crm.workbench.domain.Tran;

import java.util.List;
import java.util.Map;

/**
 * ClassName:TranService
 * Package:com.powernode.crm.workbench.service
 * Description:
 *
 * @Author abel
 * @CreateDate 2023-11-03 21:00
 * @Version 1.0
 */
public interface TranService {

    void saveCreateTran(Map<String,Object> map);

    List<Tran> queryTranByConditionForPage(Map<String,Object> map);

    int queryCountOfTranByCondition(Map<String,Object> map);

    int deleteTranByIds(String[] ids);

    Tran queryTranById(String id);

    void saveEditTran(Map<String,Object> map);

    Tran queryTranForDetailById(String id);

    List<FunnelVO> queryCountOfTranGroupByStage();
}
