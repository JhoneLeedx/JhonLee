package com.jhonlee.app.network;


import com.jhonlee.app.bean.Token;
import com.jhonlee.app.network.api.GankInfoApi;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;


/**
 * Created by JhoneLee on 2017/2/6.
 */
public class ApiService {

    /**
     * 分类数据: http://gank.io/api/data/数据类型/请求个数/第几页
     * •数据类型： 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
     * •请求个数： 数字，大于0
     * •第几页：数字，大于0
     * <p>
     * 例：•http://gank.io/api/data/Android/10/1
     * •http://gank.io/api/data/福利/10/1
     * •http://gank.io/api/data/iOS/20/2
     * •http://gank.io/api/data/all/20/2
     * <p>
     * 每日数据： http://gank.io/api/day/年/月/日
     * <p>
     * 例：
     * •http://gank.io/api/day/2015/08/06
     * <p>
     * 随机数据：http://gank.io/api/random/data/分类/个数
     * •数据类型：福利 | Android | iOS | 休息视频 | 拓展资源 | 前端
     * •个数： 数字，大于0
     * <p>
     * 例：•http://gank.io/api/random/data/Android/20
     */

    private static ApiService apiService;
    private Retrofit retrofit;

    public static final String URL_HOST ="http://gank.io/api/random/data/";

    private ApiService(){
        //初始化retrofit
        retrofit = new Retrofit.Builder()
                .baseUrl(URL_HOST)
                .client(genericClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        //创建实例
        pApi = retrofit.create(GankInfoApi.class);
    }

    private GankInfoApi pApi ;
    public Observable<Token> getFulipicture(String name,int num){
        Observable<Token> observable = pApi.showFulipicture(name,num);
        return observable;
    }

    //单例实现
    public synchronized static ApiService getApiservice(){
        if (apiService==null){
            apiService = new ApiService();
        }
        return apiService;
    }
    //快速添加header。。
    private  static OkHttpClient genericClient() {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                                .build();
                        return chain.proceed(request);
                    }
                }).build();
        return httpClient;
    }


}

