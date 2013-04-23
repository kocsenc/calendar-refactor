package com.std.view.panel;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseListener;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Set;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.std.model.appointment.RefAppointment;
import com.std.util.range.WeekRange;
import com.std.view.block.WeeklyDayBlock;

/**
 * This class represents the week view, it contains all the information needed
 * to maintain an updated look at the week, including ways to move to the next
 * week and previous week and to add and remove current appointments, the
 * appointments are displayed in more detail in the week view then in the month
 * view
 * 
 * @author xxx
 * 
 */

public class DailyPanel extends JPanel  {
	
	/**
	 * These are the representaions of all the days in this
	 * week
	 */
	
	private WeeklyDayBlock block;
	
	private Hashtable<Integer, JLabel> daysOfWeek;
	
	/**
	 * This is the constructor of the class it creates all of 
	 * the weekDayBlocks that are associated with this class
	 * and sets the currently selected date and creates a 
	 * weekRange around that date
	 * 
	 * @param date is the currentlySelectedDate
	 */
	
	public DailyPanel(Date date) {
		super();
		
		daysOfWeek = new Hashtable<Integer,JLabel>();
		
		JPanel daysPanel = new JPanel();
		daysPanel.setOpaque(false);
		daysPanel.setLayout(new GridLayout(1, 7));
		
		Calendar curcal = Calendar.getInstance();
		curcal.setTime(date);
		
		Calendar tmpCal = Calendar.getInstance();
		WeekRange week = new WeekRange();
		tmpCal.setTime(week.getStartDate());
		while(tmpCal.getTime().before(week.getEndDate())) {
			JLabel dayText = new JLabel(tmpCal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault()));
			dayText.setFont(dayText.getFont().deriveFont(13f).deriveFont(Font.BOLD));
			dayText.setOpaque(false);
			dayText.setHorizontalAlignment(SwingConstants.CENTER);
			daysPanel.add(dayText);
			daysOfWeek.put(tmpCal.get(Calendar.DAY_OF_WEEK), dayText);
			tmpCal.add(Calendar.DATE, 1);
		}
		
		JPanel content = new JPanel();
		content.setOpaque(false);
		content.setLayout(new GridLayout(0, 1));
		
		block = new WeeklyDayBlock(tmpCal.getTime());
		content.add(block);
		block.setSelected(true);
		block.setEnabled(false);
		tmpCal.add(Calendar.DATE, 1);
		
		setLayout(new BorderLayout());
		setOpaque(false);
		add(daysPanel, BorderLayout.NORTH);
		add(content, BorderLayout.CENTER);
		
		update(new HashSet<RefAppointment>(), date, null);
	}
	
	/**
	 * This method updates the week by updating all of it's weekDayBlocks
	 * 
	 * @param refSet is the new reference set
	 * @param selectedDate is the currently selected date
	 * @param selectedAppointment is the currently selected appointment
	 */
	
	public void update(Set<RefAppointment> refSet, Date selectedDate, RefAppointment selectedAppointment) {
		Calendar curcal = Calendar.getInstance();
		curcal.setTime(selectedDate);
		
		for(int key : daysOfWeek.keySet())
			daysOfWeek.get(key).setEnabled(curcal.get(Calendar.DAY_OF_WEEK) == key);
		
		block.update(refSet, curcal.getTime(), selectedAppointment);
	}
	
	/**
	 * This method adds appointment listeners to all of the dayblocks for
	 * this week
	 * @param m is the mouse listener to be added to the appointment views
	 */
	
	public void addAppointmentListener(MouseListener m) {
		block.addAppointmentMouseListener(m);
	}
	
}
