package k.art.ch5Remote;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.SystemClock;
import android.widget.RemoteViews;

import k.art.R;
import k.core.util.KLogUtil;

/**
 * Created by key on 2017/5/29.
 */

public class KAppWidgetImpl extends AppWidgetProvider {
    public static final String TAG = "KAppWidgetImpl";
    public static final String ACTION_CLICK = "k.art.ch5.click_intent";
    private Bitmap mBitmap;
    private RemoteViews mRv;
    public void onReceive(final Context context, Intent intent) {
        super.onReceive(context, intent);//必须父类处理
        String action = intent.getAction();
        KLogUtil.D(TAG, "onReceive,action = " + action);
        if (ACTION_CLICK.equals(action)) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    AppWidgetManager appWidgetManage = AppWidgetManager.getInstance(context);
                    for (int i = 0; i < 37; i++) {
                        RemoteViews rv = getRemoteViews(context);
                        rv.setImageViewBitmap(R.id.iv1, rotateBitmap((i * 10) % 360));
                        appWidgetManage.updateAppWidget(new ComponentName(context, KAppWidgetImpl.class), rv);
                        SystemClock.sleep(30);
                    }
                }
            }).start();
        }
    }

    /**
     * 安装apk才调用
     */
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        KLogUtil.D(TAG, "onUpdate,num = " + appWidgetIds.length);
        for (int it : appWidgetIds) {
            appWidgetManager.updateAppWidget(it, getRemoteViews(context));
        }
    }

    private RemoteViews getRemoteViews(Context cxt) {
        if (mRv == null){
            mRv = new RemoteViews(cxt.getPackageName(), R.layout.widget);
            PendingIntent pi = PendingIntent.getBroadcast(cxt, 0, new Intent(ACTION_CLICK), PendingIntent.FLAG_UPDATE_CURRENT);
            mRv.setOnClickPendingIntent(R.id.iv1, pi);
            mBitmap = BitmapFactory.decodeResource(cxt.getResources(), R.mipmap.ic_launcher);
        }
        return mRv;
    }
    //动画
    private Bitmap rotateBitmap(int degree) {
        Matrix matrix = new Matrix();
        matrix.reset();
        matrix.setRotate(degree);
        Bitmap temBitmap = Bitmap.createBitmap(this.mBitmap, 0, 0, this.mBitmap.getWidth(), this.mBitmap.getHeight(), matrix, true);
        return temBitmap;
    }
}
