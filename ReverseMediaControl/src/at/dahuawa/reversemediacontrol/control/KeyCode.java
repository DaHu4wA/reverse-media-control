package at.dahuawa.reversemediacontrol.control;

/**
 * @author Stefan Huber
 */
public enum KeyCode {

	MUTE(91), //
	VOLUME_UP(24), //
	VOLUME_DOWN(25), //

	POWER(26), //

	MEDIA_PLAY_PAUSE(85), //
	MEDIA_STOP(86), //
	MEDIA_NEXT(87), //
	MEDIA_PREVIOUS(88), //
	MEDIA_REWIND(89), //
	MEDIA_FAST_FORWARD(90); //

	private final int code;

	public int getCode() {
		return code;
	}

	KeyCode(int code) {
		this.code = code;
	}

}
