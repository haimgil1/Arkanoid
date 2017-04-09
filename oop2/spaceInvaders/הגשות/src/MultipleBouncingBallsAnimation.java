import java.util.Random;

import biuoop.DrawSurface;
import biuoop.GUI;

/**
 * A MultipleBouncingBallsAnimation class.
 *
 * @author Shurgil and barisya
 */
public class MultipleBouncingBallsAnimation {

    /**
     * This function converts the command line arguments, that are strings, to
     * integers.
     *
     * @param args
     *            command line arguments.
     * @return numbers- the list now as integers.
     */
    public static int[] stringsToInts(String[] args) {
        // allocating memory for a new int array
        int[] numbers = new int[args.length];
        // converts the strings to integers
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = Integer.parseInt(args[i]);
        }
        // and returns the integer array.
        return numbers;
    }

    /**
     * @param args
     *            command line arguments.
     *
     *            the main function that creates the
     *            MultipleBouncingBallsAnimation. get from the comand line the
     *            size and how many ball and drawing them on the surface
     */
    public static void main(String[] args) {

        // allocating memory for a new numbers and balls array
        int[] numbers = new int[args.length];
        Ball[] ballArray = new Ball[args.length];

        // sending the command line arguments to be converted to ints
        numbers = stringsToInts(args);
        Random rand = new Random();
        // Create a window with the title "title"
        // which is 400 pixels wide and 300 pixels high.
        GUI gui = new GUI("title", 400, 300);
        // creating a new sleeper
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        // applying to all balls that the user typed in
        for (int i = 0; i <= args.length - 1; ++i) {
            int r = numbers[i];
            int x = rand.nextInt(400 - r); // get integer in range 1-400
            if (x < r) {
                x = r;
            }
            int y = rand.nextInt(300 - r); // get integer in range 1-300
            if (y < r) {
                y = r;
            }
            float red = rand.nextFloat();
            float g = rand.nextFloat();
            float b = rand.nextFloat();
            java.awt.Color randomColor = new java.awt.Color(red, g, b);
            // creating a new ball with x and y coordinates for
            // the center point, the radius, the random color
            // we created and the width and height of the border
            Ball ball = new Ball(x, y, r, randomColor, 400, 300);
            // setting the ball's velocity
            ball.setVelocity(100 / r, 100 / r);
            ballArray[i] = ball;
        }
        // using a while loop, drawing different pictures
        // on the same area, thus creating the animation
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            // this time, applying to multiple balls
            for (int j = 0; j <= args.length - 1; ++j) {
                Ball ball = ballArray[j];
                ball.moveOneStep();
                ball.drawOn(d);
            }
            gui.show(d);
            // wait for 50 milliseconds.
            sleeper.sleepFor(50);
        }
    }
}