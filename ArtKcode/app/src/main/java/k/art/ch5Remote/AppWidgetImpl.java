package k.art.ch5Remote;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.SystemClock;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import k.art.R;

/**
 * Created by key on 2017/5/29.
 */

public class AppWidgetImpl extends AppWidgetProvider {

    public static final String TAG = "AppWidgetImpl";
    public static final String CLICK_ACTION = "k.art.ch5.click_intent";

    private AppWidgetManager appWidgetManage;
    private float degree;
    private Bitmap bitmap;

    public AppWidgetImpl() {
        super();
    }

    @Override
    public void onReceive(final Context context, final Intent intent) {
        super.onReceive(context, intent);
        String action = intent.getAction();
        Log.i(TAG, "action:" + action);
        if (CLICK_ACTION.equals(action)) {
            Toast.makeText(context, "click it", Toast.LENGTH_SHORT).show();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);
                    appWidgetManage = AppWidgetManager.getInstance(context);
                    for (int i = 0; i < 37; i++) {
                        degree = (i * 10) % 360;
                        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);
                        remoteViews.setImageViewBitmap(R.id.iv1, rotateBitmap(bitmap));
                        remoteViews.setImageViewBitmap(R.id.iv2, rotateBitmap(bitmap));
                        Intent intentClick = new Intent(CLICK_ACTION);
                        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intentClick, 0);
                        remoteViews.setOnClickPendingIntent(R.id.iv1, pendingIntent);
                        appWidgetManage.updateAppWidget(new ComponentName(context, AppWidgetImpl.class), remoteViews);
                        SystemClock.sleep(30);
                    }
                }
            }).start();
        }
    }

    /**
     * 每次窗口小部件被点击更新都调用一次该方法
     */
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        Log.i(TAG, "onUpdate");

        final int counter = appWidgetIds.length;
        Log.i(TAG, "counter = " + counter);
        for (int i = 0; i < counter; i++) {
            int appWidgetId = appWidgetIds[i];
            onWidgetUpdate(context, appWidgetManager, appWidgetId);
        }

    }

    /**
     * 窗口小部件更新
     *
     * @param context
     * @param appWidgeManger
     * @param appWidgetId
     */
    private void onWidgetUpdate(Context context,
                                AppWidgetManager appWidgeManger, int appWidgetId) {

        Log.i(TAG, "appWidgetId = " + appWidgetId);
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                R.layout.widget);

        // "窗口小部件"点击事件发送的Intent广播
        Intent intentClick = new Intent();
        intentClick.setAction(CLICK_ACTION);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0,
                intentClick, 0);
        remoteViews.setOnClickPendingIntent(R.id.iv1, pendingIntent);
        appWidgeManger.updateAppWidget(appWidgetId, remoteViews);
    }

    //动画
    private Bitmap rotateBitmap(Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.reset();
        matrix.setRotate(degree);
        Bitmap temBitmap = Bitmap.createBitmap(this.bitmap, 0, 0, this.bitmap.getWidth(), this.bitmap.getHeight(), matrix, true);
        return temBitmap;
    }
    public static void sendBrocast(Context cxt){
        cxt.sendBroadcast(new Intent(CLICK_ACTION));
    }
}
