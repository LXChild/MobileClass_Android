package com.lxchild.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lxchild.classListFragments.ContentAdapter;
import com.lxchild.classListFragments.HeaderAdapter;
import com.lxchild.classListFragments.RecyclerViewFragment;
import com.lxchild.intrface.HeaderViewPagerFragment;
import com.lxchild.mobileclass.R;
import com.lzy.widget.HeaderViewPager;
import com.lzy.widget.tab.CircleIndicator;
import com.lzy.widget.tab.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ClassListFragment extends Fragment {

    public List<HeaderViewPagerFragment> mFragments;

//    @BindView(R.id.titleBar)
//    View titleBar;
    //    @BindView(R.id.bg)
//    View titleBar_Bg;

//    @BindView(R.id.title)
//    TextView titleBar_title;

//    @BindView(R.id.status_bar_fix)
//    View status_bar_fix;

    @BindView(R.id.scrollableLayout)
    HeaderViewPager scrollableLayout;

    @BindView(R.id.pagerHeader)
    ViewPager pagerHeader;

    @BindView(R.id.ci)
    CircleIndicator ci;

    //tab标签和内容viewpager
    @BindView(R.id.tabs)
    PagerSlidingTabStrip tabs;

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View contentView = inflater.inflate(R.layout.fragment_classes_main, container, false);        ButterKnife.bind(this, contentView);
        ButterKnife.bind(this, contentView);

        initView();

        return contentView;
    }

    private void initView() {
        mFragments = new ArrayList<>();
        mFragments.add(RecyclerViewFragment.newInstance());
//        titleBar_Bg.setAlpha(0);
//        status_bar_fix.setAlpha(0);
//        titleBar_title.setText("标题栏透明度(0%)");

        pagerHeader.setAdapter(new HeaderAdapter(getContext()));
        ci.setViewPager(pagerHeader);

        viewPager.setAdapter(new ContentAdapter(getActivity().getSupportFragmentManager(), mFragments));
        tabs.setViewPager(viewPager);
        scrollableLayout.setCurrentScrollableContainer(mFragments.get(0));
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                scrollableLayout.setCurrentScrollableContainer(mFragments.get(position));
            }
        });

//        scrollableLayout.setOnScrollListener(new HeaderViewPager.OnScrollListener() {
//            @Override
//            public void onScroll(int currentY, int maxY) {
//                //让头部具有差速动画,如果不需要,可以不用设置
//                pagerHeader.setTranslationY(currentY / 2);
//                //动态改变标题栏的透明度,注意转化为浮点型
//                float alpha = 1.0f * currentY / maxY;
//                titleBar_Bg.setAlpha(alpha);
//                //注意头部局的颜色也需要改变
//                status_bar_fix.setAlpha(alpha);
//                titleBar_title.setText("标题栏透明度(" + (int) (alpha * 100) + "%)");
//            }
//        });
        viewPager.setCurrentItem(0);
    }
}
