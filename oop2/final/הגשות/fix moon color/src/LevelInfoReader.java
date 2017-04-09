import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
/**
 * A LevelInfoReader class.
 *
 * @author Shurgil and barisya
 */
public class LevelInfoReader {
    private List<String> levelInfo;
    private LevelInformation level;
    private String levelName;
    private List<Velocity> ballVelocities;
    private int paddleSpeed;
    private int paddleWidth;
    private int blockStartX;
    private int blockStartY;
    private int rowHeight;
    private int numBlocksToRemove;
    private java.awt.Color color = null;
    private Image imageName;
    private String blocksFile;
    private String blockplaceString;
/**
 * @param levelInfo info
 * @throws IOException throw
 */
    public LevelInfoReader(List<String> levelInfo) throws IOException {
        this.levelInfo = levelInfo;
        this.read();
    }
/**
 * read.
 * @throws IOException throw
 */
    public void read() throws IOException {
        if (this.levelInfo.size() < 10) {
            System.out.println("parsing process failed- missing fields");
            return;
        }

        for (int i = 0; i < this.levelInfo.size(); i++) {

            if (this.levelInfo.get(i).startsWith("level_name")) {
                int index = this.levelInfo.get(i).indexOf(':') + 1;
                this.levelName = this.levelInfo.get(i).substring(index);

            } else if (this.levelInfo.get(i).startsWith("ball_velocities")) {
                this.ballVelocities = new ArrayList<Velocity>();

                int index = this.levelInfo.get(i).indexOf(':') + 1;
                String s00 = this.levelInfo.get(i).substring(index);
                String[] velocityArray = s00.split(" ");

                for (int j = 0; j < velocityArray.length; j++) {
                    String[] velocityParam = velocityArray[j].split(",");
                    int angle = Integer.parseInt(velocityParam[0]);
                    int speed = Integer.parseInt(velocityParam[1]);
                    Velocity velocity = new Velocity(angle, speed);
                    this.ballVelocities.add(velocity);
                }

            } else if (this.levelInfo.get(i).startsWith("background")) {
                int index = this.levelInfo.get(i).indexOf(':') + 1;
                String s = this.levelInfo.get(i).substring(index);

                if (s.startsWith("image")) {
                    int index2 = this.levelInfo.get(i).indexOf('(') + 1;
                    int index3 = this.levelInfo.get(i).indexOf(')');

                    String imstr = this.levelInfo.get(i)
                            .substring(index2, index3);
                    InputStream is = null;
                    is = ClassLoader.getSystemClassLoader().getResourceAsStream(imstr);
                    BufferedImage img = ImageIO.read(is);
                    this.imageName = img;

                } else if (s.startsWith("color")) {
                    int index2 = this.levelInfo.get(i).indexOf('(') + 1;
                    int index3 = this.levelInfo.get(i).indexOf(')');
                    String sColor = this.levelInfo.get(i).substring(index2,
                            index3);

                    ColorsParser cp = new ColorsParser();
                    this.color = cp.colorFromString(sColor);
                }

            } else if (this.levelInfo.get(i).startsWith("paddle_speed")) {
                int index1 = this.levelInfo.get(i).indexOf(':') + 1;
                String str1 = this.levelInfo.get(i).substring(index1);
                this.paddleSpeed = Integer.parseInt(str1);

            } else if (this.levelInfo.get(i).startsWith("paddle_width")) {
                int index2 = this.levelInfo.get(i).indexOf(':') + 1;
                String str2 = this.levelInfo.get(i).substring(index2);
                this.paddleWidth = Integer.parseInt(str2);

            } else if (this.levelInfo.get(i).startsWith("block_definitions")) {
                int index3 = this.levelInfo.get(i).indexOf(':') + 1;
                this.blocksFile = this.levelInfo.get(i).substring(index3);

            } else if (this.levelInfo.get(i).startsWith("blocks_start_x")) {
                int index4 = this.levelInfo.get(i).indexOf(':') + 1;
                String str3 = this.levelInfo.get(i).substring(index4);
                this.blockStartX = Integer.parseInt(str3);

            } else if (this.levelInfo.get(i).startsWith("blocks_start_y")) {
                int index5 = this.levelInfo.get(i).indexOf(':') + 1;
                String str4 = this.levelInfo.get(i).substring(index5);
                this.blockStartY = Integer.parseInt(str4);

            } else if (this.levelInfo.get(i).startsWith("row_height")) {
                int index6 = this.levelInfo.get(i).indexOf(':') + 1;
                String str5 = this.levelInfo.get(i).substring(index6);
                this.rowHeight = Integer.parseInt(str5);

            } else if (this.levelInfo.get(i).startsWith("num_blocks")) {
                int index7 = this.levelInfo.get(i).indexOf(':') + 1;
                String str6 = this.levelInfo.get(i).substring(index7);
                this.numBlocksToRemove = Integer.parseInt(str6);

            }

            if (this.levelInfo.get(i).startsWith("START_BLOCKS")) {
                StringBuilder a = new StringBuilder();
                i++;
                while (!this.levelInfo.get(i).startsWith("END_BLOCKS")) {
                    a.append(this.levelInfo.get(i));
                    a.append("\n");
                    i++;
                }
                a.deleteCharAt(a.length() - 1);
                this.blockplaceString = a.toString();
            }
        }
        this.level = new Level(this.blockStartX, this.blockStartY, this.rowHeight,
                this.numBlocksToRemove, this.color, this.imageName,
                this.blockplaceString);
        ((Level) this.level).setName(this.levelName);
        ((Level) this.level).setBallveloc(this.ballVelocities);
        ((Level) this.level).setPaddlespeed(this.paddleSpeed);
        ((Level) this.level).setPaddlewidth(this.paddleWidth);
        ((Level) this.level).setBlockfile(this.blocksFile);
    }
/**
 * @return level infi.
 */
    public LevelInformation getLevel() {
        return this.level;
    }

}