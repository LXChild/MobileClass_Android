package com.lxchild.mobileclass;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
//    static {
//        System.loadLibrary("native-lib");
//    }

    public static void launch(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        // Example of a call to a native method
        //TextView tv = (TextView) findViewById(R.id.sample_text);
        //   tv.setText(stringFromJNI());
        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(mTabClickListener);
    }

    private OnTabSelectListener mTabClickListener = new OnTabSelectListener() {
        @Override
        public void onTabSelected(@IdRes int tabId) {
//            changeTitle(menuItemId);
//            switchMenu(getFragmentName(menuItemId));
        }
    };

    private long mLastBackTime = 0;
    @Override
    public void onBackPressed() {
        // finish while click back key 2 times during 1s.
        if ((System.currentTimeMillis() - mLastBackTime) < 1000) {
            finish();
        } else {
            mLastBackTime = System.currentTimeMillis();
            Toast.makeText(this, R.string.exit_click_back_again, Toast.LENGTH_SHORT).show();
        }
    }
    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
 //   public native String stringFromJNI();
}
