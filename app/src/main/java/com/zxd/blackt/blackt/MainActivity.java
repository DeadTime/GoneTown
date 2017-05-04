package com.zxd.blackt.blackt;

import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.zxd.blackt.blackt.Entity.Banners;
import com.zxd.blackt.blackt.Entity.Hots;
import com.zxd.blackt.blackt.Entity.Types;
import com.zxd.blackt.blackt.Provider.BannersProvider;
import com.zxd.blackt.blackt.Provider.HotsProvider;
import com.zxd.blackt.blackt.Provider.TypesProvider;

import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolBar;
    private FloatingActionButton fabtn;
    private SwipeRefreshLayout srl;
    private RecyclerView rlv;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                srl.setRefreshing(false);
            }
        }
    };
    private Items items;
    private MultiTypeAdapter mta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
//        setSupportActionBar(toolBar);

        initData();

    }

    private void initData() {
        mta.register(Banners.class, new BannersProvider());
        mta.register(Types.class, new TypesProvider());
        mta.register(Hots.class, new HotsProvider());
        //分别添加数据
    }

    private void initView() {
        toolBar = (Toolbar) findViewById(R.id.toolbar);
        fabtn = (FloatingActionButton) findViewById(R.id.fabtn);
        srl = (SwipeRefreshLayout) findViewById(R.id.srl);
        rlv = (RecyclerView) findViewById(R.id.rcv);
        rlv.setLayoutManager(new LinearLayoutManager(this));
        fabtn.setOnClickListener(this);
        srl.setOnRefreshListener(srlistener);
        items = new Items();
        mta = new MultiTypeAdapter(items);
    }

    SwipeRefreshLayout.OnRefreshListener srlistener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            handler.sendEmptyMessageDelayed(0, 2000);
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fabtn:
                Toast.makeText(this, "click once", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
