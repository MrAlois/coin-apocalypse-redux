package CoinApocalypse;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	public static Sound hurt = loadSound("src/main/resources/snd/hitHurt.wav");
	public static Sound jump = loadSound("src/main/resources/snd/jump.wav");
	public static Sound coin = loadSound("src/main/resources/snd/pickupCoin.wav");
	public static Sound click = loadSound("src/main/resources/snd/click.wav");
	
	public static Sound loadSound(String fileName) {
		Sound sound = new Sound();
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File(fileName).getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(ais);
			sound.clip = clip;
		} catch (Exception e) {
			System.out.println(e);
		}
		return sound;
	}

	private Clip clip;

	public void play() {
		try {
			if (clip != null) {
				new Thread() {
					public void run() {
						synchronized (clip) {
							clip.stop();
							clip.setFramePosition(0);
							clip.start();
						}
					}
				}.start();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
