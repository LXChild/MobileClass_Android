package com.lxchild.mobileclass;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.facebook.drawee.backends.pipeline.Fresco;
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
        Logger.d("performInit begin:" + System.currentTimeMillis());
        Fresco.initialize(this);
        Logger.d("performInit end:" + System.currentTimeMillis());
    }
}
