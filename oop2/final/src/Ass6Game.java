import animations.AnimationRunner;
import animations.HighScoresAnimation;
import animations.KeyPressStoppableAnimation;
import animations.MenuAnimation;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import highscores.HighScoresTable;
import levels.LevelInformation;
import menu.Menu;
import menu.Task;
import read.LevelSetsInfo;
import read.LevelSetsReader;
import read.LevelSpecificationReader;
import arkanoid.GameFlow;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * An Ass6Game class.
 *
 * @author Nir Dunetz and Haim Gil.
 */
public class Ass6Game {
    private static final int GUI_WIDTH = 800;
    private static final int GUI_HEIGHT = 600;
    private String[] args;

    /**
     * Constructor.
     *
     * @param args is String array from the command line.
     */
    public Ass6Game(String[] args) {
        this.args = args;
    }

    /**
     * Trying to load a file table txt.
     *
     * @param file is the file.
     * @return the high scores table.
     * @throws Exception when failed to create file.
     */
    public HighScoresTable getHighScoresTable(File file) throws Exception {
        HighScoresTable scoresTable = new HighScoresTable(10);

        if (file.exists() && !file.isDirectory()) {
            scoresTable.load(file);
        } else {
            scoresTable.save(file);
        }
        return scoresTable;

    }

    /**
     * @return @return a path if getting in the command line and default path otherwise.
     */
    public String getPath() {
        String path;
        if (this.args.length > 0) {
            path = this.args[0];
        } else {
            path = "level_sets.txt";
        }
        return path;
    }

    /**
     * @param path is a path to a file.
     * @return list of the subMenu info.
     * @throws Exception when failed to open or closing file.
     */
    public List<LevelSetsInfo> getLevelSetsInfo(String path) throws Exception {
        InputStreamReader is = null;
        List<LevelSetsInfo> subsMenuInfo = null;
        LevelSetsReader levelSet = new LevelSetsReader();
        try {
            is = new InputStreamReader(ClassLoader.getSystemClassLoader().getResourceAsStream((path)));
            subsMenuInfo = levelSet.fromReader(is);
        } catch (RuntimeException o) {
            System.err.println("Failed open file");
            o.printStackTrace(System.err);
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file");
            }
        }
        return subsMenuInfo;
    }

    /**
     * Adding the sub menu.
     *
     * @param subsMenuInfo    is a list contain all the information of sub menu.
     * @param levelSetMenu    is the sub menu.
     * @param animationRunner is the animation runner.
     * @param ks              is a sensor.
     * @param gui             is the gui.
     * @param highScoresTable is the high score table.
     * @param file            is a file.
     * @throws Exception when failed to open or closing file.
     */
    public void addingSubMenu(List<LevelSetsInfo> subsMenuInfo, Menu<Task<Void>> levelSetMenu,
                              final AnimationRunner animationRunner, final KeyboardSensor ks,
                              final GUI gui, final HighScoresTable highScoresTable, final File file) throws Exception {

        // Creating the levels.
        for (LevelSetsInfo subsInfo : subsMenuInfo) {
            InputStreamReader is = null;
            String fileName = subsInfo.getPath();
            List<LevelInformation> li = null;
            try {
                is = new InputStreamReader(ClassLoader.getSystemClassLoader().getResourceAsStream((fileName)));
                li = new LevelSpecificationReader().fromReader(is);
            } catch (RuntimeException o) {
                System.err.println("Failed open file");
                o.printStackTrace(System.err);
            } finally {
                try {
                    if (is != null) {
                        is.close();
                    }
                } catch (IOException e) {
                    System.err.println("Failed closing file");
                }
            }
            final List<LevelInformation> levelInformation = li;
            levelSetMenu.addSelection(subsInfo.getKey(), subsInfo.getMessage(), new Task<Void>() {
                /**
                 * running the sub menu levels.
                 * @return null.
                 */
                @Override
                public Void run() {
                    GameFlow game = new GameFlow(animationRunner, ks, gui, highScoresTable, file);
                    //Running the game according to levels list.
                    try {
                        game.runLevels(levelInformation);
                    } catch (IOException io) {
                        System.err.println("Failed playing levels");
                        io.printStackTrace(System.err);
                    }
                    return null;
                }
            });
        }
    }

    /**
     * Starting the game.
     *
     * @throws Exception when failed to open or closing file.
     */
    public void startGame() throws Exception {
        // Create the GUI.
        final GUI gui = new GUI("Arkanoid", GUI_WIDTH, GUI_HEIGHT);
        final File file = new File("highscores.txt");
        final AnimationRunner animationRunner = new AnimationRunner(gui);
        final KeyboardSensor keyboardSensor = gui.getKeyboardSensor();
        final HighScoresTable highScoresTable = this.getHighScoresTable(file);

        String path = getPath();
        List<LevelSetsInfo> subsMenuInfo = getLevelSetsInfo(path);

        Menu<Task<Void>> levelSetMenu = new MenuAnimation<Task<Void>>("Level Sets", keyboardSensor, animationRunner);

        addingSubMenu(subsMenuInfo, levelSetMenu, animationRunner, keyboardSensor, gui, highScoresTable, file);
        runningMenu(levelSetMenu, keyboardSensor, animationRunner, highScoresTable);

    }

    /**
     * Running the menu.
     *
     * @param levelSetMenu    is the sub menu.
     * @param keyboardSensor  is the sensor.
     * @param animationRunner is the runner.
     * @param highScoresTable is the high score table.
     */
    public void runningMenu(Menu<Task<Void>> levelSetMenu, final KeyboardSensor keyboardSensor,
                            final AnimationRunner animationRunner, final HighScoresTable highScoresTable) {
        Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>("Arkanoid", keyboardSensor, animationRunner);
        menu.addSubMenu("s", "Start Game", levelSetMenu);

        menu.addSelection("h", "High Scores", new Task<Void>() {

            /**
             *  Running the the animations.
             * @return null.
             */
            @Override
            public Void run() {
                animationRunner.run(new KeyPressStoppableAnimation(keyboardSensor, "space",
                        new HighScoresAnimation(highScoresTable)));
                return null;
            }
        });

        menu.addSelection("q", "Quit", new Task<Void>() {

            @Override
            public Void run() {
                System.exit(0);
                return null;
            }
        });
        while (true) {
            animationRunner.run(menu);
            // wait for user selection
            Task<Void> task = menu.getStatus();
            task.run();
            menu.setAnimation();
        }
    }

    /**
     * Ass5 game main method.
     *
     * @param args is getting a path.
     *             <p/>
     *             Example usage:
     *             java -cp biuoop-1.4.jar:. Ass6Game level_sets.txt
     */
    public static void main(String[] args) {

        Ass6Game game = new Ass6Game(args);
        try {
            game.startGame();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
