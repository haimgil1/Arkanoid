package arkanoid;

import collidables.Collidable;
import collidables.CollisionInfo;
import geometry.Line;
import geometry.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * A GameEnvironment class.
 *
 * @author Nir Dunetz and Haim Gil.
 */
public class GameEnvironment {
    private List<Collidable> collidablesList;

    /**
     * Creating new list of cilldables.
     */
    public GameEnvironment() {
        this.collidablesList = new ArrayList<Collidable>();
    }

    /**
     * Add the given collidable to the environment.
     *
     * @param c is a collidiable.
     */
    public void addCollidable(Collidable c) {
        this.collidablesList.add(c);
    }

    /**
     * @param c is a collidable.
     */
    public void removeCollidable(Collidable c) {
        this.collidablesList.remove(c);
    }

    /**
     * Checks if there is intersection of line with collidables and find the closest one.
     *
     * @param trajectory is line that represent trajectory of object.
     * @return the closest collidable object and the point where collidable occurred as CollisionInfo.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Collidable closestCollidable;
        Point closestHit, tmpHit = null;
        double closestDist, tmpDist;
        Collidable collidable;
        int i = 0;

        // Checking if there is any collidables.
        if (this.collidablesList.isEmpty()) { // No collision.
            return null;
        }

        // Finding the first collidable that collision.
        while (tmpHit == null && i < this.collidablesList.size()) {
            collidable = this.collidablesList.get(i);
            tmpHit = trajectory.closestIntersectionToStartOfLine(collidable.getCollisionRectangle());
            i++;
        }

        if (tmpHit == null) { // No collision.
            return null;
        }
        // Initialize the closest collision with  first collision.
        closestHit = tmpHit;
        closestCollidable = this.collidablesList.get(i - 1);
        closestDist = trajectory.start().distance(closestHit);

        // Finding the others collidable that collision.
        for (int j = i; j < this.collidablesList.size(); j++) {
            collidable =  this.collidablesList.get(j);

            // Check if there is collision.
            if (!collidable.getCollisionRectangle().isIntersectWithLine(trajectory)) {
                continue;
            }
            // Calculate distance from start of line to collision.
            tmpHit = trajectory.closestIntersectionToStartOfLine(collidable.getCollisionRectangle());
            tmpDist = trajectory.start().distance(tmpHit);

            // Checks if collision that was found closer than the closest one that found before.
            if (tmpDist < closestDist) {
                closestHit = tmpHit;
                closestCollidable = this.collidablesList.get(j);

            }
        }
        return new CollisionInfo(closestHit, closestCollidable);
    }
}

