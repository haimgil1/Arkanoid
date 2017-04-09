import java.util.ArrayList;
import java.util.List;

/**
 * A Rectangle class.
 *
 * @author Shurgil and barisya
 */
class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * @param upperLeft
     *            right point
     * @param width
     *            of the rectangle.
     * @param height
     *            of the rectangle. Create a new rectangle with location and
     *            width/height.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * @param line
     *            .
     * @return a (possibly empty) List of intersection points with the specified
     *         line.
     */
    public List<Point> intersectionPoints(Line line) {

        List<Point> pointList = new ArrayList<Point>();

        Point intersectDown = line.intersectionWith(this.getLowerLine());
        Point intersectRight = line.intersectionWith(this.getRightLine());
        Point intersectUp = line.intersectionWith(this.getUpperLine());
        Point intersectLeft = line.intersectionWith(this.getLeftLine());

        if (line.isIntersecting(this.getLowerLine())) {
            pointList.add(intersectDown);
        }
        if (line.isIntersecting(this.getRightLine())) {
            pointList.add(intersectRight);
        }
        if (line.isIntersecting(this.getUpperLine())) {
            pointList.add(intersectUp);
        }
        if (line.isIntersecting(this.getLeftLine())) {
            pointList.add(intersectLeft);
        }
        return pointList;
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
     * @return the upper-Right point of the rectangle.
     */
    public Point getUpperRight() {
        double x = this.upperLeft.getX() + width;
        Point upperRight = new Point(x, this.upperLeft.getY());
        return upperRight;
    }

    /**
     * @return the lower-left point of the rectangle.
     */
    public Point getLowerLeft() {
        double y = this.upperLeft.getY() + height;
        Point lowerleft = new Point(this.upperLeft.getX(), y);
        return lowerleft;
    }

    /**
     * @return the lower-right point of the rectangle.
     */
    public Point getLowerRight() {
        double x = this.upperLeft.getX() + width;
        double y = this.upperLeft.getY() + height;
        Point lowerright = new Point(x, y);
        return lowerright;
    }

    /**
     * @return the upper line of the rectangle.
     */
    public Line getUpperLine() {
        Line newline = new Line(this.upperLeft, this.getUpperRight());
        return newline;
    }

    /**
     * @return the left line of the rectangle.
     */
    public Line getLeftLine() {
        Line newline = new Line(this.upperLeft, this.getLowerLeft());
        return newline;
    }

    /**
     * @return the lower line of the rectangle.
     */
    public Line getLowerLine() {
        Line newline = new Line(this.getLowerLeft(), this.getLowerRight());
        return newline;
    }

    /**
     * @return the right line of the rectangle.
     */
    public Line getRightLine() {
        Line newline = new Line(this.getLowerRight(), this.getUpperRight());
        return newline;
    }

}
