package com.std.controller.commands;

import com.std.controller.CalendarController;
import com.std.controller.dialog.AppointmentDialog;
import com.std.model.CalendarModelUtility;
import com.std.model.appointment.RefAppointment;

/**
 * Created with IntelliJ IDEA.
 * User: Kocsen
 * Date: 5/3/13
 * Time: 6:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class NewAppointmentCommand implements Command {

    public NewAppointmentCommand() {
    }

    public void execute(CalendarController cc) {
        RefAppointment ref =
                CalendarModelUtility.getNewAppointment(cc.getModel());

        if (AppointmentDialog.changeAppointment(cc.getView(), ref)) {
            CalendarModelUtility.addUsingPattern(cc.getModel(), ref);
        }
    }
}
