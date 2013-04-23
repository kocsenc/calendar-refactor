package com.std.util.range;
import java.io.Serializable;
import java.util.Date;

/**
 * represents a constant range of dates
 * @author xxx
 */
public class DateRange implements Serializable {
	
	public static final long serialVersionUID = 0L;
	
	/**
	 * number of milliseconds in a day
	 */
	private static final long MS_PER_DAY = 1000 * 60 * 60 * 24;
	
	/**
	 * start date of the range
	 */
	private Date startDate;
	
	/**
	 * end date of the range
	 */
	private Date endDate;
	
	/**
	 * creates a new DateRange where the start and 
	 * end dates are set to the moment of creation
	 */
	public DateRange() {
		// this eventually forwards to the defined
		// mutator methods, which default to new Date()
		this(null, null);
	}
	
	/**
	 * start and end dates are set to the given date
	 * @param date the start and end dates of the range
	 */
	public DateRange(Date date) {
		// this eventually forwards to the defined
		// mutator methods, which default to new Date()
		this(date, null);
	}
	
	/**
	 * start and end dates are set to the given dates
	 * @param startDate the start date of the range
	 * @param endDate the end date of the range
	 */
	public DateRange(Date startDate, Date endDate) {
		// the defined mutator methods 
		// default to new Date(), so
		// we don't have to worry about
		// nulls, and there is no other
		// bad input
		setStartDate(startDate);
		setEndDate(endDate);
	}
	public boolean equals(Object obj) {
		// this is overridden to make sure we're
		// testing equality by value and not by
		// reference.  otherwise it would mess
		// up any sorting functions
		
		boolean ret = false;
		try {
			DateRange cmp = (DateRange)obj;
			ret = cmp.startDate.equals(startDate) && cmp.endDate.equals(endDate);
		} catch(Exception e) {}
		return ret;
	}
	
	/**
	 * @return the length of the range in days
	 */
	public double getDurationInDays() {
		// x MD / y (MS / Days) = (x/y) Days
		return getDurationInMS() / (double)MS_PER_DAY;
	}
	
	/**
	 * @return the length of the range in milliseconds
	 */
	public long getDurationInMS() {
		// ticks represent milliseconds, so just get the difference
		return endDate.getTime() - startDate.getTime();
	}
	
	/**
	 * @return the end date of the range
	 */
	public Date getEndDate() {
		// we clone because DateRange is supposed 
		// to be immutable, and the Date class 
		// has methods to alter itself
		// this prevents any alterations
		// to the returned date from affecting us
		return (Date)endDate.clone();
	}
	
	/**
	 * @return the start date of the range
	 */
	public Date getStartDate() {
		// we clone because DateRange is supposed 
		// to be immutable, and the Date class 
		// has methods to alter itself
		// this prevents any alterations
		// to the returned date from affecting us
		return (Date)startDate.clone();
	}
	public int hashCode() {
		// hashCode has a contract with equals, so
		// if we're re-implementing equals, we need
		// to re-implement this too.  otherwise
		// anything that tries to hash this class
		// will act wonky
		return startDate.hashCode() ^ endDate.hashCode();
	}

	/**
	 * changes the end date of the range
	 * @param endDate the end date of the range
	 */
	protected void setEndDate(Date endDate) {
		// this is necessary for sub classes
		// that might have to calculate the
		// end date after the call to the
		// super constructor
		
		// default to new Date()
		this.endDate = endDate == null ? startDate == null ? new Date() : (Date)startDate.clone() : (Date)endDate.clone();
	}

	/**
	 * changes the start date of the range
	 * @param startDate the start date of the range
	 */
	protected void setStartDate(Date startDate) {
		// this is necessary for sub classes
		// that might have to calculate the
		// start date after the call to the
		// super constructor
		
		// default to new Date()
		this.startDate = startDate == null ? new Date() : (Date)startDate.clone();
	}
	public String toString() {
		// just so we print by value and not reference
		return "{" + startDate + " -> " + endDate + "}";
	}
}
