package manuel.game.actors.weapons;

import manuel.engine.graphics.Animation;
import manuel.engine.graphics.FrameData;
import manuel.engine.utils.resourceManager.ResourceManager;
import manuel.game.actors.Actor;

/**
 *
 * @author Merioksan Mikko
 */
public class RubberBlaster extends Weapon {
    
    public RubberBlaster() {
        super(3, 6,
            new Animation(
                ResourceManager.get().getTextureManager().getTexture("assets/graphics/actors/rubberBlaster.png"),
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
        return "Rubber Blaster";
    }
    
    @Override
    public void fire() {
        RubberBlast rb = new RubberBlast();
        rb.setX(mountedOn.getX());
        rb.setY(mountedOn.getY() - 30);
        
        rb.setSide(mountedOn.getSide());
        
        mountedOn.getGame().addActor(rb);
    }
}
