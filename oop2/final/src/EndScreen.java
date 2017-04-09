import biuoop.DrawSurface;
/**
 * A EndScreen class.
 *
 * @author Shurgil and barisya
 */
public class EndScreen implements Animation {
    private Counter live;
    private Counter score;

    /**
     * the constructor.
     * @param live2 the counter of lives
     * @param score2 the counter of score.
     */
    public EndScreen(Counter live2, Counter score2) {
        this.live = live2;
        this.score = score2;
    }

    /**
     * @param d the drawsurface.
     * @param dt speed
     */
    public void doOneFrame(DrawSurface d, double dt) {
        if (this.live.getValue() > 0) {
            d.drawText(200, d.getHeight() / 2, "You Win! Your score is "
                    + this.score.getValue(), 32);
        } else {
            d.drawText(200, d.getHeight() / 2, "Game Over. Your score is "
                    + this.score.getValue(), 32);
        }
    }

    /**
     * @return boolean if should stop.
     */
    public boolean shouldStop() {
        return false;
    }
}
