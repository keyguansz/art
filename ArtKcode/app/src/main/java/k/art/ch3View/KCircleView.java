package k.art.ch3View;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import k.art.R;

/**
 * Created by key on 2017/5/28.
 */

public class KCircleView extends View {
    public static final String TAG = "KView";
    private int mColor = Color.RED;
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public KCircleView(Context context) {
        this(context, null, 0);
        init();
    }

    public KCircleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        init();
    }

    public KCircleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        //自定义的属性，
        // 在values目录下面创建自定义属性的xml，比如attrs.xml,也可以其他名字，
        // <?xml version="1.0" encoding="utf-8"?>
      /*  <resources>
        <declare-styleable name="KCircleView">
        <attr name="circle_color" format="color" />
        </declare-styleable>
        </resources>*/
        final TypedArray a = context.obtainStyledAttributes(
                attrs, R.styleable.KCircleView, defStyle, 0);
        mColor = a.getColor(R.styleable.KCircleView_k_color, Color.RED);
        init();
    }

    private void init() {
        mPaint.setColor(mColor);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int w = widthMode == MeasureSpec.AT_MOST ? MeasureSpec.getSize(widthMeasureSpec) : 50;
        int h = heightMode == MeasureSpec.AT_MOST ? MeasureSpec.getSize(heightMeasureSpec) : 100;
        setMeasuredDimension(w, h);
    }

    @Override
    public void onDraw(Canvas canvas) {
        //处理android:padding
        int w = getWidth() - getPaddingLeft() - getPaddingRight();
        int h = getHeight() - getPaddingTop() - getPaddingEnd();
        canvas.drawCircle(getPaddingLeft() + w / 2, getPaddingTop() + getHeight() / 2, Math.min(w / 2, h / 2), mPaint);
        super.onDraw(canvas);
    }

}