package com.example.lib.plan.year_2019.mothy_01.day_03;

public class Test {

    public static class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
        }
    }


    public static Node fun(Node node1, Node node2) {
        Node temp1 = node1;
        Node temp2 = node2;
        int carry = 0;
        int num = 0;
        Node result = null;
        Node curr = null;
        while (temp1 != null || temp2 != null) {
            num += carry;
            num += temp1 == null ? 0 : temp1.data;
            num += temp2 == null ? 0 : temp2.data;
            carry = num / 10;
            curr = new Node(num % 10);
            if (result == null) {
                result = curr;
            } else {
                result.next = curr;
            }
            temp1 = temp1 == null ? null : temp1.next;
            temp2 = temp2 == null ? null : temp2.next;
        }
        if (carry > 0) {
            curr.next = new Node(carry);
        }
        return result;
    }
}
