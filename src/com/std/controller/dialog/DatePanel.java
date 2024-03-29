package com.std.controller.dialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * DatePanel is a helper class for the AppointmentDialog and RecurrencePattern
 * dialogs.  Both require editing one or more dates, and this provides a
 * convenient and standardized way of doing it.<br/> <br/> Usage:<br/>
 * <tt>DatePanel datePanel = new DatePanel(new Date(), true);</tt>
 */
public class DatePanel extends JPanel{

	// UID Used for Serializable
	private static final long serialVersionUID = -1945081777034974946L;

	// format to use when displaying date information
	private static final SimpleDateFormat FORMAT =
			new SimpleDateFormat("EEE, d MMM yyyy");

	/**
	 * the Frame from which the dialog is displayed
	 */
	private Dialog dialog;

	/**
	 * current date display
	 */
	private final JTextField label;

	/**
	 * currently selected date
	 */
	private Date date;

	/**
	 * auxilliary action listeners that should be notified after the ellipsis
	 * button has been pressed
	 */
	private final Set<ActionListener> listeners;

	/**
	 * ActionListener intended to be used with the "..." button on the DatePanel
	 * form.
	 *
	 * Prompts the user to select a date, then sets its internal date to the new
	 * input
	 */
	private class EllipsisActionListener implements ActionListener{

		/**
		 * Invoked when the "..." button is pressed.
		 *
		 * Prompts the user to select a date, then sets its internal date to the new
		 * input
		 *
		 * @param e not used
		 */
		public void actionPerformed(ActionEvent e){

			// get the new date
			Date tempDate = DateDialog.getDate(dialog, date);

			// only set the change if the user didn't cancel out
			if(tempDate != null){
				date = tempDate;
			}

			// update the date display
			label.setText(FORMAT.format(date));

			// notify all auxiliary action listeners
			for(ActionListener al : listeners){
				al.actionPerformed(e);
			}
		}
	}

	/**
	 * @return the currently selected date
	 */
	public Date getDate(){
		return date;
	}

	/**
	 * Adds an auxiliary action listener that should be notified after the
	 * ellipsis button has been pressed.
	 *
	 * @param listener the <code>ActionListener</code> to be added
	 */
	public void addActionListener(ActionListener listener){
		listeners.add(listener);
	}

	/**
	 * Creates a new DatePanel with an initial date and the given enabled
	 * button state.
	 *
	 * @param date       the initially selected date
	 * @param enableAppt true iff the ellipsis button should be enabled
	 * @param dialog 	 dialog
	 */
	public DatePanel(Date date, boolean enableAppt, Dialog dialog){
		super();
		this.date = date;
		this.dialog = dialog;
		this.listeners = new HashSet<ActionListener>();

		// date display
		label = new JTextField(FORMAT.format(date));
		label.setEditable(false);
		label.setPreferredSize(new Dimension(200, 0));
		label.setHorizontalAlignment(SwingConstants.CENTER);

		// ellipsis button
		JButton button = new JButton("...");
		button.addActionListener(new EllipsisActionListener());
		button.setEnabled(enableAppt);


		// initialize this DatePanel
		setLayout(new BorderLayout());
		add(label, BorderLayout.CENTER);
		add(button, BorderLayout.EAST);
	}
}
