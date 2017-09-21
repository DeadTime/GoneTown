package com.zxd.blackt.blackt.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.zxd.blackt.blackt.Adapter.PullToRefreshAdapter;
import com.zxd.blackt.blackt.Entity.LeaderBoards;
import com.zxd.blackt.blackt.Entity.LeaderBoards.ShowapiResBodyBean.PagebeanBean.SonglistBean;
import com.zxd.blackt.blackt.Entrance.Entrances;
import com.zxd.blackt.blackt.Fragment.HotMusicFragment;
import com.zxd.blackt.blackt.Fragment.InfoFragment;
import com.zxd.blackt.blackt.Fragment.NewsFragment;
import com.zxd.blackt.blackt.Fragment.NoteFragment;
import com.zxd.blackt.blackt.Fragment.PlayMusicFragment;
import com.zxd.blackt.blackt.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;

public class MusicListActivity extends BaseActivity implements BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.rlv_rank_musics)
    RecyclerView rlvRankMusics;
    @BindView(R.id.swiprefresh)
    SwipeRefreshLayout swiprefresh;
    @BindView(R.id.fabtn_info)
    FloatingActionButton fabtnInfo;
    @BindView(R.id.fabtn_pen)
    FloatingActionButton fabtnPen;
    @BindView(R.id.fabtn_notes)
    FloatingActionButton fabtnNotes;
    @BindView(R.id.fabtn_music)
    FloatingActionButton fabtnMusic;
    @BindView(R.id.fabtn_news)
    FloatingActionButton fabtnNews;
    @BindView(R.id.fabtn_setting)
    FloatingActionButton fabtnSetting;
    @BindView(R.id.menu)
    FloatingActionMenu menu;
    @BindView(R.id.show_music_rll)
    RelativeLayout showMusicRll;
    private String rankey;

    private static List<SonglistBean> slist;

    private static final int TOTAL_COUNTER = 100;
    private static final int PAGE_SIZE = 10;
    private boolean isErr;
    private boolean mLoadMoreEndGone = false;
    private int delayMillis = 1000;
    private int mCurrentCounter = 0;
    private PullToRefreshAdapter pullToRefreshAdapter;

    private FragmentManager fm;
    private FragmentTransaction ft;

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

    @OnClick({R.id.fabtn_info, R.id.fabtn_pen, R.id.fabtn_notes, R.id.fabtn_music, R.id.fabtn_news, R.id.fabtn_setting, R.id.menu})
    void musiclist(View view) {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        switch (view.getId()) {
            case R.id.fabtn_info:
                menu.close(true);
                ft.replace(R.id.show_music_rll, new InfoFragment(), "info");
                break;
            case R.id.fabtn_pen:
                break;
            case R.id.fabtn_notes:
                break;
            case R.id.fabtn_music:
                menu.close(true);
                ft.replace(R.id.show_music_rll, new HotMusicFragment(), "hotmusic");
                break;
            case R.id.fabtn_news:
                menu.close(true);
                ft.replace(R.id.show_music_rll, new NewsFragment(), "news");
                break;
            case R.id.fabtn_setting:
                break;
            case R.id.menu:
                break;
        }
        ft.commit();
    }

    private void initAdapter(final List<SonglistBean> slist) {
        pullToRefreshAdapter = new PullToRefreshAdapter(slist);
        pullToRefreshAdapter.setOnLoadMoreListener(this, rlvRankMusics);
        pullToRefreshAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_RIGHT);
        rlvRankMusics.setAdapter(pullToRefreshAdapter);
        mCurrentCounter = pullToRefreshAdapter.getData().size();

        rlvRankMusics.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(final BaseQuickAdapter adapter, final View view, final int position) {

                showMusicRll.setVisibility(View.VISIBLE);
                swiprefresh.setVisibility(View.GONE);
                final int songid = slist.get(position).getSongid();
                final String songurl = slist.get(position).getUrl();
                final String downurl = slist.get(position).getDownUrl();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                PlayMusicFragment playMusicFragment = new PlayMusicFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("sid", songid);
                bundle.putString("songurl", songurl);
                bundle.putString("downurl", downurl);
                playMusicFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.show_music_rll, playMusicFragment, "playmusicfragment");
                fragmentTransaction.commit();

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
