package manuel.game.actors.ships;

import java.util.ArrayList;
import java.util.Iterator;
import manuel.engine.graphics.Animation;
import manuel.engine.graphics.ISprite;
import manuel.engine.renderer.Renderer;
import manuel.game.actors.Actor;
import manuel.game.actors.weapons.Weapon;
import manuel.game.logic.GameLogic;

/**
 *
 * @author Merioksan Mikko
 */
public abstract class Ship extends Actor {
    protected Animation idleAnim;
    protected Animation moveAnim;
    protected Animation attackAnim;
    protected Animation currentAnim;
    
    protected ArrayList<Weapon> weapons;
    protected boolean firing;
    
    protected float speed;
    protected int health;
    
    /**
     * Booleans that are set if we are to move to a certain direction.
     */
    protected boolean mLeft, mRight, mUp, mDown;
    
    public Ship(int w, int h, GameLogic g, Animation idle, Animation move, Animation attack, float s, int he) {
        super(w, h, g);
        
        idleAnim = idle;
        moveAnim = move;
        attackAnim = attack;
        
        currentAnim = idleAnim;
        
        speed = s;
        health = he;
        
        weapons = new ArrayList<Weapon>();
        firing = false;
        
        mLeft = mRight = mUp = mDown = false;
        
        super.setType(Actor.TYPE_SHIP);
    }
    /**
     * Constructor for ships that have not yet entered the game.
     */
    public Ship(int w, int h, Animation idle, Animation move, Animation attack, float s, int he) {
        this(w, h, null, idle, move, attack, s, he);
    }
    
    public void addWeapon(Weapon w) {
        if(w != null) {
            boolean haveSame = false;
            for(Weapon w2 : weapons) {
                if(w2.getName().equals(w.getName())) {
                    w2.setAmmo(w2.getAmmo() + w.getAmmo());
                    haveSame = true;
                }
            }
            
            if(!haveSame) {
                weapons.add(w);
                w.mountOnShip(this);
                if(firing) {
                    w.pullTrigger();
                }
            }
        }
    }
    public void removeWeapon(Weapon w) {
        weapons.remove(w);
    }
    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }
    
    public void startFiring() {
        for(Weapon w : weapons) {
            w.pullTrigger();
        }
        firing = true;
    }
    public void stopFiring() {
        for(Weapon w : weapons) {
            w.releaseTrigger();
        }
        firing = false;
    }
    
    @Override
    public void update(long deltaMs) {
        move(deltaMs);
        
        Iterator<Weapon> it = weapons.iterator();
        while(it.hasNext()) {
            Weapon w = it.next();
            w.update(deltaMs);
            if(w.getAmmo() == 0) {
                it.remove();
            }
        }
        
        currentAnim.update(deltaMs);
    }
    
    public ISprite getSprite() {
        return currentAnim;
    }
    
    public int getHealth() {
        return health;
    }
    public void setHealth(int h) {
        health = h;
    }
    
    public float getSpeed() {
        return speed;
    }
    public boolean isMoving() {
        return mLeft || mRight || mUp || mDown;
    }
    
    public void accelerateLeft(float rate) {
        if(rate > 0) {
            mLeft = true;
        }
        else {
            mLeft = false;
        }
    }
    public void accelerateRight(float rate) {
        if(rate > 0) {
            mRight = true;
        }
        else {
            mRight = false;
        }
    }
    public void accelerateUp(float rate) {
        if(rate > 0) {
            mUp = true;
        }
        else {
            mUp = false;
        }
    }
    public void accelerateDown(float rate) {
        if(rate > 0) {
            mDown = true;
        }
        else {
            mDown = false;
        }
    }
    
    public void move(long deltaMs) {
        //System.out.println(getSpeed() + "*" + deltaMs + " / 1000 = " + (getSpeed() * (float)deltaMs) / 1000.0f);
        if(mLeft) {
            x -= (getSpeed() * (float)deltaMs) / 1000.0f;
        }
        if(mRight) {
            x += (getSpeed() * (float)deltaMs) / 1000.0f;
        }
        if(mUp) {
            y -= (getSpeed() * (float)deltaMs) / 1000.0f;
        }
        if(mDown) {
            y += (getSpeed() * (float)deltaMs) / 1000.0f;
        }
        
        
        if(x < 0) {
            x = 0;
        }
        else if(x > game.GAME_AREA_WIDTH) {
            x = game.GAME_AREA_WIDTH;
        }
        if(y < 0) {
            y = 0;
        }
        else if(y > game.GAME_AREA_HEIGHT) {
            y = game.GAME_AREA_HEIGHT;
        }
    }
    
    @Override
    public void render() {
        Renderer.get().draw(currentAnim.getTexture(), x, y, currentAnim.getWidth(), currentAnim.getHeight(), currentAnim.getU(), currentAnim.getV(), currentAnim.getU2(), currentAnim.getV2());
        for(Weapon w : weapons) {
            ISprite s = w.getSprite();
            if(s != null) {
                Renderer.get().draw(s.getTexture(), x, y, s.getWidth(), s.getHeight(), s.getU(), s.getV(), s.getU2(), s.getV2());
            }
        }
        
        //renderHitbox();
    }
}
