package com.beiing.threaddemo.threadpool;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.beiing.threaddemo.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class ThreadPoolActivity extends AppCompatActivity {

    TextView tvLog;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String log = msg.obj.toString();
            tvLog.append(log);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_pool);
        tvLog = (TextView) findViewById(R.id.tv_log);
    }


    public void newFixedThreadPool(View view) {
        tvLog.setText("");
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(4);
        for (int i = 1; i <= 10; i++) {
            final int index = i;
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    String threadName = Thread.currentThread().getName();
                    String log = "线程："+threadName+",正在执行第" + index + "个任务\n";
                    Message msg = handler.obtainMessage();
                    msg.obj = log;
                    handler.sendMessage(msg);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }


    public void newSingleThreadExecutor(View view) {
        tvLog.setText("");
        ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
        for (int i = 1; i <= 10; i++) {
            final int index = i;
            singleThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    String threadName = Thread.currentThread().getName();
                    String log = "线程："+threadName+",正在执行第" + index + "个任务\n";
                    Message msg = handler.obtainMessage();
                    msg.obj = log;
                    handler.sendMessage(msg);
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }


    public void newCachedThreadPool(View view) {
        tvLog.setText("");
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 1; i <= 10; i++) {
            final int index = i;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    String threadName = Thread.currentThread().getName();
                    String log = "线程："+threadName+",正在执行第" + index + "个任务\n";
                    Message msg = handler.obtainMessage();
                    msg.obj = log;
                    handler.sendMessage(msg);
                    try {
                        long time = index * 500;
                        Thread.sleep(time);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }


    public void newScheduledThreadPool(View view) {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3);
        //延迟2秒后执行该任务
        scheduledThreadPool.schedule(new Runnable() {
            @Override
            public void run() {
                String threadName = Thread.currentThread().getName();
                String log = "线程："+threadName+",正在执行"  + "任务\n";
                Message msg = handler.obtainMessage();
                msg.obj = log;
                handler.sendMessage(msg);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 2, TimeUnit.SECONDS);

        //延迟1秒后，每隔1秒执行一次该任务
        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                String threadName = Thread.currentThread().getName();
                String log = "线程："+threadName+",正在执行"  + "任务\n";
                Message msg = handler.obtainMessage();
                msg.obj = log;
                handler.sendMessage(msg);
                try {
                    Thread.sleep(1200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 1, 1, TimeUnit.SECONDS);
    }


    public void priorityThreadPool(View view) {
        tvLog.setText("");
        ExecutorService priorityThreadPool = new ThreadPoolExecutor(3,3,0L,TimeUnit.SECONDS,new PriorityBlockingQueue<Runnable>());
        for (int i = 1; i <= 10; i++) {
            final int priority = i;
            priorityThreadPool.execute(new PriorityRunnable(priority) {
                @Override
                public void doSth() {
                    String threadName = Thread.currentThread().getName();
                    String log = "线程：" + threadName + ",正在执行优先级为：" + priority + "的任务\n";
                    Message msg = handler.obtainMessage();
                    msg.obj = log;
                    handler.sendMessage(msg);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public void MyThreadPoolExecutor(View view) {
        tvLog.setText("");
        ExecutorService myExecutor = new MyThreadPoolExecutor(2, 2, 0, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());
        for (int i = 1; i <= 10; i++) {
            final int index = i;
            myExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    String threadName = Thread.currentThread().getName();
                    String log = "线程："+threadName+",正在执行第" + index + "个任务\n";
                    Message msg = handler.obtainMessage();
                    msg.obj = log;
                    handler.sendMessage(msg);
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }



}


