
import java.io.IOException;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
/**
 * A KeyPressStoppableAnimation class.
 *
 * @author Shurgil and barisya
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;
/**
 * @param sensor keyboard
 * @param key key press
 * @param animation animation
 */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key,
            Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt)
            throws IOException {
        this.animation.doOneFrame(d, dt);

        if (this.isAlreadyPressed) {
            this.isAlreadyPressed = false;
            return;
        }

        if (this.sensor.isPressed(this.key)) {
            this.stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
/**
 * new stop; intialize the should stop.
 */
    public void newstop() {
        this.stop = false;
    }

}