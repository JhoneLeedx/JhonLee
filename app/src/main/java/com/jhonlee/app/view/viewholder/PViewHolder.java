package com.jhonlee.app.view.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jhonlee.app.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by JhoneLee on 2017/2/8.
 */

public class PViewHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.iv_image)
    protected ImageView ivImage;
    @BindView(R.id.tv_title)
    protected TextView tvTitle;
    @BindView(R.id.tv_desc)
    protected TextView tvDesc;

    public ImageView getIvImage() {
        return ivImage;
    }

    public TextView getTvTitle() {
        return tvTitle;
    }

    public TextView getTvDesc() {
        return tvDesc;
    }

    public PViewHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this,itemView);
    }
}