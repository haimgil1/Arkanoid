package accessories;

import arkanoid.GameLevel;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import collidables.Collidable;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import sprites.Sprite;

import java.awt.Color;

/**
 * A Paddle class.
 *
 * @author Nir Dunetz and Haim Gil.
 */
public class Paddle implements Sprite, Collidable {
    // Members.
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rec;
    private java.awt.Color color;
    private int widthStart;
    private int widthEnd;
    private double paddleSpeed;

    /**
     * Constructs a paddle given a keyboard, a rectangle a color and a paddle speed.
     *
     * @param keyboard    is the keyboard.
     * @param rec         is a rectangle.
     * @param color       is a color.
     * @param paddleSpeed is the speed of the paddle.
     */
    public Paddle(Object keyboard, Rectangle rec, java.awt.Color color, int paddleSpeed) {
        this.keyboard = (biuoop.KeyboardSensor) keyboard;
        this.rec = rec;
        this.color = color;
        this.widthStart = 20;
        this.widthEnd = 780;
        this.paddleSpeed = paddleSpeed;
    }

    /**
     * Constructs a paddle given a keyboard, a rectangle, a color, a screen's size and a paddle speed.
     *
     * @param keyboard    is the keyboard.
     * @param rec         is a rectangle.
     * @param color       is a color.
     * @param widthScreen is the left paddle border.
     * @param widthBorder is the right paddle border.
     * @param paddleSpeed is the speed of the paddle.
     */
    public Paddle(Object keyboard, Rectangle rec, Color color, int widthScreen, int widthBorder, int paddleSpeed) {
        this.keyboard = (KeyboardSensor) keyboard;
        this.rec = rec;
        this.color = color;
        this.widthStart = widthBorder;
        this.widthEnd = widthScreen - widthBorder;
        this.paddleSpeed = paddleSpeed;
    }

    /**
     * Moving the paddle left.
     *
     * @param dt is the frame per second.
     */
    public void moveLeft(double dt) {
        // Moving the paddle's x coordinate 12 steps backward.
        double x = this.rec.getUpperLeft().getX() - (this.paddleSpeed * dt);
        this.rec = new Rectangle(x, this.rec.getUpperLeft().getY(), this.rec.getWidth(), this.rec.getHeight());
        // The paddle is located at the left boundary.
        if (this.rec.getUpperLeft().getX() < this.widthStart) {
            this.rec = new Rectangle(this.widthStart, this.rec.getUpperLeft().getY(),
                    this.rec.getWidth(), this.rec.getHeight());
        }
    }

    /**
     * Moving the paddle right.
     *
     * @param dt is the frame per second.
     */
    public void moveRight(double dt) {
        // Moving the paddle's x coordinate 12 steps forward.
        double x = this.rec.getUpperLeft().getX() + (this.paddleSpeed * dt);
        this.rec = new Rectangle(x, this.rec.getUpperLeft().getY(), this.rec.getWidth(), this.rec.getHeight());
        // The paddle is located at the right boundary.
        if (this.rec.getUpperRight().getX() > widthEnd) {
            this.rec = new Rectangle(this.widthEnd - this.rec.getWidth(), this.rec.getUpperLeft().getY(),
                    this.rec.getWidth(), this.rec.getHeight());
        }
    }

    /**
     * Moving the paddle when the keyboard is pressed.
     *
     * @param dt is the frames per seconds.
     */
    public void timePassed(double dt) {
        if (this.keyboard.isPressed(this.keyboard.LEFT_KEY)) {
            this.moveLeft(dt);
        } else if (this.keyboard.isPressed(this.keyboard.RIGHT_KEY)) {
            this.moveRight(dt);
        }
    }

    /**
     * Drawing the paddle.
     *
     * @param d is the surface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color); // Setting the color.
        int x = (int) this.rec.getUpperLeft().getX();
        int y = (int) this.rec.getUpperLeft().getY();
        int width = (int) this.rec.getWidth();
        int height = (int) this.rec.getHeight();
        //Drawing the rectangle.
        d.fillRectangle(x, y, width, height);
        d.setColor(Color.black); // Frames are in black.
        // Drawing the frame's rectangle.
        d.drawRectangle(x, y, width, height);
    }

    /**
     * @return the rectangle.
     */
    public Rectangle getCollisionRectangle() {
        return this.rec;
    }

    /**
     * Notify to paddle that we collided with that was an hit, and check where it occurred.
     *
     * @param hitter          is is a ball.
     * @param collisionPoint  is the collision point.
     * @param currentVelocity is the current velocity.
     * @return the new velocity after hitting.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();

        // Lines of paddle.
        Line upPaddle = this.getCollisionRectangle().getUpperLine();
        Line leftPaddle = this.getCollisionRectangle().getLeftLine();
        Line rightPaddle = this.getCollisionRectangle().getRightLine();

        // Checks if here is a hit on paddle and return velocity in accordance.
        if (upPaddle.ifPointOnLine(collisionPoint)) {
            return hitUp(collisionPoint, currentVelocity);
        } else if (leftPaddle.ifPointOnLine(collisionPoint)
                || rightPaddle.ifPointOnLine(collisionPoint)) {
            dx = -dx;
        }

        return new Velocity(dx, dy);

    }

    /**
     * Check on which part of the paddle was a hit.
     *
     * @param collisionPoint  is the collision point.
     * @param currentVelocity is the current velocity.
     * @return the new velocity after hitting.
     */
    public Velocity hitUp(Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        double speed = currentVelocity.getSpeed();
        double x = this.rec.getUpperLeft().getX();
        double y = this.rec.getUpperLeft().getY();
        double lengthRegion = this.rec.getWidth() / 5;

        // Divide the paddle to 5 region.
        Line[] region = new Line[5];
        for (int i = 0; i < 5; i++) {
            region[i] = new Line(x, y, x + lengthRegion, y);
            x += lengthRegion;
        }

        // Check on which part of the paddle was the collision point and return velocity in accordance.
        if (region[0].ifPointOnLine(collisionPoint)) {
            return currentVelocity.fromAngleAndSpeed(300, speed);
        } else if (region[1].ifPointOnLine(collisionPoint)) {
            return currentVelocity.fromAngleAndSpeed(330, speed);
        } else if (region[2].ifPointOnLine(collisionPoint)) {
            return new Velocity(dx, -dy);
        } else if (region[3].ifPointOnLine(collisionPoint)) {
            return currentVelocity.fromAngleAndSpeed(30, speed);
        } else if (region[4].ifPointOnLine(collisionPoint)) {
            return currentVelocity.fromAngleAndSpeed(60, speed);
        }
        return currentVelocity;
    }

    /**
     * Add this paddle to the game.
     *
     * @param g is the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}

