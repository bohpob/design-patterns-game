package cz.cvut.fit.niadp.mvcgame.chain;

public class EnemyHitSoundHandler extends BaseSoundHandler {

    @Override
    public void handle(SoundEvent event) {
        if (event.type() == SoundEvent.SoundType.ENEMY_HIT) {
            playSound("/audio/enemy.wav");
        } else {
            super.handle(event);
        }
    }
}
