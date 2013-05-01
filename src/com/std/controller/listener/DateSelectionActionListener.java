package com.std.controller.listener;

import com.std.controller.CalendarController;
import com.std.view.block.DayBlock;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;


/**
 * This class serves as the listener of each day in the view panels.
 *
 * @author xxx
 */
public class DateSelectionActionListener implements ActionListener{

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
	public DateSelectionActionListener(CalendarController cc){
		controller = cc;
	}

	/**
	 * This method is called whenever the user clicks a day in the view. This
	 * method then updates the currentDate in the model.
	 *
	 * @param e is the event sent when the day in the panel is
	 *                    clicked.
	 */
	public void actionPerformed(ActionEvent e){
		//get the day block that this listener was listening too
		DayBlock db = (DayBlock) e.getSource();
		Date newSelectedDate = db.getDate();
		controller.getModel().setCurrentDate(newSelectedDate);
	}

}
