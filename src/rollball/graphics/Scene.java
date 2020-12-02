package rollball.graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;

import rollball.common.P2d;
import rollball.model.Ball;
import rollball.model.GameObject;
import rollball.model.PickUpObj;
import rollball.model.World;

public final class Scene {

    private final JFrame frame = new JFrame("Roll A Ball");
    private final World world;

    public Scene(final World world) {
        this.world = world;
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth(); 
        final int sh = (int) screen.getHeight();
        frame.setSize(sw, sh);
        frame.setMinimumSize(new Dimension(sw, sh));
        frame.setResizable(false);
        frame.setLocationByPlatform(true);
        final ScenePanel panel = new ScenePanel(sw, sh);
        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void render() {
        frame.repaint();
    }

    private final class ScenePanel extends JPanel {

        private static final long serialVersionUID = 1L;
        private final int centerX;
        private final int centerY;

        private ScenePanel(final int w, final int h) {
            this.setSize(w, h);
            this.centerX = w / 2;
            this.centerY = h / 2;
        }

        public void paint(final Graphics g) {
            final Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                    RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_RENDERING, 
                    RenderingHints.VALUE_RENDER_QUALITY); 
            g2.clearRect(0, 0, this.getWidth(), this.getHeight());
            final Set<GameObject> objects = world.getSceneObjects();
            final Stroke strokeBall = new BasicStroke(4f);
            final Stroke strokePick = new BasicStroke(8f);
            objects.stream().forEach(obj -> {
                final P2d pos = obj.getCurrentPos();
                final int x = (int) Math.round(centerX + pos.getX() * 100);
                final int y = (int) Math.round(centerY - pos.getY() * 100);
                if (obj instanceof Ball) {
                        g2.setColor(Color.BLUE);
                        g2.setStroke(strokeBall);
                        g2.drawOval(x - 20, y - 20, 40, 40);
                } else if (obj instanceof PickUpObj) {
                        g2.setColor(Color.RED);
                        g2.setStroke(strokePick);
                        g2.drawRect(x - 20, y - 20, 40, 40);
                }
            });
        }
    }

}
