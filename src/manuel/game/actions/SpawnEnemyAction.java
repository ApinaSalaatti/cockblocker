package manuel.game.actions;

import manuel.engine.actions.Action;
import manuel.game.actors.Enemy;
import manuel.game.logic.GameLogic;

/**
 *
 * @author Merioksan Mikko
 */
public class SpawnEnemyAction extends Action {
    private Enemy enemy;
    private GameLogic logic;
    private boolean done;
    
    public SpawnEnemyAction(Enemy e, GameLogic l, float x, float y) {
        enemy = e;
        logic = l;
        enemy.setLocation(x, y);
        
        done = false;
    }
    
    @Override
    public void update(long deltaMs) {
        if(!done) {
            logic.addActor(enemy);
            done = true;
        }
    }
    
    @Override
    public boolean finished() {
        return done;
    }
}
