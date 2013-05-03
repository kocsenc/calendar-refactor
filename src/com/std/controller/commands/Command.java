package com.std.controller.commands;

import com.std.controller.CalendarController;

/**
 * Created with IntelliJ IDEA.
 * User: Kocsen
 * Date: 5/2/13
 * Time: 3:36 PM
 * The command interface for all the commands related for the ssystem
 */
public interface Command {


    /**
     *
     */
    void execute(CalendarController cc);
}
