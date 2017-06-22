package com.zxd.blackt.blackt.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zxd.blackt.blackt.Activity.MainActivity;
import com.zxd.blackt.blackt.Application.App;
import com.zxd.blackt.blackt.Entity.Music;
import com.zxd.blackt.blackt.Entity.PlayMusic;
import com.zxd.blackt.blackt.Fragment.PlayMusicFragment;
import com.zxd.blackt.blackt.Fragment.ShowPrivateNoteFragment;
import com.zxd.blackt.blackt.R;

import java.util.List;

/**
 * Created by zhuangxd on 2017/6/21.
 */

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.ViewHolder> {

    private Context context;
    private List<PlayMusic.Song> slist;

    public MusicAdapter(Context context, List<PlayMusic.Song> slist) {
        this.context = context;
        this.slist = slist;
    }

    @Override
    public MusicAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.layout_music_type, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MusicAdapter.ViewHolder holder, int position) {
        holder.tv_author.setText(slist.get(position).getSingername());
        holder.tv_name.setText(slist.get(position).getSongname());
        Glide.with(context).load(slist.get(position).getAlbumpic_big()).into(holder.image);
        final int songid = slist.get(position).getSongid();
        final String songurl = slist.get(position).getM4a();
        final String downurl = slist.get(position).getDownUrl();
        holder.ll_music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) context;
                FragmentManager fragmentManager = mainActivity.getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                PlayMusicFragment playMusicFragment = new PlayMusicFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("sid", songid);
                bundle.putString("songurl", songurl);
                bundle.putString("downurl", downurl);
                playMusicFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.rll, playMusicFragment, "playmusicfragment");
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
