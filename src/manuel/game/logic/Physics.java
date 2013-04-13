package manuel.game.logic;

import java.util.HashSet;
import java.util.Set;
import manuel.game.actors.Actor;
import manuel.game.actors.IActor;

/**
 *
 * @author Merioksan Mikko
 */
public class Physics {
    public class Collision {
        public Actor a1;
        public Actor a2;
        
        public Collision(Actor a1, Actor a2) {
            this.a1 = a1;
            this.a2 = a2;
        }
        
        @Override
        public int hashCode() {
            return a1.getID()*10000 + a2.getID();
        }
        
        @Override
        public boolean equals(Object o) {
            if(o instanceof Collision) {
                Collision c = (Collision)o;
                if((c.a1.getID() == a1.getID() && c.a2.getID() == a2.getID()) || (c.a1.getID() == a2.getID() && c.a2.getID() == a1.getID())) {
                    return true;
                }
            }
            
            return false;
        }
    }
    
    private GameLogic logic;
    
    public Physics(GameLogic l) {
        logic = l;
    }
    
    // TODO: this is not too good, it's something like O(n^3)...
    public void update(long deltaMs) {
        //ArrayList<Collision> colls = new ArrayList<Collision>();
        Set<Collision> colls = new HashSet<Collision>();
        for(IActor a1 : logic.getActors()) {
            for(IActor a2 : logic.getActors()) {
                if(checkCollision(a1, a2)) {
                    boolean add = true;
                    Collision newC = new Collision((Actor)a1, (Actor)a2);
                    for(Collision c : colls) {
                        if(c.equals(newC)) {
                            add = false;
                        }
                    }
                    if(add) {
                        colls.add(new Collision((Actor)a1, (Actor)a2));
                    }
                }
            }
        }
        
        for(Collision c : colls) {
            logic.collision(c.a1, c.a2);
        }
    }
    
    public boolean checkCollision(IActor a1, IActor a2) {
        if(a1 == a2 || a1.equals(a2)) {
            return false;
        }
        
        Actor ac1 = (Actor)a1;
        Actor ac2 = (Actor)a2;
        
        float x1 = ac1.getX();
        float y1 = ac1.getY();
        float w1 = ac1.getWidth() / 2;
        float h1 = ac1.getHeight() / 2;
        float xLeft1 = x1 - w1;
        float xRight1 = x1 + w1;
        float yTop1 = y1 - h1;
        float yBottom1 = y1 + h1;
        
        float x2 = ac2.getX();
        float y2 = ac2.getY();
        float w2 = ac2.getWidth() / 2;
        float h2 = ac2.getHeight() / 2;
        float xLeft2 = x2 - w2;
        float xRight2 = x2 + w2;
        float yTop2 = y2 - h2;
        float yBottom2 = y2 + h2;
        
        if(xLeft1 > xRight2) {
            return false;
        }
        if(xRight1 < xLeft2) {
            return false;
        }
        if(yTop1 > yBottom2) {
            return false;
        }
        if(yBottom1 < yTop2) {
            return false;
        }
        
        return true;
    }
}
