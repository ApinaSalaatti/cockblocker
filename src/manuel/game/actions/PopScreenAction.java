package manuel.game.actions;

import manuel.engine.actions.Action;
import manuel.engine.gui.Screen;
import manuel.game.view.View;

/**
 *
 * @author Merioksan Mikko
 */
public class PopScreenAction extends Action {
    private View view;
    private boolean done;
    
    public PopScreenAction(View v) {
        view = v;
        
        done = false;
    }
    
    @Override
    public void update(long deltaMs) {
        if(!done) {
            view.popScreen();
            done = true;
        }
    }
    
    @Override
    public boolean finished() {
        return done;
    }
}