package com.jhonlee.app.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.jhonlee.app.R;
import com.jhonlee.app.bean.DayLife;
import com.jhonlee.app.bean.ResultBean;
import com.jhonlee.app.util.CategoryUtil;
import com.jhonlee.app.view.listener.ResultListener;
import com.jhonlee.app.view.viewholder.ImgViewHolder;
import com.jhonlee.app.view.viewholder.NoImgViewHolder;
import com.jhonlee.app.view.viewholder.PViewHolder;
import com.jhonlee.app.view.viewholder.VideoHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by JhoneLee on 2017/2/6.
 */

public class ALLAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // 数据集
    private List<ResultBean> resultBeen;
    private Context mContext;
    private ResultListener listener;

    public ALLAdapter(List<ResultBean> resultBeen, Context mContext, ResultListener listener) {
        this.resultBeen = resultBeen;
        this.mContext = mContext;
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int resId;
        View view;
        if (viewType== CategoryUtil.NOIMG.ordinal()){
            resId = R.layout.item_android_noimg;
            view = LayoutInflater.from(mContext).inflate(resId,parent,false);
            return new NoImgViewHolder(view);
        }else if (viewType== CategoryUtil.HASIMG.ordinal()){
            resId = R.layout.item_android_img;
            view = LayoutInflater.from(mContext).inflate(resId,parent,false);
            return new ImgViewHolder(view);
        }else if (viewType== CategoryUtil.PICTURE.ordinal()){
            resId = R.layout.item_picture;
            view = LayoutInflater.from(mContext).inflate(resId,parent,false);
            return new PViewHolder(view);
        }else {
            resId = R.layout.item_video;
            view = LayoutInflater.from(mContext).inflate(resId,parent,false);
            return new VideoHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof VideoHolder){
            VideoHolder vholder = (VideoHolder)holder;
            vholder.getmVideoTime().setText(resultBeen.get(position).getCreatedAt());
            vholder.getmVideoDesc().setText(resultBeen.get(position).getDesc());
        }else if (holder instanceof PViewHolder){
            PViewHolder pholder = (PViewHolder)holder;
            pholder.getTvDesc().setText(resultBeen.get(position).getDesc());
            pholder.getTvTitle().setText(resultBeen.get(position).getType());
            Glide.with(mContext).load(resultBeen.get(position).getUrl()).into(pholder.getIvImage());
        }else if (holder instanceof ImgViewHolder){
            ImgViewHolder iholder = (ImgViewHolder)holder;
            iholder.getmAndAuthor().setText(resultBeen.get(position).getWho());
            iholder.getmAndDesc().setText(resultBeen.get(position).getDesc());
            Glide.with(mContext).load(resultBeen.get(position).getImages().get(0)).into(iholder.getmImagAndroid());
        }else {
            NoImgViewHolder nholder = (NoImgViewHolder)holder;
            nholder.getmNoAuthor().setText(resultBeen.get(position).getWho());
            nholder.getmNoDesc().setText(resultBeen.get(position).getDesc());
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.showActivity(resultBeen.get(position));
            }
        });
    }



    @Override
    public int getItemViewType(int position) {
      String type = resultBeen.get(position).getType();
        if (type.equals("福利")){
            return CategoryUtil.PICTURE.ordinal();
        }else if (type.equals("休息视频")){
            return CategoryUtil.VIDEO.ordinal();
        }else if (resultBeen.get(position).getImages()==null||resultBeen.get(position).getImages().size()==0){
            return CategoryUtil.NOIMG.ordinal();
        }else {
            return CategoryUtil.HASIMG.ordinal();
        }
    }

    @Override
    public int getItemCount() {
        return resultBeen == null ? 0 : resultBeen.size();
    }

    protected class ALLViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_type)
        TextView mType;
        @BindView(R.id.tv_desc)
        TextView mDesc;
        @BindView(R.id.tv_time)
        TextView mTime;
        @BindView(R.id.circle)
        CircleImageView mCircle;
        public ALLViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
