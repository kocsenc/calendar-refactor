package com.std.controller.listener;

import com.std.controller.CalendarController;
import com.std.controller.commands.EditAppointmentCommand;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditAppointmentActionListener implements ActionListener {

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
    public EditAppointmentActionListener(CalendarController cc) {
        controller = cc;
    }

    /**
     * This allows the user to modify an appointment when they click the edit
     * button.
     *
     * @param e is the event sent to this method when the user pressed
     *          the edit button.
     */
    public void actionPerformed(ActionEvent e) {
        new EditAppointmentCommand().execute(controller);
    }

}
