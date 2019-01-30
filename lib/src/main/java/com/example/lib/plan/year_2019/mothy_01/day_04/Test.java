package com.example.lib.plan.year_2019.mothy_01.day_04;

import java.util.HashSet;
import java.util.Set;

public class Test {

    public static int fun(String s) {
        Set<Character> temp = new HashSet<>();
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            if (temp.contains(s.charAt(i))) {
                result = Math.max(temp.size(), result);
                log(temp);
                temp.clear();
            }
            temp.add(s.charAt(i));
        }
        return temp.size() > result ? temp.size() : result;
    }

    private static void log(Set<Character> set) {
        System.out.println("----s----");
        for (Character character : set) {
            System.out.println(character);
        }
        System.out.println("----e----");
    }

    public void main(String[] args) {
        System.out.println(fun("pwwkew"));
    }
}
