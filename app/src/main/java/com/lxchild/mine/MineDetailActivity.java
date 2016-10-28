package com.lxchild.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.lxchild.base.BaseLoadingActivity;
import com.lxchild.mobileclass.R;
import com.lxchild.sharePreference.UserBeanPref;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MineDetailActivity extends BaseLoadingActivity {


    private static final String EXTRA_USER_NAME = "extra_user_name";

    @BindView(R.id.user_card)
    UserCardLayout mUserCard;

    public static void launch(Context context, String userName) {
        Intent intent = new Intent(context, MineDetailActivity.class);
        intent.putExtra(EXTRA_USER_NAME, userName);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_detail);
        initView();
    }

    private void initView() {
        ButterKnife.bind(this);

        try {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        setTitle(getIntent().getStringExtra(EXTRA_USER_NAME));
        mUserCard.setUser(UserBeanPref.getSignInUser(this));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick({R.id.layout_access, R.id.starred_layout, R.id.following_layout, R.id.followers_layout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_access:
                RequestAccessActivity.launch(this);
                break;

            case R.id.starred_layout:
               // RepoListActivity.launchToShowStars(this, mUsername);
                break;

            case R.id.following_layout:
              //  UserListActivity.launchToShowFollowing(this, mUsername);
                break;

            case R.id.followers_layout:
               // UserListActivity.launchToShowFollowers(this, mUsername);
                break;
        }
    }

}
