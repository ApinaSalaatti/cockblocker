package manuel.game.view.screens;

import manuel.engine.gui.Screen;
import manuel.engine.gui.screenItems.DisplayItem;
import manuel.engine.renderer.Renderer;
import manuel.engine.utils.resourceManager.ResourceManager;
import manuel.game.actors.PlayerStats;
import manuel.game.actors.weapons.Weapon;
import manuel.game.logic.GameLogic;
import manuel.game.logic.messaging.Message;

/**
 *
 * @author Merioksan Mikko
 */
public class GameplayScreen extends Screen {
    private GameLogic logic;

    public GameplayScreen() {
        super();
        create();
    }
    
    @Override
    public void create() {
        DisplayItem messages = new DisplayItem(ResourceManager.get().getTextureManager().getTexture("assets/graphics/ui/message.png"), 250, 537) {
            @Override
            public void render() {
                if(logic.getMessaging().getCurrentMessage() != null) {
                    super.render();
                    Message m = logic.getMessaging().getCurrentMessage();
                    Renderer.get().drawText(m.getTopic(), x-200, y-80);
                    int lineLength = 440 / 12;
                    if(m.getContent().length() < lineLength) {
                        Renderer.get().drawText(m.getContent(), x-200, y-45, 0.75f);
                    }
                    else {
                        renderManyLines(m.getContent(), lineLength, 0);
                    }
                }
            }
            
            public void renderManyLines(String text, int lineLength, int line) {
                if(text.length() <= lineLength) {
                    Renderer.get().drawText(text, x-200, y-45+line*26, 0.75f);
                }
                else {
                    int cut = findSpace(text, lineLength);
                    Renderer.get().drawText(text.substring(0, cut+1), x-200, y-45+line*26, 0.75f);
                    renderManyLines(text.substring(cut+1, text.length()), lineLength, line+1);
                }
            }

            public int findSpace(String text, int lineLength) {
                for(int i = lineLength-1; i >= 0; i--) {
                    if(text.charAt(i) == ' ') {
                        return i;
                    }  
                }

                return 0;
            }
        };
        
        DisplayItem score = new DisplayItem(null, 0, 0, 0, 0) {
            @Override
            public void render() {
                Renderer.get().drawText("SCORE:" + logic.getPlayer().getStats().getScore(), x+10, y+10);
                Renderer.get().drawText("TIME:" + logic.getTime() / 1000, x+310, y+10);
                Renderer.get().drawText("PHYSICAL:" + logic.getPlayer().getStats().getPhysical(), x+10, y+40);
                Renderer.get().drawText("MENTAL:" + logic.getPlayer().getStats().getMental(), x+10, y+80);
                Renderer.get().drawText("CHILDREN:" + logic.getPlayer().getStats().getChildren(), x+10, y+120);
                if(logic.getPlayer().getStats().hasDisease(PlayerStats.DISEASE_CHLAMYDIA)) {
                    Renderer.get().drawText("CHLAMYDIA", x+10, y+160);
                }
                if(logic.getPlayer().getStats().hasDisease(PlayerStats.DISEASE_GONORRHEA)) {
                    Renderer.get().drawText("GONORRHEA", x+10, y+200);
                }
                if(logic.getPlayer().getStats().hasDisease(PlayerStats.DISEASE_AIDS)) {
                    Renderer.get().drawText("AIDS!!", x+10, y+240);
                }
                
                int indx = 0;
                for(Weapon w : logic.getPlayer().getShip().getWeapons()) {
                    if(w.getAmmo() > 0) {
                        Renderer.get().drawText(w.getName() + ":" + w.getAmmo(), x+10, y+280+indx*40);
                    }
                    indx++;
                }
            }
        };

        addScreenItem(messages);
        addScreenItem(score);
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
