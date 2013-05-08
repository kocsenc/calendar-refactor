package com.std.view;

import com.std.controller.CalendarController;
import com.std.controller.commands.ExportCommand;
import com.std.model.export.ExportStrategyFactory;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.IOException;

/**
 * @author: Jenny Zhen; jenz.rit@gmail.com
 * date: 05.03.2013
 * language: Java
 * project: calendar-refactor
 */

public class ExportPanel {

	private final FileNameExtensionFilter CSV =
		new FileNameExtensionFilter("Comma-Separated Values (*.csv)", ".csv");
	private final FileNameExtensionFilter XML =
		new FileNameExtensionFilter("Extensible Markup Language (*.xml)", ".xml");
	private final FileNameExtensionFilter DCAL =
		new FileNameExtensionFilter("Calendar (*.cal)", ".dcal");

	public ExportPanel(JFrame parent, CalendarController cc) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.addChoosableFileFilter(CSV);
		fileChooser.addChoosableFileFilter(XML);
		fileChooser.addChoosableFileFilter(DCAL);
		int result = fileChooser.showSaveDialog(parent);
		FileFilter filter = fileChooser.getFileFilter();

		if(result == JFileChooser.APPROVE_OPTION){
			String id = "";
			if(filter == CSV)
				id = ExportStrategyFactory.CSV;
			else if(filter == XML)
				id = ExportStrategyFactory.XML;
			else if(filter == DCAL)
				id = ExportStrategyFactory.DCAL;
			try {
				cc.getModel().save(fileChooser.getSelectedFile(), id);
			} catch (IOException e) {
				e.printStackTrace();
			};
		}
	}
}
