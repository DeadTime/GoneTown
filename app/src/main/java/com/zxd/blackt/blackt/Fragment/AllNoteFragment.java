package com.zxd.blackt.blackt.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zxd.blackt.blackt.Adapter.PrivateNoteAdapter;
import com.zxd.blackt.blackt.Application.App;
import com.zxd.blackt.blackt.Entity.DaoMaster;
import com.zxd.blackt.blackt.Entity.DaoSession;
import com.zxd.blackt.blackt.Entity.Note;
import com.zxd.blackt.blackt.Entity.NoteDao;
import com.zxd.blackt.blackt.R;

import org.greenrobot.greendao.query.QueryBuilder;

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
        } else if (getArguments() == null) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("isShow", Context.MODE_PRIVATE);
            boolean isShow = sharedPreferences.getBoolean("isShow", false);
            if (isShow == true) {
                showPublicNote();
            }
        }
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
            showPublicNote();
        }
        if (b == true) {
            showPublicNote();
        }

    }

    private void showPublicNote() {
        rlv_public_note.setLayoutManager(new LinearLayoutManager(App.getContext()));
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(App.getContext(), "notedb", null);
        DaoSession daoSession = new DaoMaster(devOpenHelper.getWritableDatabase()).newSession();
        NoteDao noteDao = daoSession.getNoteDao();
        QueryBuilder<Note> queryBuilder = noteDao.queryBuilder();
        List<Note> noteList = queryBuilder.list();
        PrivateNoteAdapter privateNoteAdapter = new PrivateNoteAdapter(getContext(), noteList);
        rlv_public_note.setAdapter(privateNoteAdapter);

        rlv_private_note.setLayoutManager(new GridLayoutManager(App.getContext(), 3));
        DaoMaster.DevOpenHelper devOpenHelper2 = new DaoMaster.DevOpenHelper(App.getContext(), "notedb", null);
        DaoSession daoSession2 = new DaoMaster(devOpenHelper2.getWritableDatabase()).newSession();
        NoteDao noteDao2 = daoSession2.getNoteDao();
        QueryBuilder<Note> queryBuilder2 = noteDao2.queryBuilder();
        List<Note> noteList2 = queryBuilder2.list();
        PrivateNoteAdapter privateNoteAdapter2 = new PrivateNoteAdapter(getContext(), noteList2);
        rlv_private_note.setAdapter(privateNoteAdapter2);

    }


}
