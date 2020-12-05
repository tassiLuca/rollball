package rollball.graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.KeyEvent;
import java.util.Set;

import javax.swing.JPanel;

import rollball.common.P2d;
import rollball.input.MoveDown;
import rollball.input.MoveLeft;
import rollball.input.MoveRight;
import rollball.input.MoveUp;
import rollball.model.bbox.RectBoundingBox;
import rollball.model.objects.Ball;
import rollball.model.objects.GameObject;
import rollball.model.objects.PickUpObj;

public class ScenePanel extends JPanel implements KeyListener {
    private static final int RATIO_X = 1;
    private static final int RATIO_Y = 1;
    private static final int UP = 38;
    private static final int DOWN = 40;
    private static final int RIGHT = 39;
    private static final int LEFT = 37;
    private static final long serialVersionUID = 1L;
    private final int centerX;
    private final int centerY;
    private final Font scoreFont;

    private ScenePanel(final int screenWidth, final int screenHeight) {
        this.setSize(screenWidth, screenHeight);
        this.addKeyListener(this);
        this.setFocusable(true);
        this.setFocusTraversalKeysEnabled(false);
        this.requestFocusInWindow(); 
        this.scoreFont = new Font("Verdana", Font.PLAIN, 35);
        this.centerX = screenWidth / 2;
        this.centerY = screenHeight / 2;
    }

    private int getXinPixel(final P2d p) {
        return (int) Math.round(centerX + p.getX() * RATIO_X);
    }

    private int getYinPixel(final P2d p) {
        return (int) Math.round(centerY - p.getY() * RATIO_Y);
    }

    private int getDeltaXinPixel(final double dx) {
        return (int)  Math.round(dx * RATIO_X);
    }

    public void paint(final Graphics g) {
        // Graphics2D
        final Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, 
                RenderingHints.VALUE_RENDER_QUALITY); 
        g2.clearRect(0, 0, this.getWidth(), this.getHeight());
        final Stroke strokeBall = new BasicStroke(4f);
        final Stroke strokePick = new BasicStroke(6f);
        final Stroke strokeBorder = new BasicStroke(3f);
        // Paint the game boundaries
        final RectBoundingBox bbox = gameState.getWorld().getBoundingBox();
        final int x0 = getXinPixel(bbox.getUpperLeftCorner());
        final int y0 = getYinPixel(bbox.getUpperLeftCorner());
        final int x1 = getXinPixel(bbox.getBottomRightCorner());
        final int y1 = getYinPixel(bbox.getBottomRightCorner());
        g2.setColor(Color.BLACK);
        g2.setStroke(strokeBorder);
        g2.drawRect(x0, y0, x1 - x0, y1 - y0);
        // Paint the game objects: ball and pickup objects
        final Set<GameObject> objects = gameState.getWorld().getSceneObjects();
        objects.stream().forEach(obj -> {
            final P2d pos = obj.getCurrentPos();
            final int x = getXinPixel(pos);
            final int y = getYinPixel(pos);
            if (obj instanceof Ball) {
                final Ball b = (Ball) obj;
                g2.setColor(Color.BLUE);
                g2.setStroke(strokeBall);
                final int rad = getDeltaXinPixel(b.getRadius());
                g2.drawOval(x - rad, y - rad, rad * 2, rad * 2);
            } else if (obj instanceof PickUpObj) {
                final PickUpObj pick = (PickUpObj) obj;
                final int edge = getDeltaXinPixel(pick.getEdge());
                g2.setColor(Color.RED);
                g2.setStroke(strokePick);
                g2.drawRect(x - edge / 2, y - edge / 2, edge, edge);
            }
        });
        g2.setFont(scoreFont);
        g2.setColor(Color.GREEN);
        g2.drawString("SCORE " + gameState.getScore(), 500, 300);
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
