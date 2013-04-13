package manuel.game.actors.weapons;

import manuel.game.actors.Actor;

/**
 *
 * @author Merioksan Mikko
 */
public class HormoneWave extends Weapon {
    public HormoneWave() {
        super(3, 6, null);
    }
    
    @Override
    public String getName() {
        return "Hormone Wave";
    }
    
    @Override
    public void fire() {
        mountedOn.getLogic().addActor(createShot(0.0f, 1.0f));
        mountedOn.getLogic().addActor(createShot(0.0f, -1.0f));
        mountedOn.getLogic().addActor(createShot(1.0f, 0.0f));
        mountedOn.getLogic().addActor(createShot(-1.0f, 0.0f));
        
        mountedOn.getLogic().addActor(createShot(0.714f, 0.714f));
        mountedOn.getLogic().addActor(createShot(0.714f, -0.714f));
        mountedOn.getLogic().addActor(createShot(-0.714f, 0.714f));
        mountedOn.getLogic().addActor(createShot(-0.714f, -0.714f));
    }
    
    public HormoneShot createShot(float xR, float yR) {
        HormoneShot hs = new HormoneShot(xR, yR);
        hs.setX(mountedOn.getX());
        hs.setY(mountedOn.getY());
        
        hs.setSide(mountedOn.getSide());
        
        return hs;
    }
}
