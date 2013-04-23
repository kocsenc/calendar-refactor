package com.std.util.range;

import java.util.Calendar;
import java.util.Date;

/**
 * represents a full day as a range of dates
 * @author xxx
 */
public class DayRange extends DateRange {
	
	static final long serialVersionUID = 0L;
	
	/**
	 * creates a DayRange representing today
	 */
	public DayRange() {
		// the date parameter constructor 
		// passes the null to the DateRange
		// constructor, which uses new Date()
		// by default
		this(null);
	}
	
	/**
	 * creates a GridMonthRange representing the given date
	 * @param date day to represent
	 */
	public DayRange(Date date) {
		// defaults to new Date() if null
		super(date);

		// set up the calendar
		Calendar cal = Calendar.getInstance();
		cal.setTime(getStartDate());

		// make sure we're looking at midnight
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		// set the start date
		setStartDate(cal.getTime());

		// add a 1 day
		cal.add(Calendar.HOUR_OF_DAY, 24);
		
		// set the end date
		setEndDate(cal.getTime());
	}
	
	/**
	 * @return the next sequential day
	 */
	public DayRange nextDay() {
		// our end date is exactly
		// at midnight at the start of the
		// next day, so we can use it
		// directly to get the appropriate
		// new DayRange
		return new DayRange(getEndDate());
	}
	
	/**
	 * @return the previous sequential day
	 */
	public DayRange previousDay() {
		// because our start date is exactly
		// at midnight at the start of the
		// day, even one tick prior to that
		// can be used to get the whole
		// prior day in a new DayRange
		return new DayRange(new Date(getStartDate().getTime() - 1));
	}
}
