package com.zxd.blackt.blackt.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.zxd.blackt.blackt.Adapter.TopAdapter;
import com.zxd.blackt.blackt.Application.App;
import com.zxd.blackt.blackt.Common.Constants;
import com.zxd.blackt.blackt.Common.Events.ExitEvent;
import com.zxd.blackt.blackt.Entity.Top;
import com.zxd.blackt.blackt.Entrance.Entrances;
import com.zxd.blackt.blackt.R;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;

public class EveryNewsActivity extends BaseActivity {

    @BindView(R.id.tv_news_top)
    TextView tvNewsTop;
    @BindView(R.id.tv_news_shehui)
    TextView tvNewsShehui;
    @BindView(R.id.tv_news_guonei)
    TextView tvNewsGuonei;
    @BindView(R.id.tv_news_guoji)
    TextView tvNewsGuoji;
    @BindView(R.id.tv_news_yule)
    TextView tvNewsYule;
    @BindView(R.id.tv_news_tiyu)
    TextView tvNewsTiyu;
    @BindView(R.id.tv_news_junshi)
    TextView tvNewsJunshi;
    @BindView(R.id.tv_news_keji)
    TextView tvNewsKeji;
    @BindView(R.id.tv_news_caijing)
    TextView tvNewsCaijing;
    @BindView(R.id.tv_news_shishang)
    TextView tvNewsShishang;
    @BindView(R.id.hsview)
    HorizontalScrollView hsview;
    @BindView(R.id.rcv)
    RecyclerView rcv;
    @BindView(R.id.srl)
    SwipeRefreshLayout srl;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                topAdapter.notifyDataSetChanged();
                getTopsData(Constants.TYPE_TOP);
                srl.setRefreshing(false);
            }
            if (msg.what == 1) {
                topAdapter.notifyDataSetChanged();
                getTopsData(Constants.TYPE_TIYU);
                srl.setRefreshing(false);
            }
            if (msg.what == 2) {
                topAdapter.notifyDataSetChanged();
                getTopsData(Constants.TYPE_CAIJING);
                srl.setRefreshing(false);
            }
            if (msg.what == 3) {
                topAdapter.notifyDataSetChanged();
                getTopsData(Constants.TYPE_SHISHANG);
                srl.setRefreshing(false);
            }
            if (msg.what == 4) {
                topAdapter.notifyDataSetChanged();
                getTopsData(Constants.TYPE_GUONEI);
                srl.setRefreshing(false);
            }
            if (msg.what == 5) {
                topAdapter.notifyDataSetChanged();
                getTopsData(Constants.TYPE_GUOJI);
                srl.setRefreshing(false);
            }
            if (msg.what == 6) {
                topAdapter.notifyDataSetChanged();
                getTopsData(Constants.TYPE_JUNSHI);
                srl.setRefreshing(false);
            }
            if (msg.what == 7) {
                topAdapter.notifyDataSetChanged();
                getTopsData(Constants.TYPE_YULE);
                srl.setRefreshing(false);
            }
            if (msg.what == 8) {
                topAdapter.notifyDataSetChanged();
                getTopsData(Constants.TYPE_KEJI);
                srl.setRefreshing(false);
            }
            if (msg.what == 9) {
                topAdapter.notifyDataSetChanged();
                getTopsData(Constants.TYPE_SHEHUI);
                srl.setRefreshing(false);
            }
        }
    };
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
    @BindView(R.id.fabtn_info)
    FloatingActionButton fabtnInfo;
    @BindView(R.id.menu)
    FloatingActionMenu menu;

    private TopAdapter topAdapter;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_every_news);
        ButterKnife.bind(this);
        doSome();
        rcv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                /**
                 * RecyclerView.canScrollVertically(1)的值表示是否能向下滚动，false表示已经滚动到底部
                 * RecyclerView.canScrollVertically(-1)的值表示是否能向上滚动，false表示已经滚动到顶部
                 */
                if (!recyclerView.canScrollVertically(1)) {
                    Toast.makeText(App.getContext(), "无有更多了……", Toast.LENGTH_SHORT).show();
                }
            }
        });
        initData();
    }

    private void doSome() {

        srl.setOnRefreshListener(srlistener);
        srl.setProgressViewEndTarget(true, 200);

        tvNewsTop.setBackgroundColor(Color.parseColor("#4A4A4A"));

    }

    private void initData() {
        sp = getSharedPreferences("page", Context.MODE_PRIVATE);
        editor = sp.edit();

        getTopsData(Constants.TYPE_TOP);
    }

    private void getTopsData(String type) {
        Entrances entrances = Entrances.getEntrances();
        entrances.setTopData(new Subscriber<Top>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(App.getContext(), "ERROR!!!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(Top top) {
                List<Top.TopData> dataList = top.getResult().getData();
                rcv.addItemDecoration(new DividerItemDecoration(App.getContext(), DividerItemDecoration.VERTICAL));
                rcv.setLayoutManager(new LinearLayoutManager(App.getContext()));
                topAdapter = new TopAdapter(App.getContext(), dataList);
                rcv.setAdapter(topAdapter);
            }
        }, type, Constants.APPKEY);
    }

    //刷新监听
    SwipeRefreshLayout.OnRefreshListener srlistener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            String pageName = sp.getString("pagename", null);
            if (pageName == null) {
                handler.sendEmptyMessageDelayed(0, 2000);
            }
            if (pageName == "top") {
                handler.sendEmptyMessageDelayed(0, 2000);
            }
            if (pageName == "tiyu") {
                handler.sendEmptyMessageDelayed(1, 2000);
            }
            if (pageName == "caijing") {
                handler.sendEmptyMessageDelayed(2, 2000);
            }
            if (pageName == "shishang") {
                handler.sendEmptyMessageDelayed(3, 2000);
            }
            if (pageName == "guonei") {
                handler.sendEmptyMessageDelayed(4, 2000);
            }
            if (pageName == "guoji") {
                handler.sendEmptyMessageDelayed(5, 2000);
            }
            if (pageName == "junshi") {
                handler.sendEmptyMessageDelayed(6, 2000);
            }
            if (pageName == "yule") {
                handler.sendEmptyMessageDelayed(7, 2000);
            }
            if (pageName == "keji") {
                handler.sendEmptyMessageDelayed(8, 2000);
            }
            if (pageName == "shehui") {
                handler.sendEmptyMessageDelayed(9, 2000);
            }
        }
    };

    @OnClick({R.id.tv_news_top, R.id.tv_news_shehui, R.id.tv_news_guonei, R.id.tv_news_guoji, R.id.tv_news_yule, R.id.tv_news_tiyu, R.id.tv_news_junshi, R.id.tv_news_keji, R.id.tv_news_caijing, R.id.tv_news_shishang})
    public void onclicks(View v) {

        switch (v.getId()) {
            case R.id.tv_news_top:
                editor.putString("pagename", "top");
                editor.commit();
                getTopsData(Constants.TYPE_TOP);
                tvNewsTop.setBackgroundColor(Color.parseColor("#4A4A4A"));
                tvNewsShehui.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsShehui.setBackgroundColor(Color.BLACK);
                tvNewsGuonei.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsGuonei.setBackgroundColor(Color.BLACK);
                tvNewsGuoji.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsGuoji.setBackgroundColor(Color.BLACK);
                tvNewsTiyu.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsTiyu.setBackgroundColor(Color.BLACK);
                tvNewsCaijing.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsCaijing.setBackgroundColor(Color.BLACK);
                tvNewsShishang.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsShishang.setBackgroundColor(Color.BLACK);
                tvNewsYule.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsYule.setBackgroundColor(Color.BLACK);
                tvNewsJunshi.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsJunshi.setBackgroundColor(Color.BLACK);
                tvNewsKeji.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsKeji.setBackgroundColor(Color.BLACK);
                break;
            case R.id.tv_news_shehui:
                editor.putString("pagename", "shehui");
                editor.commit();
                getTopsData(Constants.TYPE_SHEHUI);
                tvNewsShehui.setBackgroundColor(Color.parseColor("#4A4A4A"));
                tvNewsTop.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsTop.setBackgroundColor(Color.BLACK);
                tvNewsGuonei.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsGuonei.setBackgroundColor(Color.BLACK);
                tvNewsGuoji.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsGuoji.setBackgroundColor(Color.BLACK);
                tvNewsTiyu.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsTiyu.setBackgroundColor(Color.BLACK);
                tvNewsCaijing.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsCaijing.setBackgroundColor(Color.BLACK);
                tvNewsShishang.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsShishang.setBackgroundColor(Color.BLACK);
                tvNewsYule.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsYule.setBackgroundColor(Color.BLACK);
                tvNewsJunshi.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsJunshi.setBackgroundColor(Color.BLACK);
                tvNewsKeji.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsKeji.setBackgroundColor(Color.BLACK);
                break;
            case R.id.tv_news_guonei:
                editor.putString("pagename", "guonei");
                editor.commit();
                getTopsData(Constants.TYPE_GUONEI);
                tvNewsGuonei.setBackgroundColor(Color.parseColor("#4A4A4A"));
                tvNewsTop.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsTop.setBackgroundColor(Color.BLACK);
                tvNewsShehui.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsShehui.setBackgroundColor(Color.BLACK);
                tvNewsGuoji.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsGuoji.setBackgroundColor(Color.BLACK);
                tvNewsTiyu.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsTiyu.setBackgroundColor(Color.BLACK);
                tvNewsCaijing.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsCaijing.setBackgroundColor(Color.BLACK);
                tvNewsShishang.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsShishang.setBackgroundColor(Color.BLACK);
                tvNewsYule.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsYule.setBackgroundColor(Color.BLACK);
                tvNewsJunshi.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsJunshi.setBackgroundColor(Color.BLACK);
                tvNewsKeji.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsKeji.setBackgroundColor(Color.BLACK);
                break;
            case R.id.tv_news_guoji:
                editor.putString("pagename", "guoji");
                editor.commit();
                getTopsData(Constants.TYPE_GUOJI);
                tvNewsGuoji.setBackgroundColor(Color.parseColor("#4A4A4A"));
                tvNewsTop.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsTop.setBackgroundColor(Color.BLACK);
                tvNewsShehui.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsShehui.setBackgroundColor(Color.BLACK);
                tvNewsGuonei.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsGuonei.setBackgroundColor(Color.BLACK);
                tvNewsTiyu.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsTiyu.setBackgroundColor(Color.BLACK);
                tvNewsCaijing.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsCaijing.setBackgroundColor(Color.BLACK);
                tvNewsShishang.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsShishang.setBackgroundColor(Color.BLACK);
                tvNewsYule.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsYule.setBackgroundColor(Color.BLACK);
                tvNewsJunshi.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsJunshi.setBackgroundColor(Color.BLACK);
                tvNewsKeji.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsKeji.setBackgroundColor(Color.BLACK);
                break;
            case R.id.tv_news_yule:
                editor.putString("pagename", "yule");
                editor.commit();
                getTopsData(Constants.TYPE_YULE);
                tvNewsYule.setBackgroundColor(Color.parseColor("#4A4A4A"));
                tvNewsTop.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsTop.setBackgroundColor(Color.BLACK);
                tvNewsShehui.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsShehui.setBackgroundColor(Color.BLACK);
                tvNewsGuonei.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsGuonei.setBackgroundColor(Color.BLACK);
                tvNewsTiyu.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsTiyu.setBackgroundColor(Color.BLACK);
                tvNewsCaijing.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsCaijing.setBackgroundColor(Color.BLACK);
                tvNewsShishang.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsShishang.setBackgroundColor(Color.BLACK);
                tvNewsGuoji.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsGuoji.setBackgroundColor(Color.BLACK);
                tvNewsJunshi.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsJunshi.setBackgroundColor(Color.BLACK);
                tvNewsKeji.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsKeji.setBackgroundColor(Color.BLACK);
                break;
            case R.id.tv_news_tiyu:
                editor.putString("pagename", "tiyu");
                editor.commit();
                getTopsData(Constants.TYPE_TIYU);
                tvNewsTiyu.setBackgroundColor(Color.parseColor("#4A4A4A"));
                tvNewsTop.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsTop.setBackgroundColor(Color.BLACK);
                tvNewsShehui.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsShehui.setBackgroundColor(Color.BLACK);
                tvNewsGuonei.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsGuonei.setBackgroundColor(Color.BLACK);
                tvNewsYule.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsYule.setBackgroundColor(Color.BLACK);
                tvNewsCaijing.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsCaijing.setBackgroundColor(Color.BLACK);
                tvNewsShishang.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsShishang.setBackgroundColor(Color.BLACK);
                tvNewsGuoji.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsGuoji.setBackgroundColor(Color.BLACK);
                tvNewsJunshi.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsJunshi.setBackgroundColor(Color.BLACK);
                tvNewsKeji.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsKeji.setBackgroundColor(Color.BLACK);
                break;
            case R.id.tv_news_junshi:
                editor.putString("pagename", "junshi");
                editor.commit();
                getTopsData(Constants.TYPE_JUNSHI);
                tvNewsJunshi.setBackgroundColor(Color.parseColor("#4A4A4A"));
                tvNewsTop.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsTop.setBackgroundColor(Color.BLACK);
                tvNewsShehui.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsShehui.setBackgroundColor(Color.BLACK);
                tvNewsGuonei.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsGuonei.setBackgroundColor(Color.BLACK);
                tvNewsYule.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsYule.setBackgroundColor(Color.BLACK);
                tvNewsCaijing.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsCaijing.setBackgroundColor(Color.BLACK);
                tvNewsShishang.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsShishang.setBackgroundColor(Color.BLACK);
                tvNewsGuoji.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsGuoji.setBackgroundColor(Color.BLACK);
                tvNewsTiyu.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsTiyu.setBackgroundColor(Color.BLACK);
                tvNewsKeji.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsKeji.setBackgroundColor(Color.BLACK);
                break;
            case R.id.tv_news_keji:
                editor.putString("pagename", "keji");
                editor.commit();
                getTopsData(Constants.TYPE_KEJI);
                tvNewsKeji.setBackgroundColor(Color.parseColor("#4A4A4A"));
                tvNewsTop.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsTop.setBackgroundColor(Color.BLACK);
                tvNewsShehui.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsShehui.setBackgroundColor(Color.BLACK);
                tvNewsGuonei.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsGuonei.setBackgroundColor(Color.BLACK);
                tvNewsYule.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsYule.setBackgroundColor(Color.BLACK);
                tvNewsCaijing.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsCaijing.setBackgroundColor(Color.BLACK);
                tvNewsShishang.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsShishang.setBackgroundColor(Color.BLACK);
                tvNewsGuoji.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsGuoji.setBackgroundColor(Color.BLACK);
                tvNewsTiyu.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsTiyu.setBackgroundColor(Color.BLACK);
                tvNewsJunshi.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsJunshi.setBackgroundColor(Color.BLACK);
                break;
            case R.id.tv_news_caijing:
                editor.putString("pagename", "caijing");
                editor.commit();
                getTopsData(Constants.TYPE_CAIJING);
                tvNewsCaijing.setBackgroundColor(Color.parseColor("#4A4A4A"));
                tvNewsTop.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsTop.setBackgroundColor(Color.BLACK);
                tvNewsShehui.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsShehui.setBackgroundColor(Color.BLACK);
                tvNewsGuonei.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsGuonei.setBackgroundColor(Color.BLACK);
                tvNewsYule.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsYule.setBackgroundColor(Color.BLACK);
                tvNewsKeji.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsKeji.setBackgroundColor(Color.BLACK);
                tvNewsShishang.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsShishang.setBackgroundColor(Color.BLACK);
                tvNewsGuoji.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsGuoji.setBackgroundColor(Color.BLACK);
                tvNewsTiyu.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsTiyu.setBackgroundColor(Color.BLACK);
                tvNewsJunshi.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsJunshi.setBackgroundColor(Color.BLACK);
                break;
            case R.id.tv_news_shishang:
                editor.putString("pagename", "shishang");
                editor.commit();
                getTopsData(Constants.TYPE_SHISHANG);
                tvNewsShishang.setBackgroundColor(Color.parseColor("#4A4A4A"));
                tvNewsTop.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsTop.setBackgroundColor(Color.BLACK);
                tvNewsShehui.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsShehui.setBackgroundColor(Color.BLACK);
                tvNewsGuonei.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsGuonei.setBackgroundColor(Color.BLACK);
                tvNewsYule.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsYule.setBackgroundColor(Color.BLACK);
                tvNewsKeji.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsKeji.setBackgroundColor(Color.BLACK);
                tvNewsCaijing.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsCaijing.setBackgroundColor(Color.BLACK);
                tvNewsGuoji.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsGuoji.setBackgroundColor(Color.BLACK);
                tvNewsTiyu.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsTiyu.setBackgroundColor(Color.BLACK);
                tvNewsJunshi.setTextColor(Color.parseColor("#F0F8FF"));
                tvNewsJunshi.setBackgroundColor(Color.BLACK);
                break;
        }

    }

    @OnClick({R.id.fabtn_pen, R.id.fabtn_music, R.id.fabtn_news, R.id.fabtn_notes, R.id.fabtn_setting, R.id.fabtn_info})
    public void fabclicks(View v) {
        switch (v.getId()) {
            case R.id.fabtn_setting:
                menu.close(true);
                Toast.makeText(this, "设置", Toast.LENGTH_SHORT).show();
                break;
            case R.id.fabtn_music:
                menu.close(true);
                Toast.makeText(this, "音乐", Toast.LENGTH_SHORT).show();
                break;
            case R.id.fabtn_news:
                menu.close(true);
                Toast.makeText(this, "新闻", Toast.LENGTH_SHORT).show();
                break;
            case R.id.fabtn_pen:
                menu.close(true);
                Toast.makeText(this, "铅笔", Toast.LENGTH_SHORT).show();
                break;
            case R.id.fabtn_notes:
                menu.close(true);
                Toast.makeText(this, "本子", Toast.LENGTH_SHORT).show();
                SharedPreferences sharedPreferences = getSharedPreferences("isShow", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("isShow", true);
                editor.commit();
                break;
            case R.id.fabtn_info:
                menu.close(true);
                Toast.makeText(this, "信息", Toast.LENGTH_SHORT).show();
                break;
        }
    }

}
