package arkanoid;

import accessories.Ball;
import accessories.Block;
import accessories.Paddle;
import accessories.Velocity;
import animations.AnimationRunner;
import animations.Animation;
import animations.KeyPressStoppableAnimation;
import animations.PauseScreen;
import animations.CountdownAnimation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import collidables.Collidable;
import geometry.Rectangle;
import indicators.Counter;
import indicators.LivesIndicator;
import indicators.NameIndicator;
import indicators.ScoreIndicator;
import levels.Level;
import levels.LevelInformation;
import listeners.BallRemover;
import listeners.BlockRemover;
import listeners.ScoreTrackingListener;
import sprites.Background;
import sprites.BackgroundColor;
import sprites.Sprite;
import sprites.SpriteCollection;

import java.awt.Color;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * A GameLevel class.
 *
 * @author Nir Dunetz and Haim Gil.
 */
public class GameLevel implements Animation {

    private final int widthScreen = 800;
    private final int heightScreen = 600;
    private final int widthBorder = 20;
    private final int heightScore = 20;
    // Members.
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private Counter score;
    private Counter lives;
    private AnimationRunner runner;
    private boolean running;
    private biuoop.KeyboardSensor keyboard;
    private LevelInformation levelInformation;


    /**
     * A Constructor.
     *
     * @param score            is the score of the game.
     * @param lives            is the lives of player.
     * @param runner           is AnimationRunner.
     * @param keyboard         is the keyboard.
     * @param levelInformation is the information of level.
     */
    public GameLevel(Counter score, Counter lives, AnimationRunner runner,
                     KeyboardSensor keyboard, LevelInformation levelInformation) {
        this.score = score;
        this.lives = lives;
        this.runner = runner;
        this.keyboard = keyboard;
        this.levelInformation = levelInformation;
    }

    /**
     * Adding collidable to environment.
     *
     * @param c is collidable object.
     */
    public void addCollidable(Collidable c) {
        if (this.environment == null) {
            this.environment = new GameEnvironment();
        }
        this.environment.addCollidable(c);
    }

    /**
     * Adding sprite to sprite collection.
     *
     * @param s is the sprite.
     */
    public void addSprite(Sprite s) {
        if (this.sprites == null) {
            this.sprites = new SpriteCollection();
        }
        if (this.environment == null) {
            this.environment = new GameEnvironment();
        }
        this.sprites.addSprite(s);
    }

    /**
     * Removing collidable.
     *
     * @param c Is a Collidable to be remove.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Removing Sprite.
     *
     * @param s Is a Sprite to be remove.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Initialize a new game: create Blocks, Balls, Paddle,boundaries,Indicators and add them to the game.
     */
    public void initialize() {
        this.remainingBlocks = new Counter(this.levelInformation.numberOfBlocksToRemove());
        this.remainingBalls = new Counter(0);
        ScoreTrackingListener scoreTracking = new ScoreTrackingListener(this.score);
        BlockRemover blockRemover = new BlockRemover(this, this.remainingBlocks);

        // Adding the background level.
        this.addSprite(this.levelInformation.getBackground());

        // Create ScoreIndicator and add it to the game.
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.score, widthScreen, heightScore);
        scoreIndicator.addToGame(this);

        // Create LivesIndicator and add it to the game.
        LivesIndicator livesIndicator = new LivesIndicator(this.lives, widthScreen, heightScore);
        livesIndicator.addToGame(this);

        // Create NameIndicator and add it to the game.
        NameIndicator nameIndicator = new NameIndicator(this.levelInformation.levelName(), widthScreen, heightScore);
        nameIndicator.addToGame(this);


        // Create blocks, HitListeners and add them to the game.
        for (int i = 0; i < this.levelInformation.blocks().size(); i++) {
            Block block = this.levelInformation.blocks().get(i);
            Block blockCopy = new Block(block);
            blockCopy.addHitListener(blockRemover);
            blockCopy.addHitListener(scoreTracking);
            blockCopy.addToGame(this);

        }

        // Creating the boundaries.
        this.createBoundaries();
    }

    /**
     * Setting the boundaries and create them.
     */
    public void createBoundaries() {
        // Setting the boundaries.
        Rectangle up = new Rectangle(widthBorder, heightScore, widthScreen - (widthBorder * 2), widthBorder);
        Rectangle left = new Rectangle(0, heightScore, widthBorder, heightScreen - heightScore);
        Rectangle right = new Rectangle(widthScreen - widthBorder, heightScore, widthBorder,
                heightScreen - widthBorder);
        Rectangle deathRegion = new Rectangle(0, heightScreen + 1, widthScreen, 1);

        // Creating the boundaries.
        createBlockBoundaries(up, false);
        createBlockBoundaries(left, false);
        createBlockBoundaries(right, false);
        createBlockBoundaries(deathRegion, true);
    }

    /**
     * Creating a block boundary.
     *
     * @param boundary     is the rectangle that represent the boundary.
     * @param isDeadRegion Is indicate for dead region.
     */
    public void createBlockBoundaries(Rectangle boundary, boolean isDeadRegion) {
        BallRemover ballsRemover = new BallRemover(this, this.remainingBalls);
        Background b = new BackgroundColor(Color.gray);
        // Setting the hit points to boundaries as default
        Block blockBoundary = new Block(boundary, -2);
        // Copy the block, setting the background and adding to game.
        Block blockCopy = new Block(blockBoundary);
        Map<Integer, Background> background = new TreeMap<Integer, Background>();
        background.put(new Integer(-2), b);
        blockCopy.setBackgrounds(background);
        blockCopy.addToGame(this);

        // Remove ball when hit the bottom boundary.
        if (isDeadRegion) {
            blockCopy.addHitListener(ballsRemover);
        }
    }

    /**
     * Doing One frame of the game according to the rules of the game.
     *
     * @param d  is the surface.
     * @param dt is the frames per second.
     */
    public void doOneFrame(DrawSurface d, double dt) {

        // Draw all the sprites.
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed(dt);

        // Pausing the Screen if "p" key is pressed.
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, "space", new PauseScreen()));
        }

        // Increase the score counter in 100 point if player finish a level.
        if (this.remainingBlocks.getValue() == 0) {
            this.score.increase(100);
        }

        // Decrease the live counter of a player if all balls are lost.
        if (this.remainingBalls.getValue() == 0) {
            this.lives.decrease(1);
        }

        // Check if there is no blocks or no balls left.
        if (this.remainingBlocks.getValue() == 0 || this.remainingBalls.getValue() == 0) {
            this.running = false;
        }
    }

    /**
     * Indicate to stop according to running member.
     *
     * @return boolean - True/False according to this.running.
     */
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * @return New paddle with the Data of the paddle that use in the game.
     */
    public Paddle getPaddle() {
        return new Paddle(this.keyboard, ((Level) this.levelInformation).getPaddleRectangle(),
                Color.yellow, this.levelInformation.paddleSpeed());
    }

    /**
     * Activates one turn of the game with all all the  data that related to the current level.
     */
    public void playOneTurn() {
        // Create the paddle and add to game.
        Paddle paddle = this.getPaddle();
        paddle.addToGame(this);
        List<Velocity> velocityList = this.levelInformation.initialBallVelocities();
        List<Ball> ballList = ((Level) this.levelInformation).getBallsOfLevel(levelInformation.numberOfBalls());


        // Create the Balls and add to game.
        for (int i = 0; i < velocityList.size(); i++) {

            Velocity velocity = velocityList.get(i);
            Ball ball = ballList.get(i);
            ball.setVelocity(velocity);
            ball.addToGame(this);
            ball.setGameEnvironment(this.environment);
            //Counting balls.
            this.remainingBalls.increase(1);
        }

        // Count down from 3 to 0 at the beginning of a turn within 2 seconds.
        this.runner.run(new CountdownAnimation(2.0, 3, this.sprites));
        this.running = true;
        // Use the runner to run the current animation  which is one turn of the game.
        this.runner.run(this);

        // Removing paddle when a turn one turn run is over.
        removeSprite(paddle);
        removeCollidable(paddle);
    }

    /**
     * @return the remaining blocks.
     */
    public Counter getRemainingBlocks() {
        return this.remainingBlocks;
    }

    /**
     * @return the remaining lives.
     */
    public Counter getLives() {
        return this.lives;
    }

    /**
     * @return the remaining balls.
     */
    public Counter getBallsCounter() {
        return this.remainingBalls;
    }

}

