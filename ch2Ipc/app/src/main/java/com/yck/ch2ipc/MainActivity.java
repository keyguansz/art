package com.yck.ch2ipc;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import com.yck.ch2ipc.utils.LogUtil;

public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UserManager.sUserId = 2;
        LogUtil.E(TAG," UserManager.sUserId ="+UserManager.sUserId );
        findViewById(R.id.button1).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SecondActivity.class));
            }
        });

    }

    @Override
    protected void onResume() {

      //  persistToFile();

        super.onResume();
    }

    private void persistToFile() {
        new Thread(new Runnable() {

            @Override
            public void run() {

            }
        }).start();
    }
}
