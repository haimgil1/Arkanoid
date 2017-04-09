import java.io.File;
import java.io.IOException;
import java.util.List;

import biuoop.DialogManager;
import biuoop.GUI;
import biuoop.KeyboardSensor;

/**
 * A GameFlow class.
 *
 * @author Shurgil and barisya
 */
public class GameFlow {
    private GUI gui;
    private Counter score;
    private Counter lives;
    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private HighScoresTable scoresTable;
    private File file;

    /**
     * the constructor.
     *
     * @param ar
     *            the animation runner
     * @param ks
     *            the keyboard.
     * @param gui2
     *            the gui.
     * @param file
     *            ths file we read
     * @param scoresTable
     *            the score table
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui2,
            HighScoresTable scoresTable, File file) {
        this.gui = gui2;
        this.animationRunner = ar;
        this.score = new Counter();
        this.lives = new Counter();
        this.lives.increase(7);
        this.keyboardSensor = ks;
        this.scoresTable = scoresTable;
        this.file = file;

    }

    /**
     * ron the levels.
     *
     * @param levels
     *            the list of levels.
     * @throws IOException
     *             throw
     */
    public void runLevels(List<LevelInformation> levels) throws IOException {

        LevelInformation levelInfo = levels.get(0);

        for (int i = 1;; i++) {

            levelInfo.setName("Level no. " + i);
            levelInfo.setNum(i);

            GameLevel level = new GameLevel(levelInfo, this.score, this.lives,
                    this.gui, this.keyboardSensor, this.animationRunner);

            level.initialize();

            while (level.getremainingBlocks().getValue() > level
                    .getnumofblocks() - level.getnumofblocktodestroy()
                    && level.getlives().getValue() > 0) {
                level.playOneTurn();
            }

            if (level.getlives().getValue() == 0) {

                this.animationRunner.run(new KeyPressStoppableAnimation(
                        this.keyboardSensor, KeyboardSensor.SPACE_KEY,
                        new EndScreen(this.lives, this.score)));
                break;
            }

        }

        this.animationRunner.run(new KeyPressStoppableAnimation(
                this.keyboardSensor, KeyboardSensor.SPACE_KEY, new EndScreen(
                        this.lives, this.score)));

        if (this.scoresTable.getRank(this.score.getValue()) != -1) {

            DialogManager dialog = this.gui.getDialogManager();
            String name = dialog.showQuestionDialog("Name",
                    "What is your name?", "");
            ScoreInfo newscore = new ScoreInfo(name, this.score.getValue());
            this.scoresTable.add(newscore);
            this.scoresTable.save(this.file);
        }

        HighScoresAnimation highscoreanimation = new HighScoresAnimation(
                this.scoresTable);

        this.animationRunner.run(new KeyPressStoppableAnimation(
                this.keyboardSensor, KeyboardSensor.SPACE_KEY,
                highscoreanimation));

    }
}
