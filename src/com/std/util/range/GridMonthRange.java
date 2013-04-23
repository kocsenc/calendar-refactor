package com.std.util.range;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * represents a full month as a range of dates
 * @author xxx
 */
public class GridMonthRange extends DateRange {
	
	static final long serialVersionUID = 0L;
	
	/**
	 * creates a GridMonthRange representing this month
	 */
	public GridMonthRange() {
		// the date parameter constructor 
		// passes the null to the DateRange
		// constructor, which uses new Date()
		// by default
		this(null);
	}
	
	/**
	 * creates a GridMonthRange representing the month of the given date
	 * @param date a date in the month to represent
	 */
	public GridMonthRange(Date date) {
		// defaults to new Date() if null
		super(date);

		// set up the calendar
		Calendar cal = Calendar.getInstance();
		cal.setTime(getStartDate());

		// make sure we're looking at 
		// midnight on the Sunday at or
		// prior to the 1st of the month
		cal.set(Calendar.DATE, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		
		cal.add(Calendar.DATE, 1 - cal.get(Calendar.DAY_OF_WEEK));
		
		// set the start date
		setStartDate(cal.getTime());

		// increment 6 weeks
		cal.add(Calendar.DATE, 42);

		// set the end date
		setEndDate(cal.getTime());
	}
	
	/**
	 * @return a list of the 6-weeks representing this month's grid
	 */
	public List<WeekRange> getWeeks() {
		ArrayList<WeekRange> ret = new ArrayList<WeekRange>();
		
		// using a calendar object to 
		// iterate through the dates
		Calendar cal = Calendar.getInstance();
		cal.setTime(getStartDate());
		
		// our end date is exactly 6
		// weeks after our start date
		// so, this should loop only
		// 6 times
		Date endDate = getEndDate();
		while(!cal.getTime().equals(endDate)) {
			
			// add a WeekRange to the result
			ret.add(new WeekRange(cal.getTime()));
			
			// increment what week we're looking at
			cal.add(Calendar.DATE, 7);
		}
		
		return ret;
	}
	
	/**
	 * @return the next sequential month
	 */
	public GridMonthRange nextMonth() {
		// the earliest our end date can
		// possible be is exactly
		// at midnight at the start of the
		// next month, so we can use it
		// directly to get the appropriate
		// new MonthRange.  
		// we don't have to worry about the 
		// latest that date can be, because
		// it's at most 6 days into the
		// next month, and no month 
		// has less than 6 days in it.
		return new GridMonthRange(getEndDate());
	}
	
	/**
	 * @return the previous sequential month
	 */
	public GridMonthRange previousMonth() {
		// the latest our start date can
		// possibly be is exactly
		// at midnight at the start of the
		// month.  even one tick prior to that
		// can be used to get the whole
		// prior month in a new GridMonthRange
		// we don't have to worry about the 
		// earliest that date can be, because
		// it's at most 6 days into the
		// previous month, and no month 
		// has less than 6 days in it.
		return new GridMonthRange(new Date(getStartDate().getTime() - 1));
	}
}
