package com.misern.keystroke.ui;

import com.misern.keystroke.actions.contorls.AddVectorActionHandler;
import com.misern.keystroke.actions.contorls.ExitActionHandler;

import com.misern.keystroke.dao.SampleDAO;
import com.misern.keystroke.dao.impl.SampleDAOImpl;
import com.misern.keystroke.model.Sample;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class Dashboard extends JFrame {

    private final SampleDAO sampleDAO = new SampleDAOImpl();

    private final JMenuItem exitItem = new JMenuItem("Exit");
    private final JButton addVector = new JButton("Add vector");
    private final JList<Sample> samples = new JList<>();

    public Dashboard() {
        setLayout(new BorderLayout());
        setTitle("Misern — Keystroke");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(new Dimension(800, 600));

        createMenuBar();
        createActionListeners();
        getVectors();

        add(samples, BorderLayout.CENTER);
        add(addVector, BorderLayout.SOUTH);
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
        addVector.addActionListener(new AddVectorActionHandler(this));
    }

    public void getVectors() {
        try {
            DefaultListModel<Sample> model = new DefaultListModel<>();
            samples.setModel(model);

            for (Sample sample : sampleDAO.findAll()) {
                model.addElement(sample);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Can't read vectors from database: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
