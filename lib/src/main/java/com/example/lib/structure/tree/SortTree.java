package com.example.lib.structure.tree;

import org.jetbrains.annotations.Nullable;

/**
 * 排序二叉树
 */
public class SortTree<V extends Comparable<V>> {

    private TreeNode root;

    public SortTree() {

    }


    public void insert(V v) {
        TreeNode node = new TreeNode();
        node.value = v;

        TreeNode currentNode = root;

        TreeNode resultNode = null;
        while (currentNode != null) {
            resultNode = currentNode;
            if (currentNode.value.compareTo(v) < 0) {
                currentNode = currentNode.left;
            } else {
                currentNode = currentNode.right;
            }
        }
        if (resultNode == null) {
            root = node;
            return;
        }
        node.parent = resultNode;
        if (resultNode.value.compareTo(v) < 0) {
            resultNode.left = node;
        } else {
            resultNode.right = node;
        }

    }


    public void delete(V v) {
        TreeNode node = search(v);
        if (node != null) {
            delete(node);
        }
    }


    private void delete(TreeNode treeNode) {
        if (treeNode.left == null) {
            treeNode.parent.
        }
    }


    @Nullable
    public TreeNode search(V v) {
        TreeNode currentNode = root;
        int temp;
        while (currentNode != null) {
            temp = currentNode.value.compareTo(v);
            if (temp < 0) {
                currentNode = currentNode.left;
            } else if (temp > 0) {
                currentNode = currentNode.right;
            } else {
                return currentNode;
            }
        }
        return null;
    }


    class TreeNode {
        V value;
        TreeNode left;
        TreeNode right;
        TreeNode parent;


    }
}
