/**
 * AppointmentReadView.java
 */
package com.std.view.block;

import com.std.model.appointment.AppointmentTemplate;
import com.std.model.appointment.RefAppointment;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Class that is used to write an appointment to a text area.
 */
public class AppointmentBlock extends JTextArea{

	private final boolean limitDisplay;

	private static final SimpleDateFormat FORMAT =
			new SimpleDateFormat("h:mm aa");

	// the appointment that will be written to the text area.
	private RefAppointment thisAppointment;

	/**
	 * Take an appointment and passes it to updateAppointment
	 *
	 * @param a
	 */
	public AppointmentBlock(RefAppointment a, boolean limit){
		super();
		limitDisplay = limit;
		updateAppointment(a);
	}

	/**
	 * Sets up a JTextArea to for an Appointment objects information to be written
	 * to it.
	 *
	 * @param a
	 */
	void updateAppointment(RefAppointment a){
		thisAppointment = a;
		if(!limitDisplay){
			setText("Title: " + thisAppointment.getTitle() + "\n" +
							"Description: " + thisAppointment.getDescription()
							+ "\n" +
							"Time: "
							+ FORMAT.format(thisAppointment.getStartDate()) +
							" to "
							+ FORMAT.format(thisAppointment.getEndDate()));
		}
		else{//limit the display (used for the monthlyDayBlocks)
			setText(FORMAT.format(thisAppointment.getStartDate())
							+ " - " + thisAppointment.getTitle());
		}
		setEditable(false);
		this.setOpaque(false);
		setBorder(new EtchedBorder());
		this.setLineWrap(true);
		this.setWrapStyleWord(true);
	}


	/**
	 * Returns the appointment on this object
	 *
	 * @return
	 */
	public RefAppointment getAppointment(){
		return thisAppointment;
	}

	public static void main(String[] args){
		JFrame frame = new JFrame();
		AppointmentTemplate template = new AppointmentTemplate(
				"My Temp", "This is an appointment", "Here",
				1000 * 60 * 60 * 2);

		RefAppointment ref = new RefAppointment(
				Calendar.getInstance().getTime(), template);

		AppointmentBlock b = new AppointmentBlock(ref, false);
		frame.add(b, BorderLayout.CENTER);
		frame.setVisible(true);

	}

}
