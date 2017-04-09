import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

import biuoop.GUI;
import biuoop.KeyboardSensor;
/**
 * A Ass6Game class. - main.
 *
 * @author Shurgil and barisya
 */
public class Ass6Game {
    /**
     * the main project.
     *
     * @param args
     *            from command line.
     * @throws IOException trow
     */
    @SuppressWarnings({ "unchecked" })
    public static void main(String[] args) throws IOException {

        GUI gui = new GUI("title", 800, 600);
        AnimationRunner animationRunner = new AnimationRunner(gui, 60);
        KeyboardSensor keyboardSensor = gui.getKeyboardSensor();
        HighScoresTable scoresTable = new HighScoresTable(10);
        InputStream is = null;

        File file = new File("highscores.txt");
        if (file.exists() && !file.isDirectory()) {
            scoresTable.load(file);
        } else {
            scoresTable.save(file);
        }
        HighScoresAnimation scoreanimation = new HighScoresAnimation(
                scoresTable);

        LineNumberReader reader;
        if (args.length > 0) {
            is = ClassLoader.getSystemClassLoader().getResourceAsStream(args[0]);
        } else {
            is = ClassLoader.getSystemClassLoader().getResourceAsStream("level_sets.txt");
        }

        reader = new LineNumberReader(new InputStreamReader(is));
        SubMenuRead check = new SubMenuRead(reader, keyboardSensor,
                animationRunner, gui, scoresTable, file);
        Menu<Task<Void>> menu2 = check.getmenu();
        reader.close();

        Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>(keyboardSensor);
        menu.addSubMenu("s", "Game", menu2);
        menu.addSelection("h", "High Scores", new ShowHiScoresTask(
                animationRunner, new KeyPressStoppableAnimation(keyboardSensor,
                        KeyboardSensor.SPACE_KEY, scoreanimation)));
        menu.addSelection("q", "Quit", new Quit());

        while (true) {
            animationRunner.run(menu);
            // wait for user selection
            Task<Void> task = menu.getStatus();
            task.run();

            ((MenuAnimation<Task<Void>>) menu).newstop();
            for (int i = 0; i < menu.getMenuList().size(); i++) {
                ((Task<Void>) menu.getMenuList().get(i).getReturnVal())
                        .newstop();
            }
        }

    }

}
