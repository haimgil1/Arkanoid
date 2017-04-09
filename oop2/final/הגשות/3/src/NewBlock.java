
import java.awt.Image;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
/**
 * A newBlock class.
 *
 * @author Shurgil and barisya
 */
public class NewBlock implements BlockCreator {
    private double width;
    private double height;
    private java.awt.Color color;
    private Image img;
    private boolean hasimg;
    private Map<Integer, Background> mapBackground;
    private char symbol;
    private int hitPoints;
    private java.awt.Color strokecolor;
/**
 * constructor.
 */
    public NewBlock() {
        this.hasimg = false;
        this.mapBackground = new TreeMap<Integer, Background>();
    }

    @Override
    public Block create(int xpos, int ypos) throws IOException {
        Block block1 = new Block();
        block1.setXY(xpos, ypos);
        block1.setsymbol(this.symbol);
        block1.setheight((int) this.height);
        block1.setwidth((int) this.width);
        block1.setHitPoints(this.hitPoints);
        block1.setMapFill(this.mapBackground);
        if (this.hasimg) {
            block1.setimage(this.img);
        } else {
            block1.setfillcolor(this.color);
        }
        block1.setstrokecolor(this.strokecolor);
        return block1;
    }
/**
 * @param s the char to set.
 */
    public void setSymbol(char s) {
        this.symbol = s;
    }
/**
 * @param h the height to set.
 */
    public void setheight(int h) {
        this.height = h;
    }
/**
 * @param w the width to set.
 */
    public void setwidth(int w) {
        this.width = w;
    }
/**
 * @param h the height to set.
 */
    public void sethitPoints(int h) {
        this.hitPoints = h;
    }
/**
 * @param f the color to set.
 */
    public void setfillcolor(java.awt.Color f) {
        this.color = f;
    }
/**
 * @param s image
 * @throws IOException throw not found.
 */
    public void setimage(Image s) throws IOException {
        this.img = s;
        this.hasimg = true;
    }
/**
 * @param s color.
 */
    public void setstrokecolor(java.awt.Color s) {
        this.strokecolor = s;
    }
/**
 * @param num num of fill - hit.
 * @param c color.
 */
    public void setFillcoByNum(int num, java.awt.Color c) {
        Integer a = Integer.valueOf(num);

        Background backg = new Background(c, null, (int) this.width,
                (int) this.height);
        this.mapBackground.put(a, backg);
    }
/**
 * @param num num of fill.
 * @param img2 image.
 */
    public void setFillimgByNum(int num, Image img2) {
        Integer a = Integer.valueOf(num);
        Background backg = new Background(null, img2, (int) this.width,
                (int) this.height);
        this.mapBackground.put(a, backg);
    }

}
