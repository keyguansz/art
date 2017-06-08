package com.yck.ch2ipc;



import android.app.Application;
import android.os.Process;
import android.util.Log;

import com.yck.ch2ipc.utils.LogUtil;
import com.yck.ch2ipc.utils.MyUtils;

public class MyApplication extends Application {

    private static final String TAG = "MyApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.E(TAG, MyUtils.getProcessName(getApplicationContext(),Process.myPid()));

        new Thread(new Runnable() {

            @Override
            public void run() {
                doWorkInBackground();
            }
        }).start();
    }

    private void doWorkInBackground() {
        // init binder pool

    }
}
