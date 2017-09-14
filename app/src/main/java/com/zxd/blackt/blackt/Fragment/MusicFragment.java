package com.zxd.blackt.blackt.Fragment;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.zxd.blackt.blackt.Adapter.MusicAdapter;
import com.zxd.blackt.blackt.Entity.PlayMusic;
import com.zxd.blackt.blackt.Entrance.Entrances;
import com.zxd.blackt.blackt.R;

import java.util.List;

import rx.Subscriber;

/**
 * http://tingapi.ting.baidu.com/v1/restserver/ting?format=json&calback=&from=webapp_music&method=baidu.ting.billboard.billList&type=1&size=3&offset=0
 */
public class MusicFragment extends Fragment implements View.OnClickListener {

    private RecyclerView rlv_musics;
    private EditText edt_search_music;
    private ImageView iv_search_click;
    private MusicAdapter musicAdapter;

    //    @SuppressLint("NewApi")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_music, container, false);
//        final ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
//        viewTreeObserver.addOnWindowFocusChangeListener(new ViewTreeObserver.OnWindowFocusChangeListener() {
//            @Override
//            public void onWindowFocusChanged(final boolean hasFocus) {
//                if (hasFocus && Build.VERSION.SDK_INT >= 19) {
//                    View decorView = getActivity().getWindow().getDecorView();
//                    decorView.setSystemUiVisibility(
//                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                                    | View.SYSTEM_UI_FLAG_FULLSCREEN
//                                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
//                }
//            }
//        });
        initView(view);
        return view;
    }

    private void initView(View view) {
        iv_search_click = (ImageView) view.findViewById(R.id.iv_search_click);
        rlv_musics = (RecyclerView) view.findViewById(R.id.rlv_musics);
        edt_search_music = (EditText) view.findViewById(R.id.edt_search_music);
        edt_search_music.setFocusable(true);
        edt_search_music.setFocusableInTouchMode(true);
        edt_search_music.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
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


        });

    }

    private void goneButton() {
        iv_search_click.setVisibility(View.GONE);
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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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
                Toast.makeText(getActivity(), "未知错误", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(PlayMusic playMusic) {
                String errorcode = playMusic.getShowapi_res_code();
                if (errorcode.equals("0")) {
                    PlayMusic.ResBody rb = playMusic.getShowapi_res_body();
                    PlayMusic.PageBean pb = rb.getPagebean();
                    List<PlayMusic.Song> slist = pb.getContentlist();
                    rlv_musics.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
                    rlv_musics.setLayoutManager(new LinearLayoutManager(getActivity()));
                    musicAdapter = new MusicAdapter(getActivity(), slist);
                    rlv_musics.setAdapter(musicAdapter);
                } else {
                    Toast.makeText(getActivity(), playMusic.getShowapi_res_error(), Toast.LENGTH_SHORT).show();
                }
            }
        }, appid, appsign, q);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back_notes:
                break;
        }
    }

}
