package at.dahuawa.reversemediacontrol;

import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

import at.dahuawa.reversemediacontrol.ui.MainController;

/**
 * @author Stefan Huber
 */
public class ReverseMediaControl {

	private static MainController MAIN_CONTROLLER;

	public static void main(String[] args) throws InvocationTargetException,
			InterruptedException {

		SwingUtilities.invokeAndWait(new Runnable() {
			@Override
			public void run() {
				MAIN_CONTROLLER = new MainController();
			}
		});
	}

	public static MainController getMainController() {
		return MAIN_CONTROLLER;
	}

}
