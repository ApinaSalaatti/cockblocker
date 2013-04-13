package manuel.engine.graphics;

import java.util.ArrayList;
import manuel.engine.renderer.Renderer;
import manuel.engine.renderer.Texture;

/**
 *
 * @author Merioksan Mikko
 */
public class Animation implements ISprite {
    private Texture tex;
    private ArrayList<FrameData> frames;
    
    private int currentFrame;
    private long frameDelay;
    private long onCurrentFrame;
    
    public Animation(Texture t, long delay, FrameData ... f) {
        tex = t;
        frames = new ArrayList<FrameData>();
        for(FrameData frame : f) {
            frames.add(frame);
        }
        
        currentFrame = 0;
        frameDelay = delay;
        onCurrentFrame = 0;
    }
    
    public FrameData getFrame() {
        return frames.get(currentFrame);
    }
    
    @Override
    public void update(long deltaMs) {
        onCurrentFrame += deltaMs;
        
        if(onCurrentFrame >= frameDelay) {
            currentFrame++;
            currentFrame %= frames.size();
            onCurrentFrame = 0;
        }
    }
    
    @Override
    public void reset() {
        currentFrame = 0;
        onCurrentFrame = 0;
    }

    @Override
    public Texture getTexture() {
        return tex;
    }
    
    public FrameData currentFrame() {
        return frames.get(currentFrame);
    }
    
    @Override
    public float getWidth() {
        return currentFrame().width;
    }
    @Override
    public float getHeight() {
        return currentFrame().height;
    }
    
    @Override
    public float getU() {
        return currentFrame().x / tex.getWidth();
    }
    @Override
    public float getV() {
        return currentFrame().y / tex.getHeight();
    }
    @Override
    public float getU2() {
        return (currentFrame().x + currentFrame().width) / tex.getWidth();
    }
    @Override
    public float getV2() {
        return (currentFrame().y + currentFrame().height) / tex.getHeight();
    }
}
