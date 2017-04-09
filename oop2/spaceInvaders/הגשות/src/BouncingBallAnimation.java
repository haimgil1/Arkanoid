
import biuoop.DrawSurface;
import biuoop.GUI;

/**
 * A BouncingBallAnimation class.
 *
 * @author Shurgil and barisya
 */
public class BouncingBallAnimation {

    /**
     * @param args
     *            command line arguments.
     *
     *            the main function that creates an animation using the
     *            graphical package
     */
    public static void main(String[] args) {
        // Create a window with the title "title"
        // which is 400 pixels wide and 300 pixels high.
        GUI gui = new GUI("title", 400, 300);
        // creating a new sleeper
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        // creating a new ball with x and y coordinates for the center point,
        // the radius, the color and the width and height of the border
        Ball ball = new Ball(100, 50, 30, java.awt.Color.BLACK, 400, 300);
        // setting the velocity's dx and dy coordinates
        ball.setVelocity(2, 2);
        // using a while loop, drawing different pictures
        // on the same area, thus creating the animation
        while (true) {
            // moving the ball according to the velocity
            ball.moveOneStep();
            // and drawing it again each time
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            // wait for 50 milliseconds between each picture.
            sleeper.sleepFor(5);
        }
    }
}
