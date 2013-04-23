package com.std.model;

/**
 * 
 * this class allows for users to easily manipulate the model, it has
 * utility static methods that the user passes the model and then needed
 * information and then the utility takes care of the rest
 * 
 * @author xxx
 */
import java.util.Calendar;
import java.util.Date;
import java.util.TreeSet;

import com.std.model.appointment.AppointmentTemplate;
import com.std.model.appointment.AppointmentUtility;
import com.std.model.appointment.RefAppointment;

public class CalendarModelUtility {
	
	/**
	 * This method adds a certain amount to a date object and
	 * returns a date representing that date
	 * @param date is the start date
	 * @param field is the field that the user was to incremnet
	 * @param amount by how much they would llike to increment
	 * @return
	 */
	
	private static Date add(Date date, int field, int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(field, amount);
		return cal.getTime();
	}
	
	/**
	 * rolls the claendar model one year
	 * 
	 * @param model is the model to roll
	 */

	public static void nextYear(CalendarModel model) {
		model.setCurrentDate(add(model.getCurrentDate(), Calendar.YEAR, 1));
	}
	/**
	 * rolls the claendar model one month
	 * 
	 * @param model is the model to roll
	 */
	
	public static void nextMonth(CalendarModel model) {
		model.setCurrentDate(add(model.getCurrentDate(), Calendar.MONTH, 1));
	}
	
	/**
	 * rolls the claendar model one week
	 * 
	 * @param model is the model to roll
	 */
	
	public static void nextWeek(CalendarModel model) {
		model.setCurrentDate(add(model.getCurrentDate(), Calendar.DATE, 7));
	}
	
	/**
	 * rolls the claendar model one day
	 * 
	 * @param model is the model to roll
	 */
	
	public static void nextDay(CalendarModel model) {
		model.setCurrentDate(add(model.getCurrentDate(), Calendar.DATE, 1));
	}
	
	/**
	 * this method allows the user to get the next appointment
	 * @param model is the model to get the appointment from
	 */
	
	public static void nextAppointment(CalendarModel model) {
		TreeSet<RefAppointment> set = new TreeSet<RefAppointment>(RefAppointment.COMPARATOR_APPOINTMENT_START);
		set.addAll(model.getAppointmentSet());
		model.setCurrentAppointment(set.higher(model.getCurrentAppointment()));
	}
	
	/**
	 * rolls the model back a year
	 * 
	 * @param model the model to change
	 */
	
	public static void previousYear(CalendarModel model) {
		model.setCurrentDate(add(model.getCurrentDate(), Calendar.YEAR, -1));
	}
	
	/**
	 * rolls the model back a month
	 * 
	 * @param model the model to change
	 */
	
	public static void previousMonth(CalendarModel model) {
		model.setCurrentDate(add(model.getCurrentDate(), Calendar.MONTH, -1));
	}
	
	/**
	 * rolls the model back a week
	 * 
	 * @param model the model to change
	 */
	
	public static void previousWeek(CalendarModel model) {
		model.setCurrentDate(add(model.getCurrentDate(), Calendar.DATE, -7));
	}
	
	/**
	 * rolls the model back a day
	 * 
	 * @param model the model to change
	 */
	
	public static void previousDay(CalendarModel model) {
		model.setCurrentDate(add(model.getCurrentDate(), Calendar.DATE, -1));
	}
	
	/**
	 * Allows the user to roll to the previous appointment
	 * @param model the model to roll
	 */
	
	public static void previousAppointment(CalendarModel model) {
		TreeSet<RefAppointment> set = new TreeSet<RefAppointment>(RefAppointment.COMPARATOR_APPOINTMENT_START);
		set.addAll(model.getAppointmentSet());
		model.setCurrentAppointment(set.lower(model.getCurrentAppointment()));
	}
	
	/**
	 * creates a template based on the model sent
	 * @param model the model to get the defaults from
	 * @return returns a default appointmnet template
	 */
	
	public static AppointmentTemplate getNewAppointmentTemplate(CalendarModel model) {
		AppointmentTemplate ret = new AppointmentTemplate("", "", "", 0);
		ret.setFields(model.getCurrentDefaults());
		return ret;
	}
	
	/**
	 * creates a reference based on the model sent
	 * @param model the model to get the defaults from
	 * @return returns a default RefAppointment
	 */
	
	public static RefAppointment getNewAppointment(CalendarModel model, AppointmentTemplate apptTmpl) {
		return new RefAppointment(model.getCurrentDate(), apptTmpl);
	}
	
	/**
	 * creates a reference based on the model sent
	 * @param model the model to get the defaults from
	 * @return returns a default RefAppointment
	 */
	
	public static RefAppointment getNewAppointment(CalendarModel model) { 
		return getNewAppointment(model, getNewAppointmentTemplate(model)); 
	}
	
	
	/**
	 * Adds a refrence appointment to the model
	 * @param model is the model to add things
	 * @param appt is the appointment to be added
	 */
	
	public static void add(CalendarModel model, RefAppointment appt) {
		model.getAppointmentTemplateSet().add(appt.getTemplate());
		model.getAppointmentSet().add(appt);
	}
	
	/**
	 * Add a ref appointment with the given pattern
	 * 
	 * @param model is the model to add to
	 * @param appt is the appointmnet to add
	 */
	
	public static void addUsingPattern(CalendarModel model, RefAppointment appt) {
		model.getAppointmentTemplateSet().add(appt.getTemplate());
		
		model.removeReferences(appt.getTemplate());
		if(appt.getPattern() != null)
			model.getAppointmentSet().addAll(AppointmentUtility.generatePatternAppointments(appt.getTemplate()));
		else {
			model.getAppointmentSet().add(appt);
		}
		
		
	}
	
	public static boolean conflictingAppointments(RefAppointment one, RefAppointment two) {
		if (one.getStartDate().getTime() > two.getStartDate().getTime()) {
			if (one.getStartDate().getTime() < two.getEndDate().getTime()) {
				return true;
			}
			else {
				return false;
			}
		}
		else if(one.getStartDate().getTime() < two.getStartDate().getTime() ) {
			if (two.getStartDate().getTime() <  one.getEndDate().getTime()) {
				return true;
			}
			else return false;
		}
		else {
			return true;
		}
	}
}
