package com.powernode.crm.settings.service;

import com.powernode.crm.settings.domain.DicType;

import java.util.List;

/**
 * ClassName:DicTypeService
 * Package:com.powernode.crm.settings.service
 * Description:
 *
 * @Author abel
 * @CreateDate 2023-11-06 19:16
 * @Version 1.0
 */
public interface DicTypeService {

    List<DicType> queryAllDicType();
}
