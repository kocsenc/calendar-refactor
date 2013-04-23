package com.std.model.appointment;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;

import com.std.model.pattern.RecurrencePattern;
import com.std.util.range.DateRange;


/**
 * A RefAppointment is an instantiated form of an Appointment.  It stores 
 * the start date, and the parent AppointmentTemplate from which it will 
 * draw data such as the appointment's description, title, etc. 
 * This was done so that recurring appointments could be implemented.
 * To make a full appointment, one can create a template, and instantiate
 * it by making a RefAppointment that references the template and contains
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
public class RefAppointment extends java.util.Observable implements Serializable {

	/**
	 * UID Used for Serializable
	 */
	private static final long serialVersionUID = -4331942306839501945L;
	/**
	 * compares two Appointments by their start dates
	 */
	public static final Comparator<RefAppointment> COMPARATOR_APPOINTMENT_START;
	
	/**
	 * compares two Appointments by their end dates
	 */
	public static final Comparator<RefAppointment> COMPARATOR_APPOINTMENT_END;
	
	static {
		// set the start comparator
		COMPARATOR_APPOINTMENT_START = new Comparator<RefAppointment>() {
			public int compare(RefAppointment o1, RefAppointment o2) {
				int ret = o1.getStartDate().compareTo(o2.getStartDate());
				if(ret == 0 && !o1.equals(o2))
					ret++;
				return ret;
			}
		};
		
		// set the end comparator
		COMPARATOR_APPOINTMENT_END = new Comparator<RefAppointment>() {
			public int compare(RefAppointment o1, RefAppointment o2) {
				int ret = o1.getEndDate().compareTo(o2.getEndDate());
				if(ret == 0 && !o1.equals(o2))
					ret++;
				return ret;
			}
		};
	}

	/**
	 * the start date of this particular appointment instance
	 */
	private Date startDate;
	
	/**
	 * the template appointment that stores this appointment's description, etc.
	 */
	private AppointmentTemplate parent;
	
	
	/**
	 * This is the constructor for RefAppointments<br/>
	 * Creates a new bound reference to a TemplateAppointment.
	 * 
 	 * @param d the startDate of the appointment.
 	 * @param p class from which the description, title, etc are to be obtained.
	 */
	public RefAppointment(Date d, AppointmentTemplate p){
		super();
		setTemplate(p);
		internalSetStartDate(d);
		
		notifyObservers(null);
	}
	
	/**
	 * returns the AppointmentTemplate associated with this RefAppointment
	 * 
	 * @return the AppointmentTemplate associated with this RefAppointment
	 */
	public AppointmentTemplate getTemplate(){
		return parent;
	}
	
	/**
	 * sets the AppointmentTemplate associated with this RefAppointment
	 * 
	 * @param t the new AppointmentTemplate associated with this RefAppointment
	 * @throws NullPointerException if AppointmentTemplate is null
	 */
	private void setTemplate(AppointmentTemplate t){
		if(t == null)
			throw new NullPointerException();
		parent = t;
	}
	
	/**
	 * Returns the appointment start date
	 * 
	 * @return the appointment start date
	 */
	public Date getStartDate() {
		return startDate;
	}
	
	/**
	 * Sets the appointment start date, validates the
	 * input, and marks Observable as changed if
	 * any changes have been made.
	 * 
	 * @param date the new appointment start date
	 * @throws NullPointerException if date is null
	 */
	private void internalSetStartDate(Date date){
		if(date == null)
			throw new NullPointerException("date");
		if(!date.equals(this.startDate))
			this.setChanged();
		this.startDate = date;
	}

	/**
	 * Sets the appointment start date, and
	 * notifies any Observers if
	 * any changes have been made.
	 * 
	 * @param date the new start date
	 */
	public void setStartDate(Date date){
		internalSetStartDate(date);
		this.notifyObservers();
	}
	
	// THE FOLLOWING METHODS USE DATA FROM parent:	

	/**
	 * Returns the duration of the appointment template object in milliseconds.
	 * 
	 * @return the duration of the appointment template object in milliseconds.
	 */
	public long getDuration() {
		return parent.getDuration();
	}
	
	/**
	 * Sets the duration of the appointment, and
	 * notifies observers if a change has taken
	 * place.
	 * 
	 * @param duration the new duration for the appointment in milliseconds
	 * @throws IllegalArgumentException if duration is less than 0
	 */
	public void setDuration(long duration){
		parent.setDuration(duration);
	}

	/**
	 * Returns the appointment end date
	 * 
	 * @return the appointment end date
	 */
	public Date getEndDate(){
		return new Date(startDate.getTime() + getDuration());
	}

	/**
	 * Sets the appointment end date, and
	 * notifies any Observers if
	 * any changes have been made.
	 * 
	 * @param date the new appointment end date
	 */
	public void setEndDate(Date date){
		long duration = date.getTime() - startDate.getTime();
		parent.setDuration(duration);
	}

	/**
	 * Returns the location of the appointment template Object.
	 * 
	 * @return the location of the appointment template Object.
	 */
	public String getLocation(){
		return parent.getLocation();
	}

	/**
	 * Sets the location of the appointment, and
	 * notifies observers if a change has taken
	 * place.
	 * 
	 * @param loc the new location for the appointment
	 * @throws NullPointerException if loc is null
	 */
	public void setLocation(String loc){
		parent.setLocation(loc);
	}

	/**
	 * Returns the pattern object of this appointment template.
	 * 
	 * @return the pattern object of this appointment template.
	 */
	public RecurrencePattern getPattern() {
		return parent.getPattern();
	}

	/**
	 * Sets the pattern of the appointment, and
	 * notifies observers if a change has taken
	 * place.
	 * 
	 * @param pattern the new pattern for the appointment
	 */
	public void setPattern(RecurrencePattern pattern) {
		parent.setPattern(pattern);
	}
	
	/**
	 * Returns the description of the appointment template object.
	 * 
	 * @return the description of the appointment template object.
	 */
	public String getDescription() {
		return parent.getDescription();
	}

	/**
	 * Sets the description of the appointment, and
	 * notifies observers if a change has taken
	 * place.
	 * 
	 * @param description the new description for the appointment
	 * @throws NullPointerException if description is null
	 */
	public void setDescription(String description){
		parent.setDescription(description);
	}

	/**
	 * Returns the title of the appointment template object.
	 * 
	 * @return the title of the appointment template object.
	 */
	public String getTitle() {
		return parent.getTitle();
	}

	/**
	 * Sets the title of the appointment, and
	 * notifies observers if a change has taken
	 * place.
	 * 
	 * @param title the new title of the appointment.
	 * @throws NullPointerException if title is null
	 */
	public void setTitle(String title){
		parent.setTitle(title);
	}

	/**
	 * Returns the DateRange of the appointment object.
	 * 
	 * @return the DateRange of the appointment object.
	 */
	public DateRange getDateRange(){
		return new DateRange(startDate, getEndDate());
	}

	/**
	 * Sets the title of the appointment, and
	 * notifies observers if a change has taken
	 * place.
	 * 
	 * @param dr the new DateRange of the appointment.
	 * @throws NullPointerException if dr is null
	 */
	public void setDateRange(DateRange dr){
		if(dr == null)
			throw new NullPointerException("dr");
		long duration = dr.getDurationInMS();
		parent.setDuration(duration);
		setStartDate(dr.getStartDate());
	}

	/**
	 * Sets all the fields of the appointment, and
	 * notifies observers if a change has taken
	 * place.
	 * 
	 * @param apptTmple the new appointment fields
	 */
	public void setFields(RefAppointment appt) {
		getTemplate().setFields(appt.getTemplate());
		setStartDate(appt.getStartDate());
		
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
		setTemplate((AppointmentTemplate)istream.readObject());
		internalSetStartDate((Date)istream.readObject());
	}
	
	/**
	 * Saves the object to an object output stream
	 * 
	 * @param ostream the object stream to be writing to
	 * @throws IOException an I/O exception of some sort has occurred
	 */
	private void writeObject(ObjectOutputStream ostream) throws IOException {
		ostream.writeObject(getTemplate());
		ostream.writeObject(getStartDate());
	}

}
