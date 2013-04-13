package manuel.engine;

import java.util.ArrayList;
import manuel.game.actors.Actor;

/**
 * An interface that should be implemented with the main logic class of the game. This way it can properly be registered with the Views of the game.
 * @author ApinaSalaatti
 */
public interface IGameLogic {
    public void update(long deltaMs);
}
