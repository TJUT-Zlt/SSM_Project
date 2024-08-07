package com.powernode.crm.workbench.service.impl;

import com.powernode.crm.workbench.domain.TranHistory;
import com.powernode.crm.workbench.mapper.TranHistoryMapper;
import com.powernode.crm.workbench.mapper.TranRemarkMapper;
import com.powernode.crm.workbench.service.TranHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName:TranHistoryServiceImpl
 * Package:com.powernode.crm.workbench.service.impl
 * Description:
 *
 * @Author abel
 * @CreateDate 2023-11-06 16:30
 * @Version 1.0
 */
@Service
public class TranHistoryServiceImpl implements TranHistoryService {
    @Autowired
    private TranHistoryMapper tranHistoryMapper;

    @Override
    public List<TranHistory> queryTranHistoryForDetailByTranId(String tranId) {
        return tranHistoryMapper.selectTranHistoryForDetailByTranId(tranId);
    }
}
