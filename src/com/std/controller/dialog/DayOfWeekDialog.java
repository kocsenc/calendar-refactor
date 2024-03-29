package com.std.controller.dialog;

import com.std.model.pattern.DayOfWeekPattern;
import com.std.model.pattern.RecurrencePattern;
import com.std.util.GroupLayoutUtility;
import com.std.util.range.DateRange;
import com.std.util.range.WeekRange;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Locale;

/**
 * DayOfWeekDialog is a utility class of the controller that provides an easy
 * way to get user input in the form of a DayOfWeekPattern.
 * <p/>
 * Usage:
 * <p/>
 * JFrame frame; RecurrencePattern pattern;
 * <p/>
 * // to present the user with an opportunity to create a DayOfWeekPattern
 * DayOfWeekDialog.getPattern(frame, pattern);
 * <p/>
 * The class methods block until the user finishes editing, and returns the
 * constructed DayOfWeekPattern.
 */
public class DayOfWeekDialog extends JDialog{

	// UID Used for Serializable
	private static final long serialVersionUID = 7758394355066019219L;

	private DayOfWeekPattern pattern; // pattern passed as result of user input
	private DatePanel startDate;  //start date field component
	private DatePanel endDate; //end date field component
	private JToggleButton[] days; //toggle buttons for each day of the week

	/**
	 * Prompts the user to create a DayOfWeekPattern
	 *
	 * @param frame   the Frame from which the dialog is displayed
	 * @param pattern pattern to start with
	 *
	 * @return the created DayOfWeekPattern
	 */
	public static DayOfWeekPattern getPattern(
			Frame frame, RecurrencePattern pattern){
		DayOfWeekDialog dialog = new DayOfWeekDialog(frame, pattern);
		dialog.setVisible(true);
		return dialog.getReturnPattern();
	}

	/**
	 * Prompts the user to create a DayOfWeekPattern
	 *
	 * @param frame   the Frame from which the dialog is displayed
	 * @param pattern pattern to start with
	 *
	 * @return the created DayOfWeekPattern
	 */
	public static DayOfWeekPattern getPattern(
			Dialog frame, RecurrencePattern pattern){
		DayOfWeekDialog dialog = new DayOfWeekDialog(frame, pattern);
		dialog.setVisible(true);
		return dialog.getReturnPattern();
	}

	/**
	 * ActionListener intended to be used with the "OK" button on the
	 * DayOfWeekDialog form.
	 *
	 * Applies the changes of the form to the form's DayOfWeekPattern, and
	 * disposes the form if no errors occur.
	 */
	private class OKActionListener implements ActionListener{

		/**
		 * Invoked when the "OK" button is pressed.
		 *
		 * Applies the changes of the form to the form's DayOfWeekPattern, and
		 * disposes the form if no errors occur.
		 *
		 * @param e not used
		 */
		public void actionPerformed(ActionEvent e){
			returnSuccessful();
		}
	}

	/**
	 * @return the DayOfWeekPattern that was created as a result of user input
	 */
	DayOfWeekPattern getReturnPattern(){
		return pattern;
	}

	/**
	 * Applies the changes of the user edit, and disposes the form, returning
	 * control back to the parent form.
	 */
	private void returnSuccessful(){
		try{
			// construct the days selected booleans
			boolean[] b = new boolean[7];
			for(int i = 0; i < 7; i++){
				b[i] = days[i].isSelected();
			}

			// construct the new pattern
			pattern = new DayOfWeekPattern(
					new DateRange(startDate.getDate(), endDate.getDate()), b);

			// dispose of this form
			dispose();

			// if we catch any errors, alert the user and don't dispose the form
		} catch(Exception e) {
			JOptionPane.showMessageDialog(
					this, e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Initializes this DayOfWeekDialog object, acts as a common constructor /
	 * initializer for the actual constructors that would otherwise perform
	 * redundant code
	 *
	 * @param base RecurrencePattern that will be used to initialize the fields
	 */
	private void init(RecurrencePattern base){
		/* Because base could be any type of pattern, we need to somehow convert
		it to a DayOfWeekPattern so we can use it for the defaults.  if it's
		null, make our own default, if it's already a DayOfWeekPattern,
		then cast it, otherwise, use it for its dateRange */
		DayOfWeekPattern pattern;
		if(base == null)
			pattern = new DayOfWeekPattern(new DateRange(), new boolean[7]);
		else if(base instanceof DayOfWeekPattern)
			pattern = (DayOfWeekPattern) base;
		else
			pattern = new DayOfWeekPattern(base.getRange(), new boolean[7]);
		setLayout(new BorderLayout());

		// dateRange fields
		startDate = new DatePanel(pattern.getRange().getStartDate(), true, this);
		endDate = new DatePanel(pattern.getRange().getEndDate(), true, this);
		JLabel duration = new JLabel();

		// dateRange listeners
		startDate.addActionListener(
				new DurationUpdateListener(duration, startDate, endDate));
		endDate.addActionListener(
				new DurationUpdateListener(duration, startDate, endDate));
		DurationUpdateListener.updateDuration(duration, startDate, endDate);

		// panel that holds the day options
		JPanel daysPanel = new JPanel();
		daysPanel.setLayout(new GridLayout(0, 1));
		daysPanel.setBorder(new TitledBorder(new EmptyBorder(
				new Insets(6, 6, 6, 6)), "select the days to recur on"));
		Calendar cal = Calendar.getInstance();
		cal.setTime(new WeekRange().getStartDate());
		days = new JToggleButton[7];
		boolean[] curDays = pattern.getDays();
		for(int i = 0; i < 7; i++){
			days[i] = new JToggleButton(cal.getDisplayName(
					Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault()));
			days[i].setSelected(curDays[i]);
			daysPanel.add(days[i]);
			cal.add(Calendar.DATE, 1);
		}

		// OK button
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new OKActionListener());

		// panel that holds the dateRange data
		JPanel north = new JPanel();
		GroupLayout northLayout = new GroupLayout(north);
		northLayout.setAutoCreateContainerGaps(true);
		northLayout.setAutoCreateGaps(true);
		north.setLayout(northLayout);
		GroupLayoutUtility.addToGroups(northLayout, new Component[][]{
				{new JLabel("start date"), startDate},
				{new JLabel("end date"), endDate}
			//	{new JLabel("duration"), duration}
		});

		// add the panels
		add(north, BorderLayout.NORTH);
		add(daysPanel, BorderLayout.CENTER);
		add(okButton, BorderLayout.SOUTH);

		// set the title
		setTitle("Add / Edit Recurrence Pattern");

		pack();
		setMinimumSize(getSize());
		setLocationRelativeTo(null);
	}

	/**
	 * Creates a new DayOfWeekDialog initialized to an existing pattern.
	 *
	 * @param frame   from which the dialog is displayed
	 * @param pattern pattern to initialize the fields to
	 */
	private DayOfWeekDialog(Frame frame, RecurrencePattern pattern){
		super(frame, true);
		init(pattern);
	}

	/**
	 * Creates a new DayOfWeekDialog initialized to an existing pattern.
	 *
	 * @param frame   from which the dialog is displayed
	 * @param pattern pattern to initialize the fields to
	 */
	private DayOfWeekDialog(Dialog frame, RecurrencePattern pattern){
		super(frame, true);
		init(pattern);
	}
}
