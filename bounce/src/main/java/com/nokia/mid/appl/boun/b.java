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

        public static final int THORNS_UP = 2;
        public static final int THORNS_DOWN = 3;
        public static final int THORNS_LEFT = 4;
        public static final int THORNS_RIGHT = 5;

        public static final int THORNS_WATER_UP = 6;
        public static final int THORNS_WATER_DOWN = 7;
        public static final int THORNS_WATER_LEFT = 8;
        public static final int THORNS_WATER_RIGHT = 9;

        /**
         * A.K.A. Checkpoint
         */
        public static final int CRYSTAL = 10;
        /**
         * A.K.A. Active checkpoint
         */
        public static final int CRYSTAL_ACTIVE = 11;

        public static final int EXIT = 12;

        public static final int HOOP_WIDE_LEFT_FLIP = 13;
        public static final int HOOP_WIDE_LEFT = 14;
        public static final int HOOP_WIDE_RIGHT_FLIP = 15;
        public static final int HOOP_WIDE_RIGHT = 16;

        public static final int HOOP_LEFT_FLIP = 17;
        public static final int HOOP_LEFT = 18;
        public static final int HOOP_RIGHT_FLIP = 19;
        public static final int HOOP_RIGHT = 20;

        public static final int HOOP_WIDE_LEFT_FLIP_PASSED = 21;
        public static final int HOOP_WIDE_LEFT_PASSED = 22;
        public static final int HOOP_WIDE_RIGHT_FLIP_PASSED = 23;
        public static final int HOOP_WIDE_RIGHT_PASSED = 24;

        public static final int HOOP_LEFT_FLIP_PASSED = 25;
        public static final int HOOP_LEFT_PASSED = 26;
        public static final int HOOP_RIGHT_FLIP_PASSED = 27;
        public static final int HOOP_RIGHT_PASSED = 28;

        /**
         * A.K.A. 1-Up
         */
        public static final int CRYSTAL_BALL = 45;

        public static final int DYN_THORN_QUARTER = 46;

        public static final int BALL = 47;
        public static final int POPPED_BALL = 48;
        public static final int BIG_BALL = 49;
        public static final int DEFLATER = 50;
        public static final int PUMPER = 51;
        public static final int POWER_UP_GRAVITY = 52;
        public static final int POWER_UP_SPEED = 53;
        public static final int POWER_UP_JUMP = 54;
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

    protected int TODO_ballInitialX;

    protected int TODO_ballInitialY;

    /**
     * Either 12 (small) or 16 (big)
     */
    public int BallSize;

    protected int exitX;

    protected int exitY;

    public short[][] LevelTiles;

    public int TODO_width;

    public int TODO_height;

    public int HoopsTotal;

    public int DynThornsCount;

    public Vec2S[] DynThornsBottomLeft;

    public Vec2S[] DynThornsTopRight;

    public Vec2S[] ae;

    public Vec2S[] w;

    public Image[] r;

    public Graphics[] an;

    public Image spriteDynThorn;

    public Image imageBall;

    public Image imageHoop;

    public int tileX;

    public int tileY;

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
        this.LevelTiles = null;
    }

    public void LoadLevelId(int levelId) {
        InputStream levelStream = null;
        DataInputStream levelDIS = null;
        this.d = false;
        String levelName = "";
        String[] levelIdFormat = new String[1];
        levelIdFormat[0] = Integer.valueOf(this.level).toString();
        this.LevelString = Translation.sprintf_translated(Translation.LEVEL, levelIdFormat);
        this.LevelCompletedString = Translation.sprintf_translated(Translation.LEVEL_COMPLETED, levelIdFormat);
        levelIdFormat[0] = null;
        levelIdFormat = null;
        if (levelId < 10) {
            levelName = "00" + levelId;
        } else if (levelId < 100) {
            levelName = "0" + levelId;
        }
        try {
            levelStream = getClass().getResourceAsStream("/levels/J2MElvl." + levelName);
            levelDIS = new DataInputStream(levelStream);
            this.TODO_ballInitialX = levelDIS.read();
            this.TODO_ballInitialY = levelDIS.read();
            int isBigBall = levelDIS.read();
            if (isBigBall == 0) {
                this.BallSize = 12;
            } else {
                this.BallSize = 16;
            }
            this.exitX = levelDIS.read();
            this.exitY = levelDIS.read();
            CreateTiles(this.exitX, this.exitY, this.Sprites[SpriteIDs.EXIT]);
            this.HoopsTotal = levelDIS.read();
            this.TODO_width = levelDIS.read();
            this.TODO_height = levelDIS.read();
            this.LevelTiles = new short[this.TODO_height][this.TODO_width];
            for (byte i = 0; i < this.TODO_height; i++) {
                for (byte j = 0; j < this.TODO_width; j++)
                    this.LevelTiles[i][j] = (short) levelDIS.read();
            }
            this.DynThornsCount = levelDIS.read();
            if (this.DynThornsCount != 0)
                LoadLevelDynThorns(levelDIS);
            levelDIS.close();
        } catch (IOException iOException) {
        }
    }

    public static Image ModifyImage(Image paramImage, int manipulation) {
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

    public void LoadLevelDynThorns(DataInputStream levelDIS) throws IOException {
        this.DynThornsBottomLeft = new Vec2S[this.DynThornsCount];
        this.DynThornsTopRight = new Vec2S[this.DynThornsCount];
        this.ae = new Vec2S[this.DynThornsCount];
        this.w = new Vec2S[this.DynThornsCount];
        this.r = new Image[this.DynThornsCount];
        this.an = new Graphics[this.DynThornsCount];
        for (byte thorn = 0; thorn < this.DynThornsCount; thorn++) {
            short x, y;

            x = (short) levelDIS.read();
            y = (short) levelDIS.read();
            this.DynThornsBottomLeft[thorn] = new Vec2S(x, y);

            x = (short) levelDIS.read();
            y = (short) levelDIS.read();
            this.DynThornsTopRight[thorn] = new Vec2S(x, y);

            x = (short) levelDIS.read();
            y = (short) levelDIS.read();
            this.ae[thorn].x = (short) levelDIS.read();
            this.ae[thorn].y = (short) levelDIS.read();

            x = (short) levelDIS.read();
            y = (short) levelDIS.read();
            this.w[thorn].x = x;
            this.w[thorn].y = y;
        }
        this.spriteDynThorn = Image.createImage(24, 24);
        Graphics graphics = this.spriteDynThorn.getGraphics();
        graphics.drawImage(this.Sprites[SpriteIDs.DYN_THORN_QUARTER], 0, 0, Graphics.TOP | Graphics.LEFT);
        graphics.drawImage(ModifyImage(this.Sprites[SpriteIDs.DYN_THORN_QUARTER], ImageManipulation.FLIP_HORIZONTAL), 12, 0, Graphics.TOP | Graphics.LEFT);
        graphics.drawImage(ModifyImage(this.Sprites[SpriteIDs.DYN_THORN_QUARTER], ImageManipulation.ROTATE_180), 12, 12, Graphics.TOP | Graphics.LEFT);
        graphics.drawImage(ModifyImage(this.Sprites[SpriteIDs.DYN_THORN_QUARTER], ImageManipulation.FLIP_VERTICAL), 0, 12, Graphics.TOP | Graphics.LEFT);
        graphics = null;
    }

    public void RunGarbageCollector() {
        for (byte i = 0; i < this.DynThornsCount; i++) {
            this.r[i] = null;
            this.an[i] = null;
        }
        this.r = null;
        this.an = null;
        this.LevelTiles = null;
        Runtime.getRuntime().gc();
    }

    public void UpdateDynThorns() {
        for (byte thorn = 0; thorn < this.DynThornsCount; thorn++) {
            short s1 = this.DynThornsBottomLeft[thorn].x;
            short s2 = this.DynThornsBottomLeft[thorn].y;
            short s3 = this.w[thorn].x;
            short s4 = this.w[thorn].y;
            this.w[thorn].x = (short) (this.w[thorn].x + this.ae[thorn].x);
            int n = (this.DynThornsTopRight[thorn].x - s1 - 2) * 12;
            int i1 = (this.DynThornsTopRight[thorn].y - s2 - 2) * 12;
            if (this.w[thorn].x < 0) {
                this.w[thorn].x = 0;
            } else if (this.w[thorn].x > n) {
                this.w[thorn].x = (short) n;
            }
            if (this.w[thorn].x == 0 || this.w[thorn].x == n)
                this.ae[thorn].x = (short) -this.ae[thorn].x;
            this.w[thorn].y = (short) (this.w[thorn].y + this.ae[thorn].y);
            if (this.w[thorn].y < 0) {
                this.w[thorn].y = 0;
            } else if (this.w[thorn].y > i1) {
                this.w[thorn].y = (short) i1;
            }
            if (this.w[thorn].y == 0 || this.w[thorn].y == i1)
                this.ae[thorn].y = (short) (this.ae[thorn].y * -1);
            short s5 = this.w[thorn].x;
            short s6 = this.w[thorn].y;
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
                    this.LevelTiles[s2 + i3][s1 + i2] = (short) (this.LevelTiles[s2 + i3][s1 + i2] | 0x80);
            }
        }
    }

    public int TestPointInsideDynThorns(int x, int y) {
        for (byte thorn = 0; thorn < this.DynThornsCount; thorn++) {
            if (x >= this.DynThornsBottomLeft[thorn].x && x < this.DynThornsTopRight[thorn].x
                    && y >= this.DynThornsBottomLeft[thorn].y && y < this.DynThornsTopRight[thorn].y)
                return thorn;
        }
        return -1;
    }

    public void CreateTiles(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        int k;
        Graphics graphics = this.E.getGraphics();
        if (paramInt1 < 0 || paramInt2 < 0 || paramInt1 >= this.TODO_width || paramInt2 >= this.TODO_height) {
            graphics.drawImage(this.Sprites[SpriteIDs.BRICK_WALL], paramInt3, paramInt4, Graphics.TOP | Graphics.LEFT);
            return;
        }
        this.LevelTiles[paramInt2][paramInt1] = (short) (this.LevelTiles[paramInt2][paramInt1] & (~0x80));
        int tileId = this.LevelTiles[paramInt2][paramInt1];
        boolean isInWater = (tileId & 0x40) != 0; // TODO: investigate this bit mask
        if (isInWater)
            tileId = tileId & (~0x40);
        graphics.setColor(isInWater ? BounceColors.WATER : BounceColors.SKY);
        int j;
        switch (tileId) {
            case TileIDs.BRICK_WALL ->
                    graphics.drawImage(this.Sprites[SpriteIDs.BRICK_WALL], paramInt3, paramInt4, Graphics.TOP | Graphics.LEFT);
            case TileIDs.EMPTY -> graphics.fillRect(paramInt3, paramInt4, 12, 12);
            case TileIDs.RUBBER_WALL ->
                    graphics.drawImage(this.Sprites[SpriteIDs.RUBBER_WALL], paramInt3, paramInt4, Graphics.TOP | Graphics.LEFT);
            case TileIDs.THORNS_UP -> {
                if (isInWater) {
                    graphics.drawImage(this.Sprites[SpriteIDs.THORNS_WATER_UP], paramInt3, paramInt4, Graphics.TOP | Graphics.LEFT);
                    break;
                }
                graphics.drawImage(this.Sprites[SpriteIDs.THORNS_UP], paramInt3, paramInt4, Graphics.TOP | Graphics.LEFT);
            }
            case TileIDs.THORNS_RIGHT -> {
                if (isInWater) {
                    graphics.drawImage(this.Sprites[SpriteIDs.THORNS_WATER_RIGHT], paramInt3, paramInt4, Graphics.TOP | Graphics.LEFT);
                    break;
                }
                graphics.drawImage(this.Sprites[SpriteIDs.THORNS_RIGHT], paramInt3, paramInt4, Graphics.TOP | Graphics.LEFT);
            }
            case TileIDs.THORNS_DOWN -> {
                if (isInWater) {
                    graphics.drawImage(this.Sprites[SpriteIDs.THORNS_WATER_DOWN], paramInt3, paramInt4, Graphics.TOP | Graphics.LEFT);
                    break;
                }
                graphics.drawImage(this.Sprites[SpriteIDs.THORNS_DOWN], paramInt3, paramInt4, Graphics.TOP | Graphics.LEFT);
            }
            case TileIDs.THORNS_LEFT -> {
                if (isInWater) {
                    graphics.drawImage(this.Sprites[SpriteIDs.THORNS_WATER_LEFT], paramInt3, paramInt4, Graphics.TOP | Graphics.LEFT);
                    break;
                }
                graphics.drawImage(this.Sprites[SpriteIDs.THORNS_LEFT], paramInt3, paramInt4, Graphics.TOP | Graphics.LEFT);
            }
            case TileIDs.CRYSTAL ->
                    graphics.drawImage(this.Sprites[SpriteIDs.CRYSTAL], paramInt3, paramInt4, Graphics.TOP | Graphics.LEFT);
            case TileIDs.CRYSTAL_ACTIVE ->
                    graphics.drawImage(this.Sprites[SpriteIDs.CRYSTAL_ACTIVE], paramInt3, paramInt4, Graphics.TOP | Graphics.LEFT);
            case 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28 -> {
                graphics.fillRect(paramInt3, paramInt4, 12, 12);
                graphics.drawImage(this.Sprites[com.nokia.mid.appl.boun.d.a[tileId - 13]], paramInt3, paramInt4, Graphics.TOP | Graphics.LEFT);
                graphics.drawImage(this.Sprites[com.nokia.mid.appl.boun.d.b[tileId - 13]], paramInt3, paramInt4, Graphics.TOP | Graphics.LEFT);
            }
            case 9 -> { // TODO: what's this tile???
                j = (paramInt1 - this.tileX) * 12;
                k = (paramInt2 - this.tileY) * 12;
                graphics.setClip(paramInt3, paramInt4, 12, 12);
                graphics.drawImage(this.aa, paramInt3 - j, paramInt4 - k, Graphics.TOP | Graphics.LEFT);
                graphics.setClip(0, 0, this.E.getWidth(), this.E.getHeight());
                this.z = true;
            }
            case 10 -> {
                j = TestPointInsideDynThorns(paramInt1, paramInt2);
                if (j != -1) {
                    k = (paramInt1 - this.DynThornsBottomLeft[j].x) * 12;
                    int m = (paramInt2 - this.DynThornsBottomLeft[j].y) * 12;
                    int n = this.w[j].x - k;
                    int i1 = this.w[j].y - m;
                    if ((n > -36 && n < 12) || (i1 > -36 && i1 < 12)) {
                        this.J.setColor(BounceColors.SKY);
                        this.J.fillRect(0, 0, 12, 12);
                        this.J.drawImage(this.spriteDynThorn, n, i1, Graphics.TOP | Graphics.LEFT);
                        graphics.drawImage(this.I, paramInt3, paramInt4, Graphics.TOP | Graphics.LEFT);
                        break;
                    }
                    graphics.setColor(BounceColors.SKY);
                    graphics.fillRect(paramInt3, paramInt4, 12, 12);
                }
            }
            case 29 ->
                    graphics.drawImage(this.Sprites[SpriteIDs.CRYSTAL_BALL], paramInt3, paramInt4, Graphics.TOP | Graphics.LEFT);
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
                graphics.drawImage(ModifyImage(this.Sprites[50], ImageManipulation.ROTATE_270), paramInt3, paramInt4, Graphics.TOP | Graphics.LEFT);
            }
            case 41 -> {
                graphics.fillRect(paramInt3, paramInt4, 12, 12);
                graphics.drawImage(ModifyImage(this.Sprites[50], ImageManipulation.ROTATE_180), paramInt3, paramInt4, Graphics.TOP | Graphics.LEFT);
            }
            case 42 -> {
                graphics.fillRect(paramInt3, paramInt4, 12, 12);
                graphics.drawImage(ModifyImage(this.Sprites[50], ImageManipulation.ROTATE_90), paramInt3, paramInt4, Graphics.TOP | Graphics.LEFT);
            }
            case TileIDs.PUMPER_UP -> {
                graphics.fillRect(paramInt3, paramInt4, 12, 12);
                graphics.drawImage(this.Sprites[SpriteIDs.PUMPER], paramInt3, paramInt4, Graphics.TOP | Graphics.LEFT);
            }
            case TileIDs.PUMPER_RIGHT -> {
                graphics.fillRect(paramInt3, paramInt4, 12, 12);
                graphics.drawImage(ModifyImage(this.Sprites[SpriteIDs.PUMPER], ImageManipulation.ROTATE_270), paramInt3, paramInt4, Graphics.TOP | Graphics.LEFT);
            }
            case TileIDs.PUMPER_DOWN -> {
                graphics.fillRect(paramInt3, paramInt4, 12, 12);
                graphics.drawImage(ModifyImage(this.Sprites[SpriteIDs.PUMPER], ImageManipulation.ROTATE_180), paramInt3, paramInt4, Graphics.TOP | Graphics.LEFT);
            }
            case TileIDs.PUMPER_LEFT -> {
                graphics.fillRect(paramInt3, paramInt4, 12, 12);
                graphics.drawImage(ModifyImage(this.Sprites[SpriteIDs.PUMPER], ImageManipulation.ROTATE_90), paramInt3, paramInt4, Graphics.TOP | Graphics.LEFT);
            }
            case TileIDs.POWER_UP_GRAVITY_UP -> graphics.drawImage(this.Sprites[SpriteIDs.POWER_UP_GRAVITY], paramInt3, paramInt4, Graphics.TOP | Graphics.LEFT);
            case TileIDs.POWER_UP_GRAVITY_RIGHT ->
                    graphics.drawImage(ModifyImage(this.Sprites[SpriteIDs.POWER_UP_GRAVITY], ImageManipulation.ROTATE_270), paramInt3, paramInt4, Graphics.TOP | Graphics.LEFT);
            case TileIDs.POWER_UP_GRAVITY_DOWN ->
                    graphics.drawImage(ModifyImage(this.Sprites[SpriteIDs.POWER_UP_GRAVITY], ImageManipulation.ROTATE_180), paramInt3, paramInt4, Graphics.TOP | Graphics.LEFT);
            case TileIDs.POWER_UP_GRAVITY_LEFT ->
                    graphics.drawImage(ModifyImage(this.Sprites[SpriteIDs.POWER_UP_GRAVITY], ImageManipulation.ROTATE_90), paramInt3, paramInt4, Graphics.TOP | Graphics.LEFT);
            case 38 -> graphics.drawImage(this.Sprites[53], paramInt3, paramInt4, Graphics.TOP | Graphics.LEFT);
            case 51 -> graphics.drawImage(this.Sprites[54], paramInt3, paramInt4, Graphics.TOP | Graphics.LEFT);
            case 52 ->
                    graphics.drawImage(ModifyImage(this.Sprites[54], ImageManipulation.ROTATE_270), paramInt3, paramInt4, Graphics.TOP | Graphics.LEFT);
            case 53 ->
                    graphics.drawImage(ModifyImage(this.Sprites[54], ImageManipulation.ROTATE_180), paramInt3, paramInt4, Graphics.TOP | Graphics.LEFT);
            case 54 ->
                    graphics.drawImage(ModifyImage(this.Sprites[54], ImageManipulation.ROTATE_90), paramInt3, paramInt4, Graphics.TOP | Graphics.LEFT);
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
        if (k > this.TODO_width)
            k = this.TODO_width;
        if (m > this.TODO_height)
            m = this.TODO_height;
        for (int n = i; n < k; n++) {
            for (int i1 = j; i1 < m; i1++) {
                int i2 = this.LevelTiles[i1][n] & (~0x40);
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
                if ((this.LevelTiles[j][i] & 0x80) != 0)
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
        } else if (k > (this.TODO_width + 1) * 12 - 156) {
            k = (this.TODO_width + 1) * 12 - 156;
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
        graphics.drawImage(ModifyImage(paramImage, ImageManipulation.FLIP_HORIZONTAL), 12, 0, Graphics.TOP | Graphics.LEFT);
        graphics.drawImage(ModifyImage(paramImage, ImageManipulation.FLIP_VERTICAL), 0, 12, Graphics.TOP | Graphics.LEFT);
        graphics.drawImage(ModifyImage(paramImage, ImageManipulation.FLIP_BOTH), 12, 12, Graphics.TOP | Graphics.LEFT);
        return image;
    }

    public void LoadSpriteSheet() {
        Image ss = CreateTiles("/icons/objects_nm.png");
        this.Sprites = new Image[67];
        this.Sprites[SpriteIDs.BRICK_WALL] = CreateTiles(ss, 1, 0); // Brick wall
        this.Sprites[SpriteIDs.RUBBER_WALL] = CreateTiles(ss, 1, 2); // Rubber wall
        this.Sprites[SpriteIDs.THORNS_UP] = CreateTiles(ss, 0, 3, BounceColors.SKY_ALPHA); // Thorns Up
        this.Sprites[SpriteIDs.THORNS_DOWN] = ModifyImage(this.Sprites[SpriteIDs.THORNS_UP], ImageManipulation.FLIP_VERTICAL); // Thorns Down
        this.Sprites[SpriteIDs.THORNS_LEFT] = ModifyImage(this.Sprites[SpriteIDs.THORNS_UP], ImageManipulation.ROTATE_90); // Thorns Left
        this.Sprites[SpriteIDs.THORNS_RIGHT] = ModifyImage(this.Sprites[SpriteIDs.THORNS_UP], ImageManipulation.ROTATE_270); // Thorns Right
        this.Sprites[SpriteIDs.THORNS_WATER_UP] = CreateTiles(ss, 0, 3, BounceColors.WATER_ALPHA);
        this.Sprites[SpriteIDs.THORNS_WATER_DOWN] = ModifyImage(this.Sprites[6], ImageManipulation.FLIP_VERTICAL);
        this.Sprites[SpriteIDs.THORNS_WATER_LEFT] = ModifyImage(this.Sprites[6], ImageManipulation.ROTATE_90);
        this.Sprites[SpriteIDs.THORNS_WATER_RIGHT] = ModifyImage(this.Sprites[6], ImageManipulation.ROTATE_270);
        this.Sprites[SpriteIDs.CRYSTAL] = CreateTiles(ss, 0, 4);
        this.Sprites[SpriteIDs.CRYSTAL_ACTIVE] = CreateTiles(ss, 3, 4);
        this.Sprites[SpriteIDs.EXIT] = MirrorExitTile(CreateTiles(ss, 2, 3));
        this.Sprites[SpriteIDs.HOOP_WIDE_LEFT] = CreateTiles(ss, 0, 5);
        this.Sprites[SpriteIDs.HOOP_WIDE_LEFT_FLIP] = ModifyImage(this.Sprites[SpriteIDs.HOOP_WIDE_LEFT], ImageManipulation.FLIP_VERTICAL);
        this.Sprites[SpriteIDs.HOOP_WIDE_RIGHT_FLIP] = ModifyImage(this.Sprites[SpriteIDs.HOOP_WIDE_LEFT_FLIP], ImageManipulation.FLIP_HORIZONTAL);
        this.Sprites[SpriteIDs.HOOP_WIDE_RIGHT] = ModifyImage(this.Sprites[SpriteIDs.HOOP_WIDE_LEFT], ImageManipulation.FLIP_HORIZONTAL);
        this.Sprites[SpriteIDs.HOOP_LEFT] = CreateTiles(ss, 1, 5);
        this.Sprites[SpriteIDs.HOOP_LEFT_FLIP] = ModifyImage(this.Sprites[SpriteIDs.HOOP_LEFT], ImageManipulation.FLIP_VERTICAL);
        this.Sprites[SpriteIDs.HOOP_RIGHT_FLIP] = ModifyImage(this.Sprites[SpriteIDs.HOOP_LEFT_FLIP], ImageManipulation.FLIP_HORIZONTAL);
        this.Sprites[SpriteIDs.HOOP_RIGHT] = ModifyImage(this.Sprites[SpriteIDs.HOOP_LEFT], ImageManipulation.FLIP_HORIZONTAL);
        this.Sprites[SpriteIDs.HOOP_WIDE_LEFT_PASSED] = CreateTiles(ss, 2, 5);
        this.Sprites[SpriteIDs.HOOP_WIDE_LEFT_FLIP_PASSED] = ModifyImage(this.Sprites[SpriteIDs.HOOP_WIDE_LEFT_PASSED], ImageManipulation.FLIP_VERTICAL);
        this.Sprites[SpriteIDs.HOOP_WIDE_RIGHT_FLIP_PASSED] = ModifyImage(this.Sprites[SpriteIDs.HOOP_WIDE_LEFT_FLIP_PASSED], ImageManipulation.FLIP_HORIZONTAL);
        this.Sprites[SpriteIDs.HOOP_WIDE_RIGHT_PASSED] = ModifyImage(this.Sprites[SpriteIDs.HOOP_WIDE_LEFT_PASSED], ImageManipulation.FLIP_HORIZONTAL);
        this.Sprites[SpriteIDs.HOOP_LEFT_PASSED] = CreateTiles(ss, 3, 5);
        this.Sprites[SpriteIDs.HOOP_LEFT_FLIP_PASSED] = ModifyImage(this.Sprites[SpriteIDs.HOOP_LEFT_PASSED], ImageManipulation.FLIP_VERTICAL);
        this.Sprites[SpriteIDs.HOOP_RIGHT_FLIP_PASSED] = ModifyImage(this.Sprites[SpriteIDs.HOOP_LEFT_FLIP_PASSED], ImageManipulation.FLIP_HORIZONTAL);
        this.Sprites[SpriteIDs.HOOP_RIGHT_PASSED] = ModifyImage(this.Sprites[SpriteIDs.HOOP_LEFT_PASSED], ImageManipulation.FLIP_HORIZONTAL);
        this.Sprites[29] = ModifyImage(this.Sprites[SpriteIDs.HOOP_WIDE_LEFT], ImageManipulation.ROTATE_270);
        this.Sprites[30] = ModifyImage(this.Sprites[29], ImageManipulation.FLIP_VERTICAL);
        this.Sprites[31] = ModifyImage(this.Sprites[29], ImageManipulation.FLIP_HORIZONTAL);
        this.Sprites[32] = ModifyImage(this.Sprites[30], ImageManipulation.FLIP_HORIZONTAL);
        this.Sprites[33] = ModifyImage(this.Sprites[SpriteIDs.HOOP_LEFT], ImageManipulation.ROTATE_270);
        this.Sprites[34] = ModifyImage(this.Sprites[33], ImageManipulation.FLIP_VERTICAL);
        this.Sprites[35] = ModifyImage(this.Sprites[33], ImageManipulation.FLIP_HORIZONTAL);
        this.Sprites[36] = ModifyImage(this.Sprites[34], ImageManipulation.FLIP_HORIZONTAL);
        this.Sprites[37] = ModifyImage(this.Sprites[SpriteIDs.HOOP_WIDE_LEFT_PASSED], ImageManipulation.ROTATE_270);
        this.Sprites[38] = ModifyImage(this.Sprites[37], ImageManipulation.FLIP_VERTICAL);
        this.Sprites[39] = ModifyImage(this.Sprites[37], ImageManipulation.FLIP_HORIZONTAL);
        this.Sprites[40] = ModifyImage(this.Sprites[38], ImageManipulation.FLIP_HORIZONTAL);
        this.Sprites[41] = ModifyImage(this.Sprites[SpriteIDs.HOOP_LEFT_PASSED], ImageManipulation.ROTATE_270);
        this.Sprites[42] = ModifyImage(this.Sprites[41], ImageManipulation.FLIP_VERTICAL);
        this.Sprites[43] = ModifyImage(this.Sprites[41], ImageManipulation.FLIP_HORIZONTAL);
        this.Sprites[44] = ModifyImage(this.Sprites[42], ImageManipulation.FLIP_HORIZONTAL);
        this.Sprites[SpriteIDs.CRYSTAL_BALL] = CreateTiles(ss, 3, 3);
        this.Sprites[SpriteIDs.DYN_THORN_QUARTER] = CreateTiles(ss, 1, 3);
        this.Sprites[SpriteIDs.BALL] = CreateTiles(ss, 2, 0);
        this.Sprites[SpriteIDs.POPPED_BALL] = CreateTiles(ss, 0, 1);
        this.Sprites[SpriteIDs.BIG_BALL] = Mirror16x16Tile(CreateTiles(ss, 3, 0));
        this.Sprites[SpriteIDs.DEFLATER] = CreateTiles(ss, 3, 1);
        this.Sprites[SpriteIDs.PUMPER] = CreateTiles(ss, 2, 4);
        this.Sprites[SpriteIDs.POWER_UP_GRAVITY] = CreateTiles(ss, 3, 2);
        this.Sprites[SpriteIDs.POWER_UP_SPEED] = CreateTiles(ss, 1, 1);
        this.Sprites[SpriteIDs.POWER_UP_JUMP] = CreateTiles(ss, 2, 2);
        this.Sprites[55] = CreateTiles(ss, 0, 0, BounceColors.SKY_ALPHA);
        this.Sprites[56] = ModifyImage(this.Sprites[55], ImageManipulation.ROTATE_90);
        this.Sprites[57] = ModifyImage(this.Sprites[55], ImageManipulation.ROTATE_180);
        this.Sprites[58] = ModifyImage(this.Sprites[55], ImageManipulation.ROTATE_270);
        this.Sprites[59] = CreateTiles(ss, 0, 0, BounceColors.WATER_ALPHA);
        this.Sprites[60] = ModifyImage(this.Sprites[59], ImageManipulation.ROTATE_90);
        this.Sprites[61] = ModifyImage(this.Sprites[59], ImageManipulation.ROTATE_180);
        this.Sprites[62] = ModifyImage(this.Sprites[59], ImageManipulation.ROTATE_270);
        this.Sprites[63] = CreateTiles(ss, 0, 2);
        this.Sprites[64] = ModifyImage(this.Sprites[63], ImageManipulation.ROTATE_90);
        this.Sprites[65] = ModifyImage(this.Sprites[63], ImageManipulation.ROTATE_180);
        this.Sprites[66] = ModifyImage(this.Sprites[63], ImageManipulation.ROTATE_270);
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
        if (image == null) {
            image = Image.createImage(12, 12);
            Graphics graphics1 = image.getGraphics();
            graphics1.setColor(ARGB);
            graphics1.fillRect(0, 0, 12, 12);
        }

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

    public void CreateTiles(int tileX, int tileY, Image paramImage) {
        this.tileX = tileX;
        this.tileY = tileY;
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