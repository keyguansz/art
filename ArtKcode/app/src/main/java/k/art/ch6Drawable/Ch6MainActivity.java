package k.art.ch6Drawable;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.RemoteViews;

import k.art.R;
import k.art.ch3View.KCircleView;


public class Ch6MainActivity extends Activity {
    private final String TAG = "Ch6MainActivity";
    int level = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_ch6main);
        TransitionDrawable d = (TransitionDrawable)(findViewById(R.id.transition_demo_tv).getBackground());
        d.startTransition(10000);
        View mScaleView = findViewById(R.id.scale_demo_tv);
        final ScaleDrawable sd = (ScaleDrawable)(mScaleView.getBackground());
        mScaleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sd.setLevel(level++);
            }
        });

        ClipDrawable cd = (ClipDrawable)(findViewById(R.id.clip_demo_tv).getBackground());
        cd.setLevel(5000);

        View mKView = findViewById(R.id.k_demo_tv);
        mKView.setBackground(new KDrawable(Color.RED));

    }
}
