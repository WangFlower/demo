package com.example.momo.myapplication.demo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.momo.myapplication.BaseActivity;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.FlowableSubscriber;
import io.reactivex.FlowableTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.flowables.ConnectableFlowable;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * Created by wangrenguang on 2017/3/24.
 * RX
 */

public class RxDemoActivity extends BaseActivity {
    private static final String TAG = "RxDemoActivity";

    CompositeDisposable compositeDisposable = new CompositeDisposable();
    Flowable<String> flowable;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            handler.sendEmptyMessageDelayed(1, 1000);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        setContentView(R.layout.activity_main);

        //        try {
        //            FrameSequence frameSequence = FrameSequence.decodeStream(getAssets().open("webp.webp"));
        //            FrameSequenceDrawable frameSequenceDrawable = new FrameSequenceDrawable(frameSequence);
        //            ImageView imageView = (ImageView) findViewById(R.id.webp);
        //            imageView.setImageDrawable(frameSequenceDrawable);
        //        } catch (IOException e) {
        //            e.printStackTrace();
        //        }

        //创建一个上游 Observable：
        demo10();

    }

    public void flowableDemo() {
        // 登录的demo
        Flowable.fromCallable(new Callable<RegisterRespone>() {
            @Override
            public RegisterRespone call() throws Exception {
                // 注册
                return new RegisterRespone();
            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).doOnNext(new Consumer<RegisterRespone>() {
            @Override
            public void accept(RegisterRespone registerRespone) throws Exception {
                // Toast("Login success!")
            }
        }).observeOn(Schedulers.io()).flatMap(new Function<RegisterRespone, Publisher<LoginRespone>>() {
            @Override
            public Publisher<LoginRespone> apply(RegisterRespone registerRespone) throws Exception {
                return Flowable.just(new LoginRespone());
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new DisposableSubscriber<LoginRespone>() {
            @Override
            public void onNext(LoginRespone loginRespone) {
            }

            @Override
            public void onError(Throwable t) {
            }

            @Override
            public void onComplete() {
            }
        });
    }

    public static class RegisterRespone {

        public RegisterRespone() {
            Log.i("wangrenguang3", "register.....");
        }

    }

    public static class LoginRespone {
        public LoginRespone() {
            Log.i("wangrenguang3", "Login.....");
        }
    }

    public static class CommonSubscriber<T> extends DisposableSubscriber<T> {

        @Override
        public void onNext(T t) {

        }

        @Override
        public void onError(Throwable t) {

        }

        @Override
        public void onComplete() {

        }
    }

    public void demo10() {
        // Hot observable -->publish
//        Disposable disposable =  Flowable.fromCallable(new Callable<String>() {
//
//            @Override
//            public String call() throws Exception {
//                throw new NullPointerException();
//            }
//
//
//        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).compose(new FlowableTransformer<String,String>(){
//
//            @Override
//            public Publisher apply(Flowable upstream) {
//                return upstream.doOnError(new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        Log.i("wangrenguang0", "accept" + throwable.getMessage());
//                    }
//                });
//            }
//        }).subscribe(new Consumer<String>() {
//            @Override
//            public void accept(String s) throws Exception {
//                Log.i("wangrenguang0", "accept" + s);
//            }
//        });
//
//
//        Flowable.fromCallable(new Callable<String>() {
//            @Override
//            public String call() throws Exception {
//                return null;
//            }
//        });

//        flowable.connect(new Consumer<Disposable>() {
//            @Override
//            public void accept(Disposable disposable) throws Exception {
//                compositeDisposable.add(disposable);
//            }
//        });
//        RxJavaPlugins.setErrorHandler(new Consumer<Throwable>() {
//            @Override
//            public void accept(Throwable throwable) throws Exception {
//                Log.i("wangrenguang0", "异常..." + throwable.getMessage());
//            }
//        });
//        flowable.doOnError(new Consumer<Throwable>() {
        //            @Override
        //            public void accept(Throwable throwable) throws Exception {
        //                Log.i("wangrenguang0", "异常" + throwable.getMessage());
        //            }
        //        });
//        Disposable disposable = flowable.subscribe(new Consumer<String>() {
//            @Override
//            public void accept(String s) throws Exception {
//                Log.i("wangrenguang0", "accept" + s);
//            }
//        }, new Consumer<Throwable>() {
//            @Override
//            public void accept(Throwable throwable) throws Exception {
//                Log.i("wangrenguang0", "throwable " + throwable.getMessage());
//            }
//        });

//        compositeDisposable.add(disposable);


//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                compositeDisposable.clear();
//            }
//        }, 2000);

    }

    private void d() {
        flowable.subscribe(new FlowableSubscriber<String>() {
            @Override
            public void onSubscribe(Subscription s) {
                s.request(100);
                Log.i("wangrenguangx", "onSubscribe:");
            }

            @Override
            public void onNext(String s) {
                Log.i("wangrenguangx", "onNext:" + s);
                if (s.equals("11")) {
                }
            }

            @Override
            public void onError(Throwable t) {
                Log.i("wangrenguangx", "onError:" + t.getMessage());
            }

            @Override
            public void onComplete() {
                Log.i("wangrenguangx", "onComplete:");
            }
        });
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();

    }

}
