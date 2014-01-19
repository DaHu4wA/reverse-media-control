package at.dahuawa.reversemediacontrol.control;

import com.melloware.jintellitype.HotkeyListener;
import com.melloware.jintellitype.IntellitypeListener;
import com.melloware.jintellitype.JIntellitype;
import com.melloware.jintellitype.JIntellitypeConstants;

/**
 * @author Stefan Huber
 */
public class GlobalWindowsKeyListener {

	public static final int SHIFT = 32;
	public static final int PLAY_PAUSE = 1;
	public static final int PREV = 2;
	public static final int NEXT = 3;

	public static void addListeners(final ShellExecutor executor) {

		// TODO other keys
		JIntellitype.getInstance().registerHotKey(PLAY_PAUSE,
				JIntellitypeConstants.MOD_ALT + JIntellitypeConstants.MOD_CONTROL, SHIFT);
		JIntellitype.getInstance().registerHotKey(PREV,
				JIntellitypeConstants.MOD_ALT + JIntellitypeConstants.MOD_CONTROL, 'P');
		JIntellitype.getInstance().registerHotKey(NEXT,
				JIntellitypeConstants.MOD_ALT + JIntellitypeConstants.MOD_CONTROL, 'N');

		JIntellitype.getInstance().addHotKeyListener(new HotkeyListener() {

			@Override
			public void onHotKey(int identifier) {

				switch (identifier) {
				case PLAY_PAUSE:
					System.out.println("Play/Pause");
					executor.execute(KeyCode.MEDIA_PLAY_PAUSE);
					break;
				case PREV:
					System.out.println("Prev");
					executor.execute(KeyCode.MEDIA_PREVIOUS);
					break;
				case NEXT:
					System.out.println("Next");
					executor.execute(KeyCode.MEDIA_NEXT);
					break;

				// TODO other buttons

				default:
					throw new UnsupportedOperationException("identifier "
							+ identifier + " not configured!");
				}

			}
		});

		JIntellitype.getInstance().addIntellitypeListener(
				new IntellitypeListener() {

					@Override
					public void onIntellitype(int command) {
						// TODO !!!

					}
				});

	}

	public static void removeListeners() {
		JIntellitype.getInstance().cleanUp();
	}

}
