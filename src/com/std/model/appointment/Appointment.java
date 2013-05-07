package com.std.model.appointment;

import com.std.model.pattern.RecurrencePattern;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: keegan
 * Date: 5/3/13
 * Time: 2:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class Appointment extends java.util.Observable implements Serializable {

    private static final long serialVersionUID = 8219757873492578630L;

    private String title; // title of the appointment
    private String description; // description of the appointment
    private String location; // where the appointment takes place
    private RecurrencePattern recPattern; // pattern used for appointment
    private long startTime;  //Start time on whatever day the appointment occurs
    private long endTime;  //End time on whatever day the appointment occurs

    /**
     * Creates a new AppointmentTemplate.
     *
     * @param title       Title of the appointment.
     * @param description Description of the appointment.
     * @param location    Location of the appointment.
     * @param startTime    The start time of an appointment represented by milliseconds.
     * @param endTime       THe end time of an appointment represented by milliseconds.
     */
    public Appointment(
            String title, String description, String location, long startTime, long endTime){
        super();
        setTitle(title);
        setDescription(description);
        setLocation(location);
        setStartTime(startTime);
        setEndTime(endTime);
        recPattern = null;

        this.notifyObservers();
    }

    /**
     * @return the title of the appointment template object.
     */
    public String getTitle(){
        return title;
    }

    /**
     * @return the description of the appointment template object.
     */
    public String getDescription(){
        return description;
    }

    /**
     * @return the location of the appointment template Object.
     */
    public String getLocation(){
        return location;
    }

    /**
     * @return the duration of the appointment template object in milliseconds.
     */
    public long getDuration(){
        return endTime - startTime;
    }

    /**
     * @return the pattern object of this appointment template.
     */
    public RecurrencePattern getPattern(){
        return recPattern;
    }

    public long getStartTime(){
        return startTime;
    }

    public long getEndTime(){
        return endTime;
    }

    /**
     * Sets the title of the appointment, validates the input, and marks the
     * Observable as changed if a change has taken place.
     *
     * @param title the new title of the appointment.
     *
     * @throws NullPointerException if title is null
     */
    public void setTitle(String title){
        if(title == null){
            throw new NullPointerException("title");
        }
        if(!title.equals(this.title)){
            this.setChanged();
        }
        this.title = title;
        this.notifyObservers();
    }

    /**
     * Sets the description of the appointment, validates the input, and marks the
     * Observable as changed if a change has taken place.
     *
     * @param description the new description for the appointment
     *
     * @throws NullPointerException if description is null
     */
    public void setDescription(String description){
        if(description == null){
            throw new NullPointerException("description");
        }
        if(!description.equals(this.description)){
            this.setChanged();
        }
        this.description = description;
        this.notifyObservers();
    }

    /**
     * Sets the location of the appointment, validates the input, and marks the
     * Observable as changed if a change has taken place.
     *
     * @param location the new location for the appointment
     *
     * @throws NullPointerException if loc is null
     */
    public void setLocation(String location){
        if(location == null){
            throw new NullPointerException("location");
        }
        if(!location.equals(this.location)){
            this.setChanged();
        }
        this.location = location;
        this.notifyObservers();
    }

    /**
     * Sets the pattern of the appointment, validates the input, and marks the
     * Observable as changed if a change has taken place.
     *
     * @param pattern the new pattern for the appointment
     */
    public void setPattern(RecurrencePattern pattern){
        if(pattern == null ? this.recPattern != null
                : !pattern.equals(this.recPattern)){
            this.setChanged();
        }
        this.recPattern = pattern;
    }


    public void setStartTime(long time){
        this.startTime = time;
    }

    public void setEndTime(long time){
        this.endTime = time;
    }
}
