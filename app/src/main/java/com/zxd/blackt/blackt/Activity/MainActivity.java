package com.zxd.blackt.blackt.Activity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.zxd.blackt.blackt.Application.App;
import com.zxd.blackt.blackt.Event.DayEvent;
import com.zxd.blackt.blackt.Fragment.AllNoteFragment;
import com.zxd.blackt.blackt.Fragment.HotMusicFragment;
import com.zxd.blackt.blackt.Fragment.InfoFragment;
import com.zxd.blackt.blackt.Fragment.MusicFragment;
import com.zxd.blackt.blackt.Fragment.NewsFragment;
import com.zxd.blackt.blackt.Fragment.NoteFragment;
import com.zxd.blackt.blackt.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.rll)
    RelativeLayout rll;
    @BindView(R.id.fabtn_info)
    FloatingActionButton fabtnInfo;
    @BindView(R.id.fabtn_pen)
    FloatingActionButton fabtn_pen;
    @BindView(R.id.fabtn_notes)
    FloatingActionButton fabtn_notes;
    @BindView(R.id.fabtn_music)
    FloatingActionButton fabtn_music;
    @BindView(R.id.fabtn_news)
    FloatingActionButton fabtn_news;
    @BindView(R.id.fabtn_setting)
    FloatingActionButton fabtn_setting;
    @BindView(R.id.menu)
    FloatingActionMenu fam;
    private boolean flag = true;
    private FragmentManager fm;
    private FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initView();

        home();

    }

    private void initView() {
        fam = (FloatingActionMenu) findViewById(R.id.menu);
        fabtn_setting = (FloatingActionButton) findViewById(R.id.fabtn_setting);
        fabtn_music = (FloatingActionButton) findViewById(R.id.fabtn_music);
        fabtn_news = (FloatingActionButton) findViewById(R.id.fabtn_news);
        fabtn_notes = (FloatingActionButton) findViewById(R.id.fabtn_notes);
        fabtn_pen = (FloatingActionButton) findViewById(R.id.fabtn_pen);
        fabtn_setting.setOnClickListener(this);
        fabtn_music.setOnClickListener(this);
        fabtn_news.setOnClickListener(this);
        fabtn_notes.setOnClickListener(this);
        fabtn_pen.setOnClickListener(this);
        fabtnInfo.setOnClickListener(this);

    }

    private void home() {

        Intent intent = getIntent();
        String setting = intent.getStringExtra("setting");
        String news = intent.getStringExtra("news");
        String note = intent.getStringExtra("note");
        String music = intent.getStringExtra("music");
        String info = intent.getStringExtra("info");
        String search = intent.getStringExtra("search");

        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();

        if (setting != null) {
//            ft.replace(R.id.rll, new SettingFragment(), "setting");
        } else if (news != null) {
            ft.replace(R.id.rll, new NewsFragment(), "news");
        } else if (note != null) {
            ft.replace(R.id.rll, new NoteFragment(), "note");
        } else if (music != null) {
            ft.replace(R.id.rll, new HotMusicFragment(), "hotmusic");
        } else if (info != null) {
            ft.replace(R.id.rll, new InfoFragment(), "info");
        } else if (search != null) {
            ft.replace(R.id.rll, new MusicFragment(), "music");
        }

        ft.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fabtn_setting:
                fam.close(true);
                Toast.makeText(this, "设置", Toast.LENGTH_SHORT).show();
                break;
            case R.id.fabtn_music:
                fam.close(true);
                ft = fm.beginTransaction();
                ft.replace(R.id.rll, new HotMusicFragment(), "hotmusic");
                ft.commit();
                break;
            case R.id.fabtn_news:
                fam.close(true);
                ft = fm.beginTransaction();
                ft.replace(R.id.rll, new NewsFragment(), "news");
                ft.commit();
                break;
            case R.id.fabtn_pen:
                fam.close(true);
                ft = fm.beginTransaction();
                ft.replace(R.id.rll, new NoteFragment(), "note");
                ft.commit();
                break;
            case R.id.fabtn_notes:
                fam.close(true);
                SharedPreferences sharedPreferences = getSharedPreferences("isShow", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("isShow", true);
                editor.commit();
                ft = fm.beginTransaction();
                ft.replace(R.id.rll, new AllNoteFragment(), "allnote");
                ft.commit();
                break;
            case R.id.fabtn_info:
                fam.close(true);
                ft = fm.beginTransaction();
                ft.replace(R.id.rll, new InfoFragment(), "info");
                ft.commit();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (flag) {
            Toast.makeText(this, "再次点击即可退出", Toast.LENGTH_SHORT).show();
            flag = false;
        } else {
            super.onBackPressed();
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
            ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
            am.restartPackage(getPackageName());
            finish();
            flag = true;
        }
    }

}
