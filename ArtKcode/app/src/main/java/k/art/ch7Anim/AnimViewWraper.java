package k.art.ch7Anim;

import android.view.View;

/**
 * Created by key on 2017/6/10.
 */

public class AnimViewWraper {
    private View view;

    public AnimViewWraper(View view) {
        this.view = view;
    }

    public int getWidth() {
        return view.getLayoutParams().width;
    }

    public void setWidth(int width) {
        view.getLayoutParams().width = width;
        view.requestLayout();
    }
}
