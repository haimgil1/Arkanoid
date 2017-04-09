/**
 * A MenuSelection class.
 *
 * @author Shurgil and barisya
 * @param <T> task of menu.
 */
public class MenuSelection<T> {

    private String key;
    private String message;
    private T returnVal;
/**
 * @param key key
 * @param message messege
 * @param returnVal return value
 */
    public MenuSelection(String key, String message, T returnVal) {
        this.key = key;
        this.message = message;
        this.returnVal = returnVal;
    }
/**
 * @return the key
 */
    public String getKey() {
        return this.key;
    }
/**
 * @return the messege
 */
    public String getMessage() {
        return this.message;
    }
/**
 * @return T value
 */
    public T getReturnVal() {
        return this.returnVal;
    }

}
