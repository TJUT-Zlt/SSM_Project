package com.powernode.crm.workbench.service;

import com.powernode.crm.workbench.domain.TranHistory;

import java.util.List;

/**
 * ClassName:TranHistoryService
 * Package:com.powernode.crm.workbench.service
 * Description:
 *
 * @Author abel
 * @CreateDate 2023-11-06 16:29
 * @Version 1.0
 */
public interface TranHistoryService {
    List<TranHistory> queryTranHistoryForDetailByTranId(String tranId);

}
