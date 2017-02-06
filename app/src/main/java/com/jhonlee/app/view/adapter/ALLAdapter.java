package com.jhonlee.app.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.jhonlee.app.R;
import com.jhonlee.app.bean.ResultBean;
import com.jhonlee.app.view.listener.ResultListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by JhoneLee on 2017/2/6.
 */

public class ALLAdapter extends RecyclerView.Adapter<ALLAdapter.ALLViewHolder> {

    // 数据集
    private List<ResultBean> mList;
    private Context mContext;
    private ResultListener listener;

    public ALLAdapter(List<ResultBean> mList, Context mContext, ResultListener listener) {
        this.mList = mList;
        this.mContext = mContext;
        this.listener = listener;
    }

    @Override
    public ALLViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int resId = R.layout.item_index;
        View view = LayoutInflater.from(mContext).inflate(resId, parent, false);
        ALLAdapter.ALLViewHolder holder = new ALLAdapter.ALLViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ALLViewHolder holder, int position) {
        if (mList.size() == 0)
            return;
        final ResultBean bean = mList.get(position);
        holder.mType.setText(bean.getType());
        holder.mDesc.setText(bean.getDesc());
        holder.mTime.setText(bean.getCreatedAt());

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
