package com.zxd.blackt.blackt.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zxd.blackt.blackt.Activity.ShowActivity;
import com.zxd.blackt.blackt.Application.App;
import com.zxd.blackt.blackt.Entity.Note;
import com.zxd.blackt.blackt.R;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by zhuangxd on 2017/5/10.
 */

public class PrivateNoteAdapter extends RecyclerView.Adapter<PrivateNoteAdapter.ViewHolder> {
    private Context context = App.getContext();
    private List<Note> noteList;

    public PrivateNoteAdapter(Context context, List<Note> noteList) {
        this.context = context;
        this.noteList = noteList;
    }

    @Override
    public PrivateNoteAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.layout_private_note, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PrivateNoteAdapter.ViewHolder holder, final int position) {
        holder.tv_privatenote_name.setText(noteList.get(position).getNname());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = simpleDateFormat.format(noteList.get(position).getNdate());
        holder.tv_privatenote_date.setText(date);
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv_privatenote_name;
        private final TextView tv_privatenote_date;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_privatenote_name = (TextView) itemView.findViewById(R.id.tv_privatenote_name);
            tv_privatenote_date = (TextView) itemView.findViewById(R.id.tv_privatenote_date);
        }
    }

}
