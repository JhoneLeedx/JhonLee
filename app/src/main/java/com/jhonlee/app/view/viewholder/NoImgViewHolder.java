package com.jhonlee.app.view.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.jhonlee.app.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by JhoneLee on 2017/2/8.
 */

public class NoImgViewHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.tv_no_author)
    TextView mNoAuthor;
    @BindView(R.id.tv_no_desc)
    TextView mNoDesc;

    public TextView getmNoAuthor() {
        return mNoAuthor;
    }

    public TextView getmNoDesc() {
        return mNoDesc;
    }

    public NoImgViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
