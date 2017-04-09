import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.imageio.ImageIO;
/**
 * A BlocksFromSymbolsFactory class.
 *
 * @author Shurgil and barisya
 */
public class BlocksFromSymbolsFactory {
    private Map<String, Integer> spacerWidths;
    private Map<String, BlockCreator> blockCreators;
    private List<Block> blockList;
    private int dwidth;
    private int dheight;
    private int dhitPoints;
    private java.awt.Color dcolorfill;
    private char dsymbol;
    private java.awt.Color dcolorstroke;
/**
 * constructor.
 */
    BlocksFromSymbolsFactory() {
        this.blockCreators = new TreeMap<String, BlockCreator>();
        this.spacerWidths = new TreeMap<String, Integer>();
        this.blockList = new ArrayList<Block>();
    }
/**
 * @param s string
 * @throws IOException throw
 */
    public void setblock(String s) throws IOException {

        NewBlock newblock = new NewBlock();

        char symbol;
        if (s.contains("symbol:")) {
            int c = s.indexOf("symbol:") + 7;
            symbol = s.charAt(c);
            newblock.setSymbol(symbol);
        } else {
            symbol = this.dsymbol;
            newblock.setSymbol(this.dsymbol);
        }

        if (s.contains("height:")) {
            int c = s.indexOf("height:") + 7;
            int d = s.indexOf(" ", c);
            if (d == -1) {
                d = s.length();
            }
            String heightString = s.substring(c, d);
            int height = Integer.parseInt(heightString);
            newblock.setheight(height);
        } else {
            newblock.setheight(this.dheight);
        }

        if (s.contains("width:")) {
            int c = s.indexOf("width:") + 6;
            int d = s.indexOf(" ", c);
            if (d == -1) {
                d = s.length();
            }
            String widthString = s.substring(c, d);
            int width = Integer.parseInt(widthString);
            newblock.setwidth(width);
        } else {
            newblock.setwidth(this.dwidth);
        }

        if (s.contains("hit_points:")) {
            int c = s.indexOf("hit_points:") + 11;
            int d = s.indexOf(" ", c);
            if (d == -1) {
                d = s.length();
            }
            String hitString = s.substring(c, d);
            int hit = Integer.parseInt(hitString);
            newblock.sethitPoints(hit);
        } else {
            newblock.sethitPoints(this.dhitPoints);
        }

        if (s.contains("fill:")) {
            int c = s.indexOf("fill:") + 5;
            int d = s.indexOf(" ", c);
            if (d == -1) {
                d = s.length();
            }
            String colorString = s.substring(c, d);
            if (colorString.startsWith("image(")) {
                c = c + 6;
                int e = s.indexOf(")");
                String strimg = s.substring(c, e);
                InputStream is = null;
                is = ClassLoader.getSystemClassLoader().getResourceAsStream(strimg);
                Image img = ImageIO.read(is);
                newblock.setimage(img);

            } else {
                ColorsParser colorp = new ColorsParser();
                java.awt.Color color = colorp.colorFromString(colorString);
                newblock.setfillcolor(color);
            }
        } else {
            newblock.setfillcolor(this.dcolorfill);
        }

        if (s.contains("fill-")) {
            int c = s.indexOf("fill-") + 5;
            int r = s.indexOf(":", c);
            String strnum = s.substring(c, r);
            int num = Integer.parseInt(strnum);

            c = s.indexOf(":", r) + 1;
            int d = s.indexOf(" ", c);
            if (d == -1) {
                d = s.length();
            }

            String colorString = s.substring(c, d);

            if (colorString.startsWith("image(")) {
                c = c + 6;
                int e = s.indexOf(")", c);
                colorString = s.substring(c, e);
                InputStream is = null;
                is = ClassLoader.getSystemClassLoader().getResourceAsStream(colorString);
                BufferedImage img = ImageIO.read(is);
                newblock.setFillimgByNum(num, img);

            } else {
                ColorsParser colorp = new ColorsParser();
                java.awt.Color color = colorp.colorFromString(colorString);
                newblock.setFillcoByNum(num, color);
            }
        }

        if (s.contains("stroke:")) {
            int c = s.indexOf("stroke:") + 7;
            String strokeString = s.substring(c);
            ColorsParser colorp = new ColorsParser();
            java.awt.Color color = colorp.colorFromString(strokeString);
            newblock.setstrokecolor(color);
        } else {
            newblock.setstrokecolor(this.dcolorstroke);
        }

        String symbolString = Character.toString(symbol);
        this.blockCreators.put(symbolString, newblock);
    }
/**
 *  // returns true if 's' is a valid space symbol.
 * @param s string
 * @return if in map.
 */
    public boolean isSpaceSymbol(String s) {
        return this.spacerWidths.containsKey(s);
    }
/**
 * // returns true if 's' is a valid block symbol.
 * @param s string
 * @return if in map
 */
    public boolean isBlockSymbol(String s) {
        return this.blockCreators.containsKey(s);
    }
/**
 * // Return a block according to the definitions associated
    // with symbol s. The block will be located at position (xpos, ypos).
 * @param s string
 * @param x place
 * @param y place
 * @return block
 * @throws IOException throw
 */
    public Block getBlock(String s, int x, int y) throws IOException {
        return this.blockCreators.get(s).create(x, y);
    }
/**
 * Returns the width in pixels associated with the given spacer-symbol.
 * @param s string
 * @return num width of space
 */
    public int getSpaceWidth(String s) {

        return this.spacerWidths.get(s);

    }
/**
 * @return list of blocks
 */
    public List<Block> getblockList() {
        return this.blockList;
    }
/**
 * @param blockList2 list of blocks
 */
    public void setblockList(List<Block> blockList2) {
        this.blockList = blockList2;
    }
/**
 * @param s string of deafult
 */
    public void setdefault(String s) {

        if (s.contains("symbol:")) {
            int c = s.indexOf("symbol:") + 7;
            this.dsymbol = s.charAt(c);
        }

        if (s.contains("height:")) {
            int c = s.indexOf("height:") + 7;
            int d = s.indexOf(" ", c);
            if (d == -1) {
                d = s.length();
            }
            String heightString = s.substring(c, d);
            this.dheight = Integer.parseInt(heightString);
        }

        if (s.contains("width:")) {
            int c = s.indexOf("width:") + 6;
            int d = s.indexOf(" ", c);
            if (d == -1) {
                d = s.length();
            }
            String widthString = s.substring(c, d);
            this.dwidth = Integer.parseInt(widthString);
        }

        if (s.contains("hit_points:")) {
            int c = s.indexOf("hit_points:") + 11;
            int d = s.indexOf(" ", c);
            if (d == -1) {
                d = s.length();
            }
            String hitString = s.substring(c, d);
            this.dhitPoints = Integer.parseInt(hitString);
        }

        if (s.contains("fill:")) {
            int c = s.indexOf("fill:") + 5;
            int d = s.indexOf(" ", c);
            if (d == -1) {
                d = s.length();
            }
            String colorString = s.substring(c, d);
            ColorsParser colorp = new ColorsParser();
            this.dcolorfill = colorp.colorFromString(colorString);
        }

        if (s.contains("stroke:")) {
            int c = s.indexOf("stroke:") + 7;
            String strokeString = s.substring(c);
            ColorsParser colorp = new ColorsParser();
            this.dcolorstroke = colorp.colorFromString(strokeString);
        }
    }
/**
 * @param s string of space def
 */
    public void setSpace(String s) {

        String symbol = null;
        Integer intwidth = null;
        if (s.contains("symbol:")) {
            int c = s.indexOf("symbol:") + 7;
            int d = s.indexOf(" ", c);
            if (d == -1) {
                d = s.length();
            }
            symbol = s.substring(c, d);
        }
        if (s.contains("width:")) {
            int c = s.indexOf("width:") + 6;
            String widths = s.substring(c);
            intwidth = Integer.parseInt(widths);
        }

        this.spacerWidths.put(symbol, intwidth);

    }
/**
 * @return map
 */
    public Map<String, Integer> getspacerWidths() {
        return this.spacerWidths;
    }
/**
 * @return map
 */
    public Map<String, BlockCreator> getblockCreators() {
        return this.blockCreators;
    }

}
