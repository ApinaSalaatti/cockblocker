package manuel.game.actors;

import manuel.engine.renderer.Renderer;
import manuel.engine.utils.resourceManager.ResourceManager;
import manuel.game.logic.GameLogic;

/**
 *
 * @author Merioksan Mikko
 */
public abstract class Actor implements IActor {
    public static int lastID = 0;
    private int ID;
    
    public static final int TYPE_UNDEFINED = 0;
    public static final int TYPE_SHIP = 1;
    public static final int TYPE_PROJECTILE = 2;
    public static final int TYPE_PICKUP = 3;
    private int type;
    
    public static final int SIDE_NEUTRAL = 10;
    public static final int SIDE_FRIENDLY = 11;
    public static final int SIDE_HOSTILE = 12;
    private int side;
    
    /**
     * The game this actor is currently a part of.
     */
    protected GameLogic game;
    
    protected float x, y;
    protected int width, height;
    
    public Actor(int w, int h, GameLogic g) {
        ID = lastID++;
        
        width = w;
        height = h;
        game = g;
        
        type = TYPE_UNDEFINED;
        side = SIDE_NEUTRAL;
    }
    public Actor(int w, int h) {
        this(w, h, null);
    }
    
    @Override
    public int getID() {
        return ID;
    }
    
    public void setType(int t) {
        type = t;
    }
    public int getType() {
        return type;
    }
    public void setSide(int s) {
        side = s;
    }
    public int getSide() {
        return side;
    }
    
    @Override
    public void setGame(GameLogic g) {
        game = g;
    }
    public GameLogic getGame() {
        return game;
    }
    
    public void setLocation(float x, float y) {
        this.x = x;
        this.y = y;
    }
    public float getX() {
        return x;
    }
    public void setX(float X) {
        x = X;
    }
    public float getY() {
        return y;
    }
    public void setY(float Y) {
        y = Y;
    }
    
    public GameLogic getLogic() {
        return game;
    }
    
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    
    // for debugging crap...
    protected void renderHitbox() {
        float[] letter = new float[48];
        // vertices of current letter:
        // top left
        letter[0] = x - width / 2;
        letter[1] = y - height / 2;
        letter[2] = 1;
        letter[3] = 1;
        letter[4] = 1;
        letter[5] = 1;
        letter[6] = 0;
        letter[7] = 0;

        // top right
        letter[8+0] = x + width / 2;
        letter[8+1] = y - width / 2;
        letter[8+2] = 1;
        letter[8+3] = 1;
        letter[8+4] = 1;
        letter[8+5] = 1;
        letter[8+6] = 1;
        letter[8+7] = 0;

        // bottom left
        letter[16+0] = x - width / 2;
        letter[16+1] = y + width / 2;
        letter[16+2] = 1;
        letter[16+3] = 1;
        letter[16+4] = 1;
        letter[16+5] = 1;
        letter[16+6] = 0;
        letter[16+7] = 1;

        // top right
        letter[24+0] = x + width / 2;
        letter[24+1] = y - width / 2;
        letter[24+2] = 1;
        letter[24+3] = 1;
        letter[24+4] = 1;
        letter[24+5] = 1;
        letter[24+6] = 1;
        letter[24+7] = 0;

        // bottom right
        letter[32+0] = x + width / 2;
        letter[32+1] = y + width / 2;
        letter[32+2] = 1;
        letter[32+3] = 1;
        letter[32+4] = 1;
        letter[32+5] = 1;
        letter[32+6] = 1;
        letter[32+7] = 1;

        // bottom left
        letter[40+0] = x - width / 2;
        letter[40+1] = y + width / 2;
        letter[40+2] = 1;
        letter[40+3] = 1;
        letter[40+4] = 1;
        letter[40+5] = 1;
        letter[40+6] = 0;
        letter[40+7] = 1;
        
        Renderer.get().draw(ResourceManager.get().getTextureManager().getTexture("assets/graphics/debug.png"), letter, 0);
    }
}
