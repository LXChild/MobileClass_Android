package com.lxchild.signin.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lxchild.base.BaseLoadingActivity;
import com.lxchild.mobileclass.MainActivity;
import com.lxchild.mobileclass.R;
import com.lxchild.signin.model.Observable;
import com.lxchild.signin.presenter.SignInPresenter;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by LXChild on 22/10/2016.
 */

public class SignInActivity extends BaseLoadingActivity implements ISignInView, View.OnClickListener, View.OnLongClickListener, IObserver {
    @BindView(R.id.et_username)
    EditText et_name;
    @BindView(R.id.et_password)
    EditText et_pass;
    @BindView(R.id.et_agnomen)
    EditText et_agnomen;
    @BindView(R.id.login)
    Button mLoginButton;
    @BindView(R.id.login_error)
    Button mLoginError;
    @BindView(R.id.register)
    Button mRegister;
    @BindView(R.id.registfer)
    Button ONLYTEST;

    int selectIndex=1;
    int tempSelect=selectIndex;
    boolean isReLogin=false;
    private int SERVER_FLAG=0;

    private RelativeLayout countryselect;
    private TextView coutry_phone_sn, coutryName;//
    public final static int LOGIN_ENABLE=0x01;    //注册完毕了
    public final static int LOGIN_UNABLE=0x02;    //注册完毕了
    public final static int PASS_ERROR=0x03;      //注册完毕了
    public final static int NAME_ERROR=0x04;      //注册完毕了

    private static class MyHandler extends Handler {
        private WeakReference<SignInActivity> activityWeakReference;

        public MyHandler(SignInActivity activity) {
            activityWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            SignInActivity activity = activityWeakReference.get();
            if (activity != null) {
            }
        }
    }

    private MyHandler mHandler = new MyHandler(this);

    @BindView(R.id.bt_username_clear)
    Button bt_username_clear;

    @BindView(R.id.bt_pwd_clear)
    Button bt_pwd_clear;
    @BindView(R.id.bt_pwd_eye)
    Button bt_pwd_eye;

    @BindView(R.id.bt_agnomen_clear)
    Button bt_agnomen_clear;
    @BindView(R.id.bt_agnomen)
    Button bt_agnomen;

    private TextWatcher username_watcher;
    private TextWatcher password_watcher;
    private TextWatcher agnomen_watcher;

    private SignInPresenter mSignInPresenter;

    public static void launch(Context context) {
        context.startActivity(new Intent(context, SignInActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        initView();
    }

    private void initView() {
        ButterKnife.bind(this);

        bt_username_clear.setOnClickListener(this);

        bt_pwd_clear.setOnClickListener(this);
        bt_pwd_eye.setOnClickListener(this);

        bt_agnomen_clear.setOnClickListener(this);
        bt_agnomen.setOnClickListener(this);

        initWatcher();

        et_name.addTextChangedListener(username_watcher);
        et_pass.addTextChangedListener(password_watcher);
        et_agnomen.addTextChangedListener(agnomen_watcher);

        ONLYTEST.setOnClickListener(this);
        ONLYTEST.setOnLongClickListener(this);

        mLoginButton.setOnClickListener(this);
        mLoginError.setOnClickListener(this);
        mRegister.setOnClickListener(this);

        mSignInPresenter = new SignInPresenter(this, this);
        mSignInPresenter.getAgnomenCode();
        mSignInPresenter.loadUser();
    }

    /**
     * 手机号，密码输入控件公用这一个watcher
     */
    private void initWatcher() {
        username_watcher = new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void afterTextChanged(Editable s) {
                et_pass.setText("");
                if (s.toString().length() > 0) {
                    bt_username_clear.setVisibility(View.VISIBLE);
                } else {
                    bt_username_clear.setVisibility(View.INVISIBLE);
                }
            }
        };
        password_watcher = new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            public void beforeTextChanged(CharSequence s, int start, int count,int after) {}
            public void afterTextChanged(Editable s) {
                if(s.toString().length()>0){
                    bt_pwd_clear.setVisibility(View.VISIBLE);
                }else{
                    bt_pwd_clear.setVisibility(View.INVISIBLE);
                }
            }
        };
        agnomen_watcher = new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            public void beforeTextChanged(CharSequence s, int start, int count,int after) {}
            public void afterTextChanged(Editable s) {
                if(s.toString().length()>0){
                    bt_agnomen_clear.setVisibility(View.VISIBLE);
                }else{
                    bt_agnomen_clear.setVisibility(View.INVISIBLE);
                }
            }
        };
    }

    @Override
    public void update(Observable observable) {
        if (observable.getObj() == null) {
            Toast.makeText(this, "get agnomen code failed!", Toast.LENGTH_SHORT).show();
            return;
        }
        bt_agnomen.setBackground(new BitmapDrawable(null, (String) observable.getObj()));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:  //登陆
                // TODO login
                mSignInPresenter.saveUser(getUserName(), getPassword());
                mSignInPresenter.verifyUser(getUserName(), getPassword());
                MainActivity.launch(this);
                break;
            case R.id.login_error: //无法登陆(忘记密码了吧)
                // TODO go to forget password activity
                break;
            case R.id.register:    //注册新的用户
                // TODO go to forget register activity
                break;
            case R.id.registfer:
                if(SERVER_FLAG>10){
                    Toast.makeText(this, "[内部测试--谨慎操作]", Toast.LENGTH_SHORT).show();
                }
                SERVER_FLAG++;
                break;
            case R.id.bt_username_clear:
                et_name.setText("");
                et_pass.setText("");
                break;
            case R.id.bt_pwd_clear:
                et_pass.setText("");
                break;
            case R.id.bt_pwd_eye:
                if(et_pass.getInputType() == (InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD)){
                    bt_pwd_eye.setBackgroundResource(R.mipmap.ic_launcher);
                    et_pass.setInputType(InputType.TYPE_CLASS_TEXT);
                }else{
                    bt_pwd_eye.setBackgroundResource(R.mipmap.ic_launcher);
                    et_pass.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                et_pass.setSelection(et_pass.getText().toString().length());
                break;
            case R.id.bt_agnomen_clear:
                et_agnomen.setText("");
                break;
            case R.id.bt_agnomen:
                mSignInPresenter.getAgnomenCode();
                break;
        }
    }

    @Override
    public boolean onLongClick(View v) {
        switch (v.getId()) {
            case R.id.registfer:
                if(SERVER_FLAG>9){

                }
                //   SERVER_FLAG++;
                break;
        }
        return true;
    }

    @Override
    public String getUserName() {
        return et_name.getText().toString().trim();
    }

    @Override
    public void setUserName(String userName) {
        et_name.setText(userName);
    }

    @Override
    public String getPassword() {
        return et_pass.getText().toString().trim();
    }

    @Override
    public void setPassword(String password) {
        et_pass.setText(password);

    }

    @Override
    protected void onDestroy() {
        mHandler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}
