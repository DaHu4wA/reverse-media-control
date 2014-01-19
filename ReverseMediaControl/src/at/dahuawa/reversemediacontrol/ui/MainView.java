package at.dahuawa.reversemediacontrol.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Stefan Huber
 */
public class MainView extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private static final ImageIcon LEFT_ICON = new ImageIcon(MainView.class.getResource("/images/icons/back.png"));
	private static final ImageIcon RIGHT_ICON = new ImageIcon(MainView.class.getResource("/images/icons/fwd.png"));
	private static final ImageIcon PLAY_ICON = new ImageIcon(MainView.class.getResource("/images/icons/play.png"));
	
	private static final ImageIcon SOUND_LOWER_ICON = new ImageIcon(MainView.class.getResource("/images/icons/soundlower.png"));
	private static final ImageIcon SOUND_HIGHER_ICON = new ImageIcon(MainView.class.getResource("/images/icons/soundhigher.png"));
	private static final ImageIcon POWER_ICON = new ImageIcon(MainView.class.getResource("/images/icons/power.png"));
	private static final ImageIcon LIGHT_ICON = new ImageIcon(MainView.class.getResource("/images/icons/light_small.png"));
	
	private JButton playPauseButton;
	private JButton nextButton;
	private JButton prevButton;

	private JButton powerButton;
	private JButton volDownButton;
	private JButton volUpButton;

	private JButton infoButton;
	private JLabel infoLabel;

	public MainView() {
		init();
	}

	private void init() {
		setLayout(new BorderLayout());

		playPauseButton = new JButton(PLAY_ICON);
		nextButton = new JButton(RIGHT_ICON);
		prevButton = new JButton(LEFT_ICON);

		powerButton = new JButton(POWER_ICON);
		volDownButton = new JButton(SOUND_LOWER_ICON);
		volUpButton = new JButton(SOUND_HIGHER_ICON);

		infoButton = new JButton(LIGHT_ICON);
		infoLabel = new JLabel("Welcome!");

		JPanel mediaButtonPanel = new JPanel(new FlowLayout());

		mediaButtonPanel.add(prevButton);
		mediaButtonPanel.add(playPauseButton);
		mediaButtonPanel.add(nextButton);

		JPanel volumeButtonPanel = new JPanel(new FlowLayout());

		volumeButtonPanel.add(volDownButton);
		volumeButtonPanel.add(powerButton);
		volumeButtonPanel.add(volUpButton);

		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		JPanel info = new JPanel(new BorderLayout());

		p.add(mediaButtonPanel);
		p.add(volumeButtonPanel);

		info.add(infoLabel, BorderLayout.WEST);
		info.add(infoButton, BorderLayout.EAST);

		add(p, BorderLayout.CENTER);
		add(info, BorderLayout.SOUTH);
	}

	public JButton getInfoButton() {
		return infoButton;
	}

	public JButton getPowerButton() {
		return powerButton;
	}

	public JButton getVolDownButton() {
		return volDownButton;
	}

	public JButton getVolUpButton() {
		return volUpButton;
	}

	public JLabel getInfoLabel() {
		return infoLabel;
	}

	public JButton getPlayPauseButton() {
		return playPauseButton;
	}

	public JButton getNextButton() {
		return nextButton;
	}

	public JButton getPrevButton() {
		return prevButton;
	}

}
