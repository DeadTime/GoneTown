package com.zxd.blackt.blackt.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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

public class NoteFragment extends Fragment implements View.OnClickListener {

    private EditText et_note;
    private ImageView iv_back;
    private ImageView iv_save;
    private ImageView iv_del;
    private DaoMaster.DevOpenHelper devOpenHelper;
    private DaoSession daoSession;
    private NoteDao noteDao;
    private Note note;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private EditText edit_notes_name;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        et_note = (EditText) view.findViewById(R.id.edit_notes);
        et_note.setFocusable(true);
        edit_notes_name = (EditText) view.findViewById(R.id.edit_notes_name);
        iv_back = (ImageView) view.findViewById(R.id.iv_back_notes);
        iv_save = (ImageView) view.findViewById(R.id.iv_savenote);
        iv_del = (ImageView) view.findViewById(R.id.iv_delnote);
        iv_back.setOnClickListener(this);
        iv_save.setOnClickListener(this);
        iv_del.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        devOpenHelper = new DaoMaster.DevOpenHelper(App.getContext(), "notedb", null);
        daoSession = new DaoMaster(devOpenHelper.getWritableDatabase()).newSession();
        noteDao = daoSession.getNoteDao();
        note = new Note();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back_notes:
                break;
            case R.id.iv_savenote:
                note.setNname(edit_notes_name.getText().toString().trim());
                note.setNcontent(et_note.getText().toString().trim());
                Date curDate = new Date(System.currentTimeMillis());
                note.setNdate(curDate);
                noteDao.insert(note);
                fm = getActivity().getSupportFragmentManager();
                ft = fm.beginTransaction();
                dialog(ft);
                break;
            case R.id.iv_delnote:
                break;
        }
    }

    private void dialog(final FragmentTransaction ft) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());  //先得到构造器
        builder.setTitle("保存成功"); //设置标题
        builder.setMessage("请问是否公开?"); //设置内容
        builder.setIcon(R.mipmap.ic_tishi);//设置图标，图片id即可
        builder.setPositiveButton("公开", new DialogInterface.OnClickListener() { //设置确定按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss(); //关闭dialog
                //方法一
                AllNoteFragment allNoteFragment = AllNoteFragment.newInstance(false);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.replace(R.id.rll, allNoteFragment, "allnote");
                ft.commit();
                Toast.makeText(App.getContext(), "设置成功，你的日记将会被更多人看到", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("保密", new DialogInterface.OnClickListener() { //设置取消按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                //方法二
                AllNoteFragment allNoteFragment = new AllNoteFragment();
                Bundle bundle = new Bundle();
                bundle.putBoolean("flag", true);
                allNoteFragment.setArguments(bundle);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.replace(R.id.rll, allNoteFragment, "allnote");
                ft.commit();
                Toast.makeText(App.getContext(), "设置成功，该日记将仅自己可见", Toast.LENGTH_SHORT).show();
            }
        });
        //参数都设置完成了，创建并显示出来
        builder.create().show();
    }

}
