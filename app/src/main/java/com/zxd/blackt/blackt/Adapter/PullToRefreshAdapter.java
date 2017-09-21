package com.zxd.blackt.blackt.Adapter;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zxd.blackt.blackt.Activity.MusicListActivity;
import com.zxd.blackt.blackt.Fragment.PlayMusicFragment;
import com.zxd.blackt.blackt.R;
import com.zxd.blackt.blackt.Entity.LeaderBoards.ShowapiResBodyBean.PagebeanBean.SonglistBean;

import java.util.List;

/**
 * 文 件 名: PullToRefreshAdapter
 * 创 建 人: Allen
 * 创建日期: 16/12/24 19:55
 * 邮   箱: AllenCoder@126.com
 * 修改时间：
 * 修改备注：
 */
public class PullToRefreshAdapter extends BaseQuickAdapter<SonglistBean, BaseViewHolder> {

    public PullToRefreshAdapter(List<SonglistBean> songlist) {
        super(R.layout.layout_music_type, songlist);
    }

    @Override
    protected void convert(BaseViewHolder helper, final SonglistBean item) {

        helper.setText(R.id.tv_music_name, item.getSongname());
        helper.setText(R.id.tv_music_author, item.getSingername());
        Glide.with(mContext).load(item.getAlbumpic_big()).crossFade().into((ImageView) helper.getView(R.id.iv_music_image));
//        ((LinearLayout) helper.getView(R.id.ll_music)).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final int songid = item.getSongid();
//                final String songurl = item.getUrl();
//                final String downurl = item.getDownUrl();
//                MusicListActivity musicListActivity = (MusicListActivity) mContext;
//                FragmentManager fragmentManager = musicListActivity.getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                PlayMusicFragment playMusicFragment = new PlayMusicFragment();
//                Bundle bundle = new Bundle();
//                bundle.putInt("sid", songid);
//                bundle.putString("songurl", songurl);
//                bundle.putString("downurl", downurl);
//                playMusicFragment.setArguments(bundle);
//                fragmentTransaction.replace(R.id.show_music_rll, playMusicFragment, "playmusicfragment");
//                fragmentTransaction.commit();
//            }
//        });
    }

}
