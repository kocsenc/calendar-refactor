package com.std.util.range;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * represents a full week as a range of dates
 * the start date will always be at midnight
 * on the Sunday at the beginning of the week,
 * and the end date is exactly 7 days later.
 * 
 * @author xxx
 */
public class WeekRange extends DateRange {	
	
	static final long serialVersionUID = 0L;
	
	/**
	 * creates a WeekRange representing this week
	 */
	public WeekRange() {
		// the date parameter constructor 
		// passes the null to the DateRange
		// constructor, which uses new Date()
		// by default
		this(null);
	}
	
	/**
	 * creates a WeekRange representing the week of the given date
	 * @param date a date in the week to represent
	 */
	public WeekRange(Date date) {
		// defaults to new Date() if null
		super(date);
		
		// set up the calendar
		Calendar cal = Calendar.getInstance();
		cal.setTime(getStartDate());
		
		// make sure we're looking at midnight on Sunday
		cal.set(Calendar.DAY_OF_WEEK, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		
		// set the start date
		setStartDate(cal.getTime());
		
		// add 7 days
		cal.add(Calendar.DATE, 7);

		// set the end date
		setEndDate(cal.getTime());
	}
	
	/**
	 * @return a list of the 7-days representing this week
	 */
	public List<DayRange> getDays() {
		ArrayList<DayRange> ret = new ArrayList<DayRange>();
		
		// using a calendar object to 
		// iterate through the dates
		Calendar cal = Calendar.getInstance();
		cal.setTime(getStartDate());
		
		// our end date is exactly 7
		// days after our start date
		// so, this should loop only
		// 7 times
		Date endDate = getEndDate();
		while(!cal.getTime().equals(endDate)) {
			
			// add a DayRange to the result
			ret.add(new DayRange(cal.getTime()));
			
			// increment what day we're looking at
			cal.add(Calendar.DATE, 1);
		}
		
		return ret;
	}
	
	/**
	 * @return the next sequential week
	 */
	public WeekRange nextWeek() {
		// our end date is exactly
		// at midnight at the start of the
		// next week, so we can use it
		// directly to get the appropriate
		// new WeekRange
		return new WeekRange(getEndDate());
	}
	
	/**
	 * @return the previous sequential week
	 */
	public WeekRange previousWeek() {
		// because our start date is exactly
		// at midnight at the start of the
		// week, even one tick prior to that
		// can be used to get the whole
		// prior week in a new WeekRange
		return new WeekRange(new Date(getStartDate().getTime() - 1));
	}
}
