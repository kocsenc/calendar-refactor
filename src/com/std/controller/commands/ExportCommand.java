package com.std.controller.commands;

import com.std.controller.CalendarController;
import com.std.view.ExportPanel;

/**
 * Author:      Grant Kurtz
 */
public class ExportCommand implements Command{


	public void execute(CalendarController cc){
		new ExportPanel();
	}
}
