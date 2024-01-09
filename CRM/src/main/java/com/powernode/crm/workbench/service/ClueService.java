package com.powernode.crm.workbench.service;

import com.powernode.crm.workbench.domain.Activity;
import com.powernode.crm.workbench.domain.Clue;

import java.util.List;
import java.util.Map;

/**
 * ClassName:ClueService
 * Package:com.powernode.crm.workbench.service
 * Description:
 *
 * @Author abel
 * @CreateDate 2023-10-30 11:57
 * @Version 1.0
 */
public interface ClueService {

    int saveCreateClue(Clue clue);

    List<Clue> queryClueByConditionForPage(Map<String,Object> map);

    int queryCountOfClueByCondition(Map<String,Object> map);

    int deleteClueByIds(String[] ids);

    Clue queryClueById(String id);

    int saveEditClue(Clue clue);

    Clue queryClueForDetailById(String id);

    void saveConvertClue(Map<String,Object> map);

}
