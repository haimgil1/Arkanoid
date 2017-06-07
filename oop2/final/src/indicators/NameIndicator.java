package indicators;

import arkanoid.GameLevel;
import biuoop.DrawSurface;
import sprites.Sprite;

/**
 * A NameIndicator class.
 *
 * @author Nir Dunetz and Haim Gil.
 */
public class NameIndicator implements Sprite {
    // Finals.
    private final int nameTextSize = 15;
    // Members.
    private String nameLevel;
    private int width;
    private int height;

    /**
     * Constructor.
     *
     * @param nl Is the name of a level(String).
     */
    public NameIndicator(String nl) {
        this.nameLevel = nl;
        this.width = 800;
        this.height = 20;
    }


    /**
     * Constructor given sizes of screen and name level.
     *
     * @param nl     Is the name of a level(String).
     * @param width  Is the width of the screen.
     * @param height Is the width of the screen.
     */
    public NameIndicator(String nl, int width, int height) {
        this.nameLevel = nl;
        this.width = width;
        this.height = height;
    }

    /**
     * Draw the Level name.
     *
     * @param surface is a surface to be draw on a score indicator.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        String fullNameLevel = "Level Name: " + this.nameLevel;
        surface.setColor(java.awt.Color.black);
        // Drawing the live in the top of the screen.
        surface.drawText((int) (this.width * 0.6), this.height - (nameTextSize / 2), fullNameLevel, nameTextSize);
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
     * @param g the game level.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

}
