package com.lxchild.signin.model;

import android.content.Context;
import android.os.Message;

import com.lxchild.bean.UserBean;
import com.lxchild.sharePreference.AccountPref;
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

public class SignInModel extends Observable implements ISignInModel {

    private final String AGNOMEN_CODE_URL = "http://cityjw.dlut.edu.cn:7001/ACTIONVALIDATERANDOMPICTURE.APPPROCESS";
    private final String SIGN_IN_URL = "http://cityjw.dlut.edu.cn:7001/ACTIONLOGON.APPPROCESS";
    private final String applicant = "ACTIONQUERYSTUDENTSCHEDULEBYSELF";

    private Context mContext;
    public SignInModel(Context context) {
        super();
        this.mContext = context;
    }

    @Override
    public void setUserName(String userName) {
        AccountPref.setUserName(mContext, userName);
    }

    @Override
    public void setPassword(String password) {
        AccountPref.setPassword(mContext, password);
    }

    @Override
    public UserBean load() {
        String userName = AccountPref.getUserName(mContext);
        String password = AccountPref.getPassword(mContext);
        return new UserBean(userName, password);
    }

    private Message msg = new Message();
    public void getAgnomenCode() {

        OkGo.get(AGNOMEN_CODE_URL)
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
     * @param userName 用户名
     * @param password 密码
     * @param agnomen 附加码
     * */
    public void verifyUser(String userName, String password, String agnomen) {
        HttpParams params = new HttpParams();
        params.put("WebUserNO", userName);//
        params.put("Password", password);
        params.put("Agnomen", agnomen);
        params.put("applicant", applicant);
        OkGo.post(SIGN_IN_URL)
                .params(params)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        if (response.code() == 200) {
                            setChanged();
                            msg.obj = ParseHTML.getScheduleList(s);
                            msg.what = 0x02;
                            notifyObservers(msg);
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
}
