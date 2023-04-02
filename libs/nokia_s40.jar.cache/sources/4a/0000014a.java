package javax.microedition.lcdui;

import com.nokia.mid.impl.isa.ui.gdi.Pixmap;
import java.io.IOException;
import java.io.InputStream;

/* JADX WARN: Classes with same name are omitted:
  nokia_s40.jar:javax/microedition/lcdui/Image.class
 */
/* loaded from: nokia5100.jar:javax/microedition/lcdui/Image.class */
public class Image {
    private static final int MAX_BUF_SIZE = 1024;
    Pixmap pixmap;
    boolean mutable;

    private static native void s_detectSize(byte[] bArr, int i, int i2, int[] iArr);

    private native void decodeImage(byte[] bArr, int i, int i2);

    public static Image createImage(int width, int height) {
        Image image;
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException();
        }
        synchronized (Pixmap.nokia_ui_pixmap_toggle_lock) {
            image = new DirectImageImpl(width, height, false);
        }
        image.mutable = true;
        Graphics g = image.getGraphics();
        int currentColor = g.getARGBColor();
        g.setARGBColor(-1);
        g.fillRect(0, 0, width, height);
        g.setARGBColor(currentColor);
        return image;
    }

    public static Image createImage(String name) throws IOException {
        int readbytes;
        Image imgRetVal;
        if (name == null) {
            throw new NullPointerException();
        }
        InputStream res = Runtime.getRuntime().getClass().getResourceAsStream(name);
        if (res == null) {
            throw new IOException(new StringBuffer().append("Cannot read ").append(name).toString());
        }
        byte[] buffer = new byte[1024];
        int read = res.read(buffer);
        while (true) {
            readbytes = read;
            if (readbytes != 1024) {
                break;
            }
            byte[] newbuffer = new byte[buffer.length + 1024];
            System.arraycopy(buffer, 0, newbuffer, 0, buffer.length);
            buffer = newbuffer;
            read = res.read(buffer, buffer.length - 1024, 1024);
        }
        try {
            if (-1 == readbytes) {
                imgRetVal = createImage(buffer, 0, buffer.length - 1024);
            } else {
                imgRetVal = createImage(buffer, 0, (buffer.length - 1024) + readbytes);
            }
            return imgRetVal;
        } catch (IllegalArgumentException e) {
            throw new IOException();
        }
    }

    public static Image createImage(byte[] imageData, int imageOffset, int imageLength) {
        if (imageData == null) {
            throw new NullPointerException("ImageData passed to Image.CreateImage() is null.");
        }
        if (imageLength < 0 || imageOffset < 0 || imageOffset + imageLength > imageData.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int[] xy = new int[2];
        s_detectSize(imageData, imageOffset, imageLength, xy);
        Image image = new DirectImageImpl(xy[0], xy[1], true);
        image.decodeImage(imageData, imageOffset, imageLength);
        image.mutable = false;
        return image;
    }

    public static Image createImage(Image source) {
        Image imgRetVal = source;
        if (source == null) {
            throw new NullPointerException();
        }
        if (source.mutable) {
            imgRetVal = new Image(source);
            imgRetVal.mutable = false;
        }
        return imgRetVal;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Image(int width, int height, boolean isTransparent) {
        this.pixmap = new Pixmap((short) width, (short) height, isTransparent);
    }

    private Image(Image img) {
        this.pixmap = new Pixmap(img.pixmap);
    }

    public Graphics getGraphics() {
        if (!this.mutable) {
            throw new IllegalStateException();
        }
        Graphics gr = new DirectGraphicsImpl(this);
        gr.setTextTransparency(true);
        return gr;
    }

    public int getHeight() {
        return this.pixmap.getHeight();
    }

    public int getWidth() {
        return this.pixmap.getWidth();
    }

    public boolean isMutable() {
        return this.mutable;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Pixmap getPixmap() {
        return this.pixmap;
    }
}