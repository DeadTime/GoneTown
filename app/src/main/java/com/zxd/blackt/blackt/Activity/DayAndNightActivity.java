package com.zxd.blackt.blackt.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.zxd.blackt.blackt.Customs.AnimationImageView;
import com.zxd.blackt.blackt.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DayAndNightActivity extends BaseActivity {

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                Intent intent = new Intent(DayAndNightActivity.this, MainActivity.class);
                intent.putExtra("setting", "setting");
                startActivity(intent);
                overridePendingTransition(R.anim.fade_tra, R.anim.fade_trb);
            }
        }
    };
    @BindView(R.id.imageView)
    AnimationImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_and_night);
        ButterKnife.bind(this);
        String isDay = getIntent().getStringExtra("isDay");
        showAnimation(isDay);
    }

    private void showAnimation(String isDay) {

        if (isDay.equals("day")) {

            imageView.loadAnimation(R.drawable.day_animation, new AnimationImageView.OnFrameAnimationListener() {
                @Override
                public void onStart() {
                }

                @Override
                public void onEnd() {
                    handler.sendEmptyMessage(1);
                }
            });

        } else {

            imageView.loadAnimation(R.drawable.night_animation, new AnimationImageView.OnFrameAnimationListener() {
                @Override
                public void onStart() {
                }

                @Override
                public void onEnd() {
                    handler.sendEmptyMessage(1);
                }
            });

        }

    }
}
