package com.nokia.mid.appl.boun;

import com.nokia.mid.sound.Sound;

import javax.microedition.lcdui.Image;

public class f {
    private boolean j = true;

    public int TODO_unkX;

    public int TODO_unkY;

    public int l;

    public int o;

    public int w;

    public int ballSize;

    public int p;

    public int d;

    public int c;

    public int b;

    public int z;

    public int t;

    public int TODO_somePowerUp1;

    public int powerUpGravity;

    public int TODO_somePowerUp3;

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

    public Image spriteCurrentBall;

    public Image spritePoppedBall;

    public Image spriteBigBall;

    public Image spriteBall;

    private int q;

    public f(int paramInt1, int paramInt2, int ballSize, e parame) {
        this.TODO_unkX = paramInt1;
        this.TODO_unkY = paramInt2;
        this.l = 0;
        this.o = 0;
        this.n = parame;
        this.t = 0;
        this.m = false;
        this.v = false;
        this.u = false;
        this.q = 0;
        this.TODO_somePowerUp1 = 0;
        this.powerUpGravity = 0;
        this.TODO_somePowerUp3 = 0;
        this.C = 0;
        this.z = 0;
        this.w = 0;
        this.n.CreateTiles(this);
        if (ballSize == 12) {
            UseRegularBall();
        } else {
            UseBigBall();
        }
    }

    public void a(int tileX, int tileY) {
        this.d = tileX;
        this.c = tileY;
        this.b = this.ballSize;
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

    public boolean TODO_CheckBallCollision(int x, int y) {
        int i = (x - this.p) / 12;
        int j = (y - this.p) / 12;
        int k = (x - 1 + this.p) / 12 + 1;
        int m = (y - 1 + this.p) / 12 + 1;
        for (int tileX = i; tileX < k; tileX++) {
            for (int tileY = j; tileY < m; tileY++) {
                if (!a(x, y, tileY, tileX))
                    return false;
            }
        }
        return true;
    }

    public void UseBigBall() {
        this.ballSize = 16;
        this.p = 8;
        this.spriteCurrentBall = this.spriteBigBall;

        // Try to solve any collisions with the world
        boolean TODO_collidesWithLevel = false;
        for (byte offset = 1; !TODO_collidesWithLevel; offset++) {
            TODO_collidesWithLevel = true;
            if (TODO_CheckBallCollision(this.TODO_unkX, this.TODO_unkY - offset)) {
                this.TODO_unkY -= offset;
                continue;
            }
            if (TODO_CheckBallCollision(this.TODO_unkX - offset, this.TODO_unkY - offset)) {
                this.TODO_unkX -= offset;
                this.TODO_unkY -= offset;
                continue;
            }
            if (TODO_CheckBallCollision(this.TODO_unkX + offset, this.TODO_unkY - offset)) {
                this.TODO_unkX += offset;
                this.TODO_unkY -= offset;
                continue;
            }
            if (TODO_CheckBallCollision(this.TODO_unkX, this.TODO_unkY + offset)) {
                this.TODO_unkY += offset;
                continue;
            }
            if (TODO_CheckBallCollision(this.TODO_unkX - offset, this.TODO_unkY + offset)) {
                this.TODO_unkX -= offset;
                this.TODO_unkY += offset;
                continue;
            }
            if (TODO_CheckBallCollision(this.TODO_unkX + offset, this.TODO_unkY + offset)) {
                this.TODO_unkX += offset;
                this.TODO_unkY += offset;
                continue;
            }
            TODO_collidesWithLevel = false;
        }
    }

    public void UseRegularBall() {
        this.ballSize = 12;
        this.p = 6;
        this.spriteCurrentBall = this.spriteBall;
    }

    public void KillBall() {
        if (!this.n.GodMode) {
            this.q = 7;
            this.z = 2;
            this.n.lives--;
            this.TODO_somePowerUp1 = 0;
            this.powerUpGravity = 0;
            this.TODO_somePowerUp3 = 0;
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

    public boolean TODO_CheckWallCollision(int paramInt1, int paramInt2, int tileY, int tileX) {
        byte b1;
        int n;
        byte b2;
        int i1;
        byte[][] ballCollision;
        int i = tileX * 12;
        int j = tileY * 12;
        int k = paramInt1 - this.p - i;
        int m = paramInt2 - this.p - j;
        if (k >= 0) {
            b1 = (byte) k;
            n = 12;
        } else {
            b1 = 0;
            n = this.ballSize + k;
        }
        if (m >= 0) {
            b2 = (byte) m;
            i1 = 12;
        } else {
            b2 = 0;
            i1 = this.ballSize + m;
        }
        if (this.ballSize == 16) {
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

    public boolean c(int paramInt1, int paramInt2, int y, int x, int tileId) {
        byte b3;
        int n;
        byte b4;
        int i1;
        byte[][] ballCollision;
        int i = x * 12;
        int j = y * 12;
        int k = paramInt1 - this.p - i;
        int m = paramInt2 - this.p - j;
        byte b1 = 0;
        byte b2 = 0;
        switch (tileId) {
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
            n = this.ballSize + k;
        }
        if (m >= 0) {
            b4 = (byte) m;
            i1 = 12;
        } else {
            b4 = 0;
            i1 = this.ballSize + m;
        }
        if (this.ballSize == 16) {
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
                        b(tileId);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean b(int paramInt1, int paramInt2, int tileY, int tileX, int tileId) {
        int i = tileX * 12;
        int j = tileY * 12;
        int k = i + 12;
        int m = j + 12;
        switch (tileId) {
            case TileIDs.THORNS_UP, TileIDs.THORNS_DOWN, 9, 13, 14, 17, 18, 21, 22, 43, 45 -> {
                i += 4;
                k -= 4;
            }
            case TileIDs.THORNS_RIGHT, TileIDs.THORNS_LEFT, 15, 16, 19, 20, 23, 24, 44, 46 -> {
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

    public boolean a(int x, int y, int tileY, int tileX) {
        int k;
        if (tileY >= this.n.TODO_height || tileY < 0 || tileX >= this.n.TODO_width || tileX < 0)
            return false;
        if (this.z == 2)
            return false;
        boolean bool = true;
        int isInWater = this.n.LevelTiles[tileY][tileX] & 0x40;
        int tileId = this.n.LevelTiles[tileY][tileX] & (~0x40) & (~0x80);
        Sound sound = null;
        switch (tileId) {
            case TileIDs.BRICK_WALL -> {
                if (TODO_CheckWallCollision(x, y, tileY, tileX)) {
                    bool = false;
                    this.u = true;
                    break;
                }
                this.u = true;
            }
            case TileIDs.RUBBER_WALL -> {
                if (TODO_CheckWallCollision(x, y, tileY, tileX)) {
                    this.v = true;
                    bool = false;
                    break;
                }
                this.u = true;
            }
            case 34, 35, 36, 37 -> {
                if (c(x, y, tileY, tileX, tileId)) {
                    this.v = true;
                    bool = false;
                    this.u = true;
                }
            }
            case 30, 31, 32, 33 -> {
                if (c(x, y, tileY, tileX, tileId)) {
                    bool = false;
                    this.u = true;
                }
            }
            case 10 -> {
                k = this.n.TestPointInsideDynThorns(tileX, tileY);
                if (k != -1) {
                    int m = this.n.DynThornsBottomLeft[k].x * 12 + this.n.w[k].x;
                    int n = this.n.DynThornsBottomLeft[k].y * 12 + this.n.w[k].y;
                    if (a(x - this.p + 1, y - this.p + 1, x + this.p - 1, y + this.p - 1, m + 1, n + 1, m + 24 - 1, n + 24 - 1)) {
                        bool = false;
                        KillBall();
                    }
                }
            }
            case TileIDs.THORNS_UP, TileIDs.THORNS_RIGHT, TileIDs.THORNS_DOWN, TileIDs.THORNS_LEFT -> {
                if (b(x, y, tileY, tileX, tileId)) {
                    bool = false;
                    KillBall();
                }
            }
            case TileIDs.CRYSTAL -> {
                this.n.AddScore(200);
                this.n.LevelTiles[this.c][this.d] = 0x80 | TileIDs.EMPTY;
                a(tileX, tileY);
                this.n.LevelTiles[tileY][tileX] = 0x80 | TileIDs.CRYSTAL_ACTIVE;
                sound = this.n.soundPickup;
            }
            case 23 -> {
                if (b(x, y, tileY, tileX, tileId)) {
                    if (a(x, y, tileY, tileX, tileId)) {
                        bool = false;
                        break;
                    }
                    OnTouchHoop();
                    this.n.LevelTiles[tileY][tileX] = (short) (0x80 | 27 | isInWater);
                    this.n.LevelTiles[tileY][tileX + 1] = (short) (0x80 | 28 | isInWater);
                    sound = this.n.soundUp;
                }
            }
            case 15 -> {
                if (b(x, y, tileY, tileX, tileId)) {
                    if (this.ballSize == 16) {
                        bool = false;
                        break;
                    }
                    if (a(x, y, tileY, tileX, tileId))
                        bool = false;
                    OnTouchHoop();
                    this.n.LevelTiles[tileY][tileX] = (short) (0x93 | isInWater);
                    this.n.LevelTiles[tileY][tileX + 1] = (short) (0x94 | isInWater);
                    sound = this.n.soundUp;
                }
            }
            case 24 -> {
                if (b(x, y, tileY, tileX, tileId)) {
                    if (a(x, y, tileY, tileX, tileId))
                        bool = false;
                    OnTouchHoop();
                    this.n.LevelTiles[tileY][tileX] = (short) (0x9C | isInWater);
                    this.n.LevelTiles[tileY][tileX - 1] = (short) (0x9B | isInWater);
                    sound = this.n.soundUp;
                }
            }
            case 16 -> {
                if (b(x, y, tileY, tileX, tileId)) {
                    if (this.ballSize == 16) {
                        bool = false;
                        break;
                    }
                    if (a(x, y, tileY, tileX, tileId))
                        bool = false;
                    OnTouchHoop();
                    this.n.LevelTiles[tileY][tileX] = (short) (0x94 | isInWater);
                    this.n.LevelTiles[tileY][tileX - 1] = (short) (0x93 | isInWater);
                    sound = this.n.soundUp;
                }
            }
            case 21 -> {
                if (b(x, y, tileY, tileX, tileId)) {
                    if (a(x, y, tileY, tileX, tileId))
                        bool = false;
                    OnTouchHoop();
                    this.n.LevelTiles[tileY][tileX] = (short) (0x99 | isInWater);
                    this.n.LevelTiles[tileY + 1][tileX] = (short) (0x9A | isInWater);
                    sound = this.n.soundUp;
                }
            }
            case 13 -> {
                if (b(x, y, tileY, tileX, tileId)) {
                    if (this.ballSize == 16) {
                        bool = false;
                        break;
                    }
                    if (a(x, y, tileY, tileX, tileId))
                        bool = false;
                    OnTouchHoop();
                    this.n.LevelTiles[tileY][tileX] = (short) (0x91 | isInWater);
                    this.n.LevelTiles[tileY + 1][tileX] = (short) (0x92 | isInWater);
                    sound = this.n.soundUp;
                }
            }
            case 22 -> {
                if (b(x, y, tileY, tileX, tileId)) {
                    OnTouchHoop();
                    this.n.LevelTiles[tileY][tileX] = (short) (0x9A | isInWater);
                    this.n.LevelTiles[tileY - 1][tileX] = (short) (0x99 | isInWater);
                    sound = this.n.soundUp;
                }
            }
            case 14 -> {
                if (b(x, y, tileY, tileX, tileId)) {
                    if (this.ballSize == 16) {
                        bool = false;
                        break;
                    }
                    OnTouchHoop();
                    this.n.LevelTiles[tileY][tileX] = (short) (0x92 | isInWater);
                    this.n.LevelTiles[tileY - 1][tileX] = (short) (0x91 | isInWater);
                    sound = this.n.soundUp;
                }
            }
            case 17, 19, 20 -> {
                if (b(x, y, tileY, tileX, tileId)) {
                    if (this.ballSize == 16) {
                        bool = false;
                        break;
                    }
                    if (a(x, y, tileY, tileX, tileId))
                        bool = false;
                }
            }
            case 25, 27, 28 -> {
                if (a(x, y, tileY, tileX, tileId))
                    bool = false;
            }
            case 18 -> {
                if (b(x, y, tileY, tileX, tileId) && this.ballSize == 16)
                    bool = false;
            }
            case 9 -> {
                if (b(x, y, tileY, tileX, tileId)) {
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
                this.n.LevelTiles[tileY][tileX] = 0x80 | TileIDs.EMPTY;
                sound = this.n.soundPickup;
            }
            case 39, 40, 41, 42 -> {
                bool = false;
                if (this.ballSize == 16)
                    UseRegularBall();
            }
            case TileIDs.PUMPER_UP, TileIDs.PUMPER_RIGHT, TileIDs.PUMPER_DOWN, TileIDs.PUMPER_LEFT -> {
                if (b(x, y, tileY, tileX, tileId)) {
                    bool = false;
                    if (this.ballSize == 12)
                        UseBigBall();
                }
            }
            case TileIDs.POWER_UP_GRAVITY_UP, TileIDs.POWER_UP_GRAVITY_RIGHT, TileIDs.POWER_UP_GRAVITY_DOWN, TileIDs.POWER_UP_GRAVITY_LEFT -> {
                this.powerUpGravity = 300;
                sound = this.n.soundPickup;
                this.m = false;
                bool = false;
            }
            case 51, 52, 53, 54 -> {
                this.TODO_somePowerUp3 = 300;
                sound = this.n.soundPickup;
                bool = false;
            }
            case 38 -> {
                this.TODO_somePowerUp1 = 300;
                sound = this.n.soundPickup;
                bool = false;
            }
        }
        if (sound != null)
            sound.play(1);
        return bool;
    }

    public void b() {
        int i = this.TODO_unkX;
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
        int tileX = this.TODO_unkX / 12;
        int tileY = this.TODO_unkY / 12;
        boolean isInWater = (this.n.LevelTiles[tileY][tileX] & 0x40) != 0;
        if (isInWater) {
            if (this.ballSize == 16) {
                k = -30;
                j = -2;
                if (this.m)
                    this.o = -10;
            } else {
                k = 42;
                j = 6;
            }
        } else if (this.ballSize == 16) {
            k = 38;
            j = 3;
        } else {
            k = 80;
            j = 4;
        }
        if (this.powerUpGravity != 0) {
            bool1 = true;
            k *= -1;
            j *= -1;
            this.powerUpGravity--;
            if (this.powerUpGravity == 0) {
                bool1 = false;
                this.m = false;
                k *= -1;
                j *= -1;
            }
        }
        if (this.TODO_somePowerUp3 != 0) {
            if (-1 * Math.abs(this.t) > -80)
                if (bool1) {
                    this.t = 80;
                } else {
                    this.t = -80;
                }
            this.TODO_somePowerUp3--;
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
        if (this.o < 10 && this.o > 0 && !isInWater && !bool1)
            this.o = 10;
        for (byte b2 = 0; b2 < Math.abs(this.o) / 10; b2++) {
            byte b = 0;
            if (this.o != 0)
                b = (byte) ((this.o < 0) ? -1 : 1);
            if (TODO_CheckBallCollision(this.TODO_unkX, this.TODO_unkY + b)) {
                this.TODO_unkY += b;
                this.m = false;
                if (k == -30) {
                    tileY = this.TODO_unkY / 12;
                    if ((this.n.LevelTiles[tileY][tileX] & 0x40) == 0) {
                        this.o >>= 1;
                        if (this.o <= 10 && this.o >= -10)
                            this.o = 0;
                    }
                }
            } else {
                if (this.u && this.l < 10 && this.C == 0) {
                    byte b4 = 1;
                    if (TODO_CheckBallCollision(this.TODO_unkX + b4, this.TODO_unkY + b)) {
                        this.TODO_unkX += b4;
                        this.TODO_unkY += b;
                        this.u = false;
                    } else if (TODO_CheckBallCollision(this.TODO_unkX - b4, this.TODO_unkY + b)) {
                        this.TODO_unkX -= b4;
                        this.TODO_unkY += b;
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
                    } else if (this.TODO_somePowerUp3 == 0) {
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
        if (this.TODO_somePowerUp1 != 0) {
            b1 = 100;
            this.TODO_somePowerUp1--;
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
        if (this.ballSize == 16 && this.TODO_somePowerUp3 == 0)
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
            if (TODO_CheckBallCollision(this.TODO_unkX + b, this.TODO_unkY)) {
                this.TODO_unkX += b;
            } else if (this.u) {
                this.u = false;
                byte b4 = 0;
                if (bool1) {
                    b4 = 1;
                } else {
                    b4 = -1;
                }
                if (TODO_CheckBallCollision(this.TODO_unkX + b, this.TODO_unkY + b4)) {
                    this.TODO_unkX += b;
                    this.TODO_unkY += b4;
                } else if (TODO_CheckBallCollision(this.TODO_unkX + b, this.TODO_unkY - b4)) {
                    this.TODO_unkX += b;
                    this.TODO_unkY -= b4;
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