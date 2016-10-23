package com.lxchild.signin.model;

import com.lxchild.bean.UserBean;

/**
 * Created by LXChild on 22/10/2016.
 */

public interface ISignInModel {
    void setUserName (String userName);
    void setPassword (String password);
    UserBean load();//通过id读取user信息,返回一个UserBean
    void getAgnomenCode();
    void verifyUser(String userName, String password, String agnomen);
}
