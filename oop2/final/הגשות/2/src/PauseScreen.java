import biuoop.DrawSurface;

/**
 * A PauseScreen class.
 *
 * @author Shurgil and barisya
 */
public class PauseScreen implements Animation {

    /**
     * the constructor.
     */
    public PauseScreen() {
    }

    /**
     * do one frame.
     * @param d
     *            the darw surface.
     *            @param dt speed
     */
    public void doOneFrame(DrawSurface d, double dt) {
        d.drawText(160, d.getHeight() / 2, "paused -- press space to continue",
                32);
    }

    /**
     * @return booleab if should stop.
     */
    public boolean shouldStop() {
        return true;
    }
}
