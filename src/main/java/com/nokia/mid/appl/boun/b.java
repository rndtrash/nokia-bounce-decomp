package com.nokia.mid.appl.boun;

import com.nokia.mid.ui.DirectGraphics;
import com.nokia.mid.ui.DirectUtils;
import com.nokia.mid.ui.FullCanvas;

import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public abstract class b extends FullCanvas {
    public static final class BounceColors {
        public static final int ALPHA = 0xff000000;
        public static final int SKY = 0xb0e0f0;
        public static final int SKY_ALPHA = ALPHA | SKY;
        public static final int WATER = 0x1060b0;
        public static final int WATER_ALPHA = ALPHA | WATER;
    }

    public static final class ImageManipulation {
        public static final int FLIP_HORIZONTAL = 0;
        public static final int FLIP_VERTICAL = 1;
        public static final int FLIP_BOTH = 2;
        /**
         * Rotate counter-clockwise by 90 degrees
         */
        public static final int ROTATE_90 = 3;
        /**
         * Rotate counter-clockwise by 180 degrees
         */
        public static final int ROTATE_180 = 4;
        /**
         * Rotate counter-clockwise by 270 degrees
         */
        public static final int ROTATE_270 = 5;
    }

    public static final class SpriteIDs {
        public static final int BRICK_WALL = 0;
        public static final int RUBBER_WALL = 1;

        public static final int SPIKES_UP = 2;
        public static final int SPIKES_DOWN = 3;
        public static final int SPIKES_LEFT = 4;
        public static final int SPIKES_RIGHT = 5;

        public static final int SPIKES_WATER_UP = 6;
        public static final int SPIKES_WATER_DOWN = 7;
        public static final int SPIKES_WATER_LEFT = 8;
        public static final int SPIKES_WATER_RIGHT = 9;

        /**
         * A.K.A. Checkpoint
         */
        public static final int CRYSTAL = 10;
        /**
         * A.K.A. Active checkpoint
         */
        public static final int CRYSTAL_ACTIVE = 11;

        public static final int EXIT = 12;

        public static final int BALL = 47;
        public static final int POPPED_BALL = 48;
        public static final int BIG_BALL = 49;
    }

    protected int l;

    protected int k;

    protected int G;

    protected int Z;

    protected int v;

    protected boolean z;

    protected Image E;

    private Image[] Sprites;

    private Image I;

    private Graphics J;

    public int level = -1;

    public String LevelString;

    public String LevelCompletedString;

    public boolean d;

    protected int s;

    protected int S;

    /**
     * Either 12 (small) or 16 (big)
     */
    public int BallSize;

    protected int W;

    protected int V;

    public short[][] C;

    public int c;

    public int ai;

    public int HoopsTotal;

    public int B;

    public short[][] P;

    public short[][] O;

    public short[][] ae;

    public short[][] w;

    public Image[] r;

    public Graphics[] an;

    public Image L;

    public Image imageBall;

    public Image imageHoop;

    public int al;

    public int u;

    public Image aa;

    public Image o;

    public int b;

    public boolean M;

    protected int displayWidth = 0;

    protected int displayHeight = 0;

    protected Display display;

    public BounceTimer GameTimer = null;

    public b(Display display) {
        this.display = display;
        this.displayWidth = getWidth();
        this.displayHeight = getHeight();
        this.v = 0;
        this.Z = 156;
        this.E = Image.createImage(156, 96);
        this.I = Image.createImage(12, 12);
        this.J = this.I.getGraphics();
        LoadSpriteSheet();
        this.d = false;
        this.l = 0;
        this.k = 0;
        this.z = false;
        this.G = this.l + 13;
        this.C = null;
    }

    public void CreateTiles(int levelId) {
        InputStream levelStream = null;
        DataInputStream levelDIS = null;
        this.d = false;
        String levelName = "";
        String[] arrayOfString = new String[1];
        arrayOfString[0] = Integer.valueOf(this.level).toString();
        this.LevelString = Translation.sprintf_translated(Translation.LEVEL, arrayOfString);
        this.LevelCompletedString = Translation.sprintf_translated(Translation.LEVEL_COMPLETED, arrayOfString);
        arrayOfString[0] = null;
        arrayOfString = null;
        if (levelId < 10) {
            levelName = "00" + levelId;
        } else if (levelId < 100) {
            levelName = "0" + levelId;
        }
        try {
            levelStream = getClass().getResourceAsStream("/levels/J2MElvl." + levelName);
            levelDIS = new DataInputStream(levelStream);
            this.s = levelDIS.read();
            this.S = levelDIS.read();
            int i = levelDIS.read();
            if (i == 0) {
                this.BallSize = 12;
            } else {
                this.BallSize = 16;
            }
            this.W = levelDIS.read();
            this.V = levelDIS.read();
            CreateTiles(this.W, this.V, this.Sprites[SpriteIDs.EXIT]);
            this.HoopsTotal = levelDIS.read();
            this.c = levelDIS.read();
            this.ai = levelDIS.read();
            this.C = new short[this.ai][this.c];
            for (byte b1 = 0; b1 < this.ai; b1++) {
                for (byte b2 = 0; b2 < this.c; b2++)
                    this.C[b1][b2] = (short) levelDIS.read();
            }
            this.B = levelDIS.read();
            if (this.B != 0)
                CreateTiles(levelDIS);
            levelDIS.close();
        } catch (IOException iOException) {
        }
    }

    public static Image CreateTiles(Image paramImage, int manipulation) {
        Image image = DirectUtils.createImage(paramImage.getWidth(), paramImage.getHeight(), 0);
        if (image == null)
            image = Image.createImage(paramImage.getWidth(), paramImage.getHeight());
        Graphics graphics = image.getGraphics();
        DirectGraphics directGraphics = DirectUtils.getDirectGraphics(graphics);
        switch (manipulation) {
            case ImageManipulation.FLIP_HORIZONTAL -> {
                directGraphics.drawImage(paramImage, 0, 0, Graphics.TOP | Graphics.LEFT, DirectGraphics.FLIP_HORIZONTAL);
                return image;
            }
            case ImageManipulation.FLIP_VERTICAL -> {
                directGraphics.drawImage(paramImage, 0, 0, Graphics.TOP | Graphics.LEFT, DirectGraphics.FLIP_VERTICAL);
                return image;
            }
            case ImageManipulation.FLIP_BOTH -> {
                directGraphics.drawImage(paramImage, 0, 0, Graphics.TOP | Graphics.LEFT, DirectGraphics.FLIP_HORIZONTAL | DirectGraphics.FLIP_VERTICAL);
                return image;
            }
            case ImageManipulation.ROTATE_90 -> {
                directGraphics.drawImage(paramImage, 0, 0, Graphics.TOP | Graphics.LEFT, DirectGraphics.ROTATE_90);
                return image;
            }
            case ImageManipulation.ROTATE_180 -> {
                directGraphics.drawImage(paramImage, 0, 0, Graphics.TOP | Graphics.LEFT, DirectGraphics.ROTATE_180);
                return image;
            }
            case ImageManipulation.ROTATE_270 -> {
                directGraphics.drawImage(paramImage, 0, 0, Graphics.TOP | Graphics.LEFT, DirectGraphics.ROTATE_270);
                return image;
            }
        }
        graphics.drawImage(paramImage, 0, 0, Graphics.TOP | Graphics.LEFT);
        return image;
    }

    public void CreateTiles(DataInputStream paramDataInputStream) throws IOException {
        this.P = new short[this.B][2];
        this.O = new short[this.B][2];
        this.ae = new short[this.B][2];
        this.w = new short[this.B][2];
        this.r = new Image[this.B];
        this.an = new Graphics[this.B];
        for (byte b1 = 0; b1 < this.B; b1++) {
            this.P[b1][0] = (short) paramDataInputStream.read();
            this.P[b1][1] = (short) paramDataInputStream.read();
            this.O[b1][0] = (short) paramDataInputStream.read();
            this.O[b1][1] = (short) paramDataInputStream.read();
            this.ae[b1][0] = (short) paramDataInputStream.read();
            this.ae[b1][1] = (short) paramDataInputStream.read();
            int i = paramDataInputStream.read();
            int j = paramDataInputStream.read();
            this.w[b1][0] = (short) i;
            this.w[b1][1] = (short) j;
        }
        this.L = Image.createImage(24, 24);
        Graphics graphics = this.L.getGraphics();
        graphics.drawImage(this.Sprites[46], 0, 0, Graphics.TOP | Graphics.LEFT);
        graphics.drawImage(CreateTiles(this.Sprites[46], ImageManipulation.FLIP_HORIZONTAL), 12, 0, Graphics.TOP | Graphics.LEFT);
        graphics.drawImage(CreateTiles(this.Sprites[46], ImageManipulation.ROTATE_180), 12, 12, Graphics.TOP | Graphics.LEFT);
        graphics.drawImage(CreateTiles(this.Sprites[46], ImageManipulation.FLIP_VERTICAL), 0, 12, Graphics.TOP | Graphics.LEFT);
        graphics = null;
    }

    public void RunGarbageCollector() {
        for (byte b1 = 0; b1 < this.B; b1++) {
            this.r[b1] = null;
            this.an[b1] = null;
        }
        this.r = null;
        this.an = null;
        this.C = null;
        Runtime.getRuntime().gc();
    }

    public void o() {
        for (byte b1 = 0; b1 < this.B; b1++) {
            short s1 = this.P[b1][0];
            short s2 = this.P[b1][1];
            short s3 = this.w[b1][0];
            short s4 = this.w[b1][1];
            this.w[b1][0] = (short) (this.w[b1][0] + this.ae[b1][0]);
            int n = (this.O[b1][0] - s1 - 2) * 12;
            int i1 = (this.O[b1][1] - s2 - 2) * 12;
            if (this.w[b1][0] < 0) {
                this.w[b1][0] = 0;
            } else if (this.w[b1][0] > n) {
                this.w[b1][0] = (short) n;
            }
            if (this.w[b1][0] == 0 || this.w[b1][0] == n)
                this.ae[b1][0] = (short) -this.ae[b1][0];
            this.w[b1][1] = (short) (this.w[b1][1] + this.ae[b1][1]);
            if (this.w[b1][1] < 0) {
                this.w[b1][1] = 0;
            } else if (this.w[b1][1] > i1) {
                this.w[b1][1] = (short) i1;
            }
            if (this.w[b1][1] == 0 || this.w[b1][1] == i1)
                this.ae[b1][1] = (short) (this.ae[b1][1] * -1);
            short s5 = this.w[b1][0];
            short s6 = this.w[b1][1];
            if (s5 < s3) {
                short s = s5;
                s5 = s3;
                s3 = s;
            }
            if (s6 < s4) {
                short s = s6;
                s6 = s4;
                s4 = s;
            }
            s5 += 23;
            s6 += 23;
            int i = s3 / 12;
            int j = s4 / 12;
            int k = s5 / 12 + 1;
            int m = s6 / 12 + 1;
            for (int i2 = i; i2 < k; i2++) {
                for (int i3 = j; i3 < m; i3++)
                    this.C[s2 + i3][s1 + i2] = (short) (this.C[s2 + i3][s1 + i2] | 0x80);
            }
        }
    }

    public int b(int paramInt1, int paramInt2) {
        for (byte b1 = 0; b1 < this.B; b1++) {
            if (this.P[b1][0] <= paramInt1 && this.O[b1][0] > paramInt1 && this.P[b1][1] <= paramInt2 && this.O[b1][1] > paramInt2)
                return b1;
        }
        return -1;
    }

    public void CreateTiles(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        int j;
        int k;
        Graphics graphics = this.E.getGraphics();
        if (paramInt1 < 0 || paramInt2 < 0 || paramInt1 >= this.c || paramInt2 >= this.ai) {
            graphics.drawImage(this.Sprites[SpriteIDs.BRICK_WALL], paramInt3, paramInt4, Graphics.TOP | Graphics.LEFT);
            return;
        }
        this.C[paramInt2][paramInt1] = (short) (this.C[paramInt2][paramInt1] & 0xFF7F);
        int i = this.C[paramInt2][paramInt1];
        boolean isInWater = (i & 0x40) != 0; // TODO: investigate this bit mask
        if (isInWater)
            i = i & (~0x40);
        graphics.setColor(isInWater ? BounceColors.WATER : BounceColors.SKY);
        switch (i) {
            case 1 -> graphics.drawImage(this.Sprites[SpriteIDs.BRICK_WALL], paramInt3, paramInt4, Graphics.TOP | Graphics.LEFT);
            case 0 -> graphics.fillRect(paramInt3, paramInt4, 12, 12);
            case 2 -> graphics.drawImage(this.Sprites[SpriteIDs.RUBBER_WALL], paramInt3, paramInt4, Graphics.TOP | Graphics.LEFT);
            case 3 -> {
                if (isInWater) {
                    graphics.drawImage(this.Sprites[SpriteIDs.SPIKES_WATER_UP], paramInt3, paramInt4, Graphics.TOP | Graphics.LEFT);
                    break;
                }
                graphics.drawImage(this.Sprites[SpriteIDs.SPIKES_UP], paramInt3, paramInt4, Graphics.TOP | Graphics.LEFT);
            }
            case 4 -> {
                if (isInWater) {
                    graphics.drawImage(this.Sprites[SpriteIDs.SPIKES_WATER_RIGHT], paramInt3, paramInt4, Graphics.TOP | Graphics.LEFT);
                    break;
                }
                graphics.drawImage(this.Sprites[SpriteIDs.SPIKES_RIGHT], paramInt3, paramInt4, Graphics.TOP | Graphics.LEFT);
            }
            case 5 -> {
                if (isInWater) {
                    graphics.drawImage(this.Sprites[SpriteIDs.SPIKES_WATER_DOWN], paramInt3, paramInt4, Graphics.TOP | Graphics.LEFT);
                    break;
                }
                graphics.drawImage(this.Sprites[SpriteIDs.SPIKES_DOWN], paramInt3, paramInt4, Graphics.TOP | Graphics.LEFT);
            }
            case 6 -> {
                if (isInWater) {
                    graphics.drawImage(this.Sprites[SpriteIDs.SPIKES_WATER_LEFT], paramInt3, paramInt4, Graphics.TOP | Graphics.LEFT);
                    break;
                }
                graphics.drawImage(this.Sprites[SpriteIDs.SPIKES_LEFT], paramInt3, paramInt4, Graphics.TOP | Graphics.LEFT);
            }
            case 7 -> graphics.drawImage(this.Sprites[SpriteIDs.CRYSTAL], paramInt3, paramInt4, Graphics.TOP | Graphics.LEFT);
            case 8 -> graphics.drawImage(this.Sprites[SpriteIDs.CRYSTAL_ACTIVE], paramInt3, paramInt4, Graphics.TOP | Graphics.LEFT);
            case 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28 -> {
                graphics.fillRect(paramInt3, paramInt4, 12, 12);
                graphics.drawImage(this.Sprites[com.nokia.mid.appl.boun.d.a[i - 13]], paramInt3, paramInt4, Graphics.TOP | Graphics.LEFT);
                graphics.drawImage(this.Sprites[com.nokia.mid.appl.boun.d.b[i - 13]], paramInt3, paramInt4, Graphics.TOP | Graphics.LEFT);
            }
            case 9 -> {
                j = (paramInt1 - this.al) * 12;
                k = (paramInt2 - this.u) * 12;
                graphics.setClip(paramInt3, paramInt4, 12, 12);
                graphics.drawImage(this.aa, paramInt3 - j, paramInt4 - k, Graphics.TOP | Graphics.LEFT);
                graphics.setClip(0, 0, this.E.getWidth(), this.E.getHeight());
                this.z = true;
            }
            case 10 -> {
                j = b(paramInt1, paramInt2);
                if (j != -1) {
                    k = (paramInt1 - this.P[j][0]) * 12;
                    int m = (paramInt2 - this.P[j][1]) * 12;
                    int n = this.w[j][0] - k;
                    int i1 = this.w[j][1] - m;
                    if ((n > -36 && n < 12) || (i1 > -36 && i1 < 12)) {
                        this.J.setColor(BounceColors.SKY);
                        this.J.fillRect(0, 0, 12, 12);
                        this.J.drawImage(this.L, n, i1, Graphics.TOP | Graphics.LEFT);
                        graphics.drawImage(this.I, paramInt3, paramInt4, Graphics.TOP | Graphics.LEFT);
                        break;
                    }
                    graphics.setColor(BounceColors.SKY);
                    graphics.fillRect(paramInt3, paramInt4, 12, 12);
                }
            }
            case 29 -> graphics.drawImage(this.Sprites[45], paramInt3, paramInt4, Graphics.TOP | Graphics.LEFT);
            case 30 -> {
                if (isInWater) {
                    graphics.drawImage(this.Sprites[61], paramInt3, paramInt4, Graphics.TOP | Graphics.LEFT);
                    break;
                }
                graphics.drawImage(this.Sprites[57], paramInt3, paramInt4, Graphics.TOP | Graphics.LEFT);
            }
            case 31 -> {
                if (isInWater) {
                    graphics.drawImage(this.Sprites[60], paramInt3, paramInt4, Graphics.TOP | Graphics.LEFT);
                    break;
                }
                graphics.drawImage(this.Sprites[56], paramInt3, paramInt4, Graphics.TOP | Graphics.LEFT);
            }
            case 32 -> {
                if (isInWater) {
                    graphics.drawImage(this.Sprites[59], paramInt3, paramInt4, Graphics.TOP | Graphics.LEFT);
                    break;
                }
                graphics.drawImage(this.Sprites[55], paramInt3, paramInt4, Graphics.TOP | Graphics.LEFT);
            }
            case 33 -> {
                if (isInWater) {
                    graphics.drawImage(this.Sprites[62], paramInt3, paramInt4, Graphics.TOP | Graphics.LEFT);
                    break;
                }
                graphics.drawImage(this.Sprites[58], paramInt3, paramInt4, Graphics.TOP | Graphics.LEFT);
            }
            case 34 -> {
                graphics.fillRect(paramInt3, paramInt4, 12, 12);
                graphics.drawImage(this.Sprites[65], paramInt3, paramInt4, Graphics.TOP | Graphics.LEFT);
            }
            case 35 -> {
                graphics.fillRect(paramInt3, paramInt4, 12, 12);
                graphics.drawImage(this.Sprites[64], paramInt3, paramInt4, Graphics.TOP | Graphics.LEFT);
            }
            case 36 -> {
                graphics.fillRect(paramInt3, paramInt4, 12, 12);
                graphics.drawImage(this.Sprites[63], paramInt3, paramInt4, Graphics.TOP | Graphics.LEFT);
            }
            case 37 -> {
                graphics.fillRect(paramInt3, paramInt4, 12, 12);
                graphics.drawImage(this.Sprites[66], paramInt3, paramInt4, Graphics.TOP | Graphics.LEFT);
            }
            case 39 -> {
                graphics.fillRect(paramInt3, paramInt4, 12, 12);
                graphics.drawImage(this.Sprites[50], paramInt3, paramInt4, Graphics.TOP | Graphics.LEFT);
            }
            case 40 -> {
                graphics.fillRect(paramInt3, paramInt4, 12, 12);
                graphics.drawImage(CreateTiles(this.Sprites[50], ImageManipulation.ROTATE_270), paramInt3, paramInt4, Graphics.TOP | Graphics.LEFT);
            }
            case 41 -> {
                graphics.fillRect(paramInt3, paramInt4, 12, 12);
                graphics.drawImage(CreateTiles(this.Sprites[50], ImageManipulation.ROTATE_180), paramInt3, paramInt4, Graphics.TOP | Graphics.LEFT);
            }
            case 42 -> {
                graphics.fillRect(paramInt3, paramInt4, 12, 12);
                graphics.drawImage(CreateTiles(this.Sprites[50], ImageManipulation.ROTATE_90), paramInt3, paramInt4, Graphics.TOP | Graphics.LEFT);
            }
            case 43 -> {
                graphics.fillRect(paramInt3, paramInt4, 12, 12);
                graphics.drawImage(this.Sprites[51], paramInt3, paramInt4, Graphics.TOP | Graphics.LEFT);
            }
            case 44 -> {
                graphics.fillRect(paramInt3, paramInt4, 12, 12);
                graphics.drawImage(CreateTiles(this.Sprites[51], ImageManipulation.ROTATE_270), paramInt3, paramInt4, Graphics.TOP | Graphics.LEFT);
            }
            case 45 -> {
                graphics.fillRect(paramInt3, paramInt4, 12, 12);
                graphics.drawImage(CreateTiles(this.Sprites[51], ImageManipulation.ROTATE_180), paramInt3, paramInt4, Graphics.TOP | Graphics.LEFT);
            }
            case 46 -> {
                graphics.fillRect(paramInt3, paramInt4, 12, 12);
                graphics.drawImage(CreateTiles(this.Sprites[51], ImageManipulation.ROTATE_90), paramInt3, paramInt4, Graphics.TOP | Graphics.LEFT);
            }
            case 47 -> graphics.drawImage(this.Sprites[52], paramInt3, paramInt4, Graphics.TOP | Graphics.LEFT);
            case 48 ->
                    graphics.drawImage(CreateTiles(this.Sprites[52], ImageManipulation.ROTATE_270), paramInt3, paramInt4, Graphics.TOP | Graphics.LEFT);
            case 49 ->
                    graphics.drawImage(CreateTiles(this.Sprites[52], ImageManipulation.ROTATE_180), paramInt3, paramInt4, Graphics.TOP | Graphics.LEFT);
            case 50 ->
                    graphics.drawImage(CreateTiles(this.Sprites[52], ImageManipulation.ROTATE_90), paramInt3, paramInt4, Graphics.TOP | Graphics.LEFT);
            case 38 -> graphics.drawImage(this.Sprites[53], paramInt3, paramInt4, Graphics.TOP | Graphics.LEFT);
            case 51 -> graphics.drawImage(this.Sprites[54], paramInt3, paramInt4, Graphics.TOP | Graphics.LEFT);
            case 52 ->
                    graphics.drawImage(CreateTiles(this.Sprites[54], ImageManipulation.ROTATE_270), paramInt3, paramInt4, Graphics.TOP | Graphics.LEFT);
            case 53 ->
                    graphics.drawImage(CreateTiles(this.Sprites[54], ImageManipulation.ROTATE_180), paramInt3, paramInt4, Graphics.TOP | Graphics.LEFT);
            case 54 ->
                    graphics.drawImage(CreateTiles(this.Sprites[54], ImageManipulation.ROTATE_90), paramInt3, paramInt4, Graphics.TOP | Graphics.LEFT);
        }
    }

    public void CreateTiles(Graphics g, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        int i = (paramInt1 - paramInt3) / 12;
        int j = (paramInt2 - paramInt3) / 12;
        int k = (paramInt1 + paramInt3 - 1) / 12 + 1;
        int m = (paramInt2 + paramInt3 - 1) / 12 + 1;
        if (i < 0)
            i = 0;
        if (j < 0)
            j = 0;
        if (k > this.c)
            k = this.c;
        if (m > this.ai)
            m = this.ai;
        for (int n = i; n < k; n++) {
            for (int i1 = j; i1 < m; i1++) {
                int i2 = this.C[i1][n] & 0xFFFFFFBF;
                if (i2 >= 13 && i2 <= 28) {
                    int i3 = (n - this.l) * 12 + paramInt4;
                    int i4 = (i1 - this.k) * 12;
                    g.drawImage(this.Sprites[com.nokia.mid.appl.boun.d.b[i2 - 13]], i3, i4, Graphics.TOP | Graphics.LEFT);
                }
            }
        }
    }

    public void f() {
        for (byte b1 = 0; b1 < 13; b1++) {
            for (byte b2 = 0; b2 < 8; b2++)
                CreateTiles(this.l + b1, this.k + b2, b1 * 12, b2 * 12);
        }
    }

    public void i() {
        int i = this.l;
        int j = this.k;
        for (byte b1 = 0; b1 < 13; b1++) {
            if (b1 * 12 >= this.Z && i >= this.l)
                i = this.G - 13;
            for (byte b2 = 0; b2 < 8; b2++) {
                if ((this.C[j][i] & 0x80) != 0)
                    CreateTiles(i, j, b1 * 12, b2 * 12);
                j++;
            }
            j = this.k;
            i++;
        }
    }

    public void LoadSpriteSheet(int paramInt) {
        int i = this.G - 13;
        int j = this.G;
        int k = paramInt - 64;
        if (k < 0) {
            k = 0;
        } else if (k > (this.c + 1) * 12 - 156) {
            k = (this.c + 1) * 12 - 156;
        }
        while (k / 12 < i) {
            this.Z -= 12;
            int m = this.Z;
            this.G--;
            j--;
            i--;
            if (this.Z <= 0) {
                this.Z = 156;
                this.l -= 13;
            }
            for (byte b1 = 0; b1 < 8; b1++)
                CreateTiles(this.G - 13, this.k + b1, m, b1 * 12);
        }
        while ((k + 128) / 12 >= j) {
            if (this.Z >= 156) {
                this.Z = 0;
                this.l += 13;
            }
            int m = this.Z;
            this.Z += 12;
            this.G++;
            j++;
            i++;
            for (byte b1 = 0; b1 < 8; b1++)
                CreateTiles(this.l + m / 12, this.k + b1, m, b1 * 12);
        }
        this.v = this.l * 12 - k;
    }

    public int m() {
        return this.l * 12 - this.v;
    }

    public int g() {
        return this.l * 12 - this.v + 128;
    }

    public Image Mirror16x16Tile(Image paramImage) {
        Image image = DirectUtils.createImage(16, 16, 0);
        if (image == null)
            image = Image.createImage(16, 16);
        Graphics graphics = image.getGraphics();
        DirectGraphics directGraphics = DirectUtils.getDirectGraphics(graphics);
        graphics.drawImage(paramImage, -4, -4, Graphics.TOP | Graphics.LEFT);
        directGraphics.drawImage(paramImage, 8, -4, Graphics.TOP | Graphics.LEFT, DirectGraphics.FLIP_HORIZONTAL);
        directGraphics.drawImage(paramImage, -4, 8, Graphics.TOP | Graphics.LEFT, DirectGraphics.FLIP_VERTICAL);
        directGraphics.drawImage(paramImage, 8, 8, Graphics.TOP | Graphics.LEFT, DirectGraphics.ROTATE_180);
        return image;
    }

    public Image MirrorExitTile(Image paramImage) {
        Image image = Image.createImage(24, 48);
        Graphics graphics = image.getGraphics();
        graphics.setColor(BounceColors.SKY);
        graphics.fillRect(0, 0, 24, 48);
        graphics.setColor(0xfc9d9e);
        graphics.fillRect(4, 0, 16, 48);
        graphics.setColor(0xe33a3f);
        graphics.fillRect(6, 0, 10, 48);
        graphics.setColor(0xc2848e);
        graphics.fillRect(10, 0, 4, 48);
        graphics.drawImage(paramImage, 0, 0, Graphics.TOP | Graphics.LEFT);
        graphics.drawImage(CreateTiles(paramImage, ImageManipulation.FLIP_HORIZONTAL), 12, 0, Graphics.TOP | Graphics.LEFT);
        graphics.drawImage(CreateTiles(paramImage, ImageManipulation.FLIP_VERTICAL), 0, 12, Graphics.TOP | Graphics.LEFT);
        graphics.drawImage(CreateTiles(paramImage, ImageManipulation.FLIP_BOTH), 12, 12, Graphics.TOP | Graphics.LEFT);
        return image;
    }

    public void LoadSpriteSheet() {
        Image ss = CreateTiles("/icons/objects_nm.png");
        this.Sprites = new Image[67];
        this.Sprites[SpriteIDs.BRICK_WALL] = CreateTiles(ss, 1, 0); // Brick wall
        this.Sprites[SpriteIDs.RUBBER_WALL] = CreateTiles(ss, 1, 2); // Rubber wall
        this.Sprites[SpriteIDs.SPIKES_UP] = CreateTiles(ss, 0, 3, BounceColors.SKY_ALPHA); // Spikes Up
        this.Sprites[SpriteIDs.SPIKES_DOWN] = CreateTiles(this.Sprites[2], ImageManipulation.FLIP_VERTICAL); // Spikes Down
        this.Sprites[SpriteIDs.SPIKES_LEFT] = CreateTiles(this.Sprites[2], ImageManipulation.ROTATE_90); // Spikes Left
        this.Sprites[SpriteIDs.SPIKES_RIGHT] = CreateTiles(this.Sprites[2], ImageManipulation.ROTATE_270); // Spikes Right
        this.Sprites[SpriteIDs.SPIKES_WATER_UP] = CreateTiles(ss, 0, 3, BounceColors.WATER_ALPHA);
        this.Sprites[SpriteIDs.SPIKES_WATER_DOWN] = CreateTiles(this.Sprites[6], ImageManipulation.FLIP_VERTICAL);
        this.Sprites[SpriteIDs.SPIKES_WATER_LEFT] = CreateTiles(this.Sprites[6], ImageManipulation.ROTATE_90);
        this.Sprites[SpriteIDs.SPIKES_WATER_RIGHT] = CreateTiles(this.Sprites[6], ImageManipulation.ROTATE_270);
        this.Sprites[SpriteIDs.CRYSTAL] = CreateTiles(ss, 0, 4);
        this.Sprites[SpriteIDs.CRYSTAL_ACTIVE] = CreateTiles(ss, 3, 4);
        this.Sprites[SpriteIDs.EXIT] = MirrorExitTile(CreateTiles(ss, 2, 3));
        this.Sprites[14] = CreateTiles(ss, 0, 5);
        this.Sprites[13] = CreateTiles(this.Sprites[14], ImageManipulation.FLIP_VERTICAL);
        this.Sprites[15] = CreateTiles(this.Sprites[13], ImageManipulation.FLIP_HORIZONTAL);
        this.Sprites[16] = CreateTiles(this.Sprites[14], ImageManipulation.FLIP_HORIZONTAL);
        this.Sprites[18] = CreateTiles(ss, 1, 5);
        this.Sprites[17] = CreateTiles(this.Sprites[18], ImageManipulation.FLIP_VERTICAL);
        this.Sprites[19] = CreateTiles(this.Sprites[17], ImageManipulation.FLIP_HORIZONTAL);
        this.Sprites[20] = CreateTiles(this.Sprites[18], ImageManipulation.FLIP_HORIZONTAL);
        this.Sprites[22] = CreateTiles(ss, 2, 5);
        this.Sprites[21] = CreateTiles(this.Sprites[22], ImageManipulation.FLIP_VERTICAL);
        this.Sprites[23] = CreateTiles(this.Sprites[21], ImageManipulation.FLIP_HORIZONTAL);
        this.Sprites[24] = CreateTiles(this.Sprites[22], ImageManipulation.FLIP_HORIZONTAL);
        this.Sprites[26] = CreateTiles(ss, 3, 5);
        this.Sprites[25] = CreateTiles(this.Sprites[26], ImageManipulation.FLIP_VERTICAL);
        this.Sprites[27] = CreateTiles(this.Sprites[25], ImageManipulation.FLIP_HORIZONTAL);
        this.Sprites[28] = CreateTiles(this.Sprites[26], ImageManipulation.FLIP_HORIZONTAL);
        this.Sprites[29] = CreateTiles(this.Sprites[14], ImageManipulation.ROTATE_270);
        this.Sprites[30] = CreateTiles(this.Sprites[29], ImageManipulation.FLIP_VERTICAL);
        this.Sprites[31] = CreateTiles(this.Sprites[29], ImageManipulation.FLIP_HORIZONTAL);
        this.Sprites[32] = CreateTiles(this.Sprites[30], ImageManipulation.FLIP_HORIZONTAL);
        this.Sprites[33] = CreateTiles(this.Sprites[18], ImageManipulation.ROTATE_270);
        this.Sprites[34] = CreateTiles(this.Sprites[33], ImageManipulation.FLIP_VERTICAL);
        this.Sprites[35] = CreateTiles(this.Sprites[33], ImageManipulation.FLIP_HORIZONTAL);
        this.Sprites[36] = CreateTiles(this.Sprites[34], ImageManipulation.FLIP_HORIZONTAL);
        this.Sprites[37] = CreateTiles(this.Sprites[22], ImageManipulation.ROTATE_270);
        this.Sprites[38] = CreateTiles(this.Sprites[37], ImageManipulation.FLIP_VERTICAL);
        this.Sprites[39] = CreateTiles(this.Sprites[37], ImageManipulation.FLIP_HORIZONTAL);
        this.Sprites[40] = CreateTiles(this.Sprites[38], ImageManipulation.FLIP_HORIZONTAL);
        this.Sprites[41] = CreateTiles(this.Sprites[26], ImageManipulation.ROTATE_270);
        this.Sprites[42] = CreateTiles(this.Sprites[41], ImageManipulation.FLIP_VERTICAL);
        this.Sprites[43] = CreateTiles(this.Sprites[41], ImageManipulation.FLIP_HORIZONTAL);
        this.Sprites[44] = CreateTiles(this.Sprites[42], ImageManipulation.FLIP_HORIZONTAL);
        this.Sprites[45] = CreateTiles(ss, 3, 3);
        this.Sprites[46] = CreateTiles(ss, 1, 3);
        this.Sprites[SpriteIDs.BALL] = CreateTiles(ss, 2, 0);
        this.Sprites[SpriteIDs.POPPED_BALL] = CreateTiles(ss, 0, 1);
        this.Sprites[SpriteIDs.BIG_BALL] = Mirror16x16Tile(CreateTiles(ss, 3, 0));
        this.Sprites[50] = CreateTiles(ss, 3, 1);
        this.Sprites[51] = CreateTiles(ss, 2, 4);
        this.Sprites[52] = CreateTiles(ss, 3, 2);
        this.Sprites[53] = CreateTiles(ss, 1, 1);
        this.Sprites[54] = CreateTiles(ss, 2, 2);
        this.Sprites[55] = CreateTiles(ss, 0, 0, BounceColors.SKY_ALPHA);
        this.Sprites[56] = CreateTiles(this.Sprites[55], ImageManipulation.ROTATE_90);
        this.Sprites[57] = CreateTiles(this.Sprites[55], ImageManipulation.ROTATE_180);
        this.Sprites[58] = CreateTiles(this.Sprites[55], ImageManipulation.ROTATE_270);
        this.Sprites[59] = CreateTiles(ss, 0, 0, BounceColors.WATER_ALPHA);
        this.Sprites[60] = CreateTiles(this.Sprites[59], ImageManipulation.ROTATE_90);
        this.Sprites[61] = CreateTiles(this.Sprites[59], ImageManipulation.ROTATE_180);
        this.Sprites[62] = CreateTiles(this.Sprites[59], ImageManipulation.ROTATE_270);
        this.Sprites[63] = CreateTiles(ss, 0, 2);
        this.Sprites[64] = CreateTiles(this.Sprites[63], ImageManipulation.ROTATE_90);
        this.Sprites[65] = CreateTiles(this.Sprites[63], ImageManipulation.ROTATE_180);
        this.Sprites[66] = CreateTiles(this.Sprites[63], ImageManipulation.ROTATE_270);
        this.imageBall = CreateTiles(ss, 2, 1);
        this.imageHoop = CreateTiles(ss, 1, 4);
    }

    public void CreateTiles(f paramf) {
        paramf.spriteBall = this.Sprites[SpriteIDs.BALL];
        paramf.spritePoppedBall = this.Sprites[SpriteIDs.POPPED_BALL];
        paramf.spriteBigBall = this.Sprites[SpriteIDs.BIG_BALL];
    }

    public static Image CreateTiles(Image paramImage, int tileX, int tileY) {
        return CreateTiles(paramImage, tileX, tileY, 0);
    }

    public static Image CreateTiles(Image paramImage, int tileX, int tileY, int ARGB) {
        Image image = DirectUtils.createImage(12, 12, ARGB);
    /*if (image == null) {
      image = Image.createImage(12, 12);
      Graphics graphics1 = image.getGraphics();
      graphics1.setColor(ARGB);
      graphics1.fillRect(0, 0, 12, 12);
    }*/
        Graphics graphics = image.getGraphics();
        graphics.drawImage(paramImage, -tileX * 12, -tileY * 12, Graphics.TOP | Graphics.LEFT);
        return image;
    }

    public static Image CreateTiles(String path) {
        Image image = null;
        try {
            image = Image.createImage(path);
        } catch (IOException ignored) {
        }
        return image;
    }

    public void CreateTiles(int paramInt1, int paramInt2, Image paramImage) {
        this.al = paramInt1;
        this.u = paramInt2;
        this.o = paramImage;
        this.aa = Image.createImage(24, 24);
        this.b = 0;
        p();
        this.M = false;
    }

    public void p() {
        Graphics graphics = this.aa.getGraphics();
        graphics.drawImage(this.o, 0, -this.b, Graphics.TOP | Graphics.LEFT);
    }

    public void h() {
        this.b += 4;
        if (this.b >= 24) {
            this.b = 24;
            this.M = true;
        }
        p();
    }

    public abstract void Tick();

    public synchronized void StartGameTimer() {
        if (this.GameTimer != null)
            return;
        this.GameTimer = new BounceTimer(this, this);
    }

    public synchronized void StopGameTimer() {
        if (this.GameTimer == null)
            return;
        this.GameTimer.stop();
        this.GameTimer = null;
    }

    protected void GameTimerTick() {
        Tick();
    }
}


/* Location:              C:\Users\kuzme\Downloads\nokiabounc_jdifc8jb.jar!\com\nokia\mid\appl\boun\b.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */