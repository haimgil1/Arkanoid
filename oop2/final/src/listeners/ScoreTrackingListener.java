package listeners;

import accessories.Ball;
import accessories.Block;
import indicators.Counter;

/**
 * A ScoreTrackingListener class.
 *
 * @author Nir Dunetz and Haim Gil.
 */
public class ScoreTrackingListener implements HitListener {

    //Member.
    private Counter currentScore;

    /**
     * Constructor.
     *
     * @param scoreCounter Is a Counter of points score .
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * Increase  score according to blocks that hit.
     *
     * @param beingHit is the block.
     * @param hitter   is the Ball that's doing the hitting.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        // Increase 5 point for a hit.
        this.currentScore.increase(5);

        // Increase 10 point if a hit remove a block from game.
        if (beingHit.getHitPoints() == 0) {
            this.currentScore.increase(10);
        }
    }
}