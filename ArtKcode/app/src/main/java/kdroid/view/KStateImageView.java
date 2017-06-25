package kdroid.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

public class KStateImageView extends ImageView {

    private View mView = null;
	private float mStateAlpha = 0.3f;
	private boolean mOnlyDisable = false;

	public KStateImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	@Override
	protected void drawableStateChanged() {
		super.drawableStateChanged();

		boolean flg = (!mOnlyDisable && (isPressed() || isFocused())) || !isEnabled();
		if (flg) {
			setAlpha(mStateAlpha);
			if (null != mView) {
                mView.setAlpha(mStateAlpha);
            }
		} else {
			setAlpha(1.0f);
			if (null != mView) {
                mView.setAlpha(1.0f);
            }
		}
	}
}
