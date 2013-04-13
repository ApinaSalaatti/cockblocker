package manuel.game.actors.weapons;

import manuel.engine.graphics.ISprite;
import manuel.engine.renderer.Renderer;
import manuel.game.actors.Actor;

/**
 * A base class for all the stuff that different weapons fire, i.e. bullets, missiles, etc.
 * 
 * @author Merioksan Mikko
 */
public abstract class Projectile extends Actor {
    protected int damage;
    protected float speed;
    
    private ISprite sprite;
    
    public Projectile(int w, int h, int d, int s, ISprite sp) {
        super(w, h);
        damage = d;
        speed = s;
        
        sprite = sp;
        
        super.setType(Actor.TYPE_PROJECTILE);
    }
    
    public int getDamage() {
        return damage;
    }
    
    @Override
    public void update(long deltaMs) {
        move(deltaMs);
        sprite.update(deltaMs);
    }
    
    /**
     * Basic movement of a projectile is straight up (or down if speed is negative). Override this if need be!
     * 
     * @param deltaMs elapsed time since last update
     */
    public void move(long deltaMs) {
        y -= (speed * deltaMs) / 1000;
    }
    
    @Override
    public void render() {
        Renderer.get().draw(sprite.getTexture(), x, y, sprite.getWidth(), sprite.getHeight(), sprite.getU(), sprite.getV(), sprite.getU2(), sprite.getV2());
    }
}
