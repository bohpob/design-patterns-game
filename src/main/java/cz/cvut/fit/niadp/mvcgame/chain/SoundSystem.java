package cz.cvut.fit.niadp.mvcgame.chain;

public class SoundSystem {

    private final ISoundHandler soundHandler;

    public SoundSystem() {
        ISoundHandler wallHitHandler = new WallHitSoundHandler();
        ISoundHandler enemyHitHandler = new EnemyHitSoundHandler();

        wallHitHandler.setNext(enemyHitHandler);
        soundHandler = wallHitHandler;
    }

    public void process(SoundEvent event) {
        soundHandler.handle(event);
    }
}
