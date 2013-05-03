package com.std.view;

import com.std.controller.CalendarController;
import com.std.controller.listener.AboutCommand;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * The is the menu
 */

public class CalendarMenu extends JMenuBar {
    /**
     * UID Used for Serializable
     */
    private static final long serialVersionUID = 2649421712299673376L;

    /**
     * These will be the items displayed in the menu
     */

    private final JMenuItem newCalendar, openCalendar, saveCalendar, saveAsCalendar, exitApplication,
            newAppointment, editAppointment, editAllAppointment, removeAppointment, removeAllAppointment,
            preferences, about;
    private CalendarController cc;

    /**
     * This is the constructor for the menu, it sets up how it is going to look and
     * initializes all of the menu items
     */
    public CalendarMenu(CalendarController c) {
        cc = c;
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        add(fileMenu);

        newCalendar = new JMenuItem("New Calendar");
        newCalendar.setMnemonic(KeyEvent.VK_N);
        fileMenu.add(newCalendar);

        openCalendar = new JMenuItem("Open File...");
        openCalendar.setMnemonic(KeyEvent.VK_O);
        fileMenu.add(openCalendar);

        fileMenu.addSeparator();

        saveCalendar = new JMenuItem("Save");
        saveCalendar.setMnemonic(KeyEvent.VK_S);
        fileMenu.add(saveCalendar);

        saveAsCalendar = new JMenuItem("Save As...");
        fileMenu.add(saveAsCalendar);

        fileMenu.addSeparator();

        exitApplication = new JMenuItem("Exit");
        fileMenu.add(exitApplication);


        JMenu editMenu = new JMenu("Edit");
        editMenu.setMnemonic(KeyEvent.VK_E);
        add(editMenu);

        newAppointment = new JMenuItem("New Appointment...");
        editMenu.add(newAppointment);

        editMenu.addSeparator();

        editAllAppointment = new JMenuItem("Edit Appointment...");
        editMenu.add(editAllAppointment);

        editAppointment = new JMenuItem("Disjoin and Edit Appointment...");
        editMenu.add(editAppointment);

        editMenu.addSeparator();

        removeAppointment = new JMenuItem("Remove Single Appointment");
        editMenu.add(removeAppointment);

        removeAllAppointment =
                new JMenuItem("Remove All Recurring Appointments");
        editMenu.add(removeAllAppointment);

        editMenu.addSeparator();

        preferences = new JMenuItem("Preferences...");
        editMenu.add(preferences);


        JMenu helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_H);
        add(helpMenu);

        about = new JMenuItem("About");
        helpMenu.add(about);

    }


    /**
     * Getters and setters for all of the menu items to add an action listeners;
     */
    public JMenuItem getNewCalendarMenuItem() {
        return newCalendar;
    }

    public JMenuItem getOpenCalendarMenuItem() {
        return openCalendar;
    }

    public JMenuItem getSaveCalendarMenuItem() {
        return saveCalendar;
    }

    public JMenuItem getSaveAsCalendarMenuItem() {
        return saveAsCalendar;
    }

    public JMenuItem getExitApplicationMenuItem() {
        return exitApplication;
    }

    public JMenuItem getNewAppointmentMenuItem() {
        return newAppointment;
    }

    public JMenuItem getEditAppointmentMenuItem() {
        return editAppointment;
    }

    public JMenuItem getEditAllAppointmentMenuItem() {
        return editAllAppointment;
    }

    public JMenuItem getRemoveAppointmentMenuItem() {
        return removeAppointment;
    }

    public JMenuItem getRemoveAllAppointmentMenuItem() {
        return removeAllAppointment;
    }

    public JMenuItem getPreferencesMenuItem() {
        return preferences;
    }

    public JMenuItem getAboutMenuItem() {

        return about;
    }
}
