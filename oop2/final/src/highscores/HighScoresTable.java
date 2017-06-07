package highscores;

import java.io.ObjectInputStream;
import java.io.Serializable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * A HighScoresTable class.
 *
 * @author Nir Dunetz and Haim Gil.
 */
public class HighScoresTable implements Serializable {
    private int sizeHighScores;
    private List<ScoreInfo> highScores;

    /**
     * Create an empty high-scores table with the specified size.
     * The size means that the table holds up to size top scores.
     *
     * @param size is the size of the table.
     */
    public HighScoresTable(int size) {
        this.highScores = new ArrayList<ScoreInfo>(size + 1);
        this.sizeHighScores = size;
    }

    /**
     * Read a table from file and return it.
     * If the file does not exist, or there is a problem with
     * reading it, an empty table is returned.
     *
     * @param filename is the file that load the table.
     * @return the high score table.
     */
    public static HighScoresTable loadFromFile(File filename) {

        HighScoresTable scoresTable = null;

        ObjectInputStream objectInputStream = null;
        try {

            objectInputStream = new ObjectInputStream(new FileInputStream(
                    filename));

            scoresTable = (HighScoresTable) objectInputStream.readObject();

            return scoresTable;
        } catch (FileNotFoundException e) {
            System.err.println("Unable to find file: " + filename);
            return new HighScoresTable(10);
        } catch (ClassNotFoundException e) {
            System.err.println("Unable to find class for object in file: "
                    + filename);
            return new HighScoresTable(10);
        } catch (IOException e) {
            System.err.println("Failed reading object");
            e.printStackTrace(System.err);
            return new HighScoresTable(10);
        } finally {
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + filename);
            }
        }
    }

    /**
     * Add a high-score.
     *
     * @param score is the score info.
     */
    public void add(ScoreInfo score) {
        // Adding to list.
        this.highScores.add(score);
        Collections.sort(this.highScores, new Comparator<ScoreInfo>() {

            /**
             * Override the compere function to support sort of ScoreInfo object.
             *
             * @param score1 is a score info.
             * @param score2 is a score info.
             * @return >0 if the first is bigger, 0 if equals and <0 otherwise.
             */
            @Override
            public int compare(ScoreInfo score1, ScoreInfo score2) {
                return score2.getScore() - score1.getScore();
            }
        });
        // Remove from table if needed.
        if (this.highScores.size() > this.size()) {
            this.highScores.remove(this.sizeHighScores);
        }
    }

    /**
     * @return table size.
     */
    public int size() {
        return this.sizeHighScores;
    }

    /**
     * @return the current high scores.
     * The list is sorted such that the highest scores come first.
     */
    public List<ScoreInfo> getHighScores() {
        return this.highScores;
    }

    /**
     * Checking if a score is good enough to ente the high scores table.
     *
     * @param score is the score.
     * @return the rank of the current score.
     * Rank 1 means the score will be highest on the list.
     * Rank `size` means the score will be lowest.
     * Rank > `size` means the score is too low and will not
     * be added to the list.
     */
    public int getRank(int score) {

        if (this.highScores.isEmpty()) {
            return 1;
        }

        int i;
        for (i = 0; i < this.highScores.size(); i++) {
            if (this.highScores.get(i).getScore() < score) {
                return i + 1;
            }
        }

        if (i < this.sizeHighScores) {
            return i + 1;
        }
        return this.sizeHighScores + 1;
    }

    /**
     * Clears the table.
     */
    public void clear() {
        this.highScores.clear();
    }

    /**
     * Load table data from file.
     * Current table data is cleared
     *
     * @param filename is the file
     * @throws IOException when failed finding the class.
     */
    public void load(File filename) throws IOException {
        this.sizeHighScores = HighScoresTable.loadFromFile(filename).size();
        this.highScores = HighScoresTable.loadFromFile(filename)
                .getHighScores();
    }

    /**
     * Save table data to the specified file.
     *
     * @param filename is the file.
     * @throws IOException when failed saving or closing file.
     */
    public void save(File filename) throws IOException {
        ObjectOutputStream os = null;
        try {
            os = new ObjectOutputStream(new FileOutputStream(filename));
            os.writeObject(this);
        } catch (IOException e) {
            System.err.println("Failed saving file");
            e.printStackTrace(System.err);
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + filename);
            }
        }
    }
}
