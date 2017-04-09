import java.util.ArrayList;
import java.util.List;
import biuoop.DrawSurface;

/**
 * class spritecollection.
 */
public class SpriteCollection {

    private List<Sprite> spriteList;

    /**
     * get a new list.
     */
    public SpriteCollection() {
        this.spriteList = new ArrayList<Sprite>();
    }

    /**
     * @param s
     *            - a sprite. add to the sprite list.
     */
    public void addSprite(Sprite s) {
        this.spriteList.add(s);
    }

    /**
     * @param s
     *            - a sprite. remove the sprite from the list.
     */
    public void removeSprite(Sprite s) {
        this.spriteList.remove(s);
    }

    /**
     * call timePassed() on all sprites.
     * @param dt speed.
     */
    public void notifyAllTimePassed(double dt) {
        for (int i = 0; i < this.spriteList.size(); i++) {
            Sprite s = (Sprite) this.spriteList.get(i);
            s.timePassed(dt);
        }
    }

    /**
     * @param d
     *            - the draw surface. call drawOn(d) on all sprites.
     */
    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < spriteList.size(); i++) {
            Sprite s = (Sprite) spriteList.get(i);
            s.drawOn(d);
        }
}
}
