package com.std.controller.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.std.controller.CalendarController;

/**
 * This is the listener listening to the about menu item.
 * Selecting this item will pop up addition application information.
 * 
 * @author xxx
 *
 */
public class AboutActionListener implements ActionListener {

	/**
	 * a reference to the controller so that this listener
	 * can access both the model and the view.
	 */
	private CalendarController controller;

	/**
	 * creates a new AboutActionListener
	 * 
	 * @param CalendarControler cc is the reference to the controller 
	 */
	public AboutActionListener(CalendarController cc){
		controller = cc;
	}
	
	/**
	 * This occurs when the user has selected the about menu item.
	 * Selecting this item will pop up addition application information.
	 * 
	 * @param e is the action event, it is not used
	 */
	public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(controller.getView(), "DCal.  Copyright 2008, Super Team D, RIT", "About", JOptionPane.INFORMATION_MESSAGE);
	}
}
