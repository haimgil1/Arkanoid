package animations;

import biuoop.DrawSurface;

/**
 * An Animation interface.
 *
 * @author Nir Dunetz and Haim Gil.
 */
public interface Animation {
    /**
     * Doing one frame animation.
     *
     * @param d is the surface.
     * @param dt is the frames per second.
     */
    void doOneFrame(DrawSurface d, double dt);

    /**
     *
     * @return true when it should stop, false otherwise.
     */
    boolean shouldStop();
}