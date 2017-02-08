package com.jhonlee.app.network.api;

import com.jhonlee.app.bean.DayLife;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by JhoneLee on 2017/2/8.
 */

public interface DayApi {

    @GET("{time}")
    Observable<DayLife> ShowMessage(@Path("time")String time);
}
