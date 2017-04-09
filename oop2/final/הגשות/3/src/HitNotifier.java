/**
 * A HitNotifier interface.
 *
 * @author Shurgil and barisya
 */
public interface HitNotifier {
    /**
     * Add hl as a listener to hit events.
     * @param hl the listtener.
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     * @param hl the listtener.
     */
    void removeHitListener(HitListener hl);
}
