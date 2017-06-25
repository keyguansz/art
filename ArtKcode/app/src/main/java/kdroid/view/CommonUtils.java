package kdroid.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class CommonUtils {
    
    public static void addView(View parent, int layoutId) {
        addView(parent, layoutId, parent.getContext());
    }

    public static void addView(View parent, int layoutId, Context context) {
        View v = LayoutInflater.from(context).inflate(layoutId, null);
        if(parent instanceof LinearLayout && v instanceof LinearLayout) {
            addView((LinearLayout)parent, (LinearLayout)v);
        } else {
            ((ViewGroup)parent).addView(v);
        }
    }

    public static void addView(LinearLayout parent, LinearLayout child) {
        ArrayList<View> views = new ArrayList<View>();
        ArrayList<LinearLayout.LayoutParams> params = new ArrayList<LinearLayout.LayoutParams>();
        int count = child.getChildCount();
        for(int i = 0; i < count; i++) {
            views.add(child.getChildAt(i));
            params.add((android.widget.LinearLayout.LayoutParams)child.getChildAt(i).getLayoutParams());
        }
        
        child.removeAllViews();
        parent.setOrientation(child.getOrientation());
        for(int i = 0; i < count; i++) {
            parent.addView(views.get(i), params.get(i));
        }
    }

}
