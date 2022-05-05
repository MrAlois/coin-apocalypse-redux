package cz.asenk.vsb.coinapocalypse.game;

import lombok.extern.slf4j.Slf4j;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

@Slf4j
public class Sound {
	public static final Sound hurt = loadSound("src/main/resources/snd/hitHurt.wav");
	public static final Sound jump = loadSound("src/main/resources/snd/jump.wav");
	public static final Sound coin = loadSound("src/main/resources/snd/pickupCoin.wav");
	public static final Sound click = loadSound("src/main/resources/snd/click.wav");

	private Clip clip;

	public static Sound loadSound(String fileName) {
		Sound sound = new Sound();
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File(fileName).getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(ais);
			sound.clip = clip;

			log.debug("Sound loaded! " + fileName);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return sound;
	}

	public void play() {
		try {
			if (clip != null) {
				new Thread(() -> {
					synchronized (clip) {
						clip.stop();
						clip.setFramePosition(0);
						clip.start();
					}
				}).start();
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
}
