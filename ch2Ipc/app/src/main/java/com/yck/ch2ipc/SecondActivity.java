package com.yck.ch2ipc;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import com.yck.ch2ipc.utils.LogUtil;

public class SecondActivity extends Activity {
    private static final String TAG = "SecondActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
       // UserManager.sUserId = 3;
        LogUtil.E(TAG," UserManager.sUserId ="+UserManager.sUserId );
        findViewById(R.id.button1).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(SecondActivity.this, ThirdActivity.class);
                intent.putExtra("time", System.currentTimeMillis());
                startActivity(intent);
            }
        });
        Log.d(TAG, "onCreate");
    }

    @Override
    protected void onResume() {
        super.onResume();

        recoverFromFile();
    }

    private void recoverFromFile() {
        new Thread(new Runnable() {

            @Override
            public void run() {


            }
        }).start();
    }
}
