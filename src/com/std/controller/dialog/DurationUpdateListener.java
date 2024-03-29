package com.std.controller.dialog;

import com.std.model.appointment.AppointmentUtility;
import com.std.util.range.DateRange;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * DurationUpdateListener is a helper class for the AppointmentDialog and
 * RecurrencePattern dialogs.  Both require observing one or more DatePanels for
 * changes in duration, and this provides a convenient and standardized way of
 * doing it.
 * <p/>
 * Usage: JLabel field; DatePanel start; DatePanel end;
 * <p/>
 * DurationUpdateListener listener = new DurationUpdateListener(field, start,
 * end);
 */
class DurationUpdateListener implements ActionListener{

	/**
	 * prints the the duration of two dates to a JLabel
	 *
	 * @param field JLabel to print to
	 * @param start start date of the range
	 * @param end   end date of the range
	 */
	public static void updateDuration(
			JLabel field, DatePanel start, DatePanel end){
		// set the text
		field.setText(AppointmentUtility.getDurationDescription(
				new DateRange(start.getDate(), end.getDate()).getDurationInMS()));
	}

	private final JLabel field;  //JLabel to print to

	private final DatePanel start;  //start date of the range

	private final DatePanel end; //end date of the range

	/**
	 * called whenever the start or end dates are changed, and updates duration
	 *
	 * @param e not used
	 */
	public void actionPerformed(ActionEvent e){
		updateDuration(field, start, end);
	}

	/**
	 * creates a new DurationUpdateListener for the given field and dates
	 *
	 * @param field JLabel to print to
	 * @param start start date of the range
	 * @param end   end date of the range
	 */
	public DurationUpdateListener(JLabel field, DatePanel start, DatePanel end){
		this.field = field;
		this.start = start;
		this.end = end;
	}
}
