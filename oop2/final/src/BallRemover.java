/**
 * A BallRemover class.
 *
 * @author Shurgil and barisya
 */
public class BallRemover implements HitListener {

    private GameLevel game;
    private Counter removedBalls;

    /**
     * count the remove balls.
     * @param game the game.
     * @param removedBalls the counter.
     */
    public BallRemover(GameLevel game, Counter removedBalls) {
        this.game = game;
        this.removedBalls = removedBalls;
    }

    /**
     * // Blocks that are hit and reach 0 hit-points should be removed
     * from the game. Remember to remove this listener from the block
     * that is being removed from the game.
     * @param beingHit the block
     * @param hitter the ball.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.removedBalls.decrease(1);
        hitter.removeFromGame(this.game);
    }

}
