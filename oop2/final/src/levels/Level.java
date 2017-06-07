package levels;

import accessories.Ball;
import accessories.Block;
import accessories.Velocity;
import geometry.Point;
import sprites.Background;
import sprites.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * A Level class.
 *
 * @author Nir Dunetz and Haim Gil.
 */
public class Level implements LevelInformation {

    private String levelName;
    private List<Velocity> ballVelocities;
    private int paddleSpeed;
    private int paddleWidth;
    private Background background;
    private List<Block> blocks;
    private int numBlocks;

    /**
     * A constructor.
     *
     * @param name           is the level's name to be set.
     * @param ballVelocities is the velocities's ball to be set.
     * @param paddleSpeed    is the speed of the paddle to be set.
     * @param paddleWidth    is the width of the paddle to be set.
     * @param numBlocks      is the number of blocks to be set.
     * @param background     is the background's level to be set.
     */
    public Level(String name, List<Velocity> ballVelocities, int paddleSpeed, int paddleWidth, int numBlocks,
                 Background background) {
        this.levelName = name;
        this.ballVelocities = ballVelocities;
        this.paddleWidth = paddleWidth;
        this.paddleSpeed = paddleSpeed;
        this.numBlocks = numBlocks;
        this.blocks = new ArrayList<Block>();
        this.background = background;

    }

    /**
     * @return the speed of the paddle.
     */
    public int paddleSpeed() {
        return paddleSpeed;
    }

    /**
     * @return the width of the paddle.
     */
    public int paddleWidth() {
        return paddleWidth;
    }

    /**
     * @return the height of the paddle.
     */
    public int paddleHeight() {
        return 15;
    }

    /**
     * @return the number of balls.
     */
    public int numberOfBalls() {
        return initialBallVelocities().size();
    }

    /**
     * The initial velocity of each ball.
     *
     * @return a list of the velocities if the ball's.
     */
    public List<Velocity> initialBallVelocities() {
        return this.ballVelocities;
    }


    /**
     * The level name will be displayed at the top of the screen.
     *
     * @return the name of the level.
     */
    public String levelName() {
        return levelName;
    }

    /**
     * @return a Background with the background of the level
     */
    public Sprite getBackground() {
        return (Sprite) background;
    }

    /**
     * The Blocks that make up this level, each block contains
     * its size, color and location.
     *
     * @return a list of blocks.
     */
    public List<Block> blocks() {
        return this.blocks;
    }

    /**
     * Number of levels that should be removed
     * before the level is considered to be "cleared".
     *
     * @return the number of balls.
     */
    public int numberOfBlocksToRemove() {
        return this.numBlocks;
    }

    /**
     * Adding block list to the level.
     *
     * @param blocksList is a block list.
     */
    public void addBlocks(List<Block> blocksList) {
        this.blocks.addAll(blocksList);
    }

    /**
     * @return the point's paddle.
     */
    public Point paddlePointLocation() {
        return new Point((800 - this.paddleWidth()) / 2, 600 - this.paddleHeight() - 10);
    }

    /**
     * @return the rectangle's paddle.
     */
    public geometry.Rectangle getPaddleRectangle() {
        return new geometry.Rectangle(this.paddlePointLocation(), this.paddleWidth(), this.paddleHeight());
    }

    /**
     * Adding the balls to list and return it.
     *
     * @param numberOfBalls is the number of balls.
     * @return list of balls.
     */
    public List<Ball> getBallsOfLevel(int numberOfBalls) {
        List<Ball> balls = new ArrayList<Ball>();
        for (int i = 0; i < numberOfBalls; i++) {
            Ball ball = new Ball(400, 600 - this.paddleHeight() - 20, 3, Color.WHITE);
            balls.add(ball);
        }
        return balls;
    }
}
