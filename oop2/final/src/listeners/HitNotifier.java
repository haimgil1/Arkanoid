package listeners;

/**
 * A HitNotifier interface.
 *
 * @author Nir Dunetz and Haim Gil.
 */
public interface HitNotifier {
    /**
     * Add hl as a listener to hit events.
     *
     * @param hl is the listener.
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl is the listener.
     */
    void removeHitListener(HitListener hl);
}