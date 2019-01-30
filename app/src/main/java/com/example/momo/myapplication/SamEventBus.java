package com.example.momo.myapplication;

import android.os.Debug;
import android.support.annotation.NonNull;

import org.greenrobot.eventbus.EventBus;

/**
 * @author wang.renguang
 * @time 2019/1/10
 */
public class SamEventBus {

    @NonNull
    private EventBus eventBus;

    public static SamEventBus getInstance() {
        return SamEventBus.EventBusHolder.instance;
    }

    private static final class EventBusHolder {
        private static final SamEventBus instance = new SamEventBus();
    }


    private SamEventBus() {
        eventBus = EventBus.builder()
                .executorService(ThreadConfig.getInstance().get())
                .build();
    }

    public EventBus get() {
        return eventBus;
    }

}
