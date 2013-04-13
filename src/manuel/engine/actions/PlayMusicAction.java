package manuel.engine.actions;

import manuel.engine.audioPlayer.AudioPlayer;
/**
 *
 * @author Merioksan Mikko
 */
public class PlayMusicAction extends Action {
    private String music;
    private boolean loop, done;
    
    public PlayMusicAction(String m, boolean l) {
        music = m;
        loop = l;
        
        done = false;
    }
    
    @Override
    public void update(long deltaMs) {
        if(!done) {
            AudioPlayer.get().playMusic(music, loop);
            done = true;
        }
    }
    
    @Override
    public boolean finished() {
        return done;
    }
}
