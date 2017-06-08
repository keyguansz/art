package k.art.ch5Remote;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Process;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RemoteViews;

import k.art.R;
import k.art.ch4View.Ch4MainActivity;
import k.core.util.KLogUtil;


public class Ch5BActivity extends Activity {
    private LinearLayout mRootView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_ch5b);
        registerReceiver(receiver,new IntentFilter(Ch5AActivity.ACTION));
        mRootView = (LinearLayout) findViewById(R.id.act_ch5b_root);
    }
    public void onDestroy(){
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            KLogUtil.D("onReceive","context="+context+",intent="+intent);
            RemoteViews rv = intent.getParcelableExtra(Ch5AActivity.RV);

            int layoutId = getResources().getIdentifier("ch5_a_item","layout",getPackageName());
            View view = getLayoutInflater().inflate(layoutId, mRootView,false);
            rv.reapply(Ch5BActivity.this,view);
            mRootView.addView(view);


            View  v = rv.apply(context, mRootView);
            mRootView.addView(v);
        }
    };
}
