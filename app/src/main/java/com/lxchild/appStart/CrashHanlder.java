package com.lxchild.appStart;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Looper;
import android.widget.Toast;

import com.lxchild.utils.AppUtils;
import com.lxchild.utils.SDCardUtils;
import com.orhanobut.logger.Logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by LXChild on 25/10/2016.
 */

public class CrashHanlder implements Thread.UncaughtExceptionHandler {


    private static CrashHanlder sInstance ;
    private WeakReference<Context> mContext;

    private Thread.UncaughtExceptionHandler mDefaultCrashHandler;

    private CrashHanlder() {}

    public static CrashHanlder getInstance() {
        if (sInstance == null) {
            synchronized ("lock") {
                if (sInstance == null) {
                    sInstance = new CrashHanlder();
                }
            }
        }
        return sInstance;
    }

    public void init(Context context) {
        mDefaultCrashHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
        mContext = new WeakReference<>(context.getApplicationContext());
    }

    /**
     * 这个是最关键的函数，当程序中有未被捕捉的异常，系统将自动调用#uncaughtException方法
     * @param t 为出现未捕获异常的线程
     * @param e 为未捕获的异常，有了这个，我们就可以得到异常信息
     * */
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        e.printStackTrace();
        // 如果系统提供了默认的异常处理器，则交给系统去结束程序，否则就由自己结束自己
//        if (mDefaultCrashHandler != null && !handleException(e)) {
            mDefaultCrashHandler.uncaughtException(t, e);
 //       }
//        try{
//            Thread.sleep(1500);
//        }catch (InterruptedException ex){
//            Logger.e("error : ", ex);
//        }
//        // 重启程序
//        Intent intent = new Intent(mContext.get(), SplashActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        intent.putExtra("crash", true);
//
//        PendingIntent restartIntent = PendingIntent.getActivity(mContext.get(), 0, intent, PendingIntent.FLAG_ONE_SHOT);
//
//        AlarmManager mgr = (AlarmManager) mContext.get().getSystemService(Context.ALARM_SERVICE);
//        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 1000, restartIntent); // 1秒钟后重启应用
//        MyApplication.getInstance().appExit();
    }

    private void dumpExceptionToSDCard(Throwable e) {
        // 如果SD卡不存在或无法使用，则无法把异常信息写入SD卡
        if (!SDCardUtils.isSDCardEnable()) {
            Logger.w("SDCard unmounted, skip dump exception");
            return;
        }

        final String mPath = SDCardUtils.getSDCardPath() + "/" + AppUtils.getAppName(mContext.get()) + "/Crash/";
        final String mFileName = "crash";
        final String mFileNameSuffix = ".trace";

        File dir = new File(mPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        long current = System.currentTimeMillis();
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(current));
        File file = new File(mPath + mFileName + time + mFileNameSuffix);

        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            pw.println(time);
            dumpPhoneInfo(pw);
            pw.println();
            e.printStackTrace(pw);
            pw.close();
        } catch (Exception e1) {
            Logger.e("dump crash info failed");
            e1.printStackTrace();
        }
    }

    private void dumpPhoneInfo(PrintWriter pw) throws PackageManager.NameNotFoundException {

        // 应用版本号
        PackageManager pm = mContext.get().getPackageManager();
        PackageInfo pi = pm.getPackageInfo(mContext.get().getPackageName(), PackageManager.GET_ACTIVITIES);
        pw.print("App Version: ");
        pw.print(pi.versionName);
        pw.print('_');
        pw.println(pi.versionCode);

        // Android 版本号
        pw.print("OS Version: ");
        pw.print(Build.VERSION.RELEASE);
        pw.print("_");
        pw.println(Build.VERSION.SDK_INT);

        // 手机制造商
        pw.print("Vendor: ");
        pw.println(Build.MANUFACTURER);

        // 手机型号
        pw.print("Model: ");
        pw.println(Build.MODEL);

        // CPU 架构
        pw.print("CPU ABI: ");
        pw.println(Build.CPU_ABI);
    }

    private void uploadExceptionToServer() {
        // TODO Upload Exception Message To Your Web Server
    }

    /**
     * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.
     * @param e 异常信息
     * @return true:如果处理了该异常信息，否则返回false.
     */
    private boolean handleException(Throwable e) {
        if (e == null) {
            return false;
        } else {
            new Thread() {
                @Override
                public void run() {
                    Looper.prepare();
                    Toast.makeText(mContext.get(), "应用异常退出，即将重启", Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }
            }.start();
            // 导出异常信息到SD卡中
            dumpExceptionToSDCard(e);
            // 这里可以上传异常信息到服务器，便于开发人员分析日志从而解决BUG
            uploadExceptionToServer();
            return true;
        }
    }
}
