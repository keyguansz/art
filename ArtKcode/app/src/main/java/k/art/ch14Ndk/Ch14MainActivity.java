package k.art.ch14Ndk;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import k.art.R;


public class Ch14MainActivity extends Activity {
    private final String TAG = "Ch13MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_ch14main);
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
