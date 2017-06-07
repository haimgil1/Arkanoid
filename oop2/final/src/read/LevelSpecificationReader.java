package read;

import accessories.Block;
import accessories.Velocity;
import levels.Level;
import levels.LevelInformation;
import sprites.Background;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * A LevelSpecificationReader class.
 *
 * @author Nir Dunetz and Haim Gil.
 */
public class LevelSpecificationReader extends CheckRead {
    //Members.
    private BlocksFromSymbolsFactory blocks = null;


    /**
     * Create list of levels according to files that read.
     *
     * @param reader Is a java.io.Reader to reaf from file.
     * @return List of levels information.
     */
    public List<LevelInformation> fromReader(java.io.Reader reader) {

        String line = null;
        List<LevelInformation> levelsList = new ArrayList<LevelInformation>();
        Map<String, String> levelsInfo = new LinkedHashMap<String, String>();
        BufferedReader br = new BufferedReader(reader);
        int linesCounter = 0;
        try {
            // Reading lines until the end of the file.
            while ((line = br.readLine()) != null) {
                //Ignorance empty line and comments.
                if (!(line.isEmpty()) && !(line.startsWith("#"))) {
                    if (line.equals("START_LEVEL") || line.equals("END_LEVEL")) {
                        continue;
                    } else if (line.equals("START_BLOCKS")) {
                        //Read lines associated with blocks.
                        readBlocks(levelsInfo, br, linesCounter, levelsList);
                    } else {
                        //Spliting between definition and his data.
                        String[] splitLine = line.split(":");
                        levelsInfo.put(splitLine[0], splitLine[1]);
                    }
                }
            }

        } catch (FileNotFoundException e) {
            System.err.println("Unable to find file");
        } catch (IOException e) {
            System.err.println("Failed reading file" + ", message:" + e.getMessage());
            e.printStackTrace(System.err);
        } finally {
            try {
                if (br != null) {
                    //Closing file.
                    br.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file");
            }
        }
        return levelsList;
    }

    /**
     * Read lines with definitions of blocks and add  new block to new level.
     * Note: check validity(if exist) of definitions.
     *
     * @param levelsInfo   Is map of levels information.
     * @param br           Is a buffer reader.
     * @param linesCounter Is counter of lines of blocks.
     * @param levelsList   Is a List of levels to be set in the new level
     */
    public void readBlocks(Map<String, String> levelsInfo, BufferedReader br,
                           int linesCounter, List<LevelInformation> levelsList) {
        //Valid definitions of block.
        String[] definitions = {"level_name", "ball_velocities", "background", "paddle_speed", "paddle_width",
                "block_definitions", "blocks_start_x", "blocks_start_y", "row_height", "num_blocks"};
        InputStreamReader is = null;
        String line = null;
        try {
            //Check if all definitions are exist.
            checkDefinitionsExist(levelsInfo, definitions);
            //Create new level according to level information.
            Level newLevel = buildLevel(levelsInfo);
            is = new InputStreamReader(ClassLoader.getSystemClassLoader().getResourceAsStream(
                    levelsInfo.get("block_definitions")));
            blocks = BlocksDefinitionReader.fromReader(is);
            int yPosition = Integer.parseInt(levelsInfo.get("row_height"));
            line = br.readLine();
            while (!(line.equals("END_BLOCKS"))) {
                //Adding blocks to level.
                if (!(line.isEmpty()) && !(line.startsWith("#"))) {
                    newLevel.addBlocks(createBlocks(line, Integer.parseInt(levelsInfo.get("blocks_start_x")),
                            Integer.parseInt(levelsInfo.get("blocks_start_y"))
                                    + linesCounter * yPosition));
                    linesCounter++;
                }
                line = br.readLine();
            }
            //Insert level to the level list.
            levelsList.add(newLevel);
        } catch (Exception exp) {
            exp.printStackTrace(System.err);
            System.exit(1);
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file");
            }
        }
    }

    /**
     * Getting map of level informatinn,take ball_velocities key and split them into list of Velocities.
     *
     * @param level Is Map of level information.
     * @return List of Velocities.
     */
    public List<Velocity> splitVelocities(Map<String, String> level) {
        String velocities = level.get("ball_velocities");
        List<Velocity> velocityList = new ArrayList<Velocity>();
        String[] items = velocities.split(" ");
        for (int i = 0; i < items.length; i++) {
            String[] splitItem = items[i].split(",");
            //Create new velocity
            Velocity v = Velocity.fromAngleAndSpeed(Integer.parseInt(splitItem[0]), Integer.parseInt(splitItem[1]));
            //Add velocity to list.
            velocityList.add(v);
        }
        return velocityList;

    }

    /**
     * Create level according to definitions from map.
     *
     * @param level Is a map of level definitions,
     * @return new Level according to keys and value from map
     */
    public Level buildLevel(Map<String, String> level) {
        //Setting the parameters of new level according to Map.
        String name = level.get("level_name");
        int paddleSpeed = Integer.parseInt(level.get("paddle_speed"));
        int numBlocks = Integer.parseInt(level.get("num_blocks"));
        int paddleWidth = Integer.parseInt(level.get("paddle_width"));
        List<Velocity> velocityList = splitVelocities(level);
        Background background = getBackground(level.get("background"));
        return new Level(name, velocityList, paddleSpeed, paddleWidth, numBlocks, background);
    }

    /**
     * Getting String represent line of block,goes over the string check and create blocks and spacers according
     * to BlocksFromSymbolsFactory.
     *
     * @param s Is String represent line of block/spacers.
     * @param x Is the x position to set line of blocks.
     * @param y Is the y position to set line of blocks.
     * @return List of blocks according to String line(s).
     */
    public List<Block> createBlocks(String s, int x, int y) {
        List<Block> blocksList = new ArrayList<Block>();
        //Count the width of every block/spacer
        double widthCount = 0;
        //Loop goes over all chars in string.
        for (char ch : s.toCharArray()) {
            String stringValue = String.valueOf(ch);
            //Check if symbol is blocks symbol or space symbol.
            if (blocks.isBlockSymbol(stringValue)) {
                blocksList.add(blocks.getBlock(stringValue, x + (int) widthCount, y));
                widthCount += blocks.getBlock(stringValue, 0, 0).getWidth();
            } else if (blocks.isSpaceSymbol(stringValue)) {
                widthCount += blocks.getSpaceWidth(stringValue);
            }
        }
        return blocksList;

    }
}
