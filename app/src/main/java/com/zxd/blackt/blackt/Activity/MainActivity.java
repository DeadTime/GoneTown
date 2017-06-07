package com.zxd.blackt.blackt.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.zxd.blackt.blackt.Fragment.AllNoteFragment;
import com.zxd.blackt.blackt.Fragment.NewsFragment;
import com.zxd.blackt.blackt.Fragment.NoteFragment;
import com.zxd.blackt.blackt.R;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private SharedPreferences sp;
    private boolean login_status;

    private FragmentManager fm;
    private FragmentTransaction ft;

    private FloatingActionButton fabtn_setting;//设置
    private FloatingActionButton fabtn_music;//音乐
    private FloatingActionButton fabtn_news;//新闻
    private FloatingActionButton fabtn_notes;//日记本
    private FloatingActionButton fabtn_pen;//笔
    private FloatingActionMenu fam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getStatus();

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
    }

    private void home() {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.rll, new NewsFragment(), "news");
        ft.commit();
    }

    private void getStatus() {
        sp = getSharedPreferences("SharedPreferences", 0);
        login_status = sp.getBoolean("login_status", false);
        if (!login_status) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
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
                Toast.makeText(this, "音乐", Toast.LENGTH_SHORT).show();
                break;
            case R.id.fabtn_news:
                fam.close(true);
                Toast.makeText(this, "新闻", Toast.LENGTH_SHORT).show();
                break;
            case R.id.fabtn_pen:
                fam.close(true);
                Toast.makeText(this, "铅笔", Toast.LENGTH_SHORT).show();
                ft = fm.beginTransaction();
                ft.replace(R.id.rll, new NoteFragment(), "note");
                ft.commit();
                break;
            case R.id.fabtn_notes:
                fam.close(true);
                Toast.makeText(this, "本子", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
