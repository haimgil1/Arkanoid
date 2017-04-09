/**
 * A BallRemover class.
 *
 * @author Shurgil and barisya
 */
public class BallRemover implements HitListener {

    private GameLevel game;

    /**
     * count the remove balls.
     *
     * @param game
     *            the game.
     */
    public BallRemover(GameLevel game) {
        this.game = game;
    }

    /**
     * // Blocks that are hit and reach 0 hit-points should be removed from the
     * game. Remember to remove this listener from the block that is being
     * removed from the game.
     *
     * @param beingHit
     *            the block
     * @param hitter
     *            the ball.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        // this.removedBalls.decrease(1);
        hitter.removeFromGame(this.game);

    }

}
