package k.art.ch5Remote;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.RemoteViews;

import k.art.R;
import k.art.ch3View.KCircleView;
import k.core.util.KLogUtil;


public class Ch5MainActivity extends Activity {
    private final String TAG = "Ch4MainActivity";
    private KCircleView mKv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_ch5main);
      //  systemStyleNotirfication();
        viewStyleNotirfication();
        findViewById(R.id.start_appwidget).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewStyleNotirfication();
            }
        });
    }
    private void systemStyleNotirfication() {
        Notification notification = new Notification();
        notification.icon = R.mipmap.ic_launcher;
        notification.tickerText = "Hello K";
        notification.when = System.currentTimeMillis();
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        Intent intent = new Intent(this, Ch5MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
      //  notification.contentIntent = pendingIntent;
    //    notification.setLatestEventInfo(this, "点击查看", "点击查看详细内容", pendingIntent);
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);
    }
    //自定义通知栏
    int cnt = 0;
    private void viewStyleNotirfication() {
        cnt++;
        Notification notification = new Notification();
        notification.icon = R.mipmap.ic_launcher;
        notification.tickerText = "Hello K";
        notification.when = System.currentTimeMillis();
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        Intent intent = new Intent(this, Ch5MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        //RemoteViews的使用也是非常的简单，只要提供当前应用的包名和布局文件的资源ID就可以创建
        // 一个RemoteViews了，如何更新呢？这一点和View还是有很大的不同，
        // RemoteViews更新的时候，无法直接访问里面的View,必须通过RemoteViews所提供的一系列方法来更新，
        // 比如设置TextView,那就需要
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.notification_item);
        remoteViews.setTextViewText(R.id.tv_title, "Title"+cnt);
        remoteViews.setTextViewText(R.id.tv_content, "Content");
        remoteViews.setImageViewResource(R.id.iv_img, R.mipmap.ic_launcher);
        remoteViews.setOnClickPendingIntent(R.id.ll_open, pendingIntent);//待定的意图
        notification.contentView = remoteViews;
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(cnt, notification);
    }

}
