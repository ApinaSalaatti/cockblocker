package manuel.game.logic.messaging;

/**
 *
 * @author Merioksan Mikko
 */
public class Message {
    private String topic, content;
    private long delay;
    
    public Message(String t, String c) {
        topic = t;
        content = c;
        delay = t.length() + c.length() * 75;
        if(delay > 6000) {
            delay = 6000;
        }
    }
    
    public String getTopic() {
        return topic;
    }
    public String getContent() {
        return content;
    }
    public long getDelay() {
        return delay;
    }
}
