package animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import menu.Menu;
import menu.SelectionMenuInfo;
import sprites.Background;
import sprites.BackgroundColor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * A MenuAnimation class.
 *
 * @param <T> is void.
 * @author Nir Dunetz and Haim Gil.
 */
public class MenuAnimation<T> implements Menu<T> {

    private String menuName;
    private List<SelectionMenuInfo<T>> menuInfo;
    private T status;
    private AnimationRunner runner;
    private KeyboardSensor sensor;
    private boolean done;
    private List<Menu<T>> subMenus;

    /**
     * A Constructor given a title, a keyboard and animation.
     *
     * @param menuName is the title.
     * @param sensor   is a sensor.
     * @param runner   is animation.
     */
    public MenuAnimation(String menuName, KeyboardSensor sensor, AnimationRunner runner) {
        this.menuName = menuName;
        this.sensor = sensor;
        this.runner = runner;
        this.menuInfo = new ArrayList<SelectionMenuInfo<T>>();
        this.done = false;
        this.status = null;
        this.subMenus = new ArrayList<Menu<T>>();
    }

    /**
     * Adding selection to the menu.
     *
     * @param key       is the key that need to be pressed to turn off the animation.
     * @param message   is the message selection.
     * @param returnVal is the animation.
     */
    public void addSelection(String key, String message, T returnVal) {
        SelectionMenuInfo<T> menuInformation = new SelectionMenuInfo<T>(key, message, returnVal);
        this.menuInfo.add(menuInformation);
        this.subMenus.add(null);

    }

    /**
     * Adding selection to the sub menu.
     *
     * @param key     is the key that need to be pressed to turn off the animation.
     * @param message is the message selection.
     * @param subMenu is the sub menu.
     */
    public void addSubMenu(String key, String message, Menu<T> subMenu) {
        SelectionMenuInfo<T> menuInformation = new SelectionMenuInfo<T>(key, message, null);
        this.menuInfo.add(menuInformation);
        this.subMenus.add(subMenu);
    }

    /**
     * @return the status of the animation.
     */
    public T getStatus() {
        return this.status;
    }

    /**
     * Drawing the menu animaion.
     *
     * @param d  is the surface.
     * @param dt is the frames per second.
     */
    public void doOneFrame(DrawSurface d, double dt) {

        // Setting the background.
        Background b = new BackgroundColor(Color.gray);
        b.drawOn(d);

        // Setting the animation.
        d.setColor(Color.YELLOW);
        d.drawText(50, 50, this.menuName, 32);

        // Drawing the selection.
        for (int i = 0; i < this.menuInfo.size(); i++) {
            d.setColor(Color.GREEN);
            d.drawText(100, 120 + i * 40, "(" + this.menuInfo.get(i).getKey() + ") "
                    + this.menuInfo.get(i).getMessage(), 24);
        }

        // Turning on the animation according to the key that pressed.
        for (int i = 0; i < this.menuInfo.size(); i++) {
            if (this.sensor.isPressed(this.menuInfo.get(i).getKey())) {
                if (this.subMenus.get(i) == null) {
                    this.status = this.menuInfo.get(i).getReturnVal();
                } else {
                    this.runner.run(this.subMenus.get(i));
                    this.status = this.subMenus.get(i).getStatus();
                    this.subMenus.get(i).setAnimation();
                }
                this.done = true;
                break;
            }
        }
    }

    /**
     * @return true when it should stop, false otherwise.
     */
    public boolean shouldStop() {
        return this.done;
    }

    /**
     * Setting the animation after the key was pressed.
     */
    public void setAnimation() {
        this.status = null;
        this.done = false;
    }
}
