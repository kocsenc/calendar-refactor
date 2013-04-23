package com.std.controller.listener;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import com.std.controller.CalendarController;

public class SaveAsCalendarActionListener implements ActionListener {

	/**
	 * a reference to the controller so that this listener
	 * can access both the model and the view.
	 */
	private CalendarController controller;

	/**
	 * creates a new AppointmentSelectionMouseListener
	 * 
	 * @param CalendarControler cc is the reference to the controller 
	 */
	public SaveAsCalendarActionListener(CalendarController cc){
		controller = cc;
	}
	 /** This allows the user to save a calendar when they 
	 * 	click the saveAs  button.
	 * 
	 * 	@param ActionEvent e is the event sent to this method when
	 * 	the user pressed the saveAs button.
	 */
	public void actionPerformed(ActionEvent e) {
		controller.saveAs();
	}

}
