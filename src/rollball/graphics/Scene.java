package rollball.graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;

import rollball.common.P2d;
import rollball.input.MoveDown;
import rollball.input.MoveLeft;
import rollball.input.MoveRight;
import rollball.input.MoveUp;
import rollball.input.Controller;
import rollball.model.Ball;
import rollball.model.GameObject;
import rollball.model.PickUpObj;
import rollball.model.World;

/**
 * [VIEW]
 * Graphics management.
 */
public final class Scene {

    private final JFrame frame = new JFrame("Roll A Ball");
    private final World world;
    private Controller controller;

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

    public void setInputController(final Controller c) {
        this.controller = c;
    }

    public void render() {
        frame.repaint();
    }

    private final class ScenePanel extends JPanel implements KeyListener {

        private static final int OBJ_DIM = 40;
        private static final int UP = 38;
        private static final int DOWN = 40;
        private static final int RIGHT = 39;
        private static final int LEFT = 37;
        private static final long serialVersionUID = 1L;
        private final int centerX;
        private final int centerY;

        private ScenePanel(final int w, final int h) {
            this.setSize(w, h);
            this.addKeyListener(this);
            this.setFocusable(true);
            this.setFocusTraversalKeysEnabled(false);
            this.requestFocusInWindow(); 
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
                        g2.drawOval(x, y, OBJ_DIM, OBJ_DIM);
                } else if (obj instanceof PickUpObj) {
                        g2.setColor(Color.RED);
                        g2.setStroke(strokePick);
                        g2.drawRect(x, y, OBJ_DIM, OBJ_DIM);
                }
            });
        }

        @Override
        public void keyPressed(final KeyEvent e) {
            if (e.getKeyCode() == UP) {
                controller.notifyCommand(new MoveUp());
            } else if (e.getKeyCode() == DOWN) {
                controller.notifyCommand(new MoveDown());
            } else if (e.getKeyCode() == RIGHT) {
                controller.notifyCommand(new MoveRight());
            } else if (e.getKeyCode() == LEFT) {
                controller.notifyCommand(new MoveLeft());
            }
        }

        @Override
        public void keyTyped(final KeyEvent e) { }

        @Override
        public void keyReleased(final KeyEvent e) { }
    }

}
