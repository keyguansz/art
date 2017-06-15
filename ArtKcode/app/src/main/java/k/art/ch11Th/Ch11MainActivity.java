package k.art.ch11Th;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import k.art.R;


public class Ch11MainActivity extends Activity {
    private final String TAG = "Ch11MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_ch11main);
        initView();
    }

    private void initView() {
        // 开启3个服务
        Intent intent = new Intent(this, KIntentService.class);
        intent.putExtra("task", "hi, 我是数据1");
        startService(intent);
        intent.putExtra("task", "hi, 我是数据2");
        startService(intent);
        intent.putExtra("task", "hi, 我是数据3");
        startService(intent);
    }
}
