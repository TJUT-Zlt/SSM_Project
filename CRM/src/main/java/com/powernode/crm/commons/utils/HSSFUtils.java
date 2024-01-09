package com.powernode.crm.commons.utils;

import org.apache.poi.hssf.usermodel.HSSFCell;

/**
 * ClassName:HSSFUtils
 * Package:com.powernode.crm.commons.utils
 * Description:关于excel文件操作的工具类
 *
 * @Author abel
 * @CreateDate 2023-10-24 16:45
 * @Version 1.0
 */
public class HSSFUtils {
    /**
     * 从指定的HSSFCell对象中获取列的值
     * @param cell
     * @return
     */
    public static String getCellValueForStr(HSSFCell cell){
        String ret = "";
        if(cell.getCellType()==HSSFCell.CELL_TYPE_STRING){
            ret = cell.getStringCellValue();
        }else if(cell.getCellType()==HSSFCell.CELL_TYPE_NUMERIC){
            ret = cell.getNumericCellValue() + "";
        }else if(cell.getCellType()==HSSFCell.CELL_TYPE_BOOLEAN){
            ret = cell.getBooleanCellValue() + "";
        }else if(cell.getCellType()==HSSFCell.CELL_TYPE_FORMULA){
            ret = cell.getCellFormula();
        }else{
            ret = "";
        }

        return ret;
    }
}
