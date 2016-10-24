package com.lxchild.classListFragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

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

    private String[] titles = new String[]{"课程一", "课程二", "课程三"};

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
