package rollball.graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;

import rollball.common.P2d;
import rollball.model.Ball;
import rollball.model.GameObject;
import rollball.model.PickUpObj;
import rollball.model.World;

public class Scene {

    private final JFrame frame;
    private final World world;

    public Scene(final World world, final int w, final int h) {
        frame = new JFrame("Roll A Ball");
        frame.setSize(w, h);
        frame.setMinimumSize(new Dimension(w, h));
        frame.setResizable(false);
        this.world = world;
        final ScenePanel panel = new ScenePanel(w, h);
        frame.getContentPane().add(panel);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent e) {
                System.exit(-1);
            }
            public void windowClosed(final WindowEvent e) {
                System.exit(-1);
            }
        });
        frame.pack();
        frame.setVisible(true);
    }

    public final void render() {
        frame.repaint();
    }

    public class ScenePanel extends JPanel {

        private static final long serialVersionUID = 1L;
        private final int centerX;
        private final int centerY;

        public ScenePanel(final int w, final int h) {
            this.setSize(w, h);
            this.centerX = w / 2;
            this.centerY = h / 2;
        }

        public final void paint(final Graphics g) {
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
                        g2.drawOval(x-20, y-20, 40, 40);
                } else if (obj instanceof PickUpObj) {
                        g2.setColor(Color.RED);
                        g2.setStroke(strokePick);
                        g2.drawRect(x-20, y-20, 40, 40);
                }
            });
        }
    }

}
