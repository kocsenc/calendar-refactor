package com.std.controller.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JOptionPane;

import com.std.controller.CalendarController;
import com.std.model.appointment.RefAppointment;

/**
 * This is the listener listening to the remove appointment button
 * If the button is pressed the user will be promted if they are sure
 * they would like to delete the appointment, if it is a recurring appointment
 * then the user will be promted if they would like to remove all of the recurring 
 * appointments
 * 
 * @author xxx
 *
 */

public class RemoveAppointmentActionListener implements ActionListener {

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
	public RemoveAppointmentActionListener(CalendarController cc){
		controller = cc;
	}
	
	/**
	 * This occurs when the user has clicked the remove button, if a 
	 * Appointment is selected then the user is prompted if they are sure
	 * they would like to delete the appointment, if it is a recurring appointment
	 * then the user will be prompted if they would like to remove all of the recurring 
	 * appointments
	 * 
	 * @param e is the action event, it is not used
	 */
	
	public void actionPerformed(ActionEvent e) {
		RefAppointment ref = controller.getModel().getCurrentAppointment();
		if(ref != null)
			controller.getModel().getAppointmentSet().remove(ref);
		else
			JOptionPane.showMessageDialog(controller.getView(), "no appointment is selected", "", JOptionPane.ERROR_MESSAGE);
		
//		if(controller.getModel().getCurrentAppointment() != null){
//			
//			RefAppointment ref = controller.getModel().getCurrentAppointment();
//			int input = JOptionPane.showConfirmDialog(controller.getView(), 
//											"Are you sure you want to delete this appointment? " +
//											"\n" + e.getSource(), "Delete Appointment?",
//											JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
//			if (input == JOptionPane.YES_OPTION) {
//				
//				// Must determine if this is a recurring appointment
//				Set<RefAppointment> tempSet = controller.getModel().getAppointmentSet();
//				Iterator<RefAppointment> i = tempSet.iterator();
//				int count = 0;
//				while(i.hasNext()) {
//					
//					if (i.next().equals(ref)) 
//						count++;
//
//					if (count > 1)
//						break;
//				}
//				
//				if (count > 1) {
//					
//					int selection = JOptionPane.showConfirmDialog(controller.getView(), 
//							"This is a recurring appointment would you like to delete all of the recurrences?" +
//							"\n" + ref.getTitle(), "Delete Appointment?",
//							JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
//					
//					//The user wants to delete all recurring appointments
//					if (selection == JOptionPane.YES_OPTION) {
//						controller.getModel().removeReferences(ref.getTemplate());
//					}
//					
//					// The User wants to delete just this refrence
//					else if (selection == JOptionPane.NO_OPTION) {
//						//This gets the appointment set from the model
//						//and then deletes the current reference appointment
//						tempSet.remove(ref);
//					}
//				}
//				else {
//					//This gets the appointment set from the model
//					//and then deletes the current reference appointment
//					tempSet.remove(ref);
//				}
//			}
//			//End if user wants to delete
//			
//		}
	}
}
