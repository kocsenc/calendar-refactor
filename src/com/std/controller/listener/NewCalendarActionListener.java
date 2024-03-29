package com.std.controller.listener;

import com.std.controller.CalendarController;
import com.std.controller.commands.NewCalendarCommand;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * This class represents the listener for the new calendar button.  It resets
 * the calendar to a new one.
 *
 * @author xxx
 */
public class NewCalendarActionListener implements ActionListener {

    /**
     * a reference to the controller so that this listener can access both the
     * model and the view.
     */
    private final CalendarController controller;

    /**
     * creates a new AppointmentSelectionMouseListener
     *
     * @param cc is the reference to the controller
     */
    public NewCalendarActionListener(CalendarController cc) {
        controller = cc;
    }

    /**
     * This method resets the calendar to a blank one.
     *
     * @param e is the event spawn when the new calendar button is
     *          pressed.
     */
    public void actionPerformed(ActionEvent e) {
        new NewCalendarCommand().execute(controller);
    }

}



