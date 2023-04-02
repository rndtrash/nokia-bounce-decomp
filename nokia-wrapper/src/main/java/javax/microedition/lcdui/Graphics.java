package javax.microedition.lcdui;

import java.awt.*;
import java.awt.font.LineMetrics;

public class Graphics {
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


    java.awt.Graphics impl;

    private int clipx = 0;
    private int clipy = 0;
    private int clipw = 0;
    private int cliph = 0;
    int strokeStyle = 0;
    int translateX = 0;
    int translateY = 0;
    private Font font = Font.getDefaultFont();

    public Graphics(Image image) {
        if (image == null) {
            throw new IllegalStateException("Can not create Graphics without an Image");
        }
        this.impl = image.getPixmap().getGraphics();
        reset();
    }

    public Graphics(java.awt.Graphics graphics) {
        this.impl = graphics;
        reset();
    }

    public void reset() {
        reset(0, 0, this.impl.getClipBounds().width, this.impl.getClipBounds().height);
    }

    public void reset(int i, int i2, int i3, int i4) {
        this.translateX = 0;
        this.translateY = 0;
        setClip(i, i2, i3, i4);
        setColor(0);
        this.strokeStyle = 0;
        setFont(null);
    }

    public void drawChars(char[] data, int offset, int length, int x, int y, int anchor) {
        if (data == null) {
            throw new NullPointerException();
        }
        if (anchor == 0) {
            anchor = 20;
        }
        if (!isValidStringAnchor(anchor)) {
            throw new IllegalArgumentException("Graphics: Invalid anchor. Anchor is: " + anchor);
        }
        if (data.length != 0) {
            String text = String.valueOf(data, offset, length);
            FontMetrics metrics = this.impl.getFontMetrics(font.getImpl());
            LineMetrics lineMetrics = metrics.getLineMetrics(text, this.impl);
            int width = metrics.stringWidth(text);
            int height;
            if ((anchor & SVERTICAL) == BOTTOM) {
                height = (int) lineMetrics.getHeight();
            } else {
                height = (int) lineMetrics.getBaselineOffsets()[lineMetrics.getBaselineIndex()]; // FIXME: is it even right?
            }
            /*TextLine tLine = TextBreaker.breakOneLineTextInArea(32767, -1, this.font.getImpl(), text, 0, true);
            int width = tLine.getTextLineWidth();
            if ((anchor & SVERTICAL) == 32) {
                height = tLine.getTextLineHeight();
            } else {
                height = tLine.getTextLineBase();
            }*/
            this.impl.drawString(text, (short) resolveAnchorX(x + this.translateX, anchor, width), (short) resolveAnchorY(y + this.translateY, anchor, height));
        }
    }

    public void drawString(String str, int x, int y, int anchor) {
        if (str == null) {
            throw new NullPointerException();
        }
        drawChars(str.toCharArray(), 0, str.length(), x, y, anchor);
    }

    public void fillRect(int x, int y, int width, int height) {
        if (width > 0 && height > 0) {
            this.impl.fillRect((short) (x + this.translateX), (short) (y + this.translateY), (short) width, (short) height);
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
            throw new IllegalArgumentException("Graphics: Invalid anchor. Anchor is: " + anchor);
        }
        int width = img.getWidth();
        int height = img.getHeight();
        java.awt.Image pixmap = img.getPixmap();
        this.impl.drawImage(pixmap, (short) resolveAnchorX(x + this.translateX, anchor, width), (short) resolveAnchorY(y + this.translateY, anchor, height), null);
    }

    public int getARGBColor() {
        return this.impl.getColor().getRGB();
    }

    public void setClip(int i, int i2, int i3, int i4) {
        this.clipx = i + this.translateX;
        this.clipy = i2 + this.translateY;
        this.clipw = i3;
        this.cliph = i4;
        this.impl.setClip((short) this.clipx, (short) this.clipy, (short) this.clipw, (short) this.cliph);
    }

    public void setColor(int i) {
        this.impl.setColor(new Color(i));
    }

    public void setARGBColor(int argb) {
        this.impl.setColor(new Color(argb, true));
    }

    public void setFont(Font font) {
        if (font == null) {
            this.font = Font.getDefaultFont();
        } else {
            this.font = font;
        }
        this.impl.setFont(this.font.getImpl());
    }

    public java.awt.Graphics getImpl() {
        return this.impl;
    }

    boolean isValidStringAnchor(int anchor) {
        return (anchor & (-126)) <= 0
                && (
                (
                        (anchor & HORIZONTAL) == 4
                                || (anchor & HORIZONTAL) == 8
                                || (anchor & HORIZONTAL) == 1)
                        && (
                        (anchor & SVERTICAL) == 16
                                || (anchor & SVERTICAL) == 32
                                || (anchor & SVERTICAL) == 64)
        );
    }

    public boolean isValidImageAnchor(int anchor) {
        return (
                anchor & (-64)) <= 0
                && (
                (
                        (anchor & HORIZONTAL) == LEFT
                                || (anchor & HORIZONTAL) == RIGHT
                                || (anchor & HORIZONTAL) == HCENTER)
                        && (
                        (anchor & IVERTICAL) == TOP
                                || (anchor & IVERTICAL) == BOTTOM
                                || (anchor & IVERTICAL) == VCENTER)
        );
    }

    public int resolveAnchorX(int x, int anchor, int width) {
        return switch (anchor & 13) {
            case HCENTER -> x - (width / 2);
            case LEFT -> x;
            default -> x - (width - 1);
        };
    }

    public int resolveAnchorY(int y, int anchor, int height) {
        return switch (anchor & 114) {
            case TOP -> y;
            case BOTTOM, BASELINE -> y - (height - 1);
            default -> y - (height / 2);
        };
    }

    public void setTextTransparency(boolean txtTransparency) {
        System.err.println("setTextTransparency(" + txtTransparency + "): not implemented");
    }
}
