package com.std.util.range;

import java.util.Calendar;
import java.util.Date;

/**
 * represents a full month as a range of dates
 * @author xxx
 */
public class MonthRange extends DateRange {	
	
	static final long serialVersionUID = 0L;
	
	/**
	 * creates a MonthRange representing this month
	 */
	public MonthRange() {
		// the date parameter constructor 
		// passes the null to the DateRange
		// constructor, which uses new Date()
		// by default
		this(null);
	}
	
	/**
	 * creates a MonthRange representing the month of the given date
	 * @param date a date in the month to represent
	 */
	public MonthRange(Date date) {
		// defaults to new Date() if null
		super(date);

		// set up the calendar
		Calendar cal = Calendar.getInstance();
		cal.setTime(getStartDate());

		// make sure we're looking at 
		// midnight on the 1st of the month
		cal.set(Calendar.DATE, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		// set the start date
		setStartDate(cal.getTime());

		// rolling back -1 should take us
		// directly to the last day in the
		// month.  then, we only need to add
		// one to get the end date
		cal.roll(Calendar.DATE, -1);
		cal.add(Calendar.DATE, 1);

		// set the end date
		setEndDate(cal.getTime());
	}
	
	/**
	 * @return the next sequential month
	 */
	public MonthRange nextMonth() {
		// our end date is exactly
		// at midnight at the start of the
		// next month, so we can use it
		// directly to get the appropriate
		// new MonthRange
		return new MonthRange(getEndDate());
	}
	
	/**
	 * @return the previous sequential month
	 */
	public MonthRange previousMonth() {
		// because our start date is exactly
		// at midnight at the start of the
		// month, even one tick prior to that
		// can be used to get the whole
		// prior month in a new MonthRange
		return new MonthRange(new Date(getStartDate().getTime() - 1));
	}
}
