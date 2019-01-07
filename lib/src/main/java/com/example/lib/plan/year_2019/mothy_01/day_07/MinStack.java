package com.example.lib.plan.year_2019.mothy_01.day_07;

import java.util.Stack;

/**
 * @author wang.renguang
 * @time 2019/1/7
 * <p>
 * <p>
 * 用两个栈来维护
 */

public class MinStack {


    private Stack<Integer> data = new Stack<>();
    private Stack<Integer> minData = new Stack<>();


    public Integer pop() {
        minData.pop();
        return data.pop();
    }

    public void push(int x) {

        data.push(x);
        Integer temp = minData.peek();
        if (temp != null && temp.compareTo(x) < 0) {
            minData.push(temp);
        } else {
            minData.push(x);
        }
    }

    public Integer top() {
        return data.peek();
    }

    public Integer min() {
        return minData.peek();
    }


}
