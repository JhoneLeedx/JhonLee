package com.jhonlee.app.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by JhoneLee on 2017/2/8.
 */

public class ResultsBean implements Parcelable{

    private List<ResultBean> Android;
    private List<ResultBean> iOS;
    private List<ResultBean> 休息视频;
    private List<ResultBean> 前端;
    private List<ResultBean> 拓展资源;
    private List<ResultBean> 瞎推荐;
    private List<ResultBean> 福利;

    public List<ResultBean> getAndroid() {
        return Android;
    }

    public List<ResultBean> getiOS() {
        return iOS;
    }

    public List<ResultBean> get休息视频() {
        return 休息视频;
    }

    public List<ResultBean> get前端() {
        return 前端;
    }

    public List<ResultBean> get拓展资源() {
        return 拓展资源;
    }

    public List<ResultBean> get瞎推荐() {
        return 瞎推荐;
    }

    public List<ResultBean> get福利() {
        return 福利;
    }

    protected ResultsBean(Parcel in) {
        Android = in.createTypedArrayList(ResultBean.CREATOR);
        iOS = in.createTypedArrayList(ResultBean.CREATOR);
        休息视频 = in.createTypedArrayList(ResultBean.CREATOR);
        前端 = in.createTypedArrayList(ResultBean.CREATOR);
        拓展资源 = in.createTypedArrayList(ResultBean.CREATOR);
        瞎推荐 = in.createTypedArrayList(ResultBean.CREATOR);
        福利 = in.createTypedArrayList(ResultBean.CREATOR);
    }

    public static final Creator<ResultsBean> CREATOR = new Creator<ResultsBean>() {
        @Override
        public ResultsBean createFromParcel(Parcel in) {
            return new ResultsBean(in);
        }

        @Override
        public ResultsBean[] newArray(int size) {
            return new ResultsBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(Android);
        parcel.writeTypedList(iOS);
        parcel.writeTypedList(休息视频);
        parcel.writeTypedList(前端);
        parcel.writeTypedList(拓展资源);
        parcel.writeTypedList(瞎推荐);
        parcel.writeTypedList(福利);
    }
}
