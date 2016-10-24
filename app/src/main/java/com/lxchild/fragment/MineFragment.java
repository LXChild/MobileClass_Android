package com.lxchild.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lxchild.bean.UserBean;
import com.lxchild.mobileclass.R;
import com.lxchild.sharePreference.AccountPref;
import com.lxchild.sharePreference.SignInPref;

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

        getActivity().setTitle(R.string.tab_classes);

        return contentView;
    }

    @Override
    public void onStart() {
        super.onStart();
        updateUser();
    }

    private void updateUser() {
        if (SignInPref.isSignIn(getContext())) {
            UserBean user = AccountPref.getSignInUser(getContext());
            // TODO load user icon
            String displayName = TextUtils.isEmpty(user.getName()) ? getString(R.string.please_sign_in) : user.getName();
            mUserName.setText(displayName);
        }
        else {
            mUserName.setText(R.string.please_sign_in);
        }
    }

    @OnClick({R.id.account_view, R.id.history, R.id.share_app, R.id.feedback, R.id.settings})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.account_view:
//                if (AccountPref.isLogon(getContext())) {
//                    UserActivity.launch(getActivity(), AccountPref.getLogonUser(getActivity()));
//                }
//                else {
//                    LoginActivity.launch(getActivity());
//                }
                break;
            case R.id.history:
                // TODO
                break;

            case R.id.share_app:
 //               SharePlatform.share(getActivity());
                break;

            case R.id.feedback:
 //               FeedbackPlatform.openFeedback(getActivity());
                break;

            case R.id.settings:
 //               SettingsActivity.launch(getActivity());
                break;
        }
    }
}
