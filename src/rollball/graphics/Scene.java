package rollball.graphics;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import rollball.input.Controller;
import rollball.model.GameState;

/**
 * [VIEW]
 * Graphics management.
 */
public final class Scene {

    private final JFrame frame = new JFrame("Roll A Ball");

    public Scene(final GameState gameState, final Controller controller) {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int screenWidth = (int) screen.getWidth(); 
        final int screenHeight = (int) screen.getHeight();
        frame.setSize(screenWidth, screenHeight);
        frame.setMinimumSize(new Dimension(screenWidth, screenHeight));
        frame.setResizable(false);
        frame.setLocationByPlatform(true);
        final ScenePanel panel = new ScenePanel(screenWidth, screenHeight, controller, gameState);
        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void render() {
        frame.repaint();
    }

}
