package javax.microedition.lcdui;

/* JADX WARN: Classes with same name are omitted:
  nokia_s40.jar:javax/microedition/lcdui/Font.class
 */
/* loaded from: nokia5100.jar:javax/microedition/lcdui/Font.class */
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
    private static final Font DEFAULT_FONT = new Font(0, 0, 0);
    private com.nokia.mid.impl.isa.ui.gdi.Font impl;

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
        this.impl = new com.nokia.mid.impl.isa.ui.gdi.Font(size, style, true);
    }

    public int getStyle() {
        return this.impl.getMIDPStyle();
    }

    public int getSize() {
        return this.impl.getMIDPSize();
    }

    public int getFace() {
        return 0;
    }

    public boolean isPlain() {
        return (getStyle() & 5) == 0;
    }

    public boolean isBold() {
        return (getStyle() & 1) != 0;
    }

    public boolean isItalic() {
        return false;
    }

    public boolean isUnderlined() {
        return (getStyle() & 4) != 0;
    }

    public int getHeight() {
        return this.impl.getAbove() + this.impl.getDefaultCharHeight() + this.impl.getBelow();
    }

    public int getBaselinePosition() {
        return this.impl.getCharBaseline(' ');
    }

    public int charWidth(char ch) {
        return this.impl.getCharASpace(ch) + this.impl.getCharWidth(ch) + this.impl.getCharCSpace(ch);
    }

    public int charsWidth(char[] ch, int offset, int len) {
        try {
            String _str = new String(ch).substring(offset, offset + len);
            return this.impl.stringWidth(this.impl.getStringWithCompatibleFont(_str));
        } catch (StringIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public int stringWidth(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        return this.impl.stringWidth(this.impl.getStringWithCompatibleFont(str));
    }

    public int substringWidth(String str, int offset, int len) {
        return this.impl.stringWidth(this.impl.getStringWithCompatibleFont(str.substring(offset, offset + len)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public com.nokia.mid.impl.isa.ui.gdi.Font getImpl() {
        return this.impl;
    }
}