package sprites;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * A SpriteCollection class.
 *
 * @author Nir Dunetz and Haim Gil.
 */
public class SpriteCollection {

    private List<Sprite> spriteCollection;

    /**
     * Creating array of collections.
     */
    public SpriteCollection() {
        this.spriteCollection = new ArrayList<Sprite>();
    }

    /**
     * Adding a sprite to collections.
     *
     * @param s is a sprite.
     */
    public void addSprite(Sprite s) {
        this.spriteCollection.add(s);
    }

    /**
     * @param s is a Sprite.
     */
    public void removeSprite(Sprite s) {
        this.spriteCollection.remove(s);
    }

    /**
     * Turning on all the sprites time passed.
     *
     * @param dt is the frames per seconds.
     */
    public void notifyAllTimePassed(double dt) {
        for (int i = 0; i < this.spriteCollection.size(); i++) {
            (this.spriteCollection.get(i)).timePassed(dt);
        }
    }

    /**
     * Draw all the sprites.
     *
     * @param d is the surface.
     */
    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < spriteCollection.size(); i++) {
            Sprite s = spriteCollection.get(i);
            s.drawOn(d);
        }
    }
}



