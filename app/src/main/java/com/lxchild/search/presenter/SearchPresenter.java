package com.lxchild.search.presenter;

import com.lxchild.search.model.ISearchModel;
import com.lxchild.search.model.SearchModelImpl;
import com.lxchild.search.view.ISearchView;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by LXChild on 24/10/2016.
 */

public class SearchPresenter {

    private ISearchModel mSearchModel;

    public SearchPresenter(ISearchView searchView) {
        mSearchModel = new SearchModelImpl();
        Observable observable = (Observable) mSearchModel;
        observable.addObserver((Observer) searchView);
    }

    public void loadData() {
        mSearchModel.loadData();
    }

    public void searchClass(String key) {
        mSearchModel.searchData(key);
    }
}
