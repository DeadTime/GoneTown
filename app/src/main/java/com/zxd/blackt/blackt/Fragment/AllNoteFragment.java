package com.zxd.blackt.blackt.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.zxd.blackt.blackt.Adapter.PrivateNoteAdapter;
import com.zxd.blackt.blackt.Application.App;
import com.zxd.blackt.blackt.Entity.DaoMaster;
import com.zxd.blackt.blackt.Entity.DaoSession;
import com.zxd.blackt.blackt.Entity.Note;
import com.zxd.blackt.blackt.Entity.NoteDao;
import com.zxd.blackt.blackt.R;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.Date;
import java.util.List;

public class AllNoteFragment extends Fragment {


    private RecyclerView rlv_private_note;
    private RecyclerView rlv_public_note;
    private boolean b;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_allnote, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        if (getArguments() != null) {
            b = getArguments().getBoolean("flag");
        }
        b = getArguments().getBoolean("flag");
    }

    private void initView(View view) {
        rlv_private_note = (RecyclerView) view.findViewById(R.id.rlv_private_note);
        rlv_public_note = (RecyclerView) view.findViewById(R.id.rlv_public_note);
    }

    public static AllNoteFragment newInstance(boolean flag) {
        AllNoteFragment fragment = new AllNoteFragment();
        Bundle args = new Bundle();
        args.putBoolean("flag", flag);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (b == false) {
            rlv_public_note.addItemDecoration(new DividerItemDecoration(App.getContext(), DividerItemDecoration.VERTICAL));
            rlv_public_note.setLayoutManager(new LinearLayoutManager(App.getContext()));
        }
        if (b == true) {
            rlv_private_note.setLayoutManager(new GridLayoutManager(App.getContext(), 3));
            DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(App.getContext(), "notedb", null);
            DaoSession daoSession = new DaoMaster(devOpenHelper.getWritableDatabase()).newSession();
            NoteDao noteDao = daoSession.getNoteDao();
            QueryBuilder<Note> queryBuilder = noteDao.queryBuilder();
            List<Note> noteList = queryBuilder.list();
            PrivateNoteAdapter privateNoteAdapter = new PrivateNoteAdapter(getContext(), noteList);
            rlv_private_note.setAdapter(privateNoteAdapter);
        }

    }

}
