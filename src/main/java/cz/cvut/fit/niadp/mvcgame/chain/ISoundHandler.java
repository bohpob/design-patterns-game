package cz.cvut.fit.niadp.mvcgame.chain;

public interface ISoundHandler {

    void handle(SoundEvent event);

    void setNext(ISoundHandler next);
}
