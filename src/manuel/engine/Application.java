package manuel.engine;

import manuel.engine.renderer.Renderer;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

/**
 *
 * @author Merioksan Mikko
 */
public class Application {
    public static int WINDOW_WIDTH = 800;
    public static int WINDOW_HEIGHT = 640;
    public static String WINDOW_TITLE = "Cockblocker";
    public static boolean FULLSCREEN = false;
    public static boolean VSYNC = true;
    
    private long lifetime;
    
    private Game game;
    
    public Application() {
        
    }
    
    public void create(Game g) {
        try {
            // initiate the window
            Display.setDisplayMode(new DisplayMode(WINDOW_WIDTH, WINDOW_HEIGHT));
            Display.setTitle(WINDOW_TITLE);
            Display.setResizable(true); //whether our window is resizable
            Display.setVSyncEnabled(VSYNC); //whether hardware VSync is enabled
            Display.setFullscreen(FULLSCREEN); //whether fullscreen is enabled

            //Mouse.setGrabbed(true);

            //create and show our display
            Display.create();

            resize();
            
            Renderer.get().create();
            
        } catch(LWJGLException e) {
            System.exit(1);
        }
        game = g;
        game.create();
    } 
    
    public void setTitle(String t) {
        WINDOW_TITLE = t;
        Display.setTitle(t);
    }
    
    public void run() {
        lifetime = 0;
        
        long timeWas, timeIs = (Sys.getTime() * 1000) / Sys.getTimerResolution();;
        long deltaMs = 0;
        while(!game.isQuitRequested() && !Display.isCloseRequested()) {
            handleInput();
            
            timeWas = timeIs;
            timeIs = (Sys.getTime() * 1000) / Sys.getTimerResolution();;
            deltaMs = timeIs - timeWas;
            //System.out.println(deltaMs);
            lifetime += deltaMs;
            
            game.update(deltaMs);
            
            Renderer.get().begin();
            game.render();
            Renderer.get().end();
            
            // If the window was resized, we need to update our projection
            if (Display.wasResized()) {
                resize();
            }
            
            Display.update();
            Display.sync(50);
        }
        
        destroy();
    }
    
    public boolean handleInput() {
        while(Keyboard.next()) {
	    if(Keyboard.getEventKeyState()) {
                game.onKeyDown(Keyboard.getEventKey());
	    } else {
	        game.onKeyUp(Keyboard.getEventKey());
	    }
	}
        float mDX = Mouse.getDX();
        float mDY = -Mouse.getDY();
        float mouseX = Mouse.getX();
        float mouseY = Display.getHeight() - Mouse.getY();
        
        while(Mouse.next()) {
            int button = Mouse.getEventButton();
            int mX = Mouse.getEventX();
            int mY = Display.getHeight() - Mouse.getEventY(); // invert the y-axis to match all our coordinates
            if(button != -1) { // check that a state of a mouse button has changed
                if(Mouse.getEventButtonState()) {
                    game.onMouseDown((int)mX, (int)mY, button);
                }
                else {
                    game.onMouseUp((int)mX, (int)mY, button);
                }
            }
        }
        
        if(mDX != 0 || mDY != 0) {
            game.onPointerMove((int)mouseX, (int)mouseY, (int)mDX, (int)mDY);
        }
        
        return false;
    }
    
    /**
     * Resize the window.
     */
    public void resize() {
        GL11.glViewport(0, 0, Display.getWidth(), Display.getHeight());
    }
    
    public void destroy() {
        game.destroy();
    }
}
