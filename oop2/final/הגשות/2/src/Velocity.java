/**
 * A Velocity class.
 *
 * @author Shurgil and barisya
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * construct a ball's velocity given the dx and dy coordinates.
     *
     * @param dx
     *            the x coordinate
     * @param dy
     *            the y coordinate
     */
    public Velocity(double dx, double dy) {
        if (dx == 0) {
            dx = 0.01;
        }
        if (dy == 0) {
            dy = 0.01;
        }
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * @return the dx of the velocity
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * @return the dy of the velocity
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * @param p
     *            the point which we need to apply the new position on
     * @return the new point with position (x+dx, y+dy)
     */
    public Point applyToPoint(Point p) {
        double x = this.dx + p.getX(); // dx + x
        double y = this.dy + p.getY(); // dy + y
        Point newpoint = new Point(x, y);
        return newpoint;
    }

    /**
     * @param p
     *            the point which we need to apply the new position on
     * @return the new point with position (x+dx, y+dy) with an epsilon to
     *         correct the corners.
     */
    public Point applyToPointCorner(Point p) {
        double x = this.dx * 0.1 + p.getX(); // dx + x
        double y = this.dy * 0.1 + p.getY(); // dy + y
        Point newpoint = new Point(x, y);
        return newpoint;
    }

    /**
     * @param angle
     *            the angle of the velocity
     * @param speed
     *            the speed of the velocity
     * @return a new velocity with the dx and dy coordinates we build from the
     *         angle and speed
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = Math.cos(Math.toRadians(angle) - Math.PI / 2) * speed;
        double dy = Math.sin(Math.toRadians(angle) - Math.PI / 2) * speed;
        return new Velocity(dx, dy);
    }
}
