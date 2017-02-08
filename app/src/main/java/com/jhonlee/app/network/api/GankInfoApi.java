package com.jhonlee.app.network.api;

import com.jhonlee.app.bean.DayLife;
import com.jhonlee.app.bean.Token;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by JhoneLee on 2017/2/6.
 */

public interface GankInfoApi {

    @GET("{name}/{num}")
    Observable<Token> showFulipicture(@Path("name")String name,@Path("num")int num);

}
