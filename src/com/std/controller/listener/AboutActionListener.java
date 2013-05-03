package com.std.controller.listener;

import com.std.controller.CalendarController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This is the listener listening to the about menu item. Selecting this item
 * will pop up addition application information.
 *
 * @author kxc4519
 */
public class AboutActionListener implements ActionListener {

    CalendarController cc;

    /**
     * creates a new AboutActionListener
     */
    public AboutActionListener(CalendarController c) {
        cc = c;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(cc.getView(), "DCal.  Copyright 2008, Super Team D, RIT", "About", JOptionPane.INFORMATION_MESSAGE);
    }
}
