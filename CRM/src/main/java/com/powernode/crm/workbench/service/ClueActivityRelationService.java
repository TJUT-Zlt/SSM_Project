package com.powernode.crm.workbench.service;

import com.powernode.crm.workbench.domain.ClueActivityRelation;

import java.util.List;

/**
 * ClassName:ClueActivityRelationService
 * Package:com.powernode.crm.workbench.service
 * Description:
 *
 * @Author abel
 * @CreateDate 2023-11-02 14:23
 * @Version 1.0
 */
public interface ClueActivityRelationService {

    int saveCreateClueActivityRelationByList(List<ClueActivityRelation> list);

    int deleteClueActivityRelationByClueIdActivityId(ClueActivityRelation relation);

    List<ClueActivityRelation> queryClueActivityRelationByClueId(String clueId);


}
