package com.lxchild.DataOperatorMVP;

import com.lxchild.base.MVP.IBasePresenter;
import com.lxchild.base.MVP.IBaseView;

/**
 * Created by LXChild on 15/11/2016.
 */

public interface DataContract {
    interface View extends IBaseView<Presenter> {
        void setLoadingIndicator(boolean active);
    }

    interface Presenter extends IBasePresenter {
        void endTask();
        void deleteTask();
    }
}
