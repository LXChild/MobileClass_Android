package com.lxchild.widget;

import android.content.Context;
import android.view.View;

import com.pnikosis.materialishprogress.ProgressWheel;

/**
 * Created by LXChild on 22/10/2016.
 */

public class LoadingView {

    private ProgressWheel mLoadingDialog;

    public LoadingView(Context context) {
        mLoadingDialog = new ProgressWheel(context);

    }

    public void show() {
        if (mLoadingDialog != null){
            mLoadingDialog.setVisibility(View.VISIBLE);
        }
    }

    public void dismiss() {
        if (mLoadingDialog != null) {
            mLoadingDialog.setVisibility(View.GONE);
        }
    }
}
