package com.example.momo.myapplication;

import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import android.util.Printer;

/**
 * Created by MOMO on 1/1/11.
 */

public class LooperMonitorHelper {

    public static int sLogThreshold = 100;
    private static final String TAG = "wangrenguang";

    public static void start() {
        monitorMainLooper();
    }

    private static void monitorMainLooper() {
        LooperLogger logger = new LooperLogger();
        Looper.getMainLooper().setMessageLogging(logger);
    }


    private static class LooperLogger implements Printer {
        private long msgCount;
        private boolean startedMonitor = false;
        private long startTime;
        private String lastLog;

        public LooperLogger() {

        }


        @Override
        public void println(String x) {
            if (!startedMonitor) {
                startTime = SystemClock.uptimeMillis();
                startedMonitor = true;
                lastLog = x;

            } else if (startTime != 0L) {

                msgCount++;
                long cost = SystemClock.uptimeMillis() - startTime;
                startTime = 0;

                if (cost > sLogThreshold) {
                    Log.i(TAG, "------"+lastLog);
                    Log.i(TAG, "--------------------");
                    final String formattedLog = format(lastLog);
                    final String msg = "MainLooper, cost=" + cost + ", " + formattedLog;
                    Log.i(TAG, msg);
                }
                startedMonitor = false;
            }
        }

        private static String format(String src) {
            if (src == null || src.length() == 0 || !src.startsWith(">>>")) {
                return src;
            }
            int substrBegin = -1, substrEnd = -1;
            // 1. find first part, handler
            substrBegin = src.indexOf('(');
            if (substrBegin == -1) {
                return src;
            }
            substrEnd = src.indexOf(')', substrBegin);
            if (substrEnd == -1) {
                return src;
            }
            String handler = src.substring(substrBegin + 1, substrEnd);

            // 2. find second part, callback
            substrBegin = src.indexOf("} ", substrEnd);
            if (substrBegin == -1) {
                return src;
            }
            String callback = null;
            if ((substrEnd = src.indexOf('@', substrBegin + 2)) != -1 ||
                    (substrEnd = src.indexOf(':', substrBegin + 2)) != -1 ||
                    (substrEnd = src.indexOf(' ', substrBegin + 2)) != -1) {
                callback = src.substring(substrBegin + 2, substrEnd);
            } else {
                return src;
            }

            // 3. find third part, message-id
            substrBegin = src.indexOf(": ", substrEnd);
            if (substrBegin == -1) {
                return src;
            }
            String msgId = src.substring(substrBegin + 2);

            // 4. combine them
            String ret = String.format("%s|%s|%s", handler, callback, msgId);

            return ret;
        }

        @Override
        public String toString() {
            return super.toString() + "(msgCount = " + msgCount + ")";
        }
    }


}
