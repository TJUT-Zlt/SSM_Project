package com.powernode.crm.poi;

import com.powernode.crm.commons.utils.HSSFUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * ClassName:ParseExcelTest
 * Package:com.powernode.crm.poi
 * Description:
 *
 * @Author abel
 * @CreateDate 2023-10-24 16:38
 * @Version 1.0
 */
public class ParseExcelTest {
    @Test
    public void ParseExcel() throws Exception {
        //根据excel文件生成HSSFWorkbook对象，封装了excel文件的所有信息
        InputStream is = new FileInputStream("D:\\WorkSpace\\source\\studentList.xls");
        //根据wb获取HSSFSheet对象，封装了一页的所有信息
        HSSFWorkbook wb = new HSSFWorkbook(is);
        HSSFSheet sheet = wb.getSheetAt(0);//页的下标，下标从0开始，依次增加
        //根据sheet获取HSSFRow对象，封装了一行的所有信息
        HSSFRow row = null;
        HSSFCell cell = null;
        for(int i = 0;i <= sheet.getLastRowNum();i++){//sheet.getLastRowNum()：最后一行的下标
            row = sheet.getRow(i);
            for(int j = 0;j < row.getLastCellNum();j++){//row.getLastCellNum():最后一列的下标+1
                //根据row获取HSSFCell对象，封装了一列的所有信息
                cell = row.getCell(j);//列的下标，下标从0开始，依次增加

                System.out.print(HSSFUtils.getCellValueForStr(cell) + " ");
            }
            System.out.println();

        }
    }



}
