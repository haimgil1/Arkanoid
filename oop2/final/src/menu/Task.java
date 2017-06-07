package menu;

/**
 * A Task interface.
 *
 * @author Nir Dunetz and Haim Gil.
 * @param <T> is void.
 */
public interface Task<T> {
    /**
     *
     * @return null.
     */
    T run();
}
