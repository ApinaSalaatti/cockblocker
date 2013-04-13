package manuel.game.actors;

import manuel.engine.graphics.ISprite;
import manuel.engine.renderer.Renderer;
import manuel.game.logic.GameLogic;
import manuel.game.actors.ships.CondomRocket;
import manuel.game.actors.ships.Ship;

/**
 * A convenient class for sending commands that affect the player and storing the player's stats.
 * 
 * @author Merioksan Mikko
 */
public class Player {
    /**
     * The ship this player is currently using.
     */
    private Ship currentShip;
    
    private GameLogic game;
    
    private PlayerStats stats;
    
    public Player(GameLogic g) {
        game = g;
        
        currentShip = null;
        
        stats = new PlayerStats(this);
    }
    
    public void update(long deltaMs) {
        stats.update(deltaMs);
    }
    
    public void setShip(Ship s) {
        currentShip = s;
    }
    public Ship getShip() {
        return currentShip;
    }
    
    public void accelerateLeft(float rate) {
        currentShip.accelerateLeft(rate);
    }
    public void accelerateRight(float rate) {
        currentShip.accelerateRight(rate);
    }
    public void accelerateUp(float rate) {
        currentShip.accelerateUp(rate);
    }
    public void accelerateDown(float rate) {
        currentShip.accelerateDown(rate);
    }
    
    public void firing(boolean b) {
        if(b) {
            currentShip.startFiring();
        }
        else {
            currentShip.stopFiring();
        }
    }
    
    public PlayerStats getStats() {
        return stats;
    }
    public void enemyThrough(Enemy e) {
        stats.enemyThrough(e);
    }
    public void enemyKilled(Enemy e) {
        stats.enemyKilled(e);
    }
    
    public GameLogic getGame() {
        return game;
    }
}
