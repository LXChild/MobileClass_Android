package com.lxchild.mine;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.lxchild.bean.UserBean;
import com.lxchild.mobileclass.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mingjun on 16/9/5.
 */
public class UserCardLayout extends FrameLayout {

    @BindView(R.id.user_icon)
    ImageView mUserIcon;
    @BindView(R.id.username)
    TextView mUsername;
    @BindView(R.id.tv_user_identity)
    TextView mTv_userIdentity;
    @BindView(R.id.bio)
    TextView mBio;

    private Context mContext;

    public UserCardLayout(Context context) {
        super(context);
        init(context);
    }

    public UserCardLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public UserCardLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        LayoutInflater.from(getContext()).inflate(R.layout.layout_usercard, this);
        ButterKnife.bind(this);
    }

    public void setUser(UserBean userBean) {
        // TODO set user icon

        mUsername.setText(userBean.getName());
        if (userBean.getIdentity() == UserBean.UserIdentity.administrator) {
            mTv_userIdentity.setText(mContext.getString(R.string.user_identity_admin));
        } else if (userBean.getIdentity() == UserBean.UserIdentity.teacher) {
            mTv_userIdentity.setText(mContext.getString(R.string.user_identity_teacher));
        } else if (userBean.getIdentity() == UserBean.UserIdentity.teacher) {
            mTv_userIdentity.setText(mContext.getString(R.string.user_identity_student));
        }

        if (TextUtils.isEmpty(userBean.getBio())) {
            mBio.setVisibility(View.GONE);
        } else {
            mBio.setVisibility(View.VISIBLE);
            mBio.setText(userBean.getBio());
        }
    }
}
