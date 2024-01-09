package com.powernode.crm.settings.service.impl;

import com.powernode.crm.settings.domain.DicValue;
import com.powernode.crm.settings.mapper.DicValueMapper;
import com.powernode.crm.settings.service.DicValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName:ClueServiceImpl
 * Package:com.powernode.crm.settings.service.impl
 * Description:
 *
 * @Author abel
 * @CreateDate 2023-10-30 10:06
 * @Version 1.0
 */
@Service("dicValueService")
public class DicValueServiceImpl implements DicValueService {

    @Autowired
    private DicValueMapper dicValueMapper;

    @Override
    public List<DicValue> queryDicValueByTypeCode(String typeCode) {
        return dicValueMapper.selectDicValueByTypeCode(typeCode);
    }

    @Override
    public List<DicValue> queryAllDicValue() {
        return dicValueMapper.selectAllDicValue();
    }
}
