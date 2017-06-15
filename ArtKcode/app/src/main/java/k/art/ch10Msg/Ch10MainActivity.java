package k.art.ch10Msg;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Toast;

import k.art.KApplication;
import k.art.R;
import k.core.util.KLogUtil;


public class Ch10MainActivity extends Activity {
    private final String TAG = "Ch10MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_ch10main);
        initView();
    }

    private void initView() {
        findViewById(R.id.main_1_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KLogUtil.T("toast when main_1_tv click");
            }
        });
        testLooperException();
        testThreadLocal();

    }
    private void testLooperException() {
        final String log = "toast in sub Thread ..";
        new Thread(new Runnable() {
            @Override
            public void run() {//Can't create handler inside thread that has not called Looper.prepare()
               /* Handler handler = new Handler();//fix
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(KApplication.getIns(),log,Toast.LENGTH_LONG).show();
                    }
                });
            }*/
            }
        }).start();


        //解决办法1
        new Thread(new Runnable() {
            @Override
            public void run() {
                Handler handler = new Handler(Looper.getMainLooper());//fix
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(KApplication.getIns(),log,Toast.LENGTH_LONG).show();
                    }
                });
            }
        }).start();
        //解决办法2
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                Handler handler = new Handler();//fix
                Looper.loop();
            }
        }).start();



    }
    ThreadLocal<Boolean> tl = new ThreadLocal<>();
    private void testThreadLocal() {
        tl.set(true);
        KLogUtil.E("testThreadLocal:main"+tl.get());//true
        new Thread("thread#1"){
            @Override
            public void run(){
                tl.set(false);
                KLogUtil.E("testThreadLocal:thread#1"+tl.get());//thread#1false
            }
        }.start();
        new Thread("thread#2"){
            @Override
            public void run(){
                KLogUtil.E("testThreadLocal:thread#2"+tl.get());// thread#2null
            }
        }.start();
        Looper.loop();
    }
}
