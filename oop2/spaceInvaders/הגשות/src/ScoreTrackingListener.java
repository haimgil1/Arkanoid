/**
 * A ScoreTrackingListener class.
 *
 * @author Shurgil and barisya
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * the constructor.
     * @param scoreCounter the conter of score.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * notify hit event.
     * @param beingHit the block.
     * @param hitter the ball.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitPoints() == 0) {
            this.currentScore.increase(100);
        }
    }

    /**
     * get hundred for finish level.
     */
    public void gethundred() {
        this.currentScore.increase(100);
    }

}
