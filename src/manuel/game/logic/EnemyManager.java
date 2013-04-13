package manuel.game.logic;

import java.util.ArrayList;
import manuel.engine.actions.*;
import manuel.game.actions.*;
import manuel.game.actors.Enemy;
import manuel.game.actors.enemies.*;

/**
 *
 * @author Merioksan Mikko
 */
public class EnemyManager {
    private static final int ENEMY_COCK = 1;
    private static final int ENEMY_AIDS_COCK = 2;
    private static final int ENEMY_GONORRHEA_COCK = 3;
    private static final int ENEMY_CHLAMYDIA_COCK = 4;
    
    private GameLogic logic;
    
    private ArrayList<Integer> enemyPool;
    private ActionSequence sequence;
    
    public EnemyManager(GameLogic l) {
        logic = l;
        enemyPool = new ArrayList<Integer>();
        sequence = null;
    }
    
    public void start() {
        enemyPool.clear();
        sequence = new ActionSequence(
            new NewMessageAction("Incoming!", "Here they come, prepare yourself.", logic.getMessaging()),
            new NewEnemyTypeAction(this, ENEMY_COCK),
            new SpawnRandomEnemiesAction(this, 3000, 2000),
            new NewMessageAction("Disease!", "Oh god, those new cocks have CHLAMYDIA! Take them out fast!!", logic.getMessaging()),
            new NewEnemyTypeAction(this, ENEMY_CHLAMYDIA_COCK),
            new SpawnRandomEnemiesAction(this, 3000, 1500),
            new NewMessageAction("BLEEDING COCKS!", "There are some cocks infected with gonorrhea approaching!", logic.getMessaging()),
            new NewEnemyTypeAction(this, ENEMY_GONORRHEA_COCK),
            new SpawnRandomEnemiesAction(this, 3000, 1000),
            new NewMessageAction("Death is coming!", "Things could not get any worse! AIDS is spreading all around us!", logic.getMessaging()),
            new NewEnemyTypeAction(this, ENEMY_AIDS_COCK),
            new SpawnRandomEnemiesAction(this, 1000000, 200)
        );
    }
    
    public GameLogic getLogic() {
        return logic;
    }
    
    public void addEnemyType(int t) {
        enemyPool.add(t);
    }
    public ArrayList<Integer> getEnemyPool() {
        return enemyPool;
    }
    public Enemy newEnemy(int type) {
        switch(type) {
            case ENEMY_COCK:
                return new CockEnemy();
            case ENEMY_CHLAMYDIA_COCK:
                return new ChlamydiaCockEnemy();
            case ENEMY_GONORRHEA_COCK:
                return new GonorrheaCockEnemy();
            case ENEMY_AIDS_COCK:
                return new AIDSCockEnemy();
            default:
                return null;
        }
    }
    
    public void update(long deltaMs) {
        if(sequence != null) {
            sequence.update(deltaMs);
        }
    }
}
