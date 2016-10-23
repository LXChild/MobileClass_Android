package com.lxchild.signin.presenter;

import android.content.Context;

import com.lxchild.bean.UserBean;
import com.lxchild.signin.model.ISignInModel;
import com.lxchild.signin.model.Observable;
import com.lxchild.signin.model.SignInModel;
import com.lxchild.signin.view.IObserver;
import com.lxchild.signin.view.ISignInView;

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
        observable.addObserver((IObserver) mSignInView);
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

    public boolean verifyUser(String userName, String password) {
        saveUser(userName, password);
        return mSignInModel.verifyUser(userName, password);
    }
}
