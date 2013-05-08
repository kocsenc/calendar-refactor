package com.std.controller.listener;

import com.std.controller.CalendarController;
import com.std.controller.commands.ExportCommand;
import com.std.view.ExportPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author: Jenny Zhen; jenz.rit@gmail.com
 * date: 05 03, 2013
 * language: Java
 * project: calendar-refactor
 */

public class ExportCalendarActionListener implements ActionListener {

	private final CalendarController calendarController;

	public ExportCalendarActionListener(CalendarController calendarController) {
		this.calendarController = calendarController;
	}

    public void actionPerformed(ActionEvent e) {
        new ExportCommand().execute(calendarController);
    }
}
