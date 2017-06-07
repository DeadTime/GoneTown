package com.zxd.blackt.blackt.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.AbsoluteSizeSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zxd.blackt.blackt.R;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private SharedPreferences sp;
    private SharedPreferences.Editor ed;
    private EditText et_user;
    private EditText et_pwd;
    private Button btn_login;
    private Button btn_regist;
    private Button btn_other;
    private String user;
    private String pwd;
    private boolean flag = true;
    private boolean flag2 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getStatus();
        initView();
    }

    //及时检测是否为空
    private void checkEmpty() {
        et_user.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (flag) {
                    flag = false;
                } else {
                    if (hasFocus) {
                        Log.d("++++++", hasFocus + user);
                        if (pwd.equals("") || pwd == null) {
                            SpannableString ss = new SpannableString("请输入密码");
                            AbsoluteSizeSpan ass = new AbsoluteSizeSpan(15, true);
                            ss.setSpan(ass, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            et_pwd.setHint(ss);
                        }
                    } else {
                        et_pwd.setHint("");
                    }
                }
            }
        });
        et_pwd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (flag2) {
                    flag2 = true;
                } else {
                    if (hasFocus) {
                        Log.d("++++++", hasFocus + user);
                        if (user.equals("") || user == null) {
                            SpannableString ss2 = new SpannableString("请输入用户名");
                            AbsoluteSizeSpan ass = new AbsoluteSizeSpan(15, true);
                            ss2.setSpan(ass, 0, ss2.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            et_user.setHint(ss2);
                        }
                    } else {
                        et_user.setHint("");
                    }
                }
            }
        });
    }

    private boolean isEmpty() {
        user = et_user.getText().toString();
        pwd = et_pwd.getText().toString();
        if (user.equals("") || user == null) {
            checkEmpty();
            return false;
        } else if (pwd.equals("") || pwd == null) {
            checkEmpty();
            return false;
        } else {
            return true;
        }
    }

    private void initView() {
        et_user = (EditText) findViewById(R.id.username);
        et_pwd = (EditText) findViewById(R.id.password);
        btn_login = (Button) findViewById(R.id.button);
        btn_regist = (Button) findViewById(R.id.button2);
        btn_other = (Button) findViewById(R.id.btn_other);
        btn_login.setOnClickListener(this);
        btn_regist.setOnClickListener(this);
        btn_other.setOnClickListener(this);
    }

    private void getStatus() {
        sp = getSharedPreferences("SharedPreferences", 0);
        ed = sp.edit();
        ed.putBoolean("login_status", true);
        ed.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                if (isEmpty() == true) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    checkEmpty();
                }
                break;
            case R.id.button2:
                break;
            case R.id.btn_other:
                break;
        }
    }
}
