package com.linechen.eventbusdemo.event;

import com.linechen.eventbusdemo.model.Inviter;

/**
 * Created by LineChen on 2017/8/31.
 */

public class InviteEvent {
    private Inviter inviter;

    public InviteEvent(Inviter inviter) {
        this.inviter = inviter;
    }

    public Inviter getInviter() {
        return inviter;
    }

    public void setInviter(Inviter inviter) {
        this.inviter = inviter;
    }
}
