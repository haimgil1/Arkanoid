package listeners;

import accessories.Ball;
import accessories.Block;
import arkanoid.GameLevel;
import indicators.Counter;

/**
 * A BlockRemover class.
 * A BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 *
 * @author Nir Dunetz and Haim Gil.
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * Construct a BlockRemover given the game and a counter represent the number of block to remove.
     *
     * @param game          is the game.
     * @param removedBlocks is a counter represent the number of block to remove.
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * Blocks that are hit and reach 0 hit-points should be removed
     * from the game.
     *
     * @param beingHit is the block.
     * @param hitter   is the Ball that's doing the hitting.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitPoints() == 0) {
            beingHit.removeFromGame(this.game);
            beingHit.removeHitListener(this);
            this.remainingBlocks.decrease(1);
        }
    }
}
