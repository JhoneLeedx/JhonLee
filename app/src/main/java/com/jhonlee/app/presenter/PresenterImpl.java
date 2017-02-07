package com.jhonlee.app.presenter;


import com.jhonlee.app.bean.Token;
import com.jhonlee.app.contract.Contract;
import com.jhonlee.app.model.ModelImpl;

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


    public PresenterImpl(Contract.View view) {
        this.view = view;
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
}