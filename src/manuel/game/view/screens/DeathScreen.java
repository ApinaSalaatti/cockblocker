package manuel.game.view.screens;

import manuel.engine.gui.Screen;
import manuel.engine.gui.screenItems.Button;
import manuel.engine.gui.screenItems.listeners.ButtonListener;
import manuel.engine.renderer.Renderer;
import manuel.engine.utils.resourceManager.ResourceManager;
import manuel.game.actors.Player;
import manuel.game.actors.PlayerStats;
import manuel.game.logic.GameLogic;
import manuel.game.view.View;

/**
 *
 * @author Merioksan Mikko
 */
public class DeathScreen extends Screen {
    private Player player;
    private String[] logs;
    private int lineOffset, show;
    
    public DeathScreen(Player p) {
        super();
        player = p;
        create();
    }
    
    @Override
    public void create() {
        Button quitButton = new Button(ResourceManager.get().getTextureManager().getTexture("assets/graphics/ui/quit.png"), 100, 100, 60, 60);
        quitButton.addButtonListener(new ButtonListener() {
            @Override
            public void onClick() {
                View v = (View)view;
                v.popScreen();
                v.addScreen(new MainMenuScreen());
                GameLogic l = (GameLogic)v.getLogic();
                l.changeState(GameLogic.GameState.MAIN_MENU);
            }
        });
        
        Button upButton = new Button(ResourceManager.get().getTextureManager().getTexture("assets/graphics/ui/textField.png"), 500, 100, 60, 60);
        upButton.addButtonListener(new ButtonListener() {
            @Override
            public void onClick() {
                lineOffset--;
                if(lineOffset < 0) {
                    lineOffset = 0;
                }
            }
        });
        Button downButton = new Button(ResourceManager.get().getTextureManager().getTexture("assets/graphics/ui/textField.png"), 500, 500, 60, 60);
        downButton.addButtonListener(new ButtonListener() {
            @Override
            public void onClick() {
                lineOffset++;
                if(lineOffset > logs.length - show) {
                    lineOffset = logs.length - show;
                }
            }
        });
        
        addScreenItem(quitButton);
        addScreenItem(upButton);
        addScreenItem(downButton);
        
        String log = player.getStats().getEventLog();
        logs = log.split(";");
        lineOffset = 0;
        show = 5;
    }
    
    @Override
    public void destroy() {
        player = null;
    }
    
    @Override
    public void render() {
        super.render();
        
        int indx = 0;
        for(int i = lineOffset; i < lineOffset+show; i++) {
            if(i >= 0 && i < logs.length) {
                String l = logs[i];
                Renderer.get().drawText(l, 100, 100 + indx*40);
                indx++;
            }
        }
        
        /*
        Renderer.get().drawText("There is a...", 100, 200);
        int c = (int)(player.getStats().getDisease(PlayerStats.DISEASE_CHLAMYDIA) * 100f);
        int g = (int)(player.getStats().getDisease(PlayerStats.DISEASE_GONORRHEA) * 100);
        int a = (int)(player.getStats().getDisease(PlayerStats.DISEASE_AIDS) * 100);
        Renderer.get().drawText(c + "% change you contracted chlamydia!", 100, 240);
        Renderer.get().drawText(g + "% change you contracted gonorrhea!", 100, 280);
        Renderer.get().drawText(a + "% change you contracted AIDS!", 100, 320);
        
        Renderer.get().drawText("Additionally...", 100, 400);
        int ch = player.getStats().getChildren();
        Renderer.get().drawText("You now have " + ch + " bastard children!", 100, 440);
        
        Renderer.get().drawText("Your resistance score is:" + player.getStats().getScore(), 100, 520);
        
        String grade = getGrade();
        Renderer.get().drawText("Final grade:" + grade, 100, 600);
        * 
        */
    }
    
}
