
import java.io.IOException;

import biuoop.DrawSurface;
import biuoop.GUI;

/**
 * A Ball class.
 *
 * @author Shurgil and barisya
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private biuoop.Sleeper sleeper;

    /**
     * constructor of the class. get the gui and run the game.
     *
     * @param gui2
     *            the gui
     *            @param framesPerSecond2 how many frame
     */
    public AnimationRunner(GUI gui2, int framesPerSecond2) {
        this.gui = gui2;
        this.framesPerSecond = framesPerSecond2;
        biuoop.Sleeper sleeper2 = new biuoop.Sleeper();
        this.sleeper = sleeper2;
    }

    /**
     * run the game.
     * @param animation
     *            the animation.
     * @throws IOException trow
     */
    public void run(Animation animation) throws IOException {

        int millisecondsPerFrame = 1000 / framesPerSecond;

        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            double dt = (double) 1 / this.framesPerSecond;
            animation.doOneFrame(d, dt);

            this.gui.show(d);

            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**
     * @return num of frame.
     */
    public int getframesPerSecond() {
        return this.framesPerSecond;
    }
}
