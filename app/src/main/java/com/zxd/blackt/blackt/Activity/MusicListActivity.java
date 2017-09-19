package com.zxd.blackt.blackt.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.zxd.blackt.blackt.Adapter.PullToRefreshAdapter;
import com.zxd.blackt.blackt.Entity.LeaderBoards;
import com.zxd.blackt.blackt.Entrance.Entrances;
import com.zxd.blackt.blackt.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;

import com.zxd.blackt.blackt.Entity.LeaderBoards.ShowapiResBodyBean.PagebeanBean.SonglistBean;

public class MusicListActivity extends BaseActivity implements BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.rlv_rank_musics)
    RecyclerView rlvRankMusics;
    @BindView(R.id.swiprefresh)
    SwipeRefreshLayout swiprefresh;
    private String rankey;

    private static List<SonglistBean> slist;

    private static final int TOTAL_COUNTER = 100;
    private static final int PAGE_SIZE = 10;
    private boolean isErr;
    private boolean mLoadMoreEndGone = false;
    private int delayMillis = 1000;
    private int mCurrentCounter = 0;
    private PullToRefreshAdapter pullToRefreshAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_list);
        ButterKnife.bind(this);
        swiprefresh.setOnRefreshListener(this);
        swiprefresh.setColorSchemeColors(Color.rgb(47, 223, 189));
        rlvRankMusics.setLayoutManager(new LinearLayoutManager(this));
        rankey = getRankey();
        showRanks();
    }

    private void initAdapter(List<SonglistBean> slist) {
        pullToRefreshAdapter = new PullToRefreshAdapter(slist);
        pullToRefreshAdapter.setOnLoadMoreListener(this, rlvRankMusics);
        pullToRefreshAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_RIGHT);
        rlvRankMusics.setAdapter(pullToRefreshAdapter);
        mCurrentCounter = pullToRefreshAdapter.getData().size();

        rlvRankMusics.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(final BaseQuickAdapter adapter, final View view, final int position) {
                Toast.makeText(MusicListActivity.this, Integer.toString(position), Toast.LENGTH_LONG).show();
            }
        });
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
                slist = leaderBoards.getShowapi_res_body().getPagebean().getSonglist();
                initAdapter(slist);
            }
        }, appid, appsign, rankey);

    }

    private String getRankey() {
        return getIntent().getStringExtra("rankey");
    }

    @Override
    public void onRefresh() {
        pullToRefreshAdapter.setEnableLoadMore(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                pullToRefreshAdapter.setNewData(slist);
                isErr = false;
                mCurrentCounter = PAGE_SIZE;
                swiprefresh.setRefreshing(false);
                pullToRefreshAdapter.setEnableLoadMore(true);
            }
        }, delayMillis);
    }

    @Override
    public void onLoadMoreRequested() {
        swiprefresh.setEnabled(false);
        if (pullToRefreshAdapter.getData().size() < PAGE_SIZE) {
            pullToRefreshAdapter.loadMoreEnd(true);
        } else {
            if (mCurrentCounter >= TOTAL_COUNTER) {
                pullToRefreshAdapter.loadMoreEnd(mLoadMoreEndGone);//true is gone,false is visible
            } else {
                if (isErr) {
                    pullToRefreshAdapter.addData(slist);
                    mCurrentCounter = pullToRefreshAdapter.getData().size();
                    pullToRefreshAdapter.loadMoreComplete();
                } else {
                    isErr = true;
                    Toast.makeText(MusicListActivity.this, "is nothing … …", Toast.LENGTH_LONG).show();
                    pullToRefreshAdapter.loadMoreEnd();

                }
            }
            swiprefresh.setEnabled(true);
        }

    }
}
