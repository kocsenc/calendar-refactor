package com.std.controller.listener;

import com.std.controller.CalendarController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * this is the action listener that is added to the load button
 *
 * @author xxx
 */
public class OpenCalendarActionListener implements ActionListener{

	private class OpenRunnable implements Runnable{
		public void run(){
			controller.open();
		}
	}

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
	public OpenCalendarActionListener(CalendarController cc){
		controller = cc;
	}

	public void actionPerformed(ActionEvent e){
		controller.confirm(new OpenRunnable());
	}
}
