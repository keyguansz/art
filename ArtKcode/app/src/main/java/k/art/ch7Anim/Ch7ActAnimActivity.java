package k.art.ch7Anim;

import android.app.Activity;
import android.os.Bundle;

import k.art.R;

public class Ch7ActAnimActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch7_act_anim);
    }
    @Override
    public void onBackPressed() {
       // overridePendingTransition(R.anim.ch7_animation_enter,R.anim.ch7_animation_back);
        super.onBackPressed();
        overridePendingTransition(R.anim.ch7_animation_enter,R.anim.ch7_animation_back);

    }

}
