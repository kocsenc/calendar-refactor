package com.std.controller.dialog;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

import com.std.util.range.GridMonthRange;

/**
 * DateDialog is a utility class of the controller that 
 * provides an easy way to get user input in the form of an
 * Date. <br/>
 * <br/>
 * Usage:</br>
 * <tt>
 * JFrame frame;</br>
 * Date startDate;</br>
 * </br>
 * // to present the user with an opportunity to select a date</br>
 * AppointmentDialog.changeAppointment(frame, startDate);</br></tt>
 * </br>
 * The class methods block until the user finishes editing, and
 * returns the selected date.
 * 
 * @author xxx
 *
 */
public class DateDialog extends JDialog {
	
	/**
	 * UID Used for Serializable
	 */
	private static final long serialVersionUID = -8475931971102498692L;

	/**
	 * Prompts the user to select a Date.
	 * 
	 * @param frame the <code>Frame</code> from which the dialog is displayed
	 * @param startDate date to initially focus on
	 * @return the selected date
	 */
	public static Date getDate(Frame frame, Date startDate) {
		DateDialog dialog = new DateDialog(frame, startDate);
		dialog.setVisible(true);
		return dialog.getReturnDate();
	}

	/**
	 * Prompts the user to select a Date.
	 * 
	 * @param frame the <code>Frame</code> from which the dialog is displayed
	 * @param startDate date to initially focus on
	 * @return the selected date
	 */
	public static Date getDate(Dialog frame, Date startDate) {
		DateDialog dialog = new DateDialog(frame, startDate);
		dialog.setVisible(true);
		return dialog.getReturnDate();
	}

	/**
	 * ActionListener intended to be used with 
	 * the "<<" button on the DateDialog form.
	 * 
	 * Subtracts 1 year from the currently selected date
	 * 
	 * @author xxx
	 *
	 */
	private class PreviousYearListener implements ActionListener {

	    /**
	     * Invoked when the "<<" button is pressed.
		 * 
		 * Subtracts 1 year from the currently selected date
	     * 
	     * @param e not used
	     */
		public void actionPerformed(ActionEvent e) {
			addDate(Calendar.YEAR, -1);
		}
	}

	/**
	 * ActionListener intended to be used with 
	 * the "<" button on the DateDialog form.
	 * 
	 * Subtracts 1 month from the currently selected date
	 * 
	 * @author xxx
	 *
	 */
	private class PreviousMonthListener implements ActionListener {

	    /**
	     * Invoked when the "<" button is pressed.
		 * 
		 * Subtracts 1 month from the currently selected date
	     * 
	     * @param e not used
	     */
		public void actionPerformed(ActionEvent e) {
			addDate(Calendar.MONTH, -1);
		}
	}

	/**
	 * ActionListener intended to be used with 
	 * the ">" button on the DateDialog form.
	 * 
	 * Adds 1 month to the currently selected date
	 * 
	 * @author xxx
	 *
	 */
	private class NextMonthListener implements ActionListener {

	    /**
	     * Invoked when the ">" button is pressed.
		 * 
		 * Adds 1 month to the currently selected date
	     * 
	     * @param e not used
	     */
		public void actionPerformed(ActionEvent e) {
			addDate(Calendar.MONTH, 1);
		}
	}

	/**
	 * ActionListener intended to be used with 
	 * the ">>" button on the DateDialog form.
	 * 
	 * Adds 1 year to the currently selected date
	 * 
	 * @author xxx
	 *
	 */
	private class NextYearListener implements ActionListener {

	    /**
	     * Invoked when the ">>" button is pressed.
		 * 
		 * Adds 1 year to the currently selected date
	     * 
	     * @param e not used
	     */
		public void actionPerformed(ActionEvent e) {
			addDate(Calendar.YEAR, 1);
		}
	}

	/**
	 * HourListener intended to be used with 
	 * the hourly combo box on the DateDialog form.
	 * 
	 * sets the date to the hour selected
	 * 
	 * @author xxx
	 *
	 */
	private class HourListener implements ActionListener {

	    /**
	     * Invoked when the hourly combo box is changed.
		 * 
		 * sets the date to the hour selected
	     * 
	     * @param e not used
	     */
		public void actionPerformed(ActionEvent e) {
			
			// get the source
			JComboBox source = (JComboBox)e.getSource();
			
			// get the hour
			int hour = source.getSelectedIndex();
			
			// set the time
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.set(Calendar.HOUR, hour);
			date = cal.getTime();
		}
	}

	/**
	 * MinuteListener intended to be used with 
	 * the minute combo box on the DateDialog form.
	 * 
	 * sets the date to the minute selected
	 * 
	 * @author xxx
	 *
	 */
	private class MinuteListener implements ActionListener {

	    /**
	     * Invoked when the minute combo box is changed.
		 * 
		 * sets the date to the minute selected
	     * 
	     * @param e not used
	     */
		public void actionPerformed(ActionEvent e) {
			
			// get the source
			JComboBox source = (JComboBox)e.getSource();
			
			// get the minute
			int minute = Integer.parseInt(source.getSelectedItem().toString());

			// set the time
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.set(Calendar.MINUTE, minute);
			date = cal.getTime();
		}
	}

	/**
	 * AmPmListener intended to be used with 
	 * the AM / PM combo box on the DateDialog form.
	 * 
	 * sets the date to AM or PM as selected
	 * 
	 * @author xxx
	 *
	 */
	private class AmPmListener implements ActionListener {

	    /**
	     * Invoked when the AM / PM combo box is changed.
		 * 
		 * sets the date to AM or PM as selected
	     * 
	     * @param e not used
	     */
		public void actionPerformed(ActionEvent e) {
			
			// get the source
			JComboBox source = (JComboBox)e.getSource();
			
			// derive the selection
			int amPm;
			if(source.getSelectedItem().equals("AM"))
				amPm = Calendar.AM;
			else
				amPm = Calendar.PM;
			
			// set the date
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.set(Calendar.AM_PM, amPm);
			date = cal.getTime();
		}
	}

	/**
	 * ActionListener intended to be used with 
	 * the "OK" button on the DateDialog form.
	 * 
	 * Applies the changes of the form to the form's 
	 * return date, and disposes the form if no 
	 * errors occur.
	 * 
	 * @author xxx
	 *
	 */
	private class OKListener implements ActionListener {

	    /**
	     * Invoked when the "OK" button is pressed.
		 * 
		 * Applies the changes of the form to the form's 
		 * return date, and disposes the form if no 
		 * errors occur.
	     * 
	     * @param e not used
	     */
		public void actionPerformed(ActionEvent e) {
			returnSuccessful();
		}
	}

	/**
	 * ActionListener intended to be used with 
	 * the date buttons on the DateDialog form grid.
	 * 
	 * sets the date to the day selected
	 * 
	 * @author xxx
	 *
	 */
	private class GridActionListener implements ActionListener {
		
		/**
		 * array index of the element we're listening to
		 */
		private int index;

	    /**
	     * Invoked when the one of the date buttons is selected.
		 * 
		 * sets the date to the day selected
	     * 
	     * @param e not used
	     */
		public void actionPerformed(ActionEvent e) {
			setDate(index);
		}
		
		/**
		 * creates a new listener at the index specified
		 * 
		 * @param index
		 */
		public GridActionListener(int index) {
			this.index = index;
		}
	}	
	
	/**
	 * the currently selected date
	 */
	private Date date;
	
	/**
	 * what gets returned to the user, null if the form was canceled out of
	 */
	private Date returnDate;
	
	/**
	 * display for the current month and year
	 */
	private JLabel label;
	
	/**
	 * array of day elements in the grid
	 */
	private JToggleButton[] buttons;
	
	/**
	 * Returns the date selected by the user, null if the form was canceled out of
	 * 
	 * @return the date selected by the user, null if the form was canceled out of
	 */
	public Date getReturnDate() {
		return returnDate;
	}
	
	/**
	 * adds the specified amount to the specified field
	 * in the current date, and updates the form accordingly
	 * 
     * @param field the calendar field.
     * @param amount the amount of date or time to be added to the field.
	 */
	public void addDate(int field, int amount) {
		
		// set the new time
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(field, amount);
		date = cal.getTime();	
		
		// set the date display
		label.setText(
			cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()) + 
			", " + 
			cal.get(Calendar.YEAR));
		
		// update the grid blocks
		GridMonthRange range = new GridMonthRange(date);
		Calendar rangeCal = Calendar.getInstance();
		rangeCal.setTime(range.getStartDate());
		for(int i = 0; i < buttons.length; i++) {
			buttons[i].setText("" + rangeCal.get(Calendar.DATE));
			buttons[i].setEnabled(rangeCal.get(Calendar.MONTH) == cal.get(Calendar.MONTH));
			if(rangeCal.get(Calendar.DAY_OF_MONTH) == cal.get(Calendar.DAY_OF_MONTH))
				buttons[i].doClick();
			rangeCal.add(Calendar.DATE, 1);
		}
	}
	
	/**
	 * sets the current date according to the specified
	 * index into the grid month, and updates the form accordingly
	 * 
	 * @param index number of days since the beginning of the grid month
	 */
	private void setDate(int index) {
		
		// get the grid month
		GridMonthRange range = new GridMonthRange(date);
		
		// get the new time
		Calendar calNew = Calendar.getInstance();
		calNew.setTime(range.getStartDate());
		calNew.add(Calendar.DATE, index);
		
		// set the date of the new time into the date of the old time
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DATE, calNew.get(Calendar.DATE));
		date = cal.getTime();
	}
	
	/**
	 * applies the changes of the user edit, and
	 * disposes the form, returning control back
	 * to the parent form.
	 */
	private void returnSuccessful() {
		returnDate = date;
		dispose();
	}
	
	/**
	 * Returns a new panel for selecting a date from a grid
	 * 
	 * @return a new panel for selecting a date from a grid
	 */
	private JPanel getNewGridPanel() {
		
		// get a calendar, and set it to the last or current Sunday
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		
		// the return panel
		JPanel ret = new JPanel();
		ret.setLayout(new GridLayout(0, 7));
		
		// add the names of the days of the week
		do {
			JLabel label = new JLabel(cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.getDefault()));
			label.setHorizontalAlignment(SwingConstants.CENTER);
			ret.add(label);
			cal.add(Calendar.DATE, 1);
		} while(cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY);

		// add the days of the month
		buttons = new JToggleButton[6 * 7];
		JToggleButton b;
		ButtonGroup group = new ButtonGroup();
		for(int i = 0; i < buttons.length; i++) {
			b = new JToggleButton();
			buttons[i] = b;
			b.setFocusable(false);
			b.addActionListener(new GridActionListener(i));
			group.add(b);
			ret.add(b);
		}
		
		return ret;
	}
	
	/**
	 * Returns a new panel for editing the current month and year
	 * 
	 * @return a new panel for editing the current month and year
	 */
	private JPanel getNewNorthPanel() {
		
		// display for the current month and year
		label = new JLabel();
		label.setHorizontalAlignment(SwingConstants.CENTER);
		
		// return panel
		JPanel ret = new JPanel();
		ret.setLayout(new BorderLayout());
		ret.add(getNewPrevPanel(), BorderLayout.WEST);
		ret.add(label, BorderLayout.CENTER);
		ret.add(getNewNextPanel(), BorderLayout.EAST);
		
		return ret;
	}

	/**
	 * Returns a new panel for incrementing the current month and year
	 * 
	 * @return a new panel for incrementing the current month and year
	 */
	private JPanel getNewNextPanel() {
		
		// incrementing the month
		JButton nextMonth = new JButton(">");
		nextMonth.setFocusable(false);
		nextMonth.addActionListener(new NextMonthListener());
		
		// incrementing the year
		JButton nextYear = new JButton(">>");
		nextYear.setFocusable(false);
		nextYear.addActionListener(new NextYearListener());
		
		
		// return panel
		JPanel ret = new JPanel();
		ret.setLayout(new BoxLayout(ret, BoxLayout.X_AXIS));
		ret.add(nextMonth);
		ret.add(nextYear);
		
		return ret;
	}

	/**
	 * Returns a new panel for decrementing the current month and year
	 * 
	 * @return a new panel for decrementing the current month and year
	 */
	private JPanel getNewPrevPanel() {
		
		// decrementing the year
		JButton prevYear = new JButton("<<");
		prevYear.setFocusable(false);
		prevYear.addActionListener(new PreviousYearListener());

		// decrementing the month
		JButton prevMonth = new JButton("<");
		prevMonth.setFocusable(false);
		prevMonth.addActionListener(new PreviousMonthListener());
		

		// return panel
		JPanel ret = new JPanel();
		ret.setLayout(new BoxLayout(ret, BoxLayout.X_AXIS));
		ret.add(prevYear);
		ret.add(prevMonth);
		
		return ret;
	}

	/**
	 * Returns a new panel for editing the time and confirming the selection
	 * 
	 * @return a new panel for editing the time and confirming the selection
	 */
	private JPanel getNewSouthPanel() {
		
		// ok button
		JButton ok = new JButton("OK");
		ok.setFocusable(false);
		ok.addActionListener(new OKListener());
		
		
		// return panel
		JPanel ret = new JPanel();
		ret.setLayout(new BorderLayout());
		ret.add(getNewTimePanel(), BorderLayout.NORTH);
		ret.add(ok, BorderLayout.SOUTH);
		
		return ret;
	}

	/**
	 * Returns a new panel for editing the time
	 * 
	 * @return a new panel for editing the time
	 */
	private JPanel getNewTimePanel() {
		
		// the current time
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		
		// holds combo model information
		String[] ints;
		
		// hour combo box
		ints = new String[12];
		ints[0] = "12";
		for(int i = 1; i < ints.length; i++)
			ints[i] = "" + i;
		JComboBox hours = new JComboBox(ints);
		((BasicComboBoxRenderer)hours.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		hours.setSelectedIndex(cal.get(Calendar.HOUR) % 12);
		hours.addActionListener(new HourListener());
		
		// minute combo box
		ints = new String[60];
		for(int i = 0; i < ints.length; i++)
			ints[i] = (i < 10 ? "0" : "") + i;
		JComboBox minutes = new JComboBox(ints);
		((BasicComboBoxRenderer)minutes.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		minutes.setSelectedIndex(cal.get(Calendar.MINUTE));
		minutes.addActionListener(new MinuteListener());
		
		// AM / PM combo box
		JComboBox amPm = new JComboBox(
			new String[] {
				"AM",
				"PM"
			}
		);
		((BasicComboBoxRenderer)amPm.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		amPm.setSelectedIndex(cal.get(Calendar.AM_PM) == Calendar.AM ? 0 : 1);
		amPm.addActionListener(new AmPmListener());
		
		
		// return panel
		JPanel ret = new JPanel();
		ret.setLayout(new GridLayout(0, 3));
		ret.setBorder(new EmptyBorder(4, 0, 4, 0));
		ret.add(hours);
		ret.add(minutes);
		ret.add(amPm);
		
		return ret;
	}
	
	/**
	 * Initializes this DateDialog object, acts as
	 * a common constructor / initializer for the actual
	 * constructors that would otherwise perform redundant code
	 * 
	 * @param startDate date to initially focus on
	 */
	private void init(Date startDate) {
		
		// default to the current date
		if(startDate == null)
			startDate = new Date();
		date = startDate;
		
		// set aesthetics
		setLayout(new BorderLayout());
		setTitle("Please select a date...");
		
		// add panels
		add(getNewNorthPanel(), BorderLayout.NORTH);
		add(getNewGridPanel(), BorderLayout.CENTER);
		add(getNewSouthPanel(), BorderLayout.SOUTH);
		
		// populate panels
		addDate(Calendar.DATE, 0);
		
		pack(); 
		setResizable(false);
		
		setLocationRelativeTo(null);
	}
	
	/**
	 * creates a new DateDialog initialized to a start date
	 * 
	 * @param frame from which the dialog is displayed
	 * @param startDate date to initially focus on
	 */
	private DateDialog(Frame frame, Date startDate) {
		super(frame, true);
		init(startDate);
	}
	
	/**
	 * creates a new DateDialog initialized to a start date
	 * 
	 * @param frame from which the dialog is displayed
	 * @param startDate date to initially focus on
	 */
	private DateDialog(Dialog frame, Date startDate) {
		super(frame, true);
		init(startDate);
	}
}