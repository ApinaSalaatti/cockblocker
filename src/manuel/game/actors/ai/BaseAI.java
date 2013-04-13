package manuel.game.actors.ai;

import manuel.game.actors.Enemy;

/**
 *
 * @author Merioksan Mikko
 */
public interface BaseAI {
    public void update(long deltaMs);
    public void init(Enemy e);
}
