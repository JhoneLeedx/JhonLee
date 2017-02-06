package com.jhonlee.app.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jhonlee.app.R;
import com.jhonlee.app.bean.ResultBean;
import com.jhonlee.app.view.listener.ResultListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by JhoneLee on 2017/2/6.
 */

public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.PViewHolder> {

    // 数据集
    private List<ResultBean> mList;
    private Context mContext;
    private ResultListener listener;

    public PictureAdapter(List<ResultBean> mList, Context mContext, ResultListener listener) {
        this.mList = mList;
        this.mContext = mContext;
        this.listener = listener;
    }

    @Override
    public PViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        int resId = R.layout.item_picture;
        View view = LayoutInflater.from(mContext).inflate(resId, parent, false);
        PViewHolder holder = new PViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(PViewHolder holder, int position) {
        if (mList.size() == 0)
            return;
        final ResultBean bean = mList.get(position);
        holder.tvTitle.setText(bean.getWho());
        holder.tvDesc.setText(bean.getDesc());
        Glide.with(mContext).load(bean.getUrl())
                .placeholder(android.R.drawable.ic_search_category_default)
                .error(android.R.drawable.stat_notify_error)
                .into(holder.ivImage);
        holder.itemView.setTag(position);
        holder.itemView.setFocusable(true);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.showActivity(bean);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    protected class PViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.iv_image)
        protected ImageView ivImage;
        @BindView(R.id.tv_title)
        protected TextView tvTitle;
        @BindView(R.id.tv_desc)
        protected TextView tvDesc;

        public PViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
