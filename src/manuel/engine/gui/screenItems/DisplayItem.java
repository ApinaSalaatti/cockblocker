package manuel.engine.gui.screenItems;

import manuel.engine.gui.ScreenItem;
import manuel.engine.renderer.Texture;

/**
 * Create a Display if you only want a screen item to render a sprite.
 * You can also override the render method ro render everyting and anyting you want, as this is actually just a concrete implementation which does nothing. :)
 * 
 * @author Merioksan Mikko
 */
public class DisplayItem extends ScreenItem {
    /**
     * Please don't use this if texture is null. 
     */
    public DisplayItem(Texture t, int x, int y) {
        super(t, x, y);
    }
    /**
     * Here, the texture can also be null. If width and height are not needed, just pass them as 0.
     */
    public DisplayItem(Texture t, int x, int y, int w, int h) {
        super(t, x, y, w, h);
    }
}
