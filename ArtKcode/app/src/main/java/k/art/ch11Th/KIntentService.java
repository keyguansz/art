package k.art.ch11Th;


import android.app.IntentService;
import android.content.Intent;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;



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
