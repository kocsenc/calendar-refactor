package com.std.controller.dialog;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * DatePanel is a helper class for the AppointmentDialog and RecurrencePattern 
 * dialogs.  Both require editing one or more dates, and this provides a convenient
 * and standardized way of doing it.<br/>
 * <br/>
 * Usage:<br/>
 * <tt>DatePanel datePanel = new DatePanel(new Date(), true);</tt>
 * 
 * @author xxx
 *
 */
public class DatePanel extends JPanel {
	
	/**
	 * UID Used for Serializable
	 */
	private static final long serialVersionUID = -1945081777034974946L;
	
	/**
	 * format to use when displaying date information
	 */
	private static final SimpleDateFormat FORMAT = new SimpleDateFormat("EEE, d MMM yyyy 'at' h:mm aa");

	/**
	 * ActionListener intended to be used with 
	 * the "..." button on the DatePanel form.
	 * 
	 * Prompts the user to select a date, then 
	 * sets its internal date to the new input
	 * 
	 * @author xxx
	 *
	 */
	private class EllipsisActionListener implements ActionListener {

	    /**
	     * Invoked when the "..." button is pressed.
		 * 
		 * Prompts the user to select a date, then 
		 * sets its internal date to the new input
	     * 
	     * @param e not used
	     */
		public void actionPerformed(ActionEvent e) {
			
			// get the new date
			Date tempDate = DateDialog.getDate(dialog, date);
			
			// only set the change if the user didn't cancel out
			if(tempDate != null)
				date = tempDate;
			
			// update the date display
			label.setText(FORMAT.format(date));
			
			// notify all auxiliary action listeners
			for(ActionListener al : listeners)
				al.actionPerformed(e);
		}
	}
	
	/**
	 * the <code>Frame</code> from which the dialog is displayed
	 */
	private Dialog dialog;
	
	/**
	 * current date display
	 */
	private JTextField label;
	
	/**
	 * currently selected date
	 */
	private Date date;
	
	/**
	 * auxilliary action listeners that should
	 * be notified after the ellipsis button has
	 * been pressed
	 */
	private Set<ActionListener> listeners;
	
	/**
	 * Returns the currently selected date
	 * 
	 * @return the currently selected date
	 */
	public Date getDate() {
		return date;
	}
	
	/**
	 * Adds an auxiliary action listener that should be 
	 * notified after the ellipsis button has been pressed
	 * 
	 * @param listener the <code>ActionListener</code> to be added
	 */
	public void addActionListener(ActionListener listener) {
		listeners.add(listener);
	}
	
	/**
	 * Removes an auxiliary action listener that would otherwise be 
	 * notified after the ellipsis button has been pressed
	 * 
	 * @param listener the <code>ActionListener</code> to be removed
	 */
	public void removeActionListener(ActionListener listener) {
		listeners.remove(listener);
	}
	
	/**
	 * creates a new DatePanel with an initial 
	 * date and the given enabled button state
	 * 
	 * @param date the initially selected date
	 * @param enableAppt true iff the ellipsis button should be enabled
	 */
	public DatePanel(Date date, boolean enableAppt) {
		super();
		this.date = date;
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
