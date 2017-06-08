package k.art.ch1lifecycle;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import k.art.R;


public class LaunchDemoStandardActivity extends AppCompatActivity {
    private static int mOnCreateCnt = 0;
    private static int mOnNewIntentCnt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lauch_demo_standard);
        mOnCreateCnt++;
        ((Button)findViewById(R.id.ok_btn)).setText("mOnCreateCnt="+mOnCreateCnt);
        findViewById(R.id.ok_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LaunchDemoStandardActivity.this,LaunchDemoStandardActivity.class);

                startActivity(intent);
            }
        });
        ((Button)findViewById(R.id.single_top_btn)).setText("mOnNewIntentCnt="+mOnNewIntentCnt
                +"taskId="+getTaskId());
        findViewById(R.id.single_top_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LaunchDemoStandardActivity.this,LaunchDemoStandardActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        findViewById(R.id.new_main_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LaunchDemoStandardActivity.this,MainActivity.class);

                startActivity(intent);
            }
        });

    }
    @Override
    public void onNewIntent(Intent intent){
        mOnNewIntentCnt++;
        ((Button)findViewById(R.id.single_top_btn)).setText("mOnNewIntentCnt="+mOnNewIntentCnt
        +"taskId="+getTaskId());
        super.onNewIntent(intent);
    }

}
