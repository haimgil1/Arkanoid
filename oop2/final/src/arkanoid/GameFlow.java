package arkanoid;

import animations.AnimationRunner;
import animations.EndScreen;
import animations.HighScoresAnimation;
import animations.KeyPressStoppableAnimation;
import biuoop.DialogManager;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import highscores.HighScoresTable;
import highscores.ScoreInfo;
import indicators.Counter;
import levels.LevelInformation;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * A GameFlow class.
 *
 * @author Nir Dunetz and Haim Gil.
 */
public class GameFlow {
    private GUI gui;
    private Counter score;
    private Counter lives;
    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private HighScoresTable highScoresTable;
    private File fileName;

    /**
     * A Constructor.
     *
     * @param ar              Is a AnimationRunner object to use.
     * @param ks              Is the keyboard.
     * @param gui             Ia a GUI.
     * @param highScoresTable is the high score table.
     * @param fileName        is the file.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui, HighScoresTable highScoresTable, File fileName) {
        this.animationRunner = ar;
        this.score = new Counter();
        this.lives = new Counter(7);
        this.keyboardSensor = ks;
        this.gui = gui;
        this.highScoresTable = highScoresTable;
        this.fileName = fileName;
    }

    /**
     * Getting list of levels and running them in order.
     *
     * @param levels Is a list of levels to run.
     * @throws IOException when failed to save file.
     */
    public void runLevels(List<LevelInformation> levels) throws IOException {

        // Goes over the levels in order.
        for (LevelInformation levelInfo : levels) {

            // Initializing each level.
            GameLevel level = new GameLevel(this.score, this.lives, this.animationRunner,
                    this.keyboardSensor, levelInfo);
            level.initialize();

            // Check if there is lives or blocks to continue the turn.
            while (level.getRemainingBlocks().getValue() > 0
                    && level.getLives().getValue() > 0) {
                level.playOneTurn();
            }

            // Check if there is lives left.
            if (level.getLives().getValue() == 0) {
                break;
            }
        }
        // Running the end screen animation.
        this.animationRunner.run(new KeyPressStoppableAnimation(
                this.keyboardSensor, "space", new EndScreen(this.lives, this.score)));

        // Adding to high score table.
        int rank = this.highScoresTable.getRank(this.score.getValue());
        if (rank <= this.highScoresTable.size()) {
            // Run animation with score After all levels are done.
            DialogManager dialog = gui.getDialogManager();
            String name = dialog.showQuestionDialog("Name", "What is your name?", "Anonymous");
            this.highScoresTable.add(new ScoreInfo(name, this.score.getValue()));
            try {
                this.highScoresTable.save(this.fileName);
            } catch (IOException io) {
                System.err.println("Failed saving file");
                io.printStackTrace(System.err);
            }
        }
        // Running the high score animation.
        this.animationRunner.run(new KeyPressStoppableAnimation(
                this.keyboardSensor, "space", new HighScoresAnimation(this.highScoresTable)));
    }
}

