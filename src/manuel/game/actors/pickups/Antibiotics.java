package manuel.game.actors.pickups;

import manuel.engine.graphics.FrameData;
import manuel.engine.graphics.Sprite;
import manuel.engine.utils.resourceManager.ResourceManager;
import manuel.game.actors.Actor;

/**
 *
 * @author Merioksan Mikko
 */
public class Antibiotics extends Pickup {

    public Antibiotics() {
        super(40, 40, new Sprite(ResourceManager.get().getTextureManager().getTexture("assets/graphics/actors/pickups.png"), new FrameData(64, 0, 64, 64)));
    }
    @Override
    public void actOn(Actor a) {
        game.getPlayer().getStats().applyAntibiotics();
    }
}
