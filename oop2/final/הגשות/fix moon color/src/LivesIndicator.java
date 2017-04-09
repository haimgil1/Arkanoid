import biuoop.DrawSurface;
/**
 * A LivesIndicator class.
 *
 * @author Shurgil and barisya
 */
public class LivesIndicator implements Sprite {
    private Counter lives;

    /**
     * the constructor.
     * @param live the counte.
     */
    public LivesIndicator(Counter live) {
        this.lives = live;
    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(java.awt.Color.WHITE);
        surface.fillRectangle(0, 0, 200, 20);

        int w = 100;
        int h = 15;

        String st2 = "Lives: " + this.lives.getValue();
        int c = 15;
        surface.setColor(java.awt.Color.black);
        surface.drawText(w, h, st2, c);

    }

    @Override
    public void timePassed(double dt) {

    }

    /**
     * add to the game.
     * @param g the gamelevel.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

}
