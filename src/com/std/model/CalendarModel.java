package com.std.model;

import com.std.model.appointment.AppointmentTemplate;
import com.std.model.appointment.RefAppointment;
import com.std.model.export.ExportStrategy;
import com.std.model.export.ExportStrategyFactory;
import com.sun.jmx.remote.internal.ArrayQueue;

import java.io.*;
import java.util.*;

/**
 * CalendarModel is a basic representation of the data of the calendar.  This
 * class provides all the necessary functions to manipulate the model, but
 * offers very little in the way of convenience functions.  Additional utility
 * classes could be made to interact with this through a simpler or more
 * sanitized interface, or to provide services on this data not implemented here
 * because of non-necessity.
 * <p/>
 * CalendarModel calMdl; AppointmentTemplate apptTmpl;
 * <p/>
 * To add a RefAppointment template to the model: calMdl.
 * getAppointmentTemplateSet().add(apptTmpl);
 * <p/>
 * To remove a RefAppointment template from the model (this will also remove all
 * the associated RefAppointment instantiations): calMdl.
 * getAppointmentTemplateSet().remove(apptTmpl);
 * <p/>
 * To add an RefAppointment instantiation to the model (this will throw an
 * exception if the associated RefAppointment template is not already in the
 * model): if(calMdl.getAppointmentTemplateSet().contains(appt.getTemplate()))
 * calMdl.getAppointmentSet().add(appt);
 * <p/>
 * To remove an RefAppointment instantiation from the model:
 * calMdl.getAppointment().remove(appt);
 * <p/>
 * This class extends Observable and will pass the following argument to its
 * updates: AppointmentTemplate: when an RefAppointment template has been added
 * or removed from the set, or has been otherwise changed RefAppointment: when
 * an RefAppointment instantiation has been added or removed from the set, or
 * has been otherwise changed Object: when the calendar model has been altered.
 * The Object argument will always be null, but may indicated these changes:
 * <p/>
 * current date has changed current RefAppointment has changed model has been
 * saved - URI possibly changed model has been loaded - URI possibly changed
 */
public class CalendarModel extends Observable{
	private Date curDate; // selected date; should never be null
	private RefAppointment curAppt; // selected appt; can be null-none selected
	private File curFile; // current file URI; can be null - unsaved file
	private final HashSet<AppointmentTemplate> apptTmplSet;

	/**
	 * Set of RefAppointments.  should contain no RefAppointment whose template
	 * is not in apptTmplSet
	 */
	private final HashSet<RefAppointment> apptSet;

	private boolean diffFile;
	//true iff the file denoted by curURI is different than the data on record

	private final AppointmentTemplate defaultApptTmpl;

	private final ExportStrategyFactory exportStrategyFactory;
	//the default parameters for new appointment templates

	/**
	 * Creates a new Calendar model: current date: moment of object creation
	 * current appointment: null current URI: null appointment template set:
	 * empty appointment reference set: empty.
	 */
	public CalendarModel(){
		super();
		curDate = new Date();
		curAppt = null;
		curFile = null;
        apptTmplSet = new HashSet<AppointmentTemplate>();
        apptSet = new HashSet<RefAppointment>();
		diffFile = false;
		defaultApptTmpl = getNewDefaults();
		defaultApptTmpl.addObserver(new ElementObserver());
		exportStrategyFactory = new ExportStrategyFactory();
	}

	/**
	 * ElementObserver is an Observer that watches an object and forwards their
	 * updates to the CalendarModel observers.
	 *
	 * @author xxx
	 */
	private class ElementObserver implements Observer{

		/**
		 * This method is called whenever the observed object is changed. An
		 * application calls an <tt>Observable</tt> object's
		 * <code>notifyObservers</code> method to have all the object's
		 * observers notified of the change.
		 *
		 * @param o   the observable object.
		 * @param arg an argument passed to <code>notifyObservers</code> method.
		 *
		 * @see <a href="http://java.sun.com/javase/6/docs/api/java/util/
		 *      Observer.html#update(java.util.Observable,%20java.lang.Object)">
		 *      Observer.update(Observable, Object)</a>
		 */
		public void update(Observable o, Object arg){
			diffFile = true;
			setChanged();
			notifyObservers(o);
		}
	}

	/**
	 * removes all RefAppointments from the set of all RefAppointments whose
	 * template is the passed AppointmentTemplate.
	 *
	 * @param apptTmpl AppointmentTemplate of whose references to remove
	 */
	public void removeReferences(AppointmentTemplate apptTmpl){
		Iterator<RefAppointment> iter = apptSet.iterator();
		while(iter.hasNext()){
			RefAppointment appt = iter.next();
			if(appt.getTemplate() == apptTmpl){
				iter.remove();
			}
		}
	}



	/**
	 * @return the currently selected date
	 */
	public Date getCurrentDate(){
		return curDate;
	}

	/**
	 * sets the current date, checks it for validity, and marks this
	 * CalendarModel as changed if a change has been made
	 *
	 * @param d new current date
	 *
	 * @throws NullPointerException if the passed date is null
	 */
	private void internalSetCurrentDate(Date d){
		if(d == null)
			throw new NullPointerException("d");

		if(!d.equals(curDate))
			this.setChanged();
		curDate = d;
	}

	/**
	 * Sets current date, checks it for validity, marks this CalendarModel as
	 * changed if a change has been made, and notifies observers of the change.
	 *
	 * @param d new current date
	 */
	public void setCurrentDate(Date d){
		internalSetCurrentDate(d);
		this.notifyObservers();
	}

	/**
	 * @return the currently selected appointment
	 */
	public RefAppointment getCurrentAppointment(){
		return curAppt;
	}

	/**
	 * Sets the current appointment, checks it for validity, and marks this
	 * CalendarModel as changed if a change has been made.
	 *
	 * @param appt new current appointment
	 *
	 * @throws IllegalArgumentException the passed appointment is not in the
	 *                                  RefAppointment set
	 */
	private void internalSetCurrentAppointment(RefAppointment appt){
		if(appt != null && !apptSet.contains(appt))
			throw new IllegalArgumentException(
					"appointment does not exist or has not been added");

		if(appt == null ? curAppt != null : !appt.equals(curAppt))
			this.setChanged();

		curAppt = appt;
		if(curAppt != null)
			internalSetCurrentDate(curAppt.getStartDate());
	}

	/**
	 * Sets the current appointment, checks it for validity, marks this
	 * CalendarModel as changed if a change has been made, and notifies
	 * observers of the change.
	 *
	 * @param appt new current appointment
	 */
	public void setCurrentAppointment(RefAppointment appt){
		internalSetCurrentAppointment(appt);
		this.notifyObservers();
	}

	/**
	 * @return the currently viewed file URI
	 */
	public File getFile(){
		return curFile;
	}

	/**
	 * @return true iff file denoted by curURI is different than data on record
	 */
	public boolean isDifferentFromFile(){
		return diffFile;
	}

	/**
	 * @return a new AppointmentTemplate containing the default defaults
	 */
	private AppointmentTemplate getNewDefaults(){
		return new AppointmentTemplate("New Appointment", "", "n/a", 0);
	}

	/**
	 * @return a AppointmentTemplate containing the current defaults
	 */
	public AppointmentTemplate getCurrentDefaults(){
		return defaultApptTmpl;
	}

	/**
	 * saves the data model to a file specified by the passed URI parameter, and
	 * notifies any CalendarModel observers
	 *
	 * @throws IOException          if an I/O error occurs while writing stream
	 *                              header
	 * @throws NullPointerException if the passed URI is null
	 */
	public void save(File file, String id) throws IOException{
		if(file == null)
			throw new NullPointerException("file");
		if(id == null)
			throw new NullPointerException("id");

		// Build a complete list of all objects that need to be saved.
		LinkedList<Serializable> objectsToWrite = new LinkedList<Serializable>();
		objectsToWrite.add(defaultApptTmpl);
		objectsToWrite.add(apptTmplSet.size());
		for(AppointmentTemplate apptTmpl : apptTmplSet)
			objectsToWrite.add(apptTmpl);
		objectsToWrite.add(apptSet.size());
		for(RefAppointment appt : apptSet)
			objectsToWrite.add(appt);

		// Actually export the objects
		ExportStrategy exportStrategy = exportStrategyFactory.getExportStrategy(
				id);
		if(exportStrategy != null)
			exportStrategy.export(objectsToWrite, file);

		curFile = file;
		diffFile = false;
		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * loads the data model from a file specified by the passed URI parameter, and
	 * notifies any CalendarModel observers
	 *
	 * @param file name of file, null if a new file should be loaded
	 *
	 * @throws IOException if an I/O error occurs while writing stream header
	 */
	public void load(File file) throws IOException, ClassNotFoundException{
		Set<AppointmentTemplate> apptTmplSet =
				new HashSet<AppointmentTemplate>();
		Set<RefAppointment> apptSet = new HashSet<RefAppointment>();
		AppointmentTemplate defaultApptTmpl = getNewDefaults();
		if(file != null){
			ObjectInputStream in =
					new ObjectInputStream(new FileInputStream(file));
			defaultApptTmpl = (AppointmentTemplate) in.readObject();
			int count = in.readInt();
			while(count-- > 0)
				apptTmplSet.add((AppointmentTemplate) in.readObject());
			count = in.readInt();
			while(count-- > 0)
				apptSet.add((RefAppointment) in.readObject());
		}
		this.defaultApptTmpl.setFields(defaultApptTmpl);
		this.apptSet.clear();
		this.apptTmplSet.clear();
		this.apptTmplSet.addAll(apptTmplSet);
		this.apptSet.addAll(apptSet);
		curFile = file;
		diffFile = false;
		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * @return the set of Appointment references
	 */
	public Set<RefAppointment> getAppointmentSet(){
		return apptSet;
	}

    /**
     * Removes a RefAppointment from the appointment set.
     * @param a The RefAppointment to remove.
     * @return True if the removal was successful.
     */
    public boolean removeAppointment(RefAppointment a){
        if(a == curAppt){
            internalSetCurrentAppointment(null);
        }
        boolean result = apptSet.remove(a);
        if (result){
            diffFile = true;
            setChanged();
            notifyObservers();
        }
        return result;
    }

    /**
     * Adds a RefAppointment to the appointment set.
     * @param a The RefAppointment to add.
     * @return True if addition was successful.
     */
    public boolean addAppointment(RefAppointment a){
        if(!apptTmplSet.contains(a.getTemplate())){
            throw new IllegalArgumentException(
                    "template does not exist or has not been added");
        }
        boolean result = apptSet.add(a);
        if (result){
            diffFile = true;
            setChanged();
            notifyObservers();
        }
        return result;
    }

    /**
     * Adds a collection to the appointment set.
     * @param as The collection of RefAppointments to add.
     */
    public void addAllAppointments(Collection<RefAppointment> as){
        for (RefAppointment a : as){
            if(!apptTmplSet.contains(a.getTemplate())){
                throw new IllegalArgumentException(
                        "template does not exist or has not been added");
            }
            apptSet.add(a);
        }
        setChanged();
        notifyObservers();
    }

    /**
     * Removes an AppointmentTemplate from the appointment template set.
     * It then removes all RefAppointments associated with this template.
     * @param a The template to remove.
     */
    public void removeAppointmentTemplate(AppointmentTemplate a){
        apptTmplSet.remove(a);
        removeReferences(a);
        diffFile = true;
        setChanged();
        notifyObservers();
    }

    /**
     * Adds a template to the appointment template set.
     * @param a The template to add.
     */
    public void addAppointmentTemplate(AppointmentTemplate a){
        apptTmplSet.add(a);
        diffFile = true;
        setChanged();
        notifyObservers();
    }
}
