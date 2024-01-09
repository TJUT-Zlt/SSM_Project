package com.powernode.crm.settings.service.impl;

import com.powernode.crm.settings.domain.DicType;
import com.powernode.crm.settings.mapper.DicTypeMapper;
import com.powernode.crm.settings.service.DicTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName:DicTypeServiceImpl
 * Package:com.powernode.crm.settings.service.impl
 * Description:
 *
 * @Author abel
 * @CreateDate 2023-11-06 19:16
 * @Version 1.0
 */
@Service("dicTypeService")
public class DicTypeServiceImpl implements DicTypeService {

    @Autowired
    private DicTypeMapper dicTypeMapper;

    @Override
    public List<DicType> queryAllDicType() {
        return dicTypeMapper.selectAllDicType();
    }
}
