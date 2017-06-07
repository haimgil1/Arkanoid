package levels;

import accessories.Block;
import accessories.Velocity;
import sprites.Sprite;

import java.util.List;

/**
 * A LevelInformation interface.
 *
 * @author Nir Dunetz and Haim Gil.
 */
public interface LevelInformation {
    /**
     * @return the number of balls.
     */
    int numberOfBalls();

    /**
     * The initial velocity of each ball.
     *
     * @return a list of the velocities if the ball's.
     */
    List<Velocity> initialBallVelocities();

    /**
     * @return the speed of the paddle.
     */
    int paddleSpeed();

    /**
     * @return the width of the paddle.
     */
    int paddleWidth();

    /**
     * The level name will be displayed at the top of the screen.
     *
     * @return the name of the level.
     */
    String levelName();

    /**
     * @return a sprite with the background of the level
     */
    Sprite getBackground();

    /**
     * The Blocks that make up this level, each block contains
     * its size, color and location.
     *
     * @return a list of blocks.
     */
    List<Block> blocks();

    /**
     * Number of levels that should be removed
     * before the level is considered to be "cleared".
     *
     * @return the number of balls.
     */
    int numberOfBlocksToRemove();

}
