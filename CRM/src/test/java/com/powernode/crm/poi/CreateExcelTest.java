package com.powernode.crm.poi;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.junit.Test;

import java.io.FileOutputStream;

import java.io.IOException;
import java.io.OutputStream;


/**
 * ClassName:CreateExcelTest
 * Package:com.powernode.crm.poi
 * Description:
 *
 * @Author abel
 * @CreateDate 2023-10-23 17:00
 * @Version 1.0
 */
public class CreateExcelTest {


    @Test
    public void CreateExcel() throws Exception {
        //创建HSSFWorkbook对象，对应一个excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        //使用wb创建HSSFSheet对象 ，对应wb文件中的一页
        HSSFSheet sheet = wb.createSheet("学生列表");
        //使用sheet创建HSSFRow对象，对应sheet中的一行
        HSSFRow row = sheet.createRow(0);

        //使用row创建HSSFCell对象，对应row中的列
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("学号");

        cell = row.createCell(1);
        cell.setCellValue("姓名");

        cell = row.createCell(2);
        cell.setCellValue("年龄");

        //生成HSSFCellStyle对象
        HSSFCellStyle style=wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);


        for(int i = 1;i < 10;i++){
            row =sheet.createRow(i);

            cell= row.createCell(0);
            cell.setCellValue(100+i);

            cell= row.createCell(1);
            cell.setCellValue("NAME"+i);

            cell = row.createCell(2);
            cell.setCellValue(20+i);
        }

        //调用工具函数生成excel文件
        OutputStream os = new FileOutputStream("D:\\WorkSpace\\source\\studentList.xls");
        wb.write(os);
        os.close();
        wb.close();

    }
}
