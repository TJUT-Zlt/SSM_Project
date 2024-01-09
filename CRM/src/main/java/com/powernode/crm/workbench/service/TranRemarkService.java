package com.powernode.crm.workbench.service;

import com.powernode.crm.workbench.domain.ActivityRemark;
import com.powernode.crm.workbench.domain.TranRemark;

import java.util.List;

/**
 * ClassName:TranRemarkService
 * Package:com.powernode.crm.workbench.service
 * Description:
 *
 * @Author abel
 * @CreateDate 2023-11-05 21:06
 * @Version 1.0
 */

public interface TranRemarkService {

    List<TranRemark> queryTranRemarkForDetailByTranId(String tranId);


    int saveCreateTranRemark(TranRemark tranRemark);

    int deleteTranRemarkById(String id);

    int saveEditTranRemark(TranRemark tranRemark);

}
