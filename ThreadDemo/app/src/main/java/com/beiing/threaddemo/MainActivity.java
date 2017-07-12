package com.beiing.threaddemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.beiing.threaddemo.threadpool.ThreadPoolActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void testAsyncTask(View view) {
        startActivity(new Intent(this, AsyncTaskActivity.class));
    }

    public void testThreadPool(View view) {
        startActivity(new Intent(this, ThreadPoolActivity.class));
    }
}
