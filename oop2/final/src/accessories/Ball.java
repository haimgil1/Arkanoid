package accessories;

import arkanoid.GameEnvironment;
import arkanoid.GameLevel;
import biuoop.DrawSurface;
import collidables.CollisionInfo;
import geometry.Line;
import geometry.Point;
import sprites.Sprite;

import java.awt.Color;

/**
 * A Ball class.
 *
 * @author Nir Dunetz and Haim Gil.
 */
public class Ball implements Sprite {
    // Members.
    private Point center;
    private int radius;
    private java.awt.Color color;
    private Velocity velocity;
    private GameEnvironment gameEn;

    /**
     * Constructs a ball given the point of the center.
     *
     * @param center is the center of the ball.
     * @param r      is the radius.
     * @param color  is the color.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
    }

    /**
     * Constructs a ball given the coordinate of the center.
     *
     * @param x     is the x  coordinate.
     * @param y     is the y  coordinate.
     * @param r     is the radius.
     * @param color is the color.
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
    }


    /**
     * @return the x center coordinate.
     */
    public int getX() {
        return (int) center.getX();
    }

    /**
     * @return the y center coordinate.
     */
    public int getY() {
        return (int) center.getY();
    }

    /**
     * @return the  center.
     */
    public Point getCenter() {
        return this.center;
    }

    /**
     * @return the size of the radius.
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * @return the color.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * @return the size of the radius.
     */
    public GameEnvironment getGameEnvironment() {
        return this.gameEn;
    }

    /**
     * Setting the game environment.
     *
     * @param ge is the game environment.
     */
    public void setGameEnvironment(GameEnvironment ge) {
        this.gameEn = ge;
    }

    /**
     * @param dt is the frames per seconds.
     * @return the trajectory.
     */
    public Line getTrajectory(double dt) {
        // Returning the trajectory according to the velocity.
        return new Line(this.getX(), this.getY(), (this.getX() + (this.velocity.getDx() * dt * 1.5)),
                (this.getY() + (this.velocity.getDy() * dt * 1.5)));
    }

    /**
     * @param x is the x coordinate velocity.
     * @param y is the y coordinate velocity.
     */
    public void setVelocity(double x, double y) {
        this.velocity = new Velocity(x, y);
    }

    /**
     * @return the velocity.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Setting the velocity.
     *
     * @param v is the velocity.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Moving the ball one step.
     *
     * @param dt is the frames per seconds.
     */
    public void moveOneStep(double dt) {
        // Getting the collision information.
        CollisionInfo collisionInfo = this.gameEn.getClosestCollision(this.getTrajectory(dt));

        if (collisionInfo == null) { // No collision.
            this.center = this.getVelocity().applyToPoint(this.center, dt);
        } else {
            // There is collision. Moving the ball almost to collision point and update velocity.
            this.center = this.velocity.applyAlmostToPoint(collisionInfo.collisionPoint(),
                    collisionInfo.collisionObject());
            Velocity newV = collisionInfo.collisionObject().hit(this, collisionInfo.collisionPoint(), this.velocity);
            this.setVelocity(newV);

        }
    }

    /**
     * Draw the ball on the given DrawSurface.
     *
     * @param surface is the surface.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color); // Setting the color.
        // Casting from double to int.
        int x = (int) this.center.getX();
        int y = (int) this.center.getY();
        surface.fillCircle(x, y, this.getSize()); // Filling the ball.
        surface.setColor(Color.black);
        surface.drawCircle(x, y, this.getSize()); // Drawing the ball.
    }


    /**
     *
     * @param dt is the frames per seconds.
     */
    public void timePassed(double dt) {
        this.moveOneStep(dt);
    }

    /**
     * Adding the ball to the game.
     *
     * @param g is the game environment.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * Removing the ball from the game.
     *
     * @param g is the game environment.
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}

