package manuel.game.actions;

import manuel.engine.actions.Action;
import manuel.engine.gui.Screen;
import manuel.game.view.View;

/**
 *
 * @author Merioksan Mikko
 */
public class AddScreenAction extends Action {
    private View view;
    private Screen screen;
    private boolean done;
    
    public AddScreenAction(View v, Screen s) {
        view = v;
        screen = s;
        
        done = false;
    }
    
    @Override
    public void update(long deltaMs) {
        if(!done) {
            view.addScreen(screen);
            done = true;
        }
    }
    
    @Override
    public boolean finished() {
        return done;
    }
}
