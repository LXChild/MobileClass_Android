package com.lxchild.signin.model;

import android.content.Context;
import android.os.Message;

import com.lxchild.bean.AccountBean;
import com.lxchild.sharePreference.AccountPref;
import com.lxchild.sharePreference.SignInPref;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.util.Observable;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by LXChild on 22/10/2016.
 */

public class SignInModelImpl extends Observable implements ISignInModel {

    private final String mAgnomenCodeUrl = "http://cityjw.dlut.edu.cn:7001/ACTIONVALIDATERANDOMPICTURE.APPPROCESS";
    private final String mSignInUrl = "http://cityjw.dlut.edu.cn:7001/ACTIONLOGON.APPPROCESS";
    private final String mApplicant = "ACTIONQUERYSTUDENTSCHEDULEBYSELF";

    private Context mContext;
    public SignInModelImpl(Context context) {
        super();
        this.mContext = context;
    }

    @Override
    public void setUserID(String userID) {
        AccountPref.setUserID(mContext, userID);
    }

    @Override
    public void setPassword(String password) {
        AccountPref.setPassword(mContext, password);
    }

    @Override
    public AccountBean loadAccount() {
        return AccountPref.getSignInAccount(mContext);
    }

    private Message msg = new Message();
    /**
     * 使用get方式获取附加码图片并存储到本地目录
     * */
    public void getAgnomenCode() {
        OkGo.get(mAgnomenCodeUrl)
                .execute(new FileCallback(System.currentTimeMillis() + ".jpeg") {
                    @Override
                    public void onSuccess(File file, Call call, Response response) {
                        setChanged();
                        msg.obj = file.getPath();
                        msg.what = 0x01;
                        notifyObservers(msg);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        setChanged();
                        msg.obj = null;
                        msg.what = 0x01;
                        notifyObservers(msg);
                        e.printStackTrace();
                        super.onError(call, response, e);
                    }
                });
    }

    /**
     * 使用post请求验证用户身份并获取结果
     * @param userID 学号
     * @param password 密码
     * @param agnomen 附加码
     * */
    public void verifyAccount(String userID, String password, String agnomen) {
        HttpParams params = new HttpParams();
        params.put("WebUserNO", userID);//
        params.put("Password", password);
        params.put("Agnomen", agnomen);
        params.put("applicant", mApplicant);
        OkGo.post(mSignInUrl)
                .params(params)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        if (response.code() == 200) {
                            if (s.contains("错误的用户名或者密码")) {
                                setChanged();
                                msg.what = 0x02;
                                notifyObservers(msg);
                            } else if (s.contains("请输入正确的附加码")) {
                                setChanged();
                                msg.what = 0x03;
                                notifyObservers(msg);
                            } else {
                                setChanged();
                                setUserName(ParseHTML.getUserName(s));
                                msg.obj = ParseHTML.getScheduleList(s);
                                msg.what = 0x04;
                                notifyObservers(msg);
                            }
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        setChanged();
                        msg.obj = null;
                        msg.what = 0x02;
                        notifyObservers(msg);
                        Logger.d(e);
                        e.printStackTrace();
                        super.onError(call, response, e);
                    }
                });
    }

    private void setUserName(String userName) {
        SignInPref.setUserName(mContext, userName);
    }
}
