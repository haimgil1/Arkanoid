import java.util.List;

import biuoop.DrawSurface;

/**
 * A Line class.
 *
 * @author Shurgil and barisya
 */
public class Line {
    private Point start;
    private Point end;

    /**
     * constructs a line given two points.
     *
     * @param start
     *            the start of the line
     * @param end
     *            the end of the line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * constructs a line given two pairs of x and y coordinates.
     *
     * @param x1
     *            the x coordinate of the first point
     * @param y1
     *            the y coordinate of the first point
     * @param x2
     *            the x coordinate of the second point
     * @param y2
     *            the y coordinate of the second point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * @return the length of the line
     */
    public double length() {
        return this.start.distance(end); // by the distance between two points
    }

    /**
     * @return the middle point of the line
     */
    public Point middle() {
        double x1 = this.start.getX();
        double y1 = this.start.getY();
        double x2 = this.end.getX();
        double y2 = this.end.getY();
        // return the average of the x and y coordinates
        // of both points of the line
        Point mid = new Point(((x1 + x2) / 2), ((y1 + y2) / 2));
        return mid;
    }

    /**
     * @return the start point of the line
     */
    public Point start() {
        return this.start;
    }

    /**
     * @return the end point of the line
     */
    public Point end() {
        return this.end;
    }

    /**
     * @param p1
     *            a point to check.
     * @return if the point on the line
     */
    public boolean isPointOnLine(Point p1) {

        double minx = Math.min(this.start().getX(), this.end().getX());
        double maxx = Math.max(this.start().getX(), this.end().getX());
        double miny = Math.min(this.start().getY(), this.end().getY());
        double maxy = Math.max(this.start().getY(), this.end().getY());

        if ((p1.getX() >= minx - 0.0001) && (p1.getX() <= maxx + 0.0001)
                && (p1.getY() >= miny - 0.0001) && (p1.getY() <= maxy + 0.0001)) {
            return true;
        }
        return false;
    }

    /**
     * @param other
     *            a line to check if the current line intersects with
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        boolean check = false;
        if (this.intersectionWith(other) == null) { // not intersecting
            return false;
        }
        Point intersect = this.intersectionWith(other);
        double x1 = other.start().getX();
        double x2 = other.end().getX();
        if ((((intersect.getX() <= this.start.getX()) && (intersect.getX() >= this.end
                .getX())) || ((intersect.getX() >= this.start.getX()) && (intersect
                .getX() <= this.end.getX())))
                && (((intersect.getX() <= other.start().getX()) && (intersect
                        .getX() >= other.end().getX())) || ((intersect.getX() >= x1) && (intersect
                        .getX() <= x2)))) {

            if (other.isPointOnLine(intersect)) {
                check = true;
            }
        }
        return check;
    }

    /**
     * @param other
     *            a line to find the point of the intersection
     * @return the intersection point if the lines intersect, and null
     *         otherwise.
     */
    public Point intersectionWith(Line other) {
        double a1 = this.end.getY() - this.start.getY();
        double b1 = this.start.getX() - this.end.getX();
        double c1 = a1 * this.start.getX() + b1 * this.start.getY();
        double a2 = other.end().getY() - other.start().getY();
        double b2 = other.start().getX() - other.end().getX();
        double c2 = a2 * other.start().getX() + b2 * other.start().getY();
        double det = (a1 * b2) - (a2 * b1);
        if (det == 0) {
            return null;
        } else {
            double x = (b2 * c1 - b1 * c2) / det;
            double y = (a1 * c2 - a2 * c1) / det;
            Point intersection = new Point(x, y);
            return intersection;
        }
    }

    /**
     * @param other
     *            a line to check if the current line equals to it
     * @return true if the lines are equal, false otherwise
     *
     */
    public boolean equals(Line other) {
        // checks if both x and y coordinates equal each other
        return (this.start.getX() == other.start().getX() && this.start.getY() == other
                .start().getY());
    }

    /**
     * @param surface
     *            the given DrawSurface to draw the ball on
     */
    public void drawOn(DrawSurface surface) {
        // draw the Trajectory - just for check
        int x1 = (int) (this.start().getX());
        int y1 = (int) (this.start().getY());
        int x2 = (int) (this.end().getX());
        int y2 = (int) (this.end().getY());
        surface.drawLine(x1, y1, x2, y2);
    }

    /**
     * @param rect
     *            - a rectangle to check intersect with line
     *
     *            If this line does not intersect with the rectangle, return
     *            null. Otherwise, return the closest intersection point to the
     *            start of the line.
     * @return the point of the intersection.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {

        List<Point> intersects = rect.intersectionPoints(this);
        int size = intersects.size();
        double min, temp;
        int i = 0;

        if (intersects.isEmpty()) {
            return null;
        }

        // should be Point.
        Object p1 = intersects.get(i);
        Object p2;
        min = this.start.distance((Point) p1);

        for (i = 1; i < size; i++) {
            p2 = intersects.get(i);
            temp = this.start.distance((Point) p2);
            if (temp < min) {
                min = temp;
                p1 = p2;
            }
        }

        return (Point) p1;
    }

}
