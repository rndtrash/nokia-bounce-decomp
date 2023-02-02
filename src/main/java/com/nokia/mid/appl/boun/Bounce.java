package com.nokia.mid.appl.boun;

import com.nokia.mid.ui.DeviceControl;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;

public class Bounce extends MIDlet {
    private BounceGame BounceGame;

    public Bounce() {
        if (this.BounceGame == null)
            this.BounceGame = new BounceGame(this);
    }

    protected void startApp() {
        DeviceControl.setLights(0, 100);
    }

    protected void pauseApp() {
    }

    public void destroyApp(boolean paramBoolean) {
        if (this.BounceGame != null && this.BounceGame.v != null) {
            this.BounceGame.WriteToStore(3);
            this.BounceGame.v.StopGameTimer();
        }
        Display.getDisplay(this).setCurrent(null);
        this.BounceGame = null;
    }
}


/* Location:              C:\Users\kuzme\Downloads\nokiabounc_jdifc8jb.jar!\com\nokia\mid\appl\boun\Bounce.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */