package com.lxchild.search.view;

/**
 * Created by LXChild on 24/10/2016.
 */

public interface ISearchView<M> {
    void showSearchResult(M result);

    void error(Throwable e);
}
