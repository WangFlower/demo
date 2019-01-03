package com.example.lib.plan.year_2019.mothy_01.day_02;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wang.renguang
 * @time 2019/1/2
 */
public class Test {


    public static int[] fun(int[] arg, int result) {

        int temp;
        int length = arg.length;
        Map<Integer, Integer> tempMap = new HashMap(length);
        for (int i = 0; i < length; i++) {
            temp = result - arg[i];
            if (tempMap.containsKey(temp)) {
                return new int[]{i, tempMap.get(temp), i};
            }
            tempMap.put(arg[i], i);
        }

        throw new IllegalArgumentException("has no find");
    }
}

