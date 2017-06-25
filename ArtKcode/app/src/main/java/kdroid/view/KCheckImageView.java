package kdroid.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import k.art.R;

public class KCheckImageView extends RelativeLayout {
	private float mStateAlpha = 0.3f;
	private boolean mOnlyDisable = false;
	private boolean mChecked;
	private final View mCheckView;

	public boolean isChecked() {
		mChecked = checked;
		return mChecked;
	}

	public void setChecked(boolean checked) {
		if (mChecked == checked){
			return;
		}
		mCheckView.setVisibility(checked ? VISIBLE : INVISIBLE);
		setAlpha(checked ? mStateAlpha : 1.0f );
		this.checked = checked;
	}

	private boolean checked = false;

	public KCheckImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		CommonUtils.addView(this, R.layout.k_check_image_view);
		mCheckView = findViewById(R.id.k_check_iv);
	}
	@Override
	protected void drawableStateChanged() {
		super.drawableStateChanged();
		boolean flg = (!mOnlyDisable && (isPressed() || isFocused())) || !isEnabled();
		if (flg) {
			setAlpha(mStateAlpha);
		} else {
			setAlpha(1.0f);
		}
	}
}
