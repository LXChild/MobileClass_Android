package com.lxchild.classTest.partList;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;

import com.lxchild.base.BaseLoadingActivity;
import com.lxchild.bean.PaperPart;
import com.lxchild.dataMVP.IDataContract;
import com.lxchild.mobileclass.R;
import com.lxchild.utils.TimeUtils;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PartListActivity extends BaseLoadingActivity implements IDataContract.IDataView<PaperPart> {


    @BindView(R.id.rv_part_list)
    RecyclerView mRvPartList;
    @BindView(R.id.fab_add)
    FloatingActionButton mFabAdd;
    @BindView(R.id.cl_part_list)
    CoordinatorLayout mClPartList;

    private List<String> mBeans;
    private PartListAdapter mAdapter;

    private int paperID;

    private IDataContract.IDataPresenter<PaperPart> mPresenter;

    public static void launch(Context context) {
        context.startActivity(new Intent(context, PartListActivity.class));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part_list);
        ButterKnife.bind(this);

        try {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        initData();

        new PartListPresenter(this);
        mPresenter.start();


        mRvPartList.setLayoutManager(new LinearLayoutManager(this));
        mRvPartList.setAdapter(mAdapter);
        mRvPartList.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
        mRvPartList.setItemAnimator(new DefaultItemAnimator());
    }

    protected void initData() {
        int paperID = getIntent().getIntExtra("PaperID", -1);
        if (paperID == -1) {
            Snackbar.make(mClPartList, "读取paperID失败", Snackbar.LENGTH_SHORT).show();
        } else {
            this.paperID = paperID;
        }
        Logger.d(paperID);
        mBeans = new ArrayList<>();
        mAdapter = new PartListAdapter(mRvPartList, mBeans, R.layout.item_part_list);
    }

    @OnClick({R.id.fab_add})
    public void onClick(View v) {
        showAddDialog();
    }

    private void showAddDialog() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialog_layout = inflater.inflate(R.layout.layout_paper_part_add_dialog, null);
        final RadioButton rb_sc = (RadioButton) dialog_layout.findViewById(R.id.rb_sc);
        final RadioButton rb_ot = (RadioButton) dialog_layout.findViewById(R.id.rb_ot);

        final AlertDialog.Builder connectDialog = new AlertDialog.Builder(this);
        connectDialog.setTitle(R.string.add);
        connectDialog.setView(dialog_layout);
        connectDialog.setPositiveButton(R.string.confirm,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (rb_sc.isChecked()) {
                            addToTable(0);
                        } else {
                            addToTable(1);
                        }
                    }
                });
        connectDialog.setNegativeButton(R.string.cancel, null);
        connectDialog.show();
    }

    private void addToTable(int code) {
        PartListPresenter presenter = (PartListPresenter) mPresenter;
        PaperPart part = presenter.selectWhere(paperID);
        if (part != null) {
            addToPosition(part, code);
            mPresenter.updateData(paperID, part);
        } else {
            part = new PaperPart(paperID, "table" + TimeUtils.getCurrentTime(), code);
            // TODO create question table
            if (!mPresenter.insertData(part)) {
                Snackbar.make(mClPartList, "添加题目失败！试卷号重复", Snackbar.LENGTH_SHORT).show();
            }
        }
        mPresenter.start();
    }

    private void addToPosition(PaperPart part, int code) {
        switch (mBeans.size()) {
            case 1:
                part.setPartB("table" + TimeUtils.getCurrentTime());
                // TODO create question table
                part.setTypeB(code);
                break;
            case 2:
                part.setPartC("table" + TimeUtils.getCurrentTime());
                // TODO create question table
                part.setTypeC(code);
                break;
            case 3:
                part.setPartD("table" + TimeUtils.getCurrentTime());
                // TODO create question table
                part.setTypeD(code);
                break;
            case 4:
                part.setPartE("table" + TimeUtils.getCurrentTime());
                // TODO create question table
                part.setTypeE(code);
                break;
            case 5:
                Snackbar.make(mClPartList, "题目已满，最多添加5条", Snackbar.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        if (active) {
            showLoadingDialog();
        } else {
            dismissLoadingDialog();
        }
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void loadDataSucceed(List<PaperPart> beans) {
        if (beans == null) {
            return;
        }
        PaperPart part = beans.get(0);
        if (part == null) {
            return;
        }

        mBeans.clear();
        int partCodeA = part.getTypeA();
        if (partCodeA != -1) {
            mBeans.add(getPartName(partCodeA));
            mAdapter.notifyItemInserted(0);
        }

        int partCodeB = part.getTypeB();
        if (partCodeB != -1) {
            mBeans.add(getPartName(partCodeB));
            mAdapter.notifyItemInserted(1);
        }

        int partCodeC = part.getTypeC();
        if (partCodeC != -1) {
            mBeans.add(getPartName(partCodeC));
            mAdapter.notifyItemInserted(2);
        }

        int partCodeD = part.getTypeD();
        if (partCodeD != -1) {
            mBeans.add(getPartName(partCodeD));
            mAdapter.notifyItemInserted(3);
        }

        int partCodeE = part.getTypeE();
        if (partCodeE != -1) {
            mBeans.add(getPartName(partCodeE));
            mAdapter.notifyItemInserted(4);
        }
        Snackbar.make(mClPartList, "成功加载数据", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void loadDataFailed(String result) {
        Snackbar.make(mClPartList, "加载数据失败", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(IDataContract.IDataPresenter presenter) {
        mPresenter = presenter;
    }

    private String getPartName(int code) {
        switch (code) {
            case 0:
                return "单选题";
            case 1:
                return "填空／简答";
            default:
                return null;
        }
    }
}
