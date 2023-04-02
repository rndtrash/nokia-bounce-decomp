package javax.microedition.lcdui;

import com.nokia.mid.impl.isa.ui.DirectImage;

public class DirectImageImpl extends Image implements DirectImage {
    public DirectImageImpl(int width, int height, boolean isTransparent) {
        super(width, height, isTransparent);
    }

    @Override
    public void setMutable(boolean isMutable) {
        this.mutable = isMutable;
    }

    @Override
    public void setImageColor(int argbColor) {
        System.err.println("setImageColor(" + argbColor + "): not implemented");
    }
}
