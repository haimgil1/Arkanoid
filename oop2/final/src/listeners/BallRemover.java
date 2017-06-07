package listeners;

import accessories.Ball;
import accessories.Block;
import arkanoid.GameLevel;
import indicators.Counter;

/**
 * A BallRemover class.
 * A BallRemover is in charge of removing balls from the game, as well as keeping count
 * of the number of balls that remain.
 *
 * @author Nir Dunetz and Haim Gil.
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;

    /**
     * Construct a BallRemover given the game and a counter represent the number of balls to remove.
     *
     * @param game         is the game.
     * @param removedBalls is a counter represent the number of block to remove.
     */
    public BallRemover(GameLevel game, Counter removedBalls) {
        this.game = game;
        this.remainingBalls = removedBalls;
    }

    /**
     * Remove the ball when hit the block.
     *
     * @param beingHit is the block.
     * @param hitter   is the Ball that's doing the hitting.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.remainingBalls.decrease(1);
    }
}
