package com.example.myapplication1;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication1.service.DatabaseHelper;
import com.example.myapplication1.service.UserService;
import com.google.android.material.textfield.TextInputLayout;

public class ResetActivity extends AppCompatActivity {
    EditText id;
    EditText oldpassword;
    EditText password0;
    EditText name;
    EditText password1;
    RadioGroup rgroup;
    Button register;
    private TextInputLayout textInputLayout;
    private  SQLiteDatabase db;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);
        findViews();

        UserService uService=new UserService(ResetActivity.this);
        register.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("Range")
            public void onClick(View v) {
                String id= ResetActivity.this.id.getText().toString().trim();
                String oldpass=oldpassword.getText().toString().trim();
                String pass0= password0.getText().toString().trim();
                String pass1= password1.getText().toString().trim();
                //String namestr= ResetActivity.this.name.getText().toString().trim();
                //String userstr=((RadioButton)ResetActivity.this.findViewById(rgroup.getCheckedRadioButtonId())).getText().toString();
                String true_password="";

                int i=0;
                for (i = 0; i < rgroup.getChildCount(); i++) {
                    RadioButton rd = (RadioButton) rgroup.getChildAt(i);
                    if (rd.isChecked()) {
                        //Toast.makeText(getApplicationContext(), "??????????????????,?????????????????????:" + rd.getText(), Toast.LENGTH_LONG).show();
                        break;
                    }
                }

                if (TextUtils.isEmpty(id) || TextUtils.isEmpty(oldpass) || TextUtils.isEmpty(pass0)
                        || TextUtils.isEmpty(pass1)) {
                    Toast.makeText(ResetActivity.this, "???????????????", Toast.LENGTH_SHORT).show();
                } else {
                    Cursor cursor;
                    if(i==0){
                        //??????????????????
                         cursor = db.query("user_student", null, "id = ?", new String[]{id}, null, null, null);
                    }else{
                         cursor = db.query("user_teacher", null, "id = ?", new String[]{id}, null, null, null);
                    }

                    //???????????????????????????
                    if (cursor.getCount() == 0) {
                        Toast.makeText(ResetActivity.this, "?????????????????????", Toast.LENGTH_SHORT).show();
                    } else {
                        while (cursor.moveToNext()) {
                            true_password = cursor.getString(cursor.getColumnIndex("password"));
                        }

                        //?????????????????????
                        if (!oldpass.equals(true_password)) {
                            Toast.makeText(ResetActivity.this, "??????????????????", Toast.LENGTH_SHORT).show();
                        } else {
                            //????????????????????????????????????
                            if (!pass0.equals(pass1)) {
                                Toast.makeText(ResetActivity.this, "?????????????????????????????????", Toast.LENGTH_SHORT).show();
                            } else {
                                ContentValues values = new ContentValues();
                                values.put("password", pass0);
                                //???????????????
                                db.update("user_student", values, "id = ? ", new String[]{id});

                                Toast.makeText(ResetActivity.this, "???????????????", Toast.LENGTH_SHORT).show();

                                startActivity(new Intent(ResetActivity.this, MainActivity.class));
                            }
                        }

                    }


                }
                //????????????????????????
                /*password0.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        if (textInputLayout.getCounterMaxLength() < password0.length()) {
                            textInputLayout.setError("?????????????????????");
                        } else {
                            textInputLayout.setErrorEnabled(false);
                        }
                    }
                });*/
            }
        });
    }
    class CommonDatabase {
        private DatabaseHelper dbHelper;
        private SQLiteDatabase sqlite;

        public SQLiteDatabase getSqliteObject(Context context) {
            dbHelper = new DatabaseHelper(context);
            sqlite = dbHelper.getWritableDatabase();
            return sqlite;
        }

    }

    private void findViews() {
        id =(EditText) findViewById(R.id.readText);
        oldpassword=(EditText)findViewById(R.id.password) ;
        password0 =(EditText) findViewById(R.id.passwordRegister);
        name =(EditText) findViewById(R.id.nameRegister);
        password1 =(EditText)findViewById(R.id.passwordRegister1) ;
        rgroup=(RadioGroup) findViewById(R.id.userRegister);
        register=(Button) findViewById(R.id.Register);
        db = new CommonDatabase().getSqliteObject(ResetActivity.this);
        rgroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

            }
        });

    }
}
