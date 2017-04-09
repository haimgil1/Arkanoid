import biuoop.DrawSurface;

/**
 * A Ball class.
 *
 * @author Shurgil and barisya
 */
public class Ball implements Sprite {
    private Point center;
    private int radius;
    private java.awt.Color color;
    private double dx;
    private double dy;
    private Line trajectory;
    private GameEnvironment gb;

    /**
     * construct a ball given the center, the radius and the color.
     *
     * @param center
     *            the center of the ball (its location)
     * @param r
     *            the radius of the circle
     * @param color
     *            the color of the ball
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
    }

    /**
     * construct a ball given the x and y coordinates of the center point, the
     * radius and the color.
     *
     * @param x
     *            the x coordinate of the center point
     * @param y
     *            the y coordinate of the center point
     * @param r
     *            the radius of the circle
     * @param color
     *            the color of the ball
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
    }

    /**
     * construct a ball given the x and y coordinates of the center point, the
     * radius, the color and the width and height of the border.
     *
     * @param x
     *            the x coordinate of the center point
     * @param y
     *            the y coordinate of the center point
     * @param r
     *            the radius of the circle
     * @param color
     *            the color of the ball
     * @param bx
     *            the width of the border
     * @param by
     *            the height of the border
     */
    public Ball(int x, int y, int r, java.awt.Color color, int bx, int by) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
    }

    /**
     * @return the x coordinate of the ball (the center)
     */
    public int getX() {
        return (int) center.getX();
    }

    /**
     * @return the y coordinate of the ball (the center)
     */
    public int getY() {
        return (int) center.getY();
    }

    /**
     * @return the size of the ball (the radius)
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * @return the color of the ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * @return the trajectory
     */
    public Line getTrajectory() {
        this.trajectory = new Line(this.getX(), this.getY(),
                (this.getX() + this.dx * 1.2), (this.getY() + this.dy * 1.2));
        return this.trajectory;
    }

    /**
     * @param surface
     *            the given DrawSurface to draw the ball on
     */
    public void drawOn(DrawSurface surface) {
        // get the x and y coordinates of the center of the ball
        int x = (int) this.center.getX();
        int y = (int) this.center.getY();

        surface.setColor(this.color);
        // and fill in the circle
        surface.fillCircle(x, y, this.radius);
        surface.setColor(java.awt.Color.black);
        surface.drawCircle(x, y, this.radius);

        this.getTrajectory();
        // draw the Trajectory - just for check
        // int x1 = (int) (this.getTrajectory().start().getX());
        // int y1 = (int) (this.getTrajectory().start().getY());
        // int x2 = (int) (this.getTrajectory().end().getX());
        // int y2 = (int) (this.getTrajectory().end().getY());
        // surface.drawLine(x1, y1, x2, y2);
    }

    /**
     * construct the dx and dy coordinates of the ball's velocity, given the
     * velocity.
     *
     * @param v
     *            the given velocity of the ball
     */
    public void setVelocity(Velocity v) {
        this.dx = v.getDx();
        this.dy = v.getDy();
    }

    /**
     * construct the dx and dy coordinates of the ball's velocity, given the x
     * and y coordinates of the velocity.
     *
     * @param x
     *            the dx coordinate of the velocity
     * @param y
     *            the dy coordinate of the velocity
     */
    public void setVelocity(double x, double y) {
        this.dx = x;
        this.dy = y;
    }

    /**
     * @return the ball's velocity
     */
    public Velocity getVelocity() {
        // creating a new velocity according to the
        // dx and dy coordinates of the ball
        Velocity ballVelocity = new Velocity(this.dx, this.dy);
        return ballVelocity;
    }

    /**
     * this method moves the ball and check for hit.
     */
    public void moveOneStep() {

        if (this.gb.getClosestCollision(trajectory) == null) {
            this.center = this.getVelocity().applyToPoint(this.center);
        } else {
            Line left = this.gb.getClosestCollision(trajectory)
                    .collisionObject().getCollisionRectangle().getLeftLine();
            Line right = this.gb.getClosestCollision(trajectory)
                    .collisionObject().getCollisionRectangle().getRightLine();
            Line up = this.gb.getClosestCollision(trajectory).collisionObject()
                    .getCollisionRectangle().getUpperLine();
            Line down = this.gb.getClosestCollision(trajectory)
                    .collisionObject().getCollisionRectangle().getLowerLine();

            if (this.trajectory.isIntersecting(up)
                    || this.trajectory.isIntersecting(down)
                    || this.trajectory.isIntersecting(right)
                    || this.trajectory.isIntersecting(left)) {

                Point collisionPoint = this.gb.getClosestCollision(trajectory)
                        .collisionPoint();
                this.setVelocity(this.gb.getClosestCollision(trajectory)
                        .collisionObject()
                        .hit(this, collisionPoint, this.getVelocity()));
                this.center = this.getVelocity()
                        .applyToPointCorner(this.center);
            } else {
                this.center = this.getVelocity().applyToPoint(this.center);
            }
        }
    }

    /**
     * this method set the ball environment to the ball.
     *
     * @param g
     *            GameEnvironment.
     */
    public void ballSetEnvironment(GameEnvironment g) {
        this.gb = g;
    }

    /**
     * this method is a in Sprite interface. call all of the Sprites to move;
     * @param dt dt
     */
    public void timePassed(double dt) {
        this.moveOneStep();
    }

    /**
     * @param g
     *            a game. enter to the sprite list this ball.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * remove sprite from the game.
     * @param game
     *            the game
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }

}
