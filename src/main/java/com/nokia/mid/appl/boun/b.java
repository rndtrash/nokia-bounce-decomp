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
    public enum ImageManipulation {
        FLIP_HORIZONTAL, // 0
        FLIP_VERTICAL, // 1
        FLIP_BOTH, // 2
        ROTATE_90, // 3
        ROTATE_180, // 4
        ROTATE_270, // 5
    }

    protected int l;

    protected int k;

    protected int G;

    protected int Z;

    protected int v;

    protected boolean z;

    protected Image E;

    private Image[] Q;

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

    public int ringsTotal;

    public int B;

    public short[][] P;

    public short[][] O;

    public short[][] ae;

    public short[][] w;

    public Image[] r;

    public Graphics[] an;

    public Image L;

    public Image imageBall;

    public Image imageRing;

    public int al;

    public int u;

    public Image aa;

    public Image o;

    public int b;

    public boolean M;

    protected int displayWidth = 0;

    protected int displayHeight = 0;

    protected Display display;

    public BounceTimer ab = null;

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
            CreateTiles(this.W, this.V, this.Q[12]);
            this.ringsTotal = levelDIS.read();
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

    public static Image CreateTiles(Image paramImage, ImageManipulation manipulation) {
        Image image = DirectUtils.createImage(paramImage.getWidth(), paramImage.getHeight(), 0);
        if (image == null)
            image = Image.createImage(paramImage.getWidth(), paramImage.getHeight());
        Graphics graphics = image.getGraphics();
        DirectGraphics directGraphics = DirectUtils.getDirectGraphics(graphics);
        switch (manipulation) {
            case FLIP_HORIZONTAL -> {
                directGraphics.drawImage(paramImage, 0, 0, Graphics.TOP | Graphics.LEFT, DirectGraphics.FLIP_HORIZONTAL);
                return image;
            }
            case FLIP_VERTICAL -> {
                directGraphics.drawImage(paramImage, 0, 0, Graphics.TOP | Graphics.LEFT, DirectGraphics.FLIP_VERTICAL);
                return image;
            }
            case FLIP_BOTH -> {
                directGraphics.drawImage(paramImage, 0, 0, Graphics.TOP | Graphics.LEFT, DirectGraphics.FLIP_HORIZONTAL | DirectGraphics.FLIP_VERTICAL);
                return image;
            }
            case ROTATE_90 -> {
                directGraphics.drawImage(paramImage, 0, 0, Graphics.TOP | Graphics.LEFT, DirectGraphics.ROTATE_90);
                return image;
            }
            case ROTATE_180 -> {
                directGraphics.drawImage(paramImage, 0, 0, Graphics.TOP | Graphics.LEFT, DirectGraphics.ROTATE_180);
                return image;
            }
            case ROTATE_270 -> {
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
        graphics.drawImage(this.Q[46], 0, 0, 20);
        graphics.drawImage(CreateTiles(this.Q[46], ImageManipulation.FLIP_HORIZONTAL), 12, 0, 20);
        graphics.drawImage(CreateTiles(this.Q[46], ImageManipulation.ROTATE_180), 12, 12, 20);
        graphics.drawImage(CreateTiles(this.Q[46], ImageManipulation.FLIP_VERTICAL), 0, 12, 20);
        graphics = null;
    }

    public void r() {
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
            graphics.drawImage(this.Q[0], paramInt3, paramInt4, 20);
            return;
        }
        this.C[paramInt2][paramInt1] = (short) (this.C[paramInt2][paramInt1] & 0xFF7F);
        int i = this.C[paramInt2][paramInt1];
        boolean bool = ((i & 0x40) != 0) ? true : false;
        if (bool)
            i = i & 0xFFFFFFBF;
        graphics.setColor(bool ? 1073328 : 11591920);
        switch (i) {
            case 1 -> graphics.drawImage(this.Q[0], paramInt3, paramInt4, 20);
            case 0 -> graphics.fillRect(paramInt3, paramInt4, 12, 12);
            case 2 -> graphics.drawImage(this.Q[1], paramInt3, paramInt4, 20);
            case 3 -> {
                if (bool) {
                    graphics.drawImage(this.Q[6], paramInt3, paramInt4, 20);
                    break;
                }
                graphics.drawImage(this.Q[2], paramInt3, paramInt4, 20);
            }
            case 4 -> {
                if (bool) {
                    graphics.drawImage(this.Q[9], paramInt3, paramInt4, 20);
                    break;
                }
                graphics.drawImage(this.Q[5], paramInt3, paramInt4, 20);
            }
            case 5 -> {
                if (bool) {
                    graphics.drawImage(this.Q[7], paramInt3, paramInt4, 20);
                    break;
                }
                graphics.drawImage(this.Q[3], paramInt3, paramInt4, 20);
            }
            case 6 -> {
                if (bool) {
                    graphics.drawImage(this.Q[8], paramInt3, paramInt4, 20);
                    break;
                }
                graphics.drawImage(this.Q[4], paramInt3, paramInt4, 20);
            }
            case 7 -> graphics.drawImage(this.Q[10], paramInt3, paramInt4, 20);
            case 8 -> graphics.drawImage(this.Q[11], paramInt3, paramInt4, 20);
            case 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28 -> {
                graphics.fillRect(paramInt3, paramInt4, 12, 12);
                graphics.drawImage(this.Q[com.nokia.mid.appl.boun.d.a[i - 13]], paramInt3, paramInt4, 20);
                graphics.drawImage(this.Q[com.nokia.mid.appl.boun.d.b[i - 13]], paramInt3, paramInt4, 20);
            }
            case 9 -> {
                j = (paramInt1 - this.al) * 12;
                k = (paramInt2 - this.u) * 12;
                graphics.setClip(paramInt3, paramInt4, 12, 12);
                graphics.drawImage(this.aa, paramInt3 - j, paramInt4 - k, 20);
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
                        this.J.setColor(11591920);
                        this.J.fillRect(0, 0, 12, 12);
                        this.J.drawImage(this.L, n, i1, 20);
                        graphics.drawImage(this.I, paramInt3, paramInt4, 20);
                        break;
                    }
                    graphics.setColor(11591920);
                    graphics.fillRect(paramInt3, paramInt4, 12, 12);
                }
            }
            case 29 -> graphics.drawImage(this.Q[45], paramInt3, paramInt4, 20);
            case 30 -> {
                if (bool) {
                    graphics.drawImage(this.Q[61], paramInt3, paramInt4, 20);
                    break;
                }
                graphics.drawImage(this.Q[57], paramInt3, paramInt4, 20);
            }
            case 31 -> {
                if (bool) {
                    graphics.drawImage(this.Q[60], paramInt3, paramInt4, 20);
                    break;
                }
                graphics.drawImage(this.Q[56], paramInt3, paramInt4, 20);
            }
            case 32 -> {
                if (bool) {
                    graphics.drawImage(this.Q[59], paramInt3, paramInt4, 20);
                    break;
                }
                graphics.drawImage(this.Q[55], paramInt3, paramInt4, 20);
            }
            case 33 -> {
                if (bool) {
                    graphics.drawImage(this.Q[62], paramInt3, paramInt4, 20);
                    break;
                }
                graphics.drawImage(this.Q[58], paramInt3, paramInt4, 20);
            }
            case 34 -> {
                graphics.fillRect(paramInt3, paramInt4, 12, 12);
                graphics.drawImage(this.Q[65], paramInt3, paramInt4, 20);
            }
            case 35 -> {
                graphics.fillRect(paramInt3, paramInt4, 12, 12);
                graphics.drawImage(this.Q[64], paramInt3, paramInt4, 20);
            }
            case 36 -> {
                graphics.fillRect(paramInt3, paramInt4, 12, 12);
                graphics.drawImage(this.Q[63], paramInt3, paramInt4, 20);
            }
            case 37 -> {
                graphics.fillRect(paramInt3, paramInt4, 12, 12);
                graphics.drawImage(this.Q[66], paramInt3, paramInt4, 20);
            }
            case 39 -> {
                graphics.fillRect(paramInt3, paramInt4, 12, 12);
                graphics.drawImage(this.Q[50], paramInt3, paramInt4, 20);
            }
            case 40 -> {
                graphics.fillRect(paramInt3, paramInt4, 12, 12);
                graphics.drawImage(CreateTiles(this.Q[50], ImageManipulation.ROTATE_270), paramInt3, paramInt4, 20);
            }
            case 41 -> {
                graphics.fillRect(paramInt3, paramInt4, 12, 12);
                graphics.drawImage(CreateTiles(this.Q[50], ImageManipulation.ROTATE_180), paramInt3, paramInt4, 20);
            }
            case 42 -> {
                graphics.fillRect(paramInt3, paramInt4, 12, 12);
                graphics.drawImage(CreateTiles(this.Q[50], ImageManipulation.ROTATE_90), paramInt3, paramInt4, 20);
            }
            case 43 -> {
                graphics.fillRect(paramInt3, paramInt4, 12, 12);
                graphics.drawImage(this.Q[51], paramInt3, paramInt4, 20);
            }
            case 44 -> {
                graphics.fillRect(paramInt3, paramInt4, 12, 12);
                graphics.drawImage(CreateTiles(this.Q[51], ImageManipulation.ROTATE_270), paramInt3, paramInt4, 20);
            }
            case 45 -> {
                graphics.fillRect(paramInt3, paramInt4, 12, 12);
                graphics.drawImage(CreateTiles(this.Q[51], ImageManipulation.ROTATE_180), paramInt3, paramInt4, 20);
            }
            case 46 -> {
                graphics.fillRect(paramInt3, paramInt4, 12, 12);
                graphics.drawImage(CreateTiles(this.Q[51], ImageManipulation.ROTATE_90), paramInt3, paramInt4, 20);
            }
            case 47 -> graphics.drawImage(this.Q[52], paramInt3, paramInt4, 20);
            case 48 ->
                    graphics.drawImage(CreateTiles(this.Q[52], ImageManipulation.ROTATE_270), paramInt3, paramInt4, 20);
            case 49 ->
                    graphics.drawImage(CreateTiles(this.Q[52], ImageManipulation.ROTATE_180), paramInt3, paramInt4, 20);
            case 50 ->
                    graphics.drawImage(CreateTiles(this.Q[52], ImageManipulation.ROTATE_90), paramInt3, paramInt4, 20);
            case 38 -> graphics.drawImage(this.Q[53], paramInt3, paramInt4, 20);
            case 51 -> graphics.drawImage(this.Q[54], paramInt3, paramInt4, 20);
            case 52 ->
                    graphics.drawImage(CreateTiles(this.Q[54], ImageManipulation.ROTATE_270), paramInt3, paramInt4, 20);
            case 53 ->
                    graphics.drawImage(CreateTiles(this.Q[54], ImageManipulation.ROTATE_180), paramInt3, paramInt4, 20);
            case 54 ->
                    graphics.drawImage(CreateTiles(this.Q[54], ImageManipulation.ROTATE_90), paramInt3, paramInt4, 20);
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
                    g.drawImage(this.Q[com.nokia.mid.appl.boun.d.b[i2 - 13]], i3, i4, 20);
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

    public Image CreateTiles(Image paramImage) {
        Image image = DirectUtils.createImage(16, 16, 0);
        if (image == null)
            image = Image.createImage(16, 16);
        Graphics graphics = image.getGraphics();
        DirectGraphics directGraphics = DirectUtils.getDirectGraphics(graphics);
        graphics.drawImage(paramImage, -4, -4, 20);
        directGraphics.drawImage(paramImage, 8, -4, Graphics.TOP | Graphics.LEFT, DirectGraphics.FLIP_HORIZONTAL);
        directGraphics.drawImage(paramImage, -4, 8, Graphics.TOP | Graphics.LEFT, DirectGraphics.FLIP_VERTICAL);
        directGraphics.drawImage(paramImage, 8, 8, Graphics.TOP | Graphics.LEFT, DirectGraphics.ROTATE_180);
        return image;
    }

    public Image b(Image paramImage) {
        Image image = Image.createImage(24, 48);
        Graphics graphics = image.getGraphics();
        graphics.setColor(0xb0e0f0);
        graphics.fillRect(0, 0, 24, 48);
        graphics.setColor(0xfc9d9e);
        graphics.fillRect(4, 0, 16, 48);
        graphics.setColor(0xe33a3f);
        graphics.fillRect(6, 0, 10, 48);
        graphics.setColor(0xc2848e);
        graphics.fillRect(10, 0, 4, 48);
        graphics.drawImage(paramImage, 0, 0, Graphics.TOP | Graphics.LEFT);
        graphics.drawImage(CreateTiles(paramImage, ImageManipulation.FLIP_HORIZONTAL), 12, 0, 20);
        graphics.drawImage(CreateTiles(paramImage, ImageManipulation.FLIP_VERTICAL), 0, 12, 20);
        graphics.drawImage(CreateTiles(paramImage, ImageManipulation.FLIP_BOTH), 12, 12, 20);
        return image;
    }

    public void LoadSpriteSheet() {
        Image ss = CreateTiles("/icons/objects_nm.png");
        this.Q = new Image[67];
        this.Q[0] = CreateTiles(ss, 1, 0);
        this.Q[1] = CreateTiles(ss, 1, 2);
        this.Q[2] = CreateTiles(ss, 0, 3, -5185296);
        this.Q[3] = CreateTiles(this.Q[2], ImageManipulation.FLIP_VERTICAL);
        this.Q[4] = CreateTiles(this.Q[2], ImageManipulation.ROTATE_90);
        this.Q[5] = CreateTiles(this.Q[2], ImageManipulation.ROTATE_270);
        this.Q[6] = CreateTiles(ss, 0, 3, -15703888);
        this.Q[7] = CreateTiles(this.Q[6], ImageManipulation.FLIP_VERTICAL);
        this.Q[8] = CreateTiles(this.Q[6], ImageManipulation.ROTATE_90);
        this.Q[9] = CreateTiles(this.Q[6], ImageManipulation.ROTATE_270);
        this.Q[10] = CreateTiles(ss, 0, 4);
        this.Q[11] = CreateTiles(ss, 3, 4);
        this.Q[12] = b(CreateTiles(ss, 2, 3));
        this.Q[14] = CreateTiles(ss, 0, 5);
        this.Q[13] = CreateTiles(this.Q[14], ImageManipulation.FLIP_VERTICAL);
        this.Q[15] = CreateTiles(this.Q[13], ImageManipulation.FLIP_HORIZONTAL);
        this.Q[16] = CreateTiles(this.Q[14], ImageManipulation.FLIP_HORIZONTAL);
        this.Q[18] = CreateTiles(ss, 1, 5);
        this.Q[17] = CreateTiles(this.Q[18], ImageManipulation.FLIP_VERTICAL);
        this.Q[19] = CreateTiles(this.Q[17], ImageManipulation.FLIP_HORIZONTAL);
        this.Q[20] = CreateTiles(this.Q[18], ImageManipulation.FLIP_HORIZONTAL);
        this.Q[22] = CreateTiles(ss, 2, 5);
        this.Q[21] = CreateTiles(this.Q[22], ImageManipulation.FLIP_VERTICAL);
        this.Q[23] = CreateTiles(this.Q[21], ImageManipulation.FLIP_HORIZONTAL);
        this.Q[24] = CreateTiles(this.Q[22], ImageManipulation.FLIP_HORIZONTAL);
        this.Q[26] = CreateTiles(ss, 3, 5);
        this.Q[25] = CreateTiles(this.Q[26], ImageManipulation.FLIP_VERTICAL);
        this.Q[27] = CreateTiles(this.Q[25], ImageManipulation.FLIP_HORIZONTAL);
        this.Q[28] = CreateTiles(this.Q[26], ImageManipulation.FLIP_HORIZONTAL);
        this.Q[29] = CreateTiles(this.Q[14], ImageManipulation.ROTATE_270);
        this.Q[30] = CreateTiles(this.Q[29], ImageManipulation.FLIP_VERTICAL);
        this.Q[31] = CreateTiles(this.Q[29], ImageManipulation.FLIP_HORIZONTAL);
        this.Q[32] = CreateTiles(this.Q[30], ImageManipulation.FLIP_HORIZONTAL);
        this.Q[33] = CreateTiles(this.Q[18], ImageManipulation.ROTATE_270);
        this.Q[34] = CreateTiles(this.Q[33], ImageManipulation.FLIP_VERTICAL);
        this.Q[35] = CreateTiles(this.Q[33], ImageManipulation.FLIP_HORIZONTAL);
        this.Q[36] = CreateTiles(this.Q[34], ImageManipulation.FLIP_HORIZONTAL);
        this.Q[37] = CreateTiles(this.Q[22], ImageManipulation.ROTATE_270);
        this.Q[38] = CreateTiles(this.Q[37], ImageManipulation.FLIP_VERTICAL);
        this.Q[39] = CreateTiles(this.Q[37], ImageManipulation.FLIP_HORIZONTAL);
        this.Q[40] = CreateTiles(this.Q[38], ImageManipulation.FLIP_HORIZONTAL);
        this.Q[41] = CreateTiles(this.Q[26], ImageManipulation.ROTATE_270);
        this.Q[42] = CreateTiles(this.Q[41], ImageManipulation.FLIP_VERTICAL);
        this.Q[43] = CreateTiles(this.Q[41], ImageManipulation.FLIP_HORIZONTAL);
        this.Q[44] = CreateTiles(this.Q[42], ImageManipulation.FLIP_HORIZONTAL);
        this.Q[45] = CreateTiles(ss, 3, 3);
        this.Q[46] = CreateTiles(ss, 1, 3);
        this.Q[47] = CreateTiles(ss, 2, 0);
        this.Q[48] = CreateTiles(ss, 0, 1);
        this.Q[49] = CreateTiles(CreateTiles(ss, 3, 0));
        this.Q[50] = CreateTiles(ss, 3, 1);
        this.Q[51] = CreateTiles(ss, 2, 4);
        this.Q[52] = CreateTiles(ss, 3, 2);
        this.Q[53] = CreateTiles(ss, 1, 1);
        this.Q[54] = CreateTiles(ss, 2, 2);
        this.Q[55] = CreateTiles(ss, 0, 0, -5185296);
        this.Q[56] = CreateTiles(this.Q[55], ImageManipulation.ROTATE_90);
        this.Q[57] = CreateTiles(this.Q[55], ImageManipulation.ROTATE_180);
        this.Q[58] = CreateTiles(this.Q[55], ImageManipulation.ROTATE_270);
        this.Q[59] = CreateTiles(ss, 0, 0, -15703888);
        this.Q[60] = CreateTiles(this.Q[59], ImageManipulation.ROTATE_90);
        this.Q[61] = CreateTiles(this.Q[59], ImageManipulation.ROTATE_180);
        this.Q[62] = CreateTiles(this.Q[59], ImageManipulation.ROTATE_270);
        this.Q[63] = CreateTiles(ss, 0, 2);
        this.Q[64] = CreateTiles(this.Q[63], ImageManipulation.ROTATE_90);
        this.Q[65] = CreateTiles(this.Q[63], ImageManipulation.ROTATE_180);
        this.Q[66] = CreateTiles(this.Q[63], ImageManipulation.ROTATE_270);
        this.imageBall = CreateTiles(ss, 2, 1);
        this.imageRing = CreateTiles(ss, 1, 4);
    }

    public void CreateTiles(f paramf) {
        paramf.A = this.Q[47];
        paramf.k = this.Q[48];
        paramf.B = this.Q[49];
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
        graphics.drawImage(this.o, 0, -this.b, 20);
    }

    public void h() {
        this.b += 4;
        if (this.b >= 24) {
            this.b = 24;
            this.M = true;
        }
        p();
    }

    public abstract void CreateTiles();

    public synchronized void d() {
        if (this.ab != null)
            return;
        this.ab = new BounceTimer(this, this);
    }

    public synchronized void j() {
        if (this.ab == null)
            return;
        this.ab.stop();
        this.ab = null;
    }

    protected void n() {
        CreateTiles();
    }
}


/* Location:              C:\Users\kuzme\Downloads\nokiabounc_jdifc8jb.jar!\com\nokia\mid\appl\boun\b.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */