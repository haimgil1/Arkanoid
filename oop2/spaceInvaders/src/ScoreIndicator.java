import biuoop.DrawSurface;
/**
 * A ScoreIndicator class.
 *
 * @author Shurgil and barisya
 */
public class ScoreIndicator implements Sprite {
    private Counter score;

    /**
     * the constructor.
     * @param score the score counter.
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(java.awt.Color.WHITE);
        surface.fillRectangle(200, 0, 400, 20);

        int w = 350;
        int h = 15;

        String st2 = "Score: " + this.score.getValue();
        int c = 15;
        surface.setColor(java.awt.Color.black);
        surface.drawText(w, h, st2, c);

    }

    @Override
    public void timePassed(double dt) {

    }

    /**
     * add to the game.
     * @param g the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

}
