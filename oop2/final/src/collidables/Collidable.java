package collidables;

import accessories.Ball;
import accessories.Velocity;
import geometry.Point;
import geometry.Rectangle;

/**
 * A Collidable interface.
 *
 * @author Nir Dunetz and Haim Gil.
 */
public interface Collidable {
    /**
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     *
     * @param hitter          is a ball.
     * @param collisionPoint  is the point where collision occurred.
     * @param currentVelocity is the current velocity of a ball.
     * @return the new velocity expected after the hit (based on the force the object inflicted on us).
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}

