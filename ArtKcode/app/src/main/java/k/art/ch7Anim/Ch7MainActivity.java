package k.art.ch7Anim;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import k.art.R;


public class Ch7MainActivity extends Activity {
    private final String TAG = "Ch7MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_ch7main);
        initView();
    }

    private void initView() {
        findViewById(R.id.ch7_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Ch7MainActivity.this, Ch7ViewAnimActivity.class));
            }
        });

        findViewById(R.id.ch7_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Ch7MainActivity.this, Ch7ActAnimActivity.class));
                overridePendingTransition(R.anim.ch7_animation_enter,R.anim.ch7_animation_back);
            }
        });
    }

}
