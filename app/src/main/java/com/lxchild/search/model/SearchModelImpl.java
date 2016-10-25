package com.lxchild.search.model;

import android.os.Handler;
import android.os.Message;

import com.lxchild.bean.ClassBean;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by LXChild on 24/10/2016.
 */

public class SearchModelImpl extends Observable implements ISearchModel {
    private final ArrayList<ClassBean> classBeen = new ArrayList<>();

    private static class MyHandler extends Handler {
        private WeakReference<SearchModelImpl> activityWeakReference;

        public MyHandler(SearchModelImpl activity) {
            activityWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            SearchModelImpl activity = activityWeakReference.get();
            if (activity != null) {
                activity.setChanged();
                activity.notifyObservers(activity.classBeen);
            }
        }
    }

    private MyHandler mHandler = new MyHandler(this);

    public SearchModelImpl() {
        super();
    }

    @Override
    public void loadData() {

        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 200; i++) {
                    try {
                        classBeen.add(new ClassBean(i, "name" + i, "teacher wang", "10:00 - 11:00", "1210"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                mHandler.sendEmptyMessage(0x01);
                super.run();
            }
        }.start();
    }

    @Override
    public void searchData(String key) {
        // TODO search data
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 200; i++) {
                    try {
                        classBeen.add(new ClassBean(i, "name" + i, "teacher wang", "10:00 - 11:00", "1210"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                mHandler.sendEmptyMessage(0x01);
                super.run();
            }
        }.start();
    }
}
