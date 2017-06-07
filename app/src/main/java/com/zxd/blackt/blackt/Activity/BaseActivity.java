package com.zxd.blackt.blackt.Activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.Toast;

/**
 * Created by zhuangxd on 2017/5/4.
 */

public class BaseActivity extends AppCompatActivity{

    boolean flag = true;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (flag) {
                Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
                flag = false;
                return false;
            } else {
                finish();
                System.exit(0);
                flag = true;
                return true;
            }
        }
        return false;
    }
}
