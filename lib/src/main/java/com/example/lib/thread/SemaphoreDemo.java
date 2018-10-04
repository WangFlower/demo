package com.example.lib.thread;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;

public class SemaphoreDemo<T> {

    private final Set<T> set;
    private Semaphore semaphore;

    public SemaphoreDemo(int bound) {
        set = Collections.synchronizedSet(new HashSet<T>());
        semaphore = new Semaphore(bound);
    }

    public boolean add(T t) throws InterruptedException {
        semaphore.acquire();
        boolean wasAdd = false;

        try {
            wasAdd = set.add(t);
            return wasAdd;
        } finally {
            if (!wasAdd) {
                semaphore.release();
            }
        }
    }

    public boolean remove(T t) {
        boolean wasRemove = set.remove(t);
        if (wasRemove) {
            semaphore.release();
        }
        return wasRemove;
    }
}
