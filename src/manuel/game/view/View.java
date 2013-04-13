package manuel.game.view;

import java.util.ArrayDeque;
import java.util.Iterator;
import manuel.engine.IGameLogic;
import manuel.engine.IView;
import manuel.engine.audioPlayer.AudioPlayer;
import manuel.engine.gui.Screen;
import manuel.engine.renderer.Renderer;
import manuel.engine.renderer.Texture;
import manuel.engine.utils.resourceManager.ResourceManager;
import manuel.game.Options;
import manuel.game.actors.IActor;
import manuel.game.logic.GameLogic;
import manuel.game.logic.GameLogic.GameState;
import manuel.game.view.screens.DeathScreen;
import manuel.game.view.screens.MainMenuScreen;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

/**
 *
 * @author Merioksan Mikko
 */
public class View implements IView {
    private GameLogic logic;
    
    private Texture background;
    
    private ArrayDeque<Screen> screens;
    
    private boolean gameEnded;
    
    public View() {
        background = ResourceManager.get().getTextureManager().getTexture("assets/graphics/background.png");
        screens = new ArrayDeque<Screen>();
        
        addScreen(new MainMenuScreen());
        
        gameEnded = false;
    }
    
    @Override
    public void registerLogic(IGameLogic l) {
        logic = (GameLogic)l;
    }
    @Override
    public IGameLogic getLogic() {
        return logic;
    }
    
    public void addScreen(Screen s) {
        s.attachToView(this);
        screens.addFirst(s);
    }
    public void popScreen() {
        Screen s = screens.pollFirst();
        s.attachToView(null);
        s.destroy();
    }
    public void clearScreens() {
        for(Screen s: screens) {
            s.attachToView(null);
            s.destroy();
        }
        screens.clear();
    }

    @Override
    public void update(long deltaMs) {
        if(logic.getCurrentState() == GameState.GAME_END && !gameEnded) {
            gameEnded = true;
            clearScreens();
            addScreen(new DeathScreen(logic.getPlayer()));
        }
        for(Screen i : screens) {
            i.update(deltaMs);
        }
    }
    
    @Override
    public void render() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
        // KEEP THIS FIRST COCKSUCKER!
        Renderer.get().draw(background, Display.getWidth() / 2, Display.getHeight() / 2);
        
        for(IActor a : logic.getActors()) {
            a.render();
        }
        
        Iterator<Screen> it = screens.descendingIterator();
        while(it.hasNext()) {
            Screen s = it.next();
            s.render();
        }
    }
    
    public boolean onMouseDown(int mX, int mY, int button) {
        for(Screen i : screens) {
            i.onMouseDown(mX, mY, button);
        }
        return false;
    }
    public boolean onMouseUp(int mX, int mY, int button) {
        for(Screen i : screens) {
            i.onMouseUp(mX, mY, button);
        }
        return false;
    }
    
    public boolean onKeyDown(int key) {
        for(Screen i : screens) {
            i.onKeyDown(key);
        }
        
        if(key == Keyboard.KEY_LEFT) {
            logic.getPlayer().accelerateLeft(1.0f);
            return true;
        }
        else if(key == Keyboard.KEY_RIGHT) {
            logic.getPlayer().accelerateRight(1.0f);
            return true;
        }
        else if(key == Keyboard.KEY_UP) {
            logic.getPlayer().accelerateUp(1.0f);
            return true;
        }
        else if(key == Keyboard.KEY_DOWN) {
            logic.getPlayer().accelerateDown(1.0f);
            return true;
        }
        else if(key == Keyboard.KEY_LCONTROL) {
            logic.getPlayer().firing(true);
        }
        
        return false;
    }
    
    public boolean onKeyUp(int key) {
        for(Screen i : screens) {
            i.onKeyUp(key);
        }
        
        if(key == Keyboard.KEY_LEFT) {
            logic.getPlayer().accelerateLeft(0.0f);
            return true;
        }
        else if(key == Keyboard.KEY_RIGHT) {
            logic.getPlayer().accelerateRight(0.0f);
            return true;
        }
        else if(key == Keyboard.KEY_UP) {
            logic.getPlayer().accelerateUp(0.0f);
            return true;
        }
        else if(key == Keyboard.KEY_DOWN) {
            logic.getPlayer().accelerateDown(0.0f);
            return true;
        }
        else if(key == Keyboard.KEY_LCONTROL) {
            logic.getPlayer().firing(false);
        }
        
        return false;
    }
    
    public boolean onPointerMove(int mX, int mY, int mDX, int mDY) {
        for(Screen i : screens) {
            i.onPointerMove(mX, mY, mDX, mDY);
        }
        return false;
    }
    
    public void optionsChanged(Options o) {
        AudioPlayer.get().setMusicVolume(o.getMusicVolume());
        AudioPlayer.get().setSoundVolume(o.getSoundVolume());
    }
}
