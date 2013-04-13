package manuel.game.actors;

import manuel.engine.graphics.Animation;
import manuel.game.actors.ai.BaseAI;
import manuel.game.actors.ships.Ship;
import manuel.game.logic.GameLogic;

/**
 *
 * @author Merioksan Mikko
 */
public abstract class Enemy extends Ship {
    private BaseAI AI;
    private int value;
    private String disease;
    
    public Enemy(int w, int h, GameLogic g, Animation idle, Animation move, Animation attack, float s, int he, int v) {
        super(w, h, g, idle, move, attack, s, he);
        value = v;
        disease = PlayerStats.DISEASE_HEALTHY;
    }
    
    public void setAI(BaseAI ai) {
        AI = ai;
        AI.init(this);
    }
    
    @Override
    public void update(long deltaMs) {
        super.update(deltaMs);
        if(AI != null) {
            AI.update(deltaMs);
        }
    }
    
    public void setDisease(String d) {
        disease = d;
    }
    public String getDisease() {
        return disease;
    }
    public int getScoreValue() {
        return value;
    }
    
    public BaseAI getAI() {
        return AI;
    }
}
