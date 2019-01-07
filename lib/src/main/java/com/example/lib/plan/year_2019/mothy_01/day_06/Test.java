package com.example.lib.plan.year_2019.mothy_01.day_06;

/**
 * @author wang.renguang
 * @time 2019/1/6
 */
public class Test {


    public static String fun(String s) {

        int l = 0;
        int r = 0;
        for (int i = 1; i < s.length(); i++) {

            int templ = i - 1;
            int tempr = i + 1;
            while (templ >= 0 && tempr < s.length()) {
                if (s.charAt(templ) != s.charAt(tempr)) {
                    break;
                }
                templ--;
                tempr++;
                if (tempr - templ > r - l) {
                    l = templ;
                    r = tempr;
                }
            }
        }
        return s.substring(l, r);
    }
}
