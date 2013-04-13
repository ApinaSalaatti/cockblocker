package manuel.game.actors.ships;

import manuel.engine.graphics.Animation;
import manuel.engine.graphics.FrameData;
import manuel.engine.utils.resourceManager.ResourceManager;
import manuel.game.actors.Actor;
import manuel.game.logic.GameLogic;

/**
 *
 * @author Merioksan Mikko
 */
public class CockRocket extends Ship {

    public CockRocket() {
        this(null);
    }
    public CockRocket(GameLogic g) {
        super(
            20,
            20,
            g,
            new Animation(
                ResourceManager.get().getTextureManager().getTexture("assets/graphics/actors/cockRocket.png"),
                100,
                new FrameData(0, 0, 64, 64)
            ),
            new Animation(
                ResourceManager.get().getTextureManager().getTexture("assets/graphics/actors/cockRocket.png"),
                100,
                new FrameData(0, 64, 64, 64)
            ),
            new Animation(
                ResourceManager.get().getTextureManager().getTexture("assets/graphics/actors/condomRocket.png"),
                100,
                new FrameData(0, 128, 64, 64)
            ),
            200,
            300
        );
        
        super.setSide(Actor.SIDE_FRIENDLY);
    }
}
