package com.lxchild.appStart;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.store.PersistentCookieStore;
import com.orhanobut.logger.Logger;

/**
 * Created by LXChild on 16/10/2016.
 */

public class InitializeService extends IntentService {
    private static final String ACTION_INIT_WHEN_APP_CREATE = "com.lxchild.mobileclass.service.action.INIT";
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */
    public InitializeService() {
        super("InitializeService");
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, InitializeService.class);
        intent.setAction(ACTION_INIT_WHEN_APP_CREATE);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_INIT_WHEN_APP_CREATE.equals(action)) {
                performInit();
            }
        }
    }

    private void performInit() {
        long time = System.currentTimeMillis();
        // 初始化异常处理
        CrashHanlder crashHanlder = CrashHanlder.getInstance();
        crashHanlder.init(this);
        // 初始化Fresco
        Fresco.initialize(this);
        // 初始化OkGo
        OkGo.init(getApplication());
        OkGo.getInstance().setCacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST) // 先使用缓存，不管是否存在，仍然请求网络 CacheMode.FIRST_CACHE_THEN_REQUEST
                .setCacheTime(24 * 60 * 60 * 1000)  // 设置Cache时间为1天
                .setCookieStore(new PersistentCookieStore());   // cookie持久化存储，如果cookie不过期，则一直有效
        Logger.d((System.currentTimeMillis() - time) + ">>>>>>>>>>>>>>>>>>>>>>>>>>");
    }
}
