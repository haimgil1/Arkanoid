package animations;

import biuoop.DrawSurface;
import sprites.Background;
import sprites.BackgroundColor;

import java.awt.Color;

/**
 * A PauseScreen class.
 *
 * @author Nir Dunetz and Haim Gil.
 */
public class PauseScreen implements Animation {

    /**
     * A Constructor.
     */
    public PauseScreen() {

    }

    /**
     * Drawing the pause screen animation.
     *
     * @param d  is the surface.
     * @param dt is the frames per second.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        // Setting the background.
        Background b = new BackgroundColor(Color.gray);
        b.drawOn(d);

        // Setting the pause screen.
        d.setColor(Color.WHITE);
        d.fillCircle(d.getWidth() / 2, d.getHeight() / 2, 98);
        d.setColor(Color.BLACK);
        d.fillCircle(d.getWidth() / 2, d.getHeight() / 2, 90);
        d.setColor(new Color(57, 134, 196));
        d.fillRectangle(350, 250, 40, 100);
        d.fillRectangle(410, 250, 40, 100);

        // Setting the message.
        String str = "paused -- press space to continue";
        d.setColor(Color.BLACK);
        d.drawText(150, 500, str, 32);
    }

    /**
     * @return true when it should stop, false otherwise.
     */
    public boolean shouldStop() {
        return true;
    }
}