package manuel.game.actors;

import manuel.game.logic.GameLogic;

/**
 *
 * @author Merioksan Mikko
 */
public interface IActor {
    public int getID();
    public abstract void update(long deltaMs);
    public abstract void render();
    public void setGame(GameLogic g);
}
