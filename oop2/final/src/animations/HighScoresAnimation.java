package animations;

import biuoop.DrawSurface;
import highscores.HighScoresTable;
import highscores.ScoreInfo;
import sprites.Background;
import sprites.BackgroundColor;

import java.awt.Color;

/**
 * A HighScoresAnimation class.
 *
 * @author Nir Dunetz and Haim Gil.
 */
public class HighScoresAnimation implements Animation {
    private HighScoresTable scores;

    /**
     * A Constructor given the HighScoresTable.
     *
     * @param scores is a scores table.
     */
    public HighScoresAnimation(HighScoresTable scores) {
        this.scores = scores;
    }

    /**
     * Doing frame of HighScores.
     * Note: showing the top 10 all time score list.
     *
     * @param d  is the surface.
     * @param dt is the frames per second.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        // Setting the background.
        Background b = new BackgroundColor(Color.gray);
        b.drawOn(d);

        // Setting the table.
        d.setColor(Color.YELLOW);
        d.drawText(250, 60, "High Scores", 32);
        d.setColor(Color.GREEN);
        d.drawText(100, 120, "Player Name", 24);
        d.setColor(Color.GREEN);
        d.drawText(450, 120, "Score", 24);
        d.setColor(Color.GREEN);
        d.drawLine(100, 131, 700, 131);
        d.setColor(Color.BLACK);
        for (int i = 0; i < this.scores.getHighScores().size(); i++) {
            ScoreInfo scoreInfo = this.scores.getHighScores().get(i);
            d.setColor(Color.ORANGE);
            d.drawText(100, 30 * i + 160, (i + 1) + ") " + scoreInfo.getName(), 28);
            d.setColor(Color.ORANGE);
            d.drawText(450, 30 * i + 160, String.valueOf(scoreInfo.getScore()), 24);
        }
        d.setColor(Color.BLACK);
        d.drawText(200, 500, "Press space to continue", 32);

    }

    /**
     * @return true when it should stop, false otherwise.
     */
    public boolean shouldStop() {
        return false;
    }
}