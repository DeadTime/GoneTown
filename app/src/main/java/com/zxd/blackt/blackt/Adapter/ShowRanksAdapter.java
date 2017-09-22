package com.zxd.blackt.blackt.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zxd.blackt.blackt.Activity.MainActivity;
import com.zxd.blackt.blackt.Activity.MusicListActivity;
import com.zxd.blackt.blackt.Entity.LeaderBoards;
import com.zxd.blackt.blackt.Entity.PlayMusic;
import com.zxd.blackt.blackt.Fragment.PlayMusicFragment;
import com.zxd.blackt.blackt.R;

import java.util.List;

import static com.bumptech.glide.request.RequestOptions.centerCropTransform;

/**
 * Created by zhuangxd on 2017/6/21.
 */

public class ShowRanksAdapter extends RecyclerView.Adapter<ShowRanksAdapter.ViewHolder> {

    private Context context;
    private List<LeaderBoards.ShowapiResBodyBean.PagebeanBean.SonglistBean> slist;

    public ShowRanksAdapter(Context context, List<LeaderBoards.ShowapiResBodyBean.PagebeanBean.SonglistBean> slist) {
        this.context = context;
        this.slist = slist;
    }

    @Override
    public ShowRanksAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_music_type, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ShowRanksAdapter.ViewHolder holder, int position) {
        holder.tv_author.setText(slist.get(position).getSingername());
        holder.tv_name.setText(slist.get(position).getSongname());
        Glide.with(context).load(slist.get(position).getAlbumpic_big()).apply(centerCropTransform()
                .placeholder(R.mipmap.ic_launcher)).into(holder.image);
        final int songid = slist.get(position).getSongid();
        final String songurl = slist.get(position).getUrl();
        final String downurl = slist.get(position).getDownUrl();
        holder.ll_music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MusicListActivity musicListActivity = (MusicListActivity) context;
                FragmentManager fragmentManager = musicListActivity.getSupportFragmentManager();
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

    @Override
    public int getItemCount() {
        return slist.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView image;
        private final TextView tv_name;
        private final TextView tv_author;
        private final LinearLayout ll_music;

        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.iv_music_image);
            tv_name = (TextView) itemView.findViewById(R.id.tv_music_name);
            tv_author = (TextView) itemView.findViewById(R.id.tv_music_author);
            ll_music = (LinearLayout) itemView.findViewById(R.id.ll_music);
        }
    }

}
