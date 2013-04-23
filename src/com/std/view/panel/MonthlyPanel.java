package com.std.view.panel;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.IOException;
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
import com.std.util.range.GridMonthRange;
import com.std.util.range.WeekRange;
import com.std.view.block.MonthlyDayBlock;




/**
 * This class represents the month view, it contains all the information needed
 * to maintain an updated look at the month, including ways to move to the next
 * month and previous month and to add and remove current appointments, the
 * appointments are displayed in less detail in the month view then in the week
 * view.  Only the titles of each appointment are displayed in this view.
 * 
 * @author xxx
 * 
 */
public class MonthlyPanel extends JPanel {
	
	private List<MonthlyDayBlock> blocks;

	/**
	 * This is the constructor it initializes all the variables needed and then
	 * updates the view to display the appointment set sent as an argument.
	 */
	public MonthlyPanel(Date date) throws IOException {
		super();
		
		blocks = new ArrayList<MonthlyDayBlock>();
		
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
		
		GridMonthRange gRange = new GridMonthRange(date);
		tmpCal.setTime(gRange.getStartDate());
		while(tmpCal.getTime().before(gRange.getEndDate())) {
			MonthlyDayBlock dayBox = new MonthlyDayBlock(tmpCal.getTime());
			blocks.add(dayBox);
			content.add(dayBox);
			group.add(dayBox);
			tmpCal.add(Calendar.DATE, 1);
		}
		
		setLayout(new BorderLayout());
		setOpaque(false);
		add(daysPanel, BorderLayout.NORTH);
		add(content, BorderLayout.CENTER);
		
		update(new HashSet<RefAppointment>(), date, null);
	}

	/**
	 * 
	 * @param dl is the dayListener to set to each MonthlyDayBlock
	 */
	public void addDayListener(ActionListener dl) {
		for(MonthlyDayBlock dayBox : blocks) // for each day in the month
			dayBox.addActionListener(dl);
	}

	/**
	 * 
	 * @param al is the appointmentListener to set to each appointment in each
	 * MonthlyDayBlock
	 */
	public void addAppointmentListener(MouseListener al) {
		for(MonthlyDayBlock dayBox : blocks) // for each day in the month
			dayBox.addAppointmentActionListener(al);
	}

	/**
	 * 
	 *update the panel's entire display
	 *this method is to be called any time the appointment set is changed.
	 *this method is also to be called any time the view is changed
	 */
	public void update(Set<RefAppointment> as, Date cd, RefAppointment ca) {
		
		Calendar curcal = Calendar.getInstance();
		curcal.setTime(cd);
		
		Calendar tmpCal = Calendar.getInstance();
		GridMonthRange gRange = new GridMonthRange(cd);
		for(int i = 0; i < blocks.size(); i++) { // for each day in the month
			tmpCal.setTime(gRange.getStartDate());
			tmpCal.add(Calendar.DATE, i);
			
			MonthlyDayBlock dayBox = blocks.get(i);
			dayBox.setSelected(tmpCal.get(Calendar.DAY_OF_YEAR) == curcal.get(Calendar.DAY_OF_YEAR));
			dayBox.setEnabled(tmpCal.get(Calendar.MONTH) == curcal.get(Calendar.MONTH));
			dayBox.update(as, tmpCal.getTime(), ca);
		}
	}
}



