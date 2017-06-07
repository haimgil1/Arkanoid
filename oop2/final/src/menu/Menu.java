package menu;

import animations.Animation;

/**
 *
 * A Menu interface.
 *
 * @author Nir Dunetz and Haim Gil.
 * @param <T> is void.
 */
public interface Menu<T> extends Animation {

    /**
     * Adding selection to the menu.
     *
     * @param key       is the key that need to be pressed to turn off the animation.
     * @param message   is the message selection.
     * @param returnVal is the animation.
     */
    void addSelection(String key, String message, T returnVal);

    /**
     * @return the status of the animation.
     */
    T getStatus();

    /**
     * Setting the animation after the key was pressed.
     */
    void setAnimation();

    /**
     * Adding selection to the sub menu.
     *
     * @param key     is the key that need to be pressed to turn off the animation.
     * @param message is the message selection.
     * @param subMenu is the sub menu.
     */
    void addSubMenu(String key, String message, Menu<T> subMenu);
}