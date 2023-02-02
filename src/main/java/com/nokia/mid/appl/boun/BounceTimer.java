package com.nokia.mid.appl.boun;

import java.util.Timer;
import java.util.TimerTask;

public class BounceTimer extends TimerTask {
    b a;

    Timer timer;

    private final b b;

    public BounceTimer(b paramb1, b paramb2) {
        this.b = paramb1;
        this.a = paramb2;
        this.timer = new Timer();
        this.timer.schedule(this, 0L, 40L); // ~25 Ticks per second
    }

    public void run() {
        this.a.GameTimerTick();
    }

    void stop() {
        if (this.timer == null)
            return;
        cancel();
        this.timer.cancel();
        this.timer = null;
    }
}


/* Location:              C:\Users\kuzme\Downloads\nokiabounc_jdifc8jb.jar!\com\nokia\mid\appl\boun\g.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */