#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_simpleimageimprover_ui_MainActivity_stringFromJNI(JNIEnv *env, jobject thiz) {
        std::string hello = "Insert image\n(This string is implemented from C++)";
        return env->NewStringUTF(hello.c_str());
}