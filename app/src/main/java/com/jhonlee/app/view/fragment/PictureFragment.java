package com.jhonlee.app.view.fragment;


import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.jhonlee.app.R;
import com.jhonlee.app.bean.ResultBean;
import com.jhonlee.app.contract.Contract;
import com.jhonlee.app.presenter.PresenterImpl;
import com.jhonlee.app.view.activity.ShowImageActivity;
import com.jhonlee.app.view.adapter.PictureAdapter;
import com.jhonlee.app.view.listener.ResultListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
* Created by JhoneLee on 2017/02/06
*/

public class PictureFragment extends Fragment implements Contract.View,ResultListener {

    @BindView(R.id.refresh)
    protected SwipeRefreshLayout refresh;
    @BindView(R.id.recycler)
    protected RecyclerView mRecycler;
    @BindView(R.id.rl_loading)
    protected RelativeLayout mRlLoading;

    private Contract.Presenter presenter;
    private PictureAdapter adapter;
    private List<ResultBean> mList;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_picture,container,false);
        ButterKnife.bind(this,view);
        presenter = new PresenterImpl(this);
        presenter.showPicture("福利",100,1);
        initRecyclerView();
        return view;
    }
    public void initRecyclerView(){
        mList = new ArrayList<>();
        StaggeredGridLayoutManager manager=null;
        manager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        mRecycler.setLayoutManager(manager);
        mRecycler.setFocusable(false);
        adapter = new PictureAdapter(mList,getContext(),this);
        mRecycler.setAdapter(adapter);
        SpacesItemDecoration decoration=new SpacesItemDecoration(16);
        mRecycler.addItemDecoration(decoration);
        refresh.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                // TODO Auto-generated method stub
                presenter.showPicture("福利",100,1);
                refresh.setRefreshing(false);
            }
        });
    }
    @Override
    public void showError(String error) {
        Toast.makeText(getContext(),error,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        mRlLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismisProgress() {
        mRlLoading.setVisibility(View.GONE);
    }

    @Override
    public void loadURlImage(List<ResultBean> list) {
        if(mList.size()==0){
            mList.addAll(list);
        }else{
            mList.clear();
            mList.addAll(list);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showActivity(ResultBean bean) {
        Intent intent = new Intent();
        intent.putExtra("url",bean.getUrl());
        intent.setClass(getContext(), ShowImageActivity.class);
        startActivity(intent);
    }

    public class SpacesItemDecoration extends RecyclerView.ItemDecoration {

        private int space;

        public SpacesItemDecoration(int space) {
            this.space=space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.left=space;
            outRect.right=space;
            outRect.bottom=space;
            if(parent.getChildAdapterPosition(view)==0){
                outRect.top=space;
            }
        }
    }
}