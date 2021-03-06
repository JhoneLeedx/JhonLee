package com.jhonlee.app.model;


import com.jhonlee.app.bean.DayLife;
import com.jhonlee.app.bean.Token;
import com.jhonlee.app.contract.Contract;
import com.jhonlee.app.network.ApiService;

import rx.Observable;

/**
* Created by JhoneLee on 2017/02/06
*/

public class ModelImpl implements Contract.Model{

    @Override
    public Observable<Token> showPicture(String name,int num) {
        return ApiService.getApiservice().getFulipicture(name,num);
    }

    @Override
    public Observable<DayLife> ShowMessage(String time) {
        return ApiService.getApiservice().ShowMessage(time);
    }
}