package listeners;

import accessories.Ball;
import accessories.Block;

/**
 * A HitListener interface.
 *
 * @author Nir Dunetz and Haim Gil.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     *
     * @param beingHit is the block.
     * @param hitter   is the Ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}