package com.example.myapplication1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class HomeActivity extends AppCompatActivity {
    private Button bt;
    private TextView tv;
    private Button bt1;
    private EditText et;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);//即activity_home.xml
        //findViews();
        bt=(Button) findViewById(R.id.buttonExport);
        tv=(TextView) findViewById(R.id.textView4);
        bt1=(Button)findViewById(R.id.buttonRead);
        et=(EditText)findViewById(R.id.readText);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent=new Intent(HomeActivity.this,ExportExcel.class);
                startActivity(intent);

            }
        });

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str=et.getText().toString();
                try {

                    new ReadExcel(str);
                    Log.i("TAG","读取成功");
                    Toast.makeText(HomeActivity.this, "读取"+str+"(.xls)文件成功", Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(HomeActivity.this,ReadExcel.class);
                    startActivity(intent);
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(HomeActivity.this,"读取"+str+"(.xls)文件失败，请检查文件名",Toast.LENGTH_LONG).show();
                }


            }
        });
    }


}
