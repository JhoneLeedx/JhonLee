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
 * Created by JhoneLee on 2017/2/7.
 */

public class AndroidAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // 数据集
    private List<ResultBean> mList;
    private Context mContext;
    private ResultListener listener;

    public AndroidAdapter(List<ResultBean> mList, Context mContext, ResultListener listener) {

        this.mList = mList;
        this.mContext = mContext;
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int resId;
        View view;
        if (viewType==ITEM_TYPE.ITEM_TYPE_NO_IMG.ordinal()){
            resId = R.layout.item_android_noimg;
            view = LayoutInflater.from(mContext).inflate(resId,parent,false);
            return new NoImgViewHolder(view);
        }else {
            resId = R.layout.item_android_img;
            view = LayoutInflater.from(mContext).inflate(resId,parent,false);
            return new ImgViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ResultBean bean = mList.get(position);
        if (holder instanceof ImgViewHolder){
            ImgViewHolder viewHolder = (ImgViewHolder)holder;
            viewHolder.mAndAuthor.setText(bean.getWho());
            viewHolder.mAndDesc.setText(bean.getDesc());
            Glide.with(mContext).load(bean.getImages().get(0)) .placeholder(android.R.drawable.ic_search_category_default)
                    .error(android.R.drawable.stat_notify_error)
                    .into(viewHolder.mImagAndroid);
        }else {
            NoImgViewHolder viewHolder = (NoImgViewHolder)holder;
            viewHolder.mNoAuthor.setText(bean.getWho());
            viewHolder.mNoDesc.setText(bean.getDesc());
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.showActivity(bean);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }
    @Override
    public int getItemViewType(int position) {

        if (mList.get(position).getImages()==null||mList.get(position).getImages().size()==0){
            return ITEM_TYPE.ITEM_TYPE_NO_IMG.ordinal();
        }else {
            return ITEM_TYPE.ITEM_TYPE_IMAGE.ordinal();
        }
    }
    protected class NoImgViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_no_author)
        TextView mNoAuthor;
        @BindView(R.id.tv_no_desc)
        TextView mNoDesc;

        public NoImgViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    protected class ImgViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_android_author)
        TextView mAndAuthor;
        @BindView(R.id.tv_android_desc)
        TextView mAndDesc;
        @BindView(R.id.img_android)
        ImageView mImagAndroid;
        public ImgViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    protected  enum ITEM_TYPE {
        ITEM_TYPE_IMAGE,
        ITEM_TYPE_NO_IMG
    }
}
