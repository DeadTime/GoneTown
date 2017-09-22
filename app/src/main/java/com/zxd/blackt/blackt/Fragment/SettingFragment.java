package com.zxd.blackt.blackt.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sdsmdg.tastytoast.TastyToast;
import com.zxd.blackt.blackt.Activity.DayAndNightActivity;
import com.zxd.blackt.blackt.Application.App;
import com.zxd.blackt.blackt.R;
import com.zxd.blackt.blackt.Util.GlideUtils.GlideCatchConfig;
import com.zxd.blackt.blackt.Util.GlideUtils.GlideCatchUtil;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SettingFragment extends Fragment {


    Unbinder unbinder;
    @BindView(R.id.setthemmm)
    Button setNight;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            if (msg.what == 1) {

                int current = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
                if (current == Configuration.UI_MODE_NIGHT_YES) {
                    ((AppCompatActivity) getActivity()).getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    showDayorNight("day");
                } else {
                    ((AppCompatActivity) getActivity()).getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    showDayorNight("night");
                }

            }
        }
    };
    @BindView(R.id.textcache)
    TextView textcache;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    private void showDayorNight(String isDay) {

        sp = getActivity().getSharedPreferences("isDay", Context.MODE_PRIVATE);
        editor = sp.edit();
        editor.putString("isDay", isDay);
        editor.commit();

        Intent intent = new Intent(getActivity(), DayAndNightActivity.class);
        intent.putExtra("isDay", isDay);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        getActivity().finish();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        textcache.setText(getCacheSize());
    }

    @OnClick(R.id.setthemmm)
    void setNight() {
        handler.sendEmptyMessage(1);
    }

    @OnClick(R.id.clearcache)
    void clearcache() {
        cleanCatchDisk();
        clearCacheDiskSelf();
        textcache.setText(getCacheSize());
        TastyToast.makeText(getActivity(), "Clear success !", TastyToast.LENGTH_SHORT, TastyToast.SUCCESS);
    }

    public boolean cleanCatchDisk() {
        return GlideCatchUtil.deleteFolderFile(App.getInstances().getCacheDir() + "/" + GlideCatchConfig.GLIDE_CARCH_DIR, true);
    }

    public boolean clearCacheDiskSelf() {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.get(App.getInstances()).clearDiskCache();
                    }
                }).start();
            } else {
                Glide.get(App.getInstances()).clearDiskCache();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getCacheSize() {
        try {
            return GlideCatchUtil.getFormatSize(GlideCatchUtil.getFolderSize(new File(App.getInstances().getCacheDir() + "/" + GlideCatchConfig.GLIDE_CARCH_DIR)));
        } catch (Exception e) {
            e.printStackTrace();
            return "获取失败";
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
