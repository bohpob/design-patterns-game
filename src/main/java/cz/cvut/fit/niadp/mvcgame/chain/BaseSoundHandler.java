package cz.cvut.fit.niadp.mvcgame.chain;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.Objects;

public abstract class BaseSoundHandler implements ISoundHandler {

    protected ISoundHandler next;

    protected void playSound(String filePath) {
        try {
            String uri = Objects.requireNonNull(getClass().getResource(filePath)).toURI().toString();
            Media sound = new Media(uri);
            MediaPlayer mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.setOnEndOfMedia(mediaPlayer::dispose);
            mediaPlayer.play();
        } catch (Exception e) {
            System.err.println("Error playing sound: " + e.getMessage());
        }
    }

    @Override
    public void handle(SoundEvent event) {
        if (next != null) {
            next.handle(event);
        }
    }

    @Override
    public void setNext(ISoundHandler next) {
        this.next = next;
    }
}
