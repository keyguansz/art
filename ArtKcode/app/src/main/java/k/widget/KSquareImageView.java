package k.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;



public class KSquareImageView extends ImageView {

    public KSquareImageView(Context context) {
        super(context);
    }

    public KSquareImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public KSquareImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}
