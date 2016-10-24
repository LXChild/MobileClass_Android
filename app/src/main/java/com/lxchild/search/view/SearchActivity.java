package com.lxchild.search.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.lxchild.base.BaseLoadingActivity;
import com.lxchild.base.BaseRecyclerAdapter;
import com.lxchild.bean.ClassBean;
import com.lxchild.mobileclass.R;
import com.lxchild.search.presenter.SearchPresenter;
import com.lxchild.utils.InputMethodUtils;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by LXChild on 22/10/2016.
 */

public class SearchActivity extends BaseLoadingActivity implements ISearchView<ArrayList<ClassBean>>, Observer {

    @BindView(R.id.search_view)
    MaterialSearchView mSearchView;
    @BindView(R.id.classes_list)
    RecyclerView mRecyclerView;

    private ClassListRecyclerAdapter mAdapter;

    private SearchPresenter mPresenter;

    public static void launch(Context context) {
        context.startActivity(new Intent(context, SearchActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
    }

    private void initView() {
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(R.string.menu_search);

        mSearchView.setVoiceSearch(false);
        mSearchView.setOnQueryTextListener(mQueryListener);
        mSearchView.post(new Runnable() {
            @Override
            public void run() {
                mSearchView.showSearch(false);
            }
        });

        mAdapter = new ClassListRecyclerAdapter(mRecyclerView, null, R.layout.item_class);


        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());//设置动画
        mRecyclerView.addItemDecoration(new RecyclerViewDecoration());//设置分割线

        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(mOnItemClickListener);

        mPresenter = new SearchPresenter(this);
        mPresenter.loadData();
    }

    private BaseRecyclerAdapter.OnItemClickListener mOnItemClickListener = new BaseRecyclerAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View view, Object data, int position) {
            // TODO go to detail page
        }
    };

    @Override
    public void update(Observable o, Object arg) {
        if (arg != null) {
            showSearchResult((ArrayList<ClassBean>)arg);
        }
    }

    private MaterialSearchView.OnQueryTextListener mQueryListener = new MaterialSearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            InputMethodUtils.hideSoftInput(SearchActivity.this);
            mSearchView.closeSearch();

            search(query);
            return true;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            return false;
        }
    };

    private void search(String key) {
        Logger.d("search, key = " + key);
        if (!TextUtils.isEmpty(key)) {
            mPresenter.searchClass(key);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        mSearchView.setMenuItem(item);
        return true;
    }

    @Override
    public void showSearchResult(ArrayList<ClassBean> classes) {
        mAdapter.setNewData(classes);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        if (mSearchView.isSearchOpen()) {
            mSearchView.closeSearch();
        } else {
            super.onBackPressed();
        }
    }
}
