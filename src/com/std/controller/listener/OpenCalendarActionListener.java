package com.std.controller.listener;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JOptionPane;

import com.std.controller.CalendarController;
import com.std.model.CalendarModel;


/**
 * this is the action listener that is added to the load button
 * 
 * @author xxx
 * 
 */
public class OpenCalendarActionListener implements ActionListener {
	
	private class OpenRunnable implements Runnable {
		public void run() {
			controller.open();
		}
	}

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
	public OpenCalendarActionListener(CalendarController cc){
		controller = cc;
	}
	
	public void actionPerformed(ActionEvent e) {
		controller.confirm(new OpenRunnable());
	}
}
