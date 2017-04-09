
import java.io.IOException;
import java.util.List;

import biuoop.KeyboardSensor;
import biuoop.DrawSurface;
import biuoop.GUI;

/**
 * A GameLevel class.
 *
 * @author Shurgil and barisya
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Counter remainingBlocks;
    private Counter ballsCounter;
    private Counter score;
    private Counter lives;
    private AnimationRunner runner;
    private boolean running;
    private biuoop.KeyboardSensor keyboard;
    private LevelInformation levelinfi;
    private double dt;
    private Paddle paddle;

    /**
     * the constructor.
     * @param levelinf
     *            the levels info.
     * @param score2
     *            the score.
     * @param lives2
     *            the lives.
     * @param gui2
     *            the gui.
     * @param keyboard2
     *            the keyboard.
     * @param runner2
     *            the animation runner.
     */
    public GameLevel(LevelInformation levelinf, Counter score2, Counter lives2,
            GUI gui2, KeyboardSensor keyboard2, AnimationRunner runner2) {
        this.levelinfi = levelinf;
        this.score = score2;
        this.lives = lives2;
        this.gui = gui2;
        this.runner = runner2;
        this.keyboard = keyboard2;
        this.dt = (double) 1 / runner2.getframesPerSecond();
        this.running = true;
    }

    /**
     * @param c
     *            - a collidable. add a collidable to the list of game
     *            environment.
     */
    public void addCollidable(Collidable c) {
        if (this.environment == null) {
            this.environment = new GameEnvironment();
        }
        this.environment.addCollidable(c);
    }

    /**
     * @param s
     *            a sprite object. add a sprite to the list of sprites.
     */
    public void addSprite(Sprite s) {
        if (this.sprites == null) {
            this.sprites = new SpriteCollection();
        }
        this.sprites.addSprite(s);
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle) and add
     * them to the game.
     * @throws IOException throw
     */
    public void initialize() throws IOException {

        this.remainingBlocks = new Counter();
        this.ballsCounter = new Counter();

        BlockRemover checkremoveblock = new BlockRemover(this,
                this.remainingBlocks);
        BallRemover checkremoveball = new BallRemover(this, this.ballsCounter);
        ScoreTrackingListener checkscore = new ScoreTrackingListener(this.score);

        this.addSprite(this.levelinfi.getBackground());

        for (int i = 0; i < this.levelinfi.blocks().size(); i++) {

            Block block = this.levelinfi.blocks().get(i);
            block.addHitListener(checkremoveblock);
            block.addHitListener(checkscore);
            this.remainingBlocks.increase(1);
            block.addToGame(this);
        }

        // borders

        Point p1 = new Point(0, 20);
        Block blockUp = new Block(p1, 800.0, 20.0, java.awt.Color.GRAY);
        blockUp.addToGame(this);
        blockUp.setHitPoints(-1);

        ScoreIndicator scoreIndicator = new ScoreIndicator(this.score);
        scoreIndicator.addToGame(this);

        LivesIndicator liveIndicator = new LivesIndicator(this.lives);
        liveIndicator.addToGame(this);

        NameIndicator nameIndicator = new NameIndicator(
                this.levelinfi.levelName());
        nameIndicator.addToGame(this);

        Point p2 = new Point(790, 40);
        Block blockRight = new Block(p2, 10.0, 560.0, java.awt.Color.GRAY);
        blockRight.addToGame(this);
        blockRight.setHitPoints(-1);

        Point p3 = new Point(0, 40);
        Block blockLeft = new Block(p3, 10.0, 560.0, java.awt.Color.GRAY);
        blockLeft.addToGame(this);
        blockLeft.setHitPoints(-1);

        Point p4 = new Point(0, 590);
        Block blockDown = new Block(p4, 800.0, 10.0, java.awt.Color.GRAY);
        blockDown.addHitListener(checkremoveball);
        blockDown.addToGame(this);
        blockDown.setHitPoints(-1);

        Point p11 = this.levelinfi.paddleLocation();
        this.paddle = new Paddle(p11, this.levelinfi.paddleWidth(), 20.0,
                gui.getKeyboardSensor(), this.gui, this.levelinfi.paddleSpeed()
                        * this.dt);
        this.paddle.addToGame(this);
    }

    /**
     * Run the game -- start the animation loop.
     * @throws IOException throw
     */
    public void playOneTurn() throws IOException {

        Point p11 = this.levelinfi.paddleLocation();
        this.paddle.moveTo(p11);

        List<Velocity> velocityList = this.levelinfi.initialBallVelocities();
        List<Ball> ballList = this.levelinfi.locationOfBalls();

        for (int i = 0; i < velocityList.size(); i++) {

            Velocity velocity = velocityList.get(i);
            double dx = velocity.getDx();
            double dy = velocity.getDy();
            Ball ball1 = ballList.get(i);
            ball1.setVelocity(dx * this.dt, dy * this.dt);
            ball1.addToGame(this);
            ball1.ballSetEnvironment(this.environment);
            this.ballsCounter.increase(1);
            /*
             * Velocity velocity = velocityList.get(i); Ball ball1 =
             * ballList.get(i); ball1.setVelocity(velocity);
             * ball1.addToGame(this);
             * ball1.ballSetEnvironment(this.environment);
             * this.ballsCounter.increase(1);
             */
        }

        this.runner.run(new CountdownAnimation(2.0, 3, this.sprites));

        this.running = true;

        this.runner.run(this);
    }

    /**
     * run the game.
     * @throws IOException throw
     */
    public void run() throws IOException {
        while (this.lives.getValue() != 0) {
            this.playOneTurn();
            this.lives.decrease(1);
        }
        System.out.println("The End of the Game");
        return;
    }

    /**
     * remove block.
     * @param c
     *            a Collidable.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * remove this sprite.
     * @param s
     *            a sprite to reemove.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt2) throws IOException {
        this.dt = dt2;

        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed(dt);

        if (this.remainingBlocks.getValue() <= this.levelinfi.blocks().size()
                - this.levelinfi.numberOfBlocksToRemove()
                || this.ballsCounter.getValue() == 0) {
            System.out.println("End one turn");
            this.running = false;
        }

        if (this.remainingBlocks.getValue() == 0) {
            this.score.increase(100);
        }

        if (this.ballsCounter.getValue() == 0) {
            this.lives.decrease(1);
        }

        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard,
                    KeyboardSensor.SPACE_KEY, new PauseScreen()));
        }
    }

    /**
     * check for remaining blocks.
     * @return a counter of blocks.
     */
    public Counter getremainingBlocks() {
        return this.remainingBlocks;
    }

    /**
     * check for remaining lives.
     * @return a counter of lives.
     */
    public Counter getlives() {
        return this.lives;
    }

    /**
     * check for remaining balls.
     * @return a counter of balls.
     */
    public Counter getballsCounter() {
        return this.ballsCounter;
    }

    /**
     * check for num of blocks.
     * @return a int of blocks.
     * @throws IOException
     *             throw
     */
    public int getnumofblocks() throws IOException {
        return this.levelinfi.blocks().size();
    }

    /**
     * check for num of blocks to remove.
     * @return a int of blocks.
     * @throws IOException
     *             throw
     */
    public int getnumofblocktodestroy() throws IOException {
        return this.levelinfi.numberOfBlocksToRemove();
    }

}
