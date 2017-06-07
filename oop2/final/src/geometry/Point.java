package geometry;

/**
 * A Point class.
 *
 * @author Nir Dunetz and Haim Gil.
 */
public class Point {
    // Members.
    private double x;
    private double y;

    /**
     * Constructs a point given x and y coordinates.
     *
     * @param x is the x coordinate.
     * @param y is the y coordinate.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Measuring distance between two points.
     *
     * @param other is a point.
     * @return distance of this point to the other point.
     */
    public double distance(Point other) {
        double dx = this.x - other.getX();
        double dy = this.y - other.getY();
        return Math.sqrt((dx * dx) + (dy * dy));
    }

    /**
     * Checking weather two points are equal.
     *
     * @param other is a point.
     * @return true if the points are equals and false otherwise.
     */
    public boolean equals(Point other) {
        if (this.x == other.getX() && this.y == other.getY()) {
            return true;
        }
        return false;
    }

    /**
     * @return the x coordinate.
     */
    public double getX() {
        return this.x;
    }

    /**
     * @return the y coordinate.
     */
    public double getY() {
        return this.y;
    }
}
