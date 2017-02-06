package com.jhonlee.app.contract;

import com.jhonlee.app.bean.ResultBean;
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

    public interface Presenter {
        void showPicture(String name,int num, int page);
    }

    public interface Model {
        Observable<Token> showPicture(String name,int num, int page);
    }
}
