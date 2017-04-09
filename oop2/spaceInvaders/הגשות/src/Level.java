import java.awt.Image;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * A Level class.
 *
 * @author Shurgil and barisya
 */
public class Level implements LevelInformation {
    private String levelName;
    private List<Velocity> ballVelocities;
    private int paddleSpeed;
    private int paddleWidth;
    private String blocksFile;
    private int blockStartX;
    private int blockStartY;
    private int rowHeight;
    private int numBlocksToRemove;
    private java.awt.Color color;
    private Image imageName;
    private Background background;
    private String blockplaceString;
    private int num;

    /**
     * @param blockStartX
     *            block
     * @param blockStartY
     *            block
     * @param rowHeight
     *            rows
     * @param numBlocksToRemove
     *            remove block
     * @param color
     *            color
     * @param imageName
     *            img
     * @param blockplaceString
     *            block string
     */
    public Level(int blockStartX, int blockStartY, int rowHeight,
            int numBlocksToRemove, java.awt.Color color, Image imageName,
            String blockplaceString) {

        this.blockStartX = blockStartX;
        this.blockStartY = blockStartY;
        this.rowHeight = rowHeight;
        this.numBlocksToRemove = numBlocksToRemove;
        this.color = color;
        this.imageName = imageName;
        this.background = new Background(this.color, this.imageName, 800, 600);
        this.background.setplace(0, 0);
        this.blockplaceString = blockplaceString;

    }

    /**
     * @param name
     *            the name
     */
    public void setName(String name) {
        this.levelName = name;
    }
/**
 * @param speed paddle speed.
 */
    public void setPaddlespeed(int speed) {
        this.paddleSpeed = speed;
    }
/**
 * @param ballVelocities2 bals velocity.
 */
    public void setBallveloc(List<Velocity> ballVelocities2) {
        this.ballVelocities = ballVelocities2;
    }
/**
 * @param w paddle width.
 */
    public void setPaddlewidth(int w) {
        this.paddleWidth = w;
    }
/**
 * @param blocksFile2 block file read from.
 */
    public void setBlockfile(String blocksFile2) {
        this.blocksFile = blocksFile2;
    }

    @Override
    public int numberOfBalls() {
        return this.ballVelocities.size();
    }

    @Override
    public List<Ball> locationOfBalls() {
        List<Ball> balls = new ArrayList<Ball>();
        for (int i = 0; i < this.ballVelocities.size(); i++) {
            Ball b = new Ball(400, 550, 5, java.awt.Color.WHITE);
            balls.add(b);
        }
        return balls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return this.ballVelocities;
    }

    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }

    @Override
    public Point paddleLocation() {
        return new Point((400.0 - this.paddleWidth / 2), 570.0);
    }

    @Override
    public String levelName() {
        return this.levelName;
    }

    /**
     * @return color
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * @return image
     */
    public Image getImageName() {
        return this.imageName;
    }

    @Override
    public Sprite getBackground() {
        return this.background;
    }

    /**
     * @return file name
     */
    public String getFileName() {
        return this.blocksFile;
    }

    // liron the king //
    @Override
    public List<Block> blocks() throws IOException {

        InputStream is = null;
        is = ClassLoader.getSystemClassLoader().getResourceAsStream(this.blocksFile);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        BlocksDefinitionReader checking = new BlocksDefinitionReader(br);
        List<Block> blockList2 = checking.getblockplaces(this.blockStartX,
                this.blockStartY, this.rowHeight, this.blockplaceString);

        return blockList2;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.numBlocksToRemove;
    }

    @Override
    public void setNum(int i) {
        this.num = i;
    }
    /**
     * @return int num of the level.
     */
    public int getNum() {
        return this.num;
    }
}
