package com.powernode.crm.settings.service;

import com.powernode.crm.settings.domain.DicType;
import com.powernode.crm.settings.domain.DicValue;

import java.util.List;

/**
 * ClassName:ClueService
 * Package:com.powernode.crm.settings.service
 * Description:
 *
 * @Author abel
 * @CreateDate 2023-10-30 10:06
 * @Version 1.0
 */
public interface DicValueService {

    List<DicValue> queryDicValueByTypeCode(String typeCode);

    List<DicValue> queryAllDicValue();

}
