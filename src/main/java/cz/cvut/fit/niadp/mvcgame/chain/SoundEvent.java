package cz.cvut.fit.niadp.mvcgame.chain;

public record SoundEvent(SoundType type) {

    public enum SoundType {
        WALL_HIT,
        ENEMY_HIT
    }
}
