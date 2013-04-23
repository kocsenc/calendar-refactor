package com.std.view.block;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.border.EtchedBorder;

import com.std.model.appointment.AppointmentUtility;
import com.std.model.appointment.RefAppointment;
import com.std.util.range.DayRange;

/**
 * This is the day representation in side of a week, its job is
 * to hold the RefreenceAppointments in order to display them
 * 
 * @author xxx
 *
 */

public class WeeklyDayBlock extends JToggleButton implements DayBlock {
	
	private static final SimpleDateFormat FORMAT = new SimpleDateFormat("MMM d");

	/**
	 *  The currently selected day
	 */
	private Date day;
	
	/**
	 * The JLabel that shows the date that this represents
	 */
	private JLabel displayDate;
	
	/**
	 * The panel that all of the appointments will be added
	 */
	private JPanel center;
	
	/**
	 * this is the action listener that will be listening to
	 * all of the AppointmentReadViews
	 */
	private MouseListener appointmentListener;
	
	/**
	 * This is the constructor, it sets up the center panel and the scollPane
	 * that the center panel goes into, it also sets up the date from the parameter d
	 *
	 * @param d is the date that this panel represents
	 * @param listener is the listener for to the AppointmentReadViews
	 */
	
	public WeeklyDayBlock(Date d) {
		super();
		
		day = d;
		displayDate = new JLabel(FORMAT.format(day));
		displayDate.setOpaque(false);
		displayDate.setFont(displayDate.getFont().deriveFont(18f));
		
		center = new JPanel();
		center.setLayout(new GridLayout(0, 1));
		center.setOpaque(true);
		center.setBackground(new Color(0xBBFFFFFF, false));
		JScrollPane centerScroll = new JScrollPane(center);
		centerScroll.setOpaque(false);
		centerScroll.getViewport().setOpaque(false);
		centerScroll.setBorder(new EtchedBorder());
		centerScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		setLayout(new BorderLayout());
		setMargin(new Insets(1, 1, 1, 1));
		add(displayDate, BorderLayout.NORTH);
		add(centerScroll, BorderLayout.CENTER);
	}
	
	/**
	 * This allows the mouseListener for the appointment views to be set
	 * @param list is the MouseListener to be set
	 */
	
	public void addAppointmentMouseListener(MouseListener list) {
		appointmentListener = list;
	}
	
	/**
	 * 
	 * This method updates the panel with new information from the model
	 * it takes as parameter the neccesary information to update
	 * 
	 * @param appts is the new appointmentset
	 * @param d is the date that this day should represent
	 * @param r is the currently selected reference appointment
	 */
	
	public void update(Set<RefAppointment> appts, Date d, RefAppointment r ) {
		day = d;
		displayDate.setText(FORMAT.format(day));
		
		Set<RefAppointment> subSet = AppointmentUtility.getRange(appts, new DayRange(day));
		center.removeAll();
		for (RefAppointment ref : subSet) {
			AppointmentBlock apptView = new AppointmentBlock(ref, false);
			
			apptView.addMouseListener(appointmentListener);
			if (ref.equals(r)) {
				apptView.setBackground(Color.RED);
			}
			center.add(apptView);
		}	
		
		revalidate();
		repaint();
	}
	
	public Date getDate() {
		return day;
	}
	
}
