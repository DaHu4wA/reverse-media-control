package at.dahuawa.reversemediacontrol.control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.SwingUtilities;

/**
 * @author Stefan Huber
 */
public class ShellExecutor {

	public static final String ADB_SHELL_INPUT_CMD = "adb shell input keyevent ";

	private final String adbPath;
	private final ShellExecutionCallback callback;

	public ShellExecutor(ShellExecutionCallback callback, String adbPath) {
		this.adbPath = adbPath;
		this.callback = callback;
	}

	public void execute(KeyCode keyCode) {
		execute(buildShellInputCmdCommand(keyCode));
	}

	public void execute(final String command) {
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				try {

					long startTime = System.currentTimeMillis();

					String logMsg = "";
					String errorMsg = "";

					Process process = Runtime.getRuntime().exec(command);

					BufferedReader logging = new BufferedReader(
							new InputStreamReader(process.getInputStream()));
					BufferedReader error = new BufferedReader(
							new InputStreamReader(process.getErrorStream()));

					String log = logging.readLine();
					while (log != null) {
						logMsg = logMsg + log;
						log = logging.readLine();
					}

					String err = error.readLine();
					while (err != null) {
						errorMsg = errorMsg + err;
						err = logging.readLine();
					}

					final String errorMsgf = errorMsg;
					if (!errorMsg.trim().isEmpty()) {
						SwingUtilities.invokeLater(new Runnable() {
							@Override
							public void run() {

								callback.onError(errorMsgf);
							}
						});
						return;
					}

					final long time = System.currentTimeMillis() - startTime;
					final String logMsgf = logMsg;
					SwingUtilities.invokeLater(new Runnable() {

						@Override
						public void run() {

							if (!errorMsgf.trim().isEmpty()) {
								callback.onFinished(logMsgf);
							} else {
								callback.onFinished("Command executed in "
										+ time + "ms");
							}
						}
					});

				} catch (IOException e) {
					callback.onError(e.getMessage());
				}
			}
		});
		t.start();
	}

	private String buildShellInputCmdCommand(KeyCode keycode) {
		return adbPath + ADB_SHELL_INPUT_CMD + keycode.getCode();
	}

}
