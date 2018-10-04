package com.example.lib.thread;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class MemoizerDemo {

    public interface Computable<A, V> {
        V compute(A arg) throws InterruptedException;
    }


    public class Memoizer<A, V> implements Computable<A, V> {

        private final Map<A, FutureTask<V>> cache = new ConcurrentHashMap<A, FutureTask<V>>();
        private final Computable<A, V> computable;


        public Memoizer(Computable<A, V> computable) {
            this.computable = computable;
        }


        @Override
        public V compute(final A arg) throws InterruptedException {
            while (true) {
                // todo 限制循环重试次数  缓存时间设置  缓存有效期设置
                FutureTask<V> f = cache.get(arg);
                if (f == null) {
                    f = new FutureTask<V>(new Callable<V>() {
                        @Override
                        public V call() throws Exception {
                            return computable.compute(arg);
                        }
                    });
                    cache.putIfAbsent(arg, f);
                    f.run();
                }
                try {
                    return f.get();
                } catch (CancellationException e) {
                    cache.remove(arg, f);
                } catch (ExecutionException e) {
                    throw new IllegalStateException("", e);
                }
            }

        }
    }

}
