/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class com_example_elcapi_jnielc */

#ifndef _Included_com_example_elcapi_jnielc
#define _Included_com_example_elcapi_jnielc
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     com_example_elcapi_jnielc
 * Method:    open
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_example_elcapi_jnielc_open
  (JNIEnv *, jclass);

/*
 * Class:     com_example_elcapi_jnielc
 * Method:    ScreenOn
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_com_example_elcapi_jnielc_ScreenOn
  (JNIEnv *, jclass, jint);

/*
 * Class:     com_example_elcapi_jnielc
 * Method:    ScreenOff
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_com_example_elcapi_jnielc_ScreenOff
  (JNIEnv *, jclass, jint);

/*
 * Class:     com_example_elcapi_jnielc
 * Method:    openWatchdog
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_com_example_elcapi_jnielc_openWatchdog
  (JNIEnv *, jclass, jint);

/*
 * Class:     com_example_elcapi_jnielc
 * Method:    closeWatchdog
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_com_example_elcapi_jnielc_closeWatchdog
  (JNIEnv *, jclass, jint);

/*
 * Class:     com_example_elcapi_jnielc
 * Method:    feedWatchdog
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_com_example_elcapi_jnielc_feedWatchdog
  (JNIEnv *, jclass, jint);

/*
 * Class:     com_example_elcapi_jnielc
 * Method:    touchOn
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_com_example_elcapi_jnielc_touchOn
  (JNIEnv *, jclass, jint);

/*
 * Class:     com_example_elcapi_jnielc
 * Method:    touchOff
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_com_example_elcapi_jnielc_touchOff
  (JNIEnv *, jclass, jint);

JNIEXPORT jint JNICALL Java_com_example_elcapi_jnielc_ledred
    (JNIEnv *, jclass, jint);

JNIEXPORT jint JNICALL Java_com_example_elcapi_jnielc_ledblue
    (JNIEnv *, jclass, jint);

JNIEXPORT jint JNICALL Java_com_example_elcapi_jnielc_ledgreen
    (JNIEnv *, jclass, jint);

JNIEXPORT jint JNICALL Java_com_example_elcapi_jnielc_ledmix
    (JNIEnv *, jclass, jint);

JNIEXPORT jint JNICALL Java_com_example_elcapi_jnielc_ledoff
    (JNIEnv *, jclass);

JNIEXPORT jint JNICALL Java_com_example_elcapi_jnielc_ledseek
    (JNIEnv *, jclass, jint ,jint);

JNIEXPORT jint JNICALL Java_com_example_elcapi_jnielc_seekstart
    (JNIEnv *, jclass);

JNIEXPORT jint JNICALL Java_com_example_elcapi_jnielc_seekstop
    (JNIEnv *, jclass);

#ifdef __cplusplus
}
#endif
#endif