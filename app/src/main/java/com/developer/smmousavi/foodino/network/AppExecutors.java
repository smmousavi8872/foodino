package com.developer.smmousavi.foodino.network;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import androidx.annotation.NonNull;

public class AppExecutors {

    private static AppExecutors sInstance;

    public static AppExecutors getInstance() {
        if (sInstance == null)
            sInstance = new AppExecutors();
        return sInstance;
    }

    private AppExecutors() {
    }


    // do the db operations on the cache
    // insert/ update/ delete on cache
    private final Executor mDiskIO = Executors.newSingleThreadExecutor();

    // send information to main thread
    private final Executor mMainThreadExecutor = new MainThreadExecutor();

    public Executor diskIO() {
        return mDiskIO;
    }

    public Executor mainThread() {
        return mMainThreadExecutor;
    }


    // this call is going to post things to main thread
    private static class MainThreadExecutor implements Executor {

        private Handler mainThreadHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(@NonNull Runnable command) {
            mainThreadHandler.post(command);
            }
    }

    private final ScheduledExecutorService mNetworkIO = Executors.newScheduledThreadPool(3);

    public ScheduledExecutorService getNetworkIO(){
        return mNetworkIO;
    }

}
