
/**
 * A BlockRemover class. a BlockRemover is in charge of removing blocks from the
 * game, as well as keeping count of the number of blocks that were removed.
 *
 * @author Shurgil and barisya
 */

public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter removedBlocks;

    /**
     * the constructor.
     * @param game
     *            the game.
     * @param removedBlocks
     *            the counter.
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.removedBlocks = removedBlocks;
    }

    /**
     * // Blocks that are hit and reach 0 hit-points should be removed from the
     * game. Remember to remove this listener from the block that is being
     * removed from the game.
     * @param beingHit
     *            the block.
     * @param hitter
     *            the hit.
     */
    public void hitEvent(Block beingHit, Ball hitter) {

        if (beingHit.getHitPoints() == 0) {
            this.removedBlocks.decrease(1);
            beingHit.removeFromGame(this.game);
            beingHit.removeHitListener(this);
        }

    }
}
