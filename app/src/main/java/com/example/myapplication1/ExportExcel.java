
package com.example.myapplication1;

import android.content.Context;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;






import com.example.myapplication1.service.MySQLiteOpenHelper;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;




public class ExportExcel extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SQLiteDatabase sqLiteDatabase = MySQLiteOpenHelper.getInstance(this).getWritableDatabase();

        ContentValues contentValues1 = getContentValues("zhang", "男", 18);
        ContentValues contentValues2 = getContentValues("phil", "男", 19);

        //往SQLite数据库中插入两条数据。
        sqLiteDatabase.insert(MySQLiteOpenHelper.TABLE_NAME, null, contentValues1);
        sqLiteDatabase.insert(MySQLiteOpenHelper.TABLE_NAME, null, contentValues2);
        sqLiteDatabase.close();





        //从SQLite数据库中读出数据。
        List<Student> students = query(MySQLiteOpenHelper.getInstance(this).getReadableDatabase());

        HSSFWorkbook mWorkbook = new HSSFWorkbook();
        HSSFSheet mSheet = mWorkbook.createSheet(MySQLiteOpenHelper.TABLE_NAME);
        createExcelHead(mSheet);

        for (Student student : students) {
            System.out.println(student.id + "," + student.name + "," + student.gender + "," + student.age);
            createCell(student.id, student.name, student.gender, student.age, mSheet);
        }
        //检查权限
        //checkPermission();

        //ExportExcel();
        //File xlsFile = new File(Environment.getExternalStorageDirectory(), "/excel.xls");
        //输入文件名name
        String name="excel";

        File xlsFile = new File("data/data/com.example.myapplication1/"+name+".xls");

        try {
            if (!xlsFile.exists()) {
                xlsFile.createNewFile();
            }
            mWorkbook.write(xlsFile);// 或者以流的形式写入文件 mWorkbook.write(new FileOutputStream(xlsFile));
            //mWorkbook.write(new FileOutputStream(xlsFile));
            Toast.makeText(ExportExcel.this,"文件存储至：data/data/com.example.myapplication1/"+name+".xls",Toast.LENGTH_SHORT).show();
            mWorkbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ContentValues getContentValues(String name, String gender, int age) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MySQLiteOpenHelper.STUDENT_NAME, name);
        contentValues.put(MySQLiteOpenHelper.STUDENT_GENDER, gender);
        contentValues.put(MySQLiteOpenHelper.STUDENT_AGE, age);
        return contentValues;
    }

    //查询SQLite数据库。读出所有数据内容。
    @SuppressLint("Range")
    private List<Student> query(SQLiteDatabase db) {
        List<Student> students = null;

        Cursor cursor = db.rawQuery("SELECT * FROM " + MySQLiteOpenHelper.TABLE_NAME, null);
        if (cursor != null && cursor.getCount() > 0) {

            students = new ArrayList<>();

            while (cursor.moveToNext()) {
                Student student = new Student();

                student.id = cursor.getInt(cursor.getColumnIndex(MySQLiteOpenHelper.STUDENT_ID));
                student.name = cursor.getString(cursor.getColumnIndex(MySQLiteOpenHelper.STUDENT_NAME));
                student.gender = cursor.getString(cursor.getColumnIndex(MySQLiteOpenHelper.STUDENT_GENDER));
                student.age = cursor.getInt(cursor.getColumnIndex(MySQLiteOpenHelper.STUDENT_AGE));

                students.add(student);
            }

            cursor.close();
        }

        db.close();

        return students;
    }

    //数据容器，装载从数据库中读出的数据内容。
    private class Student {
        public int id;
        public String name;
        public String gender;
        public int age;
    }

    // 创建Excel标题行，第一行。
    private void createExcelHead(HSSFSheet mSheet) {
        HSSFRow headRow = mSheet.createRow(0);
        headRow.createCell(0).setCellValue(MySQLiteOpenHelper.STUDENT_ID);
        headRow.createCell(1).setCellValue(MySQLiteOpenHelper.STUDENT_NAME);
        headRow.createCell(2).setCellValue(MySQLiteOpenHelper.STUDENT_GENDER);
        headRow.createCell(3).setCellValue(MySQLiteOpenHelper.STUDENT_AGE);
    }

    // 创建Excel的一行数据。
    private static void createCell(int id, String name, String gender, int age, HSSFSheet sheet) {
        HSSFRow dataRow = sheet.createRow(sheet.getLastRowNum() + 1);

        dataRow.createCell(0).setCellValue(id);
        dataRow.createCell(1).setCellValue(name);
        dataRow.createCell(2).setCellValue(gender);
        dataRow.createCell(3).setCellValue(age);
    }

    private static final String TAG="Read";
    public static final int CODE_READ_SMS= 100;
    private  void checkPermission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//6.0以上

            int permission = ActivityCompat.checkSelfPermission(getApplication(), Manifest.permission.RECEIVE_SMS);
            int permission1 = ActivityCompat.checkSelfPermission(getApplication(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if(permission != PackageManager.PERMISSION_GRANTED && permission1 != PackageManager.PERMISSION_GRANTED) {
                Log.e(TAG,"没有获取权限，请申请");
                // 申请一个（或多个）权限，并提供用于回调返回的获取码（用户定义)
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.RECEIVE_SMS) && ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)) {//这里可以写个对话框之类的项向用户解释为什么要申请权限，并在对话框的确认键后续再次申请权限

                    Log.e(TAG,"提示");
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.RECEIVE_SMS,Manifest.permission.WRITE_EXTERNAL_STORAGE}, CODE_READ_SMS);
                } else {
                    //申请权限，字符串数组内是一个或多个要申请的权限，1是申请权限结果的返回参数，在onRequestPermissionsResult可以得知申请结果

                    Log.e(TAG,"您已禁止");
                    Toast.makeText(ExportExcel.this,"没有获取读取手机权限，请到应用中心手动打开该权限",Toast.LENGTH_SHORT).show();

                }
            }else{
                Log.e(TAG,"获取到了权限");
            }
        }else{
            Log.e(TAG,"获取到了权限");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == CODE_READ_SMS){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.e(TAG,"获取到了权限");
            } else{
                // 没有获取到权限，做特殊处理
                Log.e(TAG,"没有获取到权限");
                Toast.makeText(ExportExcel.this,"没有获取读取手机权限，请到应用中心手动打开该权限",Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void ExportExcel(){
        int size = 10;
        Workbook wb = new HSSFWorkbook();
        Sheet sh = wb.createSheet();

        String[] str = new String[]{"one","two","three","four","five","six","seven","eight","nine","ten"};
        String filename = "textExcel.xls";

        for(int rownum=0;rownum<size;rownum++){
            Row row = sh.createRow(rownum);
            for(int cellnum=0;cellnum<10;cellnum++){
                Cell cell = row.createCell(cellnum);
                cell.setCellValue(str[cellnum]);
            }
        }

        try {
            FileOutputStream fos = openFileOutput(filename, Context.MODE_PRIVATE);
            wb.write(fos);
            fos.close();
            Toast.makeText(getApplicationContext(),"导出成功",Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}




