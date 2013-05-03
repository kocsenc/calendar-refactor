/**
 * @author: Jenny Zhen; jenz.rit@gmail.com
 * date: 05.02.2013
 * language: Java
 * project: calendar-refactor
 */

package com.std.view.export;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class ExportPanel extends JDialog {
	public static enum EXPORT_TYPE {
		CSV,
		DCAL
	}

	private JButton exportButton; // button to export the calendar
	private JButton cancelButton; // button to exit out of the export menu
	private JLabel nameLabel; // label for the fileName text area
	private JTextArea fileName; // text area for the file name
	private JComboBox fileType; // drop down menu for file type
	private List<String> options;

	private JComboBox createOptions() {
		options = new ArrayList<String>();
		for(EXPORT_TYPE type : EXPORT_TYPE.values())
			options.add(type.name());
		fileType = new JComboBox(options.toArray()); // put in parameters
		fileType.setSelectedIndex(0);
		//fileType.addActionListener();
		return null;
	}

	public void actionPerformed(ActionEvent e) {
		JComboBox cb = (JComboBox)e.getSource();
		String type = (String)cb.getSelectedItem();
	}
}
