package manuel.engine;

/**
 * 
 * @author Merioksan Mikko
 */
public interface IView {
    public void registerLogic(IGameLogic logic);
    public IGameLogic getLogic();
    public void update(long deltaMs);
    public void render();
}
