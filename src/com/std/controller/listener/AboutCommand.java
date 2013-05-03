package com.std.controller.listener;

import com.std.controller.CalendarController;

import javax.swing.*;

/**
 * This is the listener listening to the about menu item. Selecting this item
 * will pop up addition application information.
 *
 * @author xxx
 */
public class AboutCommand implements Command {


    /**
     * creates a new AboutCommand
     */
    public AboutCommand() {

    }

    @Override
    public void execute(CalendarController cc) {
        System.out.println("Command is actually executed in command");
        JOptionPane.showMessageDialog(cc.getView(), "DCal.  Copyright 2008, Super Team D, RIT", "About", JOptionPane.INFORMATION_MESSAGE);
    }

}
