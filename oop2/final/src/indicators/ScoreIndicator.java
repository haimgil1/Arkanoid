package indicators;

import arkanoid.GameLevel;
import biuoop.DrawSurface;
import sprites.Sprite;

import java.awt.Color;

/**
 * A ScoreIndicator class.
 *
 * @author Nir Dunetz and Haim Gil.
 */
public class ScoreIndicator implements Sprite {
    // Finals.
    private final int scoreTextSize = 15;
    // Members.
    private Counter score;
    private int height;
    private int width;

    /**
     * Constructor.
     *
     * @param score Is a Counter of points score.
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
        this.width = 800;
        this.height = 20;
    }

    /**
     * Constructor.
     *
     * @param score  Is a Counter of points score.
     * @param width  Is the width of the screen.
     * @param height Is the width of the screen.
     */
    public ScoreIndicator(Counter score, int width, int height) {
        this.score = score;
        this.width = width;
        this.height = height;
    }

    /**
     * Draw track of score.
     *
     * @param surface is a surface to be draw on a score indicator.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.white);
        surface.fillRectangle(0, 0, this.width, this.height);
        String stringScore = "Score: " + this.score.getValue();
        // Drawing the live in the top of the screen.
        surface.setColor(Color.black);
        surface.drawText((this.width / 3), this.height - (scoreTextSize / 2), stringScore, scoreTextSize);
    }

    /**
     * Notify the sprite that time has passed.
     *
     * @param dt is the frames per seconds.
     */
    @Override
    public void timePassed(double dt) {

    }

    /**
     * Add to the game.
     *
     * @param g the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

}
