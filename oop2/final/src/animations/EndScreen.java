package animations;

import biuoop.DrawSurface;
import indicators.Counter;

/**
 * A EndScreen class.
 *
 * @author Nir Dunetz and Haim Gil.
 */
public class EndScreen implements Animation {
    private Counter live;
    private Counter score;

    /**
     * A Constructor given the keyboard, the gui, the nmber of lives and the score.
     *
     * @param live  is a counter of lives
     * @param score is the score counter.
     */
    public EndScreen(Counter live, Counter score) {
        this.live = live;
        this.score = score;
    }

    /**
     * Doing frame of end screen, and showing the final score
     * Note: showing GameOver/Win message according to lives left and finishing levels.
     *
     * @param d is the surface.
     * @param dt is the frames per second.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        //Check if there are lives left.
        if (this.live.getValue() > 0) {
            d.drawText(d.getWidth() / 5, d.getHeight() / 2, "You Win! Your score is "
                    + this.score.getValue(), 40);
        } else {
            d.drawText(d.getWidth() / 5, d.getHeight() / 2, "Game Over. Your score is "
                    + this.score.getValue(), 40);
        }

    }

    /**
     * @return true when it should stop, false otherwise.
     */
    public boolean shouldStop() {
        return false;
    }
}
