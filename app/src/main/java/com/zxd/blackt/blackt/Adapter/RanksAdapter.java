package com.zxd.blackt.blackt.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zxd.blackt.blackt.Entity.LeaderBoards;
import com.zxd.blackt.blackt.Entity.Song;
import com.zxd.blackt.blackt.R;

import java.util.List;

/**
 * Created by zhuangxd on 2017/6/21.
 */

public class RanksAdapter extends BaseAdapter {

    private Context context;
    private List<String> strlist;

    public RanksAdapter(Context context, List<String> strlist) {
        this.context = context;
        this.strlist = strlist;
    }

    @Override
    public int getCount() {
        return strlist.size();
    }

    @Override
    public Object getItem(int position) {
        return strlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_hot_list, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tv = (TextView) convertView.findViewById(R.id.tv_view_music_name);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv.setText(strlist.get(position));
        return convertView;
    }

    class ViewHolder {
        TextView tv;
    }

}
