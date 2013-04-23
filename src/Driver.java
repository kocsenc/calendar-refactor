
import java.io.IOException;

import com.std.controller.CalendarController;
import com.std.model.CalendarModel;
import com.std.view.CalendarView;

/**
 * Load the GUI to run the Calendar application
 * 
 * @author xxx
 *
 */
public class Driver {
	public static void main(String[] args) throws IOException {
		
		CalendarModel model = new CalendarModel();
		CalendarView view = new CalendarView();
		
		CalendarController controler = new CalendarController(model, view);
		view.setVisible(true);	
	}
}
