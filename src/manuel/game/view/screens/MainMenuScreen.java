package manuel.game.view.screens;

import manuel.engine.actions.ActionSequence;
import manuel.engine.actions.DelayAction;
import manuel.engine.actions.DisplayAllScreenItemsAction;
import manuel.engine.actions.PlayMusicAction;
import manuel.engine.audioPlayer.AudioPlayer;
import manuel.engine.gui.Screen;
import manuel.engine.gui.screenItems.Button;
import manuel.engine.gui.screenItems.DisplayItem;
import manuel.engine.gui.screenItems.listeners.ButtonListener;
import manuel.engine.utils.resourceManager.ResourceManager;
import manuel.game.logic.GameLogic;
import manuel.game.view.View;
import org.lwjgl.opengl.Display;

/**
 *
 * @author Merioksan Mikko
 */
public class MainMenuScreen extends Screen {
    private GameLogic logic;
    
    public MainMenuScreen() {
        super();
        create();
    }
    
    @Override
    public void create() {
        Button start = new Button(ResourceManager.get().getTextureManager().getTexture("assets/graphics/ui/mainmenuscreen/start.png"), 350, 225, 500, 250);
        start.addButtonListener(new ButtonListener() {
            @Override
            public void onClick() {
                logic.changeState(GameLogic.GameState.PLAYING);
                View v = (View)view;
                v.popScreen(); // remove this screen
                v.addScreen(new GameplayScreen());
                AudioPlayer.get().stopMusic();
            }
        });
        
        Button quit = new Button(ResourceManager.get().getTextureManager().getTexture("assets/graphics/ui/mainmenuscreen/quit.png"), 500, 440, 500, 250);
        quit.addButtonListener(new ButtonListener() {
            @Override
            public void onClick() {
                logic.requestQuit();
            }
        });
        
        DisplayItem bg = new DisplayItem(
            ResourceManager.get().getTextureManager().getTexture("assets/graphics/ui/mainmenuscreen/background.png"),
            Display.getWidth() / 2,
            Display.getHeight() / 2
        );
        
        start.hide();
        quit.hide();
        
        addScreenItem(bg);
        addScreenItem(start);
        addScreenItem(quit);
        
        actions = new ActionSequence(
            new PlayMusicAction("assets/audio/testi.wav", true),
            new DelayAction(2200),
            new DisplayAllScreenItemsAction(this, true)
        );
    }
    
    @Override
    public void destroy() {
        logic = null;
    }
    
    @Override
    public void update(long deltaMs) {
        super.update(deltaMs);
        if(logic == null && view != null) {
            logic = (GameLogic)view.getLogic();
        }
    }
}
