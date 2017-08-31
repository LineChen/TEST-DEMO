package com.linechen.eventbusdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.linechen.eventbusdemo.event.InviteEvent;
import com.linechen.eventbusdemo.event.PlusEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {
    TextView tvPlusEvent;
    TextView tvInviteEvent;
    int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvPlusEvent = (TextView) findViewById(R.id.tv_plus_evet);
        tvInviteEvent = (TextView) findViewById(R.id.tv_invite_evet);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPlusEvent(PlusEvent plusEvent){
        count++;
        tvPlusEvent.setText(String.valueOf(count));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onInviteEvent(InviteEvent inviteEvent){
        tvInviteEvent.append(inviteEvent.getInviter() + "\n");
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this);
    }

    public void TextActivity(View view) {
        startActivity(new Intent(this, TestActivity.class));
    }
}
