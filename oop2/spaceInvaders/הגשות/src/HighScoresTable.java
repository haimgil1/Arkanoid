import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * A HighScoresTable class.
 *
 * @author Shurgil and barisya
 */
@SuppressWarnings("serial")
class HighScoresTable implements Serializable {
    private int size;
    private List<ScoreInfo> highScores;
/**
 * // Create an empty high-scores table with the specified size.
    // The size means that the table holds up to size top scores.
 * @param size size of table
 */
    public HighScoresTable(int size) {
        this.highScores = new ArrayList<ScoreInfo>();
        this.size = size;
    }
/**
 * Add a high-score.
 * @param score score.
 */
    public void add(ScoreInfo score) {

        this.highScores.add(score);

        // sort

        for (int i = 0; i < this.highScores.size(); i++) {
            // for each element in the list
            for (int j = i + 1; j < this.highScores.size(); j++) {
                // if a number is bigger than a number
                // that's next on the list, swap them
                if (this.highScores.get(j).getScore() > this.highScores.get(i)
                        .getScore()) {
                    ScoreInfo temp = this.highScores.get(j);
                    this.highScores.set(j, this.highScores.get(i));
                    this.highScores.set(i, temp);
                }
            }
        }
    }
/**
 * Return table size.
 * @return size of table
 */
    public int size() {
        return this.size;
    }
/**
 * // Return the current high scores.
    // The list is sorted such that the highest
    // scores come first.
 * @return list of hgih score
 */
    public List<ScoreInfo> getHighScores() {
        return this.highScores;
    }
/**
    // return the rank of the current score: where will it
    // be on the list if added?
    // Rank 1 means the score will be highest on the list.
    // Rank `size` means the score will be lowest.
    // Rank > `size` means the score is too low and will not
    // be added to the list.
 * @param score score
 * @return rank in table
 */
    public int getRank(int score) {
        if (this.highScores.isEmpty()) {
            return 1;
        }
        for (int i = 0; i < this.highScores.size(); i++) {
            if (score > this.highScores.get(i).getScore()) {
                return i + 1;
            }
        }
        return -1; // not be added
    }
/**
 *  Clears the table.
 */
    public void clear() {
        this.highScores.clear();
    }
/**
 *  // Load table data from file.
    // Current table data is cleared.
 * @param filename name
 * @throws IOException not found
 */
    public void load(File filename) throws IOException {
        this.size = HighScoresTable.loadFromFile(filename).size();
        this.highScores = HighScoresTable.loadFromFile(filename)
                .getHighScores();
    }
/**
 * Save table data to the specified file.
 * @param filename name
 * @throws IOException thow
 */
    public void save(File filename) throws IOException {

        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(
                    filename));
            objectOutputStream.writeObject(this);
        } catch (IOException e) {
            System.err.println("Failed saving object");
            e.printStackTrace(System.err);
        } finally {
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + filename);
            }
        }
    }
/**
 * // Read a table from file and return it.
    // If the file does not exist, or there is a problem with
    // reading it, an empty table is returned.
 * @param filename name
 * @return high score table
 */
    public static HighScoresTable loadFromFile(File filename) {

        HighScoresTable newTable = null;

        ObjectInputStream objectInputStream = null;
        try {

            objectInputStream = new ObjectInputStream(new FileInputStream(
                    filename));

            // unsafe down casting, we better be sure that the stream really
            // contains a HighScoresTable!
            newTable = (HighScoresTable) objectInputStream.readObject();

            return newTable;
        } catch (FileNotFoundException e) { // Can't find file to open
            System.err.println("Unable to find file: " + filename);
            return new HighScoresTable(10);
        } catch (ClassNotFoundException e) { // The class in the stream is
                                                // unknown to the JVM
            System.err.println("Unable to find class for object in file: "
                    + filename);
            return new HighScoresTable(10);
        } catch (IOException e) { // Some other problem
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

}