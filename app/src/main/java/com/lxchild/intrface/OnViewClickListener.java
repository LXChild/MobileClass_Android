package com.lxchild.intrface;

import android.view.View;

import com.lxchild.bean.ClassBean;

/**
 * Created by LXChild on 22/10/2016.
 */

public interface OnViewClickListener {
    //此接口回调用于listView,RecyclerView的item的子View的点击事件
    void onChanageViewClick(ClassBean item, int position, View v);
}