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

public class ImgViewHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.tv_android_author)
    TextView mAndAuthor;
    @BindView(R.id.tv_android_desc)
    TextView mAndDesc;
    @BindView(R.id.img_android)
    ImageView mImagAndroid;

    public TextView getmAndAuthor() {
        return mAndAuthor;
    }

    public TextView getmAndDesc() {
        return mAndDesc;
    }

    public ImageView getmImagAndroid() {
        return mImagAndroid;
    }

    public ImgViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
