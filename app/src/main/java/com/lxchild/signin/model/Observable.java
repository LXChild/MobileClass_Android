package com.lxchild.signin.model;

import com.lxchild.signin.view.IObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LXChild on 23/10/2016.
 */

public abstract class Observable {
    private boolean isChanaged;
    private List<IObserver> mIObservers = new ArrayList<>();
    private Object obj;

    public boolean isChanaged() {
        return isChanaged;
    }

    public void setChanaged(boolean chanaged) {
        isChanaged = chanaged;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public Observable() {
        isChanaged = false;
    }
    public void addObserver(IObserver IObserver) {
        mIObservers.add(IObserver);
    }

    public void removeObserver(IObserver IObserver) {
        mIObservers.remove(IObserver);
    }

    public void removeObservers() {
        mIObservers.clear();
    }
    public void notifyObservers() {
        if (isChanaged) {
            for (int i = 0; i < mIObservers.size(); i ++) {
                mIObservers.get(i).update(this);
            }
            isChanaged = false;
        }
    }
}
