package k.art.ch5Remote;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.view.View;
import android.widget.RemoteViews;

import k.art.R;
import k.art.ch4View.Ch4MainActivity;


public class Ch5AActivity extends Activity {
    private final String TAG = "Ch5AActivity";
    public static final String ACTION = "K-ch5-send";
    public static final String RV = "rv";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_ch5a);
        findViewById(R.id.start_brocast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRemoteViewsBrocast();
            }
        });
        findViewById(R.id.open_b).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Ch5AActivity.this, Ch5BActivity.class));
            }
        });
    }

    private void sendRemoteViewsBrocast() {
        RemoteViews rv =  new RemoteViews(getPackageName(), R.layout.ch5_a_item);
        rv.setTextViewText(R.id.tv_title, "K，端午安康"+"msg from process:" + Process.myPid());
        PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(this,Ch4MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
        rv.setOnClickPendingIntent(R.id.iv_img, pi);
        Intent intent = new Intent(ACTION);
        intent.putExtra(RV,rv);
        sendBroadcast(intent);
    }
}
