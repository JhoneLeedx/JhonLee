package com.jhonlee.app.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by JhoneLee on 2017/2/7.
 */

public class DayLife implements Parcelable{


    /**
     * category : ["iOS","拓展资源","Android","福利","瞎推荐","前端","休息视频"]
     * error : false
     * results : {"Android":[{"_id":"586ddc96421aa9316407fb5e","createdAt":"2017-01-05T13:41:42.513Z","desc":"贝塞尔Loading\u2014\u2014化学风暴","publishedAt":"2017-01-06T13:20:19.591Z","source":"web","type":"Android","url":"http://mp.weixin.qq.com/s?__biz=MzAxNzMxNzk5OQ==&mid=2649485066&idx=1&sn=6f9e3b2fc27835aa3cdf8d88103383d9&chksm=83f8260ab48faf1cbf90aa0e174ed7c0522b887fc9c0f1becb221b701d7e317dca572069ecbb#rd","used":true,"who":"xuyisheng"},{"_id":"586e2f4c421aa93161103da7","createdAt":"2017-01-05T19:34:36.54Z","desc":"仿小红书图片标签","images":["http://img.gank.io/e353b213-cfb8-4dd1-9f02-4cb757f0c449"],"publishedAt":"2017-01-06T13:20:19.591Z","source":"web","type":"Android","url":"https://github.com/shellljx/TagViewGroup","used":true,"who":"li jinxiang"},{"_id":"586f0566421aa9315bfbe827","createdAt":"2017-01-06T10:48:06.166Z","desc":"一键接入Tinker","publishedAt":"2017-01-06T13:20:19.591Z","source":"web","type":"Android","url":"http://w4lle.github.io/2017/01/05/one-key-for-tinker/","used":true,"who":"王令龙"},{"_id":"586f1d0c421aa9316407fb6a","createdAt":"2017-01-06T12:29:00.43Z","desc":"通过贝塞尔曲线实现的药瓶和液体效果","images":["http://img.gank.io/77339fbf-d557-457e-a17e-aa17993d6790"],"publishedAt":"2017-01-06T13:20:19.591Z","source":"chrome","type":"Android","url":"https://github.com/Ajian-studio/GABottleLoading","used":true,"who":"代码家"},{"_id":"586f26b3421aa9315ea7990d","createdAt":"2017-01-06T13:10:11.354Z","desc":"Aircrack-ng Android GUI程序，用于攻击无线网络。","publishedAt":"2017-01-06T13:20:19.591Z","source":"chrome","type":"Android","url":"https://github.com/chrisk44/Hijacker","used":true,"who":"代码家"},{"_id":"586f2708421aa93161103db3","createdAt":"2017-01-06T13:11:36.315Z","desc":"Android 动态 Menu 菜单 Demo，做的很棒。","images":["http://img.gank.io/17fa781a-92f9-46a3-b9a9-1848ce643ad2"],"publishedAt":"2017-01-06T13:20:19.591Z","source":"chrome","type":"Android","url":"https://github.com/adonixis/android-animated-menu-items","used":true,"who":"代码家"}],"iOS":[{"_id":"58632da3421aa94db821c254","createdAt":"2016-12-28T11:12:35.806Z","desc":"iOS Runtime之五：方法与消息","images":["http://img.gank.io/0d3249e5-512f-4171-89e9-f17465edff14"],"publishedAt":"2017-01-06T13:20:19.591Z","source":"chrome","type":"iOS","url":"http://www.imlifengfeng.com/blog/?p=398&come=gank","used":true,"who":"feng"},{"_id":"58632dbb421aa94dbe2ccd9c","createdAt":"2016-12-28T11:12:59.602Z","desc":"iOS Runtime之六：Method Swizzling","publishedAt":"2017-01-06T13:20:19.591Z","source":"chrome","type":"iOS","url":"http://www.imlifengfeng.com/blog/?p=400","used":true,"who":"feng"}],"休息视频":[{"_id":"586ef725421aa9315ea79907","createdAt":"2017-01-06T09:47:17.539Z","desc":"首富马云小品首秀，搭档宋小宝却当面把首富们都损了一遍","publishedAt":"2017-01-06T13:20:19.591Z","source":"chrome","type":"休息视频","url":"http://www.toutiao.com/i6372124630777332226/?tt_from=weixin&utm_campaign=client_share&app=news_article&utm_source=weixin&iid=7132594338&utm_medium=toutiao_android&wxshare_count=1","used":true,"who":"daimajia"}],"前端":[{"_id":"586e6400421aa9316407fb64","createdAt":"2017-01-05T23:19:28.548Z","desc":"vue插件-简洁好用的markdown编辑器","publishedAt":"2017-01-06T13:20:19.591Z","source":"web","type":"前端","url":"https://github.com/F-loat/vue-simplemde","used":true,"who":"柴茂源"}],"拓展资源":[{"_id":"586dd4ed421aa9316407fb5c","createdAt":"2017-01-05T13:09:01.289Z","desc":"ITCoder WeeklyBlog 第八期","publishedAt":"2017-01-06T13:20:19.591Z","source":"web","type":"拓展资源","url":"http://itscoder.com/weeklyblog-phase-8/","used":true,"who":null}],"瞎推荐":[{"_id":"586e5e31421aa9316407fb62","createdAt":"2017-01-05T22:54:41.373Z","desc":"某熊周刊:一周推荐外文技术资料（1.1）","publishedAt":"2017-01-06T13:20:19.591Z","source":"chrome","type":"瞎推荐","url":"https://zhuanlan.zhihu.com/p/24739573","used":true,"who":"王下邀月熊"}],"福利":[{"_id":"586e1aad421aa9315ea79905","createdAt":"2017-01-05T18:06:37.810Z","desc":"1-5","publishedAt":"2017-01-06T13:20:19.591Z","source":"chrome","type":"福利","url":"http://ww4.sinaimg.cn/large/610dc034gw1fbfwwsjh3zj20u00u00w1.jpg","used":true,"who":"daimajia"}]}
     */

    private boolean error;
    private ResultsBean results;
    private List<String> category;

    public DayLife(Parcel in) {
        error = in.readByte() != 0;
        results = in.readParcelable(ResultsBean.class.getClassLoader());
        category = in.createStringArrayList();
    }

    public static final Creator<DayLife> CREATOR = new Creator<DayLife>() {
        @Override
        public DayLife createFromParcel(Parcel in) {
            return new DayLife(in);
        }

        @Override
        public DayLife[] newArray(int size) {
            return new DayLife[size];
        }
    };

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public ResultsBean getResults() {
        return results;
    }

    public void setResults(ResultsBean results) {
        this.results = results;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte((byte) (error ? 1 : 0));
        parcel.writeParcelable(results, i);
        parcel.writeStringList(category);
    }
}
