package k.art.ch3View;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * Created by key on 2017/5/28.
 */

public class KListViewIn extends ListView {

    public static final String TAG = "KListViewIn";

    private int mLastX = 0;
    private int mLastY = 0;


    public KListViewIn(Context context) {
        super(context);
    }

    public KListViewIn(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public KListViewIn(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev){
        int x = (int)ev.getX();
        int y = (int)ev.getY();
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                if (Math.abs(mLastX - x) > Math.abs(mLastY - y)){
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
            default:
                break;
        }
        mLastX = x;
        mLastY = y;
        return super.dispatchTouchEvent(ev);
    }

}