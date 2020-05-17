package com.misern.keystroke.ui;

import com.misern.keystroke.actions.contorls.ExitActionHandler;
import com.misern.keystroke.actions.contorls.KeystrokeDynamicHandler;

import javax.swing.*;
import java.awt.*;

/**
 * First and main view of application.
 * Includes image panel where output where be displayed.
 */
public class Dashboard extends JFrame {

    private final JMenuItem exitItem = new JMenuItem("Exit");
    private final JTextArea textArea = new JTextArea();
    private final JLabel sentence = new JLabel("Wpadła bomba do piwnicy, napisała na tablicy S.O.S");

    /**
     * Creates dashboard frame with menu bar and image panel
     */
    public Dashboard() {
        setLayout(new BorderLayout());
        setTitle("Misern — Keystroke");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(new Dimension(800, 600));

        createMenuBar();
        createActionListeners();

        add(textArea, BorderLayout.CENTER);
        add(sentence, BorderLayout.SOUTH);
        setVisible(true);
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu files = new JMenu("File");

        menuBar.add(files);
        files.add(exitItem);

        add(menuBar, BorderLayout.NORTH);
    }

    private void createActionListeners() {
        exitItem.addActionListener(new ExitActionHandler(this));
        textArea.addKeyListener(new KeystrokeDynamicHandler());
    }

}
