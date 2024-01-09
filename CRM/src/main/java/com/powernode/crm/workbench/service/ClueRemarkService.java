package com.powernode.crm.workbench.service;

import com.powernode.crm.workbench.domain.ActivityRemark;
import com.powernode.crm.workbench.domain.ClueRemark;

import java.util.List;

/**
 * ClassName:ClueRemarkService
 * Package:com.powernode.crm.workbench.web.controller
 * Description:
 *
 * @Author abel
 * @CreateDate 2023-10-31 18:07
 * @Version 1.0
 */
public interface ClueRemarkService {

    List<ClueRemark> queryClueRemarkForDetailByClueId(String clueId);

    int saveCreateClueRemark(ClueRemark clueRemark);

    int deleteClueRemarkById(String id);

    int saveEditClueRemark(ClueRemark clueRemark);
}
