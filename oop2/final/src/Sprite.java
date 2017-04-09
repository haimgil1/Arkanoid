import biuoop.DrawSurface;

/**
 * the sprite interface.
 */
public interface Sprite {
    /**
     * draw the sprite to the screen.
     * @param d
     *            - the draw surface.
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     * @param dt speed.
     */
    void timePassed(double dt);
}
