package com.std.model.appointment;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

import com.std.model.pattern.RecurrencePattern;

/**
 * An AppointmentTemplate is a free-floating template for an appointment.  It
 * contains all of the data to create multiple entries of a type of appointment,
 * but cannot serve directly as an appointment because it lacks a start date.
 * To make a full appointment, one can instantiate the template by making a
 * RefAppointment (reference appointment) that references a template and contains
 * a start date for binding it to the calendar.<br/>
 * <br/>
 * Usage:<br/>
 * <tt>
 *  CalendarModel model = new CalendarModel();<br/>
 *  <br/>
 *  // creates a new template<br/>
 *  AppointmentTemplate template = new AppointmentTemplate("title", "", "n/a", 0);<br/>
 *  <br/>
 *  // adds it to the calendar model<br/>
 *  model.getAppointmentTemplateSet().add(template);<br/>
 *  <br/>
 *  // creates a new reference to the template<br/>
 *  RefAppointment reference = new RefAppointment(template, new Date());<br/>
 *  <br/>
 *  // adds the reference to the calendar model<br/>
 *  model.getAppointmentSet().add(reference);
 * </tt>
 * 
 * @author xxx
 *
 */
public class AppointmentTemplate extends java.util.Observable implements Serializable {

	/**
	 * UID Used for Serializable
	 */
	private static final long serialVersionUID = 8219757873492578630L;

	/** 
	 * Title of the appointment.
	 */
	private String title;
	
	/**
	 * Description of the appointment.
	 */
	private String description;
	
	/**
	 * Where the appointment takes place.
	 */
	private String location;
	
	/**
	 * How long the appointment occurs for in milliseconds.
	 */
	private long duration;
	
	/**
	 * The pattern that this appointment recurs by (daily,weekly, etc.)
	 */
	private RecurrencePattern recPattern;	
	
	/**
	 * Returns the title of the appointment template object.
	 * 
	 * @return the title of the appointment template object.
	 */
	public String getTitle(){
		return title;
	}

	/**
	 * Returns the description of the appointment template object.
	 * 
	 * @return the description of the appointment template object.
	 */
	public String getDescription(){
		return description;
	}
	
	/**
	 * Returns the location of the appointment template Object.
	 * 
	 * @return the location of the appointment template Object.
	 */
	public String getLocation(){
		return location;
	}

	/**
	 * Returns the duration of the appointment template object in milliseconds.
	 * 
	 * @return the duration of the appointment template object in milliseconds.
	 */
	public long getDuration(){
		return duration;
	}

	/**
	 * Returns the pattern object of this appointment template.
	 * 
	 * @return the pattern object of this appointment template.
	 */
	public RecurrencePattern getPattern() {
		return recPattern;
	}

	/**
	 * Sets the title of the appointment, validates
	 * the input, and marks the Observable as changed
	 * if a change has taken place.
	 * 
	 * @param title the new title of the appointment.
	 * @throws NullPointerException if title is null
	 */
	private void internalSetTitle(String title) {
		if(title == null)
			throw new NullPointerException("title");
		if(!title.equals(this.title))
			this.setChanged();
		this.title = title;
	}

	/**
	 * Sets the title of the appointment, and
	 * notifies observers if a change has taken
	 * place.
	 * 
	 * @param title the new title of the appointment.
	 * @throws NullPointerException if title is null
	 */
	public void setTitle(String title) {
		internalSetTitle(title);
		this.notifyObservers();
	}

	/**
	 * Sets the description of the appointment, validates
	 * the input, and marks the Observable as changed
	 * if a change has taken place.
	 * 
	 * @param description the new description for the appointment
	 * @throws NullPointerException if description is null
	 */
	private void internalSetDescription(String description) {
		if(description == null)
			throw new NullPointerException("description");
		if(!description.equals(this.description))
			this.setChanged();
		this.description = description;
	}

	/**
	 * Sets the description of the appointment, and
	 * notifies observers if a change has taken
	 * place.
	 * 
	 * @param description the new description for the appointment
	 * @throws NullPointerException if description is null
	 */
	public void setDescription(String description) {
		internalSetDescription(description);
		this.notifyObservers();
	}

	/**
	 * Sets the location of the appointment, validates
	 * the input, and marks the Observable as changed
	 * if a change has taken place.
	 * 
	 * @param location the new location for the appointment
	 * @throws NullPointerException if loc is null
	 */
	private void internalSetLocation(String loc) {
		if(loc == null)
			throw new NullPointerException("loc");
		if(!loc.equals(this.location))
			this.setChanged();
		this.location = loc;
	}

	/**
	 * Sets the location of the appointment, and
	 * notifies observers if a change has taken
	 * place.
	 * 
	 * @param loc the new location for the appointment
	 * @throws NullPointerException if loc is null
	 */
	public void setLocation(String loc) {
		internalSetLocation(loc);
		this.notifyObservers();
	}
	
	/**
	 * Sets the duration of the appointment, validates
	 * the input, and marks the Observable as changed
	 * if a change has taken place.
	 * 
	 * @param duration the new duration for the appointment in milliseconds
	 * @throws IllegalArgumentException if duration is less than 0
	 */
	private void internalSetDuration(long duration) {
		if(duration < 0)
			throw new IllegalArgumentException("duration is less than 0");
		if(duration != this.duration)
			this.setChanged();
		this.duration = duration;
	}

	/**
	 * Sets the duration of the appointment, and
	 * notifies observers if a change has taken
	 * place.
	 * 
	 * @param duration the new duration for the appointment in milliseconds
	 * @throws IllegalArgumentException if duration is less than 0
	 */
	public void setDuration(long duration) {
		internalSetDuration(duration);
		this.notifyObservers();
	}

	/**
	 * Sets the pattern of the appointment, validates
	 * the input, and marks the Observable as changed
	 * if a change has taken place.
	 * 
	 * @param pattern the new pattern for the appointment
	 */
	private void internalSetPattern(RecurrencePattern pattern) {
		if(pattern == null ? this.recPattern != null : !pattern.equals(this.recPattern))
			this.setChanged();
		this.recPattern = pattern;
	}

	/**
	 * Sets the pattern of the appointment, and
	 * notifies observers if a change has taken
	 * place.
	 * 
	 * @param pattern the new pattern for the appointment
	 */
	public void setPattern(RecurrencePattern pattern) {
		internalSetPattern(pattern);
		this.notifyObservers(recPattern);
	}

	/**
	 * Sets all the fields of the appointment, and
	 * notifies observers if a change has taken
	 * place.
	 * 
	 * @param apptTmple the new appointment fields
	 */
	public void setFields(AppointmentTemplate apptTmpl) {
		internalSetTitle(apptTmpl.getTitle());
		internalSetDescription(apptTmpl.getDescription());
		internalSetLocation(apptTmpl.getLocation());
		internalSetDuration(apptTmpl.getDuration());
		internalSetPattern(apptTmpl.getPattern());
		this.notifyObservers();
	}

	/**
	 * Loads the object from an object input stream
	 * 
	 * @param istream the object stream to be reading from
	 * @throws ClassNotFoundException if there is a casting error
	 * @throws IOException an I/O exception of some sort has occurred
	 */
	private void readObject(ObjectInputStream istream) throws ClassNotFoundException, IOException {
		internalSetTitle((String)istream.readObject());
		internalSetDescription((String)istream.readObject());
		internalSetLocation((String)istream.readObject());
		internalSetDuration(istream.readLong());
		internalSetPattern((RecurrencePattern)istream.readObject());
	}
	
	/**
	 * Saves the object to an object output stream
	 * 
	 * @param ostream the object stream to be writing to
	 * @throws IOException an I/O exception of some sort has occurred
	 */
	private void writeObject(ObjectOutputStream ostream) throws IOException {
		ostream.writeObject(getTitle());
		ostream.writeObject(getDescription());
		ostream.writeObject(getLocation());
		ostream.writeLong(getDuration());
		ostream.writeObject(getPattern());
	}
	
	public Object clone() {
		AppointmentTemplate ret = new AppointmentTemplate("", "", "", 0);
		ret.setFields(this);
		return ret;
	}
	
	/**
	 * creates a new AppointmentTemplate
	 * 
	 * @param title Title of the appointment.
	 * @param description Description of the appointment.
	 * @param location Location of the appointment.
	 * @param duration Duration of the appointment in milliseconds.
	 */
	public AppointmentTemplate(String title, String description, String location, long duration) {
		super();
		internalSetTitle(title);
		internalSetDescription(description);
		internalSetLocation(location);
		internalSetDuration(duration);
		recPattern = null;
		
		this.notifyObservers();
	}
}
