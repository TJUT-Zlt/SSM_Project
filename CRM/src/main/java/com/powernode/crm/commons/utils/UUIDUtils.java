package com.powernode.crm.commons.utils;

import java.util.UUID;

/**
 * ClassName:UUIDUtils
 * Package:com.powernode.crm.commons.utils
 * Description: 随机生成id值
 *
 * @Author abel
 * @CreateDate 2023-10-20 10:46
 * @Version 1.0
 */
public class UUIDUtils {

    public static String getUUID(){
        /**
         * 随机生成uuid的值
         */
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
