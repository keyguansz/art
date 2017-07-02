package k.art.ch14Ndk;

/**
 * Created by key on 2017/6/25.
 */

public class HelloJni {
    static {
        System.loadLibrary("HelloJni");
    }
    public native String get();
    public native void set(String str);
    // mian
    public static void main(String arg[]) {
        HelloJni jniTest = new HelloJni();
        System.out.println(jniTest.get());
        jniTest.set("java set");
    }
}
