package com.jhonlee.app.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.jhonlee.app.R;
import com.jhonlee.app.bean.ResultBean;
import com.jhonlee.app.contract.Contract;
import com.jhonlee.app.presenter.PresenterImpl;
import com.jhonlee.app.util.RecyclerViewDivider;
import com.jhonlee.app.view.activity.DetailActivity;
import com.jhonlee.app.view.adapter.AndroidAdapter;
import com.jhonlee.app.view.listener.ResultListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by JhoneLee on 2017/2/6.
 */

public class AndroidFragment extends Fragment implements Contract.View,ResultListener{

    @BindView(R.id.refresh)
    protected SwipeRefreshLayout refresh;
    @BindView(R.id.recycler)
    protected RecyclerView mRecycler;
    @BindView(R.id.rl_loading)
    protected RelativeLayout mRlLoading;

    private Contract.Presenter presenter;
    private AndroidAdapter anAdapter;
    private List<ResultBean> mList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_index,container,false);
        ButterKnife.bind(this,view);
        presenter = new PresenterImpl(this);
        presenter.showPicture("Android",30);
        initRecyclerView();
        return view;
    }
    public void initRecyclerView(){
        mList = new ArrayList<>();
        LinearLayoutManager manager=null;
        manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycler.setLayoutManager(manager);
        mRecycler.addItemDecoration(new RecyclerViewDivider(getActivity(),RecyclerViewDivider.VERTICAL_LIST));
        anAdapter = new AndroidAdapter(mList,getContext(),this);
        mRecycler.setAdapter(anAdapter);
        refresh.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                // TODO Auto-generated method stub
                presenter.showPicture("Android",30);
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
        anAdapter.notifyDataSetChanged();
    }


    @Override
    public void showActivity(ResultBean bean) {
        Intent intent = new Intent();
        intent.putExtra("url",bean.getUrl());
        intent.setClass(getActivity(), DetailActivity.class);
        startActivity(intent);
    }
}
