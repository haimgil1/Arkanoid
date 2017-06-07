package animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * A KeyPressStoppableAnimation class.
 *
 * @author Nir Dunetz and Haim Gil.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor sensor;
    private String keyName;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * A Constructor given a keyboard, a key and animation.
     *
     * @param sensor    is a sensor.
     * @param key       is a key.
     * @param animation is animation.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.keyName = key;
        this.animation = animation;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    /**
     * @param d  is the surface.
     * @param dt is the frames per second.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        this.animation.doOneFrame(d, dt);

        // Pressed for the first time.
        if (this.sensor.isPressed(this.keyName) && !this.isAlreadyPressed) {
            this.stop = true;
        }

        if (!this.sensor.isPressed(this.keyName)) {
            this.isAlreadyPressed = false;
        }
    }

    /**
     * @return true when it should stop, false otherwise.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}