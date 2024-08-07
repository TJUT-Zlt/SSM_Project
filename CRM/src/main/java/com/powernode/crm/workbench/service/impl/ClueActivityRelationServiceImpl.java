package com.powernode.crm.workbench.service.impl;

import com.powernode.crm.workbench.domain.ClueActivityRelation;
import com.powernode.crm.workbench.mapper.ClueActivityRelationMapper;
import com.powernode.crm.workbench.service.ClueActivityRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName:ClueActivityRelationServiceImpl
 * Package:com.powernode.crm.workbench.service.impl
 * Description:
 *
 * @Author abel
 * @CreateDate 2023-11-02 14:24
 * @Version 1.0
 */
@Service
public class ClueActivityRelationServiceImpl implements ClueActivityRelationService {

    @Autowired
    private ClueActivityRelationMapper clueActivityRelationMapper;

    @Override
    public int saveCreateClueActivityRelationByList(List<ClueActivityRelation> list) {
        return clueActivityRelationMapper.insertClueActivityRelationByList(list);
    }

    @Override
    public int deleteClueActivityRelationByClueIdActivityId(ClueActivityRelation relation) {
        return clueActivityRelationMapper.deleteClueActivityRelationByClueIdActivityId(relation);
    }

    @Override
    public List<ClueActivityRelation> queryClueActivityRelationByClueId(String clueId) {
        return clueActivityRelationMapper.selectClueActivityRelationByClueId(clueId);
    }
}
