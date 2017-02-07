package com.jhonlee.app.view.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.VideoView;

import com.jhonlee.app.R;
import com.jhonlee.app.bean.ResultBean;
import com.jhonlee.app.view.listener.ResultListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by JhoneLee on 2017/2/7.
 */

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoHolder> {
    // 数据集
    private List<ResultBean> mList;
    private Context mContext;
    private ResultListener listener;

    public VideoAdapter(List<ResultBean> mList, Context mContext, ResultListener listener) {
        this.mList = mList;
        this.mContext = mContext;
        this.listener = listener;
    }

    @Override
    public VideoHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        int resId = R.layout.item_video;
        View view = LayoutInflater.from(mContext).inflate(resId,parent,false);
        return new VideoHolder(view);
    }

    @Override
    public void onBindViewHolder(VideoHolder holder, int position) {

        final ResultBean bean = mList.get(position);
       holder.mVideoDesc.setText(bean.getDesc());
        holder.mVideoTime.setText(bean.getCreatedAt());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.showActivity(bean);
            }
        });
    }

    @Override
    public int getItemCount() {
        return  mList == null ? 0 : mList.size();
    }

    protected class VideoHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.video)
        VideoView video;
        @BindView(R.id.tv_video_desc)
        TextView mVideoDesc;
        @BindView(R.id.tv_video_time)
        TextView mVideoTime;
        public VideoHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
