package com.example.lib.plan.year_2018.mothy_11.day_6;

/**
 * @author wang.renguang
 * @time 2018/12/27
 */
public class SamList<T> {


    private Node firstNode;
    private Node lastNode;

    public class Node {
        Node pre;
        Node next;
        T data;
    }

    public SamList() {

    }

    private void init(T t) {
        firstNode = new Node();
        firstNode.data = t;
        lastNode = firstNode;
    }


    public void put(T t) {
        if (firstNode == null) {
            init(t);
        } else {
            Node temp = new Node();
            lastNode.next = temp;
            temp.pre = lastNode;
            lastNode = temp;
        }
    }


    public boolean delete(T t) {
        Node temp = firstNode;
        return false;
    }

    public boolean find(T t) {


        return false;
    }


    public void clear() {
        Node temp = lastNode;
        while (temp != null) {
            lastNode = temp.pre;
            temp.pre = null;
            lastNode.next = null;
            temp = lastNode;
        }
    }

    public void insetBefore(T t, T taget) {


    }


}
