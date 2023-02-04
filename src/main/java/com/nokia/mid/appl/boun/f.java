package com.nokia.mid.appl.boun;

import com.nokia.mid.sound.Sound;

import javax.microedition.lcdui.Image;

public class f {
    private boolean j = true;

    public int s;

    public int r;

    public int l;

    public int o;

    public int w;

    public int a;

    public int p;

    public int d;

    public int c;

    public int b;

    public int z;

    public int t;

    public int h;

    public int g;

    public int y;

    public boolean m;

    public boolean v;

    public boolean u;

    public int C;

    public static final byte[][] SLOPE_COLLISION = new byte[][] {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1},
            {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1},
            {0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };

    public static final byte[][] BALL_COLLISION = new byte[][] {
            {0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0},
            {0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
            {0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
            {0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0}
    };

    public static final byte[][] BIG_BALL_COLLISION = new byte[][] {
            {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
            {0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
            {0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
            {0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0}
    };

    public e n;

    public Image i;

    public Image spritePoppedBall;

    public Image spriteBigBall;

    public Image spriteBall;

    private int q;

    public f(int paramInt1, int paramInt2, int paramInt3, e parame) {
        this.s = paramInt1;
        this.r = paramInt2;
        this.l = 0;
        this.o = 0;
        this.n = parame;
        this.t = 0;
        this.m = false;
        this.v = false;
        this.u = false;
        this.q = 0;
        this.h = 0;
        this.g = 0;
        this.y = 0;
        this.C = 0;
        this.z = 0;
        this.w = 0;
        this.n.CreateTiles(this);
        if (paramInt3 == 12) {
            c();
        } else {
            f();
        }
    }

    public void a(int paramInt1, int paramInt2) {
        this.d = paramInt1;
        this.c = paramInt2;
        this.b = this.a;
    }

    public void c(int paramInt) {
        if (paramInt == 8 || paramInt == 4 || paramInt == 2 || paramInt == 1)
            this.w |= paramInt;
    }

    public void a(int paramInt) {
        if (paramInt == 8 || paramInt == 4 || paramInt == 2 || paramInt == 1)
            this.w &= ~paramInt;
    }

    public void a() {
        this.w &= 0xFFFFFFF0;
    }

    public boolean b(int paramInt1, int paramInt2) {
        int i = (paramInt1 - this.p) / 12;
        int j = (paramInt2 - this.p) / 12;
        int k = (paramInt1 - 1 + this.p) / 12 + 1;
        int m = (paramInt2 - 1 + this.p) / 12 + 1;
        for (int n = i; n < k; n++) {
            for (int i1 = j; i1 < m; i1++) {
                if (!a(paramInt1, paramInt2, i1, n))
                    return false;
            }
        }
        return true;
    }

    public void f() {
        this.a = 16;
        this.p = 8;
        this.i = this.spriteBigBall;
        boolean bool = false;
        for (byte b = 1; !bool; b++) {
            bool = true;
            if (b(this.s, this.r - b)) {
                this.r -= b;
                continue;
            }
            if (b(this.s - b, this.r - b)) {
                this.s -= b;
                this.r -= b;
                continue;
            }
            if (b(this.s + b, this.r - b)) {
                this.s += b;
                this.r -= b;
                continue;
            }
            if (b(this.s, this.r + b)) {
                this.r += b;
                continue;
            }
            if (b(this.s - b, this.r + b)) {
                this.s -= b;
                this.r += b;
                continue;
            }
            if (b(this.s + b, this.r + b)) {
                this.s += b;
                this.r += b;
                continue;
            }
            bool = false;
        }
    }

    public void c() {
        this.a = 12;
        this.p = 6;
        this.i = this.spriteBall;
    }

    public void KillBall() {
        if (!this.n.GodMode) {
            this.q = 7;
            this.z = 2;
            this.n.lives--;
            this.h = 0;
            this.g = 0;
            this.y = 0;
            this.n.y = true;
            this.n.soundPop.play(1);
        }
    }

    public void OnTouchHoop() {
        this.n.AddScore(500);
        this.n.HoopsScored++;
        this.n.y = true;
    }

    public void b(int paramInt) {
        int i = this.l;
        switch (paramInt) {
            case 35 -> {
                this.l = (this.l > -this.o) ? this.l : this.o;
                this.o = i;
            }
            case 37 -> {
                this.l = (-this.l > this.o) ? this.l : this.o;
                this.o = i;
            }
            case 34 -> {
                this.l = (this.l < this.o) ? this.l : -this.o;
                this.o = -i;
            }
            case 36 -> {
                this.l = (this.l > this.o) ? this.l : -this.o;
                this.o = -i;
            }
            case 31 -> {
                this.l = (this.l > -this.o) ? this.l : (this.o >> 1);
                this.o = i;
            }
            case 33 -> {
                this.l = (-this.l > this.o) ? this.l : (this.o >> 1);
                this.o = i;
            }
            case 30 -> {
                this.l = (this.l < this.o) ? this.l : -(this.o >> 1);
                this.o = -i;
            }
            case 32 -> {
                this.l = (this.l > this.o) ? this.l : -(this.o >> 1);
                this.o = -i;
            }
        }
    }

    public boolean b(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        byte b1;
        int n;
        byte b2;
        int i1;
        byte[][] ballCollision;
        int i = paramInt4 * 12;
        int j = paramInt3 * 12;
        int k = paramInt1 - this.p - i;
        int m = paramInt2 - this.p - j;
        if (k >= 0) {
            b1 = (byte) k;
            n = 12;
        } else {
            b1 = 0;
            n = this.a + k;
        }
        if (m >= 0) {
            b2 = (byte) m;
            i1 = 12;
        } else {
            b2 = 0;
            i1 = this.a + m;
        }
        if (this.a == 16) {
            ballCollision = BIG_BALL_COLLISION;
        } else {
            ballCollision = BALL_COLLISION;
        }
        if (n > 12)
            n = 12;
        if (i1 > 12)
            i1 = 12;
        for (byte b3 = b1; b3 < n; b3++) {
            for (byte b = b2; b < i1; b++) {
                if (ballCollision[b - m][b3 - k] != 0)
                    return true;
            }
        }
        return false;
    }

    public boolean c(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
        byte b3;
        int n;
        byte b4;
        int i1;
        byte[][] ballCollision;
        int i = paramInt4 * 12;
        int j = paramInt3 * 12;
        int k = paramInt1 - this.p - i;
        int m = paramInt2 - this.p - j;
        byte b1 = 0;
        byte b2 = 0;
        switch (paramInt5) {
            case 30, 34 -> {
                b2 = 11;
                b1 = 11;
            }
            case 31, 35 -> b2 = 11;
            case 33, 37 -> b1 = 11;
        }
        if (k >= 0) {
            b3 = (byte) k;
            n = 12;
        } else {
            b3 = 0;
            n = this.a + k;
        }
        if (m >= 0) {
            b4 = (byte) m;
            i1 = 12;
        } else {
            b4 = 0;
            i1 = this.a + m;
        }
        if (this.a == 16) {
            ballCollision = BIG_BALL_COLLISION;
        } else {
            ballCollision = BALL_COLLISION;
        }
        if (n > 12)
            n = 12;
        if (i1 > 12)
            i1 = 12;
        for (byte b5 = b3; b5 < n; b5++) {
            for (byte b = b4; b < i1; b++) {
                if ((SLOPE_COLLISION[Math.abs(b - b2)][Math.abs(b5 - b1)] & ballCollision[b - m][b5 - k]) != 0) {
                    if (!this.m)
                        b(paramInt5);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean b(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
        int i = paramInt4 * 12;
        int j = paramInt3 * 12;
        int k = i + 12;
        int m = j + 12;
        switch (paramInt5) {
            case 3, 5, 9, 13, 14, 17, 18, 21, 22, 43, 45 -> {
                i += 4;
                k -= 4;
            }
            case 4, 6, 15, 16, 19, 20, 23, 24, 44, 46 -> {
                j += 4;
                m -= 4;
            }
        }
        return a(paramInt1 - this.p, paramInt2 - this.p, paramInt1 + this.p - 1, paramInt2 + this.p - 1, i, j, k - 1, m - 1);
    }

    public boolean a(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
        int i = paramInt4 * 12;
        int j = paramInt3 * 12;
        int k = i + 12;
        int m = j + 12;
        switch (paramInt5) {
            case 15, 19, 23, 27 -> {
                j += 6;
                m -= 6;
                k -= 11;
            }
            case 16, 20, 24, 28 -> {
                j += 6;
                m -= 6;
                i += 11;
            }
            case 13, 17 -> {
                i += 6;
                k -= 6;
                m -= 11;
            }
            case 21, 25 -> {
                m = j;
                j--;
                i += 6;
                k -= 6;
            }
            case 14, 18, 22, 26 -> {
                i += 6;
                k -= 6;
                j += 11;
            }
        }
        return a(paramInt1 - this.p, paramInt2 - this.p, paramInt1 + this.p, paramInt2 + this.p, i, j, k, m);
    }

    public boolean a(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        int k;
        if (paramInt3 >= this.n.TODO_height || paramInt3 < 0 || paramInt4 >= this.n.TODO_width || paramInt4 < 0)
            return false;
        if (this.z == 2)
            return false;
        boolean bool = true;
        int isInWater = this.n.LevelTiles[paramInt3][paramInt4] & 0x40;
        int j = this.n.LevelTiles[paramInt3][paramInt4] & (~0x40) & (~0x80);
        Sound sound = null;
        switch (j) {
            case 1 -> {
                if (b(paramInt1, paramInt2, paramInt3, paramInt4)) {
                    bool = false;
                    this.u = true;
                    break;
                }
                this.u = true;
            }
            case 2 -> {
                if (b(paramInt1, paramInt2, paramInt3, paramInt4)) {
                    this.v = true;
                    bool = false;
                    break;
                }
                this.u = true;
            }
            case 34, 35, 36, 37 -> {
                if (c(paramInt1, paramInt2, paramInt3, paramInt4, j)) {
                    this.v = true;
                    bool = false;
                    this.u = true;
                }
            }
            case 30, 31, 32, 33 -> {
                if (c(paramInt1, paramInt2, paramInt3, paramInt4, j)) {
                    bool = false;
                    this.u = true;
                }
            }
            case 10 -> {
                k = this.n.TestPointInsideSpikeStars(paramInt4, paramInt3);
                if (k != -1) {
                    int m = this.n.SpikeStarsTopLeft[k].x * 12 + this.n.w[k][0];
                    int n = this.n.SpikeStarsTopLeft[k].y * 12 + this.n.w[k][1];
                    if (a(paramInt1 - this.p + 1, paramInt2 - this.p + 1, paramInt1 + this.p - 1, paramInt2 + this.p - 1, m + 1, n + 1, m + 24 - 1, n + 24 - 1)) {
                        bool = false;
                        KillBall();
                    }
                }
            }
            case 3, 4, 5, 6 -> {
                if (b(paramInt1, paramInt2, paramInt3, paramInt4, j)) {
                    bool = false;
                    KillBall();
                }
            }
            case 7 -> {
                this.n.AddScore(200);
                this.n.LevelTiles[this.c][this.d] = 128;
                a(paramInt4, paramInt3);
                this.n.LevelTiles[paramInt3][paramInt4] = 136;
                sound = this.n.soundPickup;
            }
            case 23 -> {
                if (b(paramInt1, paramInt2, paramInt3, paramInt4, j)) {
                    if (a(paramInt1, paramInt2, paramInt3, paramInt4, j)) {
                        bool = false;
                        break;
                    }
                    OnTouchHoop();
                    this.n.LevelTiles[paramInt3][paramInt4] = (short) (0x9B | isInWater);
                    this.n.LevelTiles[paramInt3][paramInt4 + 1] = (short) (0x9C | isInWater);
                    sound = this.n.soundUp;
                }
            }
            case 15 -> {
                if (b(paramInt1, paramInt2, paramInt3, paramInt4, j)) {
                    if (this.a == 16) {
                        bool = false;
                        break;
                    }
                    if (a(paramInt1, paramInt2, paramInt3, paramInt4, j))
                        bool = false;
                    OnTouchHoop();
                    this.n.LevelTiles[paramInt3][paramInt4] = (short) (0x93 | isInWater);
                    this.n.LevelTiles[paramInt3][paramInt4 + 1] = (short) (0x94 | isInWater);
                    sound = this.n.soundUp;
                }
            }
            case 24 -> {
                if (b(paramInt1, paramInt2, paramInt3, paramInt4, j)) {
                    if (a(paramInt1, paramInt2, paramInt3, paramInt4, j))
                        bool = false;
                    OnTouchHoop();
                    this.n.LevelTiles[paramInt3][paramInt4] = (short) (0x9C | isInWater);
                    this.n.LevelTiles[paramInt3][paramInt4 - 1] = (short) (0x9B | isInWater);
                    sound = this.n.soundUp;
                }
            }
            case 16 -> {
                if (b(paramInt1, paramInt2, paramInt3, paramInt4, j)) {
                    if (this.a == 16) {
                        bool = false;
                        break;
                    }
                    if (a(paramInt1, paramInt2, paramInt3, paramInt4, j))
                        bool = false;
                    OnTouchHoop();
                    this.n.LevelTiles[paramInt3][paramInt4] = (short) (0x94 | isInWater);
                    this.n.LevelTiles[paramInt3][paramInt4 - 1] = (short) (0x93 | isInWater);
                    sound = this.n.soundUp;
                }
            }
            case 21 -> {
                if (b(paramInt1, paramInt2, paramInt3, paramInt4, j)) {
                    if (a(paramInt1, paramInt2, paramInt3, paramInt4, j))
                        bool = false;
                    OnTouchHoop();
                    this.n.LevelTiles[paramInt3][paramInt4] = (short) (0x99 | isInWater);
                    this.n.LevelTiles[paramInt3 + 1][paramInt4] = (short) (0x9A | isInWater);
                    sound = this.n.soundUp;
                }
            }
            case 13 -> {
                if (b(paramInt1, paramInt2, paramInt3, paramInt4, j)) {
                    if (this.a == 16) {
                        bool = false;
                        break;
                    }
                    if (a(paramInt1, paramInt2, paramInt3, paramInt4, j))
                        bool = false;
                    OnTouchHoop();
                    this.n.LevelTiles[paramInt3][paramInt4] = (short) (0x91 | isInWater);
                    this.n.LevelTiles[paramInt3 + 1][paramInt4] = (short) (0x92 | isInWater);
                    sound = this.n.soundUp;
                }
            }
            case 22 -> {
                if (b(paramInt1, paramInt2, paramInt3, paramInt4, j)) {
                    OnTouchHoop();
                    this.n.LevelTiles[paramInt3][paramInt4] = (short) (0x9A | isInWater);
                    this.n.LevelTiles[paramInt3 - 1][paramInt4] = (short) (0x99 | isInWater);
                    sound = this.n.soundUp;
                }
            }
            case 14 -> {
                if (b(paramInt1, paramInt2, paramInt3, paramInt4, j)) {
                    if (this.a == 16) {
                        bool = false;
                        break;
                    }
                    OnTouchHoop();
                    this.n.LevelTiles[paramInt3][paramInt4] = (short) (0x92 | isInWater);
                    this.n.LevelTiles[paramInt3 - 1][paramInt4] = (short) (0x91 | isInWater);
                    sound = this.n.soundUp;
                }
            }
            case 17, 19, 20 -> {
                if (b(paramInt1, paramInt2, paramInt3, paramInt4, j)) {
                    if (this.a == 16) {
                        bool = false;
                        break;
                    }
                    if (a(paramInt1, paramInt2, paramInt3, paramInt4, j))
                        bool = false;
                }
            }
            case 25, 27, 28 -> {
                if (a(paramInt1, paramInt2, paramInt3, paramInt4, j))
                    bool = false;
            }
            case 18 -> {
                if (b(paramInt1, paramInt2, paramInt3, paramInt4, j) && this.a == 16)
                    bool = false;
            }
            case 9 -> {
                if (b(paramInt1, paramInt2, paramInt3, paramInt4, j)) {
                    if (this.n.M) {
                        this.n.e = true;
                        sound = this.n.soundPickup;
                        break;
                    }
                    bool = false;
                }
            }
            case 29 -> { // Power up
                this.n.AddScore(1000);
                if (this.n.lives < 5) {
                    this.n.lives++;
                    this.n.y = true;
                }
                this.n.LevelTiles[paramInt3][paramInt4] = 128;
                sound = this.n.soundPickup;
            }
            case 39, 40, 41, 42 -> {
                bool = false;
                if (this.a == 16)
                    c();
            }
            case 43, 44, 45, 46 -> {
                if (b(paramInt1, paramInt2, paramInt3, paramInt4, j)) {
                    bool = false;
                    if (this.a == 12)
                        f();
                }
            }
            case 47, 48, 49, 50 -> {
                this.g = 300;
                sound = this.n.soundPickup;
                this.m = false;
                bool = false;
            }
            case 51, 52, 53, 54 -> {
                this.y = 300;
                sound = this.n.soundPickup;
                bool = false;
            }
            case 38 -> {
                this.h = 300;
                sound = this.n.soundPickup;
                bool = false;
            }
        }
        if (sound != null)
            sound.play(1);
        return bool;
    }

    public void b() {
        int i = this.s;
        int j = 0;
        int k = 0;
        byte b1 = 0;
        boolean bool1 = false;
        if (this.z == 2) {
            this.q--;
            if (this.q == 0) {
                this.z = 1;
                if (this.n.lives < 0)
                    this.n.e = true;
            }
            return;
        }
        int m = this.s / 12;
        int n = this.r / 12;
        boolean bool2 = (this.n.LevelTiles[n][m] & 0x40) != 0;
        if (bool2) {
            if (this.a == 16) {
                k = -30;
                j = -2;
                if (this.m)
                    this.o = -10;
            } else {
                k = 42;
                j = 6;
            }
        } else if (this.a == 16) {
            k = 38;
            j = 3;
        } else {
            k = 80;
            j = 4;
        }
        if (this.g != 0) {
            bool1 = true;
            k *= -1;
            j *= -1;
            this.g--;
            if (this.g == 0) {
                bool1 = false;
                this.m = false;
                k *= -1;
                j *= -1;
            }
        }
        if (this.y != 0) {
            if (-1 * Math.abs(this.t) > -80)
                if (bool1) {
                    this.t = 80;
                } else {
                    this.t = -80;
                }
            this.y--;
        }
        this.C++;
        if (this.C == 3)
            this.C = 0;
        if (this.o < -150) {
            this.o = -150;
        } else if (this.o > 150) {
            this.o = 150;
        }
        if (this.l < -150) {
            this.l = -150;
        } else if (this.l > 150) {
            this.l = 150;
        }
        if (this.o < 10 && this.o > 0 && !bool2 && !bool1)
            this.o = 10;
        for (byte b2 = 0; b2 < Math.abs(this.o) / 10; b2++) {
            byte b = 0;
            if (this.o != 0)
                b = (byte) ((this.o < 0) ? -1 : 1);
            if (b(this.s, this.r + b)) {
                this.r += b;
                this.m = false;
                if (k == -30) {
                    n = this.r / 12;
                    if ((this.n.LevelTiles[n][m] & 0x40) == 0) {
                        this.o >>= 1;
                        if (this.o <= 10 && this.o >= -10)
                            this.o = 0;
                    }
                }
            } else {
                if (this.u && this.l < 10 && this.C == 0) {
                    byte b4 = 1;
                    if (b(this.s + b4, this.r + b)) {
                        this.s += b4;
                        this.r += b;
                        this.u = false;
                    } else if (b(this.s - b4, this.r + b)) {
                        this.s -= b4;
                        this.r += b;
                        this.u = false;
                    }
                }
                if (b > 0 || (bool1 && b < 0)) {
                    this.o = this.o * -1 / 2;
                    this.m = true;
                    if (this.v && (this.w & 0x8) != 0) {
                        this.v = false;
                        if (bool1) {
                            this.t += 10;
                        } else {
                            this.t += -10;
                        }
                    } else if (this.y == 0) {
                        this.t = 0;
                    }
                    if (this.o < 10 && this.o > -10) {
                        if (bool1) {
                            this.o = -10;
                            break;
                        }
                        this.o = 10;
                    }
                    break;
                }
                if (b < 0 || (bool1 && b > 0))
                    if (bool1) {
                        this.o = -20;
                    } else {
                        this.o = -this.o >> 1;
                    }
            }
        }
        if (bool1) {
            if (j == -2 && this.o < k) {
                this.o += j;
                if (this.o > k)
                    this.o = k;
            } else if (!this.m && this.o > k) {
                this.o += j;
                if (this.o < k)
                    this.o = k;
            }
        } else if (j == -2 && this.o > k) {
            this.o += j;
            if (this.o < k)
                this.o = k;
        } else if (!this.m && this.o < k) {
            this.o += j;
            if (this.o > k)
                this.o = k;
        }
        if (this.h != 0) {
            b1 = 100;
            this.h--;
        } else {
            b1 = 50;
        }
        if ((this.w & 0x2) != 0 && this.l < b1) {
            this.l += 6;
        } else if ((this.w & 0x1) != 0 && this.l > -b1) {
            this.l -= 6;
        } else if (this.l > 0) {
            this.l -= 4;
        } else if (this.l < 0) {
            this.l += 4;
        }
        if (this.a == 16 && this.y == 0)
            if (bool1) {
                this.t += 5;
            } else {
                this.t += -5;
            }
        if (this.m && (this.w & 0x8) != 0) {
            if (bool1) {
                this.o = 67 + this.t;
            } else {
                this.o = -67 + this.t;
            }
            this.m = false;
        }
        int i1 = Math.abs(this.l);
        int i2 = i1 / 10;
        for (byte b3 = 0; b3 < i2; b3++) {
            byte b = 0;
            if (this.l != 0)
                b = (byte) ((this.l < 0) ? -1 : 1);
            if (b(this.s + b, this.r)) {
                this.s += b;
            } else if (this.u) {
                this.u = false;
                byte b4 = 0;
                if (bool1) {
                    b4 = 1;
                } else {
                    b4 = -1;
                }
                if (b(this.s + b, this.r + b4)) {
                    this.s += b;
                    this.r += b4;
                } else if (b(this.s + b, this.r - b4)) {
                    this.s += b;
                    this.r -= b4;
                } else {
                    this.l = -(this.l >> 1);
                }
            }
        }
    }

    public static boolean a(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8) {
        return (paramInt1 <= paramInt7 && paramInt2 <= paramInt8 && paramInt5 <= paramInt3 && paramInt6 <= paramInt4);
    }
}


/* Location:              C:\Users\kuzme\Downloads\nokiabounc_jdifc8jb.jar!\com\nokia\mid\appl\boun\f.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */