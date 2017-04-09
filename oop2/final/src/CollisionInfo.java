/**
 * A CollisionInfo class.
 *
 * @author Shurgil and barisya
 */
public class CollisionInfo {
    private Point pointOfCollision;
    private Collidable c;

    /**
     * @param p
     *            - a point.
     * @param collidable
     *            - one collidable.
     *
     * @author Shurgil and barisya
     */
    public CollisionInfo(Collidable collidable, Point p) {

        this.pointOfCollision = p;
        this.c = (Collidable) collidable;
    }

    /**
     * @return the point at which the collision occurs.
     */

    public Point collisionPoint() {
        return this.pointOfCollision;

    }

    /**
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.c;
    }
}
