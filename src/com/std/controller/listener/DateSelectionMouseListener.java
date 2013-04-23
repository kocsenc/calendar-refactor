package com.std.controller.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;

import com.std.controller.CalendarController;
import com.std.view.block.DayBlock;


/**
 * This class serves as the listener of each day in the view panels.
 * 
 * @author xxx
 *
 */
public class DateSelectionMouseListener implements MouseListener {
	
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
	public DateSelectionMouseListener(CalendarController cc){
		controller = cc;
	}
		
	/**
	 * This method is called whenever the user clicks a day in the view.
	 * This method then updates the currentDate in the model.
	 * 
	 * @param MouseEvent e is the event sent when the day in the panel is clicked.
	 */
	public void mouseClicked(MouseEvent e) {
		//get the day block that this listener was listening too
		DayBlock db = (DayBlock) e.getSource();
		Date newSelectedDate = db.getDate();
		controller.getModel().setCurrentDate(newSelectedDate);
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
