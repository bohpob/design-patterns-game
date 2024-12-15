package cz.cvut.fit.niadp.mvcgame.chain;

public class WallHitSoundHandler extends BaseSoundHandler {

    @Override
    public void handle(SoundEvent event) {
        if (event.type() == SoundEvent.SoundType.WALL_HIT) {
            playSound("/audio/wall.wav");
        } else {
            super.handle(event);
        }
    }
}
