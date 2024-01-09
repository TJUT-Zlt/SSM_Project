package com.powernode.crm.workbench.service.impl;

import com.powernode.crm.workbench.domain.ClueRemark;
import com.powernode.crm.workbench.mapper.ClueRemarkMapper;
import com.powernode.crm.workbench.service.ClueRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName:ClueRemarkServiceImpl
 * Package:com.powernode.crm.workbench.service.impl
 * Description:
 *
 * @Author abel
 * @CreateDate 2023-10-31 18:08
 * @Version 1.0
 */
@Service("clueRemarkService")
public class ClueRemarkServiceImpl implements ClueRemarkService {

    @Autowired
    private ClueRemarkMapper clueRemarkMapper;

    @Override
    public List<ClueRemark> queryClueRemarkForDetailByClueId(String clueId) {
        return clueRemarkMapper.selectClueRemarkForDetailByClueId(clueId);
    }

    @Override
    public int saveCreateClueRemark(ClueRemark clueRemark) {
        return clueRemarkMapper.insertClueRemark(clueRemark);
    }

    @Override
    public int deleteClueRemarkById(String id) {
        return clueRemarkMapper.deleteClueRemark(id);
    }

    @Override
    public int saveEditClueRemark(ClueRemark clueRemark) {
        return clueRemarkMapper.updateClueRemark(clueRemark);
    }
}
