/**
 * A Point class.
 *
 * @author Shurgil and barisya
 */
public class Point {
    private double x;
    private double y;

    /**
     * Constructs a point given x and y coordinates.
     *
     * @param x
     *            the x coordinate
     * @param y
     *            the y coordinate
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return the x coordinate
     */
    public double getX() {
        return this.x;
    }

    /**
     * @return the y coordinate
     */
    public double getY() {
        return this.y;
    }

    /**
     * @param other
     *            a point to measure the distance to
     * @return the distance to the other point
     */
    public double distance(Point other) {
        double dx = this.x - other.getX();
        double dy = this.y - other.getY();
        return Math.sqrt((dx * dx) + (dy * dy));
    }

    /**
     * @param other
     *            a point to check if it equals to
     * @return true if they're equal and false if they don't.
     */
    public boolean equals(Point other) {
        if (this.y == other.y && this.x == other.x) {
            return true;
        }
        return false;
    }
}