/**
 * @author: Jenny Zhen; jenz.rit@gmail.com
 * date: 05.02.2013
 * language: Java
 * project: calendar-refactor
 */

package com.std.view.export;

import javax.swing.*;
import java.awt.event.ActionEvent;

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

	private JComboBox createOptions() {
		fileType = new JComboBox(); // put in parameters
		fileType.setSelectedIndex(0);
		//fileType.addActionListener();
		public void actionPerformed(ActionEvent e) {
			JComboBox cb = (JComboBox)e.getSource();
			String type = (String)cb.getSelectedItem();
		}
	}
}
