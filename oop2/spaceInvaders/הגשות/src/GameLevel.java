import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

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
    private Counter score;
    private Counter lives;
    private AnimationRunner runner;
    private boolean running;
    private biuoop.KeyboardSensor keyboard;
    private LevelInformation levelinfi;
    private double dt;
    private Paddle paddle;
    private MoveEnemy moveEnemy;
    private int enemySpeed;
    private List<Block> enemy;
    private List<Block> enemyConst;
    private int lowLineY;
    private int sec;
    private int secPlayer;
    private List<Ball> ballShots;

    /**
     * the constructor.
     *
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
        this.sec = 0;
        this.secPlayer = 0;
        this.enemySpeed = this.levelinfi.getNum();
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
     *
     * @throws IOException
     *             throw
     */
    public void initialize() throws IOException {

        this.remainingBlocks = new Counter();

        this.enemy = new ArrayList<Block>();
        this.enemyConst = new ArrayList<Block>();
        this.ballShots = new ArrayList<Ball>();

        BlockRemover checkremoveblock = new BlockRemover(this,
                this.remainingBlocks, this.enemy);
        ShieldRemover checkremoveshield = new ShieldRemover(this);
        BallRemover checkremoveball = new BallRemover(this);
        ScoreTrackingListener checkscore = new ScoreTrackingListener(this.score);

        this.addSprite(this.levelinfi.getBackground());

        for (int i = 0; i < this.levelinfi.blocks().size(); i++) {

            Block block = this.levelinfi.blocks().get(i);
            block.addHitListener(checkremoveblock);
            block.addHitListener(checkscore);
            this.remainingBlocks.increase(1);
            block.addToGame(this);
            this.enemy.add(block);
        }
        this.enemyConst.addAll(this.enemy);
        checkremoveblock.setList(this.enemy);

        this.moveEnemy = new MoveEnemy(this.enemy, this.enemySpeed);

        // shields
        this.lowLineY = 450;
        for (int y = 450; y < 480; y += 10) {
            for (int x = 100; x < 750; x += 10) {
                if (x > 250 && x < 350) {
                    continue;
                }
                if (x > 500 && x < 600) {
                    continue;
                }
                Point p1 = new Point(x, y);
                Block blockshield = new Block(p1, 10, 10, java.awt.Color.blue);
                blockshield.addHitListener(checkremoveshield);
                blockshield.setHitPoints(1);
                blockshield.addToGame(this);

            }
        }

        // borders

        Point p1 = new Point(0, 20);
        Block blockUp = new Block(p1, 800.0, 20.0, java.awt.Color.GRAY);
        blockUp.addHitListener(checkremoveball);
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
                        * this.dt, this);
        this.paddle.addToGame(this);

    }

    /**
     * Run the game -- start the animation loop.
     *
     * @throws IOException
     *             throw
     */
    public void playOneTurn() throws IOException {

        Point p11 = this.levelinfi.paddleLocation();
        this.paddle.moveTo(p11);

        this.runner.run(new CountdownAnimation(2.0, 3, this.sprites));

        this.running = true;

        this.runner.run(this);
    }

    /**
     * run the game.
     *
     * @throws IOException
     *             throw
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
     *
     * @param c
     *            a Collidable.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * remove this sprite.
     *
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
        if (this.remainingBlocks.getValue() != 0) {

            this.moveEnemy.move();
            this.moveEnemy.setList(this.enemy);

            if (this.enemy.size() != 0) {
                // return -1;

                int max = (int) this.enemy.get(0).getCollisionRectangle()
                        .getLowerLeft().getY();
                for (int i = 0; i < this.enemy.size(); i++) {
                    int temp = (int) this.enemy.get(i).getCollisionRectangle()
                            .getLowerLeft().getY();
                    if (temp > max) {
                        max = temp;
                    }
                }

                if (max > this.lowLineY) {
                    this.running = false;
                    this.lives.decrease(1);

                    this.moveEnemy.moveUp();
                    this.moveEnemy.setList(this.enemy);

                    for (int i = 0; i < this.ballShots.size(); i++) {
                        this.ballShots.get(i).removeFromGame(this);
                    }

                }
            }

        }

        if (this.remainingBlocks.getValue() <= this.levelinfi.blocks().size()
                - this.levelinfi.numberOfBlocksToRemove()) {
            System.out.println("End one turn");
            this.running = false;
        }

        if (this.remainingBlocks.getValue() == 0) {
            this.score.increase(100);
        }

        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard,
                    KeyboardSensor.SPACE_KEY, new PauseScreen()));
        }

        this.secPlayer++;
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY) && (this.secPlayer >= 21)) {
            this.secPlayer = 0;
            Point padmid = this.paddle.getCenter();
            Ball ballshot = new Ball(padmid, 3, java.awt.Color.white);
            ballshot.setVelocity(0.01, -10);
            ballshot.addToGame(this);
            ballshot.ballSetEnvironment(this.environment);
            this.ballShots.add(ballshot);
        }

        this.sec++;
        if (this.sec == 30) {
            this.sec = 0;

            List<Block> shooters = new ArrayList<Block>();
            Block minBlock = null;

            for (int k = 0; k <= 9; k++) {

                int min = -1;
                for (int i = k; i <= k + 40; i += 10) {
                    if (this.enemyConst.get(i) != null) {
                        min = (int) this.enemyConst.get(i)
                                .getCollisionRectangle().getLowerLeft().getY();
                        minBlock = this.enemyConst.get(i);
                        break;
                    }
                }

                if (min != -1) {

                    for (int i = k; i <= k + 40; i += 10) {
                        if (this.enemyConst.get(i).getCollisionRectangle()
                                .getLowerLeft().getY() > min) {
                            min = (int) this.enemyConst.get(i)
                                    .getCollisionRectangle().getLowerLeft()
                                    .getY();
                            minBlock = this.enemyConst.get(i);

                        }
                    }
                    if (minBlock != null) {
                        shooters.add(minBlock);
                    }
                }
            }

            Random rand = new Random();
            int n = rand.nextInt(shooters.size() - 1);
            Point midblock = shooters.get(n).getCenter();
            Ball ballshot = new Ball(midblock, 3, java.awt.Color.red);
            ballshot.setVelocity(0.1, 3);
            ballshot.addToGame(this);
            ballshot.ballSetEnvironment(this.environment);
            this.ballShots.add(ballshot);

        }

    }

    /**
     * check for remaining blocks.
     *
     * @return a counter of blocks.
     */
    public Counter getremainingBlocks() {
        return this.remainingBlocks;
    }

    /**
     * check for remaining lives.
     *
     * @return a counter of lives.
     */
    public Counter getlives() {
        return this.lives;
    }

    /**
     * check for num of blocks.
     *
     * @return a int of blocks.
     * @throws IOException
     *             throw
     */
    public int getnumofblocks() throws IOException {
        return this.levelinfi.blocks().size();
    }

    /**
     * check for num of blocks to remove.
     *
     * @return a int of blocks.
     * @throws IOException
     *             throw
     */
    public int getnumofblocktodestroy() throws IOException {
        return this.levelinfi.numberOfBlocksToRemove();
    }

    /**
     * touch the paddle.
     */
    public void touchPaddle() {
        this.running = false;
        this.lives.decrease(1);

        this.moveEnemy.moveUp();
        this.moveEnemy.setList(this.enemy);

        for (int i = 0; i < this.ballShots.size(); i++) {
            this.ballShots.get(i).removeFromGame(this);
        }
    }
}
