package com.misern.keystroke.actions.contorls;

import com.misern.keystroke.qualityTools.QualityCounter;
import com.misern.keystroke.ui.Dashboard;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;


import static javax.swing.JOptionPane.OK_CANCEL_OPTION;
import static javax.swing.JOptionPane.OK_OPTION;

public class SystemQualityActionHandler implements ActionListener {

    private final Dashboard dashboard;
    private JPanel systemQualityActionPanel;
    private JTextField kValueTextField = new JTextField(3);
    String[] metrics = { "Euclidean", "Manhattan", "Chebyshev"};
    private  JComboBox<String> metric = new JComboBox<>(metrics);

    public SystemQualityActionHandler (Dashboard dashboard) {
        this.dashboard = dashboard;
        systemQualityActionPanel = new JPanel();
        createOptionMenu();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String quality;
        int result = JOptionPane.showConfirmDialog(null, systemQualityActionPanel,
                "Please select values", OK_CANCEL_OPTION);

        if (result == OK_OPTION) {
            if (validateKValue(kValueTextField)) {
                try {
                    quality= QualityCounter.countQuality(Integer.parseInt(kValueTextField.getText()), Objects.requireNonNull(metric.getSelectedItem()).toString());
                    qualityInfo(quality);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dashboard, "Can't read vectors from database: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                incorrectKValueInfo();
            }

        }
    }

    private void createOptionMenu() {

        systemQualityActionPanel = new JPanel();
        systemQualityActionPanel.add(Box.createHorizontalStrut(15));
        systemQualityActionPanel.add(new JLabel("Metrics:"));
        systemQualityActionPanel.add(metric);

        kValueTextField.setText("0");
        systemQualityActionPanel.add(new JLabel("K value:"));
        systemQualityActionPanel.add(kValueTextField);
    }

    public static void incorrectKValueInfo() {
        JOptionPane.showMessageDialog(null, "\n" +
                "\n" + "Entered K value is incorrect", "Action faild", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void qualityInfo(String quality) {
        JOptionPane.showMessageDialog(null, "\n" +
                "\n" + "System quality: " + quality, "Calculation result", JOptionPane.INFORMATION_MESSAGE);
    }

    private boolean validateKValue(JTextField kValueTextField) {
        int value = Integer.parseInt(kValueTextField.getText());

        return value >= 0;
    }
}
