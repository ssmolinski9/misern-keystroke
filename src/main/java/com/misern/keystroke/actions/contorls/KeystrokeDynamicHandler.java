package com.misern.keystroke.actions.contorls;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class KeystrokeDynamicHandler implements KeyListener {

    private final String statement = "Example statement for recognition purposes".toLowerCase();

    private long timeLast = -1;

    private final List<Long> times;

    public KeystrokeDynamicHandler(List<Long> times) {
        this.times = times;
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {
        timeLast = System.currentTimeMillis();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (times.size() > statement.length()) {
            return;
        }

        int keyCode = Character.toLowerCase(e.getKeyChar());
        if(keyCode != 27 && keyCode == statement.charAt(times.size())) {
            long measuredTime = System.currentTimeMillis() - timeLast;
            times.add(measuredTime);

            System.out.println(String.format("time %d", measuredTime));
        }
    }
}
