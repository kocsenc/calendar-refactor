
import com.std.controller.CalendarController;
import com.std.model.CalendarModel;
import com.std.view.CalendarView;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Load the GUI to run the Calendar application
 *
 * @author xxx
 */
public class Driver {

    /**
     * The main class for instantiating our application.
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ignored) {
        } catch (UnsupportedLookAndFeelException ignored) {
        } catch (InstantiationException ignored) {
        } catch (IllegalAccessException ignored) {
        }


        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                CalendarModel model = new CalendarModel();
                CalendarView view = null;
                try {
                    view = new CalendarView();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                view.setExtendedState(view.getExtendedState() | JFrame.MAXIMIZED_BOTH);
                new CalendarController(model, view);
                view.setVisible(true);

            }
        });


    }
}
