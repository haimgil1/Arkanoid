/**
 * A ColorsParser class.
 *
 * @author Shurgil and barisya
 */
public class ColorsParser {
    /**
     * parse color definition and return the specified color.
     * @param s
     *            string
     * @return color
     */
    public java.awt.Color colorFromString(String s) {

        java.awt.Color color = null;

        if (s.contains("color(RGB(")) {
            int c = s.indexOf("color(RGB(") + 10;
            int d = s.indexOf(")", c);
            String colorString = s.substring(c, d);
            int place = 0;
            int nextdot = colorString.indexOf(",");
            int x = Integer.parseInt(colorString.substring(place, nextdot));
            place = nextdot + 1;
            nextdot = colorString.indexOf(",", place);
            int y = Integer.parseInt(colorString.substring(place, nextdot));
            place = nextdot + 1;
            int z = Integer.parseInt(colorString.substring(place));

            color = new java.awt.Color(x, y, z);
            return color;
        }

        if (s.contains("color(")) {
            int c = s.indexOf("color(") + 6;
            int d = s.indexOf(")", c);
            String colorString = s.substring(c, d);

            if (colorString.equals("black")) {
                color = java.awt.Color.black;
            }
            if (colorString.equals("blue")) {
                color = java.awt.Color.blue;
            }
            if (colorString.equals("cyan")) {
                color = java.awt.Color.cyan;
            }
            if (colorString.equals("gray")) {
                color = java.awt.Color.gray;
            }
            if (colorString.equals("lightGray")) {
                color = java.awt.Color.lightGray;
            }
            if (colorString.equals("green")) {
                color = java.awt.Color.green;
            }
            if (colorString.equals("orange")) {
                color = java.awt.Color.orange;
            }
            if (colorString.equals("pink")) {
                color = java.awt.Color.pink;
            }
            if (colorString.equals("red")) {
                color = java.awt.Color.red;
            }
            if (colorString.equals("white")) {
                color = java.awt.Color.white;
            }
            if (colorString.equals("yellow")) {
                color = java.awt.Color.yellow;
            }
            return color;
        }

        return color;

    }
}
