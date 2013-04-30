
import com.std.controller.CalendarController;
import com.std.model.CalendarModel;
import com.std.view.CalendarView;

import javax.swing.*;
import java.io.IOException;

/**
 * Load the GUI to run the Calendar application
 *
 * @author xxx
 */
public class Driver{

	/**
	 * The main class for instantiating our application.
	 *
	 * @param args
	 *
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException{
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(ClassNotFoundException e){
		}
		catch(UnsupportedLookAndFeelException e){
		}
		catch(InstantiationException e){
		}
		catch(IllegalAccessException e){
		}

		CalendarModel model = new CalendarModel();
		CalendarView view = new CalendarView();
		view.setExtendedState(view.getExtendedState() | JFrame.MAXIMIZED_BOTH);

		new CalendarController(model, view);
		view.setVisible(true);
	}
}
