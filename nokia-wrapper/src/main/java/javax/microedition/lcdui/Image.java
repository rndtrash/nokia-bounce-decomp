package javax.microedition.lcdui;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class Image {
    boolean mutable;

    java.awt.Image pixmap;

    public static Image createImage(int width, int height) {
        if (width <= 0 || height <= 0)
            throw new IllegalArgumentException();

        DirectImageImpl image = new DirectImageImpl(width, height, false);
        image.mutable = true;
        Graphics graphics = image.getGraphics();
        int currentColor = graphics.getARGBColor();
        graphics.setARGBColor(-1);
        graphics.fillRect(0, 0, width, height);
        graphics.setARGBColor(currentColor);

        return image;
    }

    public static Image createImage(String str) throws IOException {
        if (str == null) {
            throw new NullPointerException();
        }
        InputStream resourceAsStream = Runtime.getRuntime().getClass().getResourceAsStream(str);
        if (resourceAsStream == null) {
            throw new IOException("Cannot read " + str);
        }
        return getImageFromStream(resourceAsStream);
    }

    public Graphics getGraphics() {
        if (this.mutable) {
            DirectGraphicsImpl directGraphicsImpl = new DirectGraphicsImpl(this);
            directGraphicsImpl.setTextTransparency(true);
            return directGraphicsImpl;
        }

        throw new IllegalStateException();
    }

    public Image(int width, int height, boolean isTransparent) {
        this.pixmap = new BufferedImage(width, height, isTransparent ? BufferedImage.TYPE_INT_ARGB : BufferedImage.TYPE_INT_RGB);
    }

    private Image(Image img) {
        int type;
        if (img.pixmap instanceof BufferedImage)
            type = ((BufferedImage) img.pixmap).getType();
        else
            type = BufferedImage.TYPE_INT_RGB;

        BufferedImage dest = new BufferedImage(img.pixmap.getWidth(null), img.pixmap.getHeight(null), type);
        Graphics2D g = dest.createGraphics();
        g.drawImage(img.pixmap, 0, 0, null);
        g.dispose();

        this.pixmap = dest;
    }

    public static Image createImage(byte[] imageData, int imageOffset, int imageLength) throws IOException {
        if (imageData == null) {
            throw new NullPointerException("ImageData passed to Image.CreateImage() is null.");
        }
        if (imageLength < 0 || imageOffset < 0 || imageOffset + imageLength > imageData.length) {
            throw new ArrayIndexOutOfBoundsException();
        }

        ByteArrayInputStream stream = new ByteArrayInputStream(Arrays.copyOfRange(imageData, imageOffset, imageOffset + imageLength));
        java.awt.Image image = ImageIO.read(stream);
        DirectImageImpl directImageImpl = new DirectImageImpl(image.getWidth(null), image.getHeight(null), true);

        BufferedImage dest = (BufferedImage) directImageImpl.pixmap;
        Graphics2D g = dest.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();

        directImageImpl.mutable = false;

        return directImageImpl;
    }

    private static Image getImageFromStream(InputStream inputStream) throws IOException {
        int i;
        byte[] bArr = new byte[1024];
        int read = inputStream.read(bArr);
        while (true) {
            i = read;
            if (i != 1024) {
                break;
            }
            byte[] bArr2 = new byte[bArr.length + 1024];
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            bArr = bArr2;
            read = inputStream.read(bArr, bArr.length - 1024, 1024);
        }
        try {
            return -1 == i ? createImage(bArr, 0, bArr.length - 1024) : createImage(bArr, 0, (bArr.length - 1024) + i);
        } catch (IllegalArgumentException e) {
            throw new IOException();
        }
    }

    public int getHeight() {
        return this.pixmap.getHeight(null);
    }

    public int getWidth() {
        return this.pixmap.getWidth(null);
    }

    public boolean isMutable() {
        return this.mutable;
    }

    public java.awt.Image getPixmap() {
        return this.pixmap;
    }
}
