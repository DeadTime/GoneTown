package com.zxd.blackt.blackt.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.zxd.blackt.blackt.Adapter.MusicAdapter;
import com.zxd.blackt.blackt.Entity.PlayMusic;
import com.zxd.blackt.blackt.Entrance.Entrances;
import com.zxd.blackt.blackt.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnFocusChange;
import rx.Subscriber;

public class MusicActivity extends BaseActivity {

    @BindView(R.id.edt_search_music)
    EditText edt_search_music;
    @BindView(R.id.iv_search_click)
    ImageView iv_search_click;
    @BindView(R.id.rll_search)
    RelativeLayout rllSearch;
    @BindView(R.id.rlv_musics)
    RecyclerView rlv_musics;
    private MusicAdapter musicAdapter;
    @OnFocusChange(R.id.edt_search_music)
    void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            // 获得焦点
            edt_search_music.requestFocus();
            showButton();
        } else {
            // 失去焦点
            edt_search_music.clearFocus();
            goneButton();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        ButterKnife.bind(this);
    }

    private void showButton() {
        iv_search_click.setVisibility(View.VISIBLE);
        iv_search_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search = edt_search_music.getText().toString().trim();
                getMusicData(search);
            }
        });
    }

    private void goneButton() {
        iv_search_click.setVisibility(View.GONE);
    }

    private void getMusicData(String q) {
        Entrances entrances = Entrances.getEntrances();
        String appid = entrances.getSHOWAPPID();
        String appsign = entrances.getSHOWSIGN();
        entrances.playMusic(new Subscriber<PlayMusic>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(MusicActivity.this, "未知错误", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(PlayMusic playMusic) {
                String errorcode = playMusic.getShowapi_res_code();
                if (errorcode.equals("0")) {
                    PlayMusic.ResBody rb = playMusic.getShowapi_res_body();
                    PlayMusic.PageBean pb = rb.getPagebean();
                    List<PlayMusic.Song> slist = pb.getContentlist();
                    rlv_musics.addItemDecoration(new DividerItemDecoration(MusicActivity.this, DividerItemDecoration.VERTICAL));
                    rlv_musics.setLayoutManager(new LinearLayoutManager(MusicActivity.this));
                    musicAdapter = new MusicAdapter(MusicActivity.this, slist);
                    rlv_musics.setAdapter(musicAdapter);
                } else {
                    Toast.makeText(MusicActivity.this, playMusic.getShowapi_res_error(), Toast.LENGTH_SHORT).show();
                }
            }
        }, appid, appsign, q);
    }

}
