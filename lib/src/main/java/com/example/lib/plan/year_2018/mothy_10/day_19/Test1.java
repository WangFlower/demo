package com.example.lib.plan.year_2018.mothy_10.day_19;

public class Test1 {

    public static void find(int[] datas, int des) {
        int size = datas.length;
        for (int i = 0; i < size - 1; i++) {
            int temp1 = datas[i];
            for (int j = i + 1; j < size; j++) {
                if (temp1 + datas[j] == des) {
                    System.out.println("i=" + i);
                    System.out.println("j=" + j);
                }
            }
        }
    }


    public static void main(String[] a) {
        int[] datas = {1,3,6,2,9};
        find(datas,5);
    }
}
