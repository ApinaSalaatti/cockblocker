package manuel.game;

import java.util.Random;
import manuel.game.logic.GameLogic;
import manuel.game.view.View;
import manuel.engine.Game;

/**
 *
 * @author Merioksan Mikko
 */
public class MainGame extends Game {
    private GameLogic logic;
    private View view;
    
    private Options options;
    private Random rng;
    
    private boolean quit;
    
    @Override
    public void create() {
        logic = new GameLogic();
        view = new View();
        
        options = new Options(this);
        rng = new Random();
        
        quit = false;
        
        view.registerLogic(logic);
        logic.registerGame(this);
        
        optionsChanged(); // make everyone check the options when game is created...
    }
    
    public Random getRNG() {
        return rng;
    }
    
    @Override
    public void update(long deltaMs) {
        logic.update(deltaMs);
        view.update(deltaMs);
    }
    
    @Override
    public void render() {
        view.render();
    }
    
    @Override
    public void destroy() {
        
    }
    
    public void requestQuit() {
        quit = true;
    }
    @Override
    public boolean isQuitRequested() {
        return quit;
    }
    
    @Override
    public boolean onMouseDown(int mX, int mY, int button) {
        return view.onMouseDown(mX, mY, button);
    }
    @Override
    public boolean onMouseUp(int mX, int mY, int button) {
        return view.onMouseUp(mX, mY, button);
    }
    
    @Override
    public boolean onKeyDown(int key) {
        return view.onKeyDown(key);
    }
    
    @Override
    public boolean onKeyUp(int key) {
        return view.onKeyUp(key);
    }
    
    @Override
    public boolean onPointerMove(int mX, int mY, int mDX, int mDY) {
        return view.onPointerMove(mX, mY, mDX, mDY);
    }
    
    public void optionsChanged() {
        view.optionsChanged(options);
    }
    public Options getOptions() {
        return options;
    }
}
