package com.example.myapplication1;



import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication1.service.UserService;

import pl.com.salsoft.sqlitestudioremote.SQLiteStudioService;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//即activity_main.xml
        SQLiteStudioService.instance().start(this);
        findViews();

    }

    private EditText username;
    private EditText password;
    private Button login;
    private Button register;
    private Button reset;
    private TextView tv;
    private RadioGroup rgroup;
    private RadioButton stu;
    private RadioButton tch;


        private void findViews() {
        username = (EditText) findViewById(R.id.text_userid);
        password = (EditText) findViewById(R.id.text_userpwd);
        login = (Button) findViewById(R.id.login);
        register = (Button) findViewById(R.id.register);
        reset=(Button)findViewById(R.id.reset);
        rgroup=(RadioGroup)findViewById(R.id.radioButton) ;
        stu=(RadioButton)findViewById(R.id.radioButton1) ;
        tch=(RadioButton)findViewById(R.id.radioButton2) ;


        rgroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);*/
                String name=username.getText().toString();
                System.out.println(name);
                String pass=password.getText().toString();
                System.out.println(pass);
                int i=0;
                for (i = 0; i < rgroup.getChildCount(); i++) {
                    RadioButton rd = (RadioButton) rgroup.getChildAt(i);
                    if (rd.isChecked()) {
                        //Toast.makeText(getApplicationContext(), "点击提交按钮,获取你选择的是:" + rd.getText(), Toast.LENGTH_LONG).show();
                        break;
                    }
                }


                Log.i("TAG",name+"_"+pass);
                UserService uService=new UserService(MainActivity.this);
                boolean flag=uService.login(name, pass,i);

                if(flag){
                    Log.i("TAG","登录成功");
                    Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                    startActivity(intent);
                }else{
                    Log.i("TAG","登录失败");
                    Toast.makeText(MainActivity.this, "登录失败", Toast.LENGTH_LONG).show();
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ResetActivity.class);
                startActivity(intent);
            }
        });



    }
}


