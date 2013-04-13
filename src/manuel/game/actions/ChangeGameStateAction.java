package manuel.game.actions;

import manuel.engine.actions.Action;
import manuel.game.logic.GameLogic;

/**
 *
 * @author Merioksan Mikko
 */
public class ChangeGameStateAction extends Action {
    private GameLogic logic;
    private GameLogic.GameState state;
    
    private boolean done;
    
    public ChangeGameStateAction(GameLogic l, GameLogic.GameState s) {
        logic = l;
        state = s;
        
        done = false;
    }
    
    @Override
    public void update(long deltaMs) {
        if(!done) {
            logic.changeState(state);
            done = true;
        }
    }
    
    @Override
    public boolean finished() {
        return done;
    }
}
