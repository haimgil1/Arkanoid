
import java.io.IOException;

import biuoop.DrawSurface;

/**
 * A Animation class.
 *
 * @author Shurgil and barisya
 */

public interface Animation {
    /**
     * do one frame every time.
     *
     * @param d
     *            get the surface.
     *            @param dt the dt speed
     * @throws IOException trow
     */
    void doOneFrame(DrawSurface d, double dt) throws IOException;

    /**
     * check every time if the level should stop.
     *
     * @return boolean if should stop.
     */

    boolean shouldStop();
}
