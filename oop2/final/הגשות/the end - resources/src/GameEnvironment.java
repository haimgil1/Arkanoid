import java.util.ArrayList;
import java.util.List;

/**
 * A GameEnvironment class.
 *
 * @author Shurgil and barisya
 */
public class GameEnvironment {

    private List<Collidable> collidableList;

    /**
     * construct a GameEnvironment.
     *
     * @param
     */
    public GameEnvironment() {
        this.collidableList = new ArrayList<Collidable>();
    }

    /**
     * @return the list of collidable.
     */
    public List<Collidable> getGameEnvironment() {
        return this.collidableList;
    }

    /**
     * add the given collidable to the environment.
     *
     * @param c
     *            - a collidable.
     */
    public void addCollidable(Collidable c) {
        collidableList.add(c);
    }

    /**
     * remove the given collidable from the environment.
     *
     * @param c
     *            - a collidable.
     */
    public void removeCollidable(Collidable c) {
        collidableList.remove(c);
    }

    /**
     * Assume an object moving from line.start() to line.end(). If this object
     * will not collide with any of the collidables in this collection, return
     * null. Else, return the information about the closest collision that is
     * going to occur.
     *
     * @param trajectory
     *            - the trajectory of the ball.
     * @return the CollisionInfo.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        int i = 0;
        if (this.collidableList.isEmpty()) {
            return null;
        }
        Collidable rect1 = (Collidable) this.collidableList.get(i);
        Point p1 = trajectory.closestIntersectionToStartOfLine(rect1
                .getCollisionRectangle());

        while (p1 == null && i < this.collidableList.size() - 1) {
            i++;
            rect1 = (Collidable) this.collidableList.get(i);
            p1 = trajectory.closestIntersectionToStartOfLine(rect1
                    .getCollisionRectangle());
        }
        if (p1 == null) {
            return null;
        }

        // there is an intersection
        double min = p1.distance(trajectory.start());

        Collidable rect2;
        Point p2;
        double temp;

        for (; i < this.collidableList.size(); i++) {

            rect2 = (Collidable) this.collidableList.get(i);
            p2 = trajectory.closestIntersectionToStartOfLine(rect2
                    .getCollisionRectangle());
            while (p2 == null && i < this.collidableList.size() - 1) {
                i++;
                rect2 = (Collidable) this.collidableList.get(i);
                p2 = trajectory.closestIntersectionToStartOfLine(rect2
                        .getCollisionRectangle());
            }
            if (p2 == null) {
                continue;
            }
            temp = p2.distance(trajectory.start());

            if (temp < min) {
                rect1 = rect2;
                p1 = p2;
                min = temp;
            }
        }

        CollisionInfo c = new CollisionInfo(rect1, p1);
        return c;
    }

}
