import java.io.IOException;
/**
 * A BlockCreator class.
 *
 * @author Shurgil and barisya
 */
public interface BlockCreator {
    /**
     * Create a block at the specified location.
     * @param xpos x place
     * @param ypos y place
     * @return block
     * @throws IOException throw
     */
    Block create(int xpos, int ypos) throws IOException;
}