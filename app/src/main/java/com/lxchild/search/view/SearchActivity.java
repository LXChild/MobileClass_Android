package com.lxchild.search.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;

import com.lxchild.base.BaseLoadingActivity;
import com.lxchild.bean.ClassBean;
import com.lxchild.mobileclass.R;
import com.lxchild.search.model.ClassListRecyclerAdapter;
import com.lxchild.utils.InputMethodUtils;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by LXChild on 22/10/2016.
 */

public class SearchActivity extends BaseLoadingActivity {

    @BindView(R.id.search_view)
    MaterialSearchView mSearchView;

    private ClassListRecyclerAdapter mAdapter;

    public static void launch(Context context) {
        context.startActivity(new Intent(context, SearchActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
    }

    @NonNull
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
    }

    private MaterialSearchView.OnQueryTextListener mQueryListener = new MaterialSearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            InputMethodUtils.hideSoftInput(SearchActivity.this);
            mSearchView.closeSearch();

            mCurrentKey = query;
            search(mCurrentKey, mCurrentLang);
            return true;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            return false;
        }
    };

    private String mCurrentKey;
    private String mCurrentLang;
    private void search(String key, String lang) {
        Logger.d("search, key = " + key + ", lang = " + lang);
        if (!TextUtils.isEmpty(key)) {
          //  mPresenter.searchRepo(key, lang);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        mSearchView.setMenuItem(item);
        return true;
    }

    public void showSearchResult(ArrayList<ClassBean> repos) {
        mAdapter.setNewData(repos);
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
