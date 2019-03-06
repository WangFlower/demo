package com.example.lib.structure.tree;

/**
 * @author wang.renguang
 * @time 2019/2/27
 * 顺序存储树
 */
public class ArrayTree<T> {

    private int DEFAULT_DEEP = 8;


    private Object[] datas;
    private int deep;
    private int arraySize;

    public ArrayTree() {
        this.deep = DEFAULT_DEEP;
        arraySize = (int) (Math.pow(2, deep) - 1);
        this.datas = new Object[arraySize];
    }

    /**
     * 添加节点
     *
     * @param index  父节点索引
     * @param data
     * @param isLeft 是否是左节点
     */
    public void add(int index, T data, boolean isLeft) {
        if (arraySize < index) {
            return;
        }
        if (datas[index] == null) {

            return;
        }

        if (2 * index >= arraySize) {
            return;
        }

        if (isLeft) {
            datas[2 * index + 1] = data;
        } else {
            datas[2 * index + 2] = data;
        }

    }


    public boolean empty() {
        return datas[0] == null;
    }

    public T root() {
        return (T) datas[0];
    }

    public T parent(int index) {
        return (T) datas[(index - 1) / 2];
    }

    public T left(int left) {
        return (T) datas[2 * left + 1];
    }

    public T right(int right) {
        return (T) datas[2 * right + 2];
    }

    public int deep() {
        return deep;
    }


    public static void main(String[] strings) {
    }

}
