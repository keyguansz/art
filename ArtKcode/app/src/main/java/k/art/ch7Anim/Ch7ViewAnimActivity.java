package k.art.ch7Anim;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import k.art.R;


public class Ch7ViewAnimActivity extends Activity {
    private final String TAG = "Ch7MainActivity";
    private View mTestAn1View;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_ch7view);
        mTestAn1View = findViewById(R.id.test_am1);
       // test1();
        test3();
        createList();

    }
    private void test1() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.test1);
        mTestAn1View.startAnimation(animation);
    }
    private void test2() {
        AlphaAnimation alpha = new AlphaAnimation(0,1);
        alpha.setDuration(4*1000);
        alpha.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.i("TAG", "动画开始");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.i("TAG", "动画结束");
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                Log.i("TAG", "重复动画");
            }
        });
        mTestAn1View.startAnimation(alpha);
    }
    private void test3() {
        Rotate3dAnimation animation = new Rotate3dAnimation(
                0,180,mTestAn1View.getMeasuredWidth()/2,mTestAn1View.getHeight()/2,90,true);
        mTestAn1View.startAnimation(animation);
    }
    private void createList() {
        ListView listView = (ListView)findViewById(R.id.mListView);
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.anim_layout);
        LayoutAnimationController controller = new LayoutAnimationController(animation);
        controller.setDelay(5f);
        controller.setOrder(LayoutAnimationController.ORDER_NORMAL);
        listView.setLayoutAnimation(controller);


        ArrayList<String> datas = new ArrayList<String>();
        for (int i = 0; i < 50; i++) {
            datas.add("name " + i);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.content_list_item, R.id.name, datas);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(Ch7ViewAnimActivity.this, "click item",
                        Toast.LENGTH_SHORT).show();

            }
        });


    }
}
