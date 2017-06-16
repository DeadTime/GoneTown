package com.zxd.blackt.blackt.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.zxd.blackt.blackt.Application.App;
import com.zxd.blackt.blackt.Entity.DaoMaster;
import com.zxd.blackt.blackt.Entity.DaoSession;
import com.zxd.blackt.blackt.Entity.Note;
import com.zxd.blackt.blackt.Entity.NoteDao;
import com.zxd.blackt.blackt.R;
import org.greenrobot.greendao.query.QueryBuilder;
import java.text.SimpleDateFormat;
import java.util.List;

public class ShowPrivateNoteFragment extends Fragment implements View.OnClickListener {

    private ImageView sock_private;
    private TextView private_name;
    private TextView private_date;
    private TextView private_content;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_show_privatenote, container, false);
        initView(view);
        initData();
        onClick();
        return view;
    }

    private void onClick() {
        sock_private.setOnClickListener(this);
    }

    private void initData() {
        SharedPreferences sp = getActivity().getSharedPreferences("note_name", Context.MODE_PRIVATE);
        String name = sp.getString("notename", null);
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(App.getContext(), "notedb", null);
        DaoSession daoSession = new DaoMaster(devOpenHelper.getWritableDatabase()).newSession();
        NoteDao noteDao = daoSession.getNoteDao();
        QueryBuilder<Note> queryBuilder = noteDao.queryBuilder().where(NoteDao.Properties.Nname.eq(name));
        List<Note> noteList = queryBuilder.list();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (Note note : noteList) {
            private_name.setText(note.getNname());
            String date = simpleDateFormat.format(note.getNdate());
            private_date.setText(date);
            private_content.setText(note.getNcontent());
        }
    }

    private void initView(View view) {
        sock_private = (ImageView) view.findViewById(R.id.sock_private);
        private_name = (TextView) view.findViewById(R.id.private_name);
        private_date = (TextView) view.findViewById(R.id.private_date);
        private_content = (TextView) view.findViewById(R.id.private_content);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sock_private:
                sock_private.setImageResource(R.mipmap.publinote);
                break;
        }
    }
}
