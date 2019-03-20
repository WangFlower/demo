package com.example.lib.structure.tree;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

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

        if (treeNode == root) {
            // 删除根节点
            root = null;
            return;
        }

        if (treeNode.left == null && treeNode.right == null) {
            // 没有左右子树的情况
            if (treeNode == treeNode.parent.left) {
                treeNode.parent.left = null;
            } else {
                treeNode.parent.right = null;
            }
            treeNode.parent = null;
        } else if (treeNode.left == null) {
            // 只有右子树的情况
            if (treeNode == treeNode.parent.left) {
                treeNode.parent.left = treeNode.right;
            } else {
                treeNode.parent.right = treeNode.right;
            }
            treeNode.right.parent = treeNode.parent;
        } else if (treeNode.right == null) {
            // 只有左子树
            if (treeNode == treeNode.parent.left) {
                treeNode.parent.left = treeNode.left;
            } else {
                treeNode.parent.right = treeNode.left;
            }
            treeNode.left.parent = treeNode.parent;
        } else {
            // 左子树都有

            // 找到右子树中最小的节点
            TreeNode node = treeNode.right;
            while (node.left != null) {
                node = node.left;
            }

            if (treeNode == treeNode.parent.left) {
                treeNode.parent.left = node;
            } else {
                treeNode.parent.right = node;
            }
            node.parent = treeNode.parent;
            node.left = treeNode.left;
            node.right = treeNode.right;
            treeNode.parent = null;
            treeNode.left = null;
            treeNode.right = null;
        }
    }


    public void print() {
        // 中序遍历得到有序序列
        List<V> data = print(root);

    }


    private List<V> print(TreeNode treeNode) {
        List<V> result = new ArrayList<>();
        if (treeNode.left != null) {
            result.addAll(print(treeNode.left));
        }
        result.add(treeNode.value);
        if (treeNode.right != null) {
            result.addAll(print(treeNode.right));
        }
        return result;
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
