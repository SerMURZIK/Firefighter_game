package classes.other;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

public class Audio {
    private final Clip clip;
    private final String soundSource;

    public Audio(String soundSource) {
        try {
            this.soundSource = soundSource;
            clip = AudioSystem.getClip();
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    private void open() {
        try {
            clip.open(AudioSystem.getAudioInputStream(new File(soundSource)));
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        }
    }

    public void play() {
        open();
        clip.start();
    }

    public void stop() {
        clip.stop();
        clip.close();
    }
}
