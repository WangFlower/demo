package com.example.momo.myapplication;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by wang.renguang on 2018/5/30.
 */
public class ActivityThreadHookHelper {

    public static void doHookActivityStart() {

        try {
            Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
            Method currentActivityThreadMethod = activityThreadClass.getDeclaredMethod("currentActivityThread");
            Object activityThread = currentActivityThreadMethod.invoke(null);

            Field mInstrumentationField = activityThreadClass.getDeclaredField("mInstrumentation");
            mInstrumentationField.setAccessible(true);
            Instrumentation originalInstrumentation = (Instrumentation) mInstrumentationField.get(activityThread);
            mInstrumentationField.set(activityThread, new MInstrumentation(originalInstrumentation));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class MInstrumentation extends Instrumentation {

        private Instrumentation instrumentation;

        public MInstrumentation(Instrumentation instrumentation) {
            this.instrumentation = instrumentation;
        }

        public ActivityResult execStartActivity(
                Context who, IBinder contextThread, IBinder token, Activity target,
                Intent intent, int requestCode, Bundle options) {
            Log.i("wangrenguang", "请注意! startActivity已经被hook了!");
            try {
                Method execStartActivity = Instrumentation.class.getDeclaredMethod("execStartActivity", Context.class,
                                                                                   IBinder.class, IBinder.class, Activity.class,
                                                                                   Intent.class, int.class, Bundle.class);
                return (ActivityResult) execStartActivity.invoke(instrumentation, who, contextThread, token, target,
                                                                 intent, requestCode, options);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
    }

}