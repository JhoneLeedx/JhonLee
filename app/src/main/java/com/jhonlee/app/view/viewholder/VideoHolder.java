package com.jhonlee.app.view.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.VideoView;

import com.jhonlee.app.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by JhoneLee on 2017/2/8.
 */

public class VideoHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.video)
    VideoView video;
    @BindView(R.id.tv_video_desc)
    TextView mVideoDesc;
    @BindView(R.id.tv_video_time)
    TextView mVideoTime;

    public VideoView getVideo() {
        return video;
    }

    public TextView getmVideoDesc() {
        return mVideoDesc;
    }

    public TextView getmVideoTime() {
        return mVideoTime;
    }

    public VideoHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
