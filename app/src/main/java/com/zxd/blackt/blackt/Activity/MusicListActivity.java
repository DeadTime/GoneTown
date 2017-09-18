package com.zxd.blackt.blackt.Activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.zxd.blackt.blackt.Adapter.ShowRanksAdapter;
import com.zxd.blackt.blackt.Entity.LeaderBoards;
import com.zxd.blackt.blackt.Entrance.Entrances;
import com.zxd.blackt.blackt.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;

public class MusicListActivity extends BaseActivity {

    @BindView(R.id.rlv_rank_musics)
    RecyclerView rlvRankMusics;
    private String rankey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_list);
        ButterKnife.bind(this);
        rlvRankMusics.setLayoutManager(new LinearLayoutManager(this));
        rankey = getRankey();
        showRanks();
    }

    private void showRanks() {

        Entrances entrances = Entrances.getEntrances();
        String appid = entrances.getSHOWAPPID();
        String appsign = entrances.getSHOWSIGN();
        entrances.getLeaderBoards(new Subscriber<LeaderBoards>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(LeaderBoards leaderBoards) {
                List<LeaderBoards.ShowapiResBodyBean.PagebeanBean.SonglistBean> slist = leaderBoards.getShowapi_res_body().getPagebean().getSonglist();
                ShowRanksAdapter showRanksAdapter = new ShowRanksAdapter(MusicListActivity.this, slist);
                rlvRankMusics.setAdapter(showRanksAdapter);
            }
        }, appid, appsign, rankey);

    }

    private String getRankey() {
        return getIntent().getStringExtra("rankey");
    }
}
