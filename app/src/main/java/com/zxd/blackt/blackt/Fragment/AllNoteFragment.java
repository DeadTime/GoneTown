package com.zxd.blackt.blackt.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.zxd.blackt.blackt.Application.App;
import com.zxd.blackt.blackt.Entity.DaoMaster;
import com.zxd.blackt.blackt.Entity.DaoSession;
import com.zxd.blackt.blackt.Entity.Note;
import com.zxd.blackt.blackt.Entity.NoteDao;
import com.zxd.blackt.blackt.R;

import java.util.Date;

public class AllNoteFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_allnote, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        Bundle bundle = new Bundle();
        boolean b = bundle.getBoolean("flag");
        Toast.makeText(App.getContext(), "" + b, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}
