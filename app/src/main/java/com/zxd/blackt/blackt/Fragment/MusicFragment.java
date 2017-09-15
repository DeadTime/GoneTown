package com.zxd.blackt.blackt.Fragment;

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
import android.view.WindowManager;
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
import butterknife.OnClick;
import butterknife.OnTextChanged;
import butterknife.Unbinder;
import rx.Subscriber;

/**
 * http://tingapi.ting.baidu.com/v1/restserver/ting?format=json&calback=&from=webapp_music&method=baidu.ting.billboard.billList&type=1&size=3&offset=0
 */
public class MusicFragment extends Fragment {

    @BindView(R.id.edt_search_music)
    EditText edt_search_music;
    @BindView(R.id.iv_search_click)
    ImageView iv_search_click;
    @BindView(R.id.rll_search)
    RelativeLayout rllSearch;
    @BindView(R.id.rlv_musics)
    RecyclerView rlv_musics;
    Unbinder unbinder;
    private MusicAdapter musicAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_music, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        hideStatus();
    }

    @OnClick(R.id.iv_search_click)
    void search() {
        String search = edt_search_music.getText().toString().trim();
        getMusicData(search);
    }

    private void hideStatus() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            int uiFlags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            getActivity().getWindow().addFlags(uiFlags);
        }
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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
