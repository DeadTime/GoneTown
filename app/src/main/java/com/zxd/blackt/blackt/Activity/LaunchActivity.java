package com.zxd.blackt.blackt.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import com.zxd.blackt.blackt.R;

public class LaunchActivity extends BaseActivity {

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                Intent intent = new Intent(LaunchActivity.this, HomeActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.tra_frount, R.anim.tra_back);
                finish();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        handler.sendEmptyMessageDelayed(1, 3000);
    }

}
