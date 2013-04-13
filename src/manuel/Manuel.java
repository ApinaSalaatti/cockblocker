/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package manuel;

import manuel.engine.Application;
import manuel.game.MainGame;

/**
 *
 * @author ApinaSalaatti
 */
public class Manuel {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application app = new Application();
        app.create(new MainGame());
        
        app.run();
    }
}
