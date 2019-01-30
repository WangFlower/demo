package com.example.lib.plan.year_2019.mothy_01.day_08;





public class Test2 {


    /**
     * 判断是否包含 s1 的排列的方法：
     截取 s2 中某长度和 s1 字符串长度相等的子串，判断两者每个字符的数量是否一致即可。

     统计字符数量
     由于字符串只包含 26 个小写字母，我们可以使用 计数排序 来统计，即创建一个长度为 26 的数组，其下标 0 ~ 25 对应 a ~ z 的每个字母，其值为对应字母出现的次数。

     判断条件
     先统计 s1 的字符数量 count1，再依次统计 s2 中与之长度相等的子串的字符数量 count2，比较两者是否相同。
     ---------------------
     作者：阿飞__
     来源：CSDN
     原文：https://blog.csdn.net/afei__/article/details/85058158
     版权声明：本文为博主原创文章，转载请附上博文链接！
     * @param s1
     * @param s2
     * @return
     */
    public static boolean fun(String s1, String s2) {
        int[] count1 = new int[26];
        int[] count2 = new int[26];

        for (int i = 0; i < s1.length(); i++) {
            count1[s1.charAt(i) - 'a']++;
            count2[s2.charAt(i) - 'a']++;
        }

        for (int i = s1.length(); i < s2.length(); i++) {
            if (isSame(count1, count2)) {
                return true;
            }
            count2[s2.charAt(i - s1.length())]--;
            count2[s2.charAt(i) - 'a']++;
        }
        return isSame(count1, count2);
    }

    public static boolean isSame(int[] count1, int[] count2) {
        for (int i = 0; i < count1.length; i++) {
            if (count1[i] != count2[i]) {
                return false;
            }
        }
        return true;
    }
}
