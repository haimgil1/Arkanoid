
import java.io.IOException;
/**
 * A Task class.
 *
 * @author Shurgil and barisya
 * @param <T> value
 */
public interface Task<T> {
    /**
     * @return value
     * @throws IOException throw.
     */
    T run() throws IOException;
/**
 * new stop, intialize the should stop.
 */
    void newstop();
}