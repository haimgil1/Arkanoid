import java.awt.Image;

import biuoop.DrawSurface;

/**
 * A BackgroundLevel1 class.
 *
 * @author Shurgil and barisya
 */
public class Background implements Sprite {
    private java.awt.Color color;
    private Image imageName;
    private int x;
    private int y;
    private int w;
    private int h;
/**
 * @param color color
 * @param imageName namw
 * @param w w
 * @param h h
 */
    public Background(java.awt.Color color, Image imageName, int w, int h) {
        this.color = color;
        this.imageName = imageName;
        this.w = w;
        this.h = h;
    }

    /**
     * draw the sprite to the screen.
     * @param d
     *            - the draw surface.
     */
    public void drawOn(DrawSurface d) {

        if (this.imageName != null) {
            d.drawImage(this.x, this.y, this.imageName);
        }

        if (this.color != null) {
            d.setColor(this.color);
            d.fillRectangle(this.x, this.y, this.w, this.h);
        }
    }

    /**
     * notify the sprite that time has passed.
     * @param dt the speed
     */
    public void timePassed(double dt) {

    }
/**
 * @param xp x
 * @param yp y
 */
    public void setplace(int xp, int yp) {
        this.x = xp;
        this.y = yp;

    }
}