package com.powernode.crm.workbench.service.impl;

import com.powernode.crm.workbench.domain.TranRemark;
import com.powernode.crm.workbench.mapper.TranRemarkMapper;
import com.powernode.crm.workbench.service.TranRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName:TranRemarkServiceImpl
 * Package:com.powernode.crm.workbench.service.impl
 * Description:
 *
 * @Author abel
 * @CreateDate 2023-11-06 16:24
 * @Version 1.0
 */
@Service("tranRemarkService")
public class TranRemarkServiceImpl implements TranRemarkService {

    @Autowired
    private TranRemarkMapper tranRemarkMapper;

    @Override
    public List<TranRemark> queryTranRemarkForDetailByTranId(String tranId) {
        return tranRemarkMapper.selectTranRemarkForDetailByTranId(tranId);
    }

    @Override
    public int saveCreateTranRemark(TranRemark tranRemark) {
        return tranRemarkMapper.insertTranRemark(tranRemark);
    }

    @Override
    public int deleteTranRemarkById(String id) {
        return tranRemarkMapper.deleteTranRemark(id);
    }

    @Override
    public int saveEditTranRemark(TranRemark tranRemark) {
        return tranRemarkMapper.updateTranRemark(tranRemark);
    }
}
