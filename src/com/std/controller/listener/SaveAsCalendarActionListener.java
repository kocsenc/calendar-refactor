package com.std.controller.listener;

import com.std.controller.CalendarController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveAsCalendarActionListener implements ActionListener{

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
	public SaveAsCalendarActionListener(CalendarController cc){
		controller = cc;
	}

	/**
	 * This allows the user to save a calendar when they click the saveAs  button.
	 *
	 * @param e is the event sent to this method when the user pressed
	 *                    the saveAs button.
	 */
	public void actionPerformed(ActionEvent e){
		controller.saveAs();
	}

}
