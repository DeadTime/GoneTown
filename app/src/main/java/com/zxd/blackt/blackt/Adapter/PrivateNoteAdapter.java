package com.zxd.blackt.blackt.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zxd.blackt.blackt.Activity.MainActivity;
import com.zxd.blackt.blackt.Application.App;
import com.zxd.blackt.blackt.Entity.Note;
import com.zxd.blackt.blackt.Fragment.ShowPrivateNoteFragment;
import com.zxd.blackt.blackt.R;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by zhuangxd on 2017/5/10.
 */

public class PrivateNoteAdapter extends RecyclerView.Adapter<PrivateNoteAdapter.ViewHolder> {
    private Context context = App.getContext();
    private List<Note> noteList;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    public PrivateNoteAdapter(Context context, List<Note> noteList) {
        this.context = context;
        this.noteList = noteList;
    }

    @Override
    public PrivateNoteAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.layout_private_note, null);
        sp = context.getSharedPreferences("note_name", Context.MODE_PRIVATE);
        editor = sp.edit();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PrivateNoteAdapter.ViewHolder holder, final int position) {
        holder.tv_privatenote_name.setText(noteList.get(position).getNname());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        final String date = simpleDateFormat.format(noteList.get(position).getNdate());
        holder.tv_privatenote_date.setText(date);
        holder.ll_privatenote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) context;
                FragmentManager fragmentManager = mainActivity.getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.rll, new ShowPrivateNoteFragment(), "showprivatenotefragment");
                fragmentTransaction.commit();
                editor.putString("notename", noteList.get(position).getNname());
                editor.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv_privatenote_name;
        private final TextView tv_privatenote_date;
        private final LinearLayout ll_privatenote;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_privatenote_name = (TextView) itemView.findViewById(R.id.tv_privatenote_name);
            tv_privatenote_date = (TextView) itemView.findViewById(R.id.tv_privatenote_date);
            ll_privatenote = (LinearLayout) itemView.findViewById(R.id.ll_privatenote);
        }
    }

}
