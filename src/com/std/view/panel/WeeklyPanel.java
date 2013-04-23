package com.std.view.panel;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
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

public class WeeklyPanel extends JPanel  {
	
	/**
	 * These are the representaions of all the days in this
	 * week
	 */
	
	private List<WeeklyDayBlock> blocks;
	
	/**
	 * This is the constructor of the class it creates all of 
	 * the weekDayBlocks that are associated with this class
	 * and sets the currently selected date and creates a 
	 * weekRange around that date
	 * 
	 * @param date is the currentlySelectedDate
	 */
	
	public WeeklyPanel(Date date) {
		super();
		
		blocks = new ArrayList<WeeklyDayBlock>();
		
		JPanel daysPanel = new JPanel();
		daysPanel.setOpaque(false);
		daysPanel.setLayout(new GridLayout(1, 7));
		
		Calendar tmpCal = Calendar.getInstance();
		WeekRange week = new WeekRange();
		tmpCal.setTime(week.getStartDate());
		while(tmpCal.getTime().before(week.getEndDate())) {
			JLabel dayText = new JLabel(tmpCal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault()));
			dayText.setFont(dayText.getFont().deriveFont(13f).deriveFont(Font.BOLD));
			dayText.setOpaque(false);
			dayText.setHorizontalAlignment(SwingConstants.CENTER);
			daysPanel.add(dayText);
			tmpCal.add(Calendar.DATE, 1);
		}
		
		JPanel content = new JPanel();
		content.setOpaque(false);
		content.setLayout(new GridLayout(0, 7));
		
		ButtonGroup group = new ButtonGroup();
	
		WeekRange thisWeek = new WeekRange(date);
		tmpCal.setTime(thisWeek.getStartDate());
		while(tmpCal.getTime().before(thisWeek.getEndDate())) {
			WeeklyDayBlock currentDay = new WeeklyDayBlock(tmpCal.getTime());
			blocks.add(currentDay);
			content.add(currentDay);
			group.add(currentDay);
			tmpCal.add(Calendar.DATE, 1);
		}
		
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
		
		Calendar tmpCal = Calendar.getInstance();
		WeekRange wRange = new WeekRange(selectedDate);
		for(int i = 0; i < blocks.size(); i++) { // for each day in the month
			tmpCal.setTime(wRange.getStartDate());
			tmpCal.add(Calendar.DATE, i);
			
			WeeklyDayBlock dayBox = blocks.get(i);
			dayBox.update(refSet, tmpCal.getTime(), selectedAppointment);
			dayBox.setSelected(tmpCal.get(Calendar.DAY_OF_YEAR) == curcal.get(Calendar.DAY_OF_YEAR));
		}
	}
	
	/**
	 * This method adds appointment listeners to all of the dayblocks for
	 * this week
	 * @param m is the mouse listener to be added to the appointment views
	 */
	
	public void addAppointmentListeners(MouseListener m) {
		for(WeeklyDayBlock d : blocks)
			d.addAppointmentMouseListener(m);
	}
	
	/**
	 * This method sets a day selection method for all the days in this week
	 * @param ml is the mouse listener to be added
	 */
	
	public void addDaySelectionActionListener(ActionListener ml) {
		for(WeeklyDayBlock d : blocks)
			d.addActionListener(ml);
	}
	
}
