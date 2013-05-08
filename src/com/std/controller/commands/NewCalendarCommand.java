package com.std.controller.commands;

import com.std.controller.CalendarController;

/**
 * Created with IntelliJ IDEA.
 * User: Kocsen
 * Date: 5/3/13
 * Time: 6:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class NewCalendarCommand implements Command {

    /**
     *
     */
    public NewCalendarCommand() {
    }

    public void execute(CalendarController cc) {
        try {
            cc.getModel().load(null);
        } catch (Exception ex) {
            cc.handleException(ex);
        }
    }
}
