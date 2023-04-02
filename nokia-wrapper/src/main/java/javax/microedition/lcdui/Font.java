package javax.microedition.lcdui;

public final class Font {
    public static final int STYLE_PLAIN = 0;
    public static final int STYLE_BOLD = 1;
    public static final int STYLE_ITALIC = 2;
    public static final int STYLE_UNDERLINED = 4;
    public static final int SIZE_SMALL = 8;
    public static final int SIZE_MEDIUM = 0;
    public static final int SIZE_LARGE = 16;
    public static final int FACE_SYSTEM = 0;
    public static final int FACE_MONOSPACE = 32;
    public static final int FACE_PROPORTIONAL = 64;
    public static final int FONT_STATIC_TEXT = 0;
    public static final int FONT_INPUT_TEXT = 1;
    private static final Font DEFAULT_FONT = new Font(0, 0, 0);
    private java.awt.Font impl;

    public static Font getDefaultFont() {
        return DEFAULT_FONT;
    }

    public static Font getFont(int face, int style, int size) {
        return new Font(face, style, size);
    }

    Font(int face, int style, int size) {
        if (size != 8 && size != 0 && size != 16) {
            throw new IllegalArgumentException();
        }
        if ((style & (-8)) != 0) {
            throw new IllegalArgumentException();
        }
        if (face != 0 && face != 32 && face != 64) {
            throw new IllegalArgumentException();
        }

        this.impl = new java.awt.Font(null, style, size);
    }

    public java.awt.Font getImpl() {
        return this.impl;
    }
}
