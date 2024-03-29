package com.std.controller.listener;

import com.std.controller.CalendarController;
import com.std.controller.commands.NextButtonCommand;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class serves as the Action Listener for the next button in the view.  it
 * is instantiated in CalendarControler and sent to the CalendarView.
 *
 * @author xxx
 */
public class NextButtonActionListener implements ActionListener {

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
    public NextButtonActionListener(CalendarController cc) {
        controller = cc;
    }

    /**
     * This method changes the current date in the model based on the current state
     * of the MainView (aka: based on which panel: monthly, weekly, or daily, is
     * being displayed in MainView).
     *
     * @param e is the ActionEvent passed when the next button is
     *          clicked
     */
    public void actionPerformed(ActionEvent e) {
        new NextButtonCommand().execute(controller);
    }
}
