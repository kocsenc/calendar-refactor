package com.std.view.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.std.model.appointment.AppointmentUtility;
import com.std.model.appointment.RefAppointment;
import com.std.util.GroupLayoutUtility;

/**
 * This is the side of the main window, it allows 
 * the user easy access to the currently selected 
 * appointment
 * 
 * @author xxx
 *
 */

public class AppointmentPanel extends JToggleButton {
	private static final SimpleDateFormat FORMAT = new SimpleDateFormat("EEE, d MMM yyyy 'at' h:mm aa");
	
	/**
	 *  These are all of the data fields that can be
	 *  viewed by the user
	 */
	
	private JTextArea titleField;
	private JTextArea locationField;
	private JTextArea startDateField;
	private JTextArea endDateField;
	private JTextArea durationField;
	private JTextArea recurrenceField;
	private JTextArea descriptionField;
	
	private JButton editSingleAppt;
	private JButton editAllAppt;
	private JButton removeSingleAppt;
	private JButton removeAllAppt;
	
	public void addEditSingleListener(ActionListener listener) {
		editSingleAppt.addActionListener(listener);
	}
	public void addEditRecurringListener(ActionListener listener) {
		editAllAppt.addActionListener(listener);
	}
	
	public void addRemoveSingleListener(ActionListener listener) {
		removeSingleAppt.addActionListener(listener);
	}
	public void addRemoveRecurringListener(ActionListener listener) {
		removeAllAppt.addActionListener(listener);
	}
	
	/**
	 * This updates the display to display the current appointment
	 * 
	 * @param appt is the appointment to be displayed
	 */
	
	public void setAppointment(RefAppointment appt) {
		if(appt == null) {
			titleField.setText("");
			locationField.setText("");
			startDateField.setText("");
			endDateField.setText("");
			durationField.setText("");
			recurrenceField.setText("");
			descriptionField.setText("");
			
			editSingleAppt.setEnabled(false);
			editAllAppt.setEnabled(false);
			removeSingleAppt.setEnabled(false);
			removeAllAppt.setEnabled(false);
		} else {
			titleField.setText(appt.getTitle());
			locationField.setText(appt.getLocation());
			startDateField.setText(FORMAT.format(appt.getStartDate()));
			endDateField.setText(FORMAT.format(appt.getEndDate()));
			durationField.setText(AppointmentUtility.getDurationDescription(appt.getDuration()));
			recurrenceField.setText(AppointmentUtility.getPatternDescription(appt.getPattern()));
			descriptionField.setText(appt.getDescription());
			
			editSingleAppt.setEnabled(appt.getPattern() != null);
			editAllAppt.setEnabled(true);
			removeSingleAppt.setEnabled(true);
			removeAllAppt.setEnabled(appt.getPattern() != null);
		}
	}
	
	/**
	 * This method creates a text area, because all of the text area
	 * are created in the same way, this can be used
	 * to create all of the text areas
	 * @return returns a new JTextArea
	 */
	
	private JTextArea createTextArea() {
		JTextArea ret = new JTextArea();
		ret.setBorder(new LineBorder(new Color(0x80FFFFFF, true)));
		ret.setEditable(false);
		ret.setOpaque(false);
		ret.setWrapStyleWord(true);
		ret.setLineWrap(true);
		ret.setFocusable(false);
		return ret;
	}
	
	/**
	 * This is the constructor of the appointment panel it
	 * creates all of the data fields and sets up the buttons
	 * and actionlisteners
	 */
	
	public AppointmentPanel() {
		super();
		
		titleField = createTextArea();
		locationField = createTextArea();
		startDateField = createTextArea();
		endDateField = createTextArea();
		durationField = createTextArea();
		recurrenceField = createTextArea();
		descriptionField = createTextArea();
		descriptionField.setBorder(null);
		
		editSingleAppt = new JButton("disjoin and edit");
		editAllAppt = new JButton("edit");
		removeSingleAppt = new JButton("remove");
		removeAllAppt = new JButton("remove recurring");
		

		JPanel northDataPanel = new JPanel();
		northDataPanel.setOpaque(false);
		GroupLayout groupLayout = new GroupLayout(northDataPanel);
		groupLayout.setAutoCreateContainerGaps(false);
		groupLayout.setAutoCreateGaps(true);
		GroupLayoutUtility.addToGroups(
			groupLayout, 
			new Component[][] {
				{new JLabel("title"), titleField},
				{new JLabel("location"), locationField},
				{new JLabel("start date"), startDateField},
				{new JLabel("end date"), endDateField},
				{new JLabel("duration"), durationField},
				{new JLabel("recurrence"), recurrenceField},
				{new JLabel("description"), new JLabel()}
			});
		northDataPanel.setLayout(groupLayout);
		
		JLabel label = new JLabel("selected appointment");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBorder(new EmptyBorder(4, 0, 10, 0));
		
		JPanel north = new JPanel();
		north.setOpaque(false);
		north.setLayout(new BorderLayout());
		north.add(label, BorderLayout.NORTH);
		north.add(northDataPanel, BorderLayout.CENTER);
		
		JScrollPane scroll = new JScrollPane(descriptionField);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setOpaque(false);
		scroll.getViewport().setOpaque(false);
		scroll.setBorder(new LineBorder(new Color(0x80FFFFFF, true), 1));
		
		JPanel buttons = new JPanel();
		buttons.setOpaque(false);
		buttons.setBorder(new EmptyBorder(10, 0, 4, 0));
		buttons.setLayout(new GridLayout(0, 2));
		buttons.add(editAllAppt);
		buttons.add(editSingleAppt);
		buttons.add(removeSingleAppt);
		buttons.add(removeAllAppt);
		

		setSelected(true);
		setEnabled(false);
		setLayout(new BorderLayout());
		add(north, BorderLayout.NORTH);
		add(scroll, BorderLayout.CENTER);
		add(buttons, BorderLayout.SOUTH);
		
		setAppointment(null);
	}
}
