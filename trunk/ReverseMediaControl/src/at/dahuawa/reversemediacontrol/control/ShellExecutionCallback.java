package at.dahuawa.reversemediacontrol.control;

/**
 * @author Stefan Huber
 */
public interface ShellExecutionCallback {

	public void onFinished(String message);

	public void onError(String message);

}
