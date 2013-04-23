package com.std.controller.dialog;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.std.model.CalendarModel;
import com.std.model.appointment.AppointmentTemplate;
import com.std.model.appointment.AppointmentUtility;
import com.std.model.appointment.RefAppointment;
import com.std.model.pattern.DayOfWeekPattern;
import com.std.model.pattern.NDaysPattern;
import com.std.model.pattern.RecurrencePattern;
import com.std.util.GroupLayoutUtility;
import com.std.util.range.DateRange;

/**
 * AppointmentDialog is a utility class of the controller that 
 * provides an easy way to get user input in the form of an
 * Appointment. <br/>
 * <br/>
 * Usage:</br>
 * <tt>
 * JFrame frame;</br>
 * RefAppointment appt;</br>
 * CalendarModel model;</br>
 * </br>
 * // to present the user with an opportunity to change an appointment</br>
 * AppointmentDialog.changeAppointment(frame, appt);</br>
 * </br>
 * // to present the user with an opportunity to change the model defaults</br>
 * AppointmentDialog.changeAppointmentDefaults(frame, model);</br></tt>
 * </br>
 * The class methods block until the user finishes editing, and
 * applies any changes directly to the passed appointment.
 * 
 * @author xxx
 *
 */
public class AppointmentDialog extends JDialog {

	/**
	 * UID Used for Serializable
	 */
	private static final long serialVersionUID = 5613941454015423846L;
	
	/**
	 * format to use when displaying date information
	 */
	private static final SimpleDateFormat FORMAT = new SimpleDateFormat("EEE, d MMM yyyy 'at' h:mm aa");
	
	/**
	 * Prompts the user to change an Appointment.
	 * 
	 * @param frame the <code>Frame</code> from which the dialog is displayed
	 * @param appt appointment to prompt changes for
	 * @return false iff the user has canceled out of the dialog
	 */
	public static boolean changeAppointment(JFrame frame, RefAppointment appt) {
		AppointmentDialog dialog = new AppointmentDialog(frame, appt);
		dialog.setVisible(true);
		return dialog.getReturnState();
	}
	
	/**
	 * Prompts the user to change an Appointment.
	 * 
	 * @param frame the <code>Frame</code> from which the dialog is displayed
	 * @param appt appointment to prompt changes for
	 * @return false iff the user has canceled out of the dialog
	 */
	public static boolean changeAppointment(Dialog frame, RefAppointment appt) {
		AppointmentDialog dialog = new AppointmentDialog(frame, appt);
		dialog.setVisible(true);
		return dialog.getReturnState();
	}
	
	/**
	 * Prompts the user to change an Appointment.
	 * 
	 * @param frame the <code>Frame</code> from which the dialog is displayed
	 * @param appt appointment to prompt changes for
	 * @return false iff the user has canceled out of the dialog
	 */
	public static boolean changeAppointmentDefaults(Dialog frame, CalendarModel model) {
		AppointmentDialog dialog = new AppointmentDialog(frame, model.getCurrentDefaults());
		dialog.setTitle("Edit Appointment Defaults");
		dialog.setVisible(true);
		return dialog.getReturnState();
	}
	
	/**
	 * Prompts the user to change an Appointment.
	 * 
	 * @param frame the <code>Frame</code> from which the dialog is displayed
	 * @param appt appointment to prompt changes for
	 * @return false iff the user has canceled out of the dialog
	 */
	public static boolean changeAppointmentDefaults(JFrame frame, CalendarModel model) {
		AppointmentDialog dialog = new AppointmentDialog(frame, model.getCurrentDefaults());
		dialog.setTitle("Edit Appointment Defaults");
		dialog.setVisible(true);
		return dialog.getReturnState();
	}

	/**
	 * ActionListener intended to be used with 
	 * the "OK" button on the AppointmentDialog form.
	 * 
	 * Applies the changes of the form to the form's 
	 * RefAppointment, and disposes the form if no 
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
		 * RefAppointment, and disposes the form if no 
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
	 * the "no recurrence" button on the 
	 * AppointmentDialog form.
	 * 
	 * Sets the pattern to null, and updates the
	 * pattern description to match the selection
	 * 
	 * 
	 * @author xxx
	 *
	 */
	private class NoPatternButtonListener implements ActionListener {

	    /**
	     * Invoked when the "no recurrence" button is pressed.
		 * 
		 * Sets the pattern to null, and updates the
		 * pattern description to match the selection
	     * 
	     * @param e not used
	     */
		public void actionPerformed(ActionEvent e) {
			pattern = null;
			updatePatternDesc();
		}
	}

	/**
	 * ActionListener intended to be used with 
	 * the "periodically" button on the 
	 * AppointmentDialog form.
	 * 
	 * Prompts the user for pattern input, sets
	 * the pattern to the returned NDaysPattern, 
	 * and updates the pattern description to
	 * match the selection
	 * 
	 * 
	 * @author xxx
	 *
	 */
	private class NDaysButtonListener implements ActionListener {

	    /**
	     * Invoked when the "periodically" button is pressed.
		 * 
		 * Prompts the user for pattern input, sets
		 * the pattern to the returned NDaysPattern, 
		 * and updates the pattern description to
		 * match the selection
	     * 
	     * @param e not used
	     */
		public void actionPerformed(ActionEvent e) {
			if(pattern == null)
				pattern = new NDaysPattern(new DateRange(startDatePanel.getDate(), endDatePanel.getDate()), 0);
			
			RecurrencePattern tempPattern = NDaysDialog.getPattern(getThis(), pattern);
			if(tempPattern != null)
				pattern = tempPattern;
			updatePatternDesc();
		}
	}

	/**
	 * ActionListener intended to be used with 
	 * the "select days" button on the 
	 * AppointmentDialog form.
	 * 
	 * Prompts the user for pattern input, sets
	 * the pattern to the returned DayOfWeekPattern, 
	 * and updates the pattern description to
	 * match the selection
	 * 
	 * 
	 * @author xxx
	 *
	 */
	private class DayOfWeekButtonListener implements ActionListener {

	    /**
	     * Invoked when the "select days" button is pressed.
		 * 
		 * Prompts the user for pattern input, sets
		 * the pattern to the returned DayOfWeekPattern, 
		 * and updates the pattern description to
		 * match the selection
	     * 
	     * @param e not used
	     */
		public void actionPerformed(ActionEvent e) {
			if(pattern == null)
				pattern = new DayOfWeekPattern(new DateRange(startDatePanel.getDate(), endDatePanel.getDate()), new boolean[7]);
			
			RecurrencePattern tempPattern = DayOfWeekDialog.getPattern(getThis(), pattern);
			if(tempPattern != null)
				pattern = tempPattern;
			updatePatternDesc();
		}
	}
	
	/**
	 * the appointment that will be changed as a result of user input
	 */
	private RefAppointment appt;
	
	/**
	 * title field component
	 */
	private JTextField titleField;
	
	/**
	 * location field component
	 */
	private JTextField locationField;
	
	/**
	 * start date field component
	 */
	private DatePanel startDatePanel;
	
	/**
	 * end date field component
	 */
	private DatePanel endDatePanel;
	
	/**
	 * duration field display
	 */
	private JLabel duration;
	
	/**
	 * description field component
	 */
	private JTextArea description;
	
	/**
	 * recurrence pattern that is currently selected
	 */
	private RecurrencePattern pattern;
	
	/**
	 * pattern description display
	 */
	private JTextArea patternDesc;
	
	/**
	 * NDaysPattern selection button
	 */
	private JToggleButton nDays;
	
	/**
	 * DaysOfWeekPattern selection button
	 */
	private JToggleButton daysOfWeek;

	/**
	 * null pattern selection button
	 */
	private JToggleButton none;
	
	/**
	 * false if the user has canceled out of the dialog
	 */
	private boolean returnState;
	
	/**
	 * Returns false if the user has canceled out of the dialog
	 * 
	 * @return false if the user has canceled out of the dialog
	 */
	private boolean getReturnState() {
		return returnState;
	}
	
	/**
	 * Returns this AppointmentDialog object.  for use with
	 * inner classes that can't reference 'this' directly
	 * 
	 * @return this AppointmentDialog object
	 */
	private AppointmentDialog getThis() {
		return this;
	}
	
	/**
	 * applies the changes of the user edit, and
	 * disposes the form, returning control back
	 * to the parent form.
	 */
	private void returnSuccessful() {
		try {
			// create a dummy appointment so we
			// can recieve any errors before we
			// commit to the actual appointment
			RefAppointment tempAppointment = new RefAppointment(new Date(0), new AppointmentTemplate("", "", "", 0));

			// populate the dummy appointment
			// with our field data
			tempAppointment.setTitle(titleField.getText());
			tempAppointment.setLocation(locationField.getText());
			tempAppointment.setStartDate(startDatePanel.getDate());
			tempAppointment.setEndDate(endDatePanel.getDate());
			tempAppointment.setDescription(description.getText());
			tempAppointment.setPattern(pattern);
			
			// if we've gotten this far, we're
			// error-free, so it's safe to copy
			// to the actual appointment
			appt.setFields(tempAppointment);

			// set the return state
			this.returnState = true;
			
			// dispose of the form
			this.dispose();
			
		// if we catch any errors, alert the user and don't dispose the form
		} catch(Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * updates the pattern description based on the value of pattern
	 */
	private void updatePatternDesc() {
		
		// start with a blank string
		String text = AppointmentUtility.getPatternDescription(pattern);
		if(pattern != null) {
			if(pattern instanceof NDaysPattern)
				nDays.setSelected(true);
			else if(pattern instanceof DayOfWeekPattern)
				daysOfWeek.setSelected(true);
		}
		if(text == AppointmentUtility.NO_RECUR) {
			pattern = null;
			none.setSelected(true);
		}
		
		// set the description text
		patternDesc.setText(text);
	}
	
	/**
	 * Returns a new panel for editing the Recurrence pattern
	 * 
	 * @return a new panel for editing the Recurrence pattern
	 */
	private JPanel getNewPatternPanel() {
		
		// pattern description
		patternDesc = new JTextArea();
		patternDesc.setLineWrap(true);
		patternDesc.setWrapStyleWord(true);
		patternDesc.setBorder(new BevelBorder(BevelBorder.LOWERED));
		patternDesc.setMargin(new Insets(4, 4, 4, 4));
		patternDesc.setEditable(false);
		
		// button for NDaysPattern
		nDays = new JToggleButton("periodically");
		nDays.addActionListener(new NDaysButtonListener());

		// button for DaysOfWeekPattern
		daysOfWeek = new JToggleButton("select days");
		daysOfWeek.addActionListener(new DayOfWeekButtonListener());
		
		// button for the null pattern
		none = new JToggleButton("no recurrence");
		none.addActionListener(new NoPatternButtonListener());
		
		// add all the buttons to a group so
		// only one can be selected at a time
		ButtonGroup group = new ButtonGroup();
		group.add(nDays);
		group.add(daysOfWeek);
		group.add(none);
		
		// panel that holds the non-null buttons
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1, 0));
		buttonPanel.add(nDays);
		buttonPanel.add(daysOfWeek);

		// panel to return
		JPanel ret = new JPanel();
		ret.setLayout(new BorderLayout());
		ret.setBorder(new TitledBorder(new EmptyBorder(new Insets(6, 6, 6, 6)), "recurrence pattern"));
		ret.add(patternDesc, BorderLayout.NORTH);
		ret.add(buttonPanel, BorderLayout.CENTER);
		ret.add(none, BorderLayout.SOUTH);
		
		// update the pattern description
		updatePatternDesc();
		
		return ret;
	}
	
	/**
	 * Returns a new panel for editing the description
	 * 
	 * @return a new panel for editing the description
	 */
	private JPanel getNewCenterPanel() {
		
		// appointment description
		description = new JTextArea(appt.getDescription());
		description.setLineWrap(true);
		description.setPreferredSize(new Dimension(0, 200));
		description.setMargin(new Insets(4, 4, 4, 4));
		
		// scroll pane to put the description in
		JScrollPane scroll = new JScrollPane(description);
		scroll.setBorder(
			new CompoundBorder(
				new TitledBorder(new EmptyBorder(new Insets(6, 6, 6, 6)), "description"),
				new BevelBorder(BevelBorder.LOWERED)));


		// panel to return
		JPanel ret = new JPanel();
		ret.setLayout(new BorderLayout());
		ret.add(getNewPatternPanel(), BorderLayout.NORTH);
		ret.add(scroll, BorderLayout.CENTER);
		
		return ret;
	}
	
	/**
	 * Returns a new panel for editing the title, location, and dateRange
	 * 
	 * @return a new panel for editing the description
	 */
	private JPanel getNewNorthPanel(boolean enableAppt) {
		
		// title
		titleField = new JTextField(appt.getTitle());
		
		// location
		locationField = new JTextField(appt.getLocation());
		
		// start date
		startDatePanel = new DatePanel(appt.getStartDate(), enableAppt);
		
		// end date
		endDatePanel = new DatePanel(appt.getEndDate(), true);
		
		// duration
		duration = new JLabel();
		
		// listeners
		startDatePanel.addActionListener(new DurationUpdateListener(duration, startDatePanel, endDatePanel));
		endDatePanel.addActionListener(new DurationUpdateListener(duration, startDatePanel, endDatePanel));
		DurationUpdateListener.updateDuration(duration, startDatePanel, endDatePanel);
		
		
		// panel to return
		JPanel ret = new JPanel();
		GroupLayout northLayout = new GroupLayout(ret);
		northLayout.setAutoCreateContainerGaps(true);
		northLayout.setAutoCreateGaps(true);
		GroupLayoutUtility.addToGroups(
			northLayout, 
			new Component[][] {
				{new JLabel("title"), titleField},
				{new JLabel("location"), locationField},
				{new JLabel("start date"), startDatePanel},
				{new JLabel("end date"), endDatePanel},
				{new JLabel("duration"), duration}
			});
		ret.setLayout(northLayout);
		
		return ret;
	}
	
	/**
	 * Initializes this AppointmentDialog object, acts as
	 * a common constructor / initializer for the actual
	 * constructors that would otherwise perform redundant code
	 * 
	 * @param appt the appointment that will be changed as a result of user input
	 * @param enableAppt true iff RefAppointment specific fields should be enabled
	 */
	private void init(RefAppointment appt, boolean enableAppt) {
		this.appt = appt;
		this.returnState = false;
		this.pattern = appt.getPattern();
		
		// ok button
		JButton ok = new JButton("OK");
		ok.addActionListener(new OKListener());

		// this form
		setLayout(new BorderLayout());
		setTitle("Add / Edit Appointment");
		add(getNewNorthPanel(enableAppt), BorderLayout.NORTH);
		add(getNewCenterPanel(), BorderLayout.CENTER);
		add(ok, BorderLayout.SOUTH);
		
		updatePatternDesc();
		
		pack();
		setResizable(false);
		
		setLocationRelativeTo(null);
	}
	
	/**
	 * creates a new AppointmentDialog
	 * 
	 * @param frame from which the dialog is displayed
	 * @param appt appointment to prompt changes for
	 */
	private AppointmentDialog(Frame frame, RefAppointment appt) {
		super(frame, true);
		init(appt, true);
	}
	
	/**
	 * creates a new AppointmentDialog
	 * 
	 * @param frame from which the dialog is displayed
	 * @param appt appointment to prompt changes for
	 */
	private AppointmentDialog(Dialog frame, RefAppointment appt) {
		super(frame, true);
		init(appt, true);
	}
	
	/**
	 * creates a new AppointmentDialog based on a template, with disabled RefAppointment fields
	 * 
	 * @param frame from which the dialog is displayed
	 * @param appt appointment to prompt changes for
	 */
	private AppointmentDialog(Frame frame, AppointmentTemplate apptTmpl) {
		super(frame, true);
		init(new RefAppointment(new Date(), apptTmpl), false);
	}
	
	/**
	 * creates a new AppointmentDialog based on a template, with disabled RefAppointment fields
	 * 
	 * @param frame from which the dialog is displayed
	 * @param appt appointment to prompt changes for
	 */
	private AppointmentDialog(Dialog frame, AppointmentTemplate apptTmpl) {
		super(frame, true);
		init(new RefAppointment(new Date(), apptTmpl), false);
	}
}
