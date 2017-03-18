package com.lxchild.classTest.paperList.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.lxchild.base.BaseLoadingActivity;
import com.lxchild.bean.PaperBean;
import com.lxchild.classTest.paperList.presenter.PaperListPresenter;
import com.lxchild.classTest.partList.PartListActivity;
import com.lxchild.dataMVP.IDataContract;
import com.lxchild.mobileclass.R;
import com.lxchild.utils.StringUtils;
import com.lxchild.utils.TimeUtils;
import com.lxchild.widget.swipemenulistview.SwipeMenu;
import com.lxchild.widget.swipemenulistview.SwipeMenuCreator;
import com.lxchild.widget.swipemenulistview.SwipeMenuItem;
import com.lxchild.widget.swipemenulistview.SwipeMenuListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PaperListActivity extends BaseLoadingActivity implements IDataContract.IDataView<PaperBean> {

    @BindView(R.id.lv_paper_list)
    SwipeMenuListView mLvPaper;
    private PaperListAdapter mAdapter;

    private LinearLayout mEditDialogLayout;

    private ArrayList<PaperBean> mBeans = new ArrayList<>();

    private IDataContract.IDataPresenter<PaperBean> mPresenter;

    public static void launch(Context context) {
        context.startActivity(new Intent(context, PaperListActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_paper);
        ButterKnife.bind(this);

        try {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        new PaperListPresenter(this);
        mAdapter = new PaperListAdapter(getContext(), mBeans);
        mPresenter.start();
        mLvPaper.setAdapter(mAdapter);

        mEditDialogLayout = (LinearLayout) findViewById(R.id.layout_paper_edit_dialog);

        initSwipeMenuListView();
    }

    private void initSwipeMenuListView() {
        // step 1. create a MenuCreator
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "edit" item
                SwipeMenuItem openItem = new SwipeMenuItem(getContext());
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9, 0xCE)));
                // set item width
                openItem.setWidth(dp2px(80));
//                // set item title
//                openItem.setTitle("Edit");
//                // set item title fontsize
//                openItem.setTitleSize(18);
//                // set item title font color
//                openItem.setTitleColor(Color.WHITE);
                openItem.setIcon(R.drawable.ic_edit);
                // add to menu
                menu.addMenuItem(openItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(getContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9, 0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(dp2px(80));
                // set a icon
                deleteItem.setIcon(R.drawable.ic_delete);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };
        // set creator
        mLvPaper.setMenuCreator(creator);

        // step 2. listener item click event
        mLvPaper.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public void onMenuItemClick(int position, SwipeMenu menu, int index) {
                PaperBean item = mBeans.get(position);
                switch (index) {
                    case 0:
                        edit(position, item);
                        break;
                    case 1:
                        delete(position);
                        break;
                }
            }
        });

        // set SwipeListener
        mLvPaper.setOnSwipeListener(new SwipeMenuListView.OnSwipeListener() {

            @Override
            public void onSwipeStart(int position) {
                // swipe start
            }

            @Override
            public void onSwipeEnd(int position) {
                // swipe end
            }
        });

        // test item long click
        mLvPaper.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), position + " long click", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        mLvPaper.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onLVItemClick(position);
            }
        });
    }

    private void onLVItemClick(int position) {
        PaperListPresenter presenter = (PaperListPresenter) mPresenter;
        int id = presenter.getID(position);
        Intent intent = new Intent(this, PartListActivity.class);
        intent.putExtra("PaperID", id);
        startActivity(intent);
    }

    private void delete(int position) {
        mPresenter.deleteData(position);
        mPresenter.start();
    }

    private void edit(int position, PaperBean page) {
        showEditDialog(position, page);
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
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
    public void loadDataSucceed(List<PaperBean> beans) {
        if (beans == null) {
            return;
        }
        Toast.makeText(this, "成功更新数据", Toast.LENGTH_SHORT).show();
        mBeans.clear();
        for (PaperBean bean : beans) {
            mBeans.add(bean);
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void loadDataFailed(String result) {
        Toast.makeText(this, R.string.paper_list_get_data_failed, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_paper, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_add:
                showAddDialog();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showAddDialog() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialog_layout = inflater.inflate(R.layout.layout_paper_edit_dialog, mEditDialogLayout);
        final EditText et_title = (EditText) dialog_layout.findViewById(R.id.et_paper_edit_dialog_title);
        final EditText et_remark = (EditText) dialog_layout.findViewById(R.id.et_paper_edit_dialog_remark);

        final AlertDialog.Builder connectDialog = new AlertDialog.Builder(this);
        connectDialog.setTitle(R.string.add);
        connectDialog.setView(dialog_layout);
        connectDialog.setPositiveButton(R.string.confirm,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (!StringUtils.isEmpty(et_title)) {
                            PaperBean bean = new PaperBean();
                            bean.setTitle(et_title.getText().toString());
                            bean.setRemark(et_remark.getText().toString());
                            bean.setDate(TimeUtils.getCurrentTime());
                            if (!mPresenter.insertData(bean)) {
                                Toast.makeText(getContext(), "添加试卷失败！请检查是否试卷名重复", Toast.LENGTH_SHORT).show();
                            }
                            mPresenter.start();
                        } else {
                            Toast.makeText(getContext(), R.string.paper_edit_dialog_null_title, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        connectDialog.setNegativeButton(R.string.cancel, null);
        connectDialog.show();
    }

    private void showEditDialog(final int position, final PaperBean bean) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View dialog_layout = inflater.inflate(R.layout.layout_paper_edit_dialog, mEditDialogLayout);
        final EditText et_title = (EditText) dialog_layout.findViewById(R.id.et_paper_edit_dialog_title);
        final EditText et_remark = (EditText) dialog_layout.findViewById(R.id.et_paper_edit_dialog_remark);
        et_title.setText(bean.getTitle());
        et_remark.setText(bean.getRemark());

        final AlertDialog.Builder connectDialog = new AlertDialog.Builder(getContext());
        connectDialog.setTitle(R.string.modify);
        connectDialog.setView(dialog_layout);
        connectDialog.setPositiveButton(R.string.confirm,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (!StringUtils.isEmpty(et_title)) {

                            if (mPresenter.updateData(position, new PaperBean(
                                    et_title.getText().toString(),
                                    et_remark.getText().toString(),
                                    TimeUtils.getCurrentTime()))) {
                                mPresenter.start();
                            } else {
                                Toast.makeText(getContext(), "更新试卷信息失败，请检查试卷名是否重复", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getContext(), R.string.paper_edit_dialog_null_title, Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
        connectDialog.setNegativeButton(R.string.cancel, null);
        connectDialog.show();
    }

    @Override
    public void setPresenter(IDataContract.IDataPresenter presenter) {
        mPresenter = presenter;
    }
}
