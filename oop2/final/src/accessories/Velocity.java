package accessories;

import collidables.Collidable;
import geometry.Point;
import geometry.Rectangle;

/**
 * A Velocity class.
 *
 * @author Nir Dunetz and Haim Gil.
 */
public class Velocity {
    // Members.
    private double dx;
    private double dy;

    /**
     * Constructs a velocity by coordinates.
     *
     * @param dx represent the change in position on the `x` axe.
     * @param dy represent the change in position on the `y` axe.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;

    }

    /**
     * Setting the velocity according to the angle and speed.
     *
     * @param angle is the angle.
     * @param speed is the speed.
     * @return the velocity.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = Math.cos(Math.toRadians(angle) - Math.PI / 2) * speed;
        double dy = Math.sin(Math.toRadians(angle) - Math.PI / 2) * speed;
        return new Velocity(dx, dy);
    }

    /**
     * @return the x coordinate.
     */
    public double getDx() {

        return this.dx;
    }

    /**
     * @return the y coordinate.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * @return the speed of the velocity.
     */
    public double getSpeed() {
        double hypotenuse = Math.pow(this.dx, 2) + Math.pow(this.dy, 2);
        return Math.sqrt(hypotenuse);
    }

    /**
     * Taking a point with position (x,y) and return a new point with position (x+dx, y+dy).
     *
     * @param p is a point.
     * @param dt is the frame per second.
     * @return the new point.
     */
    public Point applyToPoint(Point p, double dt) {
        double newX = (this.dx * dt) + p.getX();
        double newY = (this.dy * dt) + p.getY();
        return new Point(newX, newY);
    }

    /**
     * Taking a collision point with position (x,y) and return a new point very close to
     * collision point in accordance to where the hit occurred.
     *
     * @param collisionP is the collision point.
     * @param hit        is the hit.
     * @return the new point.
     */
    public Point applyAlmostToPoint(Point collisionP, Collidable hit) {
        Rectangle rec = hit.getCollisionRectangle();
        double newX = collisionP.getX();
        double newY = collisionP.getY();

        // Checks on which line of the collidable was the hit and update close coordinate.
        if (rec.getUpperLine().ifPointOnLine(collisionP)) {
            newY -= 2;
        } else if (rec.getBottomLine().ifPointOnLine(collisionP)) {
            newY += 2;
        } else if (rec.getLeftLine().ifPointOnLine(collisionP)) {
            newX -= 2;
        } else if (rec.getRightLine().ifPointOnLine(collisionP)) {
            newX += 2;
        }
        return new Point(newX, newY);
    }
}