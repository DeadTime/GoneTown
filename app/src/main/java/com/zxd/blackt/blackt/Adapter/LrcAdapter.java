package com.zxd.blackt.blackt.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.zxd.blackt.blackt.Entity.Song;
import com.zxd.blackt.blackt.R;

import java.util.List;
import java.util.Map;

/**
 * Created by zhuangxd on 2017/6/21.
 */

public class LrcAdapter extends RecyclerView.Adapter<LrcAdapter.ViewHolder> {

    private Context context;
    private List<String> clist;
    private List<String> tlist;
    private LayoutInflater inflater;
    private List<Song> songlist;

    public LrcAdapter(Context context, List<Song> songlist) {
        this.context = context;
        this.songlist = songlist;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public LrcAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.layout_lrc, parent, false);
        LrcAdapter.ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(LrcAdapter.ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return songlist.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv_lrc;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_lrc = (TextView) itemView.findViewById(R.id.tv_lrc);
        }
    }

}
