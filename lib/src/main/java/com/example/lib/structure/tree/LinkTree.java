package com.example.lib.structure.tree;

/**
 * @author wang.renguang
 * @time 2019/2/25
 * 链表二叉树
 */
public class LinkTree<V> {

    private Node root;


    public LinkTree() {
        root = new Node();
    }


    public void addLeft(Node parent, V left) {
        addNode(parent, left, null);
    }

    public void addRight(Node parent, V right) {
        addNode(parent, null, right);

    }

    public void addNode(Node parent, V left, V right) {
        if (left != null) {
            parent.left = new Node(left);
        }
        if (right != null) {
            parent.right = new Node(right);
        }
    }


    public void deep() {
        deep(root);
    }


    private int deep(Node node) {
        if (node == null) {
            return 0;
        }
        if (node.right == null && node.left == null) {
            return 1;
        }

        int left = deep(node.left);
        int right = deep(node.right);

        return Math.max(left, right) + 1;

    }


    private class Node {
        V v;
        Node left;
        Node right;

        public Node() {

        }

        public Node(V v) {
            this.v = v;
        }

        public Node(V v, Node left, Node right) {
            this.v = v;
            this.left = left;
            this.right = right;
        }
    }


}
