package k.art.ch1Launch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import k.art.R;


public class LaunchModeActivity extends AppCompatActivity {
    private static int mOnCreateCnt = 0;
    private static int mOnNewIntentCnt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_mode);
        mOnCreateCnt++;
        ((Button)findViewById(R.id.standard_btn)).setText("standard:mOnCreateCnt="+mOnCreateCnt);
        findViewById(R.id.standard_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LaunchModeActivity.this,LaunchModeActivity.class);
                startActivity(intent);
            }
        });
        ((Button)findViewById(R.id.single_top_btn)).setText("single_top:mOnNewIntentCnt="+mOnNewIntentCnt
                +"taskId="+getTaskId());
        findViewById(R.id.single_top_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LaunchModeActivity.this,LaunchModeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        findViewById(R.id.start_other_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LaunchModeActivity.this,Ch1MainActivity.class);
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
