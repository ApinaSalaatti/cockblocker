package manuel.engine.graphics;

import manuel.engine.renderer.Texture;
import manuel.engine.utils.resourceManager.ResourceManager;

/**
 *
 * @author Merioksan Mikko
 */
public class Sprite implements ISprite {
    private Texture tex;
    private float width, height;
    private float u, v, u2, v2;
    
    public Sprite(Texture t) {
        this(t, new FrameData(0, 0, t.getWidth(), t.getHeight()));
    }
    public Sprite(String t) {
        this(ResourceManager.get().getTextureManager().getTexture(t));
    }
    public Sprite(Texture t, FrameData fd) {
        tex = t;
        width = fd.width;
        height = fd.height;
        u = fd.x / tex.getWidth();
        v = fd.y / tex.getHeight();
        u2 = (fd.x + fd.width) / tex.getWidth();
        v2 = (fd.y + fd.height) / tex.getHeight();
    }
    
    @Override
    public void update(long deltaMs) {
        
    }
    
    @Override
    public void reset() {
        
    }
    
    @Override
    public Texture getTexture() {
        return tex;
    }
    
    @Override
    public float getWidth() {
        return width;
    }
    @Override
    public float getHeight() {
        return height;
    }
    
    @Override
    public float getU() {
        return u;
    }
    @Override
    public float getV() {
        return v;
    }
    @Override
    public float getU2() {
        return u2;
    }
    @Override
    public float getV2() {
        return v2;
    }
}
