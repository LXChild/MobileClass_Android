package com.lxchild.signin.model;

import com.lxchild.bean.AccountBean;

/**
 * Created by LXChild on 22/10/2016.
 */

public interface ISignInModel {
    void setUserID(String userName);
    void setPassword (String password);
    AccountBean loadAccount();
    void getAgnomenCode();
    void verifyAccount(String userName, String password, String agnomen);
}
