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

/**
 * @desc:
 * @ref:Android View坐标getLeft, getRight, getTop, getBottom
 * http://www.cnblogs.com/zhengbeibei/archive/2013/05/07/3065999.html
 * @ref:滑动速度跟踪类
 * http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2012/1117/574.html
 * @author: key.guan @ 2017/4/23
 */
public class KActivity extends Activity {
    private final String TAG = "Ch3MainActivity";
    private TextView mInfoTv;
    private VelocityTracker mVelocityTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_ch3main);
        init();
    }

    private void init() {
        mInfoTv = (TextView) findViewById(R.id.act_ch3main_info_tv);
        testGetPosParam();
        testMotionEvent();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
    protected void onPause() {
        super.onPause();
        mVelocityTracker.clear();
        mVelocityTracker.recycle();
    }
    private void testGetPosParam(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            updateView();
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private void testMotionEvent() {
        int i = ViewConfiguration.get(getApplicationContext()).getScaledTouchSlop();
        KLogUtil.D(TAG, "onCreate", "getScaledTouchSlop="+ i );//24像素才认为是滑动
        mInfoTv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                mVelocityTracker = VelocityTracker.obtain();
                mVelocityTracker.addMovement(motionEvent);
                mVelocityTracker.computeCurrentVelocity(1000);
                int xV = (int)mVelocityTracker.getXVelocity();
                int yV = (int)mVelocityTracker.getYVelocity();
                KLogUtil.D(TAG, "onTouch", ": xV="+ xV+",yV="+ yV );// xV=0,yV=0
                KLogUtil.D(TAG, "onTouch", ": xV="+ xV+",yV="+ yV );// xV=0,yV=0
                return false;
            }
        });
    }
    private void updateView(){
        StringBuffer sb = new StringBuffer(20);
        sb.append("l=").append(mInfoTv.getLeft())
                .append(",r=").append(mInfoTv.getRight())
                .append(",t=").append(mInfoTv.getTop())
                .append(",b=").append(mInfoTv.getBottom())
                .append("\n");
        sb
                .append(",x=").append(mInfoTv.getX())
                .append(",y=").append(mInfoTv.getY())
                .append(",transX=").append(mInfoTv.getTranslationX())
                .append(",transY=").append(mInfoTv.getTranslationY())
                .append("\n");
        KLogUtil.D(TAG,sb.toString());
        mInfoTv.setText(sb);
    }
}
