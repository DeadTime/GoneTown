package com.zxd.blackt.blackt.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.zxd.blackt.blackt.Activity.MainActivity;
import com.zxd.blackt.blackt.Adapter.TopAdapter;
import com.zxd.blackt.blackt.Application.App;
import com.zxd.blackt.blackt.Entity.Top;
import com.zxd.blackt.blackt.Entrance.Entrances;
import com.zxd.blackt.blackt.R;

import java.util.List;

import rx.Subscriber;

public class NewsFragment extends Fragment implements View.OnClickListener {

    private static final String APPKEY = "a18eeea4c51df5dd23a1dc3c50cec2e6";
    private static final String TYPE_TOP = "top";
    private static final String TYPE_SHEHUI = "shehui";
    private static final String TYPE_GUONEI = "guonei";
    private static final String TYPE_GUOJI = "guoji";
    private static final String TYPE_YULE = "yule";
    private static final String TYPE_TIYU = "tiyu";
    private static final String TYPE_JUNSHI = "junshi";
    private static final String TYPE_KEJI = "keji";
    private static final String TYPE_CAIJING = "caijing";
    private static final String TYPE_SHISHANG = "shishang";

    private SwipeRefreshLayout srl;
    private RecyclerView rlv;
    private TextView tv_top;
    private TextView tv_shehui;
    private TextView tv_guonei;
    private TextView tv_guoji;
    private TextView tv_yule;
    private TextView tv_tiyu;
    private TextView tv_junshi;
    private TextView tv_keji;
    private TextView tv_caijing;
    private TextView tv_shishang;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                getTopsData(TYPE_TOP);
                topAdapter.notifyDataSetChanged();
                srl.setRefreshing(false);
            }
        }
    };

    private TopAdapter topAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new, container, false);
        initView(view);
        rlv.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getTopsData(TYPE_TOP);
    }

    private void getTopsData(String type) {
        Entrances entrances = Entrances.getEntrances();
        entrances.getTopData(new Subscriber<Top>() {
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
                rlv.addItemDecoration(new DividerItemDecoration(App.getContext(), DividerItemDecoration.VERTICAL));
                rlv.setLayoutManager(new LinearLayoutManager(App.getContext()));
                topAdapter = new TopAdapter(App.getContext(), dataList);
                rlv.setAdapter(topAdapter);
            }
        }, type, APPKEY);
    }

    private void initView(View view) {
        tv_top = (TextView) view.findViewById(R.id.tv_news_top);
        tv_shehui = (TextView) view.findViewById(R.id.tv_news_shehui);
        tv_guonei = (TextView) view.findViewById(R.id.tv_news_guonei);
        tv_guoji = (TextView) view.findViewById(R.id.tv_news_guoji);
        tv_yule = (TextView) view.findViewById(R.id.tv_news_yule);
        tv_tiyu = (TextView) view.findViewById(R.id.tv_news_tiyu);
        tv_junshi = (TextView) view.findViewById(R.id.tv_news_junshi);
        tv_keji = (TextView) view.findViewById(R.id.tv_news_keji);
        tv_caijing = (TextView) view.findViewById(R.id.tv_news_caijing);
        tv_shishang = (TextView) view.findViewById(R.id.tv_news_shishang);
        tv_top.setOnClickListener(this);
        tv_shehui.setOnClickListener(this);
        tv_guonei.setOnClickListener(this);
        tv_guoji.setOnClickListener(this);
        tv_yule.setOnClickListener(this);
        tv_tiyu.setOnClickListener(this);
        tv_junshi.setOnClickListener(this);
        tv_keji.setOnClickListener(this);
        tv_caijing.setOnClickListener(this);
        tv_shishang.setOnClickListener(this);

        srl = (SwipeRefreshLayout) view.findViewById(R.id.srl);
        rlv = (RecyclerView) view.findViewById(R.id.rcv);
        srl.setOnRefreshListener(srlistener);
        srl.setProgressViewEndTarget(true, 200);

        tv_top.setTextColor(Color.BLACK);
        tv_top.setBackgroundResource(R.mipmap.btn_bag);

    }

    //刷新监听
    SwipeRefreshLayout.OnRefreshListener srlistener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            handler.sendEmptyMessageDelayed(0, 2000);
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_news_top:
                getTopsData(TYPE_TOP);
                tv_top.setTextColor(Color.BLACK);
                tv_top.setBackgroundResource(R.mipmap.btn_bag);
                tv_shehui.setTextColor(Color.parseColor("#F0F8FF"));
                tv_shehui.setBackgroundColor(Color.parseColor("#3F51B5"));
                tv_guonei.setTextColor(Color.parseColor("#F0F8FF"));
                tv_guonei.setBackgroundColor(Color.parseColor("#3F51B5"));
                tv_guoji.setTextColor(Color.parseColor("#F0F8FF"));
                tv_guoji.setBackgroundColor(Color.parseColor("#3F51B5"));
                tv_tiyu.setTextColor(Color.parseColor("#F0F8FF"));
                tv_tiyu.setBackgroundColor(Color.parseColor("#3F51B5"));
                tv_caijing.setTextColor(Color.parseColor("#F0F8FF"));
                tv_caijing.setBackgroundColor(Color.parseColor("#3F51B5"));
                tv_shishang.setTextColor(Color.parseColor("#F0F8FF"));
                tv_shishang.setBackgroundColor(Color.parseColor("#3F51B5"));
                tv_yule.setTextColor(Color.parseColor("#F0F8FF"));
                tv_yule.setBackgroundColor(Color.parseColor("#3F51B5"));
                tv_junshi.setTextColor(Color.parseColor("#F0F8FF"));
                tv_junshi.setBackgroundColor(Color.parseColor("#3F51B5"));
                tv_keji.setTextColor(Color.parseColor("#F0F8FF"));
                tv_keji.setBackgroundColor(Color.parseColor("#3F51B5"));
                break;
            case R.id.tv_news_shehui:
                getTopsData(TYPE_SHEHUI);
                tv_shehui.setTextColor(Color.BLACK);
                tv_shehui.setBackgroundResource(R.mipmap.btn_bag);
                tv_top.setTextColor(Color.parseColor("#F0F8FF"));
                tv_top.setBackgroundColor(Color.parseColor("#3F51B5"));
                tv_guonei.setTextColor(Color.parseColor("#F0F8FF"));
                tv_guonei.setBackgroundColor(Color.parseColor("#3F51B5"));
                tv_guoji.setTextColor(Color.parseColor("#F0F8FF"));
                tv_guoji.setBackgroundColor(Color.parseColor("#3F51B5"));
                tv_tiyu.setTextColor(Color.parseColor("#F0F8FF"));
                tv_tiyu.setBackgroundColor(Color.parseColor("#3F51B5"));
                tv_caijing.setTextColor(Color.parseColor("#F0F8FF"));
                tv_caijing.setBackgroundColor(Color.parseColor("#3F51B5"));
                tv_shishang.setTextColor(Color.parseColor("#F0F8FF"));
                tv_shishang.setBackgroundColor(Color.parseColor("#3F51B5"));
                tv_yule.setTextColor(Color.parseColor("#F0F8FF"));
                tv_yule.setBackgroundColor(Color.parseColor("#3F51B5"));
                tv_junshi.setTextColor(Color.parseColor("#F0F8FF"));
                tv_junshi.setBackgroundColor(Color.parseColor("#3F51B5"));
                tv_keji.setTextColor(Color.parseColor("#F0F8FF"));
                tv_keji.setBackgroundColor(Color.parseColor("#3F51B5"));
                break;
            case R.id.tv_news_guonei:
                getTopsData(TYPE_GUONEI);
                tv_guonei.setTextColor(Color.BLACK);
                tv_guonei.setBackgroundResource(R.mipmap.btn_bag);
                tv_top.setTextColor(Color.parseColor("#F0F8FF"));
                tv_top.setBackgroundColor(Color.parseColor("#3F51B5"));
                tv_shehui.setTextColor(Color.parseColor("#F0F8FF"));
                tv_shehui.setBackgroundColor(Color.parseColor("#3F51B5"));
                tv_guoji.setTextColor(Color.parseColor("#F0F8FF"));
                tv_guoji.setBackgroundColor(Color.parseColor("#3F51B5"));
                tv_tiyu.setTextColor(Color.parseColor("#F0F8FF"));
                tv_tiyu.setBackgroundColor(Color.parseColor("#3F51B5"));
                tv_caijing.setTextColor(Color.parseColor("#F0F8FF"));
                tv_caijing.setBackgroundColor(Color.parseColor("#3F51B5"));
                tv_shishang.setTextColor(Color.parseColor("#F0F8FF"));
                tv_shishang.setBackgroundColor(Color.parseColor("#3F51B5"));
                tv_yule.setTextColor(Color.parseColor("#F0F8FF"));
                tv_yule.setBackgroundColor(Color.parseColor("#3F51B5"));
                tv_junshi.setTextColor(Color.parseColor("#F0F8FF"));
                tv_junshi.setBackgroundColor(Color.parseColor("#3F51B5"));
                tv_keji.setTextColor(Color.parseColor("#F0F8FF"));
                tv_keji.setBackgroundColor(Color.parseColor("#3F51B5"));
                break;
            case R.id.tv_news_guoji:
                getTopsData(TYPE_GUOJI);
                tv_guoji.setTextColor(Color.BLACK);
                tv_guoji.setBackgroundResource(R.mipmap.btn_bag);
                tv_top.setTextColor(Color.parseColor("#F0F8FF"));
                tv_top.setBackgroundColor(Color.parseColor("#3F51B5"));
                tv_shehui.setTextColor(Color.parseColor("#F0F8FF"));
                tv_shehui.setBackgroundColor(Color.parseColor("#3F51B5"));
                tv_guonei.setTextColor(Color.parseColor("#F0F8FF"));
                tv_guonei.setBackgroundColor(Color.parseColor("#3F51B5"));
                tv_tiyu.setTextColor(Color.parseColor("#F0F8FF"));
                tv_tiyu.setBackgroundColor(Color.parseColor("#3F51B5"));
                tv_caijing.setTextColor(Color.parseColor("#F0F8FF"));
                tv_caijing.setBackgroundColor(Color.parseColor("#3F51B5"));
                tv_shishang.setTextColor(Color.parseColor("#F0F8FF"));
                tv_shishang.setBackgroundColor(Color.parseColor("#3F51B5"));
                tv_yule.setTextColor(Color.parseColor("#F0F8FF"));
                tv_yule.setBackgroundColor(Color.parseColor("#3F51B5"));
                tv_junshi.setTextColor(Color.parseColor("#F0F8FF"));
                tv_junshi.setBackgroundColor(Color.parseColor("#3F51B5"));
                tv_keji.setTextColor(Color.parseColor("#F0F8FF"));
                tv_keji.setBackgroundColor(Color.parseColor("#3F51B5"));
                break;
            case R.id.tv_news_yule:
                getTopsData(TYPE_YULE);
                tv_yule.setTextColor(Color.BLACK);
                tv_yule.setBackgroundResource(R.mipmap.btn_bag);
                tv_top.setTextColor(Color.parseColor("#F0F8FF"));
                tv_top.setBackgroundColor(Color.parseColor("#3F51B5"));
                tv_shehui.setTextColor(Color.parseColor("#F0F8FF"));
                tv_shehui.setBackgroundColor(Color.parseColor("#3F51B5"));
                tv_guonei.setTextColor(Color.parseColor("#F0F8FF"));
                tv_guonei.setBackgroundColor(Color.parseColor("#3F51B5"));
                tv_tiyu.setTextColor(Color.parseColor("#F0F8FF"));
                tv_tiyu.setBackgroundColor(Color.parseColor("#3F51B5"));
                tv_caijing.setTextColor(Color.parseColor("#F0F8FF"));
                tv_caijing.setBackgroundColor(Color.parseColor("#3F51B5"));
                tv_shishang.setTextColor(Color.parseColor("#F0F8FF"));
                tv_shishang.setBackgroundColor(Color.parseColor("#3F51B5"));
                tv_guoji.setTextColor(Color.parseColor("#F0F8FF"));
                tv_guoji.setBackgroundColor(Color.parseColor("#3F51B5"));
                tv_junshi.setTextColor(Color.parseColor("#F0F8FF"));
                tv_junshi.setBackgroundColor(Color.parseColor("#3F51B5"));
                tv_keji.setTextColor(Color.parseColor("#F0F8FF"));
                tv_keji.setBackgroundColor(Color.parseColor("#3F51B5"));
                break;
            case R.id.tv_news_tiyu:
                getTopsData(TYPE_TIYU);
                tv_tiyu.setTextColor(Color.BLACK);
                tv_tiyu.setBackgroundResource(R.mipmap.btn_bag);
                tv_top.setTextColor(Color.parseColor("#F0F8FF"));
                tv_top.setBackgroundColor(Color.parseColor("#3F51B5"));
                tv_shehui.setTextColor(Color.parseColor("#F0F8FF"));
                tv_shehui.setBackgroundColor(Color.parseColor("#3F51B5"));
                tv_guonei.setTextColor(Color.parseColor("#F0F8FF"));
                tv_guonei.setBackgroundColor(Color.parseColor("#3F51B5"));
                tv_yule.setTextColor(Color.parseColor("#F0F8FF"));
                tv_yule.setBackgroundColor(Color.parseColor("#3F51B5"));
                tv_caijing.setTextColor(Color.parseColor("#F0F8FF"));
                tv_caijing.setBackgroundColor(Color.parseColor("#3F51B5"));
                tv_shishang.setTextColor(Color.parseColor("#F0F8FF"));
                tv_shishang.setBackgroundColor(Color.parseColor("#3F51B5"));
                tv_guoji.setTextColor(Color.parseColor("#F0F8FF"));
                tv_guoji.setBackgroundColor(Color.parseColor("#3F51B5"));
                tv_junshi.setTextColor(Color.parseColor("#F0F8FF"));
                tv_junshi.setBackgroundColor(Color.parseColor("#3F51B5"));
                tv_keji.setTextColor(Color.parseColor("#F0F8FF"));
                tv_keji.setBackgroundColor(Color.parseColor("#3F51B5"));
                break;
            case R.id.tv_news_junshi:
                getTopsData(TYPE_JUNSHI);
                tv_junshi.setTextColor(Color.BLACK);
                tv_junshi.setBackgroundResource(R.mipmap.btn_bag);
                tv_top.setTextColor(Color.parseColor("#F0F8FF"));
                tv_top.setBackgroundColor(Color.parseColor("#3F51B5"));
                tv_shehui.setTextColor(Color.parseColor("#F0F8FF"));
                tv_shehui.setBackgroundColor(Color.parseColor("#3F51B5"));
                tv_guonei.setTextColor(Color.parseColor("#F0F8FF"));
                tv_guonei.setBackgroundColor(Color.parseColor("#3F51B5"));
                tv_yule.setTextColor(Color.parseColor("#F0F8FF"));
                tv_yule.setBackgroundColor(Color.parseColor("#3F51B5"));
                tv_caijing.setTextColor(Color.parseColor("#F0F8FF"));
                tv_caijing.setBackgroundColor(Color.parseColor("#3F51B5"));
                tv_shishang.setTextColor(Color.parseColor("#F0F8FF"));
                tv_shishang.setBackgroundColor(Color.parseColor("#3F51B5"));
                tv_guoji.setTextColor(Color.parseColor("#F0F8FF"));
                tv_guoji.setBackgroundColor(Color.parseColor("#3F51B5"));
                tv_tiyu.setTextColor(Color.parseColor("#F0F8FF"));
                tv_tiyu.setBackgroundColor(Color.parseColor("#3F51B5"));
                tv_keji.setTextColor(Color.parseColor("#F0F8FF"));
                tv_keji.setBackgroundColor(Color.parseColor("#3F51B5"));
                break;
            case R.id.tv_news_keji:
                getTopsData(TYPE_KEJI);
                tv_keji.setTextColor(Color.BLACK);
                tv_keji.setBackgroundResource(R.mipmap.btn_bag);
                tv_top.setTextColor(Color.parseColor("#F0F8FF"));
                tv_top.setBackgroundColor(Color.parseColor("#3F51B5"));
                tv_shehui.setTextColor(Color.parseColor("#F0F8FF"));
                tv_shehui.setBackgroundColor(Color.parseColor("#3F51B5"));
                tv_guonei.setTextColor(Color.parseColor("#F0F8FF"));
                tv_guonei.setBackgroundColor(Color.parseColor("#3F51B5"));
                tv_yule.setTextColor(Color.parseColor("#F0F8FF"));
                tv_yule.setBackgroundColor(Color.parseColor("#3F51B5"));
                tv_caijing.setTextColor(Color.parseColor("#F0F8FF"));
                tv_caijing.setBackgroundColor(Color.parseColor("#3F51B5"));
                tv_shishang.setTextColor(Color.parseColor("#F0F8FF"));
                tv_shishang.setBackgroundColor(Color.parseColor("#3F51B5"));
                tv_guoji.setTextColor(Color.parseColor("#F0F8FF"));
                tv_guoji.setBackgroundColor(Color.parseColor("#3F51B5"));
                tv_tiyu.setTextColor(Color.parseColor("#F0F8FF"));
                tv_tiyu.setBackgroundColor(Color.parseColor("#3F51B5"));
                tv_junshi.setTextColor(Color.parseColor("#F0F8FF"));
                tv_junshi.setBackgroundColor(Color.parseColor("#3F51B5"));
                break;
            case R.id.tv_news_caijing:
                getTopsData(TYPE_CAIJING);
                tv_caijing.setTextColor(Color.BLACK);
                tv_caijing.setBackgroundResource(R.mipmap.btn_bag);
                tv_top.setTextColor(Color.parseColor("#F0F8FF"));
                tv_top.setBackgroundColor(Color.parseColor("#3F51B5"));
                tv_shehui.setTextColor(Color.parseColor("#F0F8FF"));
                tv_shehui.setBackgroundColor(Color.parseColor("#3F51B5"));
                tv_guonei.setTextColor(Color.parseColor("#F0F8FF"));
                tv_guonei.setBackgroundColor(Color.parseColor("#3F51B5"));
                tv_yule.setTextColor(Color.parseColor("#F0F8FF"));
                tv_yule.setBackgroundColor(Color.parseColor("#3F51B5"));
                tv_keji.setTextColor(Color.parseColor("#F0F8FF"));
                tv_keji.setBackgroundColor(Color.parseColor("#3F51B5"));
                tv_shishang.setTextColor(Color.parseColor("#F0F8FF"));
                tv_shishang.setBackgroundColor(Color.parseColor("#3F51B5"));
                tv_guoji.setTextColor(Color.parseColor("#F0F8FF"));
                tv_guoji.setBackgroundColor(Color.parseColor("#3F51B5"));
                tv_tiyu.setTextColor(Color.parseColor("#F0F8FF"));
                tv_tiyu.setBackgroundColor(Color.parseColor("#3F51B5"));
                tv_junshi.setTextColor(Color.parseColor("#F0F8FF"));
                tv_junshi.setBackgroundColor(Color.parseColor("#3F51B5"));
                break;
            case R.id.tv_news_shishang:
                getTopsData(TYPE_SHISHANG);
                tv_shishang.setTextColor(Color.BLACK);
                tv_shishang.setBackgroundResource(R.mipmap.btn_bag);
                tv_top.setTextColor(Color.parseColor("#F0F8FF"));
                tv_top.setBackgroundColor(Color.parseColor("#3F51B5"));
                tv_shehui.setTextColor(Color.parseColor("#F0F8FF"));
                tv_shehui.setBackgroundColor(Color.parseColor("#3F51B5"));
                tv_guonei.setTextColor(Color.parseColor("#F0F8FF"));
                tv_guonei.setBackgroundColor(Color.parseColor("#3F51B5"));
                tv_yule.setTextColor(Color.parseColor("#F0F8FF"));
                tv_yule.setBackgroundColor(Color.parseColor("#3F51B5"));
                tv_keji.setTextColor(Color.parseColor("#F0F8FF"));
                tv_keji.setBackgroundColor(Color.parseColor("#3F51B5"));
                tv_caijing.setTextColor(Color.parseColor("#F0F8FF"));
                tv_caijing.setBackgroundColor(Color.parseColor("#3F51B5"));
                tv_guoji.setTextColor(Color.parseColor("#F0F8FF"));
                tv_guoji.setBackgroundColor(Color.parseColor("#3F51B5"));
                tv_tiyu.setTextColor(Color.parseColor("#F0F8FF"));
                tv_tiyu.setBackgroundColor(Color.parseColor("#3F51B5"));
                tv_junshi.setTextColor(Color.parseColor("#F0F8FF"));
                tv_junshi.setBackgroundColor(Color.parseColor("#3F51B5"));
                break;
        }
    }
}
