package com.zxd.blackt.blackt.Fragment;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.Toast;

import com.zxd.blackt.blackt.R;

import java.io.IOException;

public class PlayMusicFragment extends Fragment implements View.OnClickListener {

    private ImageView iv_frount;
    private ImageView iv_stop;
    private ImageView iv_play;
    private ImageView iv_next;
    private ImageView iv_lrc;
    private ImageView iv_sound;
    private MediaPlayer mediaPlayer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_playmusic, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        iv_frount = (ImageView) view.findViewById(R.id.iv_frount);
        iv_stop = (ImageView) view.findViewById(R.id.iv_stop);
        iv_play = (ImageView) view.findViewById(R.id.iv_play);
        iv_next = (ImageView) view.findViewById(R.id.iv_next);
        iv_lrc = (ImageView) view.findViewById(R.id.iv_lrc);
        iv_sound = (ImageView) view.findViewById(R.id.iv_sound);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        onClick();
    }

    /**
     * 点击事件
     */
    private void onClick() {
        iv_frount.setOnClickListener(this);
        iv_lrc.setOnClickListener(this);
        iv_next.setOnClickListener(this);
        iv_play.setOnClickListener(this);
        iv_sound.setOnClickListener(this);
        iv_stop.setOnClickListener(this);
    }

    /**
     * 获取数据
     */
    private void initData() {
        /**
         * 通过歌曲id获取歌词
         * 通过songurl进行歌曲播放
         * 通过downurl进行下载歌曲
         */
        String songurl = getArguments().getString("songurl", null);
        if (songurl != null || songurl != "") {
            mediaPlayer = new MediaPlayer();
            try {
                mediaPlayer.setDataSource(songurl);
                mediaPlayer.prepare();
                mediaPlayer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(getActivity(), "歌曲名称错误", Toast.LENGTH_SHORT).show();
        }

        int songid = getArguments().getInt("sid", 0);
        if (songid != 0) {
            Log.d("-----", "" + songid);
        } else {
            Toast.makeText(getActivity(), "获取歌词失败", Toast.LENGTH_SHORT).show();
        }

        String downurl = getArguments().getString("downurl", null);
        if (downurl != null) {

        } else {
            Toast.makeText(getActivity(), "下载链接获取错误", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_stop:
                mediaPlayer.pause();
                iv_stop.setVisibility(View.GONE);
                iv_play.setVisibility(View.VISIBLE);
                break;
            case R.id.iv_play:
                mediaPlayer.start();
                iv_play.setVisibility(View.GONE);
                iv_stop.setVisibility(View.VISIBLE);
                break;
        }
    }
}
