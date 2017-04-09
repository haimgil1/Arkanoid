import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import biuoop.DrawSurface;

/**
 * A Block class.
 *
 * @author Shurgil and barisya
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Point upperLeft;
    private double width;
    private double height;
    private int hitPoints;
    private java.awt.Color color;
    private List<HitListener> hitListeners;
    private char symbol;
    private java.awt.Color strokecolor;
    private Image img;
    private Map<Integer, Background> mapBackground;

    /**
     * Create a new rectangle with location and width/height.
     *
     * @param w
     *            - width of the block.
     * @param h
     *            - height of the block.
     * @param c
     *            - color of the block.
     * @param upPoint
     *            - up left point of the block.
     */
    public Block(Point upPoint, double w, double h, java.awt.Color c) {
        this.upperLeft = upPoint;
        this.width = w;
        this.height = h;
        this.color = c;
        this.hitListeners = new ArrayList<HitListener>();
        this.strokecolor = java.awt.Color.black;
        this.mapBackground = new TreeMap<Integer, Background>();
    }

    /**
     * Create a new block.
     */
    public Block() {
        this.hitListeners = new ArrayList<HitListener>();
        this.strokecolor = java.awt.Color.black;
        this.mapBackground = new TreeMap<Integer, Background>();
    }

    /**
     * Create a hit points.
     *
     * @param num
     *            the number to set in the hit.
     */
    public void setHitPoints(int num) {
        this.hitPoints = num;
    }

    /**
     * @return the hit points.
     */
    public int getHitPoints() {
        return this.hitPoints;
    }

    /**
     * @return the block by new rectangle.
     */
    public Rectangle getCollisionRectangle() {
        Rectangle rect = new Rectangle(this.upperLeft, this.width, this.height);
        return rect;
    }

    /**
     * @param currentVelocity
     *            - the current velocity.
     * @param collisionPoint
     *            - the collision point. draw the block on it.
     * @param hitter
     *            the ball.
     * @return the new velocity.
     */
    public Velocity hit(Ball hitter, Point collisionPoint,
            Velocity currentVelocity) {
        Velocity newVelocity;
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();

        Line up = this.getCollisionRectangle().getUpperLine();
        Line down = this.getCollisionRectangle().getLowerLine();
        Line right = this.getCollisionRectangle().getRightLine();
        Line left = this.getCollisionRectangle().getLeftLine();

        if (up.isPointOnLine(collisionPoint)
                || down.isPointOnLine(collisionPoint)) {
            newVelocity = new Velocity(dx, -dy);
            this.hitPoints--;
            this.notifyHit(hitter);
            return newVelocity;
        } else {
            if (left.isPointOnLine(collisionPoint)
                    || right.isPointOnLine(collisionPoint)) {
                newVelocity = new Velocity(-dx, dy);
                this.hitPoints--;
                this.notifyHit(hitter);
                return newVelocity;
            }
            System.out.print("no crash ");
            this.notifyHit(hitter);
            return currentVelocity;
        }
    }

    /**
     * @param surface
     *            - a draw on surface. draw the block on it.
     */
    public void drawOn(DrawSurface surface) {

        Background backg;
        Integer bigint = Integer.valueOf(this.hitPoints);
        if (this.mapBackground.containsKey(bigint)) {
            backg = this.mapBackground.get(bigint);
        } else {
            backg = new Background(this.color, this.img, (int) this.width,
                    (int) this.height);
        }

        backg.setplace((int) this.upperLeft.getX(), (int) this.upperLeft.getY());
        backg.drawOn(surface);
        if (this.strokecolor != null) {
            surface.setColor(this.strokecolor);
            surface.drawRectangle((int) this.upperLeft.getX(),
                    (int) this.upperLeft.getY(), (int) this.width,
                    (int) this.height);
        }

        /*
         * int w = (int) (this.upperLeft.getX() + this.width / 2); int h = (int)
         * (this.upperLeft.getY() + this.height / 2); String st2; if
         * (this.hitPoints < 1) { st2 = "X"; }else { st2 =
         * String.valueOf(this.hitPoints); } int c = 15;
         * surface.setColor(java.awt.Color.green); surface.drawText(w, h,
         * st2,c);
         */
    }

    /**
     * a sprite interface method.
     * @param dt the speed.
     */
    public void timePassed(double dt) {

    }

    /**
     * @param g
     *            - a game. add to the game this block to the sprite list and
     *            the the colldiable list of the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * remove from the game.
     * @param game
     *            the game level.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
        game.removeCollidable(this);
    }

    /**
     * add to the listener list.
     * @param hl
     *            the hitlistener.
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * remove from the list.
     * @param hl
     *            the listtener
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);

    }

    /**
     * notify the hit occur.
     * @param hitter
     *            the hit ball.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(
                this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
/**
 * @param c set the symblo.
 */
    public void setsymbol(char c) {
        this.symbol = c;
    }
/**
 * @return the symbol of the block.
 */
    public char getsymbol() {
        return this.symbol;
    }
/**
 * @param h set the height;
 */
    public void setheight(int h) {
        this.height = h;
    }
/**
 * @param w the width.
 */
    public void setwidth(int w) {
        this.width = w;
    }
/**
 * @param c get the color of the block
 */
    public void setfillcolor(java.awt.Color c) {
        this.color = c;
    }
/**
 * @param c the color of stroke
 */
    public void setstrokecolor(java.awt.Color c) {
        this.strokecolor = c;
    }
/**
 * @return the stroke color
 */
    public java.awt.Color getstrokecolor() {
        return this.strokecolor;
    }
/**
 * @param s image
 * @throws IOException trow image not found
 */
    public void setimage(Image s) throws IOException {
        this.img = s;
    }
/**
 * @param x place x
 * @param y place y
 */
    public void setXY(int x, int y) {
        Point p1 = new Point(x, y);
        this.upperLeft = p1;
    }
/**
 * @param num num
 * @param c color
 */
    public void setFillcoByNum(int num, java.awt.Color c) {
        Integer a = Integer.valueOf(num);
        Background backg = new Background(c, null, (int) this.width,
                (int) this.height);
        this.mapBackground.put(a, backg);
    }
/**
 * @param num which num fill
 * @param img2 which img the image
 */
    public void setFillimgByNum(int num, Image img2) {
        Integer a = Integer.valueOf(num);
        Background backg = new Background(null, img2, (int) this.width,
                (int) this.height);
        this.mapBackground.put(a, backg);
    }
/**
 * @param mapBackground2 the map of fill color
 */
    public void setMapFill(Map<Integer, Background> mapBackground2) {
        this.mapBackground = mapBackground2;

    }
}
