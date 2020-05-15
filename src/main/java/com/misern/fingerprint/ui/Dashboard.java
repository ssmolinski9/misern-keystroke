package com.misern.fingerprint.ui;

import javax.swing.*;
import java.awt.*;

/**
 * First and main view of application.
 * Includes image panel where output where be displayed.
 */
public class Dashboard extends JFrame {
    /**
     * Creates dashboard frame with menu bar and image panel
     */
    public Dashboard() {
        setLayout(new BorderLayout());
        setTitle("Misern — Fingerprint");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(new Dimension(800, 600));

        createMenuBar();

        setVisible(true);
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu files = new JMenu("File");

        menuBar.add(files);

        add(menuBar, BorderLayout.NORTH);
    }
}
