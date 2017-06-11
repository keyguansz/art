package k.art.ch8W;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import k.art.R;
import k.art.ch7Anim.Ch7ActAnimActivity;
import k.art.ch7Anim.Ch7PropertyActivity;
import k.art.ch7Anim.Ch7ViewAnimActivity;


public class Ch8MainActivity extends Activity {
    private final String TAG = "Ch7MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
        initView();
    }

    private void initView() {
        findViewById(R.id.main_1_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showWindow();
            }
        });

    }

    private void showWindow() {
        final Button button = new Button(Ch8MainActivity.this);
        button.setText("K窗口");
        final WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        final WindowManager.LayoutParams layout = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,0,0, PixelFormat.TRANSLUCENT);
        layout.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                |WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED;
        layout.gravity = Gravity.CENTER;
        layout.type = WindowManager.LayoutParams.TYPE_PHONE;
        layout.x = 0;
        layout.y = 0;
        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int rawX = (int) event.getRawX();
                int rawY = (int) event.getRawY();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        layout.x = rawX;
                        layout.y = rawY;
                        wm.updateViewLayout(button, layout);
                        break;
                }
                return false;
            }
        });
        wm.addView(button,layout);


    }

}
