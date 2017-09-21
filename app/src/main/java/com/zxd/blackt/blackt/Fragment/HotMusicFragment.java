package com.zxd.blackt.blackt.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zxd.blackt.blackt.Activity.MainActivity;
import com.zxd.blackt.blackt.Activity.MusicListActivity;
import com.zxd.blackt.blackt.Adapter.RanksAdapter;
import com.zxd.blackt.blackt.Customs.BTListView;
import com.zxd.blackt.blackt.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.Unbinder;

public class HotMusicFragment extends Fragment {


    @BindView(R.id.iv_please_click)
    ImageView ivPleaseClick;
    Unbinder unbinder;
    @BindView(R.id.btlv_hots)
    BTListView btlvHots;
    private List<String> strlist;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hotmusic, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setGifIcon();
        setRanks();
    }

    private void setRanks() {
        strlist = new ArrayList<String>();
        strlist.add("欧美排行榜");
        strlist.add("内地排行榜");
        strlist.add("港台排行榜");
        strlist.add("最火流行榜");
        strlist.add("热歌排行榜");
        strlist.add("新歌排行榜");
        strlist.add("网络歌曲榜");
        strlist.add("K歌金曲榜");
        RanksAdapter ranksAdapter = new RanksAdapter(getActivity(), strlist);
        btlvHots.setAdapter(ranksAdapter);
    }

    private void setGifIcon() {
        Glide.with(getActivity()).load(R.mipmap.pleaseclick).asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(ivPleaseClick);
    }

    @OnClick(R.id.ll_please_click)
    void pleaseClick() {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.putExtra("search", "search");
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.tra_frount, R.anim.tra_back);
        getActivity().finish();
    }

    @OnItemClick(R.id.btlv_hots)
    void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String rankname = strlist.get(position);
        String rankey = null;
        if (rankname.equals("欧美排行榜")) {
            rankey = "3";
        } else if (rankname.equals("内地排行榜")) {
            rankey = "5";
        } else if (rankname.equals("港台排行榜")) {
            rankey = "6";
        } else if (rankname.equals("最火流行榜")) {
            rankey = "4";
        } else if (rankname.equals("热歌排行榜")) {
            rankey = "26";
        } else if (rankname.equals("新歌排行榜")) {
            rankey = "27";
        } else if (rankname.equals("网络歌曲榜")) {
            rankey = "28";
        } else if (rankname.equals("K歌金曲榜")) {
            rankey = "36";
        }
        Intent intent = new Intent(getActivity(), MusicListActivity.class);
        intent.putExtra("rankey", rankey);
        getActivity().overridePendingTransition(R.anim.tra_frount, R.anim.tra_back);
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
