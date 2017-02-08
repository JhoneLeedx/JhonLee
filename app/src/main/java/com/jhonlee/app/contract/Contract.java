package com.jhonlee.app.contract;

import com.jhonlee.app.bean.DayLife;
import com.jhonlee.app.bean.ResultBean;
import com.jhonlee.app.bean.ResultsBean;
import com.jhonlee.app.bean.Token;

import java.util.List;

import rx.Observable;

/**
 * Created by JhoneLee on 2017/2/6.
 */

public class Contract {

    public interface View {
        void showError(String error);
        void showProgress();
        void dismisProgress();
        void loadURlImage(List<ResultBean> list);
    }

    public interface DayView{
        void showError(String error);
        void showProgress();
        void dismisProgress();
        void load(List<ResultBean> list);
    }

    public interface Presenter {
        void showPicture(String name,int num);
        void showMessage(String time);
    }

    public interface Model {
        Observable<Token> showPicture(String name,int num);
        Observable<DayLife> ShowMessage(String time);
    }
}
