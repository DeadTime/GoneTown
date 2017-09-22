package com.zxd.blackt.blackt.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.DrawableUtils;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zxd.blackt.blackt.Activity.ShowActivity;
import com.zxd.blackt.blackt.Application.App;
import com.zxd.blackt.blackt.Entity.Top;
import com.zxd.blackt.blackt.R;

import java.util.List;

import static com.bumptech.glide.request.RequestOptions.centerCropTransform;

/**
 * Created by zhuangxd on 2017/5/10.
 */

public class TopAdapter extends RecyclerView.Adapter<TopAdapter.ViewHolder> {
    private Context context = App.getContext();
    private List<Top.TopData> topDataList;

    public TopAdapter(Context context, List<Top.TopData> topDataList) {
        this.context = context;
        this.topDataList = topDataList;
    }

    @Override
    public TopAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.layout_top, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TopAdapter.ViewHolder holder, final int position) {
        holder.tv_top_title.setText(topDataList.get(position).getTitle());
        holder.tv_top_date.setText(topDataList.get(position).getDate());
        holder.tv_top_detail.setText(topDataList.get(position).getAuthor_name());
        Glide.with(context).load(topDataList.get(position).getThumbnail_pic_s()).apply(centerCropTransform()
                .placeholder(R.mipmap.ic_launcher)).into(holder.iv_top_image);
        holder.iv_top_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * Context中有一个startActivity方法，Activity继承自Context，重载了startActivity方法。
                 * 如果使用Activity的startActivity方法，不会有任何限制，
                 * 而如果使用Context的startActivity方法的话，就需要开启一个新的task，如遇如下异常：
                 * android.util.AndroidRuntimeException: Calling startActivity() from outside of an Activity context requires the FLAG_ACTIVITY_NEW_TASK flag. Is this really what you want?
                 * 是因为使用了Context的startActivity方法。解决办法是，加一个flag。
                 */
                Intent intent = new Intent(context, ShowActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//加个flag
                intent.putExtra("url", topDataList.get(position).getUrl());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return topDataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv_top_title;
        private final TextView tv_top_date;
        private final TextView tv_top_detail;
        private final ImageView iv_top_image;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_top_title = (TextView) itemView.findViewById(R.id.tv_top_title);
            tv_top_date = (TextView) itemView.findViewById(R.id.tv_top_date);
            tv_top_detail = (TextView) itemView.findViewById(R.id.tv_top_detail);
            iv_top_image = (ImageView) itemView.findViewById(R.id.iv_top_image);
        }
    }

}
