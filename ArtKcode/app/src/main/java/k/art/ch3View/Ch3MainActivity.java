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
import android.widget.Scroller;
import android.widget.TextView;


import java.util.Timer;
import java.util.TimerTask;

import k.art.R;
import k.core.util.KLogUtil;

/**
 * @desc:
 * @ref:Android View坐标getLeft, getRight, getTop, getBottom
 * http://www.cnblogs.com/zhengbeibei/archive/2013/05/07/3065999.html
 * @ref:滑动速度跟踪类 http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2012/1117/574.html
 * @author: key.guan @ 2017/4/23
 */
public class Ch3MainActivity extends KActivity {
    private final String TAG = "Ch3MainActivity";
    private TextView mInfoTv;
    private VelocityTracker mVelocityTracker;
    private TextView mGestureTv;
    private TextView mScrollTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_ch3main);
        init();
    }

    private void init() {
        mInfoTv = (TextView) findViewById(R.id.act_ch3main_info_tv);
        mGestureTv = (TextView) findViewById(R.id.act_ch3main_gesture_tv);
        mScrollTv = (TextView) findViewById(R.id.act_ch3main_scroll_tv);

        testGetPosParam();
        testMotionEvent();
        testGestureDetector();
        //testScroller();
        //testScroller2();
        testScrollDelay();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    protected void onPause() {
        super.onPause();
        if (mVelocityTracker != null) {
            mVelocityTracker.clear();
            mVelocityTracker.recycle();
        }
    }

    private void testGetPosParam() {
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
        int iTouchSlop = ViewConfiguration.get(getApplicationContext()).getScaledTouchSlop();
        KLogUtil.D(TAG, "testMotionEvent", "getScaledTouchSlop=" + iTouchSlop);//24像素才认为是滑动
        mInfoTv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                KLogUtil.D(TAG, "testMotionEvent->onTouch"
                        + "X=" + motionEvent.getX() + ",RawX = " + motionEvent.getRawX()
                        + ",Y=" + motionEvent.getY() + ",RawY = " + motionEvent.getRawY()
                        + ",: motionEvent=" + motionEvent);//why just action=ACTION_DOWN
                if (null == mVelocityTracker) {
                    mVelocityTracker = VelocityTracker.obtain();
                } else {
                    mVelocityTracker.clear();
                }
                mVelocityTracker.addMovement(motionEvent);
                return false;
            }
        });
    }

    /**
     * @desc:VelocityTracker：
     * @ref:http://blog.csdn.net/wuqilianga/article/details/53115506
     * @author: key.guan @ 2017/4/23
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (null == mVelocityTracker) {//1.初始化单例
                    mVelocityTracker = VelocityTracker.obtain();
                } else {
                    mVelocityTracker.clear();
                }
                mVelocityTracker.addMovement(ev);
                break;
            case MotionEvent.ACTION_MOVE://2.添加记录
                if (mVelocityTracker != null)
                    mVelocityTracker.addMovement(ev);
                break;
            case MotionEvent.ACTION_UP://3.获取v，释放单例
                if (mVelocityTracker != null)
                    mVelocityTracker.addMovement(ev);
                if (mVelocityTracker != null) {
                    mVelocityTracker.computeCurrentVelocity(5000);//5s
                    int xV = (int) mVelocityTracker.getXVelocity();
                    int yV = (int) mVelocityTracker.getYVelocity();
                    KLogUtil.D(TAG, "dispatchTouchEvent", ": xV=" + xV + ",yV=" + yV);// xV=0,yV=0
                    mVelocityTracker.clear();
                    mVelocityTracker = null;
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                if (null != mVelocityTracker) {
                    mVelocityTracker.clear();
                    mVelocityTracker.recycle();
                    mVelocityTracker = null;
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * @desc:
     * @ref: http://blog.csdn.net/a772890398/article/details/50547693
     * http://blog.csdn.net/data_hlk/article/details/52877974?locationNum=14&fps=1
     * @author: key.guan @ 2017/4/25
     */

    private void testGestureDetector() {
        final GestureDetector mGestureDetector = new GestureDetector(this, new MySimpleOnGestureListener());
        //解决长按无法拖动的现象
        mGestureDetector.setIsLongpressEnabled(false);
        mGestureTv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //接管View的消费
                boolean consume = mGestureDetector.onTouchEvent(event);
                return consume;
            }
        });
    }

    private void testScroller() {
        mScrollTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPosInfo(v);
                KLogUtil.D(TAG, "testScroller", "onClick mScrollTv");
            }
        });
        getPosInfo(mScrollTv);
        KLogUtil.D(TAG, "testScroller", "1: getScrollX=" + mScrollTv.getScrollX() + ",getScrollY=" + mScrollTv.getScrollY());
        mScrollTv.scrollBy(400, 400);//为何view没有动呢？
        KLogUtil.D(TAG, "testScroller", "2: getScrollX=" + mScrollTv.getScrollX() + ",getScrollY=" + mScrollTv.getScrollY());
        getPosInfo(mScrollTv);
        // mScrollTv.getTranslationX()
        ObjectAnimator.ofFloat(mScrollTv, "translationX", 0, 200).setDuration(10000).start();

        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) mScrollTv.getLayoutParams();
        params.width += 100;
        params.leftMargin += 100;
        mScrollTv.requestLayout();//或者mScrollTv.setLayoutParams(params)或者指定一个空的view的宽度间接调整
        KLogUtil.D(TAG, "testScroller", "3: getScrollX=" + mScrollTv.getScrollX() + ",getScrollY=" + mScrollTv.getScrollY());
        getPosInfo(mScrollTv);

    }


    private void testScroller2() {
        //5s內，fraction匀速从0变化到1
        final ValueAnimator animator = ValueAnimator.ofInt(0, 1).setDuration(5000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float fraction = animator.getAnimatedFraction();
                KLogUtil.D(TAG, "testScroller2", "3: fraction=" + fraction);
                mScrollTv.scrollTo(0 + (int) (100 * fraction), 0);
            }
        });
        animator.start();
    }

    private void testScrollDelay() {
        //method 1
       // handler.sendEmptyMessageDelayed(0, 1000);
        //method 2
    /*    mScrollTv.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (count++ <= FRAME_COUNT) {
                    mScrollTv.scrollTo((int) (100 * count / FRAME_COUNT), 0);
                    mScrollTv.postDelayed(this,1000);
                }else {
                    count = 0;
                }
            }
        },1000);*/
        //method 3
      /*  new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                        if (count++ <= FRAME_COUNT) {
                            mScrollTv.scrollTo((int) (100 * count / FRAME_COUNT), 0);
                        } else {
                            break;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();*/

        //method 4
        CountDownTimer timer = new CountDownTimer(5000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mScrollTv.scrollTo((int) (100 * millisUntilFinished / 5000), 0);
            }

            @Override
            public void onFinish() {

            }
        };
        timer.start();

    }
    private static final float FRAME_COUNT = 30;
    private int count = 1;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (count++ <= FRAME_COUNT) {
                mScrollTv.scrollTo((int) (100 * count / FRAME_COUNT), 0);
                handler.sendEmptyMessageDelayed(0, 1000);
            }else {
                count = 0;
            }

        }
    };


    /**
     * @desc: 集中包含了以下三类监听器
     * {@link  GestureDetector.OnGestureListener},
     * {@link GestureDetector.OnDoubleTapListener},
     * {@link GestureDetector.OnContextClickListener}
     * @ref:http://blog.csdn.net/a772890398/article/details/50548601
     * @author: key.guan @ 2017/4/30
     */
    private class MySimpleOnGestureListener extends GestureDetector.SimpleOnGestureListener {
        //触摸屏幕 点下   down
        public boolean onDown(MotionEvent e) {
            KLogUtil.D(TAG, "onDown=" + e);
            return true;
        }

        //手指抬起  单击 up
        public boolean onSingleTapUp(MotionEvent e) {
            KLogUtil.D(TAG, "onSingleTapUp=" + e);
            return true;
        }

        //手指抬起以后一段时间（很短的时间） 没有再点击 就会判定为只是单击确认
        //如果 在等待的时间内又点击l一次   就是不会触发该方法  那就触发双击事件
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            KLogUtil.D(TAG, "onSingleTapConfirmed=" + e);
            return super.onSingleTapConfirmed(e);
        }

        // 点击事件的分类
        //1、点击立即抬起   onDown -->onSingleTapUp -->onSingleTapConfirmed
        //2、点击后没有滑动慢点抬起   onDown --> onShowPress--> onSingleTapUp -->onSingleTapConfirmed
        //3、点击后没有滑动很长时间才抬起
        //onDown --> onShowPress--> onLongPress-->onSingleTapUp -->onSingleTapConfirmed
        public void onShowPress(MotionEvent e) {
            KLogUtil.D(TAG, "onShowPress=" + e);
        }

        public void onLongPress(MotionEvent e) {//没有响应
            KLogUtil.D(TAG, "onLongPress=" + e);
        }

        // 双击的第二下Touch down时触发
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            KLogUtil.D(TAG, "onDoubleTap=" + e);
            return super.onDoubleTap(e);
        }

        // 双击的第二下Touch down和up都会触发，可用e.getAction()区分
        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            KLogUtil.D(TAG, "onDoubleTapEvent=" + e);
            return super.onDoubleTapEvent(e);
        }

        // e1：第1个ACTION_DOWN MotionEvent
        // e2：当前滑动到的位置的ACTION_MOVE MotionEvent
        //distanceX   distanceY  xy方向滑动的距离
        //在滑动的过程中多次调用
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            KLogUtil.D(TAG, "onScroll=e1" + e1 + ",e2=" + e2 + "distanceX=" + distanceX + ",distanceY=" + distanceY);
            return true;
        }

        @Override
        public boolean onContextClick(MotionEvent e) {
            KLogUtil.D(TAG, "onContextClick=" + e);
            return super.onContextClick(e);
        }


        // e1：第1个ACTION_DOWN MotionEvent
        // e2：最后一个ACTION_MOVE MotionEvent
        // velocityX：X轴上的移动速度，像素/秒         // velocityY：Y轴上的移动速度，像素/秒
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            KLogUtil.D(TAG, "onFling=e1" + e1 + ",e2=" + e2 + "distanceX=" + velocityX + ",distanceY=" + velocityY);
            return true;
        }
    }


    private void updateView() {
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
        KLogUtil.D(TAG, sb.toString());
        mInfoTv.setText(sb);
    }

    private int cnt = 0;

    private String getPosInfo(View v) {
        cnt++;
        StringBuffer sb = new StringBuffer(20);
        sb.append("l=").append(v.getLeft())
                .append(",r=").append(v.getRight())
                .append(",t=").append(v.getTop())
                .append(",b=").append(v.getBottom())
                .append("\n");
        sb
                .append(",x=").append(v.getX())
                .append(",y=").append(v.getY())
                .append(",transX=").append(v.getTranslationX())
                .append(",transY=").append(v.getTranslationY())
                .append("\n");
        KLogUtil.D(TAG, "getPosInfo():cnt=" + cnt + ":" + sb.toString());
        return sb.toString();
    }

}
