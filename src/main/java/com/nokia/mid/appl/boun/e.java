package com.nokia.mid.appl.boun;

import com.nokia.mid.sound.Sound;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Objects;

public class e extends b {
    public int SplashId;

    public Image ImageSplash;

    private int splashTimer;

    public Sound soundUp;

    public Sound soundPickup;

    public Sound soundPop;

    public BounceGame game;

    public f aq;

    public int HoopsScored;

    public int lives;

    public int score;

    public int boostTimer;

    public int p;

    public boolean e;

    public boolean TODO_ExitUnlocked;

    public boolean y;

    public final Font font = Font.getFont(32, 0, 8);

    public Image imageFullscreen;

    public Graphics X = null;

    public boolean T;

    private boolean af = false;

    public boolean GodMode = false;

    private int g = 0;

    private static final String[] SPLASHES = new String[]{"/icons/nokiagames.png", "/icons/bouncesplash.png"};

    public boolean H = true;

    private long K = System.currentTimeMillis();

    public e(BounceGame game, int paramInt) {
        super(game.display);
        this.game = game;
        this.soundUp = LoadSound("/sounds/up.ott");
        this.soundPickup = LoadSound("/sounds/pickup.ott");
        this.soundPop = LoadSound("/sounds/pop.ott");
        this.imageFullscreen = Image.createImage(128, 128);
        this.SplashId = 1;
        try {
            this.ImageSplash = Image.createImage(SPLASHES[this.SplashId]);
        } catch (IOException iOException) {
            this.ImageSplash = Image.createImage(1, 1);
        }
        StartGameTimer();
    }

    public void a(int level, int paramInt2, int paramInt3) {
        this.level = level;
        this.HoopsScored = 0;
        this.lives = paramInt3;
        this.score = paramInt2;
        this.e = false;
        this.TODO_ExitUnlocked = false;
        InitializeGame();
        this.T = true;
    }

    public void a(int paramInt1, int paramInt2) {
        this.level = this.game.RecordLevel;
        this.HoopsScored = this.game.RecordHoopsScored;
        this.lives = this.game.RecordLives;
        this.score = this.game.RecordScore;
        RunGarbageCollector();
        LoadLevelId(this.level);
        k();
        AddScore();
        this.p = 120;
        this.y = true;
        if (this.game.e != this.s && this.game.b != this.S)
            this.LevelTiles[this.game.b][this.game.e] = (short) (0x8 | this.LevelTiles[this.game.b][this.game.e] & 0x40);
        a(paramInt1, paramInt2, this.game.A, this.game.a, this.game.g);
        synchronized (this.aq) {
            this.aq.a(this.game.e, this.game.b);
            this.aq.h = this.game.w;
            this.aq.g = this.game.z;
            this.aq.y = this.game.n;
            this.T = true;
        }
    }

    private void InitializeGame() {
        RunGarbageCollector();
        LoadLevelId(this.level);
        this.HoopsScored = 0;
        this.p = 120;
        this.y = true;
        a(this.s * 12 + 6, this.S * 12 + 6, this.BallSize, 0, 0);
        this.aq.a(this.s, this.S);
        this.T = true;
    }

    public void a(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
        this.aq = new f(paramInt1, paramInt2, paramInt3, this);
        this.aq.l = paramInt4;
        this.aq.o = paramInt5;
        this.l = 0;
        this.k = 0;
        e();
    }

    public void e() {
        int i = this.aq.s - 64;
        if (i < 0) {
            i = 0;
        } else if (i > this.TODO_width * 12 - 156) {
            i = this.TODO_width * 12 - 156;
        }
        this.l = i / 12;
        this.v = this.l * 12 - i;
        this.Z = 156;
        this.G = this.l + 13;
        while (this.aq.r - 6 < this.k * 12)
            this.k -= 7;
        while (this.aq.r + 6 > this.k * 12 + 96)
            this.k += 7;
        f();
    }

    public void AddScore(int paramInt) {
        this.score += paramInt;
        this.y = true;
    }

    public void q() {
        if (this.X == null)
            this.X = this.imageFullscreen.getGraphics();
        this.X.setClip(0, 0, 128, 96);
        if (this.E != null) {
            i();
            if (this.v <= 0) {
                this.X.drawImage(this.E, this.v, 0, 20);
            } else {
                this.X.drawImage(this.E, this.v, 0, 20);
                this.X.drawImage(this.E, this.v - 156, 0, 20);
            }
        }
        a(this.X, this.v);
        CreateTiles(this.X, this.aq.s, this.aq.r, this.aq.p, this.v);
        this.X.setClip(0, 0, 128, 128);
        if (this.y) {
            this.X.setColor(0x0853aa);
            this.X.fillRect(0, 97, 128, 32);
            for (byte b1 = 0; b1 < this.lives; b1++)
                this.X.drawImage(this.imageBall, 5 + b1 * (this.imageBall.getWidth() - 1), 99, Graphics.TOP | Graphics.LEFT);
            for (byte b2 = 0; b2 < this.HoopsTotal - this.HoopsScored; b2++)
                this.X.drawImage(this.imageHoop, 5 + b2 * (this.imageHoop.getWidth() - 4), 112, Graphics.TOP | Graphics.LEFT);
            this.X.setColor(0xfffffe);
            this.X.setFont(this.font);
            this.X.drawString(PadZeroes(this.score), 64, 100, Graphics.TOP | Graphics.LEFT);
            if (this.boostTimer != 0) {
                this.X.setColor(0xff9813);
                this.X.fillRect(1, 128 - 3 * this.boostTimer / 30, 5, 128);
            }
            this.y = false;
        }
    }

    public void paint(Graphics paramGraphics) {
        if (this.SplashId != -1) {
            if (this.ImageSplash != null) {
                paramGraphics.setColor(0x000000);
                paramGraphics.fillRect(0, 0, this.displayWidth, this.displayHeight);
                paramGraphics.drawImage(this.ImageSplash, this.displayWidth >> 1, this.displayHeight >> 1, Graphics.HCENTER | Graphics.VCENTER);
            }
        } else {
            paramGraphics.drawImage(this.imageFullscreen, 0, 0, 20);
            if (this.p != 0) {
                paramGraphics.setColor(0xfffffe);
                paramGraphics.setFont(this.font);
                paramGraphics.drawString(this.LevelString, 44, 84, 20);
            }
        }
    }

    public void a(Graphics paramGraphics, int paramInt) {
        if (this.aq == null)
            return;
        int i = this.aq.s - this.l * 12;
        int j = this.aq.r - this.k * 12;
        if (this.aq.z == 2) {
            paramGraphics.drawImage(this.aq.spritePoppedBall, i - 6 + paramInt, j - 6, 20);
        } else {
            paramGraphics.drawImage(this.aq.i, i - this.aq.p + paramInt, j - this.aq.p, 20);
        }
    }

    public void Tick() {
        if (this.d) {
            InitializeGame();
            repaint();
            return;
        }
        if (this.SplashId != -1) {
            if (this.ImageSplash == null/* || this.t == null*/) { // TODO: this is what I got from JD-GUI. I wonder why...
                this.H = false;
                this.game.ShowMainMenu();
            } else if (this.splashTimer > 30) {
                this.ImageSplash = null;
                Runtime.getRuntime().gc();
                switch (this.SplashId) {
                    case 0: // Switch to the next splash
                        this.SplashId = 1;
                        try {
                            this.ImageSplash = Image.createImage(SPLASHES[this.SplashId]);
                        } catch (IOException iOException) {
                            this.ImageSplash = Image.createImage(1, 1);
                        }
                        repaint();
                        break;
                    case 1: // Open the main menu
                        this.SplashId = -1;
                        this.H = false;
                        this.game.ShowMainMenu();
                        break;
                }
                this.splashTimer = 0;
            } else {
                this.splashTimer++;
            }
            repaint();
            return;
        }

        if (this.p != 0)
            this.p--;

        synchronized (this.aq) {
            if (this.aq.r - 6 < this.k * 12 || this.aq.r + 6 > this.k * 12 + 96) {
                e();
            } else {
                this.aq.b();
            }
            if (this.aq.z == 1) {
                if (this.lives < 0) {
                    this.game.WriteToStore();
                    StopGameTimer();
                    this.game.TODO_ShowInstructions(false);
                    return;
                }
                int i = this.aq.d;
                int j = this.aq.c;
                int k = this.aq.b;
                a(this.aq.d * 12 + 6, this.aq.c * 12 + 6, this.aq.b, 0, 0);
                this.aq.d = i;
                this.aq.c = j;
                this.aq.b = k;
            }
            if (this.SpikeStarsCount != 0)
                o();
            if (this.HoopsScored == this.HoopsTotal)
                this.TODO_ExitUnlocked = true;
            if (this.TODO_ExitUnlocked && this.z && (this.W + 1) * 12 > m() && this.W * 12 < g()) {
                if (this.M) {
                    this.z = false;
                    this.TODO_ExitUnlocked = false;
                } else {
                    h();
                }
                this.LevelTiles[this.u][this.al] = (short) (this.LevelTiles[this.u][this.al] | 0x80);
                this.LevelTiles[this.u][this.al + 1] = (short) (this.LevelTiles[this.u][this.al + 1] | 0x80);
                this.LevelTiles[this.u + 1][this.al] = (short) (this.LevelTiles[this.u + 1][this.al] | 0x80);
                this.LevelTiles[this.u + 1][this.al + 1] = (short) (this.LevelTiles[this.u + 1][this.al + 1] | 0x80);
            }
            this.boostTimer = 0;
            if (this.aq.h != 0 || this.aq.g != 0 || this.aq.y != 0) {
                if (this.aq.h > this.boostTimer)
                    this.boostTimer = this.aq.h;
                if (this.aq.g > this.boostTimer)
                    this.boostTimer = this.aq.g;
                if (this.aq.y > this.boostTimer)
                    this.boostTimer = this.aq.y;
                if (this.boostTimer % 30 == 0 || this.boostTimer == 1)
                    this.y = true;
            }
        }
        LoadSpriteSheet(this.aq.s);
        q();
        repaint();
        if (this.e) {
            this.e = false;
            this.TODO_ExitUnlocked = false;
            this.d = true;
            this.level++;
            AddScore(5000);
            this.game.WriteToStore();
            if (this.level > 11) {
                this.game.TODO_ShowInstructions(true);
            } else {
                this.H = false;
                this.game.ShowLevelComplete();
                repaint();
            }
        }
    }

    public void keyPressed(int paramInt) {
        if (this.SplashId != -1) { // Skip the splash screen
            this.splashTimer = 31;
            return;
        }
        if (this.aq == null)
            return;
        synchronized (this.aq) {
            switch (paramInt) {
                case 49:
                    this.d = true;
                    if (this.af && --this.level < 1)
                        this.level = 11;
                    break;
                case 51:
                    this.d = true;
                    if (this.af && ++this.level > 11)
                        this.level = 1;
                    break;
                case 55:
                    if (this.g == 0 || this.g == 2) {
                        this.g++;
                        break;
                    }
                    this.g = 0;
                    break;
                case 56:
                    if (this.g == 1 || this.g == 3) {
                        this.g++;
                        break;
                    }
                    if (this.g == 5) {
                        this.soundUp.play(1);
                        this.GodMode = true;
                        this.g = 0;
                        break;
                    }
                    this.g = 0;
                    break;
                case 57:
                    if (this.g == 4) {
                        this.g++;
                        break;
                    }
                    if (this.g == 5) {
                        this.soundPop.play(1);
                        this.af = true;
                        this.g = 0;
                        break;
                    }
                    this.g = 0;
                    break;
                case 35:
                    if (this.af)
                        this.aq.g = 300;
                    break;
                case -7:
                case -6:
                    this.H = false;
                    this.game.ShowMainMenu();
                    break;
                default:
                    switch (getGameAction(paramInt)) {
                        case 1:
                            this.aq.c(8);
                            break;
                        case 6:
                            this.aq.c(4);
                            break;
                        case 2:
                            this.aq.c(1);
                            break;
                        case 5:
                            this.aq.c(2);
                            break;
                        case 8:
                            if (this.af)
                                this.e = true;
                            break;
                    }
                    break;
            }
        }
    }

    public void keyReleased(int paramInt) {
        if (this.aq == null)
            return;
        synchronized (this.aq) {
            switch (getGameAction(paramInt)) {
                case 1:
                    this.aq.a(8);
                    break;
                case 6:
                    this.aq.a(4);
                    break;
                case 2:
                    this.aq.a(1);
                    break;
                case 5:
                    this.aq.a(2);
                    break;
            }
        }
    }

    public static String PadZeroes(int number) {
        String str;
        if (number < 100) {
            str = "0000000";
        } else if (number < 1000) {
            str = "00000";
        } else if (number < 10000) {
            str = "0000";
        } else if (number < 100000) {
            str = "000";
        } else if (number < 1000000) {
            str = "00";
        } else if (number < 10000000) {
            str = "0";
        } else {
            str = "";
        }
        return str + number;
    }

    protected Sound LoadSound(String path) {
        byte[] buffer = new byte[100];
        Sound sound = null;
        DataInputStream dataInputStream = new DataInputStream(Objects.requireNonNull(getClass().getResourceAsStream(path)));
        try {
            int i = dataInputStream.read(buffer);
            dataInputStream.close();
            byte[] bufferExactSize = new byte[i];
            System.arraycopy(buffer, 0, bufferExactSize, 0, i);
            sound = new Sound(bufferExactSize, Sound.FORMAT_TONE);
        } catch (IOException iOException) {
            sound = new Sound(1000, 500L); // Fallback, play a 1000Hz tone 3 times
            sound.play(3);
        }
        return sound;
    }

    public void hideNotify() {
        if (this.H) {
            if (this.aq != null)
                this.aq.a();
            this.game.ShowMainMenu();
        }
        this.H = true;
    }

    public void AddScore() {
        for (byte b1 = 0; b1 < this.game.r; b1++) {
            this.ae[b1][0] = this.game.l[b1][0];
            this.ae[b1][1] = this.game.l[b1][1];
            this.w[b1][0] = this.game.D[b1][0];
            this.w[b1][1] = this.game.D[b1][1];
        }
        this.game.D = null;
        this.game.l = null;
        this.game.r = 0;
    }

    public void k() {
        for (byte b1 = 0; b1 < this.TODO_height; b1++) {
            for (byte b2 = 0; b2 < this.TODO_width; b2++) {
                byte b3 = (byte) (this.LevelTiles[b1][b2] & 0xFF7F & 0xFFFFFFBF);
                switch (b3) {
                    case 7, 29 -> {
                        if (a(b1, b2, b3))
                            this.LevelTiles[b1][b2] = (short) (0x0 | this.LevelTiles[b1][b2] & 0x40);
                    }
                    case 13 -> {
                        if (a(b1, b2, b3))
                            this.LevelTiles[b1][b2] = (short) (0x11 | this.LevelTiles[b1][b2] & 0x40);
                    }
                    case 14 -> {
                        if (a(b1, b2, b3))
                            this.LevelTiles[b1][b2] = (short) (0x12 | this.LevelTiles[b1][b2] & 0x40);
                    }
                    case 21 -> {
                        if (a(b1, b2, b3))
                            this.LevelTiles[b1][b2] = (short) (0x19 | this.LevelTiles[b1][b2] & 0x40);
                    }
                    case 22 -> {
                        if (a(b1, b2, b3))
                            this.LevelTiles[b1][b2] = (short) (0x1A | this.LevelTiles[b1][b2] & 0x40);
                    }
                    case 15 -> {
                        if (a(b1, b2, b3))
                            this.LevelTiles[b1][b2] = (short) (0x13 | this.LevelTiles[b1][b2] & 0x40);
                    }
                    case 16 -> {
                        if (a(b1, b2, b3))
                            this.LevelTiles[b1][b2] = (short) (0x14 | this.LevelTiles[b1][b2] & 0x40);
                    }
                    case 23 -> {
                        if (a(b1, b2, b3))
                            this.LevelTiles[b1][b2] = (short) (0x1B | this.LevelTiles[b1][b2] & 0x40);
                    }
                    case 24 -> {
                        if (a(b1, b2, b3))
                            this.LevelTiles[b1][b2] = (short) (0x1C | this.LevelTiles[b1][b2] & 0x40);
                    }
                }
            }
        }
        this.game.u = null;
        this.game.p = 0;
    }

    public boolean a(int paramInt1, int paramInt2, byte paramByte) {
        for (byte b1 = 0; b1 < this.game.p; b1++) {
            if (this.game.u[b1][0] == paramInt1 && this.game.u[b1][1] == paramInt2)
                return false;
        }
        return true;
    }
}


/* Location:              C:\Users\kuzme\Downloads\nokiabounc_jdifc8jb.jar!\com\nokia\mid\appl\boun\e.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */