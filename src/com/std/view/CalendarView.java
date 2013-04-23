package com.std.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.std.model.appointment.AppointmentUtility;
import com.std.model.appointment.RefAppointment;
import com.std.util.range.DayRange;
import com.std.util.range.MonthRange;
import com.std.util.range.WeekRange;
import com.std.view.panel.AppointmentPanel;
import com.std.view.panel.DailyPanel;
import com.std.view.panel.MonthlyPanel;
import com.std.view.panel.WeeklyPanel;

/**
 * This is the main entry point for the view, it updates the views when it is called
 * by the controller
 * 
 * @author xxx
 *
 */

public class CalendarView extends JFrame {	
	private final static SimpleDateFormat FORMAT = new SimpleDateFormat("MMMM, yyyy");
	
	/**
	 *  This is the enumeration that represents the different
	 *  views in the tabbed pane
	 *
	 */
	
	public static enum TABBED_STATE {
		MONTHLY,
		WEEKLY,
		DAILY
	}
	
	private CalendarMenu calMenu;
	
	private JLabel displayDate;
	private JButton prevButton;
	private JButton nextButton;
	
	/**
	 * These are the different tabs
	 */
	private JTabbedPane tabs;
	private MonthlyPanel monthlyView;
	private WeeklyPanel weeklyView;
	private DailyPanel dailyView;
	
	private AppointmentPanel appointmentView;
	
	/**
	 * This allows elements of the view and contorller 
	 * figure out what view is currently selected
	 * 
	 * @return returns a Tabbed state of the currently selected view
	 */
	
	public TABBED_STATE getTabbedState() {
		TABBED_STATE ret = null;
		Component c = tabs.getSelectedComponent();
		if(c == monthlyView)
			ret = TABBED_STATE.MONTHLY;
		else if(c == weeklyView)
			ret = TABBED_STATE.WEEKLY;
		else if(c == dailyView)
			ret = TABBED_STATE.DAILY;
		return ret;
	}
	
	/**
	 * This passes a mouse listener down to the different views
	 * @param listener is the mouse listener to be added to the 
	 * 		  appointment blocks
	 */
	
	public void addAppointmentSelectionListener(MouseListener mouse) {
		weeklyView.addAppointmentListeners(mouse);
		monthlyView.addAppointmentListener(mouse);
		dailyView.addAppointmentListener(mouse);
	}
	
	/**
	 * This passes a mouse listener down to the different views
	 * @param listener is the mouse listener to be added to the 
	 * 		 day blocks
	 */
	
	
	public void addDateSelectionListener(ActionListener listener) {
		weeklyView.addDaySelectionActionListener(listener);
		monthlyView.addDayListener(listener);
	}
	
	/**
	 * This passes a mouse listener down to the different views
	 * @param listener is the mouse listener to be added to the 
	 * 		  next button
	 */
	
	
	public void addNextButtonActionListener(ActionListener listener) {
		nextButton.addActionListener(listener);
	}
	
	/**
	 * This passes a mouse listener down to the different views
	 * @param listener is the mouse listener to be added to the 
	 * 		  previus button
	 */
	
	
	public void addPrevButtonActionListener(ActionListener listener) {
		prevButton.addActionListener(listener);
	}

	/**
	 * This passes a mouse listener down to the different views
	 * @param listener is the mouse listener to be added to the 
	 * 		  new appointment blocks
	 */
	
	
	public void addNewCalendarActionListener(ActionListener listener) {
		calMenu.getNewCalendarMenuItem().addActionListener(listener);
	}
	
	/**
	 * This passes a mouse listener down to the different views
	 * @param listener is the mouse listener to be added to the 
	 * 		  open calendar button
	 */
	
	
	public void addOpenCalendarActionListener(ActionListener listener) {
		calMenu.getOpenCalendarMenuItem().addActionListener(listener);
	}
	
	/**
	 * This passes a mouse listener down to the different views
	 * @param listener is the mouse listener to be added to the 
	 * 		  save calendar button
	 */
	
	
	public void addSaveCalendarActionListener(ActionListener listener) {
		calMenu.getSaveCalendarMenuItem().addActionListener(listener);
	}
	
	/**
	 * This passes a mouse listener down to the different views
	 * @param listener is the mouse listener to be added to the 
	 * 		  save as button
	 */
	
	
	public void addSaveAsCalendarActionListener(ActionListener listener) {
		calMenu.getSaveAsCalendarMenuItem().addActionListener(listener);
	}
	
	/**
	 * This passes a mouse listener down to the different views
	 * @param listener is the mouse listener to be added to the 
	 * 		  exit button
	 */
	
	
	public void addExitApplicationActionListener(ActionListener listener) {
		calMenu.getExitApplicationMenuItem().addActionListener(listener);
	}
	
	/**
	 * This passes a mouse listener down to the different views
	 * @param listener is the mouse listener to be added to the 
	 * 		 new appointment button
	 */
	
	public void addNewAppointmentActionListener(ActionListener listener) {
		calMenu.getNewAppointmentMenuItem().addActionListener(listener);
	}
	
	/**
	 * This passes a mouse listener down to the different views
	 * @param listener is the mouse listener to be added to the 
	 * 		  edit appointment button
	 */
	
	public void addEditAppointmentActionListener(ActionListener listener) {
		calMenu.getEditAppointmentMenuItem().addActionListener(listener);
		appointmentView.addEditSingleListener(listener);
	}
	
	/**
	 * This passes a mouse listener down to the different views
	 * @param listener is the mouse listener to be added to the 
	 * 		  edit all appointment button
	 */
	
	public void addEditAllAppointmentActionListener(ActionListener listener) {
		calMenu.getEditAllAppointmentMenuItem().addActionListener(listener);
		appointmentView.addEditRecurringListener(listener);
	}
	
	/**
	 * This passes a mouse listener down to the different views
	 * @param listener is the mouse listener to be added to the 
	 * 		  remove button
	 */
	
	public void addRemoveAppointmentActionListener(ActionListener listener) {
		calMenu.getRemoveAppointmentMenuItem().addActionListener(listener);
		appointmentView.addRemoveSingleListener(listener);
	}
	
	/**
	 * This passes a mouse listener down to the different views
	 * @param listener is the mouse listener to be added to the 
	 * 		  Remove all appointment button
	 */
	
	public void addRemoveAllAppointmentActionListener(ActionListener listener) {
		calMenu.getRemoveAllAppointmentMenuItem().addActionListener(listener);
		appointmentView.addRemoveRecurringListener(listener);
	}
	
	/**
	 * This passes a mouse listener down to the different views
	 * @param listener is the mouse listener to be added to the 
	 * 		  preerences button
	 */
	
	public void addPreferencesActionListener(ActionListener listener) {
		calMenu.getPreferencesMenuItem().addActionListener(listener);
	}
	
	/**
	 * This passes a mouse listener down to the different views
	 * @param listener is the mouse listener to be added to the 
	 * 		  about button 
	 */
	
	public void addAboutActionListener(ActionListener listener) {
		calMenu.getAboutMenuItem().addActionListener(listener);
	}
	
	/**
	 * This method updates the different views when an event is called from the model
	 * it passes the subset of the total set of appointments by calling get range, it
	 * also passes the selected appointment and the selected date to the views
	 * 
	 * @param refSet the total set of refrence appointments
	 * @param selectedDate the currently selected Date
	 * @param selectedAppointment the currently selected Appointment
	 * @param currentFile the current file
	 */
	
	public void update(Set<RefAppointment> refSet, Date selectedDate, RefAppointment selectedAppointment, File currentFile) {
		displayDate.setText(FORMAT.format(selectedDate));
		
		if(refSet != null) {
			Set<RefAppointment> subSet;
			
			subSet = AppointmentUtility.getRange(refSet, new MonthRange(selectedDate));
			monthlyView.update(subSet, selectedDate, selectedAppointment);
			
			subSet = AppointmentUtility.getRange(refSet, new WeekRange(selectedDate));
			weeklyView.update(subSet, selectedDate, selectedAppointment);
			
			subSet= AppointmentUtility.getRange(refSet, new DayRange(selectedDate));
			dailyView.update(subSet, selectedDate, selectedAppointment);
		} else {
			monthlyView.update(null, selectedDate, selectedAppointment);
			weeklyView.update(null, selectedDate, selectedAppointment);
			dailyView.update(null, selectedDate, selectedAppointment);
		}
		appointmentView.setAppointment(selectedAppointment);
		
		setTitle((currentFile == null ? "Untitled Calendar" : currentFile.getName()) + " - DCal");
		
		this.validate();
	}
	
	/**
	 * 
	 * This is the constructor, it sets up the different views and buttons and
	 * starts the JFrame
	 * 
	 */
	
	public CalendarView() throws IOException {
		super();
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		calMenu = new CalendarMenu();
		this.setJMenuBar(calMenu);
		
		Date date = new Date();
		
		monthlyView = new MonthlyPanel(date);
		weeklyView = new WeeklyPanel(date);
		dailyView = new DailyPanel(date);
		
		appointmentView = new AppointmentPanel();
		
		displayDate = new JLabel(FORMAT.format(date));
		displayDate.setHorizontalAlignment(SwingConstants.CENTER);
		displayDate.setFont(displayDate.getFont().deriveFont(20f));
		
		prevButton = new JButton("<");
		prevButton.setFocusable(false);
		//prevButton.setPreferredSize(new Dimension(100, 0));
		
		nextButton = new JButton(">");
		nextButton.setFocusable(false);
		//nextButton.setPreferredSize(new Dimension(100, 0));
				
		tabs = new JTabbedPane();
		
		tabs.add(monthlyView, "Month View");
		tabs.add(weeklyView, "Week View");
		tabs.add(dailyView, "Day View");
		
		
		JLabel logo = new JLabel(new ImageIcon(ImageIO.read(new File("img/logo.png"))));
		logo.setBorder(new EmptyBorder(4, 4, 4, 4));
		logo.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JPanel top = new JPanel();
		top.setOpaque(false);
		top.setLayout(new BorderLayout());
		top.add(prevButton, BorderLayout.WEST);
		top.add(displayDate, BorderLayout.CENTER);
		top.add(nextButton, BorderLayout.EAST);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setBorder(new EmptyBorder(0, 4, 0, 4));
		centerPanel.setOpaque(false);
		centerPanel.setLayout(new BorderLayout());
		centerPanel.add(tabs, BorderLayout.CENTER);
		centerPanel.add(top, BorderLayout.NORTH);
		
		JPanel contentPane = new JPanel();
		contentPane.setOpaque(false);
		contentPane.setLayout(new BorderLayout());
		contentPane.add(logo, BorderLayout.NORTH);
		contentPane.add(centerPanel, BorderLayout.CENTER);
		contentPane.add(appointmentView, BorderLayout.EAST);
		
		setContentPane(contentPane);
		setTitle("Untitled Calendar - DCal");
		setBackground(Color.WHITE);
		
		this.setIconImage(ImageIO.read(new File("img/icon.png")));
		
		pack();
		setMinimumSize(getSize());
	}
}
