package com.lxchild.classTest.otherTopic.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.lxchild.base.BaseLoadingActivity;
import com.lxchild.DataOperatorMVP.view.IDataOperatorView;
import com.lxchild.bean.OtherTopicBean;
import com.lxchild.classTest.otherTopic.presenter.OtherTopicPresenter;
import com.lxchild.mobileclass.R;
import com.lxchild.utils.StringUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OtherTopicActivity extends BaseLoadingActivity implements IDataOperatorView<OtherTopicBean> {

    @BindView(R.id.et_other_topic_question)
    EditText mEtOtherTopicQuestion;
    @BindView(R.id.et_other_topic_answer)
    EditText mEtOtherTopicAnswer;
    @BindView(R.id.btn_other_topic_previous)
    Button mBtnOtherTopicPrevious;
    @BindView(R.id.btn_other_topic_next)
    Button mBtnOtherTopicNext;
    @BindView(R.id.btn_other_topic_complete)
    Button mBtnOtherTopicComplete;
    @BindView(R.id.activity_other_topic)
    LinearLayout mActivityOtherTopic;

    private OtherTopicPresenter mTopicPresenter;

    public static void launch(Context context) {
        context.startActivity(new Intent(context, OtherTopicActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_topic);
        ButterKnife.bind(this);

        try {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        setTitle("其他题目");

        mTopicPresenter = new OtherTopicPresenter(this);
    }

    @OnClick({R.id.btn_other_topic_previous, R.id.btn_other_topic_next, R.id.btn_other_topic_complete})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_other_topic_previous:

                break;
            case R.id.btn_other_topic_next:
                OtherTopicBean bean = getTopic();
                if (bean != null) {
                    if (mTopicPresenter.insertData(bean)) {
                        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
                        setEditText("", "");
                    } else {
                        Toast.makeText(this, "保存失败，数据库中已存在该题", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.btn_other_topic_complete:
                onBackPressed();
                break;
            default:
                break;
        }
    }

    private OtherTopicBean getTopic() {
        if (StringUtils.isEmpty(mEtOtherTopicQuestion)) {
            mEtOtherTopicQuestion.setError("题目为空");
            return null;
        }
        if (StringUtils.isEmpty(mEtOtherTopicAnswer)) {
            mEtOtherTopicAnswer.setError("答案为空");
            return null;
        }
        return new OtherTopicBean(mEtOtherTopicQuestion.getText().toString(), mEtOtherTopicAnswer.getText().toString());
    }

    private void setEditText(String question, String answer) {
        mEtOtherTopicQuestion.setText(question);
        mEtOtherTopicAnswer.setText(answer);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void loadDataSucceed(List<OtherTopicBean> bean) {
        if (bean == null) {
            return;
        }
//        mEtOtherTopicQuestion.setText(bean.getQuestion());
//        mEtOtherTopicAnswer.setText(bean.getAnswer());
    }

    @Override
    public void loadDataFailed(String result) {

    }
}
