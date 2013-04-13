package manuel.game.logic;

import java.util.ArrayList;
import manuel.engine.IGameLogic;
import manuel.engine.graphics.FrameData;
import manuel.engine.graphics.Sprite;
import manuel.engine.utils.resourceManager.ResourceManager;
import manuel.game.MainGame;
import manuel.game.actors.Actor;
import manuel.game.actors.Enemy;
import manuel.game.actors.IActor;
import manuel.game.actors.Player;
import manuel.game.actors.pickups.Antibiotics;
import manuel.game.actors.pickups.Pickup;
import manuel.game.actors.pickups.WeaponPickup;
import manuel.game.actors.ships.CondomRocket;
import manuel.game.actors.ships.Ship;
import manuel.game.actors.weapons.HormoneWave;
import manuel.game.actors.weapons.Projectile;
import manuel.game.logic.messaging.Messaging;
import org.lwjgl.opengl.Display;

/**
 *
 * @author Merioksan Mikko
 */
public class GameLogic implements IGameLogic {
    public int GAME_AREA_WIDTH;
    public int GAME_AREA_HEIGHT;
    
    private MainGame game;
    
    public enum GameState { MAIN_MENU, PLAYING, PAUSED, GAME_END }
    private GameState currentState;
    
    private long time;
    
    private ArrayList<IActor> actors, toAdd, toDelete;
    
    private Player player;
    
    private Messaging messaging;
    private Physics physics;
    private EnemyManager enemyManager;
    private PickupManager pickupManager;
    
    public GameLogic() {
        GAME_AREA_WIDTH = Display.getWidth();
        GAME_AREA_HEIGHT = Display.getHeight();
        
        time = 0;
        
        actors = new ArrayList<IActor>();
        toAdd = new ArrayList<IActor>();
        toDelete = new ArrayList<IActor>();
        
        messaging = new Messaging();
        physics = new Physics(this);
        enemyManager = new EnemyManager(this);
        pickupManager = new PickupManager(this);
    }
    
    public void registerGame(MainGame g) {
        game = g;
    }
    public MainGame getGame() {
        return game;
    }
    
    public void requestQuit() {
        game.requestQuit();
    }
    
    public void changeState(GameState newState) {
        switch(newState) {
            case MAIN_MENU:
                currentState = GameState.MAIN_MENU;
                break;
            case PLAYING:
                if(currentState != GameState.PAUSED) {
                    startGame();
                }
                currentState = GameState.PLAYING;
                break;
            case PAUSED:
                currentState = GameState.PAUSED;
                break;
            case GAME_END:
                currentState = GameState.GAME_END;
                break;
        }
    }
    public GameState getCurrentState() {
        return currentState;
    }
    
    public void startGame() {
        actors.clear();
        toAdd.clear();
        toDelete.clear();
        
        player = new Player(this);
        CondomRocket cr = new CondomRocket();
        cr.setLocation(400, 500);
        addActor(cr);
        player.setShip(cr);
        
        enemyManager.start();
        pickupManager.start();
        
        WeaponPickup wp = new WeaponPickup(new HormoneWave(), new Sprite(ResourceManager.get().getTextureManager().getTexture("assets/graphics/actors/pickups.png"), new FrameData(0, 0, 64, 64)));
        Antibiotics a = new Antibiotics();
        wp.setLocation(300, 300);
        a.setLocation(400, 400);
        //addActor(wp);
        //addActor(a);
        
        /*
        CockEnemy ce = new CockEnemy();
        ce.setLocation(400, 100);
        addActor(ce);
        
        CockEnemy ce1 = new CockEnemy();
        ce1.setLocation(600, 100);
        addActor(ce1);
        
        CockEnemy ce2 = new CockEnemy();
        ce2.setLocation(200, 100);
        addActor(ce2);
        
        messaging.addMessage("WELCOME!", "Welcome, warrior, to a game of COCKBLOCKER.");
        */
    }
    
    public long getTime() {
        return time;
    }
    
    public ArrayList<IActor> getActors() {
        return actors;
    }
    public void addActor(IActor a) {
        a.setGame(this);
        toAdd.add(a);
    }
    public void removeActor(IActor a) {
        toDelete.add(a);
    }
    
    public Messaging getMessaging() {
        return messaging;
    }
    public Player getPlayer() {
        return player;
    }
    
    @Override
    public void update(long deltaMs) {
        if(currentState == GameState.PLAYING) {
            time += deltaMs;

            messaging.update(deltaMs);
            physics.update(deltaMs);

            for(IActor a : toAdd) {
                actors.add(a);
            }
            toAdd.clear();

            for(IActor a : toDelete) {
                actors.remove(a);
            }
            toDelete.clear();

            for(IActor a : actors) {
                a.update(deltaMs);
                checkDeath(a);
            }
            
            player.update(deltaMs);
            enemyManager.update(deltaMs);
            pickupManager.update(deltaMs);
        }
    }
    
    public void collision(Actor a1, Actor a2) {
        Projectile proj = null;
        Ship s = null;
        Pickup p = null;
        
        if(a1.getType() == Actor.TYPE_PROJECTILE) {
            proj = (Projectile)a1;
        }
        else if(a1.getType() == Actor.TYPE_SHIP) {
            s = (Ship)a1;
        }
        else if(a1.getType() == Actor.TYPE_PICKUP) {
            p = (Pickup)a1;
        }
        if(a2.getType() == Actor.TYPE_PROJECTILE) {
            proj = (Projectile)a2;
        }
        else if(a2.getType() == Actor.TYPE_SHIP) {
            s = (Ship)a2;
        }
        else if(a2.getType() == Actor.TYPE_PICKUP) {
            p = (Pickup)a2;
        }
        
        if(proj != null && s != null) {
            if(proj.getSide() != s.getSide()) {
                s.setHealth(s.getHealth() - proj.getDamage());
                removeActor(proj);
            }
        }
        else if(p != null && s != null) {
            if(s.getSide() == Actor.SIDE_FRIENDLY) {
                p.actOn(s);
                removeActor(p);
            }
        }
    }
    
    private void checkDeath(IActor a) {
        if(player.getStats().getPhysical() <= 0) {
            changeState(GameState.GAME_END);
        }
        
        Actor ac = (Actor)a;
        if(ac.getType() == Actor.TYPE_UNDEFINED) { // These should not exist! DIE!
            removeActor(a);
        }
        else if(ac.getY() < -500 || ac.getX() < -500 || ac.getX() > Display.getWidth() + 500) { // if actor somehow went out of the game area, kill it
            removeActor(ac);
        }
        else if(ac.getType() == Actor.TYPE_SHIP) {
            checkShipDeath((Ship)ac);
        }
        else if(ac.getType() == Actor.TYPE_PROJECTILE) {
            checkProjectileDeath((Projectile)ac);
        }
    }
    
    public void checkShipDeath(Ship s) {
        if(s.getHealth() <= 0) {
            if(s.getSide() == Actor.SIDE_HOSTILE) {
                player.enemyKilled((Enemy)s);
            }
            removeActor(s);
        }
        else if(s.getY() > Display.getHeight() + 80) {
            if(s.getSide() == Actor.SIDE_HOSTILE) {
                player.enemyThrough((Enemy)s);
            }
            removeActor(s);
        }
    }
    
    public void checkProjectileDeath(Projectile p) {
        if(p.getY() > Display.getHeight() + 80) {
            removeActor(p);
        }
    }
}
