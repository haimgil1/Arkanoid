import biuoop.DrawSurface;

/**
 * A CountdownAnimation class. The CountdownAnimation will display the given
 * gameScreen, for numOfSeconds seconds, and on top of them it will show a
 * countdown from countFrom back to 1, where each number will appear on the
 * screen for (numOfSeconds / countFrom) seconds, before it is replaced with the
 * next one.
 *
 * @author Shurgil and barisya
 */

public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private boolean stop;
    private boolean first;

    /**
     * the constructor.
     * @param numOfSeconds
     *            num of seconds.
     * @param countFrom
     *            start from..
     * @param gameScreen
     *            the sprite collection.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom,
            SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.stop = false;
        this.first = true;

    }

    /**
     * @param d
     *            the drawsurface.
     *            @param dt speed
     */
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(java.awt.Color.WHITE);
        d.fillRectangle(0, 20, 800, 20);
        d.setColor(java.awt.Color.black);
        d.drawRectangle(0, 20, 800, 20);

        this.gameScreen.drawAllOn(d);

        int w = 800 / 2;
        int h = 35;

        String st2 = "" + this.countFrom + "...";
        int c = 15;
        d.setColor(java.awt.Color.black);
        d.drawText(w, h, st2, c);

        if (this.first) {
            this.first = false;
        } else {
            biuoop.Sleeper sleeper3 = new biuoop.Sleeper();
            sleeper3.sleepFor((long) 3 / (long) (this.numOfSeconds) * 1000);
        }

        if (this.countFrom == 0) {
            this.stop = true;
        }
        this.countFrom--;
    }

    /**
     * check if need to stop all.
     * @return boolean if should stop.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
