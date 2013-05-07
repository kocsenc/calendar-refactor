package com.std.controller.commands;

import com.std.controller.CalendarController;
import com.std.model.CalendarModelUtility;

/**
 * Created with IntelliJ IDEA.
 * User: Kocsen
 * Date: 5/3/13
 * Time: 6:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class NextButtonCommand implements Command {

    public NextButtonCommand() {
    }

    @Override
    public void execute(CalendarController cc) {
        switch (cc.getView().getTabbedState()) {
            case MONTHLY:
                CalendarModelUtility.nextMonth(cc.getModel());
                break;
            case WEEKLY:
                CalendarModelUtility.nextWeek(cc.getModel());
                break;
            case DAILY:
                CalendarModelUtility.nextDay(cc.getModel());
                break;
        }

    }
}
