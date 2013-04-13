package manuel.game.actors.ships;

import manuel.engine.graphics.Animation;
import manuel.engine.graphics.FrameData;
import manuel.engine.utils.resourceManager.ResourceManager;
import manuel.game.actors.Actor;
import manuel.game.actors.weapons.HormoneWave;
import manuel.game.actors.weapons.RubberBlaster;
import manuel.game.logic.GameLogic;

/**
 *
 * @author Merioksan Mikko
 */
public class CondomRocket extends Ship {

    public CondomRocket() {
        this(null);
    }
    public CondomRocket(GameLogic g) {
        super(
            20,
            20,
            g,
            new Animation(
                ResourceManager.get().getTextureManager().getTexture("assets/graphics/actors/condomRocket.png"),
                100,
                new FrameData(0, 0, 64, 64)
            ),
            new Animation(
                ResourceManager.get().getTextureManager().getTexture("assets/graphics/actors/condomRocket.png"),
                100,
                new FrameData(0, 64, 64, 64)
            ),
            new Animation(
                ResourceManager.get().getTextureManager().getTexture("assets/graphics/actors/condomRocket.png"),
                100,
                new FrameData(0, 128, 64, 64)
            ),
            500,
            100
        );
        
        super.setSide(Actor.SIDE_FRIENDLY);
        
        addWeapon(new RubberBlaster());
    }
}
