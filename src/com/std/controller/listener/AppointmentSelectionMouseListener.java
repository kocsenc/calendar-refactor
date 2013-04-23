package com.std.controller.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.std.controller.CalendarController;
import com.std.view.block.AppointmentBlock;



/**
 * This class serves as the listener of each appointment in the view panels.
 * 
 * @author xxx
 *
 */
public class AppointmentSelectionMouseListener implements MouseListener {
	
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
	public AppointmentSelectionMouseListener(CalendarController cc){
		controller = cc;
	}
	
	/**
	 * This method is called whenever the user clicks an appointment in the view.
	 * This method then updates the currentAppointment in the model.
	 * 
	 * @param MouseEvent e is the event sent when the appointment in the panel is clicked.
	 */
	public void mouseClicked(MouseEvent e) {
		AppointmentBlock appt = (AppointmentBlock) e.getSource();
		controller.getModel().setCurrentAppointment(appt.getAppointment());
	}

	/**
	 * Not used; added to satisfy the interface
	 */
	public void mouseEntered(MouseEvent arg0) {}

	/**
	 * Not used; added to satisfy the interface
	 */
	public void mouseExited(MouseEvent arg0) {}

	/**
	 * Not used; added to satisfy the interface
	 */
	public void mousePressed(MouseEvent arg0) {}

	/**
	 * Not used; added to satisfy the interface
	 */
	public void mouseReleased(MouseEvent arg0) {}

}
