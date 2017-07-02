LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)
LOCAL_MODULE := HelloJni //要生成的so库名，但实际为libHelloJni.so
LOCAL_SRC_FILES := k_art_ch14Ndk_HelloJni.cpp //指定编译的文件
include $(BUILD_SHARED_LIBRARY)