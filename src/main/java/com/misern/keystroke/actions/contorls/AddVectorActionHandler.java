package com.misern.keystroke.actions.contorls;

import com.misern.keystroke.dao.SampleDAO;
import com.misern.keystroke.dao.impl.SampleDAOImpl;
import com.misern.keystroke.model.Sample;
import com.misern.keystroke.ui.Dashboard;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AddVectorActionHandler implements ActionListener {
    private final SampleDAO sampleDAO = new SampleDAOImpl();
    private final String statement = "Example statement for recognition purposes";

    private final Dashboard dashboard;
    private final JPanel createVectorPanel;

    private final JTextField name = new JTextField("Enter your name");
    private final JTextArea inputArea = new JTextArea();
    private final JButton confirm = new JButton("Confirm");

    private JDialog inputDialog;
    private List<Long> times = new ArrayList<>();

    public AddVectorActionHandler(Dashboard dashboard) {
        this.dashboard = dashboard;
        createVectorPanel = new JPanel();

        prepareCreateVectorPanel();
        addActionListeners();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        inputDialog = new JDialog(dashboard, "Create vector", true);


        inputDialog.getContentPane().add(createVectorPanel);
        inputDialog.setSize(new Dimension(400, 300));
        inputDialog.setVisible(true);
    }

    private void saveSample() {
        Sample sample = new Sample();
        sample.setTimes(
                times.stream()
                .map(String::valueOf)
                .collect(Collectors.joining("|"))
        );
        sample.setUserName(name.getText());

        try {
            sampleDAO.save(sample);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(dashboard, "Can't save vector to database: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void prepareCreateVectorPanel() {
        JLabel instruction = new JLabel("Type above following statement: ");
        JLabel statementLabel = new JLabel(statement);

        JPanel lower = new JPanel();
        lower.setLayout(new GridLayout(3, 1));
        lower.add(instruction);
        lower.add(statementLabel);
        lower.add(confirm);

        JPanel upperPanel = new JPanel();
        upperPanel.setLayout(new BorderLayout());
        upperPanel.add(name, BorderLayout.NORTH);
        upperPanel.add(inputArea, BorderLayout.CENTER);

        createVectorPanel.setLayout(new BorderLayout());
        createVectorPanel.add(upperPanel, BorderLayout.CENTER);
        createVectorPanel.add(lower, BorderLayout.SOUTH);
    }

    private void addActionListeners() {
        inputArea.addKeyListener(new KeystrokeDynamicHandler(times));
        confirm.addActionListener(e -> {
            if (!inputArea.getText().equals(statement)) {
                JOptionPane.showMessageDialog(null, "Please, type correct statement from below box", "Error", JOptionPane.ERROR_MESSAGE);
                times.clear();
                inputArea.setText("");
            } else {
                saveSample();
                dashboard.getVectors();
                inputDialog.dispose();
            }
        });
    }
}
