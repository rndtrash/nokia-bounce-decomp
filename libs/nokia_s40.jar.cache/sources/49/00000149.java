package javax.microedition.lcdui;

import com.nokia.mid.impl.isa.ui.gdi.ColorCtrl;
import com.nokia.mid.impl.isa.ui.gdi.Pixmap;
import com.nokia.mid.impl.isa.ui.gdi.TextBreaker;
import com.nokia.mid.impl.isa.ui.gdi.TextLine;
import com.nokia.mid.impl.isa.ui.style.UIStyle;

/* JADX WARN: Classes with same name are omitted:
  nokia_s40.jar:javax/microedition/lcdui/Graphics.class
 */
/* loaded from: nokia5100.jar:javax/microedition/lcdui/Graphics.class */
public class Graphics {
    static UIStyle uistyle = UIStyle.getUIStyle();
    public static final int BASELINE = 64;
    public static final int BOTTOM = 32;
    public static final int DOTTED = 1;
    public static final int HCENTER = 1;
    public static final int LEFT = 4;
    public static final int RIGHT = 8;
    public static final int SOLID = 0;
    public static final int TOP = 16;
    public static final int VCENTER = 2;
    private static final int HORIZONTAL = 13;
    private static final int SVERTICAL = 112;
    private static final int IVERTICAL = 50;
    com.nokia.mid.impl.isa.ui.gdi.Graphics impl;
    private int clipx = 0;
    private int clipy = 0;
    private int clipw = 0;
    private int cliph = 0;
    int strokeStyle = 0;
    int translateX = 0;
    int translateY = 0;
    private Font font = Font.getDefaultFont();

    /* JADX INFO: Access modifiers changed from: package-private */
    public Graphics(Image image) {
        if (image == null) {
            throw new IllegalStateException("Can not create Graphics without an Image");
        }
        this.impl = image.getPixmap().getGraphics();
        reset();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Graphics(com.nokia.mid.impl.isa.ui.gdi.Graphics graphics) {
        this.impl = graphics;
        reset();
    }

    public void clipRect(int x, int y, int width, int height) {
        int x2 = x + this.translateX;
        int y2 = y + this.translateY;
        if (x2 >= this.clipx + this.clipw || y2 >= this.clipy + this.cliph || this.clipx >= x2 + width || this.clipy >= y2 + height || width <= 0 || height <= 0) {
            this.clipx = 0;
            this.clipy = 0;
            this.clipw = 0;
            this.cliph = 0;
        } else {
            if (x2 > this.clipx) {
                this.clipw = (this.clipx + this.clipw > x2 + width ? x2 + width : this.clipx + this.clipw) - x2;
                this.clipx = x2;
            } else {
                this.clipw = (this.clipx + this.clipw > x2 + width ? x2 + width : this.clipx + this.clipw) - this.clipx;
            }
            if (y2 > this.clipy) {
                this.cliph = (this.clipy + this.cliph > y2 + height ? y2 + height : this.clipy + this.cliph) - y2;
                this.clipy = y2;
            } else {
                this.cliph = (this.clipy + this.cliph > y2 + height ? y2 + height : this.clipy + this.cliph) - this.clipy;
            }
        }
        this.impl.setClip((short) this.clipx, (short) this.clipy, (short) this.clipw, (short) this.cliph);
    }

    public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
        if (width >= 0 && height >= 0) {
            if (this.strokeStyle == 0) {
                this.impl.drawArc((short) (x + this.translateX), (short) (y + this.translateY), (short) (width + 1), (short) (height + 1), (short) startAngle, (short) arcAngle);
            } else {
                this.impl.drawDottedArc((short) (x + this.translateX), (short) (y + this.translateY), (short) (width + 1), (short) (height + 1), (short) startAngle, (short) arcAngle);
            }
        }
    }

    public void drawLine(int x1, int y1, int x2, int y2) {
        int x12 = x1 + this.translateX;
        int y12 = y1 + this.translateY;
        int x22 = x2 + this.translateX;
        int y22 = y2 + this.translateY;
        if (this.strokeStyle == 0) {
            this.impl.drawLine((short) x12, (short) y12, (short) x22, (short) y22);
        } else {
            this.impl.drawDottedLine((short) x12, (short) y12, (short) x22, (short) y22);
        }
    }

    public void drawRect(int x, int y, int width, int height) {
        if (width >= 0 && height >= 0) {
            if (this.strokeStyle == 0) {
                this.impl.drawRect((short) (x + this.translateX), (short) (y + this.translateY), (short) (width + 1), (short) (height + 1));
            } else {
                this.impl.drawDottedRect((short) (x + this.translateX), (short) (y + this.translateY), (short) (width + 1), (short) (height + 1));
            }
        }
    }

    public void drawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
        if (width >= 0 && height >= 0) {
            if (this.strokeStyle == 0) {
                this.impl.drawRoundRect((short) (x + this.translateX), (short) (y + this.translateY), (short) (width + 1), (short) (height + 1), (short) arcWidth, (short) arcHeight);
            } else {
                this.impl.drawDottedRoundRect((short) (x + this.translateX), (short) (y + this.translateY), (short) (width + 1), (short) (height + 1), (short) arcWidth, (short) arcHeight);
            }
        }
    }

    public void drawImage(Image img, int x, int y, int anchor) {
        if (img == null) {
            throw new NullPointerException();
        }
        if (anchor == 0) {
            anchor = 20;
        }
        if (!isValidImageAnchor(anchor)) {
            throw new IllegalArgumentException(new StringBuffer().append("Graphics: Invalid anchor. Anchor is: ").append(anchor).toString());
        }
        int width = img.getWidth();
        int height = img.getHeight();
        Pixmap pixmap = img.getPixmap();
        this.impl.drawPixmap(pixmap, (short) resolveAnchorX(x + this.translateX, anchor, width), (short) resolveAnchorY(y + this.translateY, anchor, height));
    }

    public void drawChar(char character, int x, int y, int anchor) {
        char[] cha = {character};
        drawChars(cha, 0, 1, x, y, anchor);
    }

    public void drawChars(char[] data, int offset, int length, int x, int y, int anchor) {
        int height;
        if (data == null) {
            throw new NullPointerException();
        }
        if (anchor == 0) {
            anchor = 20;
        }
        if (!isValidStringAnchor(anchor)) {
            throw new IllegalArgumentException(new StringBuffer().append("Graphics: Invalid anchor. Anchor is: ").append(anchor).toString());
        }
        if (data.length != 0) {
            String text = String.valueOf(data, offset, length);
            TextLine tLine = TextBreaker.breakOneLineTextInArea(32767, -1, this.font.getImpl(), this.font.getImpl().getStringWithCompatibleFont(text), 0, true);
            int width = tLine.getTextLineWidth();
            if ((anchor & 112) == 32) {
                height = tLine.getTextLineHeight();
            } else {
                height = tLine.getTextLineBase();
            }
            this.impl.drawText(tLine, (short) resolveAnchorX(x + this.translateX, anchor, width), (short) resolveAnchorY(y + this.translateY, anchor, height));
        }
    }

    public void drawSubstring(String str, int offset, int len, int x, int y, int anchor) {
        if (str == null) {
            throw new NullPointerException();
        }
        drawChars(str.toCharArray(), offset, len, x, y, anchor);
    }

    public void drawString(String str, int x, int y, int anchor) {
        if (str == null) {
            throw new NullPointerException();
        }
        drawChars(str.toCharArray(), 0, str.length(), x, y, anchor);
    }

    public void fillArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
        if (width > 0 && height > 0) {
            this.impl.fillArc((short) (x + this.translateX), (short) (y + this.translateY), (short) width, (short) height, (short) startAngle, (short) arcAngle);
        }
    }

    public void fillRect(int x, int y, int width, int height) {
        if (width > 0 && height > 0) {
            this.impl.fillRect((short) (x + this.translateX), (short) (y + this.translateY), (short) width, (short) height);
        }
    }

    public void fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
        if (width > 0 && height > 0) {
            this.impl.fillRoundRect((short) (x + this.translateX), (short) (y + this.translateY), (short) width, (short) height, (short) arcWidth, (short) arcHeight);
        }
    }

    public int getClipHeight() {
        return this.cliph;
    }

    public int getClipWidth() {
        return this.clipw;
    }

    public int getClipX() {
        return this.clipx - this.translateX;
    }

    public int getClipY() {
        return this.clipy - this.translateY;
    }

    public int getColor() {
        return this.impl.getColorCtrl().getFgColor() & 16777215;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getARGBColor() {
        return this.impl.getColorCtrl().getFgColor();
    }

    public Font getFont() {
        return this.font;
    }

    public int getGrayScale() {
        int red = getRedComponent();
        int green = getGreenComponent();
        int blue = getBlueComponent();
        int nRetVal = blue;
        if (blue != green || blue != red) {
            nRetVal = (red / 3) + (green / 2) + ((blue * 17) / 100);
        }
        return nRetVal;
    }

    public int getGreenComponent() {
        return (this.impl.getColorCtrl().getFgColor() & 65280) >> 8;
    }

    public int getRedComponent() {
        return (this.impl.getColorCtrl().getFgColor() & 16711680) >> 16;
    }

    public int getBlueComponent() {
        return this.impl.getColorCtrl().getFgColor() & 255;
    }

    public int getStrokeStyle() {
        return this.strokeStyle;
    }

    public int getTranslateX() {
        return this.translateX;
    }

    public int getTranslateY() {
        return this.translateY;
    }

    public void setClip(int x, int y, int width, int height) {
        this.clipx = x + this.translateX;
        this.clipy = y + this.translateY;
        this.clipw = width;
        this.cliph = height;
        this.impl.setClip((short) this.clipx, (short) this.clipy, (short) this.clipw, (short) this.cliph);
    }

    public void setColor(int rgb) {
        int argb = (-16777216) | rgb;
        this.impl.getColorCtrl().setFgColor(argb);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setARGBColor(int argb) {
        this.impl.getColorCtrl().setFgColor(argb);
    }

    public void setColor(int red, int green, int blue) {
        if ((red & (-256)) != 0 || (green & (-256)) != 0 || (blue & (-256)) != 0) {
            throw new IllegalArgumentException();
        }
        setColor((red << 16) | (green << 8) | blue);
    }

    public void setFont(Font font) {
        if (font == null) {
            this.font = Font.getDefaultFont();
        } else {
            this.font = font;
        }
        this.impl.setFont(this.font.getImpl());
    }

    public void setGrayScale(int value) {
        if ((value & (-256)) > 0) {
            throw new IllegalArgumentException();
        }
        setColor(value, value, value);
    }

    public void setStrokeStyle(int style) {
        if (style != 1 && style != 0) {
            throw new IllegalArgumentException();
        }
        this.strokeStyle = style;
    }

    public void translate(int x, int y) {
        this.translateX += x;
        this.translateY += y;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void reset() {
        this.translateX = 0;
        this.translateY = 0;
        setClip(0, 0, this.impl.getWidth(), this.impl.getHeight());
        setColor(0);
        this.strokeStyle = 0;
        setFont(null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void clearScreen() {
        ColorCtrl colorCtrl = this.impl.getColorCtrl();
        int original_color = colorCtrl.getFgColor();
        colorCtrl.setFgColor(uistyle.getColour(3));
        this.impl.fillRect((short) 0, (short) 0, (short) this.impl.getWidth(), (short) this.impl.getHeight());
        colorCtrl.setFgColor(original_color);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getWidth() {
        return this.impl.getWidth();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getHeight() {
        return this.impl.getHeight();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void refresh(int x, int y, int width, int height) {
        if (x != 0 || y != 0 || width != this.impl.getWidth() || height != this.impl.getHeight()) {
            this.impl.refresh(x, y, width, height, true);
        } else {
            this.impl.refresh(0, 0, 0, 0, false);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public com.nokia.mid.impl.isa.ui.gdi.Graphics getImpl() {
        return this.impl;
    }

    boolean isValidStringAnchor(int anchor) {
        boolean retval = true;
        if ((anchor & (-126)) > 0 || (((anchor & 13) != 4 && (anchor & 13) != 8 && (anchor & 13) != 1) || ((anchor & 112) != 16 && (anchor & 112) != 32 && (anchor & 112) != 64))) {
            retval = false;
        }
        return retval;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isValidImageAnchor(int anchor) {
        boolean retval = true;
        if ((anchor & (-64)) > 0 || (((anchor & 13) != 4 && (anchor & 13) != 8 && (anchor & 13) != 1) || ((anchor & 50) != 16 && (anchor & 50) != 32 && (anchor & 50) != 2))) {
            retval = false;
        }
        return retval;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int resolveAnchorX(int x, int anchor, int width) {
        int nRetVal;
        switch (anchor & 13) {
            case 1:
                nRetVal = x - (width / 2);
                break;
            case 4:
                nRetVal = x;
                break;
            default:
                nRetVal = x - (width - 1);
                break;
        }
        return nRetVal;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int resolveAnchorY(int y, int anchor, int height) {
        int nRetVal;
        switch (anchor & 114) {
            case 16:
                nRetVal = y;
                break;
            case 32:
            case 64:
                nRetVal = y - (height - 1);
                break;
            default:
                nRetVal = y - (height / 2);
                break;
        }
        return nRetVal;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setTextTransparency(boolean txtTransparency) {
        this.impl.setTextTransparency(txtTransparency);
    }
}