package com.std.controller.commands;

import com.std.controller.CalendarController;
import com.std.controller.dialog.AppointmentDialog;
import com.std.model.CalendarModelUtility;
import com.std.model.appointment.RefAppointment;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: Kocsen
 * Date: 5/3/13
 * Time: 6:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class EditRecurringAppointmentCommand implements Command {
    public EditRecurringAppointmentCommand() {
    }

    public void execute(CalendarController cc) {
        RefAppointment ref = cc.getModel().getCurrentAppointment();
        if (ref != null) {
            AppointmentDialog.changeAppointment(cc.getView(), ref);

            CalendarModelUtility.addUsingPattern(cc.getModel(), ref);
        } else {
            JOptionPane.showMessageDialog(cc.getView(), "no appointment is selected", "", JOptionPane.ERROR_MESSAGE);
        }
    }
}
