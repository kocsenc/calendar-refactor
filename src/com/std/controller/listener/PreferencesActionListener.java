package com.std.controller.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.std.controller.CalendarController;
import com.std.controller.dialog.AppointmentDialog;

/**
 * PreferencesActionListener should listen to any click actions on the 
 * Preferences menu item.  When fired, it should open up the model's
 * appointment defaults for editing.
 * 
 * @author xxx
 *
 */
public class PreferencesActionListener implements ActionListener {

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
	public PreferencesActionListener(CalendarController cc){
		controller = cc;
	}
	
	/**
	 * This is called when the preferences menu item has been selected. 
	 * When fired, it should open up the model's appointment defaults for editing.
	 * 
	 * @param e not used
	 */
	public void actionPerformed(ActionEvent e) {
		AppointmentDialog.changeAppointmentDefaults(controller.getView(), controller.getModel());
	}
}
