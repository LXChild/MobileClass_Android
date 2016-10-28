package com.lxchild.classTest.singleChoice.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.lxchild.base.BaseLoadingActivity;
import com.lxchild.bean.SingleChoiceBean;
import com.lxchild.classTest.singleChoice.presenter.SingleChoicePresenter;
import com.lxchild.mobileclass.R;
import com.lxchild.utils.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ClassTestActivity extends BaseLoadingActivity implements ISingleChoiceView {


    @BindView(R.id.activity_class_test)
    LinearLayout rootLayout;
    @BindView(R.id.et_class_test_question)
    EditText mEtClassTestQusetion;
    @BindView(R.id.et_class_test_aa)
    EditText mEtClassTestAa;
    @BindView(R.id.et_class_test_ab)
    EditText mEtClassTestAb;
    @BindView(R.id.et_class_test_ac)
    EditText mEtClassTestAc;
    @BindView(R.id.et_class_test_ad)
    EditText mEtClassTestAd;
    @BindView(R.id.et_class_test_ar)
    EditText mEtClassTestAr;
    @BindView(R.id.btn_class_test_next)
    Button mBtnClassTestNext;
    @BindView(R.id.btn_class_test_complete)
    Button mBtnClassTestComplete;

    private SingleChoicePresenter mPresenter;

    public static void launch(Context context) {
        context.startActivity(new Intent(context, ClassTestActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_test);
        ButterKnife.bind(this);

        try {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        setTitle("课堂测试");

        mPresenter = new SingleChoicePresenter(this, this);
    }

    @OnClick({R.id.btn_class_test_next, R.id.btn_class_test_complete})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_class_test_next:
                SingleChoiceBean bean = getSingleChoice();
                if (bean != null) {
                    if (mPresenter.insertData(bean)) {
                       // Snackbar.make(rootLayout, "保存成功", Snackbar.LENGTH_SHORT).show();
                        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
                        setSingleChoice(null);
                    } else {
                        Toast.makeText(this, "保存失败，数据库中已存在该题", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.btn_class_test_complete:
                //mPresenter.loadData();
                onBackPressed();
                break;
            default:
                break;
        }
    }

    @Override
    public SingleChoiceBean getSingleChoice() {
        if (StringUtils.isEmpty(mEtClassTestQusetion)) {
            mEtClassTestQusetion.setError("题目为空");
            return null;
        }
        if (StringUtils.isEmpty(mEtClassTestAr) ||
                !StringUtils.isValidAnswer(mEtClassTestAr.getText().toString().trim())) {
            mEtClassTestAr.setError("正确答案为空或不是正确格式");
            return null;
        }

        return new SingleChoiceBean(mEtClassTestQusetion.getText().toString(),
                mEtClassTestAa.getText().toString(),
                mEtClassTestAb.getText().toString(),
                mEtClassTestAc.getText().toString(),
                mEtClassTestAd.getText().toString(),
                mEtClassTestAr.getText().toString());
    }

    @Override
    public void setSingleChoice(SingleChoiceBean bean) {
        if (bean == null) {
            mEtClassTestQusetion.setText("");
            mEtClassTestAa.setText("");
            mEtClassTestAb.setText("");
            mEtClassTestAc.setText("");
            mEtClassTestAd.setText("");
            mEtClassTestAr.setText("");
            return;
        }
        mEtClassTestQusetion.setText(bean.getQuestion_name());
        mEtClassTestAa.setText(bean.getAnswer_a());
        mEtClassTestAb.setText(bean.getAnswer_b());
        mEtClassTestAc.setText(bean.getAnswer_c());
        mEtClassTestAd.setText(bean.getAnswer_d());
        mEtClassTestAr.setText(bean.getAnswer_r());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
