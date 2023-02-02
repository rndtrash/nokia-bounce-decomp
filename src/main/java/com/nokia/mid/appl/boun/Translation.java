package com.nokia.mid.appl.boun;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Translation {
    public static final int BOUNCE = 0;
    public static final int MORE_INSTRUCTIONS = 1;
    public static final int BACK = 2;
    public static final int CONGRATS = 3;
    public static final int CONTINUE = 4;
    public static final int EXIT = 5;
    public static final int GAME_OVER = 6;
    public static final int HIGH_SCORE = 7;
    public static final int INSTRUCTIONS = 8;
    public static final int LEVEL = 9;
    public static final int LEVEL_COMPLETED = 10;
    public static final int NEW_GAME = 11;
    public static final int NEW_HIGH_SCORE = 12;
    public static final int OK = 13;

    private static Translation instance = null;

    /**
     * Structure of the file:
     * <p>
     * short offsets[14] -- offsets of individual strings
     * <p>
     * Each string is:
     * <p>
     * short length;
     * char *string;
     * <p>
     * Where string is MUTF-8 encoded
     */
    private static DataInputStream tStream = null;

    public static final String locale = System.getProperty("microedition.locale");

    private static String sprintf_translated(String input, String replace, String replacement) {
        int i = input.indexOf(replace);
        return (i >= 0) ? (input.substring(0, i) + replacement + input.substring(i + replace.length())) : input;
    }

    public static synchronized String sprintf_translated(int translationId) {
        return sprintf_translated(translationId, null);
    }

    public static synchronized String sprintf_translated(int translationId, String[] format) {
        try {
            if (instance == null)
                instance = new Translation();
            if (tStream == null) {
                InputStream translationFile = instance.getClass().getResourceAsStream("/lang." + locale);
                if (translationFile == null)
                    translationFile = instance.getClass().getResourceAsStream("/lang.xx");
                if (translationFile == null)
                    return "NoLang";
                tStream = new DataInputStream(translationFile);
                tStream.mark(512);
            }
            tStream.skipBytes(translationId * Short.BYTES);
            short stringOffset = tStream.readShort();
            tStream.skipBytes(stringOffset - translationId * 2 - Short.BYTES);
            String str = tStream.readUTF();
            try {
                tStream.reset();
            } catch (IOException iOException) {
                tStream.close();
                tStream = null;
            }
            if (format != null)
                if (format.length == 1) {
                    // Replace %U with the first format argument
                    str = sprintf_translated(str, "%U", format[0]);
                } else {
                    for (byte b = 0; b < format.length; b++)
                        str = sprintf_translated(str, "%" + b + "U", format[b]);
                }
            return str;
        } catch (IOException iOException) {
            return "Err";
        }
    }
}


/* Location:              C:\Users\kuzme\Downloads\nokiabounc_jdifc8jb.jar!\com\nokia\mid\appl\boun\c.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */