package com.std.model.pattern;

import com.std.util.range.DateRange;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

/**
 * This class is the representation of a pattern that would exist on certain
 * days of the week. If the user would like an appointment to exist several
 * times a week then this is the class that would deal with it
 *
 * @author xxx
 */
public class DayOfWeekPattern extends RecurrencePattern{

	/**
	 * UID Used for Serializable
	 */
	private static final long serialVersionUID = 5054653561326906195L;

	private static final int SUNDAY = 0;
	private static final int MONDAY = 1;
	private static final int TUESDAY = 2;
	private static final int WEDNESDAY = 3;
	private static final int THURSDAY = 4;
	private static final int FRIDAY = 5;
	private static final int SATURDAY = 6;

	/**
	 * boolean array based on days of the weeks
	 */
	private boolean[] days;

	/**
	 * creates a new DayOfWeekPattern
	 *
	 * @param r    date range of the occurrence pattern
	 * @param days of the week to occur on.  the indices of the array correspond to
	 *             the days of the week constants in the Calendar class:<br/>
	 *             <br/><tt> boolean[] days = new boolean[7];<br/>
	 *             days[Calendar.SUNDAY] = true;<br/> days[Calendar.SATURDAY] =
	 *             true; </tt>
	 *
	 * @throws NullPointerException     if days is null
	 * @throws IllegalArgumentException if the length of days is not 7
	 */
	public DayOfWeekPattern(DateRange r, boolean[] days){
		super(r);
		setDays(days);
	}

	/**
	 * sets the days of the week to occur on.
	 *
	 * @param days days of the week to occur on.  the indices of the array
	 *             correspond to the days of the week constants in the Calendar
	 *             class:<br/> <br/><tt> boolean[] days = new boolean[7];<br/>
	 *             days[Calendar.SUNDAY] = true;<br/> days[Calendar.SATURDAY] =
	 *             true; </tt>
	 *
	 * @throws NullPointerException     if days is null
	 * @throws IllegalArgumentException if the length of days is not 7
	 */
	public void setDays(boolean[] days){
		this.days = new boolean[7];
		if(days == null){
			throw new NullPointerException("days");
		}
		if(days.length != 7){
			throw new IllegalArgumentException("length of days is not 7");
		}
		System.arraycopy(days, 0, this.days, 0, this.days.length);
	}

	/**
	 * @return true if appointment occurs of Sunday false if it does not
	 */
	public boolean onSunday(){
		return days[SUNDAY];
	}

	/**
	 * @return true if appointment occurs of Monday false if it does not
	 */
	public boolean onMonday(){
		return days[MONDAY];
	}

	/**
	 * @return true if appointment occurs of Tuesday false if it does not
	 */
	public boolean onTuesday(){
		return days[TUESDAY];

	}

	/**
	 * @return true if appointment occurs of Wednesday false if it does not
	 */
	public boolean onWednesday(){
		return days[WEDNESDAY];
	}

	/**
	 * @return true if appointment occurs of Thursday false if it does not
	 */
	public boolean onThursday(){
		return days[THURSDAY];
	}

	/**
	 * @return true if appointment occurs of Friday false if it does not
	 */
	public boolean onFriday(){
		return days[FRIDAY];
	}

	/**
	 * @return true if appointment occurs of Saturday false if it does not
	 */
	public boolean onSaturday(){
		return days[SATURDAY];
	}

	public boolean[] getDays(){
		return Arrays.copyOf(days, days.length);
	}

	/**
	 * This method returns a set of all the dates that the appointment this class
	 * represents should exist on
	 *
	 * @return returns a set of dates that the pattern exists on
	 */
	public Set<Date> getDates(){
		Set<Date> dates = new HashSet<Date>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(getRange().getStartDate());
		int currentDay;
		while(cal.getTime().before(getRange().getEndDate())){
			currentDay = cal.get(Calendar.DAY_OF_WEEK) - Calendar.SUNDAY;
			if(days[currentDay]){
				dates.add((Date) cal.getTime().clone());
			}
			cal.add(Calendar.DATE, 1);
		}
		return dates;
	}

	/**
	 * This method is for loading the Recurrence pattern
	 *
	 * @param in	The file handler to read the objects from.
	 * @throws ClassNotFoundException if there is a casting error
	 * @throws IOException            an I/O exception of some sort has occurred
	 * @see <a href="http://java.sun.com/javase/6/docs/api/java/io/Serializable.html">Serializable</a>
	 */
	protected void readObject(ObjectInputStream in)
			throws ClassNotFoundException, IOException{
		setDays((boolean[]) in.readObject());
		setRange((DateRange) in.readObject());
	}

	/**
	 * This method id for saving the Recurrence pattern
	 *
	 * @param out is object stream to be writing to
	 *
	 * @throws IOException an I/O exception of some sort has occurred
	 * @see <a href="http://java.sun.com/javase/6/docs/api/java/io/Serializable.html">Serializable</a>
	 */
	protected void writeObject(ObjectOutputStream out) throws IOException{
		out.writeObject(days);
		out.writeObject(getRange());
	}

}
