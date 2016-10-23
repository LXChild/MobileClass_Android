package com.lxchild.signin.presenter;

import android.content.Context;

import com.lxchild.bean.UserBean;
import com.lxchild.signin.model.ISignInModel;
import com.lxchild.signin.model.SignInModel;
import com.lxchild.signin.view.ISignInView;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by LXChild on 22/10/2016.
 */

public class SignInPresenter {
    private ISignInView mSignInView;
    private ISignInModel mSignInModel;

    public SignInPresenter(Context context, ISignInView view) {
        mSignInView = view;
        mSignInModel = new SignInModel(context);
        Observable observable = (Observable) mSignInModel;
        observable.addObserver((Observer) mSignInView);
    }

    public void saveUser(String userName, String password) {
        mSignInModel.setUserName(userName);
        mSignInModel.setPassword(password);
    }

    public void loadUser() {
        UserBean user = mSignInModel.load();
        mSignInView.setUserName(user.getName());//通过调用IUserView的方法来更新显示
        mSignInView.setPassword(user.getPassword());
    }

    public void getAgnomenCode() {
        mSignInModel.getAgnomenCode();
    }

    public void verifyUser(String userName, String password, String agnomen) {
        saveUser(userName, password);
        mSignInModel.verifyUser(userName, password, agnomen);
    }
}
