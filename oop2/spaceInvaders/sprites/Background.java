package sprites;

import biuoop.DrawSurface;

/**
 * A Background interface.
 *
 * @author Nir Dunetz and Haim Gil.
 */
public interface Background extends Sprite {
    /**
     * Draw on surface getting  x,y position and height and width.
     *
     * @param d Is surface.
     * @param x Is x coordinate Position to be set.
     * @param y Is y coordinate Position to be set.
     * @param w Is width.
     * @param h Is height.
     */
    void drawOnBackground(DrawSurface d, int x, int y, int w, int h);

}
