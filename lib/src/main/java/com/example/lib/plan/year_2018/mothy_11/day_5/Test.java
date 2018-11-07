package com.example.lib.plan.year_2018.mothy_11.day_5;

public class Test {

    //[1,1,2,2,3,4,5]

    public int fun(int[] data){

        int j=0;
        for(int i=1;i<data.length;i++){
            if(data[j]!=data[i]){
                data[++j] = data[i];
            }
        }
        return j+1;
    }
}
