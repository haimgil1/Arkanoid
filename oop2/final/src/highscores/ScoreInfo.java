package highscores;

import java.io.Serializable;

/**
 * A ScoreInfo class.
 *
 * @author Nir Dunetz and Haim Gil.
 */
public class ScoreInfo implements Serializable {
    private String name;
    private int score;

    /**
     * A Constructor.
     *
     * @param name  is the name of the player.
     * @param score is the score's player.
     */
    public ScoreInfo(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * @return the name of the player.
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return the score's player
     */
    public int getScore() {
        return this.score;
    }
}