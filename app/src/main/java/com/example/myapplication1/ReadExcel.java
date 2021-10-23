package com.example.myapplication1;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadExcel extends AppCompatActivity {



    public ReadExcel(String toString) throws IOException {
        String name=toString;
        File xlsFile = new File("data/data/com.example.myapplication1/"+name+".xls");

        // 工作表
        Workbook workbook = WorkbookFactory.create(xlsFile);

        // 表个数。
        int numberOfSheets = workbook.getNumberOfSheets();

        // 遍历表。
        for (int i = 0; i < numberOfSheets; i++) {
            Sheet sheet = workbook.getSheetAt(i);

            // 行数。
            int rowNumbers = sheet.getLastRowNum() + 1;

            // Excel第一行。
            Row temp = sheet.getRow(0);
            if (temp == null) {
                continue;
            }

            int cells = temp.getPhysicalNumberOfCells();

            // 读数据。
            for (int row = 0; row < rowNumbers; row++) {
                Row r = sheet.getRow(row);
                for (int col = 0; col < cells; col++) {
                    System.out.print(r.getCell(col).toString()+" ");
                }

                // 换行。
                System.out.println();
            }
        }
    }
}

    /*void ReadExcel(String str) throws IOException {
        String name=str;
        File xlsFile = new File("data/data/com.example.myapplication1/"+name+".xls");

        // 工作表
        Workbook workbook = WorkbookFactory.create(xlsFile);

        // 表个数。
        int numberOfSheets = workbook.getNumberOfSheets();

        // 遍历表。
        for (int i = 0; i < numberOfSheets; i++) {
            Sheet sheet = workbook.getSheetAt(i);

            // 行数。
            int rowNumbers = sheet.getLastRowNum() + 1;

            // Excel第一行。
            Row temp = sheet.getRow(0);
            if (temp == null) {
                continue;
            }

            int cells = temp.getPhysicalNumberOfCells();

            // 读数据。
            for (int row = 0; row < rowNumbers; row++) {
                Row r = sheet.getRow(row);
                for (int col = 0; col < cells; col++) {
                    System.out.print(r.getCell(col).toString()+" ");
                }

                // 换行。
                System.out.println();
            }
        }
    }*/



    /*public static void main(String[] args) throws Exception {
    //ReadExcel(String str) throws IOException {
        String name="excel";
        File xlsFile = new File("data/data/com.example.myapplication1/"+name+".xls");

        // 工作表
        Workbook workbook = WorkbookFactory.create(xlsFile);

        // 表个数。
        int numberOfSheets = workbook.getNumberOfSheets();

        // 遍历表。
        for (int i = 0; i < numberOfSheets; i++) {
            Sheet sheet = workbook.getSheetAt(i);

            // 行数。
            int rowNumbers = sheet.getLastRowNum() + 1;

            // Excel第一行。
            Row temp = sheet.getRow(0);
            if (temp == null) {
                continue;
            }

            int cells = temp.getPhysicalNumberOfCells();

            // 读数据。
            for (int row = 0; row < rowNumbers; row++) {
                Row r = sheet.getRow(row);
                for (int col = 0; col < cells; col++) {
                    System.out.print(r.getCell(col).toString()+" ");
                }

                // 换行。
                System.out.println();
            }
        }
    }

}
*/