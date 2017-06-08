package k.art.ch3View;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.TextView;

import k.art.R;
import k.core.util.KLogUtil;

/**
 * @desc:
 * @ref:Android View坐标getLeft, getRight, getTop, getBottom
 * http://www.cnblogs.com/zhengbeibei/archive/2013/05/07/3065999.html
 * @ref:滑动速度跟踪类 http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2012/1117/574.html
 * @author: key.guan @ 2017/4/23
 */
public class Ch3Main2Activity extends KActivity {
    private final String TAG = "Ch3MainActivity";
    private TextView mInfoTv;
    private TextView mGestureTv;
    private TextView mScrollTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_ch3main2);
        //R.layout.act_ch3main =((ViewGroup) getWindow().getDecorView().findViewById(android.R.id.content)).getChildAt(0);

        init();
    }

    private void init() {
        mInfoTv = (TextView) findViewById(R.id.act_ch3main_info_tv);
        mGestureTv = (TextView) findViewById(R.id.act_ch3main_gesture_tv);
        mScrollTv = (TextView) findViewById(R.id.act_ch3main_scroll_tv);

        mInfoTv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                KLogUtil.D("init","onTouch");
                return true;//true不会再响应OnClickListener
            }
        });
        mInfoTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KLogUtil.D("init","onClick");

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    protected void onPause() {
        super.onPause();

    }

}
