package manuel.game.actors.pickups;

import manuel.engine.graphics.FrameData;
import manuel.engine.graphics.ISprite;
import manuel.engine.graphics.Sprite;
import manuel.engine.utils.resourceManager.ResourceManager;
import manuel.game.actors.Actor;
import manuel.game.actors.ships.Ship;
import manuel.game.actors.weapons.Weapon;

/**
 *
 * @author Merioksan Mikko
 */
public class WeaponPickup extends Pickup {
    private Weapon weapon;
    
    public WeaponPickup(Weapon w, ISprite s) {
        super(40, 40, s);
        weapon = w;
    }
    public WeaponPickup(Weapon w) {
        this(w, new Sprite(ResourceManager.get().getTextureManager().getTexture("assets/graphics/actors/pickups.png"), new FrameData(0, 0, 64, 64)));
    }
    
    @Override
    public void actOn(Actor a) {
        Ship s = (Ship)a;
        
        s.addWeapon(weapon);
    }
}
