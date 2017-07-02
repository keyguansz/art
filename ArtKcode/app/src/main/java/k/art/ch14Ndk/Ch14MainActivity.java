package k.art.ch14Ndk;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import k.art.R;
import k.core.util.KLogUtil;


public class Ch14MainActivity extends Activity {
    private final String TAG = "Ch13MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_ch14main);
        HelloJni jniTest = new HelloJni();
        KLogUtil.D(TAG, "jniTest.get =  " + jniTest.get());
        initView();
    }

    private void initView() {
        findViewById(R.id.main_1_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
