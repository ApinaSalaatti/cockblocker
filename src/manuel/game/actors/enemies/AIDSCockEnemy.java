package manuel.game.actors.enemies;

import manuel.engine.graphics.Animation;
import manuel.engine.graphics.FrameData;
import manuel.engine.utils.resourceManager.ResourceManager;
import manuel.game.actors.Actor;
import manuel.game.actors.Enemy;
import manuel.game.actors.PlayerStats;
import manuel.game.actors.ai.MoveDownAI;
import manuel.game.logic.GameLogic;

/**
 *
 * @author Merioksan Mikko
 */
public class AIDSCockEnemy extends Enemy {
    private float origX;
    
    public AIDSCockEnemy(GameLogic g) {
        super(
            40,
            20,
            g,
            new Animation(
                ResourceManager.get().getTextureManager().getTexture("assets/graphics/actors/cockEnemy.png"),
                100,
                new FrameData(0, 0, 64, 64)
            ),
            new Animation(
                ResourceManager.get().getTextureManager().getTexture("assets/graphics/actors/cockEnemy.png"),
                100,
                new FrameData(0, 64, 64, 64)
            ),
            new Animation(
                ResourceManager.get().getTextureManager().getTexture("assets/graphics/actors/cockEnemy.png"),
                100,
                new FrameData(0, 128, 64, 64)
            ),
            400,
            10,
            100
        );
        
        super.setSide(Actor.SIDE_HOSTILE);
        super.setDisease(PlayerStats.DISEASE_AIDS);
        
        super.setAI(new MoveDownAI());
    }
    public AIDSCockEnemy() {
        this(null);
    }
    
    @Override
    public void setLocation(float x, float y) {
        super.setLocation(x, y);
        origX = x;
    }
    
    @Override
    public void move(long deltaMs) {
        if(mUp) {
            y -= (getSpeed() * (float)deltaMs) / 1000.0f;
        }
        if(mDown) {
            y += (getSpeed() * (float)deltaMs) / 1000.0f;
        }
        
        x = origX + 50 * (float)Math.sin(y / 10);
    }
}
