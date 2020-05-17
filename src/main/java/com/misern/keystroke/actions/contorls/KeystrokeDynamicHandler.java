package com.misern.keystroke.actions.contorls;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeystrokeDynamicHandler implements KeyListener {

    private int lastCode = -1;
    private long timeLast = -1;

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {
        lastCode = e.getKeyCode();
        timeLast = System.currentTimeMillis();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(lastCode != -1) {
            long measuredTime = System.currentTimeMillis() - timeLast;
            System.out.println(String.format("Code: %d, time %d", lastCode, measuredTime));
        }
    }
}
