package geometry;

import java.util.ArrayList;
import java.util.List;

/**
 * A Rectangle class.
 *
 * @author Nir Dunetz and Haim Gil.
 */
public class Rectangle {
    // Members.
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * Constructs a rectangle given a point and the width/height.
     *
     * @param upperLeft is the upper left point.
     * @param width     is the width.
     * @param height    is the height.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Constructs a rectangle given x, y coordinates and the width/height.
     *
     * @param x      is the x coordinate.
     * @param y      is the y coordinate.
     * @param width  is the width.
     * @param height is the height.
     */
    public Rectangle(double x, double y, double width, double height) {
        this.upperLeft = new Point(x, y);
        this.width = width;
        this.height = height;
    }

    /**
     * Finding intersection point of a rectangle, add it to list and return the list.
     *
     * @param line is a line.
     * @return a (possibly empty) List of intersection points with the specified line.
     */

    public List<Point> intersectionPoints(Line line) {
        List<Point> pointsList = new ArrayList<Point>(); // List of intersections points.

        // Finding intersection points in each line of the rectangle and update the list.
        pointsList = addIntersectionToLIst(line, this.getUpperLine(), pointsList);
        pointsList = addIntersectionToLIst(line, this.getBottomLine(), pointsList);
        pointsList = addIntersectionToLIst(line, this.getLeftLine(), pointsList);
        pointsList = addIntersectionToLIst(line, this.getRightLine(), pointsList);
        return pointsList;
    }

    /**
     * Finding intersection point of a line, add it to list and return the list.
     *
     * @param line          is the line that check with if intersect with rectangle.
     * @param rectangleLine is one of a rectangle.
     * @param pointsList    is list of intersections point.
     * @return a (possibly empty) List of intersection points with the specified line.
     */
    public List<Point> addIntersectionToLIst(Line line, Line rectangleLine, List<Point> pointsList) {
        Point intersection = line.intersectionWith(rectangleLine);

        // Add the intersecting to the list.
        if (line.isIntersecting(rectangleLine) && (!pointsList.contains(intersection))) {
            pointsList.add(intersection);
        }
        return pointsList;

    }

    /**
     * @param line is a line that check if intersect with this line
     * @return true if lines are intersected, false if not.
     */
    public boolean isIntersectWithLine(Line line) {
        List<Point> pointsList = this.intersectionPoints(line);
        return (!pointsList.isEmpty());
    }

    /**
     * @return the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * @return the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * @return the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * @return the upper right point of the rectangle.
     */
    public Point getUpperRight() {
        double xUpperRight = this.upperLeft.getX() + width;
        return new Point(xUpperRight, this.upperLeft.getY());
    }

    /**
     * @return the Bottom left point of the rectangle.
     */
    public Point getBottomLeft() {
        double yBottomLeft = this.upperLeft.getY() + height;
        return new Point(this.upperLeft.getX(), yBottomLeft);
    }

    /**
     * @return the bottom right point of the rectangle.
     */
    public Point getBottomRight() {
        double x = this.upperLeft.getX() + width;
        double y = this.upperLeft.getY() + height;
        return new Point(x, y);
    }

    /**
     * @return the left line of the rectangle.
     */
    public Line getLeftLine() {
        return new Line(this.upperLeft, this.getBottomLeft());
    }

    /**
     * @return the bottom line of the rectangle.
     */
    public Line getBottomLine() {
        return new Line(this.getBottomLeft(), this.getBottomRight());
    }

    /**
     * @return the upper line of the rectangle.
     */
    public Line getUpperLine() {
        return new Line(this.upperLeft, this.getUpperRight());
    }

    /**
     * @return the right line of the rectangle.
     */
    public Line getRightLine() {
        return new Line(this.getBottomRight(), this.getUpperRight());
    }

}
