package manuel.game.actors.weapons;

import manuel.engine.graphics.Animation;
import manuel.engine.graphics.FrameData;
import manuel.engine.utils.resourceManager.ResourceManager;

/**
 *
 * @author Merioksan Mikko
 */
public class HormoneShot extends Projectile {
    private float xRate, yRate;
    
    public HormoneShot(float xR, float yR) {
        super(18, 18, 8, 400,
            new Animation(
                ResourceManager.get().getTextureManager().getTexture("assets/graphics/actors/hormoneShot.png"),
                100,
                new FrameData(0, 128, 64, 64),
                new FrameData(64, 128, 64, 64),
                new FrameData(128, 128, 64, 64),
                new FrameData(192, 128, 64, 64)
            )
        );
        
        xRate = xR;
        yRate = yR;
    }
    
    @Override
    public void move(long deltaMs) {
        y += (speed * deltaMs * yRate) / 1000;
        x += (speed * deltaMs * xRate) / 1000;
    }
}
