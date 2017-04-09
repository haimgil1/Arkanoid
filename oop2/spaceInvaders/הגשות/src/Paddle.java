import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

/**
 * A Paddle class.
 *
 * @author Shurgil and barisya
 */
public class Paddle implements Sprite, Collidable {
    private Point upperLeft;
    private double width;
    private double height;
    private biuoop.KeyboardSensor keyboard;
    private double speed;
    private GameLevel gameLevel;

    /**
     * Create a new rectangle with location and width/height.
     *
     * @param upperLeft
     *            - the upper left point of the paddle.
     * @param width
     *            - the width of the paddle
     * @param height
     *            - the height of the paddle.
     * @param keyboard
     *            - the keyboard.
     * @param g
     *            - the gui.
     * @param sp
     *            speed.
     * @param gameLevel
     *            the gameLevel.
     */
    public Paddle(Point upperLeft, double width, double height,
            Object keyboard, GUI g, double sp, GameLevel gameLevel) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.keyboard = (biuoop.KeyboardSensor) keyboard;
        this.speed = sp;
        this.gameLevel = gameLevel;
    }

    /**
     * move the paddle left.
     */
    public void moveLeft() {
        if (this.upperLeft.getX() >= 20) {
            double x = this.upperLeft.getX() - this.speed;
            double y = this.upperLeft.getY();
            this.upperLeft = new Point(x, y);
        }
    }

    /**
     * move the paddle right.
     */
    public void moveRight() {
        if (this.upperLeft.getX() + this.width <= 780) {
            double x = this.upperLeft.getX() + this.speed;
            double y = this.upperLeft.getY();
            this.upperLeft = new Point(x, y);
        }
    }

    /**
     * implements Sprite.
     *
     * @param dt
     *            speed.
     */
    public void timePassed(double dt) {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        } else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }

    /**
     * implements Sprite. the draw method. draw the paddle.
     *
     * @param d
     *            - the draw surface.
     */
    public void drawOn(DrawSurface d) {
        // get the x and y coordinates of the center of the ball
        d.setColor(java.awt.Color.PINK);
        // and fill in the rectangle
        d.fillRectangle((int) this.upperLeft.getX(),
                (int) this.upperLeft.getY(), (int) this.width,
                (int) this.height);
        d.setColor(java.awt.Color.BLACK);
        d.drawRectangle((int) this.upperLeft.getX(),
                (int) this.upperLeft.getY(), (int) this.width,
                (int) this.height);
    }

    /**
     * implements Collidable.
     *
     * @return the paddle as a rectangle.
     */
    public Rectangle getCollisionRectangle() {
        Rectangle rect = new Rectangle(this.upperLeft, this.width, this.height);
        return rect;
    }

    /**
     * @param currentVelocity
     *            the current velocity.
     * @param collisionPoint
     *            the collision point.
     * @param hitter
     *            the hit ball.
     * @return the new velocity.
     */
    public Velocity hit(Ball hitter, Point collisionPoint,
            Velocity currentVelocity) {

        this.gameLevel.touchPaddle();
        hitter.removeFromGame(this.gameLevel);

        Velocity newVelocity;
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();

        Point upperLeft1 = this.upperLeft;
        Point upperLeft2 = new Point((this.upperLeft.getX() + width / 5),
                this.upperLeft.getY());
        Point upperLeft3 = new Point((upperLeft.getX() + 2 * width / 5),
                this.upperLeft.getY());
        Point upperLeft4 = new Point((upperLeft.getX() + 3 * width / 5),
                this.upperLeft.getY());
        Point upperLeft5 = new Point((this.upperLeft.getX() + 4 * width / 5),
                this.upperLeft.getY());
        Point upperLeft6 = new Point((this.upperLeft.getX() + width),
                this.upperLeft.getY());

        Line up = this.getCollisionRectangle().getUpperLine();
        // Line down = this.getCollisionRectangle().getLowerLine();
        Line right = this.getCollisionRectangle().getRightLine();
        Line left = this.getCollisionRectangle().getLeftLine();

        if (up.isPointOnLine(collisionPoint)) {
            if (collisionPoint.getX() >= upperLeft1.getX()
                    && collisionPoint.getX() <= upperLeft2.getX()) {
                double angle = 300;
                double b2 = Math.pow(dx, 2) + Math.pow(dy, 2);
                double speed2 = Math.sqrt(b2);
                newVelocity = Velocity.fromAngleAndSpeed(angle, speed2);
                return newVelocity;
            }
            if (collisionPoint.getX() >= upperLeft2.getX()
                    && collisionPoint.getX() <= upperLeft3.getX()) {
                double angle = 330;
                double b2 = Math.pow(dx, 2) + Math.pow(dy, 2);
                double speed2 = Math.sqrt(b2);
                newVelocity = Velocity.fromAngleAndSpeed(angle, speed2);
                return newVelocity;
            }
            if (collisionPoint.getX() >= upperLeft3.getX()
                    && collisionPoint.getX() <= upperLeft4.getX()) {
                newVelocity = new Velocity(dx, -dy);
                return newVelocity;
            }
            if (collisionPoint.getX() >= upperLeft4.getX()
                    && collisionPoint.getX() <= upperLeft5.getX()) {
                double angle = 30;
                double b2 = Math.pow(dx, 2) + Math.pow(dy, 2);
                double speed2 = Math.sqrt(b2);
                newVelocity = Velocity.fromAngleAndSpeed(angle, speed2);
                return newVelocity;
            }
            if (collisionPoint.getX() >= upperLeft5.getX()
                    && collisionPoint.getX() <= upperLeft6.getX()) {
                double angle = 60;
                double b2 = Math.pow(dx, 2) + Math.pow(dy, 2);
                double speed2 = Math.sqrt(b2);
                newVelocity = Velocity.fromAngleAndSpeed(angle, speed2);
                return newVelocity;
            }
        } else {
            if (left.isPointOnLine(collisionPoint)
                    || right.isPointOnLine(collisionPoint)) {
                newVelocity = new Velocity(-dx, dy);
                return newVelocity;
            }
        }
        return currentVelocity;
    }

    /**
     * Add this paddle to the game.
     *
     * @param g
     *            the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * @param p11
     *            point to move to.
     */
    public void moveTo(Point p11) {
        this.upperLeft = p11;
    }

    /**
     * @return Point place of middle paddle;
     */
    public Point getCenter() {
        int half = (int) this.width / 2;
        int x = (int) (this.upperLeft.getX() + half);
        int y = (int) (this.upperLeft.getY() - 6);
        Point p = new Point(x, y);
        return p;
    }
}
