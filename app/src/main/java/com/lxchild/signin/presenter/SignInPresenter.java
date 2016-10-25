package com.lxchild.signin.presenter;

import android.content.Context;

import com.lxchild.bean.AccountBean;
import com.lxchild.signin.model.ISignInModel;
import com.lxchild.signin.model.SignInModelImpl;
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
        mSignInModel = new SignInModelImpl(context);
        Observable observable = (Observable) mSignInModel;
        observable.addObserver((Observer) mSignInView);
    }

    public void saveAccount(String userID, String password) {
        mSignInModel.setUserID(userID);
        mSignInModel.setPassword(password);
    }

    public void loadAcount() {
        AccountBean account = mSignInModel.loadAccount();
        mSignInView.setUserID(account.getID());// 通过调用IUserView的方法来更新显示
        mSignInView.setPassword(account.getPassword());
    }

    public void getAgnomenCode() {
        mSignInModel.getAgnomenCode();
    }

    public void verifyAccount(String userID, String password, String agnomen) {
        saveAccount(userID, password);
        mSignInModel.verifyAccount(userID, password, agnomen);
    }
}
