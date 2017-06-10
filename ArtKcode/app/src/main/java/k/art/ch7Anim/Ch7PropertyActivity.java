package k.art.ch7Anim;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.FloatEvaluator;
import android.animation.IntEvaluator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.widget.Button;

import k.art.R;
import k.core.util.KLogUtil;


public class Ch7PropertyActivity extends Activity {

    private View mOV,mVV,mSV;
    private Button mBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_ch7_property);
        init();
    }

    private void init() {
        mOV = findViewById(R.id.ch7_property_o_tv);
        mVV = findViewById(R.id.ch7_property_v_tv);
        mSV = findViewById(R.id.ch7_property_s_tv);
        mBtn= (Button) findViewById(R.id.ch7_property_s_btn);

        ObjectAnimator.ofFloat(mOV,"translationY",50).start();

        ValueAnimator  valueAnimator = ObjectAnimator.ofInt(mVV,"backgroundColor",0x00FF8080, 0xFF8080FF);
        valueAnimator.setDuration(3*1000);
        valueAnimator.setEvaluator(new ArgbEvaluator());
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.start();
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                KLogUtil.D("onAnimationStart"+animation.toString());
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                KLogUtil.D("onAnimationEnd"+animation.toString());
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                KLogUtil.D("onAnimationCancel"+animation.toString());
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                KLogUtil.D("onAnimationRepeat"+animation.toString());
            }
        });
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                KLogUtil.D(""+animation.getAnimatedFraction());
            }
        });

        AnimatorSet  set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(mSV,"rotationX",0,360)
                ,ObjectAnimator.ofFloat(mSV,"rotationY",0,90)
                ,ObjectAnimator.ofFloat(mSV,"rotation",0,-90)
                ,ObjectAnimator.ofFloat(mSV,"translationX",90)
                ,ObjectAnimator.ofFloat(mSV,"scaleX",0,5f)
                ,ObjectAnimator.ofFloat(mSV,"alpha",0,0.25f,1));
        set.setDuration(10*1000);
        set.start();
        testInBtn();


    }

    private void testInBtn() {
        ObjectAnimator.ofInt(new AnimViewWraper(mBtn),"width",300).setDuration(10*1000).start();
        final Button btn = (Button) findViewById(R.id.ch7_property_lis_btn);
        performAnimator(btn,0,500);
        PropertyValuesHolder.
;
    }
    private void performAnimator(final View target, final int start, final int end) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(1, 100);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            //持有一个IntEvaluator对象，方便下面估值的时候使用
            private IntEvaluator mEvaluator = new IntEvaluator();

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //获得当前动画的进度值，整形1-100之间
                int currentValue = (int) animation.getAnimatedValue();
                //获得当前进度占整个动画之间的比例，浮点0-1之间
                float fraction = animation.getAnimatedFraction();
                //直接使用整形估值器，通过比例计算宽度，然后再设置给按钮
                target.getLayoutParams().width = mEvaluator.evaluate(fraction, start, end);
                target.requestLayout();
            }
        });
        valueAnimator.setDuration(5000).start();
    }

    public class MyEvaluator implements TypeEvaluator{


        @Override
        public Object evaluate(float fraction, Object startValue, Object endValue) {
            int startInt = (Integer) startValue;
            int startA = (startInt >> 24) & 0xff;
            int startR = (startInt >> 16) & 0xff;
            int startG = (startInt >> 8) & 0xff;
            int startB = startInt & 0xff;

            int endInt = (Integer) endValue;
            int endA = (endInt >> 24) & 0xff;
            int endR = (endInt >> 16) & 0xff;
            int endG = (endInt >> 8) & 0xff;
            int endB = endInt & 0xff;

            return (int)((startA + (int)(fraction * (endA - startA))) << 24) |
                    (int)((startR + (int)( (endR - startR))) << 16) |
                    (int)((startG + (int)(fraction * (endG - startG))) << 8) |
                    (int)((startB + (int)((endB - startB))));
        }
    }
}
