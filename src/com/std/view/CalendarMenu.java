package com.std.view;

import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * The is the menu
 * 
 * @author xxx
 *
 */

public class CalendarMenu extends JMenuBar {
	/**
	 * UID Used for Serializable
	 */
	private static final long serialVersionUID = 2649421712299673376L;
	
	/**
	 * These will be the items displayed in the menue
	 */
	
	private final JMenuItem newCalendar;
	private final JMenuItem openCalendar;
	private final JMenuItem saveCalendar;
	private final JMenuItem saveAsCalendar;
	private final JMenuItem exitApplication;
	private final JMenuItem newAppointment;
	private final JMenuItem editAppointment;
	private final JMenuItem editAllAppointment;
	private final JMenuItem removeAppointment;
	private final JMenuItem removeAllAppointment;
	private final JMenuItem preferences;
	private final JMenuItem about;
	
	public JMenuItem getNewCalendarMenuItem() {
		return newCalendar;
	}
	public JMenuItem getOpenCalendarMenuItem() {
		return openCalendar;
	}
	public JMenuItem getSaveCalendarMenuItem() {
		return saveCalendar;
	}
	public JMenuItem getSaveAsCalendarMenuItem() {
		return saveAsCalendar;
	}
	public JMenuItem getExitApplicationMenuItem() {
		return exitApplication;
	}
	public JMenuItem getNewAppointmentMenuItem() {
		return newAppointment;
	}
	public JMenuItem getEditAppointmentMenuItem() {
		return editAppointment;
	}
	public JMenuItem getEditAllAppointmentMenuItem() {
		return editAllAppointment;
	}
	public JMenuItem getRemoveAppointmentMenuItem() {
		return removeAppointment;
	}
	public JMenuItem getRemoveAllAppointmentMenuItem() {
		return removeAllAppointment;
	}
	public JMenuItem getPreferencesMenuItem() {
		return preferences;
	}
	public JMenuItem getAboutMenuItem() {
		return about;
	}

	/**
	 * This is the constructor for the menu, it sets up how it
	 * is going to look and initializes all of the menu items
	 */
	
	public CalendarMenu() {
		super();
		
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		add(fileMenu);
		
		newCalendar = new JMenuItem("New Calendar");
		newCalendar.setMnemonic(KeyEvent.VK_N);
		fileMenu.add(newCalendar);
		
		openCalendar = new JMenuItem("Open File...");
		openCalendar.setMnemonic(KeyEvent.VK_O);
		fileMenu.add(openCalendar);
		
		fileMenu.addSeparator();
		
		saveCalendar = new JMenuItem("Save");
		saveCalendar.setMnemonic(KeyEvent.VK_S);
		fileMenu.add(saveCalendar);
		
		saveAsCalendar = new JMenuItem("Save As...");
		fileMenu.add(saveAsCalendar);
		
		fileMenu.addSeparator();
		
		exitApplication = new JMenuItem("Exit");
		fileMenu.add(exitApplication);
		
		
		JMenu editMenu = new JMenu("Edit");
		editMenu.setMnemonic(KeyEvent.VK_E);
		add(editMenu);
		
		newAppointment = new JMenuItem("New Appointment...");
		editMenu.add(newAppointment);
		
		editMenu.addSeparator();
		
		editAllAppointment = new JMenuItem("Edit Appointment...");
		editMenu.add(editAllAppointment);
		
		editAppointment = new JMenuItem("Disjoin and Edit Appointment...");
		editMenu.add(editAppointment);
		
		editMenu.addSeparator();
		
		removeAppointment = new JMenuItem("Remove Single Appointment");
		editMenu.add(removeAppointment);
		
		removeAllAppointment = new JMenuItem("Remove All Recurring Appointments");
		editMenu.add(removeAllAppointment);
		
		editMenu.addSeparator();
		
		preferences = new JMenuItem("Preferences...");
		editMenu.add(preferences);
		
		
		JMenu helpMenu = new JMenu("Help");
		helpMenu.setMnemonic(KeyEvent.VK_H);
		add(helpMenu);
		
		about = new JMenuItem("About");
		helpMenu.add(about);
	}
}
