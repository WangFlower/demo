package com.example.lib.plan.year_2019.mothy_01.day_04;

import java.util.HashSet;
import java.util.Set;

public class Test {

    public static int fun(String s) {


        Set<Character> temp = new HashSet<>();
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            if (temp.contains(s.charAt(i))) {
                result = temp.size();
                temp.clear();
            } else {
                temp.add(s.charAt(i));
            }
        }
        return temp.size() > result ? temp.size() : result;
    }
}
