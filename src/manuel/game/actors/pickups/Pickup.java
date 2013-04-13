package manuel.game.actors.pickups;

import manuel.engine.graphics.ISprite;
import manuel.engine.renderer.Renderer;
import manuel.game.actors.Actor;

/**
 *
 * @author Merioksan Mikko
 */
public abstract class Pickup extends Actor {
    private ISprite sprite;
    
    private long lifetime, lived;
    
    public Pickup() {
        this(10, 10, null);
    }
    public Pickup(ISprite s) {
        this(10, 10, s);
    }
    public Pickup(int w, int h, ISprite s) {
        this(w, h, s, 2000);
    }
    public Pickup(int w, int h, ISprite s, long life) {
        super(w, h);
        sprite = s;
        
        lifetime = life;
        lived = 0;
        
        super.setType(Actor.TYPE_PICKUP);
    }
    
    public abstract void actOn(Actor a);
    
    @Override
    public void update(long deltaMs) {
        lived += deltaMs;
        
        if(sprite != null) {
            sprite.update(deltaMs);
        }
        
        if(lived >= lifetime) {
            game.removeActor(this);
        }
    }
    @Override
    public void render() {
        if(sprite != null) {
            Renderer.get().draw(sprite.getTexture(), x, y, sprite.getWidth(), sprite.getHeight(), sprite.getU(), sprite.getV(), sprite.getU2(), sprite.getV2());
        }
    }
}
