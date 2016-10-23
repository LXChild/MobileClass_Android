package com.lxchild.signin.model;

import android.content.Context;

import com.lxchild.bean.UserBean;
import com.lxchild.sharePreference.AccountPref;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;

import java.io.File;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by LXChild on 22/10/2016.
 */

public class SignInModel extends Observable implements ISignInModel {

    private String cookie = "";
    private final String AGNOMEN_CODE_URL = "http://cityjw.dlut.edu.cn:7001/ACTIONVALIDATERANDOMPICTURE.APPPROCESS";
    private final String SIGN_IN_URL = "http://cityjw.dlut.edu.cn:7001/ACTIONLOGON.APPPROCESS";

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

    public void getAgnomenCode() {

        OkGo.post(AGNOMEN_CODE_URL).tag(this)
                .execute(new FileCallback(System.currentTimeMillis() + ".jpeg") {
                    @Override
                    public void onSuccess(File file, Call call, Response response) {
                        setObj(file.getPath());
                        setChanaged(true);
                        notifyObservers();
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        setObj(null);
                        setChanaged(true);
                        notifyObservers();
                        e.printStackTrace();
                        super.onError(call, response, e);
                    }
                });
    }

    public boolean verifyUser(String userName, String password) {
//        OkGo.post(SIGN_IN_URL)
//                .tag(this)
//                .addCookie("JSESSIONID", cookie)
//                .execute(new StringCallback() {
//                    @Override
//                    public void onSuccess(String s, Call call, Response response) {
//                        //上传成功
//                    }
//
//                    @Override
//                    public void upProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
//                        //这里回调上传进度(该回调在主线程,可以直接更新ui)
//                    }
//                });
        return false;
    }
}
