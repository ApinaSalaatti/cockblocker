package manuel.game.actors.weapons;

import manuel.engine.graphics.Animation;
import manuel.engine.graphics.FrameData;
import manuel.engine.renderer.Renderer;
import manuel.engine.utils.resourceManager.ResourceManager;

/**
 *
 * @author Merioksan Mikko
 */
public class RubberBlast extends Projectile {
    public RubberBlast() {
        super(5, 5, 8, 400,
            new Animation(
                ResourceManager.get().getTextureManager().getTexture("assets/graphics/actors/rubberBlast.png"),
                100,
                new FrameData(0, 128, 64, 64),
                new FrameData(64, 128, 64, 64),
                new FrameData(128, 128, 64, 64),
                new FrameData(192, 128, 64, 64)
            )
        );
    }
}
