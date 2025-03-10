package cz.cvut.fit.niadp.mvcgame.model.gameObjects;

import cz.cvut.fit.niadp.mvcgame.model.Position;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public abstract class LifetimeLimitedGameObject extends GameObject {

    private final LocalDateTime bornAt;

    protected LifetimeLimitedGameObject(Position initPosition) {
        position = initPosition;
        bornAt = LocalDateTime.now();
    }

    public long getAge() {
        return ChronoUnit.MILLIS.between(bornAt, LocalDateTime.now());
    }
}
