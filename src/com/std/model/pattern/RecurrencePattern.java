package com.std.model.pattern;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import com.std.util.range.DateRange;

/**
 * This is the abstract class Recurrence Pattern
 * It is a template for the patterns that recurring appointments
 * should take. For example if a user would want to have
 * an appointment happen every Tuesday and Thursday, one
 * could extend this class into a class that returns the dates
 * in which that appointment should exist
 * 
 * @author xxx
 *
 */
public abstract class RecurrencePattern implements Serializable {

	/**
	 * This is the span of time that the appointment
	 * can exist on.
	 */
	private DateRange occuranceRange;

	
	/**
	 * This is the constructor it takes in the
	 * date range and sets it as the occuranceRange
	 * 
	 * @param d the occurrence range of this pattern
	 * @throws NullPointerException if d is null
	 * @throws IllegalArgumentException if the duration of d is less than 0
	 */
	public RecurrencePattern(DateRange d) {
		setRange(d);
	}
	
	
	/**
	 * This method returns a set of all the dates
	 * that the appointment this class represents
	 * should exist on
	 * 
	 * @return returns a set of dates that the pattern exists on
	 */
	public abstract Set<Date> getDates();
	
	/**
	 * Returns the occurrence range of this pattern
	 *  
	 * @return the occurrence range of this pattern
	 */
	public DateRange getRange() {
		return occuranceRange;
	}
	
	/**
	 * Sets the occurrence range of this pattern
	 * 
	 * @param d the new occurrence range of this pattern
	 * @throws NullPointerException if d is null
	 * @throws IllegalArgumentException if the duration of d is less than 0
	 */
	protected void setRange(DateRange d) {
		if(d ==  null)
			throw new NullPointerException();
		if(d.getDurationInMS() < 0)
			throw new IllegalArgumentException("duration is less than 0");
		occuranceRange = d;
	}
	
	/**
	 * This method is for loading the Reoccurence pattern
	 * 
	 * @param o is the object stream to be reading from to
	 * @throws ClassNotFoundException if there is a casting error
	 * @throws IOException an I/O exception of some sort has occurred
	 * @see <a href="http://java.sun.com/javase/6/docs/api/java/io/Serializable.html">Serializable</a>
	 */
	protected abstract void readObject(ObjectInputStream o) throws ClassNotFoundException, IOException;
	
	/**
	 * This method id for saving the reoccurence pattern
	 * 
	 * @param i is object stream to be reading from
	 * @throws IOException an I/O exception of some sort has occurred
	 * @see <a href="http://java.sun.com/javase/6/docs/api/java/io/Serializable.html">Serializable</a>
	 */
	protected abstract void writeObject(ObjectOutputStream i)throws IOException;
	
}
