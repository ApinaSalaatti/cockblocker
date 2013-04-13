package manuel.game.actors.ai;

import manuel.game.actors.Enemy;

/**
 *
 * @author Merioksan Mikko
 */
public class MoveDownAI implements BaseAI {
    private Enemy enemy;
    
    @Override
    public void init(Enemy e) {
        enemy = e;
    }
    @Override
    public void update(long deltaMs) {
        if(!enemy.isMoving()) {
            enemy.accelerateDown(1);
        }
    }
}
