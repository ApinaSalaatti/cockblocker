package manuel.game.actors.weapons;

import manuel.engine.graphics.Animation;
import manuel.engine.graphics.FrameData;
import manuel.engine.utils.resourceManager.ResourceManager;
import manuel.game.actors.Actor;

/**
 *
 * @author Merioksan Mikko
 */
public class DoubleBlaster extends Weapon {

    public DoubleBlaster() {
        super(3, 6,
            new Animation(
                ResourceManager.get().getTextureManager().getTexture("assets/graphics/actors/doubleBlaster.png"),
                100,
                new FrameData(0, 64, 64, 64),
                new FrameData(64, 64, 64, 64),
                new FrameData(128, 64, 64, 64),
                new FrameData(192, 64, 64, 64)
            )
        );
    }
    
    @Override
    public String getName() {
        return "Double Blaster";
    }
    
    @Override
    public void fire() {
        RubberBlast rb1 = new RubberBlast();
        RubberBlast rb2 = new RubberBlast();
        rb1.setX(mountedOn.getX() - 20);
        rb1.setY(mountedOn.getY() - 30);
        
        rb1.setSide(mountedOn.getSide());
        rb1.setType(Actor.TYPE_PROJECTILE);
        
        rb2.setX(mountedOn.getX() + 20);
        rb2.setY(mountedOn.getY() - 30);
        
        rb2.setSide(mountedOn.getSide());
        rb2.setType(Actor.TYPE_PROJECTILE);
        
        mountedOn.getGame().addActor(rb1);
        mountedOn.getGame().addActor(rb2);
    }
}
