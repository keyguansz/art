#include "k_art_ch14Ndk_HelloJni.h"
JNIEXPORT jstring JNICALL Java_k_art_ch14Ndk_HelloJni_get(JNIEnv *env, jobject jObj){
       // return env->NewStringUTF("I`m Str from jni libs!");
       //return (*env)->NewStringUTF(env, "Hello world from jni)");//C语言格式，文件名应为xxx.c
       return env->NewStringUTF((char *)"Hello JIN");//C++格式，文件名应为xxx.cpp
  }
JNIEXPORT void JNICALL Java_k_art_ch14Ndk_HelloJni_set
  (JNIEnv *env, jobject, jstring jStr){
;
  }