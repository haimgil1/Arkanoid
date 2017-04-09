
/**
 * A ShieldRemover class.
 *
 * @author Shurgil and barisya
 */

public class ShieldRemover implements HitListener {
    private GameLevel game;

    /**
     * the constructor.
     * @param game
     *            the game.
     */
    public ShieldRemover(GameLevel game) {
        this.game = game;
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
            beingHit.removeFromGame(this.game);
            beingHit.removeHitListener(this);
            hitter.removeFromGame(this.game);
        }

    }
}
