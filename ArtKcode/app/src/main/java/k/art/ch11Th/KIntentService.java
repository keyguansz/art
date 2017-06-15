package k.art.ch11Th;


import android.app.IntentService;
import android.content.Intent;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * @author :key.guan
 * @package :k.art.ch11Th
 * @date : 2017/6/15
 * Description:
 * Copyright (c) 2017. DJI All Rights Reserved.
 */

public class KIntentService extends IntentService {
    private static final String TAG = KIntentService.class.getSimpleName();
    public KIntentService() {
        super(TAG);
    }
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String task = intent.getStringExtra("task");
        Log.d(TAG, "receiver task :"+task);
        SystemClock.sleep(2000);
    }
    @Override
    public void onDestroy() {
        Log.w(TAG, "onDestroy: 准备关闭" );
        super.onDestroy();
    }
}
