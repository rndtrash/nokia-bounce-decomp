package com.nokia.mid.sound;

public class Sound {
    public static final int SOUND_PLAYING = 0;
    public static final int SOUND_STOPPED = 1;
    public static final int SOUND_UNINITIALIZED = 3;
    public static final int FORMAT_TONE = 1;
    public static final int FORMAT_WAV = 5;
    private static final int SUCCESSFUL_OPERATION = 15;
    private static final int FAIL_OPERATION = 16;
    private static final int DEFAULT_VOLUME = -1;
    private static final int TONE_VOLUME_NOT_GIVEN = 255;
    private static final int FREQUENCY_LOW = 0;
    private static final int FREQUENCY_HIGH = 7692;
    private static final int MAX_DURATION = 2000;
    private static final int RET_BAD_PHONE_STATE = 0;
    private static final int RET_BAD_STATE = 1;
    private static final int RET_BAD_SEQUENCE = 2;
    private static final int RET_PLAY_FAIL = 3;
    private static final int RET_OK = 4;
    private static final int RET_NOT_READY = 5;
    private static final int RET_OUT_OF_MEMORY = 6;
    private static final int RET_COULD_NOT_SEND = 7;
    private static final int TYPE_BUZZER = 8;
    private static final int TYPE_TONE = 9;
    private static final int TYPE_ALERT = 10;
    private static final int ALERT_ALARM = 0;
    private static final int ALERT_CONFIRMATION = 1;
    private static final int ALERT_ERROR = 2;
    private static final int ALERT_INFO = 3;
    private static final int ALERT_WARNING = 4;
    private int status;
    private boolean already_played;

    public Sound(int freq, long duration) {
        System.err.println("Sound(freq: " + freq + ", duration:" + duration + "): not implemented");

        this.status = 3;

        /*int retVal = initToneBuzzer(freq, duration);
        if (retVal == 15 && !is_hw_initialized) {
            is_hw_initialized = true;
            init0();
        }*/

        this.already_played = false;
    }

    public Sound(byte[] data, int type) {
        System.err.println("Sound(data: " + data + ", type:" + type + "): not implemented");

        this.status = 3;

        /*int retVal = initRingingTone(data, type);
        if (retVal == 15 && !is_hw_initialized) {
            is_hw_initialized = true;
            init0();
        }*/

        this.already_played = false;
    }

    public void play(int loop) {
        System.err.println("play(" + loop + "): not implemented");
    }
}
