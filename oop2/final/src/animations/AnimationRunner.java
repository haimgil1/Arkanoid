package animations;

import biuoop.DrawSurface;
import biuoop.GUI;

/**
 * An AnimationRunner class.
 *
 * @author Nir Dunetz and Haim Gil.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private biuoop.Sleeper sleeper;

    /**
     * Constructor given a gui.
     *
     * @param gui is a gui to run the animation on.
     */
    public AnimationRunner(GUI gui) {
        this.gui = gui;
        this.framesPerSecond = 60;
        this.sleeper = new biuoop.Sleeper();
    }

    /**
     *  Constructor given a gui.
     *
     * @param gui is a gui to run the animation on.
     * @param framesPerSecond is the frame per second.
     */
    public AnimationRunner(GUI gui, int framesPerSecond) {
        this.gui = gui;
        this.framesPerSecond = framesPerSecond;
        this.sleeper = new biuoop.Sleeper();
    }

    /**
     * Running the animation.
     *
     * @param animation is the animation.
     */
    public void run(Animation animation) {

        int millisecondsPerFrame = 1000 / framesPerSecond;

        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = this.gui.getDrawSurface();
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
}
