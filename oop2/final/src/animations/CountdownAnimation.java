package animations;

import biuoop.DrawSurface;
import sprites.SpriteCollection;

import java.awt.Color;

/**
 * The CountdownAnimation will display the given gameScreen,
 * for numOfSeconds seconds, and on top of them it will show
 * a countdown from countFrom back to 1, where each number will
 * appear on the screen for (numOfSeconds / countFrom) seconds, before
 * it is replaced with the next one.
 * A CountdownAnimation class.
 *
 * @author Nir Dunetz and Haim Gil.
 */
public class CountdownAnimation implements Animation {
    // Members.
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private boolean endOfCount;
    private boolean firstFrame;
    private long millisecond;

    /**
     * Constructor given the number of seconds, the number to count from and the game screen.
     *
     * @param numOfSeconds is num of seconds of the counting.
     * @param countFrom    is indicate Which number to starting count from.
     * @param gameScreen   is the sprite collection.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom,
                              SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.endOfCount = false;
        this.firstFrame = true;
    }

    /**
     * Doing one frame animation.
     *
     * @param d is the surface.
     * @param dt is the frames per second.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        if (this.firstFrame) {
            this.millisecond = System.currentTimeMillis();
            this.firstFrame = false;
        }
        this.gameScreen.drawAllOn(d);

        double currentTimeMillis = (double) (System.currentTimeMillis() - this.millisecond);
        double pause = this.numOfSeconds / (double) this.countFrom;
        int currentNumber = (int) ((double) (1 + this.countFrom) - currentTimeMillis / (pause * 1000.0));
        String countingSt = "" + currentNumber + "...";

        // Display the number.
        if (currentNumber > 0) {
            d.setColor(Color.WHITE);
            d.drawText(d.getWidth() / 2, d.getHeight() / 2, "" + countingSt, 50); // Middle of the screen.
        }

        // End is over and draw "GO".
        if (currentTimeMillis > this.numOfSeconds * 1000.0) {
            countingSt = "GO";
            d.setColor(Color.WHITE);
            d.drawText(d.getWidth() / 2, d.getHeight() / 2, "" + countingSt, 50); // Middle of the screen.
            this.endOfCount = true;
        }

    }

    /**
     * @return true when it should stop, false otherwise.
     */
    public boolean shouldStop() {
        return this.endOfCount;
    }
}
