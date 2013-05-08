package com.std.view;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

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
	private final FileNameExtensionFilter CAL =
		new FileNameExtensionFilter("Calendar (*.cal)", ".cal");

	public ExportPanel(JFrame parent) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.addChoosableFileFilter(CSV);
		fileChooser.addChoosableFileFilter(XML);
		fileChooser.addChoosableFileFilter(CAL);
		fileChooser.showSaveDialog(parent);
	}
}
