import java.io.Serializable;
/**
 * A ScoreInfo class.
 *
 * @author Shurgil and barisya
 */
@SuppressWarnings("serial")
public class ScoreInfo implements Serializable {
    private String name;
    private int score;
/**
 * @param name name.
 * @param score score.
 */
    public ScoreInfo(String name, int score) {
        this.name = name;
        this.score = score;
    }
/**
 * @return name.
 */
    public String getName() {
        return this.name;
    }
/**
 * @return score.
 */
    public int getScore() {
        return this.score;
    }
/**
 * to string.
 * @return string.
 */
    public String toString() {
        return "name = " + this.name + " - " + " score = " + this.score;
    }
}
