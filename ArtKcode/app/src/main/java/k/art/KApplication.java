package k.art;


import android.app.Application;
import android.content.res.Configuration;

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
       // KDimenUtil.main();
    }

    public void onTerminate() {
        super.onTerminate();


    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

    }
}
