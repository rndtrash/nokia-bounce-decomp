package javax.microedition.lcdui;

import com.nokia.mid.impl.isa.ui.gdi.ColorCtrl;
import com.nokia.mid.impl.isa.ui.gdi.Pixmap;
import com.nokia.mid.ui.DirectGraphics;

/* JADX WARN: Classes with same name are omitted:
  nokia_s40.jar:javax/microedition/lcdui/DirectGraphicsImpl.class
 */
/* loaded from: nokia5100.jar:javax/microedition/lcdui/DirectGraphicsImpl.class */
class DirectGraphicsImpl extends Graphics implements DirectGraphics {
    /* JADX INFO: Access modifiers changed from: package-private */
    public DirectGraphicsImpl(Image image) {
        super(image);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DirectGraphicsImpl(com.nokia.mid.impl.isa.ui.gdi.Graphics graphics) {
        super(graphics);
    }

    @Override // com.nokia.mid.ui.DirectGraphics
    public int getAlphaComponent() {
        return (this.impl.getColorCtrl().getFgColor() & (-16777216)) >>> 24;
    }

    @Override // javax.microedition.lcdui.Graphics, com.nokia.mid.ui.DirectGraphics
    public void setARGBColor(int argbColor) {
        this.impl.getColorCtrl().setFgColor(argbColor);
    }

    @Override // com.nokia.mid.ui.DirectGraphics
    public void drawImage(Image img, int x, int y, int anchor, int manipulation) {
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
        com.nokia.mid.impl.isa.ui.gdi.Graphics gdiG = getImpl();
        int orig_rot_orig_x = gdiG.getRotOrigX();
        int orig_rot_orig_y = gdiG.getRotOrigY();
        int orig_rotation = gdiG.getRotation();
        gdiG.setRotOrigX((short) 0);
        gdiG.setRotOrigY((short) 0);
        gdiG.setRotation(gdiG.resolveManipulation(manipulation));
        int rotatedWidth = width;
        int rotatedHeight = height;
        if ((gdiG.getRotation() & 5) != 0) {
            rotatedWidth = height;
            rotatedHeight = width;
        }
        gdiG.drawPixmap(pixmap, (short) resolveAnchorX(x + getTranslateX(), anchor, rotatedWidth), (short) resolveAnchorY(y + getTranslateY(), anchor, rotatedHeight));
        gdiG.setRotOrigX((short) orig_rot_orig_x);
        gdiG.setRotOrigY((short) orig_rot_orig_y);
        gdiG.setRotation((short) orig_rotation);
    }

    @Override // com.nokia.mid.ui.DirectGraphics
    public void drawTriangle(int x1, int y1, int x2, int y2, int x3, int y3, int argbColor) {
        ColorCtrl colorCtrl = this.impl.getColorCtrl();
        int currentColor = colorCtrl.getFgColor();
        colorCtrl.setFgColor(argbColor);
        if (this.strokeStyle == 0) {
            getImpl().drawTriangle((short) (x1 + this.translateX), (short) (y1 + this.translateY), (short) (x2 + this.translateX), (short) (y2 + this.translateY), (short) (x3 + this.translateX), (short) (y3 + this.translateY));
        } else {
            short[] xs = {(short) (x1 + this.translateX), (short) (x2 + this.translateX), (short) (x3 + this.translateX)};
            short[] ys = {(short) (y1 + this.translateY), (short) (y2 + this.translateY), (short) (y3 + this.translateY)};
            getImpl().drawDottedPoly(xs, ys, (short) 3, true);
        }
        colorCtrl.setFgColor(currentColor);
    }

    @Override // com.nokia.mid.ui.DirectGraphics
    public void fillTriangle(int x1, int y1, int x2, int y2, int x3, int y3, int argbColor) {
        ColorCtrl colorCtrl = this.impl.getColorCtrl();
        int currentColor = colorCtrl.getFgColor();
        colorCtrl.setFgColor(argbColor);
        getImpl().fillTriangle((short) (x1 + this.translateX), (short) (y1 + this.translateY), (short) (x2 + this.translateX), (short) (y2 + this.translateY), (short) (x3 + this.translateX), (short) (y3 + this.translateY));
        colorCtrl.setFgColor(currentColor);
    }

    @Override // com.nokia.mid.ui.DirectGraphics
    public void drawPolygon(int[] xPoints, int xOffset, int[] yPoints, int yOffset, int nPoints, int argbColor) {
        checkPolyParameters(xPoints, xOffset, yPoints, yOffset, nPoints, argbColor);
        ColorCtrl colorCtrl = this.impl.getColorCtrl();
        int currentColor = colorCtrl.getFgColor();
        colorCtrl.setFgColor(argbColor);
        short[] xs = new short[nPoints];
        short[] ys = new short[nPoints];
        for (int i = 0; i < nPoints; i++) {
            xs[i] = (short) xPoints[xOffset + i];
            ys[i] = (short) yPoints[yOffset + i];
        }
        if (this.translateX != 0 || this.translateY != 0) {
            translateCoords(xs, ys, nPoints);
        }
        switch (nPoints) {
            case 0:
                break;
            case 1:
                getImpl().drawPixel(xs[0], ys[0]);
                break;
            case 2:
                if (this.strokeStyle == 0) {
                    getImpl().s_drawLine(xs[0], ys[0], xs[1], ys[1]);
                    break;
                } else {
                    getImpl().drawDottedLine(xs[0], ys[0], xs[1], ys[1]);
                    break;
                }
            default:
                if (this.strokeStyle == 0) {
                    getImpl().drawPoly(xs, ys, (short) nPoints, true);
                    break;
                } else {
                    getImpl().drawDottedPoly(xs, ys, (short) nPoints, true);
                    break;
                }
        }
        colorCtrl.setFgColor(currentColor);
    }

    @Override // com.nokia.mid.ui.DirectGraphics
    public void fillPolygon(int[] xPoints, int xOffset, int[] yPoints, int yOffset, int nPoints, int argbColor) {
        checkPolyParameters(xPoints, xOffset, yPoints, yOffset, nPoints, argbColor);
        ColorCtrl colorCtrl = this.impl.getColorCtrl();
        int currentColor = colorCtrl.getFgColor();
        colorCtrl.setFgColor(argbColor);
        short[] xs = new short[nPoints];
        short[] ys = new short[nPoints];
        for (int i = 0; i < nPoints; i++) {
            xs[i] = (short) xPoints[xOffset + i];
            ys[i] = (short) yPoints[yOffset + i];
        }
        if (this.translateX != 0 || this.translateY != 0) {
            translateCoords(xs, ys, nPoints);
        }
        switch (nPoints) {
            case 0:
                break;
            case 1:
                getImpl().drawPixel(xs[0], ys[0]);
                break;
            case 2:
                getImpl().s_drawLine(xs[0], ys[0], xs[1], ys[1]);
                break;
            default:
                getImpl().fillPoly(xs, ys, (short) nPoints);
                break;
        }
        colorCtrl.setFgColor(currentColor);
    }

    @Override // com.nokia.mid.ui.DirectGraphics
    public void getPixels(byte[] pixels, byte[] transparencyMask, int offset, int scanlength, int x, int y, int w, int h, int format) {
        this.impl.getPixels(pixels, transparencyMask, offset, scanlength, x, y, w, h, format);
    }

    @Override // com.nokia.mid.ui.DirectGraphics
    public void getPixels(short[] pixels, int offset, int scanlength, int x, int y, int w, int h, int format) {
        if (pixels == null) {
            throw new NullPointerException("Short array is null");
        }
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("Negative x/y");
        }
        if (w < 0 || h < 0) {
            throw new IllegalArgumentException("Negative width/height || width outside scan area");
        }
        if (offset < 0 || scanlength < 0 || (offset % scanlength) + w > scanlength) {
            throw new ArrayIndexOutOfBoundsException("Illegal offset | scanlength");
        }
        int pixels_needed = offset + ((h - 1) * scanlength) + w;
        if (pixels.length < pixels_needed) {
            throw new ArrayIndexOutOfBoundsException("Not enough pixels in pixel array");
        }
        this.impl.getPixels(pixels, offset, scanlength, x + this.translateX, y + this.translateY, w, h, format);
    }

    @Override // com.nokia.mid.ui.DirectGraphics
    public void getPixels(int[] pixels, int offset, int scanlength, int x, int y, int w, int h, int format) {
        this.impl.getPixels(pixels, offset, scanlength, x, y, w, h, format);
    }

    @Override // com.nokia.mid.ui.DirectGraphics
    public void drawPixels(byte[] pixels, byte[] transparencyMask, int offset, int scanlength, int x, int y, int w, int h, int manipulation, int format) {
        this.impl.drawPixels(pixels, transparencyMask, offset, scanlength, x + this.translateX, y + this.translateY, w, h, manipulation, format);
    }

    @Override // com.nokia.mid.ui.DirectGraphics
    public void drawPixels(short[] pixels, boolean transparency, int offset, int scanlength, int x, int y, int w, int h, int manipulation, int format) {
        com.nokia.mid.impl.isa.ui.gdi.Graphics localGdi = getImpl();
        int gdiRotation = localGdi.resolveManipulation(manipulation);
        if (pixels == null) {
            throw new NullPointerException("Short array is null");
        }
        if (w < 0 || h < 0) {
            throw new IllegalArgumentException("Negative width/height || width outside scan area");
        }
        if (offset < 0 || scanlength < 0 || (offset % scanlength) + w > scanlength) {
            throw new ArrayIndexOutOfBoundsException("Illegal offset | scanlength");
        }
        int pixels_needed = offset + ((h - 1) * scanlength) + w;
        if (pixels.length < pixels_needed) {
            throw new ArrayIndexOutOfBoundsException("Not enough pixels in pixel array");
        }
        if (!isAlphaGraphicsFormat(format)) {
            transparency = false;
        }
        this.impl.drawPixels(pixels, transparency, offset, scanlength, x + this.translateX, y + this.translateY, w, h, gdiRotation, format);
    }

    @Override // com.nokia.mid.ui.DirectGraphics
    public void drawPixels(int[] pixels, boolean transparency, int offset, int scanlength, int x, int y, int w, int h, int manipulation, int format) {
        if (!isAlphaGraphicsFormat(format)) {
            transparency = false;
        }
        this.impl.drawPixels(pixels, transparency, offset, scanlength, x + this.translateX, y + this.translateY, w, h, manipulation, format);
    }

    @Override // com.nokia.mid.ui.DirectGraphics
    public int getNativePixelFormat() {
        return this.impl.getNativePixelFormat();
    }

    void checkPolyParameters(int[] xPoints, int xOffset, int[] yPoints, int yOffset, int nPoints, int argbColor) {
        if (xOffset < 0 || yOffset < 0) {
            throw new ArrayIndexOutOfBoundsException("AIOBE: Negative offset");
        }
        if (xOffset + nPoints > xPoints.length || yOffset + nPoints > yPoints.length) {
            throw new ArrayIndexOutOfBoundsException("AIOBE: Not enough co-ordinate information to draw Polygon");
        }
        if (nPoints < 0) {
            throw new ArrayIndexOutOfBoundsException("AIOBE: Polygon number of points negative");
        }
        if (xPoints == null || yPoints == null) {
            throw new NullPointerException("NPE: Co-ordinate array(s) may be null");
        }
    }

    void translateCoords(short[] xs, short[] ys, int nPoints) {
        for (int i = 0; i < nPoints; i++) {
            int i2 = i;
            xs[i2] = (short) (xs[i2] + ((short) this.translateX));
            int i3 = i;
            ys[i3] = (short) (ys[i3] + ((short) this.translateY));
        }
    }

    boolean isAlphaGraphicsFormat(int format) {
        boolean isAlpha = false;
        switch (format) {
            case 1555:
            case 4444:
            case 8888:
                isAlpha = true;
                break;
        }
        return isAlpha;
    }
}