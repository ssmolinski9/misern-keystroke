package com.misern.keystroke.actions.contorls;

import com.misern.keystroke.ui.Dashboard;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitActionHandler implements ActionListener {
    private final Dashboard dashboard;

    public ExitActionHandler(Dashboard dashboard) {
        this.dashboard = dashboard;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        dashboard.dispose();
        System.exit(0);
    }
}
