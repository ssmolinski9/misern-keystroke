package com.misern.keystroke.actions.contorls;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class KeystrokeDynamicHandler implements KeyListener {

    private int lastCode = -1;
    private long timeLast = -1;

    private final List<Long> times;

    public KeystrokeDynamicHandler(List<Long> times) {
        this.times = times;
    }

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
            times.add(measuredTime);
            //System.out.println(String.format("Code: %d, time %d", lastCode, measuredTime));
        }
    }
}
