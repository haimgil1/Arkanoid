package sprites;

import biuoop.DrawSurface;

/**
 * A Sprite class.
 *
 * @author Nir Dunetz and Haim Gil.
 */
public interface Sprite {
    /**
     * Draw the sprite to the screen.
     *
     * @param d is the surface.
     */
    void drawOn(DrawSurface d);

    /**
     * Notify the sprite that time has passed.
     *
     * @param dt Is the frame per second.
     */
    void timePassed(double dt);
}