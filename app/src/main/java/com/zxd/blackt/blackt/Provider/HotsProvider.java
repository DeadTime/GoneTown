package com.zxd.blackt.blackt.Provider;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.zxd.blackt.blackt.Entity.Hots;

import me.drakeet.multitype.ItemViewProvider;

/**
 * Created by zhuangxd on 2017/5/4.
 */

public class HotsProvider extends ItemViewProvider<Hots,RecyclerView.ViewHolder>{
    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return null;
    }

    @Override
    protected void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @NonNull Hots hots) {

    }
}
