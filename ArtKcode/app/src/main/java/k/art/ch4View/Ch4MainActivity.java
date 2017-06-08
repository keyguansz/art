package k.art.ch4View;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewTreeObserver;


import k.art.R;
import k.art.ch3View.KCircleView;
import k.core.util.KLogUtil;


public class Ch4MainActivity extends Activity {
    private final String TAG = "Ch4MainActivity";
    private KCircleView mKv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_ch4main);
        mKv = (KCircleView)findViewById(R.id.act_ch4main_kv);
        KLogUtil.D(TAG,"onCreate,h="+ mKv.getMeasuredHeight()+",w="+ mKv.getMeasuredWidth());

        mKv.post(new Runnable() {
            @Override
            public void run() {
                KLogUtil.D(TAG,"ok2:Runnable,h="+ mKv.getMeasuredHeight()+",w="+ mKv.getMeasuredWidth());
            }
        });
        ViewTreeObserver o = mKv.getViewTreeObserver();
        o.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
               // mKv.getViewTreeObserver().removeOnGlobalFocusChangeListener(this);
                KLogUtil.D(TAG,"ok3:ViewTreeObserver,h="+ mKv.getMeasuredHeight()+",w="+ mKv.getMeasuredWidth());
            }
        });
    }
    public void onWindowFocusChanged(boolean hasFocus) {
        if (hasFocus){
            KLogUtil.D(TAG,"ok1:onWindowFocusChanged,h="+ mKv.getMeasuredHeight()+",w="+ mKv.getMeasuredWidth());
        }
    }
    protected void onResume() {
        KLogUtil.D(TAG,"onResume,h="+ mKv.getMeasuredHeight()+",w="+ mKv.getMeasuredWidth());
        super.onResume();
    }
    protected void onStart() {
        KLogUtil.D(TAG,"onStart,h="+ mKv.getMeasuredHeight()+",w="+ mKv.getMeasuredWidth());
        super.onStart();
    }
}
