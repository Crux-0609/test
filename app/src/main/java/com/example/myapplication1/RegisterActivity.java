package com.example.myapplication1;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication1.service.UserService;

public class RegisterActivity extends AppCompatActivity {
    EditText id;
    EditText password;
    EditText name;
    RadioGroup rgroup;
    Button register;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        findViews();
        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String id= RegisterActivity.this.id.getText().toString().trim();
                String pass=password.getText().toString().trim();
                String namestr= RegisterActivity.this.name.getText().toString().trim();
                String userstr=((RadioButton)RegisterActivity.this.findViewById(rgroup.getCheckedRadioButtonId())).getText().toString();
                Log.i("TAG",id+"_"+pass+"_"+namestr+"_"+userstr);

                int i=0;
                for (i = 0; i < rgroup.getChildCount(); i++) {
                    RadioButton rd = (RadioButton) rgroup.getChildAt(i);
                    if (rd.isChecked()) {
                        Toast.makeText(getApplicationContext(), "点击提交按钮,获取你选择的是:" + rd.getText(), Toast.LENGTH_LONG).show();
                        break;
                    }
                }

                UserService uService=new UserService(RegisterActivity.this);
                User user=new User();
                user.setId(id);
                user.setPassword(pass);
                user.setUsername(namestr);
                //user.setSex(sexstr);
                uService.register(user,i);
                Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_LONG).show();
            }
        });
    }
    private void findViews() {
        id =(EditText) findViewById(R.id.readText);
        password=(EditText) findViewById(R.id.passwordRegister);
        name =(EditText) findViewById(R.id.nameRegister);
        rgroup=(RadioGroup) findViewById(R.id.userRegister);
        register=(Button) findViewById(R.id.Register);

        rgroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

            }
        });

    }

}
