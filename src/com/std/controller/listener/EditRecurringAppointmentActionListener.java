package com.std.controller.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.std.controller.CalendarController;
import com.std.controller.dialog.AppointmentDialog;
import com.std.model.CalendarModelUtility;
import com.std.model.appointment.AppointmentTemplate;
import com.std.model.appointment.RefAppointment;

public class EditRecurringAppointmentActionListener implements ActionListener {

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
	public EditRecurringAppointmentActionListener(CalendarController cc){
		controller = cc;
	}
	
	/**
	 * This allows the user to modify an appointment when they 
	 * click the edit button.
	 * 
	 * @param ActionEvent e is the event sent to this method when
	 * the user pressed the edit button.
	 */
	public void actionPerformed(ActionEvent e) {
		RefAppointment ref = controller.getModel().getCurrentAppointment();
		if(ref != null) {
			AppointmentDialog.changeAppointment(controller.getView(), ref);
			
			CalendarModelUtility.addUsingPattern(controller.getModel(), ref);
		} else
			JOptionPane.showMessageDialog(controller.getView(), "no appointment is selected", "", JOptionPane.ERROR_MESSAGE);
	}

}
