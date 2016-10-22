package com.lxchild.mobileclass;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.lxchild.fragment.ClassListFragment;
import com.lxchild.fragment.ForumFragment;
import com.lxchild.fragment.MineFragment;
import com.lxchild.fragment.MyClassFragment;
import com.lxchild.search.controller.SearchActivity;
import com.orhanobut.logger.Logger;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
//    static {
//        System.loadLibrary("native-lib");
//    }
    @BindView(R.id.toolBar)
    Toolbar mToolbar;


    public static void launch(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        // Example of a call to a native method
        //TextView tv = (TextView) findViewById(R.id.sample_text);
        //   tv.setText(stringFromJNI());
        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(mTabClickListener);
    }


    private OnTabSelectListener mTabClickListener = new OnTabSelectListener() {
        @Override
        public void onTabSelected(@IdRes int tabId) {
            Logger.d("onTabSelected");
            changeTitle(tabId);
            switchMenu(getFragmentName(tabId));
        }
    };

    private String getFragmentName(int menuId) {
        switch (menuId) {
            case R.id.tab_classes:
                return ClassListFragment.class.getName();
            case R.id.tab_my_class:
                return MyClassFragment.class.getName();
            case R.id.tab_forum:
                return ForumFragment.class.getName();
            case R.id.tab_mine:
                return MineFragment.class.getName();

            default:
                return null;
        }
    }

    private Fragment mCurrentFragment;
    private FragmentManager mFragmentManager = getSupportFragmentManager();
    private void switchMenu(String fragmentName) {

        Fragment fragment = mFragmentManager.findFragmentByTag(fragmentName);

        if (fragment != null) {
            if (fragment == mCurrentFragment) return;

            mFragmentManager.beginTransaction().show(fragment).commit();
        } else {
            fragment = Fragment.instantiate(this, fragmentName);
            mFragmentManager.beginTransaction().add(R.id.content_frame, fragment, fragmentName).commit();
        }

        if (mCurrentFragment != null) {
            mFragmentManager.beginTransaction().hide(mCurrentFragment).commit();
        }

        mCurrentFragment = fragment;
    }

    private void changeTitle(int menuId) {
        switch (menuId) {
            case R.id.tab_classes:
                setTitle(R.string.tab_classes);
                break;
            case R.id.tab_my_class:
                setTitle(R.string.tab_my_class);
                break;
            case R.id.tab_forum:
                setTitle(R.string.tab_forum);
                break;
            case R.id.tab_mine:
                setTitle(R.string.tab_mine);
                break;

            default:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_search:
                SearchActivity.launch(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private long mLastBackTime = 0;
    @Override
    public void onBackPressed() {
        // finish while click back key 2 times during 1s.
        if ((System.currentTimeMillis() - mLastBackTime) < 1000) {
            finish();
        } else {
            mLastBackTime = System.currentTimeMillis();
            Toast.makeText(this, R.string.exit_click_back_again, Toast.LENGTH_SHORT).show();
        }
    }
    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
 //   public native String stringFromJNI();
}
