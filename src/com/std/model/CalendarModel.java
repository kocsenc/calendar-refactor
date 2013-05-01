package com.std.model;

import com.std.model.appointment.AppointmentTemplate;
import com.std.model.appointment.RefAppointment;
import com.std.util.ObservableSet;

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
 * To add a RefAppointment template to the model: calMdl.getAppointmentTemplateSet().add(apptTmpl);
 * <p/>
 * To remove a RefAppointment template from the model (this will also remove all
 * the associated RefAppointment instantiations): calMdl.getAppointmentTemplateSet().remove(apptTmpl);
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

	/**
	 * ApptObservableSet is an extension of ObservableSet<RefAppointment> that
	 * overrides add and addAll to make sure that any RefAppointments added have a
	 * template that is in the template set.
	 */
	private class ApptObservableSet extends ObservableSet<RefAppointment>{

		/**
		 * Adds the specified element to this set if it is not already present
		 * (optional operation).  More formally, adds the specified element e to this
		 * set if the set contains no element e2 such that (e==null; e2==null;
		 * e.equals(e2)) If this set already contains the element, the call leaves the
		 * set unchanged and returns false.  In combination with the restriction on
		 * constructors, this ensures that sets never contain duplicate elements.
		 * <p/>
		 * The stipulation above does not imply that sets must accept all elements;
		 * sets may refuse to add any particular element, including null, and throw an
		 * exception, as described in the specification for {@link Collection#add
		 * Collection.add}. Individual set implementations should clearly document any
		 * restrictions on the elements that they may contain.
		 *
		 * @return true if this set did not already contain specified element
		 *
		 * @throws UnsupportedOperationException if the add operation is not supported
		 *                                       by this set
		 * @throws ClassCastException            if the class of the specified element
		 *                                       prevents it from being added to this
		 *                                       set
		 * @throws NullPointerException          if the specified element is null and
		 *                                       this set does not permit null
		 *                                       elements
		 * @throws IllegalArgumentException      if some property of the specified
		 *                                       element prevents it from being added
		 *                                       to this set
		 */
		public boolean add(RefAppointment appt){
			if(!apptTmplSet.contains(appt.getTemplate())){
				throw new IllegalArgumentException(
						"template does not exist or has not been added");
			}
			return super.add(appt);
		}

		/**
		 * Adds all of the elements in the specified collection to this set if they're
		 * not already present (optional operation).  If the specified collection is
		 * also a set, the addAll operation effectively modifies this set so that its
		 * value is the union of the two sets.  The behavior of this operation is
		 * undefined if the specified collection is modified while the operation is in
		 * progress.
		 *
		 * @param c collection containing elements to be added to this set
		 *
		 * @return true if this set changed as a result of the call
		 *
		 * @throws UnsupportedOperationException if the addAll operation is not
		 *                                       supported by this set
		 * @throws ClassCastException            if the class of an element of the
		 *                                       specified collection prevents it from
		 *                                       being added to this set
		 * @throws NullPointerException          if the specified collection contains
		 *                                       one or more null elements and this
		 *                                       set does not permit null elements, or
		 *                                       if the specified collection is null
		 * @throws IllegalArgumentException      if some property of an element of the
		 *                                       specified collection prevents it from
		 *                                       being added to this set
		 */
		public boolean addAll(Collection<? extends RefAppointment> c){
			if(c == null){
				throw new NullPointerException("c");
			}
			for(RefAppointment appt : c){
				if(!apptTmplSet.contains(appt.getTemplate())){
					throw new IllegalArgumentException(
							"template does not exist or has not been added");
				}
			}
			return super.addAll(c);
		}

		/**
		 * Removes the specified element from this set if it is present (optional
		 * operation).  More formally, removes an element e such that (o==null;
		 * e==null; o.equals(e)) if this set contains such an element.  Returns
		 * <tt>true</tt> if this set contained the element (or equivalently, if this
		 * set changed as a result of the call).  (This set will not contain the
		 * element once the call returns.)
		 *
		 * @param o object to be removed from this set, if present
		 *
		 * @return true if this set contained the specified element
		 *
		 * @throws ClassCastException            if the type of the specified element
		 *                                       is incompatible with this set
		 *                                       (optional)
		 * @throws NullPointerException          if the specified element is null and
		 *                                       this set does not permit null
		 *                                       elements (optional)
		 * @throws UnsupportedOperationException if the remove operation is not
		 *                                       supported by this set
		 */
		public boolean remove(Object o){
			if(o == curAppt){
				internalSetCurrentAppointment(null);
			}
			return super.remove(o);
		}
	}

	/**
	 * ApptTmplSetObserver is an Observer that observes apptTmplSet for any removal
	 * updates, and removes any reference appointments to that template from
	 * apptSet.
	 */
	private class ApptTmplSetObserver implements Observer{

		/**
		 * This method is called whenever the observed object is changed. An
		 * application calls an Observable object's notifyObservers method to have all
		 * the object's observers notified of the change.
		 *
		 * @param o   the observable object.
		 * @param arg an argument passed to the <code>notifyObservers</code> method.
		 */
		public void update(Observable o, Object arg){
			ObservableSet<?> set = (ObservableSet<?>) o;
			AppointmentTemplate apptTmpl = (AppointmentTemplate) arg;
			if(!set.contains(apptTmpl)){
				removeReferences(apptTmpl);
			}
		}
	}

	/**
	 * SetObserver is an Observer that observes apptTmplSet and apptSet for any
	 * updates, marks the model as changed, and forwards those updates to the
	 * model's listeners.
	 */
	private class SetObserver implements Observer{

		/**
		 * This method is called whenever the observed object is changed. An
		 * application calls an Observable> object's notifyObservers method to have
		 * all the object's observers notified of the change.
		 *
		 * @param o   the observable object.
		 * @param arg an argument passed to the <code>notifyObservers</code> method.
		 */
		public void update(Observable o, Object arg){
			diffFile = true;
			that.setChanged();
			that.notifyObservers(arg);
		}
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
		 * application calls an <tt>Observable</tt> object's <code>notifyObservers</code>
		 * method to have all the object's observers notified of the change.
		 *
		 * @param o   the observable object.
		 * @param arg an argument passed to the <code>notifyObservers</code> method.
		 *
		 * @see <a href="http://java.sun.com/javase/6/docs/api/java/util/
		 *      Observer.html#update(java.util.Observable,%20java.lang.Object)">
		 *      Observer.update(Observable, Object)</a>
		 */
		public void update(Observable o, Object arg){
			diffFile = true;
			that.setChanged();
			that.notifyObservers(o);
		}
	}

	/**
	 * a pointer to this object, so that inner classes can reference the
	 * encapsulating CalendarModel
	 */
	private final CalendarModel that;

	private Date curDate;
			//the currently selected date.  should not ever be null

	private RefAppointment curAppt;
			//the currently select RefAppointment. can possibly be null,denoting no selection

	private File curFile;
			//the currently viewed file URI.  can possibly be null, denoting an unsaved file

	private final ObservableSet<AppointmentTemplate> apptTmplSet;
			//Set of AppointmentTemplates

	/**
	 * Set of RefAppointments.  should contain no RefAppointment whose template is
	 * not in apptTmplSet
	 */
	private final ObservableSet<RefAppointment> apptSet;

	private boolean diffFile;
			//true iff the file denoted by curURI is different than the data on record

	private final AppointmentTemplate defaultApptTmpl;
			//the default parameters for new appointment templates

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
	 * sets the current date, checks it for validity, and marks this CalendarModel
	 * as changed if a change has been made
	 *
	 * @param d new current date
	 *
	 * @throws NullPointerException if the passed date is null
	 */
	private void internalSetCurrentDate(Date d){
		if(d == null){
			throw new NullPointerException("d");
		}
		if(!d.equals(curDate)){
			this.setChanged();
		}
		curDate = d;
	}

	/**
	 * sets the current date, checks it for validity, marks this CalendarModel as
	 * changed if a change has been made, and notifies observers of the change
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
	 * sets the current appointment, checks it for validity, and marks this
	 * CalendarModel as changed if a change has been made
	 *
	 * @param appt new current appointment
	 *
	 * @throws IllegalArgumentException the passed appointment is not in the
	 *                                  RefAppointment set
	 */
	private void internalSetCurrentAppointment(RefAppointment appt){
		if(appt != null && !apptSet.contains(appt)){
			throw new IllegalArgumentException(
					"appointment does not exist or has not been added");
		}
		if(appt == null ? curAppt != null : !appt.equals(curAppt)){
			this.setChanged();
		}
		curAppt = appt;
		if(curAppt != null){
			internalSetCurrentDate(curAppt.getStartDate());
		}
	}

	/**
	 * sets the current appointment, checks it for validity, marks this
	 * CalendarModel as changed if a change has been made, and notifies observers
	 * of the change
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
	public void save(File file) throws IOException{
		if(file == null){
			throw new NullPointerException("file");
		}
		ObjectOutputStream out =
				new ObjectOutputStream(new FileOutputStream(file));
		out.writeObject(defaultApptTmpl);
		out.writeInt(apptTmplSet.size());
		for(AppointmentTemplate apptTmpl : apptTmplSet){
			out.writeObject(apptTmpl);
		}
		out.writeInt(apptSet.size());
		for(RefAppointment appt : apptSet){
			out.writeObject(appt);
		}
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
			while(count-- > 0){
				apptTmplSet.add((AppointmentTemplate) in.readObject());
			}
			count = in.readInt();
			while(count-- > 0){
				apptSet.add((RefAppointment) in.readObject());
			}
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
	 * @return the set of Appointment templates
	 */
	public Set<AppointmentTemplate> getAppointmentTemplateSet(){
		return apptTmplSet;
	}

	/**
	 * @return the set of Appointment references
	 */
	public Set<RefAppointment> getAppointmentSet(){
		return apptSet;
	}

	/**
	 * creates a new Calendar model: current date: moment of object creation
	 * current appointment: null current URI: null appointment template set: empty
	 * appointment reference set: empty
	 */
	public CalendarModel(){
		super();
		that = this;
		curDate = new Date();
		curAppt = null;
		curFile = null;
		apptTmplSet = new ObservableSet<AppointmentTemplate>();
		apptSet = new ApptObservableSet();
		diffFile = false;
		defaultApptTmpl = getNewDefaults();

		apptTmplSet.addObserver(new ApptTmplSetObserver());
		apptTmplSet.addObserver(new SetObserver());
		apptSet.addObserver(new SetObserver());
		defaultApptTmpl.addObserver(new ElementObserver());
	}

}
