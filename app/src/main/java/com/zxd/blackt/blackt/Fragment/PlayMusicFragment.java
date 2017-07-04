package com.zxd.blackt.blackt.Fragment;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import com.zxd.blackt.blackt.Customs.LRCView;
import com.zxd.blackt.blackt.Entity.Lrc;
import com.zxd.blackt.blackt.Entrance.Entrances;
import com.zxd.blackt.blackt.R;
import com.zxd.blackt.blackt.Utils.FromHtmlUtils;
import com.zxd.blackt.blackt.Utils.PreferenceUtil;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;
import rx.Subscriber;

public class PlayMusicFragment extends Fragment implements View.OnClickListener, LRCView.OnPlayerClickListener {

    private ImageView iv_frount;
    private ImageView iv_stop;
    private ImageView iv_play;
    private ImageView iv_next;
    private ImageView iv_lrc;
    private ImageView iv_sound;
    private MediaPlayer mediaPlayer;
    private TextView tv_start;
    private TextView tv_end;
    private SeekBar seekBar;
    private static final int UPDATE = 0;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE:
                    int c = mediaPlayer.getCurrentPosition();
                    if (mediaPlayer.isPlaying()) {
                        lrcView.setCurrentTimeMillis(makeDuration(mediaPlayer.getCurrentPosition()));
                        Log.d("------->>", "传过来的数值：" + makeDuration(mediaPlayer.getCurrentPosition()));
                        seekBar.setProgress(c);
                        tv_start.setText(makeDuration(c));
                    } else {
                        iv_stop.setVisibility(View.GONE);
                        iv_play.setVisibility(View.VISIBLE);
                    }
            }
        }
    };
    private LRCView lrcView;

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
        tv_start = (TextView) view.findViewById(R.id.tv_start_time);
        tv_end = (TextView) view.findViewById(R.id.tv_end_time);
        seekBar = (SeekBar) view.findViewById(R.id.seekbar);
        lrcView = (LRCView) view.findViewById(R.id.lrcView);
        lrcView.setOnPlayerClickListener(this);
        lrcView.setLineSpace(PreferenceUtil.getInstance(getActivity()).getFloat(PreferenceUtil.KEY_TEXT_SIZE, 12.0f));
        lrcView.setTextSize(PreferenceUtil.getInstance(getActivity()).getFloat(PreferenceUtil.KEY_TEXT_SIZE, 15.0f));
        lrcView.setHighLightTextColor(PreferenceUtil.getInstance(getActivity()).getInt(PreferenceUtil.KEY_HIGHLIGHT_COLOR, Color.parseColor("#4FC5C7")));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        onClick();
    }

    /**
     * 点击事件
     * <p>
     * 歌词不能滚动，进度时间和读取的时间并不相同，seekbar两边数值的显示不正确
     */
    private void onClick() {
        iv_frount.setOnClickListener(this);
        iv_lrc.setOnClickListener(this);
        iv_next.setOnClickListener(this);
        iv_play.setOnClickListener(this);
        iv_sound.setOnClickListener(this);
        iv_stop.setOnClickListener(this);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //不加这个判断会造成音乐播放卡顿
                if (fromUser) {
                    int postion = seekBar.getProgress();
                    mediaPlayer.seekTo(postion);
                    String curentStr = makeDuration(mediaPlayer.getDuration());
                    tv_end.setText(curentStr);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
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
                int max = mediaPlayer.getDuration();
                seekBar.setMax(max);
                String end = makeDuration(mediaPlayer.getDuration());
                tv_end.setText(end);
                //实时刷新进度条
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Timer timer = new Timer();
                        TimerTask task = new TimerTask() {
                            @Override
                            public void run() {
                                handler.sendEmptyMessage(UPDATE);
                            }
                        };
                        timer.schedule(task, 0, 1000);
                    }
                }).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(getActivity(), "歌曲名称错误", Toast.LENGTH_SHORT).show();
        }

        final int songid = getArguments().getInt("sid", 0);
        if (songid != 0) {
            final String sid = String.valueOf(songid);
            Entrances entrances = Entrances.getEntrances();
            entrances.setLrcData(new Subscriber<Lrc>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(Lrc lrc) {
                    try {
                        Lrc.LrcBody lrcBody = lrc.getShowapi_res_body();
                        String lyric = lrcBody.getLyric();
                        String ljs = FromHtmlUtils.changejs(lyric);
                        File file = writeLRC(ljs, songid);
                        lrcView.setLyricFile(file, "UTF-8");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, entrances.getSHOWAPPID(), entrances.getSHOWSIGN(), sid);
        } else {
            Toast.makeText(getActivity(), "获取歌词失败", Toast.LENGTH_SHORT).show();
        }

        /**
         * 下载
         */
        String downurl = getArguments().getString("downurl", null);
        if (downurl != null) {

        } else {
            Toast.makeText(getActivity(), "下载链接获取错误", Toast.LENGTH_SHORT).show();
        }

    }

    private String makeDuration(int current) {
        SimpleDateFormat formatter = new SimpleDateFormat("mm:ss");
        formatter.setTimeZone(TimeZone.getTimeZone("GMT+05:00"));
        String hms = formatter.format(current);
        return hms;
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

    /**
     * 写入以及读取
     *
     * @param lrcs
     * @param sid
     */
    private File writeLRC(String lrcs, int sid) {
        File file = null;
        try {
            Log.d("------>>", "写文件");
            String lpath = "/mnt/sdcard/Download/" + sid + ".lrc";
            file = new File(lpath);
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
            bw.write(lrcs);
            bw.flush();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    @Override
    public void onPlayerClicked(long progress, String content) {
        Log.d("------>>", "" + progress + ";" + content);
    }

}
