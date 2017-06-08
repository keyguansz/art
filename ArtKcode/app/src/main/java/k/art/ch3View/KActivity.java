package k.art.ch3View;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.TextView;

import k.art.R;
import k.core.util.KLogUtil;

public class KActivity extends Activity {
    private static final String TAG = "KActivity";
    protected final StringBuffer mLogSb = new StringBuffer(20);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
    protected void onPause() {
        super.onPause();
    }
}
