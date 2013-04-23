package com.std.model.pattern;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.std.util.range.DateRange;


/**
 * This class is the representation of a pattern that would exist 
 * every N number of days. If the user would like an appointment to 
 * exist over a periodic number of days, then this is the class that 
 * would deal with it
 * 
 * @author xxx
 *
 */
public class NDaysPattern extends RecurrencePattern {

	/**
	 * UID Used for Serializable
	 */
	private static final long serialVersionUID = -188613333163734408L;
	
	/**
	 * The number of days between each occurrence of the appointment.
	 */
	private int n;
	
	/**
	 * passes DateRange to constructor in super
	 * throws IllegalArgumentException if nDays is less than 1
	 * sets n to nDays
	 * 
	 * @param r date range of the occurrence pattern
	 * @param nDays number of days between each occurrence
	 */
	public NDaysPattern(DateRange r, int nDays) {
		super(r);
		if(nDays < 0)
			throw new IllegalArgumentException("number of days is less than 0");
		this.n  = nDays; 
	}
	
	/**
	 * Gets the number of days between the occurrences
	 * 
	 * @return the number of days between the occurrences
	 */
	public int instanceEvery(){
		return n;
	}

	/**
	 * This method returns a set of all the dates
	 * that the appointment this class represents
	 * should exist on
	 * 
	 * @return returns a set of dates that the pattern exists on
	 */
	public Set<Date> getDates() {		
		Set<Date> dates = new HashSet<Date>();
		Date StartD = getRange().getStartDate();
		Calendar c = Calendar.getInstance();
		c.setTime(StartD);
		
		while(c.getTime().before(getRange().getEndDate())) {
			dates.add((Date)c.getTime().clone());
			c.add(Calendar.DATE, n);
		}
		
		return dates;
	}
	
	/**
	 * This method is for loading the Recurrence pattern
	 * 
	 * @throws ClassNotFoundException if there is a casting error
	 * @throws IOException an I/O exception of some sort has occurred
	 * @param o is the object stream to be reading from to
	 */
	protected void readObject(ObjectInputStream out) throws ClassNotFoundException, IOException{
		int i = out.readInt();
		setInstanceEveryDays(i);
		
		DateRange r = (DateRange) out.readObject();
		setRange(r);
	}
	
	/**
	 * This method id for saving the Recurrence pattern
	 * 
	 * @throws IOException an I/O exception of some sort has occurred
	 * @param i is object stream to be reading from
	 */
	protected void writeObject(ObjectOutputStream in) throws IOException {
		in.writeInt(n);
		in.writeObject(getRange());
	}

	public void setInstanceEveryDays(int x) {
		n = x;
	}
	
	
	
}
