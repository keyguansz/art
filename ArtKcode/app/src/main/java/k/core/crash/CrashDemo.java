package k.core.crash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by key on 2017/9/23.
 */

public class CrashDemo {
    //请保证adapter的数据在主线程中进行更改!
    public static void main(){
        ACrash demo = new asListCrash();
        demo.onYes();
    }

    public static class asListCrash extends ACrash{

        @Override
        protected void onNo() {
            String str = "1,2,3,4,5";
            //java.util.Arrays$ArrayList
            List<String> list1 = Arrays.asList(str.split(","));//
        ArrayList<String> ArrayList1 = (ArrayList)Arrays.asList(str.split(","));//
        list1.remove(1);
        }

        @Override
        protected void onYes() {
            String str = "1,2,3,4,5";
            List<String> list1 = Arrays.asList(str.split(","));//
            ArrayList<String> listOk = new ArrayList<>(list1);
            listOk.addAll(list1);
            listOk.remove(1);
        }
    }
}
