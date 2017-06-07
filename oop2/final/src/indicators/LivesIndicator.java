package indicators;

import arkanoid.GameLevel;
import biuoop.DrawSurface;
import sprites.Sprite;

/**
 * A LivesIndicator class.
 *
 * @author Nir Dunetz and Haim Gil.
 */
public class LivesIndicator implements Sprite {
    // Finals.
    private final int livesTextSize = 15;
    // Members.
    private Counter lives;
    private int width;
    private int height;

    /**
     * Constructor.
     *
     * @param lives Is a Counter of lives.
     */
    public LivesIndicator(Counter lives) {
        this.lives = lives;
        this.width = 800;
        this.height = 600;
    }

    /**
     * Constructor given sizes of screen and Counter of lives.
     *
     * @param lives  is a Counter of lives.
     * @param width  is the width of the screen.
     * @param height is the width of the screen.
     */
    public LivesIndicator(Counter lives, int width, int height) {
        this.lives = lives;
        this.width = width;
        this.height = height;
    }

    /**
     * Draw track of number of lives.
     *
     * @param surface is a surface to be draw on a score indicator.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        String stringLives = "Lives: " + this.lives.getValue();
        // Drawing the live in the top of the screen.
        surface.setColor(java.awt.Color.black);
        surface.drawText(this.width / 10, this.height - (livesTextSize / 2), stringLives, livesTextSize);
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
     * add to the game.
     *
     * @param g the game level.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

}
