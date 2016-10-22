package com.lxchild.classListFragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lxchild.intrface.HeaderViewPagerFragment;

import java.util.List;

/**
 * Created by LXChild on 22/10/2016.
 */

/**
 * 内容页的适配器
 */
public class ContentAdapter extends FragmentPagerAdapter {

    private List<HeaderViewPagerFragment> mFragments;
    public ContentAdapter(FragmentManager fm, List<HeaderViewPagerFragment> fragments) {
        super(fm);
        this.mFragments = fragments;
    }

    public String[] titles = new String[]{"ScrollView", "ListView", "GridView", "RecyclerView", "WebView"};

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
