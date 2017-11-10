package k.art;


import android.app.Application;
import android.content.res.Configuration;

import k.core.crash.CrashDemo;
import k.core.util.KTextUtil;
import k.core.util.kil.KImgLoader;

/**
 * Created by key on 2017/4/16.
 */

public class KApplication extends Application {

    public KApplication() {
        super();
    }
   private static KApplication ins = null;
    public static KApplication getIns(){
        return ins;
    }

    public void onCreate() {
        super.onCreate();
        ins = this;
        CrashDemo.asList();
        KImgLoader.getIns().init(this);
        KTextUtil.test();
       // KDimenUtil.main();
    }

    public void onTerminate() {
        super.onTerminate();


    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

    }
}
