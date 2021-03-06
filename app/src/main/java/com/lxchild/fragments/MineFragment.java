package com.lxchild.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lxchild.classTest.paperList.view.PaperListActivity;
import com.lxchild.mine.MineDetailActivity;
import com.lxchild.mobileclass.R;
import com.lxchild.sharePreference.SignInPref;
import com.lxchild.sharePreference.UserBeanPref;
import com.lxchild.signin.view.SignInActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by LXChild on 22/10/2016.
 */

public class MineFragment extends Fragment {

    @BindView(R.id.user_icon)
    ImageView mUserIcon;
    @BindView(R.id.username)
    TextView mUserName;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_mine, container, false);
        ButterKnife.bind(this, contentView);

        return contentView;
    }

    @Override
    public void onStart() {
        super.onStart();
        updateUser();
    }

    private void updateUser() {
        if (SignInPref.isSignIn(getContext())) {
            String userName = UserBeanPref.getUserName(getContext());
            // TODO load user icon
            String displayName = userName == null ? getString(R.string.please_sign_in) : userName;
            mUserName.setText(displayName);
        }
        else {
            mUserName.setText(R.string.please_sign_in);
        }
    }

    @OnClick({R.id.account_view, R.id.tv_mine_paper, R.id.share_app, R.id.feedback, R.id.settings})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.account_view:
                if (SignInPref.isSignIn(getContext())) {
                    MineDetailActivity.launch(getActivity(), UserBeanPref.getUserName(getContext()));
                }
                break;
            case R.id.tv_mine_paper:
                PaperListActivity.launch(getContext());
                break;
            case R.id.share_app:
 //               SharePlatform.share(getActivity());
                break;

            case R.id.feedback:
 //               FeedbackPlatform.openFeedback(getActivity());
                break;

            case R.id.settings:
 //               SettingsActivity.launch(getActivity());
                SignInPref.setAlreadySignOut(getContext());
                SignInActivity.launch(getContext());
                getActivity().finish();
                break;
        }
    }
}
