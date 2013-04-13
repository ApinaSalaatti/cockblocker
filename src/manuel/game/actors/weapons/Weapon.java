package manuel.game.actors.weapons;

import manuel.engine.graphics.ISprite;
import manuel.game.actors.ships.Ship;

/**
 *
 * @author Merioksan Mikko
 */
public abstract class Weapon {
    protected int damage;
    protected long rateOfFire, sinceLastShot;
    /**
     * How many times this weapon can shoot before it runs out of ammo and is discarded.
     */
    protected int ammo;
    public static final int UNLIMITED_AMMO = -100;
    
    protected ISprite sprite;
    
    protected boolean triggered;
    
    /**
     * The ship this weapon is mounted on.
     */
    protected Ship mountedOn;
    
    /**
     * Minimum requirements of any weapon: the damage it does, the rate with which it fires and the sprite to draw it.
     * 
     * @param d weapon's damage
     * @param rate weapon's rate of fire (shots / second)
     */
    public Weapon(int d, float rate, ISprite s) {
        damage = d;
        rateOfFire = (long)(1000 / rate);
        sprite = s;
        
        sinceLastShot = rateOfFire - 1;
        triggered = false;
        
        ammo = UNLIMITED_AMMO;
    }
    
    public void mountOnShip(Ship s) {
        mountedOn = s;
    }
    public void setAmmo(int a) {
        ammo = a;
    }
    public int getAmmo() {
        return ammo;
    }
    
    public void update(long deltaMs) {
        sinceLastShot += deltaMs;
        if(triggered && sinceLastShot >= rateOfFire) {
            if(ammo != UNLIMITED_AMMO) {
                ammo--;
            }
            fire();
            sinceLastShot = 0;
        }
        
        if(sprite != null && triggered) {
            sprite.update(deltaMs);
        }
    }
    
    public ISprite getSprite() {
        return sprite;
    }
    
    public void pullTrigger() {
        triggered = true;
    }
    public void releaseTrigger() {
        triggered = false;
        if(sprite != null) {
            sprite.reset();
        }
    }
    
    protected abstract void fire();
    public abstract String getName();
}
