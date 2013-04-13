package manuel.game;

/**
 *
 * @author Merioksan Mikko
 */
public class Options {
    private MainGame game;
    
    private float musicVolume;
    private float soundVolume;
    
    public Options(MainGame g) {
        game = g;
        musicVolume = 0.0f;
        soundVolume = 1.0f;
    }
    
    public void setMusicVolume(float v) {
        if(v > 1.0f) {
            v = 1.0f;
        }
        else if(v < 0) {
            v = 0;
        }
        musicVolume = v;
    }
    public float getMusicVolume() {
        return musicVolume;
    }
    
    public void setSoundVolume(float v) {
        if(v > 1.0f) {
            v = 1.0f;
        }
        else if(v < 0) {
            v = 0;
        }
        soundVolume = v;
    }
    public float getSoundVolume() {
        return soundVolume;
    }
    
    public void save() {
        game.optionsChanged();
    }
}
