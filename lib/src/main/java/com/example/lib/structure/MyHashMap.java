package com.example.lib.structure;

/**
 * @author wang.renguang
 * @time 2019/2/25
 */
public class MyHashMap<K, V> {


    private Node<K, V>[] buckets;


    public void put(K k, V v) {

    }


    public void get(K k) {

    }

    private void resize() {

    }


    private int hash(Object object) {
        return 0;
    }


    private static class Node<K, V> {

        final int hash;
        final K k;
        V v;
        Node next;

        public Node(int hash, K k, V v, Node next) {
            this.hash = hash;
            this.k = k;
            this.v = v;
            this.next = next;
        }
    }
}
