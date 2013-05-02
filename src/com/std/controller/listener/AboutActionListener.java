package com.std.controller.listener;

import com.std.controller.CalendarController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This is the listener listening to the about menu item. Selecting this item
 * will pop up addition application information.
 *
 * @author xxx
 */
public class AboutActionListener implements Command {

    /**
     * a reference to the controller so that this listener can access both the
     * model and the view.
     */
    private final CalendarController controller;

    /**
     * creates a new AboutActionListener
     *
     * @param cc is the reference to the controller
     */
    public AboutActionListener(CalendarController cc) {
        controller = cc;
    }

    @Override
    public void execute(CalendarController cc) {
        JOptionPane.showMessageDialog(cc.getView(), "DCal.  Copyright 2008, Super Team D, RIT", "About", JOptionPane.INFORMATION_MESSAGE);
    }

}
