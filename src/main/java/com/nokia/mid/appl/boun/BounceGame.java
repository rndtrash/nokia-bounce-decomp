package com.nokia.mid.appl.boun;

import javax.microedition.lcdui.*;
import javax.microedition.rms.RecordStore;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class BounceGame implements CommandListener {
    static final long RECORD_STORE_MAGIC = -559038737L;

    public Bounce s;

    public Display display;

    public e v;

    public int K = 2;

    public int MaxLevels;

    public int HighScore;

    public boolean q;

    public int E;

    public byte J = 0;

    public byte RecordLives;

    public byte RecordHoopsScored;

    public byte RecordLevel;

    public byte A;

    public int RecordScore;

    public int I;

    public int H;

    public int a;

    public int g;

    public int y;

    public int M;

    public int e;

    public int b;

    public int w;

    public int z;

    public int n;

    public int p;

    public int[][] u;

    public int r;

    public Vec2S[] D;

    public short[][] l;

    public long LastRecordTimestampMillis;

    private Command i;

    private Command commandBack;

    private Command commandExit;

    private Command commandContinue;

    private List c;

    private List currentList;

    private Form form;

    private int N;

    private final String[] menuStrings = new String[4];

    public BounceGame(Bounce paramBounce) {
        this.s = paramBounce;
        LoadRecords();
        this.v = new e(this, 1);
        this.v.StartGameTimer();
        this.display = Display.getDisplay(this.s);
        this.display.setCurrent(this.v);
        LoadMenuStrings();
    }

    public synchronized void LoadMenuStrings() {
        this.menuStrings[0] = Translation.sprintf_translated(Translation.CONTINUE);
        this.menuStrings[1] = Translation.sprintf_translated(Translation.NEW_GAME);
        this.menuStrings[2] = Translation.sprintf_translated(Translation.HIGH_SCORE);
        this.menuStrings[3] = Translation.sprintf_translated(Translation.INSTRUCTIONS);
    }

    public synchronized void ShowMainMenu() {
        this.c = new List(Translation.sprintf_translated(Translation.BOUNCE), List.IMPLICIT);
        if (this.commandBack == null) {
            this.commandBack = new Command(Translation.sprintf_translated(Translation.BACK), Command.BACK, 1);
            this.commandExit = new Command(Translation.sprintf_translated(Translation.EXIT), Command.EXIT, 1);
        }
        if (this.K == 1 || this.J == 1 || this.J == 2)
            this.c.append(this.menuStrings[0], null);
        for (byte b = 1; b < this.menuStrings.length; b++)
            this.c.append(this.menuStrings[b], null);
        this.c.addCommand(this.commandExit);
        this.c.setCommandListener(this);
        if (this.v.SplashId != -1) {
            this.v.SplashId = -1;
            this.v.ImageSplash = null;
        }
        if (this.K == 1 || this.J == 1 || this.J == 2) {
            this.c.setSelectedIndex(0, true);
        } else {
            this.c.setSelectedIndex(this.N, true);
        }
        this.v.StopGameTimer();
        this.display.setCurrent(this.c);
    }

    public void LevelSelect() {
        String[] levelStrings = new String[this.MaxLevels];
        String[] levelStringFormat = new String[1];
        for (byte b = 0; b < this.MaxLevels; b++) {
            levelStringFormat[0] = String.valueOf(b + 1);
            levelStrings[b] = Translation.sprintf_translated(Translation.LEVEL, levelStringFormat);
        }
        this.currentList = new List(Translation.sprintf_translated(Translation.NEW_GAME), List.IMPLICIT, levelStrings, null);
        this.currentList.addCommand(this.commandBack);
        this.currentList.setCommandListener(this);
        this.display.setCurrent(this.currentList);
    }

    public void a(boolean paramBoolean, int paramInt) {
        if (paramBoolean) {
            this.q = false;
            this.v.a(paramInt, 0, 3);
        }
        this.v.StartGameTimer();
        this.v.aq.a();
        this.display.setCurrent(this.v);
        this.K = 1;
    }

    public void ShowHighScore() {
        this.form = new Form(Translation.sprintf_translated(Translation.HIGH_SCORE));
        this.form.append(String.valueOf(this.HighScore));
        this.form.addCommand(this.commandBack);
        this.form.setCommandListener(this);
        this.display.setCurrent(this.form);
    }

    public void ShowInstructions() {
        this.form = new Form(Translation.sprintf_translated(Translation.INSTRUCTIONS));
        String[] arrayOfString = {this.v.getKeyName(this.v.getKeyCode(2)), this.v.getKeyName(this.v.getKeyCode(5)), this.v.getKeyName(this.v.getKeyCode(1))};
        this.form.append(Translation.sprintf_translated(Translation.MORE_INSTRUCTIONS, arrayOfString));
        this.form.addCommand(this.commandBack);
        this.form.setCommandListener(this);
        this.display.setCurrent(this.form);
        this.form = null;
    }

    public void ShowGameEnd(boolean won) {
        this.v.StopGameTimer();
        if (this.i == null)
            this.i = new Command(Translation.sprintf_translated(Translation.OK), Command.OK, 1);
        this.form = new Form(Translation.sprintf_translated(Translation.GAME_OVER));
        if (won) {
            this.form.append(Translation.sprintf_translated(Translation.CONGRATS));
        } else {
            this.form.append(Translation.sprintf_translated(Translation.GAME_OVER));
        }
        this.form.append("\n\n");
        if (this.q) {
            this.form.append(Translation.sprintf_translated(Translation.NEW_HIGH_SCORE));
            this.form.append("\n\n");
        }
        this.form.append(String.valueOf(this.E));
        this.form.addCommand(this.i);
        this.form.setCommandListener(this);
        this.display.setCurrent(this.form);
        this.form = null;
    }

    public void ShowLevelComplete() {
        this.v.StopGameTimer();
        a(false, 0);
        this.K = 5;
        if (this.commandContinue == null)
            this.commandContinue = new Command(Translation.sprintf_translated(Translation.CONTINUE), Command.OK, 1);
        this.form = new Form("");
        this.form.append(this.v.LevelCompletedString);
        this.form.append("\n\n");
        this.form.append("" + this.E + "\n");
        this.form.addCommand(this.commandContinue);
        this.form.setCommandListener(this);
        this.display.setCurrent(this.form);
        this.form = null;
    }

    public void commandAction(Command cmd, Displayable displayable) {
        if (cmd == List.SELECT_COMMAND) {
            if (displayable == this.currentList) {
                a(true, this.currentList.getSelectedIndex() + 1);
            } else {
                String str = this.c.getString(this.c.getSelectedIndex());
                this.N = this.c.getSelectedIndex();
                if (str.equals(this.menuStrings[0])) {
                    if (this.K == 1) {
                        a(false, this.v.level);
                    } else if (this.J != 0) {
                        this.display.setCurrent(this.v);
                        if (this.J == 1) {
                            this.v.a(this.y, this.M);
                        } else {
                            this.v.a(this.RecordLevel, this.RecordScore, this.RecordLives);
                        }
                        this.u = null;
                        this.v.StartGameTimer();
                        this.K = 1;
                    }
                } else if (str.equals(this.menuStrings[1])) {
                    if (this.K != 4)
                        if (this.MaxLevels > 1) {
                            LevelSelect();
                        } else {
                            this.K = 4;
                            a(true, 1);
                        }
                } else if (str.equals(this.menuStrings[2])) {
                    ShowHighScore();
                } else if (str.equals(this.menuStrings[3])) {
                    ShowInstructions();
                } else if (str.equals("Read RMS")) {
                    LoadRecords();
                } else if (str.equals("Write RMS")) {
                    WriteToStore(1);
                    WriteToStore(2);
                    WriteToStore(3);
                }
            }
        } else if (cmd == this.commandBack || cmd == this.commandExit || cmd == this.i) {
            if (this.display.getCurrent() == this.c) {
                this.s.destroyApp(true);
                this.s.notifyDestroyed();
            } else {
                ShowMainMenu();
            }
        } else if (cmd == this.commandContinue) {
            this.K = 1;
            this.display.setCurrent(this.v);
        }
    }

    public void LoadRecords() {
        byte[] maxLevelsArray = new byte[1];
        byte[] highScoreArray = new byte[4];
        byte[] arrayOfByte3 = new byte[255];
        Object object = null;
        try {
            RecordStore recordStore = RecordStore.openRecordStore("bounceRMS", true);
            if (recordStore.getNumRecords() != 3) {
                recordStore.addRecord(maxLevelsArray, 0, maxLevelsArray.length);
                recordStore.addRecord(highScoreArray, 0, highScoreArray.length);
                recordStore.addRecord(arrayOfByte3, 0, arrayOfByte3.length);
            } else {
                maxLevelsArray = recordStore.getRecord(1);
                highScoreArray = recordStore.getRecord(2);
                arrayOfByte3 = recordStore.getRecord(3);
                ByteArrayInputStream recordBAIS = new ByteArrayInputStream(maxLevelsArray);
                DataInputStream recordDIS = new DataInputStream(recordBAIS);
                this.MaxLevels = recordDIS.readByte();
                recordBAIS = new ByteArrayInputStream(highScoreArray);
                recordDIS = new DataInputStream(recordBAIS);
                this.HighScore = recordDIS.readInt();
                recordBAIS = new ByteArrayInputStream(arrayOfByte3);
                recordDIS = new DataInputStream(recordBAIS);
                this.LastRecordTimestampMillis = recordDIS.readLong();
                this.J = recordDIS.readByte();
                this.RecordLives = recordDIS.readByte();
                this.RecordHoopsScored = recordDIS.readByte();
                this.RecordLevel = recordDIS.readByte();
                this.A = recordDIS.readByte();
                this.RecordScore = recordDIS.readInt();
                this.I = recordDIS.readInt();
                this.H = recordDIS.readInt();
                this.y = recordDIS.readInt();
                this.M = recordDIS.readInt();
                this.a = recordDIS.readInt();
                this.g = recordDIS.readInt();
                recordDIS.readInt();
                recordDIS.readInt();
                this.e = recordDIS.readInt();
                this.b = recordDIS.readInt();
                this.w = recordDIS.readInt();
                this.z = recordDIS.readInt();
                this.n = recordDIS.readInt();
                this.p = recordDIS.readByte();
                this.u = new int[this.p][3];
                for (byte b1 = 0; b1 < this.p; b1++) {
                    this.u[b1][0] = recordDIS.readShort();
                    this.u[b1][1] = recordDIS.readShort();
                    this.u[b1][2] = recordDIS.readByte();
                }
                this.r = recordDIS.readByte();
                this.D = new Vec2S[this.r];
                this.l = new short[this.r][2];
                for (byte b2 = 0; b2 < this.r; b2++) {
                    this.D[b2].x = recordDIS.readShort();
                    this.D[b2].y = recordDIS.readShort();
                    this.l[b2][0] = recordDIS.readShort();
                    this.l[b2][1] = recordDIS.readShort();
                }
                if (recordDIS.readLong() != RECORD_STORE_MAGIC)
                    this.J = 0;
            }
            recordStore.closeRecordStore();
        } catch (Exception exception) {
            this.J = 0;
        }
    }

    public void WriteToStore(int what) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        Object object = null;
        try {
            byte b1;
            int[][] arrayOfInt;
            byte b2;
            byte b3;
            byte b4;
            byte thorn;
            switch (what) {
                case 1 -> dataOutputStream.writeByte(this.MaxLevels);
                case 2 -> dataOutputStream.writeInt(this.HighScore);
                case 3 -> {
                    if (this.v == null || this.v.aq == null)
                        return;
                    b1 = 0;
                    if (this.K == 1) {
                        b1 = 1;
                    } else if (this.K == 5) {
                        b1 = 2;
                    }
                    dataOutputStream.writeLong(System.currentTimeMillis());
                    dataOutputStream.writeByte(b1);
                    dataOutputStream.writeByte(this.v.lives);
                    dataOutputStream.writeByte(this.v.HoopsScored);
                    dataOutputStream.writeByte(this.v.level);
                    dataOutputStream.writeByte(this.v.aq.ballSize);
                    dataOutputStream.writeInt(this.v.score);
                    dataOutputStream.writeInt(this.v.l);
                    dataOutputStream.writeInt(this.v.k);
                    dataOutputStream.writeInt(this.v.aq.s);
                    dataOutputStream.writeInt(this.v.aq.r);
                    dataOutputStream.writeInt(this.v.aq.l);
                    dataOutputStream.writeInt(this.v.aq.o);
                    dataOutputStream.writeInt(0);
                    dataOutputStream.writeInt(0);
                    dataOutputStream.writeInt(this.v.aq.d);
                    dataOutputStream.writeInt(this.v.aq.c);
                    dataOutputStream.writeInt(this.v.aq.TODO_somePowerUp1);
                    dataOutputStream.writeInt(this.v.aq.powerUpGravity);
                    dataOutputStream.writeInt(this.v.aq.TODO_somePowerUp3);
                    arrayOfInt = new int[50][3];
                    b2 = 0;
                    for (b3 = 0; b3 < this.v.TODO_height; b3++) {
                        for (byte b = 0; b < this.v.TODO_width; b++) {
                            byte b6 = (byte) (this.v.LevelTiles[b3][b] & 0xFF7F & (~0x40));
                            if (b6 == 7 || b6 == 29 || b6 == 13 || b6 == 14 || b6 == 21 || b6 == 22 || b6 == 15 || b6 == 16 || b6 == 23 || b6 == 24) {
                                arrayOfInt[b2][0] = b3;
                                arrayOfInt[b2][1] = b;
                                arrayOfInt[b2][2] = b6;
                                b2++;
                            }
                        }
                    }
                    dataOutputStream.writeByte(b2);
                    for (b4 = 0; b4 < b2; b4++) {
                        dataOutputStream.writeShort(arrayOfInt[b4][0]);
                        dataOutputStream.writeShort(arrayOfInt[b4][1]);
                        dataOutputStream.writeByte(arrayOfInt[b4][2]);
                    }
                    arrayOfInt = null;
                    dataOutputStream.writeByte(this.v.DynThornsCount);
                    for (thorn = 0; thorn < this.v.DynThornsCount; thorn++) {
                        dataOutputStream.writeShort(this.v.w[thorn].x);
                        dataOutputStream.writeShort(this.v.w[thorn].y);
                        dataOutputStream.writeShort(this.v.ae[thorn].x);
                        dataOutputStream.writeShort(this.v.ae[thorn].y);
                    }
                    dataOutputStream.writeLong(RECORD_STORE_MAGIC);
                }
            }
            RecordStore recordStore = RecordStore.openRecordStore("bounceRMS", true);
            recordStore.setRecord(what, byteArrayOutputStream.toByteArray(), 0, byteArrayOutputStream.size());
            recordStore.closeRecordStore();
        } catch (Exception exception) {
        }
    }

    public void WriteToStore() {
        if (this.v.level > this.MaxLevels) {
            this.MaxLevels = Math.min(this.v.level, 11);
            WriteToStore(1);
        }
        if (this.v.score > this.HighScore) {
            this.HighScore = this.v.score;
            this.q = true;
            WriteToStore(2);
        }
        this.E = this.v.score;
    }

    public void TODO_ShowInstructions(boolean paramBoolean) {
        this.K = 3;
        this.J = 0;
        this.v.H = false;
        ShowGameEnd(paramBoolean);
    }
}


/* Location:              C:\Users\kuzme\Downloads\nokiabounc_jdifc8jb.jar!\com\nokia\mid\appl\boun\a.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */