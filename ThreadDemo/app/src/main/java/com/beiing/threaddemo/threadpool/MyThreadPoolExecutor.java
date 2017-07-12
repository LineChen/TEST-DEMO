package com.beiing.threaddemo.threadpool;

import android.util.Log;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by linechen on 2017/7/11.<br/>
 * 描述：
 * </br>
 */

public class MyThreadPoolExecutor extends ThreadPoolExecutor {

    private static final String TAG = "MyThreadPoolExecutor";

    public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }



    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        Log.e(TAG, "beforeExecute" + t.getName());
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);
        String threadName = Thread.currentThread().getName();
        Log.e(TAG, "afterExecute" + threadName);
    }

    @Override
    protected void terminated() {
        super.terminated();
        Log.e(TAG, "ThreadPool terminated.");
    }

}
