package manuel.engine.gui;

import manuel.engine.renderer.Renderer;
import manuel.engine.renderer.Texture;

/**
 * An abstract screen item class. A screen item can be any gui element, like a button or a textbox.
 * 
 * @author Merioksan Mikko
 */
public abstract class ScreenItem {
    protected Texture tex;
    protected int x, y;
    protected int width, height;
    
    protected boolean hasFocus;
    protected boolean visible;
    
    /**
     * If width and height are provided, the texture parameter can be null. Then nothing will be rendered by this class.
     * 
     * @param t texture for rendering the item, or null if nothing is to be rendered.
     * @param x x-coordinate of the center of the item
     * @param y y-coordinate of the center of the item
     * @param w width of the item
     * @param h height of the item
     */
    public ScreenItem(Texture t, int x, int y, int w, int h) {
        tex = t;
        this.x = x;
        this.y = y;
        width = w;
        height = h;
        
        hasFocus = false;
        visible = true;
    }
    /**
     * Minimum requirement for a screen item is it's location and a texture to render.
     * 
     * @param t texture for rendering the item
     * @param x x-coordinate of the center of the item
     * @param y y-coordinate of the center of the item
     */
    public ScreenItem(Texture t, int x, int y) {
        this(t, x, y, t.getWidth(), t.getHeight());
    }
    
    public void setWidth(int w) {
        width = w;
    }
    public int getWidth() {
        return width;
    }
    public void setHeight(int h) {
        height = h;
    }
    public int getHeight() {
        return height;
    }
    
    public void setX(int X) {
        x = X;
    }
    public int getX() {
        return x;
    }
    public void setY(int Y) {
        y = Y;
    }
    public int getY() {
        return y;
    }
    
    public void getFocus() {
        hasFocus = true;
    }
    public void removeFocus() {
        hasFocus = false;
    }
    
    public boolean visible() {
        return visible;
    }
    public void show() {
        visible = true;
    }
    public void hide() {
        visible = false;
    }
    
    public void update(long deltaMs) {
        
    }
    
    public void render() {
        if(visible && tex != null) {
            Renderer.get().draw(tex, x, y, width, height);
        }
    }
    
    public boolean onKeyDown(int key) {
        return false;
    }
    public boolean onKeyUp(int key) {
        return false;
    }
    public boolean onMouseDown(int mX, int mY, int button) {
        if(mX < x + width / 2 && x > x - width / 2 && mY < y + height / 2 && mY > y - height / 2) {
            if(visible) {
                getFocus();
            }
        }
        else {
            if(visible) {
                removeFocus();
            }
        }
        return false;
    }
    public boolean onMouseUp(int mX, int mY, int button) {
        return false;
    }
    public boolean onPointerMove(int mX, int mY, int mDX, int mDY) {
        return false;
    }
}
