import biuoop.DrawSurface;
/**
 * A HighScoresAnimation class.
 *
 * @author Shurgil and barisya
 */
public class HighScoresAnimation implements Animation {

    private HighScoresTable scores;

    /**
     * the constructor.
     *            @param scores score
     */
    public HighScoresAnimation(HighScoresTable scores) {
        this.scores = scores;
    }

    /**
     * @param d
     *            the drawsurface.
     *            @param dt speed
     */
    public void doOneFrame(DrawSurface d, double dt) {

        for (int i = 0; i < this.scores.getHighScores().size(); i++) {
            d.drawText(200, i * 32 + 300, this.scores.getHighScores().get(i)
                    .toString(), 32);
        }
    }

    /**
     * @return boolean if should stop.
     */
    public boolean shouldStop() {
        return true;
    }

}
