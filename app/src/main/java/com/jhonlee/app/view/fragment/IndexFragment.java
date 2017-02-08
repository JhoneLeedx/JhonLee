package com.jhonlee.app.view.fragment;

import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jhonlee.app.R;
import com.jhonlee.app.bean.ResultBean;
import com.jhonlee.app.contract.Contract;
import com.jhonlee.app.presenter.PresenterImpl;
import com.jhonlee.app.util.RecyclerViewDivider;
import com.jhonlee.app.util.TimeUtil;
import com.jhonlee.app.view.activity.DetailActivity;
import com.jhonlee.app.view.activity.ShowImageActivity;
import com.jhonlee.app.view.adapter.ALLAdapter;
import com.jhonlee.app.view.listener.ResultListener;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by JhoneLee on 2017/2/6.
 */

public class IndexFragment extends Fragment implements Contract.DayView, ResultListener {


    @BindView(R.id.refresh)
    protected SwipeRefreshLayout refresh;
    @BindView(R.id.recycler)
    protected RecyclerView mRecycler;
    @BindView(R.id.rl_loading)
    protected RelativeLayout mRlLoading;
    @BindView(R.id.tv_nowday)
    protected TextView mNowday;

    private Contract.Presenter presenter;
    private ALLAdapter allAdapter;
    private List<ResultBean> mList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_index, container, false);
        ButterKnife.bind(this, view);
        presenter = new PresenterImpl(this);
        mNowday.setText("当前日期:"+TimeUtil.getTime());
        presenter.showMessage(mNowday.getText().toString().substring(5));
        initRecyclerView();
        return view;
    }

    public void initRecyclerView() {
        mList = new ArrayList<>();
        LinearLayoutManager manager = null;
        manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycler.setLayoutManager(manager);
        mRecycler.addItemDecoration(new RecyclerViewDivider(getActivity(), RecyclerViewDivider.VERTICAL_LIST));
        allAdapter = new ALLAdapter(mList, getContext(), this);
        mRecycler.setAdapter(allAdapter);
        refresh.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                // TODO Auto-generated method stub
                presenter.showMessage(mNowday.getText().toString().substring(5));
                refresh.setRefreshing(false);
            }
        });
    }

    @OnClick(R.id.tv_nowday)
    public void nowDay() {
        mNowday.setText("");
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(getActivity(),
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        System.out.println("年-->" + year + "月-->"
                                + monthOfYear + "日-->" + dayOfMonth);
                        mNowday.setText("当前日期:"+year + "/" + (monthOfYear+1) + "/"
                                + dayOfMonth);
                        presenter.showMessage(year + "/" + (monthOfYear+1) + "/"
                                + dayOfMonth);
                    }
                }, calendar.get(Calendar.YEAR), calendar
                .get(Calendar.MONTH), calendar
                .get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }
    @Override
    public void showError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
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
    public void load(List<ResultBean> list) {

        if (mList.size() == 0) {
            mList.addAll(list);
        } else {
            mList.clear();
            mList.addAll(list);
        }
        allAdapter.notifyDataSetChanged();
    }

    @Override
    public void showActivity(ResultBean bean) {
        Intent intent = new Intent();
        intent.putExtra("url", bean.getUrl());
        if (bean.getType().equals("福利")) {
            intent.setClass(getActivity(), ShowImageActivity.class);
        } else {
            intent.setClass(getActivity(), DetailActivity.class);
        }
        startActivity(intent);
    }
}
