package javax.microedition.lcdui;

public abstract class Displayable {
    Display myDisplay;
    static UIStyle uistyle = UIStyle.getUIStyle();
    static final SoftkeyManager softkeyManager = SoftkeyManager.getSoftkeyManager();
    boolean displayed = false;
    boolean alignedLeftToRight = true;
    Object displayableMutex = new Object();
    CommandVector userCommands = new CommandVector();
    CommandVector softkeyCommands = new CommandVector(3);
    CommandVector optionMenuCommands = new CommandVector();
    SoftLabel sl = new SoftLabel(this);
    private Command pendingCommand = null;
    CommandListener myCommandListener = null;
    private boolean isSystemScreen = false;
    private boolean isBogusSystemApp = false;
    private boolean afterShowNotify = false;

    public abstract void repaint();

    public boolean isShown() {
        return this.displayed;
    }

    public void addCommand(Command cmd) throws NullPointerException {
        if (cmd == null) {
            throw new NullPointerException();
        }
        Object _lock = tryToGetDisplayMutex();
        boolean _retry = false;
        synchronized (_lock) {
            synchronized (this.displayableMutex) {
                if (_lock == tryToGetDisplayMutex()) {
                    if (this.userCommands.addCommand(cmd)) {
                        updateSoftkeys(true);
                    }
                } else {
                    _retry = true;
                }
            }
        }
        if (_retry) {
            addCommand(cmd);
        }
    }

    public void removeCommand(Command cmd) {
        if (cmd != null) {
            Object _lock = tryToGetDisplayMutex();
            boolean _retry = false;
            synchronized (_lock) {
                synchronized (this.displayableMutex) {
                    if (_lock == tryToGetDisplayMutex()) {
                        if (this.userCommands.removeCommand(cmd)) {
                            updateSoftkeys(true);
                        }
                    } else {
                        _retry = true;
                    }
                }
            }
            if (_retry) {
                removeCommand(cmd);
            }
        }
    }

    public void setCommandListener(CommandListener l) {
        this.myCommandListener = l;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CommandListener getCommandListener() {
        return this.myCommandListener;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setPendingCommandAction(Command c) {
        this.pendingCommand = c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Command getPendingCommandAction() {
        return this.pendingCommand;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void createCommandList() {
        Object _lock = tryToGetDisplayMutex();
        boolean _retry = false;
        boolean bDone = false;
        synchronized (_lock) {
            synchronized (this.displayableMutex) {
                if (_lock == tryToGetDisplayMutex()) {
                    CommandList _cmdList = this.myDisplay.getCommandList();
                    if (this instanceof Screen) {
                        _cmdList.setTitle(((Screen) this).getTitle());
                    } else if (this.myDisplay != null) {
                        _cmdList.setTitle(this.myDisplay.getMIDletName());
                    } else {
                        _cmdList.setTitle("");
                    }
                    if (_cmdList.isBeingRemoved()) {
                        bDone = true;
                    } else {
                        int oldindex = _cmdList.getSelectedIndex();
                        _cmdList.enableRepaint(false);
                        _cmdList.deleteAllItems();
                        int option_len = this.optionMenuCommands.length();
                        if (option_len == 0) {
                            _cmdList.setBeingRemoved(true);
                            this.myDisplay.setCurrent(this);
                        } else {
                            for (int i = 0; i < option_len; i++) {
                                _cmdList.append(this.optionMenuCommands.getCommand(i).label, null);
                            }
                            if (_cmdList.myCommandListener == null || ((CommandListListener) _cmdList.myCommandListener).getOuterObject() != this) {
                                _cmdList.setCommandListener(new CommandListListener(this));
                            }
                            _cmdList.enableRepaint(true);
                            _cmdList.resetFocus();
                            if (_cmdList.displayed) {
                                if (oldindex < _cmdList.size()) {
                                    _cmdList.setSelectedIndex(oldindex, true);
                                }
                                _cmdList.repaint();
                                this.myDisplay.requestServiceRepaints();
                            } else {
                                this.myDisplay.setCurrent(_cmdList);
                            }
                        }
                    }
                } else {
                    _retry = false;
                }
            }
        }
        if (_retry && !bDone) {
            createCommandList();
        }
    }

    private void updateSoftkeys(boolean createCL) {
        if (usesSoftLabel()) {
            return;
        }
        this.optionMenuCommands.reconstruct(this.userCommands, getExtraCommands());
        softkeyManager.selectSoftkeys(this, this.optionMenuCommands, this.softkeyCommands);
        this.sl.set(this.softkeyCommands);
        if (createCL && this.myDisplay != null && this.myDisplay.getCurrent() == this) {
            CommandList list = this.myDisplay.getCommandList();
            if (list.isShown() && ((CommandListListener) list.myCommandListener).getOuterObject() == this) {
                createCommandList();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void updateSoftkeys() {
        updateSoftkeys(false);
    }

    boolean usesSoftLabel() {
        return false;
    }

    Command[] getExtraCommands() {
        return null;
    }

    void launchExtraCommand(Command c) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void keyPressedEvent(int keyCode) {
        if (usesSoftLabel()) {
            return;
        }
        Command cmd = null;
        switch (keyCode) {
            case -7:
                cmd = this.softkeyCommands.getCommand(2);
                break;
            case -6:
                cmd = this.softkeyCommands.getCommand(0);
                break;
            case -5:
                cmd = this.softkeyCommands.getCommand(1);
                break;
        }
        if (cmd == null) {
            return;
        }
        if (cmd == CommandList.optionsCommand) {
            updateSoftkeys(false);
            createCommandList();
        } else if (this.userCommands.containsCommand(cmd)) {
            this.myCommandListener.commandAction(cmd, this);
        } else {
            launchExtraCommand(cmd);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void keyRepeatedEvent(int keyCode) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void keyReleasedEvent(int keyCode) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void showNotify(Display d) {
        synchronized (this.displayableMutex) {
            if (!this.displayed) {
                this.myDisplay = d;
                showNotify();
                this.displayed = true;
                this.afterShowNotify = true;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void hideNotify(Display d) {
        synchronized (this.displayableMutex) {
            if (this.displayed) {
                this.displayed = false;
                hideNotify();
            }
        }
    }

    void showNotify() {
    }

    void hideNotify() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void hideNotifyInProgress() {
    }

    void paint(Graphics g) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void paintEvent(Graphics g) {
        if (this.afterShowNotify) {
            this.sl.updateAll();
            this.afterShowNotify = false;
        }
        paint(g);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void repaint(int x, int y, int width, int height) {
        if (this.displayed) {
            this.myDisplay.requestRepaint(this, x, y, width, height);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean isSystemScreen() {
        return this.isSystemScreen;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void setSystemScreen(boolean value) {
        this.isSystemScreen = value;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean isBogusSystemApp() {
        return this.isBogusSystemApp;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void setBogusSystemApp(boolean value) {
        this.isBogusSystemApp = value;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Object tryToGetDisplayMutex() {
        return this.myDisplay != null ? this.myDisplay.getDisplayMutex() : this.displayableMutex;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Object getDisplayableMutex() {
        return this.displayableMutex;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: nokia5100.jar:javax/microedition/lcdui/Displayable$CommandListListener.class */
    public class CommandListListener implements CommandListener {
        private final Displayable this$0;

        CommandListListener(Displayable this$0) {
            this.this$0 = this$0;
        }

        @Override // javax.microedition.lcdui.CommandListener
        public void commandAction(Command c, Displayable d) {
            Object _lock = this.this$0.tryToGetDisplayMutex();
            boolean _retry = false;
            boolean bDone = false;
            synchronized (_lock) {
                synchronized (this.this$0.displayableMutex) {
                    if (_lock == this.this$0.tryToGetDisplayMutex()) {
                        if (((CommandList) d).isBeingRemoved()) {
                            bDone = true;
                        } else {
                            ((CommandList) d).setBeingRemoved(true);
                            this.this$0.myDisplay.setCurrent(this.this$0);
                            if (c == List.SELECT_COMMAND) {
                                int selected = ((CommandList) d).getSelectedIndex();
                                Command cmd = this.this$0.optionMenuCommands.getCommand(selected);
                                if (this.this$0.userCommands.containsCommand(cmd)) {
                                    this.this$0.setPendingCommandAction(cmd);
                                } else {
                                    this.this$0.launchExtraCommand(cmd);
                                }
                            }
                        }
                    } else {
                        _retry = true;
                    }
                }
            }
            if (_retry && !bDone) {
                commandAction(c, d);
            }
        }

        public Displayable getOuterObject() {
            return this.this$0;
        }
    }
}
