package sprites;

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * A BackgroundColor class.
 *
 * @author Nir Dunetz and Haim Gil.
 */
public class BackgroundColor implements Sprite, Background {
    // Member.
    private Color color;

    /**
     * Constructor getting Color.
     *
     * @param color Is the color to be set.
     */
    public BackgroundColor(Color color) {
        this.color = color;
    }


    /**
     * draw the sprite to the screen.
     *
     * @param d - the draw surface.
     */
    @Override
    public void drawOn(DrawSurface d) {

        d.setColor(this.color);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());

    }

    /**
     * Fill rectangle according to color x,y,width and height.
     *
     * @param d Is surface.
     * @param x Is x coordinate Position to be set.
     * @param y Is y coordinate Position to be set.
     * @param w Is width.
     * @param h Is height.
     */
    @Override
    public void drawOnBackground(DrawSurface d, int x, int y, int w, int h) {
        d.setColor(this.color);
        d.fillRectangle(x, y, w, h);
    }

    /**
     * Notify the sprite that time has passed.
     *
     * @param dt Is the frame per second.
     */
    public void timePassed(double dt) {

    }
}
