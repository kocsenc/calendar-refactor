package com.std.controller.commands;

import com.std.controller.CalendarController;
import com.std.controller.dialog.AppointmentDialog;
import com.std.model.CalendarModelUtility;
import com.std.model.appointment.AppointmentTemplate;
import com.std.model.appointment.RefAppointment;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: Kocsen
 * Date: 5/3/13
 * Time: 6:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class EditAppointmentCommand implements Command {

    public EditAppointmentCommand() {
    }

    public void execute(CalendarController cc) {
        RefAppointment ref = cc.getModel().getCurrentAppointment();
        if (ref != null) {
            if (ref.getPattern() != null) {
                AppointmentTemplate apptTmpl =
                        (AppointmentTemplate) ref.getTemplate().clone();
                apptTmpl.setPattern(null);
                RefAppointment appt =
                        new RefAppointment(ref.getStartDate(), apptTmpl);

                if (AppointmentDialog.changeAppointment(cc.getView(), appt)) {
                    cc.getModel().removeAppointment(ref);
                    CalendarModelUtility.add(cc.getModel(), appt);
                }
            } else {
                JOptionPane.showMessageDialog(cc.getView(), "the selected appointment does not recur", "", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(cc.getView(), "no appointment is selected", "", JOptionPane.ERROR_MESSAGE);
        }
    }
}

