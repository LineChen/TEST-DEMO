package com.linechen.eventbusdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.linechen.eventbusdemo.event.InviteEvent;
import com.linechen.eventbusdemo.event.PlusEvent;
import com.linechen.eventbusdemo.model.Inviter;

import org.greenrobot.eventbus.EventBus;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }

    public void postEvent1(View view) {
        EventBus.getDefault().post(new PlusEvent());
    }

    public void postEvent2(View view) {
        Inviter inviter = new Inviter();
        inviter.setName(String.valueOf(Math.random() * 100));
        inviter.setMsg(String.valueOf("now is " + System.currentTimeMillis()));
        EventBus.getDefault().post(new InviteEvent(inviter));
    }
}
