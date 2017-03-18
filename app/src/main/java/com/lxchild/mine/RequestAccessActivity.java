package com.lxchild.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.AppCompatSpinner;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.SaveCallback;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lxchild.base.BaseLoadingActivity;
import com.lxchild.mobileclass.R;
import com.orhanobut.logger.Logger;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;

public class RequestAccessActivity extends BaseLoadingActivity {

    @BindView(R.id.spinner_request_access)
    AppCompatSpinner mSpinner;
    @BindView(R.id.et_request_access_id)
    EditText et_id;
    @BindView(R.id.et_request_access_name)
    EditText et_name;
    @BindView(R.id.iv_request_access_credentials)
    SimpleDraweeView iv_credentials;
    @BindView(R.id.btn_request_access_selectimg)
    Button btn_selectimg;

    private static final String[] m_arr = {"管理员", "老师"};

    public static void launch(Context context) {
        context.startActivity(new Intent(context, RequestAccessActivity.class));

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_access);
        initView();
    }

    private void initView() {
        ButterKnife.bind(this);

        try {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        setTitle("申请权限");
        mSpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, m_arr));
    }

    @OnItemSelected({R.id.spinner_request_access})
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                Logger.d(">>>>>>>>>>>>>>>>>>>>>");
                break;
            case 1:
                
                break;
            default:
                break;
        }
    }

    @OnClick({R.id.btn_request_access_selectimg, R.id.btn_request_access_submit})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_request_access_selectimg:
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 42);
                break;
            case R.id.btn_request_access_submit:
                if ("".equals(et_id.getText().toString())) {
                    Toast.makeText(this, "请输入学号/工号", Toast.LENGTH_SHORT).show();
                    return;
                }
                if ("".equals(et_name.getText().toString())) {
                    Toast.makeText(this, "请输入姓名", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (mImageBytes == null) {
                    Toast.makeText(this, "请选择一张照片", Toast.LENGTH_SHORT).show();
                    return;
                }
                showLoadingDialog();
                AVObject product = new AVObject("Product");
                product.put("userID", et_id.getText().toString());
                product.put("userName", et_name.getText().toString());
                product.put("image", new AVFile("productPic", mImageBytes));
                product.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(AVException e) {
                        dismissLoadingDialog();
                        if (e == null) {
                            Toast.makeText(RequestAccessActivity.this, "申请成功", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(RequestAccessActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
//        }, mImageUploadProgressCallback);
                break;
            default:
                break;
        }
    }

    private byte[] mImageBytes = null;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 42 && resultCode == RESULT_OK) {
            try {
                iv_credentials.setImageBitmap(MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getData()));
                mImageBytes = getBytes(getContentResolver().openInputStream(data.getData()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];
        int len;
        while ((len = inputStream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, len);
        }
        return byteArrayOutputStream.toByteArray();
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
