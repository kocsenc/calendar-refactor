package com.std.controller.listener;

import java.awt.Window;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import com.std.controller.CalendarController;

/**
 * This is the listener listening to any disposal events on the view.
 * 
 * @author xxx
 *
 */
public class DefaultWindowListener implements WindowListener {
	
	/**
	 * 
	 * This is an internal class that is a thread that
	 * ensures that the program is closed correctly
	 * 
	 * @author xxx
	 *
	 */
	
	private class CloseRunnable implements Runnable {
		private Window win;
		
		public void run() {
			win.dispose();
		}
		
		/**
		 * This is the constructor for the internal class
		 * 
		 * @param win the window to be closed
		 */
		
		public CloseRunnable(Window win) {
			this.win = win;
		}
	}

	/**
	 * a reference to the controller so that this listener
	 * can access both the model and the view.
	 */
	private CalendarController controller;

	/**
	 * creates a new DefaultWindowListener
	 * 
	 * @param CalendarControler cc is the reference to the controller 
	 */
	public DefaultWindowListener(CalendarController cc){
		controller = cc;
	}
	
	/**
	 * This occurs when the user has attempted to dispose of the view.
	 * If changes have been made to the model, the user will be prompted
	 * with dialog explaining that there are unsaved changed, and asking
	 * what to do about them: save, discard, or to cancel the dispose 
	 * operation.
	 * 
	 * @param e is the action event, it is not used
	 */
	public void windowClosing(WindowEvent evt) {
		controller.confirm(new CloseRunnable(evt.getWindow()));
	}

	/**
	 * Not used; added to satisfy the interface
	 */
	public void windowActivated(WindowEvent arg0) {}

	/**
	 * Not used; added to satisfy the interface
	 */
	public void windowClosed(WindowEvent arg0) {}

	/**
	 * Not used; added to satisfy the interface
	 */
	public void windowDeactivated(WindowEvent arg0) {}

	/**
	 * Not used; added to satisfy the interface
	 */
	public void windowDeiconified(WindowEvent arg0) {}

	/**
	 * Not used; added to satisfy the interface
	 */
	public void windowIconified(WindowEvent arg0) {}

	/**
	 * Not used; added to satisfy the interface
	 */
	public void windowOpened(WindowEvent arg0) {}
}
