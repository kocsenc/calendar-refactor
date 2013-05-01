package com.std.controller.listener;

import com.std.controller.CalendarController;
import com.std.controller.dialog.AppointmentDialog;
import com.std.model.CalendarModelUtility;
import com.std.model.appointment.RefAppointment;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewAppointmentActionListener implements ActionListener{

	/**
	 * a reference to the controller so that this listener can access both the
	 * model and the view.
	 */
	private final CalendarController controller;

	/**
	 * creates a new AppointmentSelectionMouseListener
	 *
	 * @param CalendarControler cc is the reference to the controller
	 */
	public NewAppointmentActionListener(CalendarController cc){
		controller = cc;
	}

	/**
	 * This when the user clicks the new appointment button, it creates a dialogue
	 * box that allows the user to enter new information
	 */

	public void actionPerformed(ActionEvent e){
		RefAppointment ref =
				CalendarModelUtility.getNewAppointment(controller.getModel());

		if(AppointmentDialog.changeAppointment(controller.getView(), ref)){
			CalendarModelUtility.addUsingPattern(controller.getModel(), ref);
		}
	}

}
