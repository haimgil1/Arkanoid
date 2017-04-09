import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
/**
 * A MenuAnimation class.
 *
 * @author Shurgil and barisya
 * @param <T> value
 */
public class MenuAnimation<T> implements Menu<T> {

    @SuppressWarnings("rawtypes")
    private List<MenuSelection> menuList;
    private T status;
    private KeyboardSensor sensor;
    private boolean stop;
/**
 * @param sensor keyboard
 */
    @SuppressWarnings("rawtypes")
    public MenuAnimation(KeyboardSensor sensor) {
        this.menuList = new ArrayList<MenuSelection>();
        this.sensor = sensor;
        this.stop = false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void doOneFrame(DrawSurface d, double dt) {

        for (int i = 0; i < this.menuList.size(); i++) {
            String k = this.menuList.get(i).getKey();
            String n = this.menuList.get(i).getMessage();
            d.drawText(200, 300 + i * 32, k + " - " + n, 32);
        }

        for (int j = 0; j < this.menuList.size(); j++) {

            if (this.sensor.isPressed(this.menuList.get(j).getKey())) {
                this.status = (T) this.menuList.get(j).getReturnVal();
                this.stop = true;
            }

        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
/**
 * new stop method.
 */
    public void newstop() {
        this.stop = false;
    }

    @Override
    public void addSelection(String key, String message, T returnVal) {
        MenuSelection<T> select = new MenuSelection<T>(key, message, returnVal);
        this.menuList.add(select);
    }

    @Override
    public T getStatus() {
        return this.status;
    }
/**
 * @return list of menu selction.
 */
    @SuppressWarnings("rawtypes")
    public List<MenuSelection> getMenuList() {
        return this.menuList;
    }
/**
 * @param status value.
 */
    @Override
    public void setStatus(T status2) {
        this.status = status2;
    }

    @Override
    public void addSubMenu(String key, String message, Menu<T> subMenu) {

        @SuppressWarnings("unchecked")
        MenuSelection<T> select = new MenuSelection<T>(key, message,
                (T) subMenu);
        this.menuList.add(select);

    }

}
