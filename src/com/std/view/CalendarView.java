package com.std.view;


import com.std.controller.CalendarController;
import com.std.controller.listener.*;
import com.std.model.appointment.AppointmentUtility;
import com.std.model.appointment.RefAppointment;
import com.std.util.range.DayRange;
import com.std.util.range.MonthRange;
import com.std.util.range.WeekRange;
import com.std.view.panel.AppointmentPanel;
import com.std.view.panel.DailyPanel;
import com.std.view.panel.MonthlyPanel;
import com.std.view.panel.WeeklyPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

/**
 * This is the main view Frame for the Calendar App
 *
 * @author Kocsen
 */

public class CalendarView extends JFrame {

    private CalendarMenu calMenu;
    private JLabel displayDate;
    private JButton prevButton, nextButton;
    private CalendarController cc;
    private JTabbedPane tabs;
    private MonthlyPanel monthlyView;
    private WeeklyPanel weeklyView;
    private DailyPanel dailyView;
    private AppointmentPanel appointmentView;

    private final static SimpleDateFormat FORMAT =
            new SimpleDateFormat("MMMM, yyyy");

    public static enum TABBED_STATE {
        MONTHLY,
        WEEKLY,
        DAILY
    }

    /**
     * This is the constructor, it sets up the different views and buttons and
     * starts the JFrame
     */
    public CalendarView() {
        calMenu = new CalendarMenu();
        try {
            initComponents();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * This method initializes the GUI components
     */
    private void initComponents() throws IOException {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setJMenuBar(calMenu);
        Date date = new Date();
        monthlyView = new MonthlyPanel(date);
        weeklyView = new WeeklyPanel(date);
        dailyView = new DailyPanel(date);

        appointmentView = new AppointmentPanel();

        displayDate = new JLabel(FORMAT.format(date));
        displayDate.setHorizontalAlignment(SwingConstants.CENTER);
        displayDate.setFont(displayDate.getFont().deriveFont(20f));

        prevButton = new JButton("<");
        prevButton.setFocusable(false);
        //prevButton.setPreferredSize(new Dimension(100, 0));

        nextButton = new JButton(">");
        nextButton.setFocusable(false);
        //nextButton.setPreferredSize(new Dimension(100, 0));

        tabs = new JTabbedPane();

        tabs.add(monthlyView, "Month View");
        tabs.add(weeklyView, "Week View");
        tabs.add(dailyView, "Day View");


        JLabel logo = new JLabel(new ImageIcon(ImageIO.read(
                new File("img/logo.png"))));
        logo.setBorder(new EmptyBorder(4, 4, 4, 4));
        logo.setHorizontalAlignment(SwingConstants.RIGHT);

        JPanel top = new JPanel();
        top.setOpaque(false);
        top.setLayout(new BorderLayout());
        top.add(prevButton, BorderLayout.WEST);
        top.add(displayDate, BorderLayout.CENTER);
        top.add(nextButton, BorderLayout.EAST);

        JPanel centerPanel = new JPanel();
        centerPanel.setBorder(new EmptyBorder(0, 4, 0, 4));
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new BorderLayout());
        centerPanel.add(tabs, BorderLayout.CENTER);
        centerPanel.add(top, BorderLayout.NORTH);

        JPanel contentPane = new JPanel();
        contentPane.setOpaque(false);
        contentPane.setLayout(new BorderLayout());
        contentPane.add(logo, BorderLayout.NORTH);
        contentPane.add(centerPanel, BorderLayout.CENTER);
        contentPane.add(appointmentView, BorderLayout.EAST);

        setContentPane(contentPane);
        setTitle("Untitled Calendar - DCal");
        setBackground(Color.WHITE);

        this.setIconImage(ImageIO.read(new File("img/icon.png")));

        pack();

    }

    /**
     * This allows elements of the view and controller figure out what view is
     * currently selected
     *
     * @return returns a Tabbed state of the currently selected view
     */
    public TABBED_STATE getTabbedState() {
        TABBED_STATE ret = null;
        Component c = tabs.getSelectedComponent();
        if (c == monthlyView) {
            ret = TABBED_STATE.MONTHLY;
        } else if (c == weeklyView) {
            ret = TABBED_STATE.WEEKLY;
        } else if (c == dailyView) {
            ret = TABBED_STATE.DAILY;
        }
        return ret;
    }


    /**
     * This method updates the different views when an event is called from the
     * model it passes the subset of the total set of appointments by calling get
     * range, it also passes the selected appointment and the selected date to the
     * views
     *
     * @param refSet              the total set of reference appointments
     * @param selectedDate        the currently selected Date
     * @param selectedAppointment the currently selected Appointment
     * @param currentFile         the current file
     */
    public void update(Set<RefAppointment> refSet, Date selectedDate,
                       RefAppointment selectedAppointment, File currentFile) {
        displayDate.setText(FORMAT.format(selectedDate));

        if (refSet != null) {
            Set<RefAppointment> subSet;

            subSet = AppointmentUtility.getRange(
                    refSet, new MonthRange(selectedDate));
            monthlyView.update(subSet, selectedDate, selectedAppointment);

            subSet = AppointmentUtility.getRange(
                    refSet, new WeekRange(selectedDate));
            weeklyView.update(subSet, selectedDate, selectedAppointment);

            subSet = AppointmentUtility.getRange(
                    refSet, new DayRange(selectedDate));
            dailyView.update(subSet, selectedDate, selectedAppointment);
        } else {
            monthlyView.update(null, selectedDate, selectedAppointment);
            weeklyView.update(null, selectedDate, selectedAppointment);
            dailyView.update(null, selectedDate, selectedAppointment);
        }
        appointmentView.setAppointment(selectedAppointment);

        setTitle((currentFile == null ? "Untitled Calendar" :
                currentFile.getName()) + " - DCal");

        this.validate();
    }


    // ************** Setting the listeners  ************** //

    /**
     * This method instantiates all the necessary listeners, giving them
     * reference to CalendarController, and then adds them to CalendarView.
     */
    private void instantiateListeners() {

        // listens for when the view tries to be disposed
        DefaultWindowListener defW = new DefaultWindowListener(cc);
        this.addWindowListener(defW);


        // listens for when an appointment is selected
        AppointmentSelectionMouseListener apptSelL =
                new AppointmentSelectionMouseListener(cc);
        weeklyView.addAppointmentListeners(apptSelL);
        monthlyView.addAppointmentListener(apptSelL);
        dailyView.addAppointmentListener(apptSelL);

        // listens for when a date is selected
        DateSelectionActionListener dateSelL =
                new DateSelectionActionListener(cc);
        weeklyView.addDaySelectionActionListener(dateSelL);
        monthlyView.addDayListener(dateSelL);


        // listens for when the next button is pressed
        nextButton.addActionListener(new NextButtonActionListener(cc));

        // listens for when the previous button is pressed
        prevButton.addActionListener(new PrevButtonActionListener(cc));

        // listens for when the menu item "New Calendar" is selected
        calMenu.getNewCalendarMenuItem().addActionListener(new NewCalendarActionListener(cc));

        // listens for when the menu item "Open Calendar" is selected
        calMenu.getOpenCalendarMenuItem().addActionListener(new OpenCalendarActionListener(cc));

        // listens for when the menu item "Save Calendar" is selected
        calMenu.getSaveCalendarMenuItem().addActionListener(new SaveCalendarActionListener(cc));

        // listens for when the menu item "Save As Calendar" is selected
        calMenu.getSaveAsCalendarMenuItem().addActionListener(new SaveAsCalendarActionListener(cc));

        // listens for when the menu item "Exit" is selected
        calMenu.getExitApplicationMenuItem().addActionListener(new ExitApplicationActionListener(cc));

        // listens for when the menu item "New Appointment" is selected
        calMenu.getNewAppointmentMenuItem().addActionListener(new NewAppointmentActionListener(cc));

        // listens for when the menu item "Edit Appointment" is selected
        appointmentView.addEditSingleListener(new EditAppointmentActionListener(cc));

        // listens for when menu item "Edit Recurring Appointment" is selected
        EditRecurringAppointmentActionListener editRecApptL =
                new EditRecurringAppointmentActionListener(cc);
        calMenu.getEditAllAppointmentMenuItem().addActionListener(editRecApptL);
        appointmentView.addEditRecurringListener(editRecApptL);

        // listens for when the menu item "Remove Appointment" is selected
        RemoveAppointmentActionListener remApptL =
                new RemoveAppointmentActionListener(cc);
        calMenu.getRemoveAppointmentMenuItem().addActionListener(remApptL);
        appointmentView.addRemoveSingleListener(remApptL);

        // listens for when the menu item "Remove All Occurrences" is selected
        RemoveAllAppointmentActionListener remAllApptL =
                new RemoveAllAppointmentActionListener(cc);
        calMenu.getRemoveAllAppointmentMenuItem().addActionListener(remAllApptL);
        appointmentView.addRemoveRecurringListener(remAllApptL);

        // listens for when the menu item "Preferences" is selected
        calMenu.getPreferencesMenuItem().addActionListener(new PreferencesActionListener(cc));


        // listens for when the menu item "About" is selected
        calMenu.getAboutMenuItem().addActionListener(new AboutActionListener(cc));

    }


    /**
     * Used on startup to gve the view knowledge of the controller.
     *
     * @param c - The calendar Controller
     */
    public void setCalendarController(CalendarController c) {
        System.out.println("Setting new controller!");
        if (c == null) {
            System.out.println("Oh oh, the controller set is null");
        } else {
            this.cc = c;
            instantiateListeners();
        }
    }


}
