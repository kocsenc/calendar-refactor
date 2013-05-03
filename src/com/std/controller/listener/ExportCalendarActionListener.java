package com.std.controller.listener;

import com.std.controller.CalendarController;
import com.std.view.ExportPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author: Jenny Zhen; jenz.rit@gmail.com date: 05 03, 2013 language: Java
 * project: calendar-refactor3
 */

public class ExportCalendarActionListener implements ActionListener {

	public ExportCalendarActionListener(CalendarController calendarController) {
		//To change body of created methods use File | Settings | File Templates.
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		new ExportPanel();
	}
}
