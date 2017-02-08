package com.jhonlee.app.presenter;


import android.util.Log;

import com.jhonlee.app.bean.DayLife;
import com.jhonlee.app.bean.ResultBean;
import com.jhonlee.app.bean.Token;
import com.jhonlee.app.contract.Contract;
import com.jhonlee.app.model.ModelImpl;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
* Created by JhoneLee on 2017/02/06
*/

public class PresenterImpl implements Contract.Presenter {

    private Contract.View view;
    private Contract.Model model;
    private Contract.DayView dayView;

    public PresenterImpl(Contract.View view) {
        this.view = view;
        model = new ModelImpl();
    }

    public PresenterImpl(Contract.DayView view) {
        this.dayView = view;
        model = new ModelImpl();
    }
    @Override
    public void showPicture(String name,int num) {
        view.showProgress();
        Observable<Token> observable = model.showPicture(name,num);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Token>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                view.dismisProgress();
                view.showError(e.getMessage());
            }

            @Override
            public void onNext(Token token) {
                view.dismisProgress();
                if (token.isError()) {
                    view.showError("访问错误。。。");
                    return;
                }
                view.loadURlImage(token.getResults());
            }
        });
    }

    @Override
    public void showMessage(String time) {
        dayView.showProgress();
        Observable<DayLife> observable = model.ShowMessage(time);
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DayLife>() {
            @Override
            public void onCompleted() {
                Log.d("Jhoe Lee","onCompleted() ");
            }

            @Override
            public void onError(Throwable e) {
                dayView.dismisProgress();
                dayView.showError(e.getMessage());
            }

            @Override
            public void onNext(DayLife dayLife) {
                Log.d("Jhoe Lee","onNext() ");
                dayView.dismisProgress();
                if (dayLife.isError()) {
                    dayView.showError("访问错误。。。");
                    return;
                }
                dayView.load(getList(dayLife));
            }
        });
    }
    private List<ResultBean> getList(DayLife dayLife){
        List<ResultBean> list = new ArrayList<ResultBean>();
        if (dayLife!=null) {
            if (dayLife.getResults() != null) {
                List<ResultBean> android = dayLife.getResults().getAndroid();
                List<ResultBean> ios = dayLife.getResults().getiOS();
                List<ResultBean> expand = dayLife.getResults().get拓展资源();
                List<ResultBean> js = dayLife.getResults().get前端();
                List<ResultBean> pic = dayLife.getResults().get福利();
                List<ResultBean> recommend = dayLife.getResults().get瞎推荐();
                List<ResultBean> video = dayLife.getResults().get休息视频();
                if (pic != null) list.addAll(pic);
                if (recommend != null) list.addAll(recommend);
                if (video != null) list.addAll(video);
                if (android != null) list.addAll(android);
                if (ios != null) list.addAll(ios);
                if (expand != null) list.addAll(expand);
                if (js != null) list.addAll(js);

            }
        }
        return list;
    }
}