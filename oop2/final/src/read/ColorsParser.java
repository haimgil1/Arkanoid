package read;

import java.awt.Color;
import java.util.Arrays;

/**
 * A ColorsParser class.
 *
 * @author Nir Dunetz and Haim Gil.
 */
public class ColorsParser {

    /**
     * Parse color definition and return the specified color.
     * Note:throwing exeption if color from string is not from the list of valid colors.
     *
     * @param s Is String representing a color.
     * @return Color according to s.
     * @throws Exception if color from string is not valid.
     */
    public Color colorFromString(String s) throws Exception {

        Color color = null;
        //Indicate if color in s is valid.
        boolean isValidColor = false;

        //Check if color input define as RGB or as color name.
        if (s.contains("RGB")) {

            //Split the numbers from string and create new color.
            String[] colors = s.substring(10, s.length() - 2).split(",");
            int x = Integer.parseInt(colors[0]);
            int y = Integer.parseInt(colors[1]);
            int z = Integer.parseInt(colors[2]);
            color = new Color(x, y, z);
        } else {
            s = (s.substring(6, s.length() - 1)).toLowerCase();
            java.util.List<String> colorsByString = Arrays.asList("black", "gray", "cyan", "lightgray", "blue",
                    "orange", "green",
                    "red", "white", "yellow", "pink");
            //Colors list of valid colors respectively to colorsByString in index.
            Color[] colors = {Color.black, Color.gray, Color.cyan, Color.lightGray, Color.blue,
                    Color.orange, Color.green, Color.red, Color.white, Color.yellow, Color.pink};
            //Check if color from string is valid color.
            if (colorsByString.contains(s)) {
                //Getting the index of the color in array colors.
                int index = colorsByString.indexOf(s);
                //Getting the color.
                color = colors[index];
                isValidColor = true;
            }
            //If color from string not valid throwing exeption.
            if (!isValidColor) {
                throw new Exception(s + " is invalid color");
            }
        }
        return color;
    }
}
