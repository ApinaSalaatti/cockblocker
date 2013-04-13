package manuel.game.actions;

import manuel.engine.actions.Action;
import manuel.game.logic.EnemyManager;

/**
 *
 * @author Merioksan Mikko
 */
public class NewEnemyTypeAction extends Action {
    private EnemyManager manager;
    private int type;
    
    private boolean done;
    
    public NewEnemyTypeAction(EnemyManager em, int t) {
        manager = em;
        type = t;
        
        done = false;
    }
    
    @Override
    public void update(long deltaMs) {
        if(!done) {
            manager.addEnemyType(type);
            done = true;
        }
    }
    
    @Override
    public boolean finished() {
        return done;
    }
}
