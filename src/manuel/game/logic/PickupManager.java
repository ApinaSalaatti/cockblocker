package manuel.game.logic;

import manuel.game.actors.pickups.Antibiotics;
import manuel.game.actors.pickups.Pickup;
import manuel.game.actors.pickups.WeaponPickup;
import manuel.game.actors.weapons.DoubleBlaster;
import manuel.game.actors.weapons.HormoneWave;
import org.lwjgl.opengl.Display;

/**
 *
 * @author Merioksan Mikko
 */
public class PickupManager {
    public static final int PICKUP_ANTIBIOTICS = 1;
    public static final int PICKUP_WEAPON_DOUBLEBLASTER = 2;
    public static final int PICKUP_WEAPON_HORMONEWAVE = 3;
    
    private GameLogic logic;
    
    private long sinceLastPickup, betweenPickups;
    
    public PickupManager(GameLogic l) {
        logic = l;
    }
    
    public void start() {
        sinceLastPickup = 0;
        betweenPickups = 2000;
    }
    
    public void update(long deltaMs) {
        sinceLastPickup += deltaMs;
        
        if(sinceLastPickup >= betweenPickups) {
            int r = logic.getGame().getRNG().nextInt(3) + 1;
            int x = logic.getGame().getRNG().nextInt(Display.getWidth() - 60) + 30;
            int y = logic.getGame().getRNG().nextInt(Display.getHeight() - 60) + 30;
            Pickup p = getPickup(r);
            p.setLocation(x, y);
            
            
            sinceLastPickup = 0;
            logic.addActor(p);
        }
    }
    
    public Pickup getPickup(int p) {
        int a = 0;
        switch(p) {
            case PICKUP_ANTIBIOTICS:
                return new Antibiotics();
            case PICKUP_WEAPON_DOUBLEBLASTER:
                a = logic.getGame().getRNG().nextInt(50) + 50;
                DoubleBlaster db = new DoubleBlaster();
                db.setAmmo(a);
                return new WeaponPickup(db);
            case PICKUP_WEAPON_HORMONEWAVE:
                a = logic.getGame().getRNG().nextInt(30) + 20;
                HormoneWave hw = new HormoneWave();
                hw.setAmmo(a);
                return new WeaponPickup(hw);
            default:
                return null;
        }
    }
}
