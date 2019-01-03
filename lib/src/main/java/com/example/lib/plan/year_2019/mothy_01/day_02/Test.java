package com.example.lib.plan.year_2019.mothy_01.day_02;

import java.util.HashMap;

public class Test {

    public static int[] fun(int[] args, int result) {

        int temp;
        HashMap<Integer, Integer> tempMap = new HashMap(args.length);

        for (int i = 0; i < args.length; i++) {
            temp = result - args[i];
            if (tempMap.containsKey(temp)) {
                return new int[]{tempMap.get(temp), i};
            }
            tempMap.put(args[i], i);
        }
        throw new IllegalArgumentException("has not find");
    }
}
