package com.std.controller.commands;

import com.std.controller.CalendarController;
import com.std.view.block.DayBlock;

import java.awt.event.ActionEvent;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Kocsen
 * Date: 5/3/13
 * Time: 6:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class DateSelectionCommand implements Command {
    ActionEvent e;

    public DateSelectionCommand(ActionEvent e) {
        this.e = e;
    }


    @Override
    public void execute(CalendarController cc) {
        //get the day block that this listener was listening too
        DayBlock db = (DayBlock) e.getSource();
        Date newSelectedDate = db.getDate();
        cc.getModel().setCurrentDate(newSelectedDate);
    }
}
