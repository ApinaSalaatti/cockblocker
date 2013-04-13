package manuel.game.logic.messaging;

import manuel.game.logic.messaging.Message;
import java.util.ArrayDeque;

/**
 *
 * @author Merioksan Mikko
 */
public class Messaging {
    private ArrayDeque<Message> queue;
    private long delayed;
    
    public Messaging() {
        queue = new ArrayDeque<Message>();
        delayed = 0;
    }
    
    public void addMessage(String topic, String content) {
        queue.addLast(new Message(topic, content));
    }
    
    public void update(long deltaMs) {
        if(!queue.isEmpty()) {
            delayed += deltaMs;
            if(delayed >= queue.peekFirst().getDelay()) {
                queue.pollFirst();
                delayed = 0;
            }
        }
    }
    
    public Message getCurrentMessage() {
        return queue.peekFirst();
    }
}
