package at.dahuawa.reversemediacontrol.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import at.dahuawa.reversemediacontrol.control.GlobalWindowsKeyListener;
import at.dahuawa.reversemediacontrol.control.KeyCode;
import at.dahuawa.reversemediacontrol.control.ShellExecutionCallback;
import at.dahuawa.reversemediacontrol.control.ShellExecutor;

/**
 * @author Stefan Huber
 */
public class MainController extends JFrame {
	private static final long serialVersionUID = 1L;
	public static final String ADB_PATH = "data\\"; // TODO Property
	
	private static final ImageIcon ICON = new ImageIcon(MainView.class.getResource("/images/icons/gen_reverse.png"));
	public static final String INFO = "Global Media Keys:\n\nSTRG+ALT+Space   Play/Pause\nSTRG+ALT+P           Prev\nSTRG+ALT+N          Next";

	private MainView view;
	private ShellExecutor executor;

	public MainController() {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}
		
		view = new MainView();

		executor = new ShellExecutor(new ShellExecutionCallback() {

			@Override
			public void onFinished(String message) {
				view.getInfoLabel().setForeground(Color.BLACK);
				view.getInfoLabel().setText(message);
			}

			@Override
			public void onError(String message) {
				view.getInfoLabel().setForeground(Color.RED);
				view.getInfoLabel().setText(message);
			}
		}, ADB_PATH);

		setTitle("Reverse MediaControl");
		setSize(350, 150);
		setContentPane(view);
		setListeners();
		setResizable(false);
		setIconImage(ICON.getImage());
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		// TODO config for that!
		GlobalWindowsKeyListener.addListeners(executor);

		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				try {
					GlobalWindowsKeyListener.removeListeners();
				} finally {
					System.exit(0);
				}
			}
		});

		setVisible(true);
	}

	private void setListeners() {

		view.getPlayPauseButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				executor.execute(KeyCode.MEDIA_PLAY_PAUSE);
			}
		});

		view.getPrevButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				executor.execute(KeyCode.MEDIA_PREVIOUS);
			}
		});

		view.getNextButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				executor.execute(KeyCode.MEDIA_NEXT);
			}
		});

		view.getPowerButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				executor.execute(KeyCode.POWER);
			}
		});

		view.getVolDownButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				executor.execute(KeyCode.VOLUME_DOWN);
			}
		});

		view.getVolUpButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				executor.execute(KeyCode.VOLUME_UP);
			}
		});

		view.getInfoButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(view, INFO);
			}
		});
	}
}
