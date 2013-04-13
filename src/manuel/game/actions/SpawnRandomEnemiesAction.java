package manuel.game.actions;

import manuel.engine.actions.Action;
import manuel.game.actors.Enemy;
import manuel.game.logic.EnemyManager;
import org.lwjgl.opengl.Display;

/**
 *
 * @author Merioksan Mikko
 */
public class SpawnRandomEnemiesAction extends Action {
    private EnemyManager manager;
    private long length, elapsed;
    private long betweenSpawns, sinceLastSpawn;
    
    public SpawnRandomEnemiesAction(EnemyManager em, long l, long between) {
        manager = em;
        length = l;
        elapsed = 0;
        
        betweenSpawns = between;
        sinceLastSpawn = 0;
    }
    
    @Override
    public void update(long deltaMs) {
        elapsed += deltaMs;
        sinceLastSpawn += deltaMs;
        
        if(elapsed < length) {
            if(sinceLastSpawn >= betweenSpawns) {
                int rnd = manager.getLogic().getGame().getRNG().nextInt(manager.getEnemyPool().size());
                Enemy e = manager.newEnemy(manager.getEnemyPool().get(rnd));

                int rndX = manager.getLogic().getGame().getRNG().nextInt(Display.getWidth() - 40) + 20;
                e.setLocation(rndX, -100);
                manager.getLogic().addActor(e);
                sinceLastSpawn = 0;
            }
        }
    }
    
    @Override
    public boolean finished() {
        return elapsed >= length;
    }
}
