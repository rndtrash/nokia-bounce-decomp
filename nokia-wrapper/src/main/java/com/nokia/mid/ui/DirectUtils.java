package com.nokia.mid.ui;

import com.nokia.mid.impl.isa.ui.DirectImage;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import java.io.IOException;

public class DirectUtils {
    private DirectUtils() {
    }

    public static DirectGraphics getDirectGraphics(Graphics g) {
        return (DirectGraphics) g;
    }

    public static Image createImage(byte[] imageData, int imageOffset, int imageLength) throws IOException {
        Image img = Image.createImage(imageData, imageOffset, imageLength);
        DirectImage di = (DirectImage) img;
        di.setMutable(true);
        return img;
    }

    public static Image createImage(int w, int h, int color) {
        Image image = Image.createImage(w, h);
        ((DirectImage) image).setImageColor(color);
        return image;
    }
}
