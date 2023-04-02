package javax.microedition.lcdui;

public abstract class Canvas extends Displayable {
    private static final KeyMap keyMap = KeyMap.getKeyMap();
    public static final int UP = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 5;
    public static final int DOWN = 6;
    public static final int FIRE = 8;
    public static final int GAME_A = 9;
    public static final int GAME_B = 10;
    public static final int GAME_C = 11;
    public static final int GAME_D = 12;
    public static final int KEY_POUND = 35;
    public static final int KEY_STAR = 42;
    public static final int KEY_NUM0 = 48;
    public static final int KEY_NUM1 = 49;
    public static final int KEY_NUM2 = 50;
    public static final int KEY_NUM3 = 51;
    public static final int KEY_NUM4 = 52;
    public static final int KEY_NUM5 = 53;
    public static final int KEY_NUM6 = 54;
    public static final int KEY_NUM7 = 55;
    public static final int KEY_NUM8 = 56;
    public static final int KEY_NUM9 = 57;
    static Image s_overlayIcon;
    private static final int DEFAULT_OVERLAY_HEIGHT = 7;
    private static final int DEFAULT_OVERLAY_WIDTH = 10;
    private boolean isFullCanvas;
    private int height;
    private int width;

    @Override // javax.microedition.lcdui.Displayable
    protected abstract void paint(Graphics graphics);

    /* JADX INFO: Access modifiers changed from: protected */
    public Canvas() {
        this.height = 0;
        this.width = 0;
        if (this instanceof FullCanvas) {
            this.isFullCanvas = true;
            this.height = DeviceInfo.getDisplayHeight(3);
            this.width = DeviceInfo.getDisplayWidth(3);
        } else {
            this.isFullCanvas = false;
            this.height = DeviceInfo.getDisplayHeight(2);
            this.width = DeviceInfo.getDisplayWidth(2);
        }
        if (s_overlayIcon == null) {
            s_overlayIcon = ImageDatabase.getImage(6);
        }
    }

    @Override // javax.microedition.lcdui.Displayable
    public final void repaint(int x, int y, int width, int height) {
        if (this.displayed) {
            this.myDisplay.requestRepaint(this, x, y, width, height);
        }
    }

    @Override // javax.microedition.lcdui.Displayable
    public final void repaint() {
        repaint(0, 0, getWidth(), getHeight());
    }

    public final void serviceRepaints() {
        if (this.displayed) {
            this.myDisplay.requestServiceRepaints();
        }
    }

    public int getGameAction(int keyCode) {
        int gameAction = keyMap.getGameAction(keyCode);
        if (-127 == gameAction) {
            throw new IllegalArgumentException("getGameAction: Invalid keyCode");
        }
        return gameAction;
    }

    public int getKeyCode(int gameAction) {
        int keyCode = keyMap.getKeyCode(gameAction);
        if (Integer.MIN_VALUE == keyCode) {
            throw new IllegalArgumentException("getKeyCode: Invalid gameAction");
        }
        return keyCode;
    }

    public String getKeyName(int keyCode) {
        String keyName;
        if (-127 == keyMap.getGameAction(keyCode)) {
            throw new IllegalArgumentException("getKeyName: Invalid keyCode");
        }
        switch (keyCode) {
            case -11:
                keyName = new String("End");
                break;
            case -10:
                keyName = new String("Send");
                break;
            case -9:
            case -8:
            default:
                if (keyCode >= 0 && keyCode <= 65535) {
                    keyName = new Character((char) keyCode).toString();
                    break;
                } else {
                    keyName = "UnnamedKey";
                    break;
                }
            case -7:
                keyName = new String("Softkey2");
                break;
            case -6:
                keyName = new String("Softkey1");
                break;
            case -5:
                keyName = new String("Softkey3");
                break;
            case -4:
                keyName = new String("→");
                break;
            case -3:
                keyName = new String("←");
                break;
            case -2:
                keyName = new String("↓");
                break;
            case -1:
                keyName = new String("↑");
                break;
        }
        return keyName;
    }

    public boolean hasPointerEvents() {
        return DeviceInfo.hasPointerEvents();
    }

    public boolean hasPointerMotionEvents() {
        return DeviceInfo.hasPointerMotionEvents();
    }

    public boolean hasRepeatEvents() {
        return DeviceInfo.hasRepeatEvents();
    }

    protected void keyPressed(int keyCode) {
    }

    protected void keyReleased(int keyCode) {
    }

    protected void keyRepeated(int keyCode) {
    }

    protected void pointerDragged(int x, int y) {
    }

    protected void pointerPressed(int x, int y) {
    }

    protected void pointerReleased(int x, int y) {
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    @Override // javax.microedition.lcdui.Displayable
    protected void showNotify() {
    }

    @Override // javax.microedition.lcdui.Displayable
    protected void hideNotify() {
    }

    public boolean isDoubleBuffered() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // javax.microedition.lcdui.Displayable
    public void keyPressedEvent(int keyCode) {
        switch (keyCode) {
            case -7:
            case -6:
            case -5:
                if (this.isFullCanvas) {
                    keyPressed(keyCode);
                    return;
                } else {
                    super.keyPressedEvent(keyCode);
                    return;
                }
            default:
                keyPressed(keyCode);
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // javax.microedition.lcdui.Displayable
    public void keyReleasedEvent(int keyCode) {
        switch (keyCode) {
            case -7:
            case -6:
            case -5:
                if (this.isFullCanvas) {
                    keyReleased(keyCode);
                    return;
                }
                return;
            default:
                keyReleased(keyCode);
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // javax.microedition.lcdui.Displayable
    public void keyRepeatedEvent(int keyCode) {
        switch (keyCode) {
            case -7:
            case -6:
            case -5:
                if (this.isFullCanvas) {
                    keyRepeated(keyCode);
                    return;
                }
                return;
            default:
                keyRepeated(keyCode);
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void repaintOverlayArea() {
        int _iconWidth;
        int _iconHeight;
        if (s_overlayIcon != null) {
            _iconWidth = s_overlayIcon.getWidth();
            _iconHeight = s_overlayIcon.getHeight();
        } else {
            _iconWidth = 10;
            _iconHeight = 7;
        }
        if (this.alignedLeftToRight) {
            repaint(0, 0, _iconWidth, _iconHeight);
        } else {
            repaint(getWidth() - _iconWidth, 0, getWidth(), _iconHeight);
        }
        serviceRepaints();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void overlay(Graphics g) {
        int _startPosX;
        int _startPosX2;
        int _clipPosX;
        int _align;
        if (s_overlayIcon != null) {
            if (this.alignedLeftToRight) {
                _startPosX2 = 0;
                _clipPosX = 0;
                _align = 20;
            } else {
                _startPosX2 = getWidth();
                _clipPosX = _startPosX2 - s_overlayIcon.getWidth();
                _align = 24;
            }
            g.setClip(_clipPosX, 0, s_overlayIcon.getWidth(), s_overlayIcon.getHeight());
            g.drawImage(s_overlayIcon, _startPosX2, 0, _align);
            return;
        }
        int _colour = g.getARGBColor();
        g.setARGBColor(Displayable.uistyle.getColour(0));
        if (this.alignedLeftToRight) {
            _startPosX = 0;
        } else {
            _startPosX = getWidth() - 10;
        }
        g.setClip(_startPosX, 0, 10, 7);
        g.fillRect(_startPosX, 0, 10, 7);
        g.setARGBColor(_colour);
    }

}
