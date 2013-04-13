package manuel.game.actions;

import manuel.engine.actions.Action;
import manuel.game.logic.messaging.Message;
import manuel.game.logic.messaging.Messaging;

/**
 *
 * @author Merioksan Mikko
 */
public class NewMessageAction extends Action {
    private Messaging messaging;
    private String topic;
    private String content;
    private boolean done;
    
    public NewMessageAction(String t, String c, Messaging receiver) {
        messaging = receiver;
        topic = t;
        content = c;
        done = false;
    }
    
    @Override
    public void update(long deltaMs) {
        if(!done) {
            messaging.addMessage(topic, content);
            done = true;
        }
    }
    
    @Override
    public boolean finished() {
        return done;
    }
}
