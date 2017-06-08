package com.yck.ch2ipc.utils;

import android.util.Log;

/**
 * <p>FileName:LogUtil.java </p>
 * <p>Description: LogUtil 功能说明</p>
 * <p>Copyright (c) 2016 yuanchuangke All rights reserved.</p>
 *
 * @author GuanJian
 * @version 1.0: by GuanJian on 2016/9/25 23:18
 * @email yuanchuangke@foxmail.com
 */
public class LogUtil {
    private static final String TAG = "ART";
    public static void E(String log){
        Log.d(TAG, log);
    }
    public static void E(String clsName,String log){
        Log.d(TAG, clsName+"->"+log);
    }
    public static void E(String clsName,String methodName, String log){
        Log.d(TAG, clsName+"->"+methodName+"():" + log);
    }
}
