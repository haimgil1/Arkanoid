import biuoop.GUI;
import biuoop.DrawSurface;

import java.util.Random;
import java.awt.Color;

/**
 * AbstractArtDrawing class.
 *
 * @author Shurgil and barisya
 */
public class AbstractArtDrawing {
    private int time = 10;
    private int radius = 3;
    private int width = 400;
    private int length = 300;

    /**
     * @return a new line
     */
    public Line generateRandomLine() {
        Random rand = new Random(); // create a random-number generator
        int x = rand.nextInt(width) + 1; // get integer in range 1-400
        int y = rand.nextInt(length) + 1; // get integer in range 1-300
        int x2 = rand.nextInt(width) + 1; // get integer in range 1-400
        int y2 = rand.nextInt(length) + 1; // get integer in range 1-300
        Line newline = new Line(x, y, x2, y2);
        return newline;
    }

    /**
     * @param l
     *            the line to draw
     * @param d
     *            the draw surface to draw with
     */
    public void drawLine(Line l, DrawSurface d) {
        // the color
        d.setColor(Color.BLACK);
        // setting the x and y coordinates of the start point
        int startX = (int) l.start().getX();
        int startY = (int) l.start().getY();
        // and setting the x and y coordinates of the end point
        int endX = (int) l.end().getX();
        int endY = (int) l.end().getY();
        // drawing the line
        d.drawLine(startX, startY, endX, endY);
    }

    /**
     * this function draws random circles.
     */
    public void drawRandomCircles() {
        // Create a window with the title "Random Circles Example"
        // with the width and length we set.
        GUI gui = new GUI("Random Circles Example", width, length);
        DrawSurface d = gui.getDrawSurface();
        // keeping the lines in an array
        Line[] linearray = new Line[time];
        for (int i = 0; i < time; ++i) {
            // draw 10 random lines
            Line l1 = this.generateRandomLine();
            linearray[i] = l1;
            // draw the middle point in blue
            d.setColor(Color.BLUE);
            // get the x and y coordinate of the middle point
            int midX = (int) l1.middle().getX();
            int midY = (int) l1.middle().getY();
            // fill that circle in
            d.fillCircle(midX, midY, radius);
            this.drawLine(l1, d);
            if (i > 0) {
                // draw the intersection point in red
                d.setColor(Color.RED);
                for (int j = 0; j < i; j++) {
                    // if the equal
                    if (l1.equals(linearray[j])) {
                        continue;
                    }
                    // or if they intesect
                    if (!l1.isIntersecting(linearray[j])) {
                        continue;
                    }
                    // get the x and y coordinate of the intersection
                    int interX = (int) l1.intersectionWith(linearray[j]).getX();
                    int interY = (int) l1.intersectionWith(linearray[j]).getY();
                    // fill that circle in
                    d.fillCircle(interX, interY, radius);
                }
            }
        }
        gui.show(d); // print it out
    }

    /**
     * @param args
     *            command line arguments.
     *
     *            the main function that creates a new AbstractArtDrawing class
     */
    public static void main(final String[] args) {
        AbstractArtDrawing lines = new AbstractArtDrawing();
        lines.drawRandomCircles();
    }
}