package sprites;

import biuoop.DrawSurface;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

/**
 * A BackgroundImage class.
 *
 * @author Nir Dunetz and Haim Gil.
 */
public class BackgroundImage implements Sprite, Background {
    // Members.
    private String imageByString;
    private Image image;

    /**
     * Constructor getting image.
     *
     * @param imageS Is String representing the name of image file.
     */
    public BackgroundImage(String imageS) {
        this.imageByString = imageS;
        InputStream is = null;

        try {
            //read image from file.
            is = ClassLoader.getSystemClassLoader().getResourceAsStream(this.imageByString);
            //Getting image.
            this.image = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace(System.err);

        } finally {
            try {
                if (is != null) {
                    //Close file.
                    is.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file");
            }
        }
    }

    /**
     * Draw the sprite to the screen with image.
     *
     * @param d - the draw surface.
     */

    public void drawOn(DrawSurface d) {
        d.drawImage(0, 0, this.image); // draw the image
    }

    /**
     * Draw image according to image and  x,y coordinate position.
     *
     * @param d Is surface.
     * @param x Is x coordinate Position to be set.
     * @param y Is y coordinate Position to be set.
     * @param w Is width.
     * @param h Is height.
     */
    @Override
    public void drawOnBackground(DrawSurface d, int x, int y, int w, int h) {
        d.drawImage(x, y, this.image);
    }

    /**
     * Notify the sprite that time has passed.
     *
     * @param dt Is the frame per second.
     */
    public void timePassed(double dt) {

    }
}
