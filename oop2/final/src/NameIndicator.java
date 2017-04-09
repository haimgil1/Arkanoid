import biuoop.DrawSurface;
/**
 * A NameIndicator class.
 *
 * @author Shurgil and barisya
 */
public class NameIndicator implements Sprite {
    private String name;

    /**
     * the constructor.
     * @param str the string.
     */
    public NameIndicator(String str) {
        this.name = str;
    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(java.awt.Color.WHITE);
        surface.fillRectangle(600, 0, 200, 20);

        int w = 600;
        int h = 15;

        String st2 = "Level Name: " + this.name;
        int c = 15;
        surface.setColor(java.awt.Color.black);
        surface.drawText(w, h, st2, c);

    }

    @Override
    public void timePassed(double dt) {

    }

    /**
     * add to the game.
     * @param g the game level.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

}
