
import java.io.IOException;
/**
 * A ShowHiScoresTask class.
 *
 * @author Shurgil and barisya
 */
public class ShowHiScoresTask implements Task<Void> {
    private AnimationRunner runner;
    private Animation highScoresAnimation;
/**
 * @param runner animation runner.
 * @param highScoresAnimation high score animation.
 */
    public ShowHiScoresTask(AnimationRunner runner,
            Animation highScoresAnimation) {
        this.runner = runner;
        this.highScoresAnimation = highScoresAnimation;
    }
/**
 * run.
 * @return void.
 * @throws IOException throw.
 */
    public Void run() throws IOException {
        this.runner.run(this.highScoresAnimation);
        return null;
    }
/**
 * new stop.
 */
    public void newstop() {
        ((KeyPressStoppableAnimation) highScoresAnimation).newstop();
    }

}