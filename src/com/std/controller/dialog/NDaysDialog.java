package com.std.controller.dialog;

import com.std.model.pattern.NDaysPattern;
import com.std.model.pattern.RecurrencePattern;
import com.std.util.GroupLayoutUtility;
import com.std.util.range.DateRange;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * NDaysDialog is a utility class of the controller that provides an easy way to
 * get user input in the form of a NDaysPattern.
 * <p/>
 * Usage: JFrame frame; RecurrencePattern pattern;
 * <p/>
 * // to present the user with an opportunity to create a NDaysPattern
 * NDaysDialog.getPattern(frame, pattern);
 * <p/>
 * The class methods block until the user finishes editing, and returns the
 * constructed NDaysPattern.
 */
public class NDaysDialog extends JDialog{

	// UID Used for Serializable
	private static final long serialVersionUID = 2554342305598276958L;

	private NDaysPattern pattern;// pattern passed as result of user input
	private DatePanel startDate; //start date field component
	private DatePanel endDate;  //end date field component
	private JTextField number;  //the number of days between each recurrence

	/**
	 * Prompts the user to create a NDaysPattern
	 *
	 * @param frame   the Frame from which the dialog is displayed
	 * @param pattern pattern to start with
	 *
	 * @return the created NDaysPattern
	 */
	public static NDaysPattern getPattern(
			Frame frame, RecurrencePattern pattern){
		NDaysDialog dialog = new NDaysDialog(frame, pattern);
		dialog.setVisible(true);
		return dialog.getReturnPattern();
	}

	/**
	 * Prompts the user to create a NDaysPattern
	 *
	 * @param frame   the Frame from which the dialog is displayed
	 * @param pattern pattern to start with
	 *
	 * @return the created NDaysPattern
	 */
	public static NDaysPattern getPattern(
			Dialog frame, RecurrencePattern pattern){
		NDaysDialog dialog = new NDaysDialog(frame, pattern);
		dialog.setVisible(true);
		return dialog.getReturnPattern();
	}

	/**
	 * ActionListener intended to be used with the "OK" button on the
	 * NDaysDialog form.
	 *
	 * Applies the changes of the form to the form's NDaysPattern, and disposes
	 * the form if no errors occur.
	 */
	private class OKActionListener implements ActionListener{

		/**
		 * Invoked when the "OK" button is pressed.
		 *
		 * Applies the changes of the form to the form's NDaysPattern, and disposes
		 * the form if no errors occur.
		 *
		 * @param e not used
		 */
		public void actionPerformed(ActionEvent e){
			returnSuccessful();
		}
	}

	/**
	 * @return the NDaysPattern that was created as a result of user input
	 */
	NDaysPattern getReturnPattern(){
		return pattern;
	}

	/**
	 * Applies the changes of the user edit, and disposes the form, returning
	 * control back to the parent form.
	 */
	private void returnSuccessful(){
		try{
			int instanceEvery = Integer.parseInt(number.getText());
			pattern = new NDaysPattern(new DateRange(
					startDate.getDate(), endDate.getDate()), instanceEvery);
			dispose();
		}
		catch(NumberFormatException e){
			JOptionPane.showMessageDialog(this,
			"number of days is not a number", "", JOptionPane.ERROR_MESSAGE);
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(
					this, e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Initializes this NDaysDialog object, acts as a common constructor /
	 * initializer for the actual constructors that would otherwise perform
	 * redundant code.
	 *
	 * @param base the RecurrencePattern that will be used to initialize fields
	 */
	private void init(RecurrencePattern base){
		/* Because base could be any type of pattern, we need to somehow convert
		it to a NDaysPattern so we can use it for the defaults.  if it's null,
		make our own default, if it's already a NDaysPattern, then cast it.
		Otherwise, use it for its dateRange */
		NDaysPattern pattern;
		if(base == null){
			pattern = new NDaysPattern(new DateRange(), 0);
		}
		else if(base instanceof NDaysPattern){
			pattern = (NDaysPattern) base;
		}
		else{
			pattern = new NDaysPattern(base.getRange(), 0);
		}

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

		// the recurrence option field
		number = new JTextField("" + pattern.instanceEvery());
		number.setMargin(new Insets(0, 4, 0, 4));
		number.setHorizontalAlignment(SwingConstants.RIGHT);

		// panel that holds the recurrence options
		JPanel numberPanel = new JPanel();
		numberPanel.setLayout(new BorderLayout());
		numberPanel.add(number, BorderLayout.CENTER);
		numberPanel.add(new JLabel("  days"), BorderLayout.EAST);

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
				{new JLabel("end date"), endDate},
				{new JLabel("duration"), duration},
				{new JLabel("recur every"), numberPanel}
		});

		// add the panels
		add(north, BorderLayout.NORTH);
		add(okButton, BorderLayout.SOUTH);

		setTitle("Add / Edit Recurrence Pattern");

		// set the title
		pack();
		setMinimumSize(getSize());

		setLocationRelativeTo(null);
	}

	/**
	 * Creates a new NDaysDialog initialized to an existing pattern.
	 *
	 * @param frame   from which the dialog is displayed
	 * @param pattern pattern to initialize the fields to
	 */
	private NDaysDialog(Frame frame, RecurrencePattern pattern){
		super(frame, true);
		init(pattern);
	}

	/**
	 * Creates a new NDaysDialog initialized to an existing pattern.
	 *
	 * @param frame   from which the dialog is displayed
	 * @param pattern pattern to initialize the fields to
	 */
	private NDaysDialog(Dialog frame, RecurrencePattern pattern){
		super(frame, true);
		init(pattern);
	}
}
