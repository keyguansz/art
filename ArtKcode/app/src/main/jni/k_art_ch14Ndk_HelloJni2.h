/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class k_art_ch14Ndk_HelloJni */

#ifndef _Included_k_art_ch14Ndk_HelloJni
#define _Included_k_art_ch14Ndk_HelloJni
/*这个宏定义是必须的, 作用是指定extern”C”内部的函数采用C语言的命名风格来编译.
如果设定那么当JNI采用C++来实现时, 由于C/C++编译过程对函数的命名风格不同,
这将导致JNI在链接时无法根据函数名找到具体的函数, 那么JNI调用肯定会失效.*/
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     k_art_ch14Ndk_HelloJni
 * Method:    get
 * Signature: ()Ljava/lang/String;
 */
   //函数名:格式遵循:Java_包名_类名_方法名包名之间的.分割全部替换成_分割.
    /* 参数: jstring是代表String类型参数. 具体的类型关系后面会说明.
         JNIEnv *: 表示一个指向JNI环境的指针, 可以通过它来访问JNI提供的方法.
         jobject: 表示java对象中的this.
         JNIEXPORT和JNICALL: 这是JNI种所定义的宏, 可以在jni.h这个头文件查到*/
JNIEXPORT jstring JNICALL Java_k_art_ch14Ndk_HelloJni_get
  (JNIEnv *, jobject);

/*
 * Class:     k_art_ch14Ndk_HelloJni
 * Method:    set
 * Signature: (Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_k_art_ch14Ndk_HelloJni_set
  (JNIEnv *, jobject, jstring);

#ifdef __cplusplus
}
#endif
#endif
