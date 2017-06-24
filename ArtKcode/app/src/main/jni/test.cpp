#inclue "k_art_ch14Ndk_HelloJni.h"
#inclue <stdio.h>
//
// Created by key on 2017/6/25.
//
JNIEXPORT jstring JNICALL Java_k_art_ch14Ndk_HelloJni_get
  (JNIEnv *env, jobject obj){
  printf("get in cpp\n");
  return env->Ne
  }

/*
 * Class:     k_art_ch14Ndk_HelloJni
 * Method:    set
 * Signature: (Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_k_art_ch14Ndk_HelloJni_set
  (JNIEnv *, jobject, jstring);
