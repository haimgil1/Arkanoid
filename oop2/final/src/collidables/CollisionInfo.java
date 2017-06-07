package collidables;

import geometry.Point;

/**
 * A CollisionInfo class.
 *
 * @author Nir Dunetz and Haim Gil.
 */
public class CollisionInfo {
    // Members.
    private Point collisionPoint;
    private Collidable collidable;

    /**
     * Constructs a CollisionInfo given the point of the collision and the collidable.
     *
     * @param collisionPoint is the point where collision occurred.
     * @param collidable     is the object that was collision.
     */
    public CollisionInfo(Point collisionPoint, Collidable collidable) {
        this.collisionPoint = collisionPoint;
        this.collidable = collidable;
    }

    /**
     * @return the point where collision occurred.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * @return the collidable object.
     */
    public Collidable collisionObject() {
        return this.collidable;
    }
}
