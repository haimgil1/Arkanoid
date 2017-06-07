package read;

import accessories.Block;

/**
 * Created by nirdunetz on 5.6.2016.
 */
public interface BlockCreator {
    /**
     * Create a block at the specified location.
     *
     * @param xpos is the x position.
     * @param ypos is the y position.
     * @return the block with xpos and ypos.
     */
    Block create(int xpos, int ypos);
}