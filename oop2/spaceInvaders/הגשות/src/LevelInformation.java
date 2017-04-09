
import java.io.IOException;
import java.util.List;

/**
 * A LevelInformation interface.
 *
 * @author Shurgil and barisya
 */
public interface LevelInformation {

    /**
     * num of blocks.
     * @return the num of blocks.
     */
    int numberOfBalls();

    /**
     * location of balls.
     * @return list of location.
     */
    List<Ball> locationOfBalls();

    /**
     * // The initial velocity of each ball. // Note that
     * initialBallVelocities().size() == numberOfBalls()
     * @return list of balls.
     */
    List<Velocity> initialBallVelocities();

    /**
     * @return the paddle speed.
     */
    int paddleSpeed();

    /**
     * @return the witdh of padle.
     */
    int paddleWidth();

    /**
     * @return point of paddle.
     */
    Point paddleLocation();

    /**
     * @return the level name. // the level name will be displayed at the top of
     *         the screen.
     */
    String levelName();

    /**
     * @return a sprite with the background of the level
     */
    Sprite getBackground();

    /**
     * // The Blocks that make up this level, each block contains // its size,
     * color and location.
     * @return list of blocks.
     * @throws IOException throw.
     */
    List<Block> blocks() throws IOException;

    /**
     * // Number of levels that should be removed // before the level is
     * considered to be "cleared". // This number should be <= blocks.size();
     * @return num of blocks.
     */
    int numberOfBlocksToRemove();

    /**
     * set name.
     * @param string the name.
     */
    void setName(String string);

    /**
     * set the num level.
     * @param i num level.
     */
    void setNum(int i);
    /**
     * @return int the level num.
     */
    int getNum();

}
