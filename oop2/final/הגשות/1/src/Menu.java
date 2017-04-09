import java.util.List;
/**
 * A Menu class.
 *
 * @author Shurgil and barisya
 * @param <T> value
 */
public interface Menu<T> extends Animation {
/**
 * @param key key
 * @param message message
 * @param returnVal return value
 */
    void addSelection(String key, String message, T returnVal);
/**
 * @return return value
 */
    T getStatus();
/**
 * @param status statue
 */
    void setStatus(T status);
/**
 * @return list of menu selection.
 */
    @SuppressWarnings("rawtypes")
    List<MenuSelection> getMenuList();
/**
 * @param key key
 * @param message m
 * @param subMenu sub menu
 */
    void addSubMenu(String key, String message, Menu<T> subMenu);

}
